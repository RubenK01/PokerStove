package prod.modelo.reproductor.acciones;

import prod.modelo.reproductor.EstadoMesa;

public interface I_AccionReproductor {

    EstadoMesa getEstado();
    String getNombreAccion();

}
