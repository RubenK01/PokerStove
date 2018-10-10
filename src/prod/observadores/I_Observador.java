package prod.observadores;

import prod.controlador.ResultadoSimulacion;
import prod.modelo.rangos.RangoSoloLectura;
import prod.modelo.reproductor.EstadoMesa;

/**
 * Interfaz que deben implementar los paneles de la prod.vista correspondiente.
 *
 */

public interface I_Observador {

    /**
     * Método al que se llamará cada vez que cambie el rango, para updatear el display
     * @param r
     *
     */
    void onRangoModificado(RangoSoloLectura r, int jugadorActual);
    
    void onStringRangoModificado(String entero, int jugadorActual);

    void onAttach(RangoSoloLectura r, int jugadorActual);
    
    void onActualizaOutput(char juego, String[] rangos, String board, String dead, double tiempo, ResultadoSimulacion resultado);
    
	void onCartasJugOmahaModificadas(String cartasDeJugadoresOmaha, int jugadorActual);

	void onBoardModificado(String stringCartas);

	void onDeadCardsModificadas(String stringCartas);

	void onClearAll();

	void onRangoInsertado();

	void onRankingInsertado(String nuevoRanking);

	void onEstadoMesaCambiado(EstadoMesa nuevoEstado, String nombreAccion);

}
