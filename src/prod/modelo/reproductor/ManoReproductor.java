package prod.modelo.reproductor;

import prod.modelo.reproductor.acciones.I_AccionReproductor;

import java.util.List;

public class ManoReproductor {

    private List<I_AccionReproductor> acciones;

    public ManoReproductor(List<I_AccionReproductor> acciones) {
        this.acciones = acciones;
    }

    public List<I_AccionReproductor> getAcciones() {
        return acciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManoReproductor that = (ManoReproductor) o;

        return !(acciones != null ? !acciones.equals(that.acciones) : that.acciones != null);

    }

    @Override
    public int hashCode() {
        return acciones != null ? acciones.hashCode() : 0;
    }
}
