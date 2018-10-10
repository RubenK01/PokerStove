/**
 * Enum (implementado como clase) que tiene los diferentes tipos de jugadas.
 */

package prod.modelo.analizador;

public class E_Jugada_Tipo {

    private final String nombre;
    private final int ranking;

    private E_Jugada_Tipo(String nombre, int ranking) {
        this.nombre = nombre;
        this.ranking = ranking;
    }
    
    public int getRanking() {
    	return ranking;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    public static final E_Jugada_Tipo STRAIGHT_FLUSH = new E_Jugada_Tipo("Straight Flush", 9);
    public static final E_Jugada_Tipo FOUR_OF_A_KIND = new E_Jugada_Tipo("Four of a Kind", 8);
    public static final E_Jugada_Tipo FULL_HOUSE = new E_Jugada_Tipo("Full House", 7);
    public static final E_Jugada_Tipo FLUSH = new E_Jugada_Tipo("Flush", 6);
    public static final E_Jugada_Tipo STRAIGHT = new E_Jugada_Tipo("Straight", 5);
    public static final E_Jugada_Tipo THREE_OF_A_KIND = new E_Jugada_Tipo("Three of a Kind", 4);
    public static final E_Jugada_Tipo TWO_PAIR = new E_Jugada_Tipo("Two Pairs", 3);
    public static final E_Jugada_Tipo PAIR = new E_Jugada_Tipo("Pair", 2);
    public static final E_Jugada_Tipo HIGH_CARD = new E_Jugada_Tipo("High Card", 1);
    public static final E_Jugada_Tipo NADA = new E_Jugada_Tipo("NADA", 0);
    
    
    //public static final E_Jugada_Tipo OESD = new E_Jugada_Tipo("Open-ended Straight Draw", 0);
    //public static final E_Jugada_Tipo GUTSHOT = new E_Jugada_Tipo("Straight Gut-shot Draw", 0);
    //public static final E_Jugada_Tipo FLUSH_DRAW = new E_Jugada_Tipo("Flush Draw", 0);
}
