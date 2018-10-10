package prod.controlador;

import prod.modelo.rangos.*;
import prod.modelo.rankings.Ranking;
import prod.modelo.reproductor.Reproductor;
import prod.modelo.util.Constantes;
import prod.modelo.util.LecturaEscritura;
import prod.modelo.util.Utilidades;
import prod.observadores.I_Observable;
import prod.observadores.I_Observador;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import prod.baraja.Carta;

/**
 *
 */
public class Controlador implements I_Observable {
	
	private int numeroJugadoresHoldem = 10;
	private int numeroJugadoresOmaha = 2;
	
	private int jugadorActual;
	
    private Rango[] rangosDeJugadoresHoldem;
	private String[] rangosTextBoxHoldem;
    private String[] cartasDeJugadoresOmaha;
    
    private String mesa;
    private String quemadas;
    
    private List<I_Observador> observadores;
    
    private Map<String, String[]> rankings;
	private Map<String, Float> rangos;
    private Ranking ranking;

	private Reproductor reproductor;

    public Controlador(Map<String, String[]> rankings, Map<String, Float> rangos) {
        this.rankings = rankings;
        this.rangos = rangos;
        ranking = new Ranking(rankings.get("SKLANSKY CHUBUKOV"));
        
        this.rangosDeJugadoresHoldem = new Rango[numeroJugadoresHoldem];
        for (int i = 0; i < this.rangosDeJugadoresHoldem.length; i++){
            this.rangosDeJugadoresHoldem[i] = new RangoUsuario();
        }

		this.rangosTextBoxHoldem = new String[numeroJugadoresHoldem];
		for (int i = 0; i < this.rangosTextBoxHoldem.length; i++){
			this.rangosTextBoxHoldem[i] = "";
		}

        this.cartasDeJugadoresOmaha = new String[numeroJugadoresOmaha];
        for (int i = 0; i < this.cartasDeJugadoresOmaha.length; i++){
            this.cartasDeJugadoresOmaha[i] = "";
        }
        
        mesa = "";
        quemadas = "";
        
        observadores = new ArrayList<I_Observador>();

		reproductor = new Reproductor();
    }
    
    public Map<String, String[]> getRankings(){
    	return rankings;
    }
    
	public Map<String, Float> getRangos() {
		return rangos;
	}
    
    public String[] getStringRankings() {
    	int i = 0;
    	String[] nombresRankings = new String[rankings.keySet().size()];
    	for (String key : rankings.keySet()) {
    		nombresRankings[i] = key;
    		i++;
    	}
    	return nombresRankings;
    }
    
    public String[] getStringRangos() {
    	int i = 0;
    	String[] rangosString = new String[rangos.keySet().size()];
    	for (String key : rangos.keySet()) {
    		rangosString[i] = key + " : " + rangos.get(key);
    		i++;
    	}
    	return rangosString;
    }
    
    public void cambiaRanking(String nombreRanking) {
    	ranking = new Ranking(rankings.get(nombreRanking));
    }
    
    public void insertaRanking(String nombre, String[] manos) {
    	rankings.put(nombre, manos);
    	LecturaEscritura.escribeRanking(nombre, manos);
    	
    	//Notifica a los prod.observadores
        for (I_Observador obs : this.observadores) {
            obs.onRankingInsertado(nombre);
        }
    }
    
    public void insertaRango(String nombre, Float porcentaje) {
    	rangos.put(nombre, porcentaje);
    	LecturaEscritura.escribeRango(nombre + " : " + porcentaje.toString());
    	
    	//Notifica a los prod.observadores
        for (I_Observador obs : this.observadores) {
            obs.onRangoInsertado();
        }
    }

	/**
	 * Metodos para el reproductor
	 */

	public void iniciaReproductor(File archivo) {
		reproductor = new Reproductor(archivo);
	}

	public void avanzarAccion() {
		if (reproductor.puedeAvanzarAccion()) {
			reproductor.avanzaAccion();

			for (I_Observador obs : observadores) {
				obs.onEstadoMesaCambiado(reproductor.getEstadoActual(), reproductor.getNombreAccionActual());
			}
		} else {
			JOptionPane.showMessageDialog(null, "No hay mas acciones en la mano.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void retrocederAccion() {
		if (reproductor.puedeRetrocederAccion()) {
			reproductor.retrocedeAccion();

			for (I_Observador obs : observadores) {
				obs.onEstadoMesaCambiado(reproductor.getEstadoActual(), reproductor.getNombreAccionActual());
			}
		} else {
			JOptionPane.showMessageDialog(null, "No se puede retroceder mas acciones.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void avanzarMano() {
		if (reproductor.puedeAvanzarMano()) {
			reproductor.avanzaMano();

			for (I_Observador obs : observadores) {
				obs.onEstadoMesaCambiado(reproductor.getEstadoActual(), reproductor.getNombreAccionActual());
			}
		} else {
			JOptionPane.showMessageDialog(null, "No hay mas manos en el archivo.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void retrocederMano() {
		if (reproductor.puedeRetrocederMano()) {
			reproductor.retrocedeMano();

			for (I_Observador obs : observadores) {
				obs.onEstadoMesaCambiado(reproductor.getEstadoActual(), reproductor.getNombreAccionActual());
			}
		} else {
			JOptionPane.showMessageDialog(null, "No se puede retroceder mas manos.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void resetAcciones() {
		reproductor.resetAcciones();
		for (I_Observador obs : observadores) {
			obs.onEstadoMesaCambiado(reproductor.getEstadoActual(), reproductor.getNombreAccionActual());
		}
	}

	public void resetManos() {
		reproductor.resetManos();
		for (I_Observador obs : observadores) {
			obs.onEstadoMesaCambiado(reproductor.getEstadoActual(), reproductor.getNombreAccionActual());
		}
	}

     /**
     * Metodos para cambiar el prod.modelo desde las vistas
     */
    public void marcaManoJugadorActual(int x, int y) {
        rangosDeJugadoresHoldem[jugadorActual].marcarMano(x, y);
        rangosTextBoxHoldem[jugadorActual] = rangosDeJugadoresHoldem[jugadorActual].toString();
        //Notifica a los prod.observadores
        for (I_Observador obs : this.observadores) {
            obs.onRangoModificado(this.rangosDeJugadoresHoldem[this.jugadorActual], this.jugadorActual);
        }
    }

    public void desmarcaManoJugadorActual(int x, int y) {
        rangosDeJugadoresHoldem[jugadorActual].desmarcarMano(x, y);
        //Notifica a los prod.observadores
        for (I_Observador obs : this.observadores) {
            obs.onRangoModificado(this.rangosDeJugadoresHoldem[this.jugadorActual], this.jugadorActual);
        }
    }

    public void marcarRangoJugadorActual(String rango) {
        rangosDeJugadoresHoldem[jugadorActual].marcarRango(rango);
        //Notifica a los prod.observadores
        for (I_Observador obs : this.observadores) {
            obs.onRangoModificado(this.rangosDeJugadoresHoldem[this.jugadorActual], this.jugadorActual);
        }
    }

    public void marcarTextoRangoJugadorActual(String texto) {
        this.rangosTextBoxHoldem[this.jugadorActual] = texto;
        for (I_Observador obs : this.observadores) {
            obs.onStringRangoModificado(this.rangosTextBoxHoldem[this.jugadorActual], this.jugadorActual);
        }
    }

    public void marcaRangoJugadorActual(float porcentaje) {
		
        this.rangosDeJugadoresHoldem[this.jugadorActual].marcarPorcentaje(porcentaje, this.ranking);

        //Notifica a los prod.observadores
        for (I_Observador obs : this.observadores) {
            obs.onRangoModificado(this.rangosDeJugadoresHoldem[this.jugadorActual], this.jugadorActual);
        }
    }
    
    public void marcaRangoPolarizadoJugadorActual(int w, int x, int y, int z) {
    	
        this.rangosDeJugadoresHoldem[this.jugadorActual].marcarPolarizado(w, x, y, z, this.ranking);

        //Notifica a los prod.observadores
        for (I_Observador obs : this.observadores) {
            obs.onRangoModificado(this.rangosDeJugadoresHoldem[this.jugadorActual], this.jugadorActual);
        }
	}
	
	public void marcaCartasJugadorOmaha(String stringCartas) {
		this.cartasDeJugadoresOmaha[jugadorActual- Constantes.NUM_JUGADORES_HOLDEM] = stringCartas;
		
		//Notifica a los prod.observadores
        for (I_Observador obs : this.observadores) {
            obs.onCartasJugOmahaModificadas(this.cartasDeJugadoresOmaha[jugadorActual - 10], jugadorActual - 10);
        }
	}

	public void marcaCartasBoard(String stringCartas) {
		mesa = stringCartas;
		
		//Notifica a los prod.observadores
        for (I_Observador obs : this.observadores) {
            obs.onBoardModificado(stringCartas);
        }
	}

	public void marcaDeadCards(String stringCartas) {
		quemadas = stringCartas;
		
		//Notifica a los prod.observadores
        for (I_Observador obs : this.observadores) {
            obs.onDeadCardsModificadas(stringCartas);
        }
	}

    public void cambiaJugadorActual (int nuevoJugador) {
        if (nuevoJugador <= this.numeroJugadoresHoldem + this.numeroJugadoresOmaha) {
            this.jugadorActual = nuevoJugador;
        }
    }

	public void evaluarHoldem() {
		boolean todoRandom = true;
		double t = 0;
		int jugadoresRandom = 0;
		ResultadoSimulacion resultados;
		
		for (int i = 0; i < this.rangosTextBoxHoldem.length; i++) {
            if (!this.rangosTextBoxHoldem[i].equals(
            		"22+,A2s+,A2o+,K2s+,K2o+,Q2s+,"
            		+ "Q2o+,J2s+,J2o+,T2s+,T2o+,92s+,92o+,82s+,82o+,"
            		+ "72s+,72o+,62s+,62o+,52s+,52o+")) {
            	if (!this.rangosTextBoxHoldem[i].equals("")) {
            		todoRandom = false;
            	}            	
            } else if (!this.rangosTextBoxHoldem[i].equals(""))   {         		
                jugadoresRandom++;
            }
        }		
		
		if (todoRandom) {
			
			double[] res = new double[53];
			double eq = 100.0/jugadoresRandom;
			for (int i = 0 ; i < jugadoresRandom; i++) {
				res[i*3] = eq;				
			}
			
			resultados = new ResultadoSimulacion(res, new int[jugadoresRandom], new int[jugadoresRandom], 1);
		}
		
		else {
		
			//Parsear mesa
			ArrayList<Carta> cartasmesa = new ArrayList<Carta>();
			String[] tokens = mesa.split(" ");
	
	        if (!tokens[0].equals("")) {
	            for (String token : tokens) {
	                cartasmesa.add(new Carta(token));
	            }
	        }
	
			//Parsear quemadas
			ArrayList<Carta> cartasquemadas = new ArrayList<Carta>();
			tokens = quemadas.split(" ");
	
	        if (!tokens[0].equals("")) {
	            for (String token : tokens) {
	                cartasquemadas.add(new Carta(token));
	            }
	        }
	
	
			ArrayList<ArrayList<Carta[]>> cartasrangos = new ArrayList<ArrayList<Carta[]>>();
			for (int i = 0; i < this.rangosTextBoxHoldem.length; i++) {
	            if (!this.rangosTextBoxHoldem[i].equals("")) {
	                cartasrangos.add(Utilidades.devolverArrayCartas(this.rangosTextBoxHoldem[i]));
	            }
			}
	
			
			//Calcular resultado
			t = System.currentTimeMillis();		
	        resultados = RealizarSimulaciones.calcularResultados(cartasrangos, cartasmesa, cartasquemadas);
	        t = System.currentTimeMillis() - t;
		}
        
        for (I_Observador obs : observadores) {
            //Cada observador cogera lo que necesite de este array.
            obs.onActualizaOutput('H', this.rangosTextBoxHoldem, this.mesa, this.quemadas, t, resultados);
        }

	}

	public void evaluarOmaha() {
		
		// Parsear mesa
		ArrayList<Carta> cartasmesa = new ArrayList<Carta>();
		String[] tokens = mesa.split(" ");

		if (!tokens[0].equals("")) {
			for (String token : tokens) {
				cartasmesa.add(new Carta(token));
		    }
		}
		
		// Parsear quemadas
		ArrayList<Carta> cartasquemadas = new ArrayList<Carta>();
			tokens = quemadas.split(" ");

		    if (!tokens[0].equals("")) {
		    	for (String token : tokens) {
		    		cartasquemadas.add(new Carta(token));
		        }
		    }
		    
		    
		ArrayList<ArrayList<Carta>> cartasrangosOmaha = new ArrayList<ArrayList<Carta>>();
		for (int i = 0; i < cartasDeJugadoresOmaha.length; i++) {
			
            if (!this.cartasDeJugadoresOmaha[i].equals("")) {
                cartasrangosOmaha.add(Utilidades.devolverArrayCartasOmaha(this.cartasDeJugadoresOmaha[i]));
            }
		}
	
		// Calcular resultado
		double t = System.currentTimeMillis();		
        ResultadoSimulacion resultados = RealizarSimulaciones.calcularResultadosOmaha(cartasrangosOmaha, cartasmesa, cartasquemadas);
		t = System.currentTimeMillis() - t;
        
        for (I_Observador obs : observadores) {
            //Cada observador cogera lo que necesite de este array.
            obs.onActualizaOutput('O', this.cartasDeJugadoresOmaha, this.mesa, this.quemadas, t, resultados);
        }
		
	}
	
	
	
	public void clearAll() {
		quemadas = "";
		mesa = "";

		for (int i = 0;i < rangosTextBoxHoldem.length; i++) {
			rangosTextBoxHoldem[i] = "";
		}
		for (int i = 0;i < cartasDeJugadoresOmaha.length; i++) {
			cartasDeJugadoresOmaha[i] = "";
		}
		
		for (int i = 0; i < rangosDeJugadoresHoldem.length; i++) {
			rangosDeJugadoresHoldem[i].marcarPorcentaje(0, ranking);
		}

		//Notifica a los prod.observadores
        for (I_Observador obs : this.observadores) {
            obs.onClearAll();
        }
	}


    //-----------------
	//Observer
    //-----------------

    @Override
    public void addObservador(I_Observador observador) {
        this.observadores.add(observador);
        //observador.onAttach(this.rangosDeJugadoresHoldem[this.jugadorActual], this.jugadorActual);
    }

    @Override
    public void removeObservador(I_Observador observador) {
        this.observadores.remove(observador);
    }

    public int getNumeroJugadores() {
        return numeroJugadoresHoldem;
    }

	
    
}
