package prod.modelo.reproductor;

import prod.modelo.excepciones.EReproductor;
import prod.modelo.util.LecturaEscritura;

import java.io.File;
import java.util.List;
import java.util.Stack;

public class Reproductor {

    private int indiceAccion;
    private List<ManoReproductor> manos;
    private int indiceMano;

    /**
     * Constructor vacío, por defecto.
     */
    public Reproductor() {
        iniciaIndices();
        manos = new Stack<>();
    }

    /**
     * Contructor que permite cargar un archivo directamente.
     * @param rutaAlArchivo Path completo al archivo que se va a cargar.
     */
    public Reproductor(File archivo) {
        iniciaIndices();
        manos = LecturaEscritura.leeArchivoPokerStars(archivo);
    }

    public void avanzaAccion() {
        if (puedeAvanzarAccion()) {
            indiceAccion++;
        } else {
            throw new EReproductor("No hay mas acciones en la mano");
        }
    }

    public void retrocedeAccion() {
        if (puedeRetrocederAccion()) {
            indiceAccion--;
        } else {
            throw new EReproductor("No se puede retroceder mas acciones.");
        }
    }

    public boolean puedeAvanzarAccion() {
        return (indiceAccion < (manos.get(indiceMano).getAcciones().size()-1));
    }

    public boolean puedeRetrocederAccion() {
        return (indiceAccion > 0);
    }

    public void avanzaMano() {
        if (puedeAvanzarMano()) {
            indiceMano++;
            indiceAccion = 0;
        } else {
            throw new EReproductor("No hay mas manos en el archivo");
        }
    }

    public void retrocedeMano() {
        if (puedeRetrocederMano()) {
            indiceMano--;
            indiceAccion = 0;
        } else {
            throw new EReproductor("No se puede retroceder mas manos");
        }
    }

    public boolean puedeAvanzarMano() {
        return (indiceMano < (manos.size()-1));
    }

    public boolean puedeRetrocederMano() {
        return (indiceMano > 0);
    }

    public EstadoMesa getEstadoActual() {
        return manos.get(indiceMano).getAcciones().get(this.indiceAccion).getEstado();
    }

    public String getNombreAccionActual() {
        return manos.get(indiceMano).getAcciones().get(this.indiceAccion).getNombreAccion();
    }

    private void iniciaIndices() {
        indiceAccion = 0;
        indiceMano = 0;
    }


    public void resetAcciones() {
        indiceAccion = 0;
    }

    public void resetManos() {
        indiceMano = 0;
    }
}
