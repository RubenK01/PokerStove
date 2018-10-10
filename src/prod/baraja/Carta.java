package prod.baraja;

import prod.modelo.util.Utilidades;

/**
 * Clase para representar una carta individual.
 * Contiene información sobre el palo y el valor de la carta.
 * E.G: La carta Ah se inicializaría con
 *      new Carta(E_Carta_Palo.HEARTS, E_Carta_Valor.A)
 *
 */

public class Carta implements Comparable<Carta> {

    private E_Carta_Valor valor;
    private E_Carta_Palo palo;

    public Carta(E_Carta_Valor valor, E_Carta_Palo palo) {
        this.valor = valor;
        this.palo = palo;
    }

    /**
     *
     * @param carta (Ah, Ac...)
     */
    public Carta(String carta) {
        this.valor = E_Carta_Valor.getValorPorInt(Utilidades.char2intCarta(carta.charAt(0)));
        this.palo = E_Carta_Palo.getPaloPorInt(Utilidades.charPalo2intCarta(carta.charAt(1)));
    }

    public E_Carta_Valor getValor() {
        return valor;
    }

    public E_Carta_Palo getPalo() {
        return palo;
    }
    
    public String toString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append(this.valor);
    	builder.append(this.palo);
		return builder.toString();
    }

    /**
     *
     * @return un int que representa a la carta:
     *   return     | carta
     * --------------------------
     *      0       | Ac
     *      1       | Kc
     *    ...       | ...
     *     12       | 2c
     *     13       | Ah
     *     14       | Kh
     *    ...       | ...
     *     51       | 2s
     */
    public int toInt() {
        return (13*palo.getIdnum() + (14-valor.getValor()));
    }

    @Override
    public boolean equals(Object obj) {
        Carta c = (Carta) obj;
        return (c.palo == this.palo && c.valor == this.valor);
    }

    @Override
    public int compareTo(Carta o) {
        if (this.getValor().getValor() > o.getValor().getValor())
            return -1;
        else if (this.getValor().getValor() < o.getValor().getValor())
            return 1;
        else
            return 0;
    }
}
