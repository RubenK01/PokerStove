package prod.modelo.reproductor;

import prod.baraja.Carta;

import java.util.List;

public class EstadoJugador {

	private String nombre;
    private List<Carta> cartas;
    private double stack;
    private double bet;
    private boolean hasFolded;
    private boolean isButton;

    public EstadoJugador(String nombre, List<Carta> cartas, double stack, double bet, boolean hasFolded, boolean isButton) {
    	this.nombre = nombre;
        this.cartas = cartas;
        this.stack = stack;
        this.bet = bet;
        this.hasFolded = hasFolded;
        this.isButton = isButton;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public double getStack() {
        return stack;
    }

    public double getBet() {
        return bet;
    }

    public boolean hasFolded() {
        return hasFolded;
    }

	public String getNombre() {
		return nombre;
	}

	public boolean isButton() {
		return isButton;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstadoJugador that = (EstadoJugador) o;

        if (Double.compare(that.stack, stack) != 0) return false;
        if (Double.compare(that.bet, bet) != 0) return false;
        if (hasFolded != that.hasFolded) return false;
        if (isButton != that.isButton) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        return !(cartas != null ? !cartas.equals(that.cartas) : that.cartas != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = nombre != null ? nombre.hashCode() : 0;
        result = 31 * result + (cartas != null ? cartas.hashCode() : 0);
        temp = Double.doubleToLongBits(stack);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(bet);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (hasFolded ? 1 : 0);
        result = 31 * result + (isButton ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EstadoJugador{" + "\n" +
                "   nombre='" + nombre + '\'' + "\n" +
                "   , cartas=" + (cartas != null ? cartas.toString() : "{}") + "\n" +
                "   , stack=" + stack + "\n" +
                "   , bet=" + bet + "\n" +
                "   , hasFolded=" + hasFolded + "\n" +
                "   , isButton=" + isButton + "\n" +
                '}';
    }
}
