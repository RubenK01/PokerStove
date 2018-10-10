package prod.modelo.reproductor.acciones;

import prod.modelo.reproductor.EstadoMesa;

public class AccionReproductor implements I_AccionReproductor{

    private EstadoMesa mesa;
    private String nombre;

    public AccionReproductor(EstadoMesa mesa, String nombre) {
        this.mesa = mesa;
        this.nombre = nombre;
    }

    @Override
    public EstadoMesa getEstado() {
        return mesa;
    }

    @Override
    public String getNombreAccion() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccionReproductor that = (AccionReproductor) o;

        if (mesa != null ? !mesa.equals(that.mesa) : that.mesa != null) return false;
        return !(nombre != null ? !nombre.equals(that.nombre) : that.nombre != null);

    }

    @Override
    public int hashCode() {
        int result = mesa != null ? mesa.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AccionReproductor{" +
                "mesa=" + mesa.toString() +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
