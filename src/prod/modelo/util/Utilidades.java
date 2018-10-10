package prod.modelo.util;

import prod.baraja.Carta;

import java.util.ArrayList;

public class Utilidades {

    public static int char2int(char c) {
        if (c == 'A')
            return 12;
        else if (c == 'K')
            return 11;
        else if (c == 'Q')
            return 10;
        else if (c == 'J')
            return 9;
        else if (c == 'T')
            return 8;
        else {
            return (int) c - 50;
        }
    }
    
    public static int char2intCarta(char c) {
        if (c == 'A')
            return 14;
        else if (c == 'K')
            return 13;
        else if (c == 'Q')
            return 12;
        else if (c == 'J')
            return 11;
        else if (c == 'T')
            return 10;
        else {
            return (int) c - 48;
        }
    }
    
    public static int charPalo2int(char c) {
        if (c == 'c')
            return 0;
        else if (c == 'd')
            return 1;
        else if (c == 'h')
            return 2;
        else
            return 3;
    }
    
    public static int charPalo2intCarta(char c) {
        if (c == 'c')
            return 0;
        else if (c == 'h')
            return 1;
        else if (c == 'd')
            return 2;
        else
            return 3;
    }

    public static String int2String(int n) {
        if (n == 12)
            return "A";
        else if (n == 11)
            return "K";
        else if (n == 10)
            return "Q";
        else if (n == 9)
            return "J";
        else if (n == 8)
            return "T";
        else {
            return Integer.toString(n + 2);
        }
    }

    public static boolean sonCoordValidas(int x, int y) {
        return !(x > Constantes.TABLA_RANGOS_X || x < 0 ||y > Constantes.TABLA_RANGOS_X || y < 0) ;
    }


    public static String getPosicionPorBoton(int numJugadores, int posicionBoton, int posicionJugador) {
        String ret = "";
        int disToBTN;
        if (numJugadores == 2) {
            if (posicionBoton == posicionJugador) {
                ret = "SB";
            } else {
                ret = "BB";
            }
        } else {
            if (posicionJugador < posicionBoton) {
            	disToBTN = posicionBoton - posicionJugador;
            	if(numJugadores == 6){
            		if(disToBTN == 5)
            			ret = "SB";            		
            		else if(disToBTN == 4)
            			ret = "BB";            		
            		else if(disToBTN == 3)
            			ret = "UTG";            		
            		else if(disToBTN == 2)
            			ret = "MP";            		
            		else	
            			ret = "CO";  
            	}
            	else if(numJugadores == 5){
            		if(disToBTN == 4)
            			ret = "SB";            		
            		else if(disToBTN == 3)
            			ret = "BB";            		
            		else if(disToBTN == 2)
            			ret = "MP";
            		else
            			ret = "CO";
            	}
            	else if(numJugadores == 4){
            		if(disToBTN == 3)
            			ret = "SB";            		
            		else if(disToBTN == 2)
            			ret = "BB";            		
            		else
            			ret = "CO";
            	}
            	else{	//=3
            		if(disToBTN == 2)
            			ret = "SB";            		
            		else
            			ret = "BB";
            	}
            		
            }
            
            else if (posicionJugador > posicionBoton) {
            	disToBTN = posicionJugador - posicionBoton;
            
            	if(numJugadores == 6){
            		if(disToBTN == 5)
            			ret = "CO";            		
            		else if(disToBTN == 4)
            			ret = "MP";            		
            		else if(disToBTN == 3)
            			ret = "UTG";            		
            		else if(disToBTN == 2)
            			ret = "BB";            		
            		else	
            			ret = "SB";  
            	}
            	else if(numJugadores == 5){
            		if(disToBTN == 4)
            			ret = "CO";            		
            		else if(disToBTN == 3)
            			ret = "MP";            		
            		else if(disToBTN == 2)
            			ret = "BB";
            		else
            			ret = "SB";
            	}
            	else if(numJugadores == 4){
            		if(disToBTN == 3)
            			ret = "CO";            		
            		else if(disToBTN == 2)
            			ret = "BB";            		
            		else
            			ret = "SB";
            	}
            	else if(numJugadores == 3){
            		if(disToBTN == 2)
            			ret = "BB";            		
            		else
            			ret = "SB";
            	}
            }
        }


        return ret;
    }

    public static ArrayList<Carta[]> devolverArrayCartas(String rango){
        ArrayList<String> resultado = new ArrayList<String>();
        String rangoArray[] = rango.split(",");

        int tam;

        for(int i = 0; i < rangoArray.length; i++){
            tam = rangoArray[i].length();
            char carta1 = rangoArray[i].charAt(0);
            char carta2 = rangoArray[i].charAt(1);

            switch (tam){
                case 2:{//parejas
                    anadirTodosLosPalos(resultado, carta1, carta2, 'p');
                }break;
                case 3:{//TT+, A3s, etc.
                    if(carta1 == carta2)//55+, 77+
                        anadirDesdeHasta(carta1, 'A', carta1, 'p', resultado);

                        //suit/off A3o, A3s, etc.
                    else if (rangoArray[i].charAt(2) == 's')
                        anadirTodosLosPalos(resultado, carta1, carta2, 's');
                    else if (rangoArray[i].charAt(2) == 'o')
                        anadirTodosLosPalos(resultado, carta1, carta2, 'o');
                }break;
                case 4:{//2h4c, A3s+, etc
                    if(rangoArray[i].charAt(3) == '+'){
                        char tipo = rangoArray[i].charAt(2);
                        char hasta = rangoArray[i].charAt(1);
                        char cartaAlta = rangoArray[i].charAt(0), desde;

                        if(cartaAlta == 'A')
                            desde = 'K';
                        else if(cartaAlta == 'K')
                            desde = 'Q';
                        else if(cartaAlta == 'Q')
                            desde = 'J';
                        else if(cartaAlta == 'J')
                            desde = 'T';
                        else if(cartaAlta == 'T')
                            desde = (char)('0'+9);
                        else
                            desde = (char) (cartaAlta - 1);

                        anadirDesdeHasta(cartaAlta, desde, hasta, tipo, resultado);

                    }
                    else if (!resultado.contains(rangoArray[i]))
                        resultado.add(rangoArray[i]);
                }break;
                case 5:{//KK-55
                    char cartaAlta = rangoArray[i].charAt(0);
                    char desde = rangoArray[i].charAt(1);
                    char hasta = rangoArray[i].charAt(3);
                    char tipo = 'p';

                    anadirDesdeHasta(cartaAlta, desde, hasta, tipo, resultado);
                }break;
                case 6:{//random
                    anadirDesdeHasta('A', 'A', '2', 's', resultado);
                    anadirDesdeHasta('A', 'A', '2', 'o', resultado);

                    anadirDesdeHasta('K', 'K', '2', 's', resultado);
                    anadirDesdeHasta('K', 'K', '2', 'o', resultado);

                    anadirDesdeHasta('Q', 'Q', '2', 's', resultado);
                    anadirDesdeHasta('Q', 'Q', '2', 'o', resultado);

                    anadirDesdeHasta('J', 'J', '2', 's', resultado);
                    anadirDesdeHasta('J', 'J', '2', 'o', resultado);

                    anadirDesdeHasta('T', 'T', '2', 's', resultado);
                    anadirDesdeHasta('T', 'T', '2', 'o', resultado);

                    for (int j = 9; j > 1; j--){
                        anadirDesdeHasta((char) ('0' + j), (char) ('0' + j), '2', 's', resultado);
                        anadirDesdeHasta((char) ('0' + j), (char) ('0' + j), '2', 'o', resultado);
                    }
                }break;

                case 7:{//KTs-K5s
                    char cartaAlta = rangoArray[i].charAt(0);
                    char desde = rangoArray[i].charAt(1);
                    char hasta = rangoArray[i].charAt(5);
                    char tipo = rangoArray[i].charAt(6);

                    anadirDesdeHasta(cartaAlta, desde, hasta, tipo, resultado);
                }break;
                default:{
                }
            }
        }

        //pasa de String a Carta[]

        ArrayList<Carta[]> resultado_Carta = new ArrayList<Carta[]>();


        for(int i = 0; i < resultado.size(); i++){
			/*
			E_Carta_Valor valor = null;
			E_Carta_Palo palo = null;

			valor = E_Carta_Valor.getValorPorInt( Utilidades.char2intCarta(resultado.get(i).charAt(0))+2 );
			palo = E_Carta_Palo.getPaloPorInt( Utilidades.charPalo2intCarta(resultado.get(i).charAt(1)) );
			*/
            Carta [] carta = new Carta[2];

            carta[0] = new Carta(resultado.get(i).substring(0, 2));
			/*
			valor = E_Carta_Valor.getValorPorInt( Utilidades.char2intCarta(resultado.get(i).charAt(2)) +2);
			palo = E_Carta_Palo.getPaloPorInt( Utilidades.charPalo2intCarta(resultado.get(i).charAt(3)) );
			*/
            carta[1] = new Carta(resultado.get(i).substring(2));

            resultado_Carta.add(carta);

        }

        return resultado_Carta;
    }

    private static void anadirDesdeHasta(char cartaAlta, char desde, char hasta, char tipo, ArrayList<String> resultado){


        boolean finDesde = false, acabado = false;

        if(desde == 'A'){
            if(!finDesde)
                finDesde = true;
            else if( 'A' == hasta)
                acabado = true;
            anadirTodosLosPalos(resultado, cartaAlta, 'A', tipo);
        }
        if(!acabado && (desde == 'K' || finDesde)){
            if(!finDesde)
                finDesde = true;
            else if( 'K' == hasta)
                acabado = true;
            anadirTodosLosPalos(resultado, cartaAlta, 'K', tipo);
        }
        if(!acabado && (desde == 'Q' || finDesde)){
            anadirTodosLosPalos(resultado, cartaAlta, 'Q', tipo);

            if(!finDesde)
                finDesde = true;
            else if( 'Q' == hasta)
                acabado = true;
        }
        if(!acabado && (desde == 'J' || finDesde)){
            anadirTodosLosPalos(resultado, cartaAlta, 'J', tipo);

            if(!finDesde)
                finDesde = true;
            else if( 'J' == hasta)
                acabado = true;
        }
        if(!acabado && (desde == 'T' || finDesde)){
            anadirTodosLosPalos(resultado, cartaAlta, 'T', tipo);

            if(!finDesde)
                finDesde = true;
            else if( 'T' == hasta)
                acabado = true;
        }
        for (int j = 9; j > 1 && !acabado; j--){

            if(desde == (char)('0'+j) || finDesde){
                anadirTodosLosPalos(resultado, cartaAlta, (char) ('0' + j), tipo);

				if(!finDesde)
					finDesde = true;
				else if( (char)('0'+j) == hasta)
					acabado = true;
			}
        }
    }

    private static void anadirTodosLosPalos(ArrayList<String> resultado, char carta1, char carta2, char tipo){

        if(carta1 != carta2 && tipo != 'p'){
            if(tipo == 's'){  		//suited
                if(!resultado.contains(carta1 + "h" + carta2 + "h"))
                    resultado.add(carta1 + "h" + carta2 + "h");
                if(!resultado.contains(carta1 + "s" + carta2 + "s"))
                    resultado.add(carta1 + "s" + carta2 + "s");
                if(!resultado.contains(carta1 + "d" + carta2 + "d"))
                    resultado.add(carta1 + "d" + carta2 + "d");
                if(!resultado.contains(carta1 + "c" + carta2 + "c"))
                    resultado.add(carta1 + "c" + carta2 + "c");
            }
            else if(tipo == 'o'){	//offsuited (6 combinaciones posibles de colores)
                if(!resultado.contains(carta1 + "h" + carta2 + "c"))
                    resultado.add(carta1 + "h" + carta2 + "c");
                if(!resultado.contains(carta1 + "h" + carta2 + "s"))
                    resultado.add(carta1 + "h" + carta2 + "s");
                if(!resultado.contains(carta1 + "h" + carta2 + "d"))
                    resultado.add(carta1 + "h" + carta2 + "d");
                if(!resultado.contains(carta1 + "c" + carta2 + "s"))
                    resultado.add(carta1 + "c" + carta2 + "s");
                if(!resultado.contains(carta1 + "c" + carta2 + "d"))
                    resultado.add(carta1 + "c" + carta2 + "d");
                if(!resultado.contains(carta1 + "d" + carta2 + "s"))
                    resultado.add(carta1 + "d" + carta2 + "s");

                if(!resultado.contains(carta1 + "c" + carta2 + "h"))
                    resultado.add(carta1 + "c" + carta2 + "h");
                if(!resultado.contains(carta1 + "s" + carta2 + "h"))
                    resultado.add(carta1 + "s" + carta2 + "h");
                if(!resultado.contains(carta1 + "d" + carta2 + "h"))
                    resultado.add(carta1 + "d" + carta2 + "h");
                if(!resultado.contains(carta1 + "s" + carta2 + "c"))
                    resultado.add(carta1 + "s" + carta2 + "c");
                if(!resultado.contains(carta1 + "d" + carta2 + "c"))
                    resultado.add(carta1 + "d" + carta2 + "c");
                if(!resultado.contains(carta1 + "s" + carta2 + "d"))
                    resultado.add(carta1 + "s" + carta2 + "d");
            }
        }else if ((carta1 == carta2) || tipo == 'p'){ //parejas
            if(!resultado.contains(carta2 + "h" + carta2 + "c"))
                resultado.add(carta2 + "h" + carta2 + "c");
            if(!resultado.contains(carta2 + "h" + carta2 + "s"))
                resultado.add(carta2 + "h" + carta2 + "s");
            if(!resultado.contains(carta2 + "h" + carta2 + "d"))
                resultado.add(carta2 + "h" + carta2 + "d");
            if(!resultado.contains(carta2 + "c" + carta2 + "s"))
                resultado.add(carta2 + "c" + carta2 + "s");
            if(!resultado.contains(carta2 + "c" + carta2 + "d"))
                resultado.add(carta2 + "c" + carta2 + "d");
            if(!resultado.contains(carta2 + "d" + carta2 + "s"))
                resultado.add(carta2 + "d" + carta2 + "s");
        }
    }

    /* Le pasamos un array de String con las 4 cartas de Omaha de un jugador en formato String,
	   y devolvemos una lista con dichas cartas. */
    public static ArrayList<Carta> devolverArrayCartasOmaha(String manoOmaha) {

        ArrayList<Carta> resultado_Carta_Omaha = new ArrayList<Carta>();

        Carta carta;

        // Pasamos el String a carta, por ejemplo 4c o Qh a ser una carta, de string a carta
        for(int j = 0; j < 12; j= j+3) {

            carta = new Carta(manoOmaha.substring(j, j+2));

            // Añadimos a la lista.
            resultado_Carta_Omaha.add(carta);

        }

        return resultado_Carta_Omaha;
    }

}
