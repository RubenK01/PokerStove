package prod.modelo.rangos;

import prod.modelo.excepciones.EIndiceInvalido;
import prod.modelo.rankings.Ranking;
import prod.modelo.util.Constantes;
import prod.modelo.util.Utilidades;

public abstract class Rango implements RangoSoloLectura {

    protected boolean[][] rango;
    
    public Rango() {
    	rango = new boolean[Constantes.TABLA_RANGOS_X][Constantes.TABLA_RANGOS_X];
    }

    /**
     * Getter para ver si la mano pertenece al rango, usando coordenadas
     * @param x
     * @param y
     * @return
     */
    public boolean getManoEnRango(int x, int y) {
        if (Utilidades.sonCoordValidas(x, y)) {
            return this.rango[x][y];
        } else {
            throw new EIndiceInvalido();
        }
    }

    /**
     * Wrapper para ver si la mano pertenece al rango pero esta vez como una string
     * @param mano string de la mano
     * @return
     */
    public boolean getManoEnRango(String mano) {
        int[] coordenadas = this.parseManoACoordenadas(mano);
        return this.getManoEnRango(coordenadas[0], coordenadas[1]);
    }

    /**
     * Metodo auxiliar para pasar una string (eg K4s o Kh5h) a coordenadas
     * @param mano
     * @return
     */
    private int[] parseManoACoordenadas(String mano) {
    	int x = 0, y = 0, iz, dr;
        if (mano.length() == 2) { // pareja
        	x = Utilidades.char2int(mano.charAt(0));
        	y = Utilidades.char2int(mano.charAt(1));
        }
        else if (mano.length() == 3) { 
        	if (mano.charAt(2) == 's') { // suited
        		iz = Utilidades.char2int(mano.charAt(0));
        		dr = Utilidades.char2int(mano.charAt(1));
        		if (iz >= dr) {
        			x = iz;
        			y = dr;
        		}
        		else {
        			x = dr;
        			y = iz;
        		}
        	}
        	else { // offsuited
        		iz = Utilidades.char2int(mano.charAt(0));
        		dr = Utilidades.char2int(mano.charAt(1));
        		if (iz >= dr) {
        			y = iz;
        			x = dr;
        		}
        		else {
        			y = dr;
        			x = iz;
        		}
        	}
        }
        else if (mano.length() == 4){
        	if (mano.charAt(1) == mano.charAt(3)) { // suited
        		iz = Utilidades.char2int(mano.charAt(0));
        		dr = Utilidades.char2int(mano.charAt(2));
        		if (iz >= dr) {
        			x = iz;
        			y = dr;
        		}
        		else {
        			x = dr;
        			y = iz;
        		}
        	}
        	else { // offsuited
        		iz = Utilidades.char2int(mano.charAt(0));
        		dr = Utilidades.char2int(mano.charAt(2));
        		if (iz >= dr) {
        			y = iz;
        			x = dr;
        		}
        		else {
        			y = dr;
        			x = iz;
        		}
        	}
        }
        else {
        	// excepcion
        }
        return new int[]{x, y};
    }

    /**
     * Marca una mano como "seleccionada"
     * @param x
     * @param y
     */
    public abstract void marcarMano(int x, int y);

    /**
     * Marca todas las manos contenidas en el rango
     * @param rango
     */
    public abstract void marcarRango(String rango);

    /**
     * Marca el rango segun un porcentaje determinado por E_Rankings
     * @param porcentaje el porcentaje de manos que se quieren marcar
     * @param ranking el ranking a usar
     */
    public void marcarPorcentaje(float porcentaje, Ranking ranking) {

        //Poner todos a false
        for (int i = 0; i < Constantes.TABLA_RANGOS_X; i++)
            for (int j = 0; j < Constantes.TABLA_RANGOS_X; j++)
                this.rango[i][j] = false;
        //Por la forma en la que funcionan los float, no pilla bien el 100%
        if (porcentaje >= 100.0) {
            porcentaje = 101;
        }
        float numManosPorMarcar = (porcentaje * 1326) / 100; // manos preflop que cubre el porcentaje


        String mano;
        int fil, col;

        for (int cont = 0; (cont < Constantes.TABLA_RANGOS_X*Constantes.TABLA_RANGOS_X) && numManosPorMarcar >= 0; cont++){
            mano = ranking.at(cont);

            if (mano.length() == 2) { // pareja
                numManosPorMarcar -= 6;
                fil = Utilidades.char2int(mano.charAt(0));
                col = fil;
            }
            else { // length == 3
                if (mano.charAt(mano.length()-1) == 's') { // suited
                    numManosPorMarcar -= 4;
                    fil = Utilidades.char2int(mano.charAt(0));
                    col = Utilidades.char2int(mano.charAt(1));
                }
                else { // offsuited
                    numManosPorMarcar -= 12;
                    col = Utilidades.char2int(mano.charAt(0));
                    fil = Utilidades.char2int(mano.charAt(1));
                }
            }

            if (numManosPorMarcar >= 0) // entra dentro del rango
                this.rango[fil][col] = true;
        }
    }
    
    public void marcarPolarizado(int w, int x, int y, int z, Ranking ranking) {

        //Poner todos a false
        for (int i = 0; i < Constantes.TABLA_RANGOS_X; i++)
            for (int j = 0; j < Constantes.TABLA_RANGOS_X; j++)
                this.rango[i][j] = false;
        
        float numManos = 0;
        float numManosW = (w * 1326) / 100;
        float numManosX = (x * 1326) / 100;
        float numManosY = (y * 1326) / 100;
        float numManosZ = (z * 1326) / 100;

        String mano;
        int fil, col;

        for (int cont = 0; cont < Constantes.TABLA_RANGOS_X*Constantes.TABLA_RANGOS_X; cont++){
            mano = ranking.at(cont);

            if (mano.length() == 2) { // pareja
                numManos += 6;
                fil = Utilidades.char2int(mano.charAt(0));
                col = fil;
            }
            else { // length == 3
                if (mano.charAt(mano.length()-1) == 's') { // suited
                    numManos += 4;
                    fil = Utilidades.char2int(mano.charAt(0));
                    col = Utilidades.char2int(mano.charAt(1));
                }
                else { // offsuited
                    numManos += 12;
                    col = Utilidades.char2int(mano.charAt(0));
                    fil = Utilidades.char2int(mano.charAt(1));
                }
            }

            if ( (numManos > numManosW && numManos < numManosX)
            		|| (numManos > numManosY && numManos <= numManosZ) ) { // entra dentro del rango
                this.rango[fil][col] = true;
            }
        }
    }


    public abstract void desmarcarMano(int x, int y);

    public abstract void cambiarMano(int x, int y);

    /**
     * Override del metodo toString
     * @return el rango en el formato correcto, e.g. "22+, KJ+, A8+"
     */
    @Override
    public String toString() {
        return this.devolverCartasSeleccionadas();
    }

    //--------------
    // METODOS AUXILIARES PARA TOSTRING
    //--------------

    // Metodo que reciba el array por parametro y nos devuelva el String con las cartas.
    // Falta meterle array por parametro para adaptarlo a la practica y poner los nombres en lugar de las posiciones.
    private String devolverCartasSeleccionadas() {

        StringBuilder resultado = new StringBuilder();
        String cartasTotales, cartasVertical, cartasHorizontal, cartasDiagonal;

        cartasDiagonal = devolverCartasSeleccionadasDiagonal((Constantes.TABLA_RANGOS_X - 1), 0);

        resultado.append(cartasDiagonal);

        // Vamos recorriendo diagonalmente y para carta de la diagonal miramos la fila entera y columna entera.
        for(int i = (Constantes.TABLA_RANGOS_X - 1); i > 1; i--) {

            cartasHorizontal = devolverCartasSeleccionadasFilaHorizontal(i, i-1, 0);

            cartasVertical = devolverCartasSeleccionadasColumnaVertical(i, i-1, 0);

            resultado.append(cartasHorizontal).append(cartasVertical);
        }



        cartasTotales = resultado.toString();
        if (cartasTotales.endsWith(",")) {
            cartasTotales = cartasTotales.substring(0, cartasTotales.length() - 1);
        }

        return cartasTotales;
    }

    // Para mirar horizontal.(12, 11, 0)
    private String devolverCartasSeleccionadasFilaHorizontal(int fila, int posColumnaInicial, int posColumnaFinal) {

        String filaInt, columnaInt, cadenaResultado;
        StringBuilder resultado = new StringBuilder();
        boolean primerasCartasIguales = false;
        int contador = posColumnaInicial, cartasIguales = 0;

        // Comprobamos si puede haber rango ++.
        if(this.rango[fila][contador] && this.rango[fila][contador-1]) {

            primerasCartasIguales = true;
            contador = contador - 2;
        }


        while(contador >= posColumnaFinal) {

            if(primerasCartasIguales) { // Son iguales las dos primeras cartas, puede haber ++.

                if(!this.rango[fila][contador] || (contador == 0)) {

                    if(!this.rango[fila][contador]) {

                        filaInt = Utilidades.int2String(fila);
                        columnaInt = Utilidades.int2String(contador + 1);
                        resultado.append(filaInt).append(columnaInt).append("s+").append(",");
                    }
                    else if(contador == 0) {

                        filaInt = Utilidades.int2String(fila);
                        columnaInt = Utilidades.int2String(contador);
                        resultado.append(filaInt).append(columnaInt).append("s+").append(",");
                    }
                    primerasCartasIguales = false;
                }
            }
            else {

                // Ya no hay cartas más más.
                if(this.rango[fila][contador]) {

                    if(cartasIguales == 0) {
                        filaInt = Utilidades.int2String(fila);
                        columnaInt = Utilidades.int2String(contador);
                        resultado.append(filaInt).append(columnaInt);
                        if(contador == 0) {
                            resultado.append("s,");
                        }
                    }

                    cartasIguales++;
                    if((contador == 0) && (cartasIguales > 1)) {

                        filaInt = Utilidades.int2String(fila);
                        columnaInt = Utilidades.int2String(contador);
                        resultado.append("s-").append(filaInt).append(columnaInt).append("s,");
                        cartasIguales = 0;
                    }

                }
                else {

                    if(cartasIguales > 1) {

                        filaInt = Utilidades.int2String(fila);
                        columnaInt = Utilidades.int2String(contador + 1);
                        resultado.append("s-").append(filaInt).append(columnaInt).append("s,");
                        cartasIguales = 0;
                    }
                    else if(cartasIguales != 0){

                        resultado.append("s,");
                        cartasIguales = 0;
                    }
                }
            }

            contador--;
        }

        cadenaResultado = resultado.toString();

        return cadenaResultado;
    }

    // Para mirar vertical(12, 11, 0)
    private String devolverCartasSeleccionadasColumnaVertical(int columna, int posFilaInicial, int posFilaFinal) {

        String filaInt, columnaInt, cadenaResultado;
        StringBuilder resultado = new StringBuilder();
        boolean primerasCartasIguales = false;
        int contador = posFilaInicial, cartasIguales = 0;

        // Comprobamos si puede haber rango ++.
        if(this.rango[contador][columna] && this.rango[contador-1][columna]) {

            primerasCartasIguales = true;
            contador = contador - 2;
        }


        while(contador >= posFilaFinal) {

            if(primerasCartasIguales) { // Son iguales las dos primeras cartas, puede haber ++.

                if(!this.rango[contador][columna] || (contador == 0)) {

                    if(!this.rango[contador][columna]) {

                        filaInt = Utilidades.int2String(contador + 1);
                        columnaInt = Utilidades.int2String(columna);
                        resultado.append(columnaInt).append(filaInt).append("o+,");
                    }
                    else if(contador == 0) {

                        filaInt = Utilidades.int2String(contador);
                        columnaInt = Utilidades.int2String(columna);
                        resultado.append(columnaInt).append(filaInt).append("o+,");
                    }
                    primerasCartasIguales = false;
                }
            }
            else {

                // Ya no hay cartas más más.
                if(this.rango[contador][columna]) {

                    if(cartasIguales == 0) {
                        filaInt = Utilidades.int2String(contador);
                        columnaInt = Utilidades.int2String(columna);
                        resultado.append(columnaInt).append(filaInt);
                        if(contador == 0) {
                            resultado.append("o,");
                        }
                    }

                    cartasIguales++;
                    if((contador == 0) && (cartasIguales > 1)) {

                        filaInt = Utilidades.int2String(contador);
                        columnaInt = Utilidades.int2String(columna);
                        resultado.append("o-").append(columnaInt).append(filaInt).append("o,");
                        cartasIguales = 0;
                    }

                }
                else {

                    if(cartasIguales > 1) {

                        filaInt = Utilidades.int2String(contador + 1);
                        columnaInt = Utilidades.int2String(columna);
                        resultado.append("o-").append(columnaInt).append(filaInt).append("o,");
                        cartasIguales = 0;
                    }
                    else if(cartasIguales != 0){

                        resultado.append("o,");
                        cartasIguales = 0;
                    }
                }
            }

            contador--;
        }

        cadenaResultado = resultado.toString();

        return cadenaResultado;
    }

    // Para la diagonal.
    private String devolverCartasSeleccionadasDiagonal(int posInicial, int posFinal) {

        String filaInt, columnaInt, cadenaResultado;
        StringBuilder resultado = new StringBuilder();
        boolean primerasCartasIguales = false;
        int contador = posInicial, cartasIguales = 0;

        // Comprobamos si puede haber rango ++.
        if(this.rango[contador][contador] && this.rango[contador-1][contador-1]) {

            primerasCartasIguales = true;
            contador = contador - 2;
        }


        while(contador >= posFinal) {

            if(primerasCartasIguales) { // Son iguales las dos primeras cartas, puede haber ++.

                if(!this.rango[contador][contador] || (contador == 0)) {

                    if(!this.rango[contador][contador]) {

                        filaInt = Utilidades.int2String(contador + 1);
                        columnaInt = Utilidades.int2String(contador + 1);
                        resultado.append(columnaInt).append(filaInt).append("+").append(",");
                    }
                    else if(contador == 0) {

                        filaInt = Utilidades.int2String(contador);
                        columnaInt = Utilidades.int2String(contador);
                        resultado.append(columnaInt).append(filaInt).append("+").append(",");
                    }
                    primerasCartasIguales = false;
                }
            }
            else {

                // Ya no hay cartas más más.
                if(this.rango[contador][contador]) {

                    if(cartasIguales == 0) {
                        filaInt = Utilidades.int2String(contador);
                        columnaInt = Utilidades.int2String(contador);
                        resultado.append(columnaInt).append(filaInt);
                        if(contador == 0) {
                            resultado.append(",");
                        }
                    }

                    cartasIguales++;
                    if((contador == 0) && (cartasIguales > 1)) {

                        filaInt = Utilidades.int2String(contador);
                        columnaInt = Utilidades.int2String(contador);
                        resultado.append("-").append(columnaInt).append(filaInt).append(",");
                        cartasIguales = 0;
                    }

                }
                else {

                    if(cartasIguales > 1) {

                        filaInt = Utilidades.int2String(contador + 1);
                        columnaInt = Utilidades.int2String(contador + 1);
                        resultado.append("-").append(columnaInt).append(filaInt).append(",");
                        cartasIguales = 0;
                    }
                    else if(cartasIguales != 0 && contador != 0){

                        resultado.append(",");
                        cartasIguales = 0;
                    }
                }
            }

            contador--;
        }

        cadenaResultado = resultado.toString();

        return cadenaResultado;
    }
}
