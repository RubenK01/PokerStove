package prod.baraja;

/**
 * Enum para representar los valores de las cartas y ayudar con el parseo.
 * Tiene dos campos:
 *  @param valor: Valor numérico que se le asigna a cada carta.
 *  @param nombre: Nombre textual de cada carta, para ayudar en el parseo.
 */


public class E_Carta_Valor {

    private final int valor;
    private final String nombre;

    E_Carta_Valor(int valor, String nombre) {
        this.valor = valor;
        this.nombre = nombre;
    }

    public int getValor() {
        return this.valor;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    public static final E_Carta_Valor A     = new E_Carta_Valor(14, "A");
    public static final E_Carta_Valor K     = new E_Carta_Valor(13, "K");
    public static final E_Carta_Valor Q     = new E_Carta_Valor(12, "Q");
    public static final E_Carta_Valor J     = new E_Carta_Valor(11, "J");
    public static final E_Carta_Valor T     = new E_Carta_Valor(10, "T");
    public static final E_Carta_Valor NUEVE = new E_Carta_Valor(9, "9");
    public static final E_Carta_Valor OCHO  = new E_Carta_Valor(8, "8");
    public static final E_Carta_Valor SIETE = new E_Carta_Valor(7, "7");
    public static final E_Carta_Valor SEIS  = new E_Carta_Valor(6, "6");
    public static final E_Carta_Valor CINCO = new E_Carta_Valor(5, "5");
    public static final E_Carta_Valor CUATRO= new E_Carta_Valor(4, "4");
    public static final E_Carta_Valor TRES  = new E_Carta_Valor(3, "3");
    public static final E_Carta_Valor DOS   = new E_Carta_Valor(2, "2");

    public static E_Carta_Valor getValorPorInt(int i) {
        switch (i) {
            case 14:
                return E_Carta_Valor.A;
            case 13:
                return E_Carta_Valor.K;
            case 12:
                return E_Carta_Valor.Q;
            case 11:
                return E_Carta_Valor.J;
            case 10:
                return E_Carta_Valor.T;
            case 9:
                return E_Carta_Valor.NUEVE;
            case 8:
                return E_Carta_Valor.OCHO;
            case 7:
                return E_Carta_Valor.SIETE;
            case 6:
                return E_Carta_Valor.SEIS;
            case 5:
                return E_Carta_Valor.CINCO;
            case 4:
                return E_Carta_Valor.CUATRO;
            case 3:
                return E_Carta_Valor.TRES;
            case 2:
                return E_Carta_Valor.DOS;
            default:
                return E_Carta_Valor.A;
        }
    }

}
