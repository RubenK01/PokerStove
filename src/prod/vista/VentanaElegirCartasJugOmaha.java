package prod.vista;

import prod.controlador.Controlador;
import prod.controlador.ResultadoSimulacion;
import prod.modelo.rangos.RangoSoloLectura;
import prod.modelo.reproductor.EstadoMesa;
import prod.observadores.I_Observador;

@SuppressWarnings("serial")
public class VentanaElegirCartasJugOmaha extends VentanaElegirCartas implements I_Observador {
    
	private Controlador controlador;
	private int numJugador;
	
	public VentanaElegirCartasJugOmaha(Controlador controlador, int numJugador) {
		super(4);
		this.controlador = controlador;
		this.numJugador = numJugador;
		
		this.controlador.addObservador(this);
	}
	
	protected void llamadaAlControlador(String cartas) {
		if (cartas.length() == 12) {
			controlador.marcaCartasJugadorOmaha(cartas);
			this.setVisible(false);;
		}
	}

	@Override
	public void onRangoModificado(RangoSoloLectura r, int jugadorActual) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStringRangoModificado(String entero, int jugadorActual) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAttach(RangoSoloLectura r, int jugadorActual) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onActualizaOutput(char juego, String[] rangos, String board, String dead, double tiempo, ResultadoSimulacion resultado) {

	}

	@Override
	public void onCartasJugOmahaModificadas(String cartas, int jugadorActual) {
		if (jugadorActual == numJugador) {
			marcaCartas(cartas);
		}
	}

	@Override
	public void onBoardModificado(String stringCartas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeadCardsModificadas(String stringCartas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClearAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRangoInsertado() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRankingInsertado(String nuevoRanking) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEstadoMesaCambiado(EstadoMesa nuevoEstado, String nombreAccion) {

	}

}
