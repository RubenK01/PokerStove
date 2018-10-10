/**
 * Clase que se encargará de:
 *  - Guardar una mano de (máximo) 5 cartas
 *  - Guardar información sobre las jugadas de mano (si tiene parejas, draws, tríos...)
 */

package prod.modelo.analizador;

import prod.baraja.Carta;
import prod.modelo.excepciones.EManoLlena;

import java.util.ArrayList;
import java.util.List;

public class Mano {

    //-----------------------
    // CAMPOS Y CONSTRUCTORES
    //-----------------------

    /**
     * Lista de (máximo) 5 cartas
     */
    private List<Carta> cartas;

    /**
     * Lista de jugadas que tiene la mano, ordenadas de mejor a peor.
     */
    private List<I_Jugada> jugadas;

    /**
     * Constructor vacío que inicializa las listas
     */
    public Mano() {
    	cartas = new ArrayList<Carta>();    	
    }

    //-----------------------
    // MÉTODOS PROPIOS
    //-----------------------

    /**
     * Método para añadir una carta a la mano.
     * Si la mano contiene ya 5 cartas, deberá lanzar una excepción de mano llena.
     *
     * @param carta: Carta para añadir.
     * @throws EManoLlena
     */
    public void anadirCarta(Carta carta) throws EManoLlena {
    	if (cartas.size() == 5)
    		throw new EManoLlena();
    	
    	cartas.add(carta);
    	
    	/*
    	ArrayList<Carta> listaAux = new ArrayList<Carta>(cartas);
    	int valorCarta = carta.getValor().getValor(), valorCartaDeLaLista;
    	boolean insertado = false;
    	try {
    		if (cartas.size() == 5)
        		throw new EManoLlena();
    		
        	// Vamos añadir las cartas de manera ordenada, por lo tanto.
        	if(cartas.size() == 0) {
        		// La lista está vacía añadimos la carta directamente.
        		cartas.add(carta);
        	}
        	else {
        		cartas.clear();
	        	while(!listaAux.isEmpty()) {
	        		valorCartaDeLaLista = listaAux.get(0).getValor().getValor(); // para el valor del primer elemento en la lista
	        		// Si el valor de la carta a añadir es mayor que el de la carta en la primera pos de la lista (que esta ordenada)
	        		if(valorCarta > valorCartaDeLaLista && !insertado) {
	        			insertado = true;
	        			cartas.add(carta);
	        		}
	        		cartas.add(listaAux.get(0));
	        		listaAux.remove(0);
	        	}
	        	if (!insertado) // si la carta es <= que todos los elementos de la lista no se habrá insertado
	        		cartas.add(carta);
        	}
        } catch (EManoLlena e) {
    		System.out.println(e.getMessage());
    	}
    	*/
    }

    
    /**
     * Método para poder asignar listas enteras de cartas a la mano.
     *
     * @param cartas
     * @throws EManoLlena
     */
    public void llenarMano(List<Carta> cartas) throws EManoLlena {
    	if (cartas.size() + this.cartas.size() > 5) {
    		throw new EManoLlena();
    	}
    	for (Carta carta : cartas) {
    		this.anadirCarta(carta);
    	}
    }

    /**
     * Método que asigna una jugada a la mano, por ejemplo un FlushDraw
     * @param jugada
     */
    public void asignarJugada(I_Jugada jugada) {}

    //-----------------------
    // GETTERS Y SETTERS
    //-----------------------

    public List<Carta> getCartas() {
        return cartas;
    }

    public List<I_Jugada> getJugadas() {
        return jugadas;
    }

    /**
     * @return las cartas de la mano, en orden, en una string
     */
    @Override
    public String toString() {
    	StringBuilder strBuild = new StringBuilder();
    	for (int i = 0; i < cartas.size(); i++)
    		strBuild.append(cartas.get(i).toString());
        return strBuild.toString();
    }


}
