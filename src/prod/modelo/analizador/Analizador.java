/**
 * Clase estatica (no puede ser instanciada) que contiene metodos
 * para comparar y analizar jugadas, manos, o listas de prod.baraja.
 *
 * Para llamarla, Analizador.analizarMano();
 *
 * Las funciones deberian mantenerse puras (que no cambien el input)
 * por si luuego queremos meter threads.
 */

package prod.modelo.analizador;

import prod.baraja.Carta;
import prod.baraja.E_Carta_Valor;
import prod.modelo.excepciones.EAnalizador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public final class Analizador {

    //-----------------------
    // CAMPOS Y CONSTRUCTORES
    //-----------------------

    /**
     * Constructor privado que permite que esta sea una clase estatica.
     */
    private Analizador() {

    }

    //_-----------
    //Testing
    //_------------
    public static List<List<Carta>> combinaPublic(List<Carta> lista1, int amount1,
                                                  List<Carta> lista2, int amount2) {
        return combinarCartasDeListas(lista1, amount1, lista2, amount2);
    }

    //-----------------------
    // METODOS PROPIOS
    //-----------------------

    /**
     * Metodo para calcular quien gana de una lista de jugadores.
     * Acepta una lista de manos de longitud 2, y las cartas del board.
     *
     * @param jugadores Lista de manos de jugadores. Cada una tiene que tener longitud 2.
     * @param mesa Lista de cartas comunes. Tiene que tener longiud 5.
     * @return
     */
    public static int[] calcularGanadoresHoldem(ArrayList<ArrayList<Carta>> jugadores, ArrayList<Carta> mesa) throws EAnalizador {

        //Comprobar que las manos y la mesa son de la longitud adecuada.
        if (mesa.size() != 5) {
            throw  new EAnalizador("La mesa no tiene el número suficiente de cartas: " + mesa.size());
        } else {
            for (List<Carta> cartas : jugadores) { //Puede optimizarse mentiendolo en el loop
                if (cartas.size() != 2) {
                    //Es ineficiente buscar el indice asi, pero si entra aqui ya da igual la eficiencia.
                    throw  new EAnalizador("La mano del jugador" + jugadores.indexOf(cartas) +
                            " no tiene el número suficiente de cartas: " + cartas.size());
                }
            }
        }

        //Lista que contiene todas las jugadas, en el mismo orden que jugadores
        List<JugadaValor> jugadas = new ArrayList<JugadaValor>(jugadores.size());

        //Por cada mano, calcular la mejor jugada.
        for (List<Carta> jugador : jugadores) {
            jugadas.add(Analizador.analizaJugadorHoldem(jugador, mesa));
        }

        //Calcular cual es la mejor jugada.
        JugadaValor mejorJugada = jugadas.get(0);
        int ganadorTmp = 0; //El jugador que va ganando

        int[] ganadores = new int[jugadores.size()];//Guarda los indices en jugadores de los ganadore
        int ganadorIndex = 0;//Index utilizado para insertar en ganadores[]

        for (int i = 0; i < jugadas.size(); i++) {
            int res = comparaJugadas(jugadas.get(i), mejorJugada);
            if (res == 1) { //Hay un nuevo ganador

                mejorJugada = jugadas.get(i);
                ganadorTmp = i;

                //Resetea los ganadores
                for (int cleaner = 0; cleaner < ganadorIndex; cleaner++){
                    ganadores[cleaner] = -1;
                }
                ganadores[0] = ganadorTmp;
                ganadorIndex = 1;

            } else if (res == 0) { //Hay empate
                ganadores[ganadorIndex] = i;
                ganadorIndex++;
            }
        }
        int[] resultado = new int[ganadorIndex];
        for (int i = 0; i < resultado.length; i++) {
            resultado[i] = ganadores[i];
        }
        //Devolver valor de los ganadores
        return resultado;
    }
    
    /**
     * Metodo para calcular quien gana de una lista de jugadores.
     * Acepta una lista de manos de longitud 2, y las cartas del board.
     *
     * @param jugadores Lista de manos de jugadores. Cada una tiene que tener longitud 2.
     * @param mesa Lista de cartas comunes. Tiene que tener longiud 5.
     * @return
     */

    public static int[] calcularGanadoresOmaha(ArrayList<ArrayList<Carta>> jugadores, ArrayList<Carta> mesa) throws EAnalizador {

        //Comprobar que las manos y la mesa son de la longitud adecuada.
        if (mesa.size() != 5) {
            throw  new EAnalizador("La mesa no tiene el número suficiente de cartas: " + mesa.size());
        } else {
            for (List<Carta> cartas : jugadores) { //Puede optimizarse mentiendolo en el loop
                if (cartas.size() != 4) {
                    //Es ineficiente buscar el indice asi, pero si entra aqui ya da igual la eficiencia.
                    throw  new EAnalizador("La mano del jugador" + jugadores.indexOf(cartas) +
                            " no tiene el número suficiente de cartas: " + cartas.size());
                }
            }
        }

        //Lista que contiene todas las jugadas, en el mismo orden que jugadores
        List<JugadaValor> jugadas = new ArrayList<JugadaValor>(jugadores.size());

        //Por cada mano, calcular la mejor jugada.
        for (List<Carta> jugador : jugadores) {
            jugadas.add(Analizador.analizaJugadorOmaha(jugador, mesa));
        }

        //Calcular cual es la mejor jugada.
        JugadaValor mejorJugada = jugadas.get(0);
        int ganadorTmp = 0; //El jugador que va ganando

        int[] ganadores = new int[jugadores.size()];//Guarda los indices en jugadores de los ganadore
        int ganadorIndex = 0;//Index utilizado para insertar en ganadores[]

        for (int i = 0; i < jugadas.size(); i++) {
            int res = comparaJugadas(jugadas.get(i), mejorJugada);
            if (res == 1) { //Hay un nuevo ganador

                mejorJugada = jugadas.get(i);
                ganadorTmp = i;

                //Resetea los ganadores
                for (int cleaner = 0; cleaner < ganadorIndex; cleaner++){
                    ganadores[cleaner] = -1;
                }
                ganadores[0] = ganadorTmp;
                ganadorIndex = 1;

            } else if (res == 0) { //Hay empate
                ganadores[ganadorIndex] = i;
                ganadorIndex++;
            }
        }
        int[] resultado = new int[ganadorIndex];
        for (int i = 0; i < resultado.length; i++) {
            resultado[i] = ganadores[i];
        }
        //Devolver valor de los ganadores
        return resultado;
    }


    /**
     * Metodo que coge las posibles manos que se pueden formar entre las prod.baraja del jugador
     * y las de la mesa, y devuelve una lista de jugadas que tiene en total el jugador.
     * @param jugadorHoldem
     * @param mesa
     * @return
     */

    public static JugadaValor analizaJugadorHoldem(List<Carta> jugadorHoldem, List<Carta> mesa) {

        List<List<Carta>> combinaciones = new ArrayList<List<Carta>>();

        if (mesa.size() >= 5) {
            combinaciones.addAll(combinarCartasDeListas(jugadorHoldem, 0, mesa, 5));
        }
        if (mesa.size() >= 4) {
            combinaciones.addAll(combinarCartasDeListas(jugadorHoldem, 1, mesa, 4));
        }
        combinaciones.addAll(combinarCartasDeListas(jugadorHoldem, 2, mesa, 3));

        @SuppressWarnings("unused")
		JugadaValor jugada, mejorJugada = new JugadaValor(E_Jugada_Tipo.NADA, null, null), jugadaAux = mejorJugada;

        for (List<Carta> mano : combinaciones) {
            jugada = analizaMano(mano);
            if (comparaJugadas(jugada, mejorJugada) == 1) {
                jugadaAux = mejorJugada;
                mejorJugada = jugada;                
            }else {
            	jugadaAux = jugada;
            }
        }

        return mejorJugada;
    }
    
    /**
     * Metodo que coge las posibles manos que se pueden formar entre las cartas del jugador
     * y las de la mesa, y devuelve una lista de jugadas que tiene en total el jugador.
     * @param j
     * @param mesa
     * @return
     */
    public static JugadaValor analizaJugadorOmaha(List<Carta> jugadorOmaha, List<Carta> mesa) {

    	List<List<Carta>> combinaciones = new ArrayList<List<Carta>>();

        combinaciones.addAll(combinarCartasDeListas(jugadorOmaha, 2, mesa, 3));

        JugadaValor jugada, mejorJugada = new JugadaValor(E_Jugada_Tipo.NADA, null, null), jugadaAux = mejorJugada;

        for (List<Carta> mano : combinaciones) {
            jugada = analizaMano(mano);
            if (comparaJugadas(jugada, mejorJugada) == 1) {
                jugadaAux = mejorJugada;
                mejorJugada = jugada;              
            } else {
            	jugadaAux = jugada;
            }
            if (!mejorJugada.getFlushDraw())
                mejorJugada.setFlushDraw(jugadaAux.getFlushDraw());
            if (!mejorJugada.getGutShot())
                mejorJugada.setGutShot(jugadaAux.getGutShot());
            if (!jugada.getOESD())
                mejorJugada.setOESD(jugadaAux.getOESD());
            if (!jugada.getStraightDraw())
                mejorJugada.setOESD(jugadaAux.getStraightDraw());
        }
       
        return mejorJugada;
    }

	/**
     * Metodo que coge las posibles manos que se pueden formar entre las prod.baraja del jugador
     * y las de la mesa, y devuelve una lista de jugadas que tiene en total el jugador.
     * @param j
     * @param mesa
     * @return
     */
    /*
    public static JugadaValor analizaJugadorOmaha(JugadorOmaha j, List<Carta> mesa) {

        List<Mano> combinaciones = new ArrayList<Mano>();

        combinaciones.addAll(combinarCartasDeListas(j.getCartas(), 2, mesa, 3));

        JugadaValor jugada, mejorJugada = new JugadaValor(E_Jugada_Tipo.NADA, null, null), jugadaAux = mejorJugada;

        for (Mano mano : combinaciones) {
            jugada = analizaMano(mano);
            if (comparaJugadas(jugada, mejorJugada) == 1) {
                jugadaAux = mejorJugada;
                mejorJugada = jugada;              
            } else {
            	jugadaAux = jugada;
            }
            if (!mejorJugada.getFlushDraw())
                mejorJugada.setFlushDraw(jugadaAux.getFlushDraw());
            if (!mejorJugada.getGutShot())
                mejorJugada.setGutShot(jugadaAux.getGutShot());
            if (!jugada.getOESD())
                mejorJugada.setOESD(jugadaAux.getOESD());
            if (!jugada.getStraightDraw())
                mejorJugada.setOESD(jugadaAux.getStraightDraw());
        }
        j.setJugada(mejorJugada);
        return mejorJugada;
    }*/

	/**
	 * Metodo que analiza la mano que le pasas, y devuelve una lusta de jugadas.
     *
     * La implementación se basa en hacer un solo traversal por la mano (ordenada), usando variables intermedias
     * para guardar informacion de la mano. Después, se interpreta esta informacion para llenar un objeto JugadaValor
     * con la información correspondiente.
     *
     * Por cada carta, analiza sus propiedades de parejas, color y escaleras por separado.
     *
	 * @param cartas Mano a analizar.
	 * @return Jugada con todas las jugadas de la mano
	 *
	 * Precondicion: m tiene como maximo 5 prod.baraja.
	 * Postcondicion: La lista de retorno tiene la mejor mano como primer elemento
	 */

	public static JugadaValor analizaMano(List<Carta> cartas) {

        // Ordenar las prod.baraja de mayor a menor por su valor
        Collections.sort(cartas);

        //PASO 1 : Iterar sobre la mano y guardar informacion necesaria
        Carta anterior = cartas.get(0);

	    int[] cartasDePalo = {0, 0, 0, 0}; // = {#clubs, #hearts, #diamonds, #spades}

        //Analiza la primera carta de la mano
        cartasDePalo[anterior.getPalo().getIdnum()]++;

        //Variables para analizar jugadas con parejas (Pair+)
        E_Jugada_Tipo jugadaParejas = E_Jugada_Tipo.HIGH_CARD;
        ArrayList<Carta> valoresDeJugadasParejas = new ArrayList<Carta>();
        //Inicializamos valoresDeJugadas... con solo dos elementos
        valoresDeJugadasParejas.add(anterior);
        valoresDeJugadasParejas.add(null);
        int cartasIgualesSeguidas = 1;
        int indexDeParejas = 0; //Index para saber cuantas parejas llevamos

        //Variables para analizar jugadas con straight
        boolean puedeHaberProyectoEscalera = true; 
        boolean hayUnHueco = false; 
        int cartasEnElProyecto = 1; 
        boolean puedeHaberRueda = false;

        for (int i = 1; i < cartas.size(); i++) {
            //Cogemos la siguiente carta a analizar
            Carta current = cartas.get(i);

            //PAREJAS + 
            if (current.getValor() == anterior.getValor()) {

                if (jugadaParejas == E_Jugada_Tipo.HIGH_CARD) {
                    jugadaParejas = E_Jugada_Tipo.PAIR;
                    valoresDeJugadasParejas.set(indexDeParejas, current);
                } else if (jugadaParejas == E_Jugada_Tipo.PAIR) {
                    if (indexDeParejas > 0) { //Ya teniamos una pareja de antes, distinta a esta

                        jugadaParejas = E_Jugada_Tipo.TWO_PAIR;
                        valoresDeJugadasParejas.set(indexDeParejas, current);

                    } else { //Tenemos un trio

                        jugadaParejas = E_Jugada_Tipo.THREE_OF_A_KIND;

                    }
                } else if (jugadaParejas == E_Jugada_Tipo.THREE_OF_A_KIND) {

                    if (indexDeParejas > 0) { //Teniamos un trío de antes, difernte a este. Ahora tenemos un FH
                        jugadaParejas = E_Jugada_Tipo.FULL_HOUSE;
                        valoresDeJugadasParejas.set(indexDeParejas, current);
                    } else { //El trio es del mismo valor que current -> Tenemos un POKER
                        jugadaParejas = E_Jugada_Tipo.FOUR_OF_A_KIND;
                    }
                } else if (jugadaParejas == E_Jugada_Tipo.TWO_PAIR) {

                    jugadaParejas = E_Jugada_Tipo.FULL_HOUSE;

                }
                cartasIgualesSeguidas++;
            } else {

                if (cartasIgualesSeguidas >= 2) {
                    indexDeParejas++;
                }

                cartasIgualesSeguidas = 1;
            }

            //ESCALERAS

            int distanciaEntreValores = anterior.getValor().getValor() - current.getValor().getValor(); //Siempre >= 1

            if (cartas.get(0).getValor() == E_Carta_Valor.A && !puedeHaberRueda && cartasEnElProyecto == 1) { //Para permitir wheel draws
                if (current.getValor() == E_Carta_Valor.CINCO) {
                    distanciaEntreValores = 1;
                    puedeHaberRueda = true;
                } else if (current.getValor() == E_Carta_Valor.CUATRO) {
                    distanciaEntreValores = 2;
                    puedeHaberRueda = true;
                }

            }


            if (distanciaEntreValores > 2 && cartasEnElProyecto > 1 && cartasEnElProyecto < 4) { //Ya no puede haber proyectos de escalera
                puedeHaberProyectoEscalera = false;
            } else if (puedeHaberProyectoEscalera) {

                if (distanciaEntreValores == 1) { //Son prod.baraja contiguas
                    cartasEnElProyecto++;
                } else if (distanciaEntreValores == 2) {
                    if (hayUnHueco) { //Si ya habia un hueco antes, no se puede hacer proyecto de escalera
                        if (cartasEnElProyecto < 4) {
                            puedeHaberProyectoEscalera = false;
                        }
                    } else {
                        hayUnHueco = true;
                        if (cartasEnElProyecto < 4) {
                            cartasEnElProyecto++;
                        }
                    }
                } //Si la distancia es 0, se ignora

            }

            //COLOR
            cartasDePalo[current.getPalo().getIdnum()]++;

            //Ponemos la carta que acabamos de analizar como "anterior"
            anterior = current;
		}

        //Recorremos el array desde el final, eliminando los nulls, para devolver un array de tamaño correcto
        for (int i = valoresDeJugadasParejas.size() - 1; i >= 0 ; i--) {
            if (valoresDeJugadasParejas.get(i) == null) {
                valoresDeJugadasParejas.remove(i);
            }
        }

        //PASO 2: Parsear resultados y rellenar jugada
        JugadaValor jugada = new JugadaValor(jugadaParejas, valoresDeJugadasParejas, cartas);

        //Marcar escaleras
        if (puedeHaberProyectoEscalera && cartasEnElProyecto == 5 && !hayUnHueco) {
            jugada = new JugadaValor(E_Jugada_Tipo.STRAIGHT, cartas, cartas);
        }

        //Marcar color
        for (int i = 0; i < cartasDePalo.length; i++) {
            if (cartasDePalo[i] == 5) {
                if (jugada.getTipo() == E_Jugada_Tipo.STRAIGHT) {
                    jugada = new JugadaValor(E_Jugada_Tipo.STRAIGHT_FLUSH, cartas, cartas);
                } else {
                    jugada = new JugadaValor(E_Jugada_Tipo.FLUSH, cartas, cartas);
                }
            }
        }

		return jugada;
	}
   
    /**
     * Metodo que compara jugadas.
     * @param jugada1
     * @param jugada2
     * @return devuelve [1 si jugada1 > jugada2], [-1 si jugada1 < jugada2], y [0 son jugada1 = jugada2]
     */

    private static int comparaJugadas(JugadaValor jugada1, JugadaValor jugada2) {
    	//System.out.println(jugada1.toString());
    	int rankJug1 = jugada1.getTipo().getRanking();
    	int rankJug2 = jugada2.getTipo().getRanking();
    	if (rankJug1 > rankJug2)
    		return 1;
    	
    	else if (rankJug1 < rankJug2)
    		return -1;
    	
    	else { // mismo tipo de jugada
    		List<Carta> cartasImp1 = jugada1.getCartasImportantes();
    		List<Carta> cartasImp2 = jugada2.getCartasImportantes();
    		for (int i = 0; i < cartasImp1.size(); i++) {
    			if (cartasImp1.get(i).getValor().getValor() > cartasImp2.get(i).getValor().getValor())
    				return 1;
    			if (cartasImp1.get(i).getValor().getValor() < cartasImp2.get(i).getValor().getValor())
    				return -1;
    		}
    		
    		// llegados a este punto tenemos que mirar los kickers
    		List<Carta> cartasKicker1 = jugada1.getMano();
    		List<Carta> cartasKicker2 = jugada2.getMano();
    		for (int i = 0; i < cartasKicker1.size(); i++) {
    			if (cartasKicker1.get(i).getValor().getValor() > cartasKicker2.get(i).getValor().getValor())
    				return 1;
    			if (cartasKicker1.get(i).getValor().getValor() < cartasKicker2.get(i).getValor().getValor())
    				return -1;
    		}
    		
    		// las jugadas son igual de buenas
    		return 0;
    	}
    }

    /**
     * Calcula todas las combinaciones entre dos listas de prod.baraja, por el método de:
     * 	- Calcular todas las combinaciones n_escoge_k de cada lista individual
     * 	- Calcular el producto cartesiano de las listas de prod.baraja
     */
   private static List<List<Carta>> combinarCartasDeListas(List<Carta> cartas1, int numCartas1,
                                                     List<Carta> cartas2, int numCartas2) {

		List<List<Carta>> combCartas1 = n_escoge_k(cartas1, numCartas1);
		List<List<Carta>> combCartas2 = n_escoge_k(cartas2, numCartas2);

        List<List<Carta>> todasLasCombinaciones = productoCartesiano(combCartas1, combCartas2);

        return todasLasCombinaciones;
    }

    /**
     * Funcion que devuelve una lista con todas las posibles combinaciones de dos listas.
     * 
     * Modela la relacion de conjuntos AxB, donde A,B son subconjuntos de {todos los tuples de prod.baraja}
     */
    private static List<List<Carta>> productoCartesiano(List<List<Carta>> combCartas1, List<List<Carta>> combCartas2) {

        List<List<Carta>> prod = new ArrayList<List<Carta>>();
        ArrayList<Carta> lista = null;
        for (int i = 0; i < combCartas1.size(); i++) {
            for (int j = 0; j < combCartas2.size(); j++) {
                lista = new ArrayList<Carta>(combCartas1.get(i));
                lista.addAll(new ArrayList<Carta>(combCartas2.get(j)));
                prod.add(lista);
            }
        }

        return prod;
    }

    /**
     * Funcion para sacar todas las combinaciones posibles de una lista, sin repeticion.
     * @param cartas Lista de prod.baraja.
     * @param k Longitud de las selecciones.
     * @return
     */
	private static List<List<Carta>> n_escoge_k(List<Carta> cartas, int k) {
		List<List<Carta>> devolver = new ArrayList<List<Carta>>();

		List<Carta> mano = new ArrayList<Carta>();

		n_escoge_k(cartas, mano, k, 0, 0, devolver); //Llamada a la versión recursiva de esta función

		return devolver;
    }

    /**
     * Funcion recursiva auxiliar, que se llama automaticamente desde n_escoge_k.
     * Recorre el arbol de posibilidades combinatorias recursivamente, y cada vez
     * que llega a una hoja valida, la guarda en la lista de prod.baraja.
     *
     * Se basa en que la lista que tiene que devolver no necesita estar ordenada, por 
     * lo que puede ir probando en orden todas las posiblidades. 
     *
     * El nivel del arbol p contiene todas las listas posibles de longitud p.
     */

    private static void n_escoge_k(List<Carta> cartas, List<Carta> mano, int k, int iteration, int curIndex, List<List<Carta>> devolver) {

		//CASOS BASE

		//Si nos hemos quedado sin opciones para llenar 'mano', no hemos llegado a una combinacion correcta
		if (curIndex > cartas.size()) {
			return;
		}

		//Si hemos llegado a una 'mano' con la longitud correcta, hemos llegado a una hoja valida
		if (iteration == k) {
			devolver.add(new ArrayList<Carta>(mano));
			return;
		}

		//CASO RECURSIVO

		//Cada nodo tiene tantos hijos como prod.baraja queden por insertar en 'mano'
		for (int i = curIndex; i < cartas.size(); i++) {
            		if (mano.size() <= iteration) {
                		mano.add(cartas.get(i));
            		} else {
                		mano.set(iteration, cartas.get(i));
            		}
			n_escoge_k(cartas, mano, k, iteration + 1, i + 1, devolver);
		}

	}

    @SuppressWarnings("unused")
	private static int[][] n_escoge_k_iter(int[] cartas, int k) throws EAnalizador {
        int[][] result = new int[binomial(cartas.length, k)][k];

        if (k > cartas.length) {
            throw new EAnalizador("K demasiado baja para hacer combinaciones");
        } else if (k == cartas.length) {

        } else {

        }

        return result;
    }

    private static int binomial(int n, int k)
    {
        if (k>n-k)
            k=n-k;

        int b=1;
        for (int i=1, m=n; i<=k; i++, m--)
            b=b*m/i;
        return b;
    }
}
