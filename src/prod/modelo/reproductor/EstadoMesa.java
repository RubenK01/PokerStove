package prod.modelo.reproductor;

import prod.baraja.Carta;

import java.util.List;

public class EstadoMesa {

    private List<EstadoJugador> jugadores;
    private double pot;
    private List<Carta> mesa;

    public EstadoMesa(List<EstadoJugador> jugadores, double pot, List<Carta> mesa) {
        this.jugadores = jugadores;
        this.pot = pot;
        this.mesa = mesa;
    }

    public List<EstadoJugador> getJugadores() {
        return jugadores;
    }

    public double getPot() {
        return pot;
    }

    public List<Carta> getMesa() {
        return mesa;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstadoMesa that = (EstadoMesa) o;

        if (Double.compare(that.pot, pot) != 0) return false;
        if (jugadores != null ? !jugadores.equals(that.jugadores) : that.jugadores != null) return false;
        return !(mesa != null ? !mesa.equals(that.mesa) : that.mesa != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = jugadores != null ? jugadores.hashCode() : 0;
        temp = Double.doubleToLongBits(pot);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (mesa != null ? mesa.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EstadoMesa{" + "\n" +
                "   jugadores=" + jugadores.toString() + "\n" +
                "   , pot=" + pot + "\n" +
                "   , mesa=" + (mesa != null ? mesa.toString() : "{}") + "\n" +
                '}';
    }
}
