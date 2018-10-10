package prod.controlador;

import prod.baraja.Carta;
import prod.baraja.Deck;
import prod.modelo.analizador.Analizador;

import java.util.ArrayList;

public class RealizarSimulaciones {

    private static final int NUM_EJECUCIONES = 1000000;

    // Método de la práctica.

    /**
     * Método que saca los porcentajes con los resultados. Dichos datos se devolverán en un array con datos de tipo double.
     * En orden como viene escrito en el pdf.
     * Le pasaremos por parámetro:
     * <p>
     * ArrayList<ArrayList <Carta[]>>" lista: lista de rangos. algo asi como Jugadores<Rangos<Manos>>>
     * El primer arraylist es de jugadores
     * El segundo es su rango, que contiene
     * El tercero que son parejas de cartas
     * <p>
     * ArrayList<Carta>" mesa: las cartas que hay en el board se lo pasaremos como una lista de cartas.
     * <p>
     * ArrayList<Carta> cartasDescartadas: las dead cards se le pasarán como hemos pasado el board, es decir como una lista de cartas.
     * <p>
     * return double[]: Nos devolverá un array con todos los resultados para mostrarlos.
     */
  //Mostrar ArrayList<Carta>
    public static void mostrarListaDeCartasJugadores(ArrayList <Carta> lista) {

    System.out.print(lista.get(0));
    System.out.print(lista.get(1));

    }		

    public static void mostrarListaDeListaDeCartasJugadores(ArrayList<ArrayList <Carta>> listaJugadores) {
    	
    	for(int i = 0; i < listaJugadores.size(); i++) {
    		
    		mostrarListaDeCartasJugadores(listaJugadores.get(i));
    	}
    }

    public static void mostrarListaDeCombinacionesJugadores(ArrayList<ArrayList<ArrayList <Carta>>> lista) {
    	
    	for(int i = 0; i < lista.size(); i++) {
    		mostrarListaDeListaDeCartasJugadores(lista.get(i));
    	}
    }
    
 // Mostrar el array de par de cartas.
    public static void mostrarParDeCartas(Carta Carta[]) {

    System.out.print(Carta[0]);
    System.out.println(Carta[1]);

    }
    
 // Mostrar ArrayList<Carta[]>
    public static void mostrarListaDeCartas(ArrayList <Carta[]> lista) {

    // Que me muestre toda la lista de cartas.
    for(Carta[] lc: lista) {

    for(int i = 0; i < lc.length; i++) {

    System.out.print(lc[i]);
    }		
    }
    }


 // Mostrar lista de lista de cartas. ArrayList<ArrayList <Carta[]>>
    public static void mostrarListaDeListaDeCartas(ArrayList<ArrayList <Carta[]>> lista) {

    // Que me muestre toda la lista de cartas.
    for(int i = 0; i < lista.size(); i++) {

    mostrarListaDeCartas(lista.get(i));
    System.out.println("");

    }
    }
    
    /*
    public static double[] calcularResultados(ArrayList<ArrayList<Carta[]>> lista, ArrayList<Carta> mesa, ArrayList<Carta> cartasDescartadas) {

        // Inicializamos el array, como máximo, con los 10 jugadores activos, tendría una capacidad de 53.
        double[] arrayResultados = new double[53];
        int n = numeroTotalEjecucionesN(lista);
        int n_simulaciones = numeroDeSimulaciones(n);

        // Vamos a crear un array de enteros para contabilizar las que van ganando.
        int[] partidasGanadas = new int[lista.size()];
        int[] partidasEmpatadas = new int[lista.size()];
        int totalempates = 0;

        int[] contadores = new int[lista.size()];

        // Inicializamos el contador poniendo todas las posiciones a 0.
        inicializarArrayContadores(contadores);

        ArrayList<Carta> mesacopia = new ArrayList<>(mesa); //Copia para resetear cada iteracion
        ArrayList<ArrayList<Carta>> jugadores = new ArrayList<ArrayList<Carta>>();

        int numCartasEnMesa = mesa.size();
        int numCartasQuemadas = cartasDescartadas.size();

        int[] ganadores;

        Carta[] cartaEscogida;


        Deck baraja = new Deck();
        baraja.shuffle();

        for (int r = 0; r < n; r++) {  // r irá recorriendo todas las combinaciones.

            baraja.shuffle();
            ArrayList<Carta[]> listaJugadores = new ArrayList<>();
            for (int i = 0; i < lista.size(); i++) { // recorrera una combinación, asignando parejas de cartas a jugadores
                // Cogemos las cartas de los contadores de la lista y los guardamos.
                Carta[] jugadorParaAnadir;
                do {
                    jugadorParaAnadir = lista.get(i).get(contadores[i]);
                    if (!(baraja.cardInDeck(jugadorParaAnadir[0]) &&
                            baraja.cardInDeck(jugadorParaAnadir[1]))) {
                        contadores[i] = (contadores[i] + 1) % (lista.get(i).size());

                    }
                } while (!(baraja.cardInDeck(jugadorParaAnadir[0]) &&
                            baraja.cardInDeck(jugadorParaAnadir[1])));
                listaJugadores.add(jugadorParaAnadir);

                baraja.removeCard(jugadorParaAnadir[0]);
                baraja.removeCard(jugadorParaAnadir[1]);

            }
            for (Carta[] c : listaJugadores) {
                jugadores.add(pasarArrayCartaAListaCarta(c));
            }
            baraja.shuffle();

            System.out.println("Evaluating hands: " + jugadores);

            // Se asume que aqui ya llegan cartas no repetidas
            for (int tc = 0; tc < n_simulaciones; tc++) {
                // Realizamos la ejecución
                // Barajamos.
                baraja.shuffle();

                // Quitamos las cartas del board.
                for (int cb = 0; cb < numCartasEnMesa; cb++) {
                    baraja.removeCard(mesa.get(cb));
                }

                // Quitamos las dead cards.
                for (int dc = 0; dc < numCartasQuemadas; dc++) {
                    baraja.removeCard(cartasDescartadas.get(dc));
                }

                // Quitamos las cartas de los jugadores, las del board y las dead cards.
                ArrayList<Carta> jugador;
                for (int j = 0; j < jugadores.size(); j++) {
                    jugador = jugadores.get(j);
                    if (baraja.cardInDeck(jugador.get(0)) && baraja.cardInDeck(jugador.get(1))){
                        baraja.removeCard(jugador.get(0));
                        baraja.removeCard(jugador.get(1));
                    } else {
                        throw  new RuntimeException("Algo ha salido mal");
                    }
                }

                //Devolvemos la mesa a su estado original
                mesa = new ArrayList<>(mesacopia);
                // Miramos cuantas cartas tiene el board.
                // 5 - cartas del board.
                // for sacando las cartas aleatoriamente de la prod.baraja y anadirlas a la lista del board.
                for (int m = numCartasEnMesa; m < 5; m++) {

                    // Añadimos al board una carta aleatoria.
                    mesa.add(baraja.dealRandomCard());
                }

                // Pasamos al analizador y que nos devuelva los ganadores, los contamos en nuestro arrayResultados
                // Si los ganadores son más de 1, han empatado, al array de empates.
                ganadores = Analizador.calcularGanadoresHoldem(jugadores, mesa);

                if (ganadores.length == 1) {
                    partidasGanadas[ganadores[0]]++;
                } else {

                    for (int p = 0; p < ganadores.length; p++) {
                        partidasEmpatadas[ganadores[p]]++;
                        totalempates++;
                    }
                }

                // Juntamos de nuevo la prod.baraja.
                baraja.shuffle();
            }

            baraja.shuffle();
            jugadores.clear();

            // Actualizamos contadores.
            int j = lista.size() - 1;
            boolean sumado = false;

            while ((j >= 0) && (!sumado)) { // Mientras no sea el final

                if (contadores[j] < ((lista.get(j).size()) - 1)) {

                    contadores[j]++;
                    sumado = true;
                } else { // Ponemos ese contador a 0.

                    contadores[j] = 0;
                }

                j--;

            }

        }

        // Una vez acabadas las simulaciones calculamos los datos y los añadimos al array de resultados.
        int cont_resultados = 0;
        for (int resultados = 0; resultados < lista.size(); resultados++) {

            double winequity = (((double)partidasGanadas[resultados] / ((NUM_EJECUCIONES / n) > 1 ? NUM_EJECUCIONES : n)) * 100.0);
            double tieequity = (((double)partidasEmpatadas[resultados] / ((NUM_EJECUCIONES / n) > 1 ? NUM_EJECUCIONES : n)) *
                    ((double) partidasEmpatadas[resultados] / totalempates) *
                    100.0);
            arrayResultados[cont_resultados] = winequity + tieequity;
            cont_resultados++;
            System.out.println("Hand equity " + resultados + ": " + (winequity + tieequity));
            System.out.println("Hand win " + resultados + ": " + winequity);
            arrayResultados[cont_resultados] = winequity;
            cont_resultados++;
            System.out.println("Hand tie " + resultados + ": " + tieequity);
            arrayResultados[cont_resultados] = tieequity;
            cont_resultados++;
        }

        return arrayResultados;
    }*/


    public static ResultadoSimulacion calcularResultados(ArrayList<ArrayList<Carta[]>> lista, ArrayList<Carta> mesa, ArrayList<Carta> cartasDescartadas) {

        // Inicializamos el array, como máximo, con los 10 jugadores activos, tendría una capacidad de 53.
        double[] arrayResultados = new double[53];
        //mostrarListaDeListaDeCartas(lista);
        ArrayList<ArrayList<ArrayList <Carta>>> combinaciones = combinaciones(lista);
        //mostrarListaDeCombinacionesJugadores(combinaciones);
        int n_simulaciones = numeroDeSimulaciones(combinaciones.size());
        int n = numeroTotalEjecucionesN(lista);


        // Vamos a crear un array de enteros para contabilizar las que van ganando.
        int[] partidasGanadas = new int[lista.size()];
        int[] partidasEmpatadas = new int[lista.size()];
        int totalempates = 0;

        int[] contadores = new int[lista.size()];

        // Inicializamos el contador poniendo todas las posiciones a 0.
        inicializarArrayContadores(contadores);

        ArrayList<Carta> mesacopia = new ArrayList<>(mesa); //Copia para resetear cada iteracion

        int numCartasEnMesa = mesa.size();
        int numCartasQuemadas = cartasDescartadas.size();

        int[] ganadores;

        Deck baraja = new Deck();
        baraja.shuffle();


        for (int r = 0; r < combinaciones.size(); r++) {  // r irá recorriendo todas las combinaciones.

            for (int tc = 0; tc < n_simulaciones; tc++) {
                // Realizamos la ejecución
                // Barajamos.
                baraja.shuffle();
                // Quitamos las cartas de los jugadores, las del board y las dead cards.
                for (ArrayList<Carta> jugador : combinaciones.get(r)) {
                    for (Carta c : jugador) {
                        baraja.removeCard(c);
                    }
                }

                // Quitamos las cartas del board.
                for (int cb = 0; cb < numCartasEnMesa; cb++) {

                    baraja.removeCard(mesa.get(cb));

                }

                // Quitamos las dead cards.
                for (int dc = 0; dc < numCartasQuemadas; dc++) {

                    baraja.removeCard(cartasDescartadas.get(dc));

                }

                //Devolvemos la mesa a su estado original
                mesa = new ArrayList<>(mesacopia);
                // Miramos cuantas cartas tiene el board.
                // 5 - cartas del board.
                // for sacando las cartas aleatoriamente de la prod.baraja y anadirlas a la lista del board.
                for (int m = numCartasEnMesa; m < 5; m++) {

                    // Añadimos al board una carta aleatoria.
                    mesa.add(baraja.dealRandomCard());
                }

                // Pasamos al analizador y que nos devuelva los ganadores, los contamos en nuestro arrayResultados
                // Si los ganadores son más de 1, han empatado, al array de empates.
                ganadores = Analizador.calcularGanadoresHoldem(combinaciones.get(r), mesa);

                if (ganadores.length == 1) {
                    partidasGanadas[ganadores[0]]++;
                } else {

                    for (int p = 0; p < ganadores.length; p++) {
                        partidasEmpatadas[ganadores[p]]++;
                        totalempates++;
                    }
                }

                // Juntamos de nuevo la prod.baraja.
                baraja.shuffle();
            }
            
            // Actualizamos contadores.
            int j = lista.size() - 1;
            boolean sumado = false;

            while ((j >= 0) && (!sumado)) { // Mientras no sea el final

                if (contadores[j] < ((lista.get(j).size()) - 1)) {

                    contadores[j]++;
                    sumado = true;
                } else { // Ponemos ese contador a 0.

                    contadores[j] = 0;
                }

                j--;

            }

        }

        // Una vez acabadas las simulaciones calculamos los datos y los añadimos al array de resultados.
        int cont_resultados = 0;
        for (int resultados = 0; resultados < lista.size(); resultados++) {

            double winequity = (((double)partidasGanadas[resultados] /((NUM_EJECUCIONES / n) > 1 ? NUM_EJECUCIONES : n)) * 100.0);
            double tieequity = (((double)partidasEmpatadas[resultados] / ((NUM_EJECUCIONES / n) > 1 ? NUM_EJECUCIONES : n)) *
                                    ((double) partidasEmpatadas[resultados] / totalempates) *
                                    100.0);
            arrayResultados[cont_resultados] = winequity + tieequity;
            cont_resultados++;
            System.out.println("Hand equity " + resultados + ": " + (winequity + tieequity));
            System.out.println("Hand win " + resultados + ": " + winequity);
            arrayResultados[cont_resultados] = winequity;
            cont_resultados++;
            System.out.println("Hand tie " + resultados + ": " + tieequity);
            arrayResultados[cont_resultados] = tieequity;
            cont_resultados++;
        }
        
        ResultadoSimulacion resultado = new ResultadoSimulacion(arrayResultados, partidasGanadas, partidasEmpatadas, combinaciones.size() * n_simulaciones);

        return resultado;
    }
    
    public static ResultadoSimulacion calcularResultadosOmaha(ArrayList<ArrayList<Carta>> lista, ArrayList<Carta> mesa, ArrayList<Carta> cartasDescartadas) {

        // Inicializamos el array, como máximo, con los 10 jugadores activos, tendría una capacidad de 53.
        double[] arrayResultados = new double[53];
        int n = numeroTotalEjecucionesNOmaha(lista);
        int n_simulaciones = numeroDeSimulaciones(n);

        // Vamos a crear un array de enteros para contabilizar las que van ganando.
        int[] partidasGanadas = new int[lista.size()];
        int[] partidasEmpatadas = new int[lista.size()];
        int totalempates = 0;

        int[] contadores = new int[lista.size()];

        // Inicializamos el contador poniendo todas las posiciones a 0.
        inicializarArrayContadores(contadores);

        ArrayList<Carta> mesacopia = new ArrayList<>(mesa); //Copia para resetear cada iteracion
        ArrayList<ArrayList<Carta>> jugadores = new ArrayList<ArrayList<Carta>>();

        int numCartasEnMesa = mesa.size();
        int numCartasQuemadas = cartasDescartadas.size();

        int[] ganadores;

        ArrayList<Carta> manoEscogidaOmaha; // 4 cartas


        Deck baraja = new Deck();
        baraja.shuffle();

        for (int r = 0; r < n; r++) {  // r irá recorriendo todas las combinaciones.

            for (int i = 0; i < lista.size(); i++) { // recorrera una combinación, asignando parejas de cartas a jugadores

                // Cogemos las cartas de los contadores de la lista y los guardamos.
            	manoEscogidaOmaha = lista.get(i); // 4 cartas.
                jugadores.add(manoEscogidaOmaha);
            }

            for (int tc = 0; tc < n_simulaciones; tc++) {
                // Realizamos la ejecución
                // Barajamos.
                baraja.shuffle();
                // Quitamos las cartas de los jugadores, las del board y las dead cards.
                for (ArrayList<Carta> jugador : jugadores) {
                    for (Carta c : jugador) {
                        baraja.removeCard(c);
                    }
                }
                    /*Carta[] carta;
                    for(int k = 0; k < lista.size(); k++) {
					
						// Quitamos las cartas seleccionadas de los jugadores.
						carta = lista.get(k);
						prod.baraja.removeCard(carta[0]);
						prod.baraja.removeCard(carta[1]);
					}*/

                // Quitamos las cartas del board.
                for (int cb = 0; cb < numCartasEnMesa; cb++) {

                    baraja.removeCard(mesa.get(cb));

                }

                // Quitamos las dead cards.
                for (int dc = 0; dc < numCartasQuemadas; dc++) {

                    baraja.removeCard(cartasDescartadas.get(dc));

                }

                //Devolvemos la mesa a su estado original
                mesa = new ArrayList<>(mesacopia);
                // Miramos cuantas cartas tiene el board.
                // 5 - cartas del board.
                // for sacando las cartas aleatoriamente de la prod.baraja y anadirlas a la lista del board.
                for (int m = numCartasEnMesa; m < 5; m++) {

                    // Añadimos al board una carta aleatoria.
                    mesa.add(baraja.dealRandomCard());
                }

                // Pasamos al analizador y que nos devuelva los ganadores, los contamos en nuestro arrayResultados
                // Si los ganadores son más de 1, han empatado, al array de empates.
                ganadores = Analizador.calcularGanadoresOmaha(jugadores, mesa);

                if (ganadores.length == 1) {
                    partidasGanadas[ganadores[0]]++;
                } else {

                    for (int p = 0; p < ganadores.length; p++) {
                        partidasEmpatadas[ganadores[p]]++;
                        totalempates++;
                    }
                }

                // Juntamos de nuevo la prod.baraja.
                baraja.shuffle();
            }

            jugadores.clear();

            // Actualizamos contadores.
            int j = lista.size() - 1;
            boolean sumado = false;

            while ((j >= 0) && (!sumado)) { // Mientras no sea el final

                if (contadores[j] < ((lista.get(j).size()) - 1)) {

                    contadores[j]++;
                    sumado = true;
                } else { // Ponemos ese contador a 0.

                    contadores[j] = 0;
                }

                j--;

            }

        }

        // Una vez acabadas las simulaciones calculamos los datos y los añadimos al array de resultados.
        int cont_resultados = 0;
        for (int resultados = 0; resultados < lista.size(); resultados++) {

            double winequity = (((double)partidasGanadas[resultados] / ((NUM_EJECUCIONES / n) > 1 ? NUM_EJECUCIONES : n)) * 100.0);
            double tieequity = (((double)partidasEmpatadas[resultados] / ((NUM_EJECUCIONES / n) > 1 ? NUM_EJECUCIONES : n)) *
                                    ((double) partidasEmpatadas[resultados] / (totalempates + 1)) *
                                    100.0);
            arrayResultados[cont_resultados] = winequity + tieequity;
            cont_resultados++;
            System.out.println("Hand equity " + resultados + ": " + (winequity + tieequity));
            System.out.println("Hand win " + resultados + ": " + winequity);
            arrayResultados[cont_resultados] = winequity;
            cont_resultados++;
            System.out.println("Hand tie " + resultados + ": " + tieequity);
            arrayResultados[cont_resultados] = tieequity;
            cont_resultados++;
        }

        ResultadoSimulacion resultado = new ResultadoSimulacion(arrayResultados, partidasGanadas, partidasEmpatadas, NUM_EJECUCIONES);

        return resultado;
    }
    
    // Total de ejecuciones Omaha.
    public static int numeroTotalEjecucionesNOmaha(ArrayList<ArrayList<Carta>> lista) {

        int n = 1;

        for (int i = 0; i < lista.size(); i++) {

            n = n * lista.get(i).size();
        }

        return n;
    }
    
    // Total de ejecuciones.
    public static int numeroTotalEjecucionesN(ArrayList<ArrayList<Carta[]>> lista) {

        int n = 1;

        for (int i = 0; i < lista.size(); i++) {

            n = n * lista.get(i).size();
        }

        return n;
    }

    // El numero de veces que se tendrá que ejecutar cada combinacion de cartas.
    public static int numeroDeSimulaciones(int n) {
        return ((NUM_EJECUCIONES / n) > 1 ? (NUM_EJECUCIONES / n) : 1);
    }

    // Inicializar el array de contadores.
    public static void inicializarArrayContadores(int[] arrayCont) {

        for (int i = 0; i < arrayCont.length; i++) {

            arrayCont[i] = 0;
        }
    }

    public static ArrayList<Carta> pasarArrayCartaAListaCarta(Carta[] cartaEscogida) {

        ArrayList<Carta> cartaResultado = new ArrayList<Carta>();

        cartaResultado.add(cartaEscogida[0]);
        cartaResultado.add(cartaEscogida[1]);

        return cartaResultado;
    }
    
 // Calcular todas las posibles combinaciones sin cartas repetidas y devolverlas en una lista.
    @SuppressWarnings("unchecked")
    public static ArrayList<ArrayList<ArrayList <Carta>>> combinaciones(ArrayList<ArrayList <Carta[]>> lista) {
    	
    	ArrayList<ArrayList<ArrayList <Carta>>> combPosiblesSinRepetirCartas = new ArrayList<ArrayList<ArrayList <Carta>>>();
    	ArrayList<ArrayList <Carta>> jugadores = new ArrayList<ArrayList <Carta>>();
    	Object jugadoresCopia = new ArrayList<ArrayList <Carta>>();

    	Carta[] cartaEscogida;
    	ArrayList<Carta> cartasRepetidas = new ArrayList<Carta>();
    	boolean correcto = true;
    	int n = numeroTotalEjecucionesN(lista);
    	int[] contadores = new int[lista.size()];

    	for(int r = 0; r < n; r++) {  // r irá recorriendo todas las combinaciones.

    		for(int i = 0; i < lista.size() && (correcto); i++) { // recorrera una combinación, asignando parejas de cartas a jugadores
    			
    			// Cogemos las cartas de los contadores de la lista y los guardamos.
    			cartaEscogida = lista.get(i).get(contadores[i]); // Carta[]
    			// Comprobariamos si la combinación es correcta, es decir no se repite ninguna carta entre los jugadores.
    			// Para ello comprobamos la carta escogida con las sacadas anteriormente.
    			for(int cr = 0; (cr < cartasRepetidas.size()) && (correcto); cr++) {
    				if((cartaEscogida[0].getValor() == cartasRepetidas.get(cr).getValor()) && (cartaEscogida[0].getPalo() == cartasRepetidas.get(cr).getPalo()))
    				{
    					correcto = false;
    				}
    				else if((cartaEscogida[1].getValor() == cartasRepetidas.get(cr).getValor()) && (cartaEscogida[1].getPalo() == cartasRepetidas.get(cr).getPalo())) {
    					
    					correcto = false;
    				}
    			}
    			
    			if(correcto) {
    				// Añadimos las dos cartas a la lista.
    				cartasRepetidas.add(cartaEscogida[0]);
    				cartasRepetidas.add(cartaEscogida[1]);

    				jugadores.add(pasarArrayCartaAListaCarta(cartaEscogida));
    				jugadoresCopia = jugadores.clone();
    			}	
            }

    		
    		if(correcto) { 
    		
    		// La añadimos a la lista de combinaciones.
    		combPosiblesSinRepetirCartas.add((ArrayList<ArrayList<Carta>>) jugadoresCopia);	
    	
    		}
    		
    		jugadores.clear();
    		cartasRepetidas.clear();
    		correcto = true;
    		
    		int j = lista.size()-1;
    		boolean sumado = false;
    		
    		while((j >= 0) && (!sumado)) { // Mientras no sea el final
    			
    			if(contadores[j] < ((lista.get(j).size())-1)) {
    			
    				contadores[j]++;
    				sumado = true;	
    			}
    			else  { // Ponemos ese contador a 0.
    				
    				contadores[j] = 0; 
    			}
    			
    			j--;
    			
    		}
    		
    		}
    	
    	
    	return combPosiblesSinRepetirCartas;
    }
}