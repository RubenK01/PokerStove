package prod.modelo.rangos;

import prod.modelo.excepciones.EIndiceInvalido;
import prod.modelo.util.Constantes;
import prod.modelo.util.Utilidades;

public class RangoUsuario extends Rango {


    /**
     * Constructor vacío que inicializa la tabla de booleanos
     */
    public RangoUsuario() {
        super();
    }

    public void marcarMano(int x, int y) {
        if (Utilidades.sonCoordValidas(x, y)) {
            this.rango[x][y] = true;
        } else {
            throw new EIndiceInvalido();
        }
    }

    @Override
    public void desmarcarMano(int x, int y) {
        if (Utilidades.sonCoordValidas(x, y)) {
            this.rango[x][y] = false;
        } else {
            throw new EIndiceInvalido();
        }
    }

    @Override
    public void cambiarMano(int x, int y) {
        if (Utilidades.sonCoordValidas(x, y)) {
            this.rango[x][y] = !this.rango[x][y];
        } else {
            throw new EIndiceInvalido();
        }
    }
    
    @Override
    public void marcarRango(String rango) {
    	boolean[][] rangoAMarcar = devolverArrayDeBooleanosConCartasEscritas(rango);
        for (int i = 0; i < Constantes.TABLA_RANGOS_X; i++) {
            for (int j = 0; j < Constantes.TABLA_RANGOS_X; j++) {
            	this.rango[i][j] = rangoAMarcar[i][j];
            }
        }
    }

    
    // Devolverá el array de booleanos con las posiciones a true del String pasado como argumento.
    private boolean[][] devolverArrayDeBooleanosConCartasEscritas(String cartas) {

        char posPCarta1, posSCarta1, simbolo, simbolo2, posSCarta2;
        int i = 0, carta1Pint, carta1Sint, carta2Sint;
        boolean[][] tabla = new boolean[Constantes.TABLA_RANGOS_X][Constantes.TABLA_RANGOS_X];

        // Recorremos el string.
        while(i < cartas.length()) {

            simbolo = 0; simbolo2 = 0;

            // Cogemos los tres primeros caracteres.
            posPCarta1 = cartas.charAt(i);
            i++;
            // Lo pasamos a entero.
            carta1Pint = Utilidades.char2int(posPCarta1);

            // Cogemos el siguiente caracter.
            posSCarta1 = cartas.charAt(i);
            i++;
            carta1Sint = Utilidades.char2int(posSCarta1);

            if(i < cartas.length()) {
                simbolo = cartas.charAt(i);
                i++;
            }
            if(simbolo == '+') {
                // Ponemos a true en la tabla desde la posici?n para arriba en la diagonal
                // aumentando en 1 fila y columna
                for(int j = carta1Pint; j <= (Constantes.TABLA_RANGOS_X-1); j++) {

                    tabla[j][j] = true;
                }
                i++;

            }
            else if(simbolo == ('s')) {


                if(i < cartas.length()){
                    // Leemos el siguiente caracter.
                    simbolo2 = cartas.charAt(i);
                    i++;
                }
                if(simbolo2 == '+') {

                    for(int j = carta1Sint; j <= carta1Pint - 1; j++) {

                        tabla[carta1Pint][j] = true;
                    }
                    i++;
                }
                // Comprobamos si el siguiente caracter es ',' o un gui?n.
                if(simbolo2 == '-') {
                    i++;
                    // Cogemos el siguiente caracter.
                    posSCarta2 = cartas.charAt(i);
                    i++;
                    carta2Sint = Utilidades.char2int(posSCarta2);


                    if(i < cartas.length()) {
                        i++;
                    }


                    // Recorremos
                    // La fila se queda constante.
                    for(int g = carta1Sint; g >= carta2Sint; g--) {

                        tabla[carta1Pint][g] = true;
                    }

                }
                else {
                    // Si es ',' solamente ponemos esa carta.
                    tabla[carta1Pint][carta1Sint] = true;
                }

            }
            else if(simbolo == 'o') {
                if(i < cartas.length()) {
                    // Leemos el siguiente caracter.
                    simbolo2 = cartas.charAt(i);
                    i++;
                }

                if(simbolo2 == '+') {

                    for(int j = carta1Sint; j <= carta1Pint - 1; j++) {

                        tabla[j][carta1Pint] = true;
                    }
                    i++;
                }
                // Comprobamos si el siguiente caracter es ',' o un gui?n.
                if(simbolo2 == '-') {
                    i++;
                    // Cogemos el siguiente caracter.
                    posSCarta2 = cartas.charAt(i);
                    i++;
                    carta2Sint = Utilidades.char2int(posSCarta2);

                    if(i < cartas.length()) {
                        i++;
                    }

                    // Recorremos
                    // La columna se queda constante.
                    for(int g = carta1Sint; g >= carta2Sint; g--) {

                        tabla[g][carta1Pint] = true;
                    }

                }
                else {
                    // Si es ',' solamente ponemos esa carta.
                    tabla[carta1Sint][carta1Pint] = true;

                }
            }
            else if(simbolo == '-'){
                //Es una combinación de parejas
                int segundaPareja = Utilidades.char2int(cartas.charAt(i + 1));
                for (int j = carta1Pint; j > segundaPareja; j--) {
                    tabla[j][j] = true;
                }
            } else {
                // Es AA o KK u otro par de cartas de la diagonal.
                tabla[carta1Pint][carta1Pint] = true;
            }

            if(i < cartas.length()) {
                posPCarta1 = cartas.charAt(i);

                if(posPCarta1 == ',') {
                    i++;
                }
            }
        }
        return tabla;
    }

}
