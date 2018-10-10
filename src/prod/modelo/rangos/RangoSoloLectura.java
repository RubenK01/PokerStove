package prod.modelo.rangos;

/**
 * Interfaz para asegurarse de que el observador de la prod.vista no cambia el rango
 */
public interface RangoSoloLectura {

    boolean getManoEnRango(int x, int y);
    boolean getManoEnRango(String mano);
    String toString();
}
