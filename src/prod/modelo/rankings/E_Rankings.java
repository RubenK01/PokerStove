package prod.modelo.rankings;

public class E_Rankings {

    private final String [] lista;

    //-----------------
    // Constructor y metodos
    //-----------------

    private E_Rankings(String [] lista) {
        this.lista = lista;
    }

    //--------------------
    // Tablas
    //--------------------

    // https://books.google.es/books?id=g9iQu95JFm4C&pg=PA299&lpg=PA299&dq=ranking+de+Sklansky-Chubukov&source=bl&ots=VqLN4kUkZp&sig=
    // DtwTbESI9WPts113yMc1pDRJFnA&hl=es&sa=X&ved=0CHIQ6AEwDGoVChMI_7jkp76JyQIVQ7gaCh0_XwTX#v=onepage&q=ranking%20de%20Sklansky-Chubukov&f=false
    private static final String[] t_SKLANSKY_CHUBUKOV = new String [] {
            "AA", "KK", "AKs", "QQ", "AKo", "JJ", "AQs", "TT", "AQo", "99", "AJs", "88", "ATs", "AJo", "77", "66",
            "ATo", "A9s", "55", "A8s", "KQs", "44", "A9o", "A7s","KJs", "A5s", "A8o", "A6s", "A4s", "33","KTs","A7o",
            "A3s","22", "KQo","A2s", "A5o", "A6o", "A40","KJo", "QJs", "A3o", "K9s", "A2o", "KTo", "QTs", "K8s", "K7s",
            "JTs","K9o","K6s","QJo","Q9s","K5s","K8o","K4s","QTo","K7o","K3s","K2s","Q8s","K6o","J9s","K5o","Q9o","JTo",
            "K4o","Q7s","T9s","Q6s","K3o","J8s","Q5s","K2o","Q8o","Q4s","Q3s","J9o","T8s","Q7o","J7s","Q2s","Q6o","98s",
            "Q5o","T9o","J8o","J6s","T7s","J5s","Q4o","J4s","J7o","Q3o","97s","J3s","T8o","T6s","Q2o","J2s","87s","J6o",
            "98o","T7o","96s","J5o","T5s","T4s","86s","J4o","T3s","97o","T6o","95s","76s","J3o","T2s","87o","85s","96o",
            "J2o","T5o","T5o","94s","75s","65s","T4o","93s","86o","84s","95o","76o","T3o","92s","74s","T2o","54s","85o",
            "64s","83s","75o","94o","82s","73s","93o","65o","53s","84o","92o","63s","43s","72s","54o","74o","62s","52s",
            "64o","83o","82o","42s","73o","63o","53o","32s","43o","72o","62o","52o","42o","32o"
    };

    
    // http://webs.ono.com/antonio.carrasco/ranking_preflop.htm
    private static final String[] t_DESCONOCIDO = new String [] {
            "AA", "KK", "QQ", "AKs", "JJ", "AQs", "KQs", "AJs", "TT", "KJs", "AKo", "ATs", "QJs", "KTs", "QTs", "AQo", "JTs", "99", "KQo", "A9s", "AJo", "K9s", "A8s", "88", "T9s", "Q9s",
            "KJo", "A5s", "A7s", "QJo", "J9s", "ATo", "A4s", "A6s", "A3s", "KTo", "77", "JTo", "K8s", "QTo", "T8s", "J8s", "A2s", "Q8s", "98s", "K7s", "K6s", "66",
            "K5s", "87s", "97s", "K4s", "T7s", "J7s", "76s", "A9o", "K3s", "Q7s", "K2s", "T9o", "Q6s", "55", "K9o", "86s", "44", "Q5s", "65s", "J9o", "Q9o",
            "A8o", "75s", "Q4s", "96s", "54s", "J6s", "33", "T6s", "Q3s", "22", "J5s", "A7o", "Q2s", "A4o", "A5o", "85s", "J4s", "64s", "53s", "K8o", "J3s",
            "74s", "A6o", "95s", "T8o", "T5s", "J2s", "A3o", "98o", "J8o", "Q8o", "T4s", "43s", "A2o", "K7o", "T3s", "T2s", "84s", "63s", "52s", "94s", "K6o",
            "93s", "87o", "73s", "42s", "K5o", "97o", "T7o", "92s", "83s", "32s", "62s", "K4o", "76o", "Q7o", "J7o", "82s", "K3o", "86o", "65o", "Q6o", "72s", "K2o",
            "54o", "75o", "Q5o", "96o", "T6o", "Q4o", "Q3o", "J6o", "85o", "64o", "Q2o", "J5o", "J4o", "53o", "74o", "95o", "J3o", "43o", "T5o", "J2o", "T4o", "63o", "84o", "52o",
            "T2o", "T3o", "94o", "73o", "42o", "93o", "32o", "92o", "83o", "62o", "82o", "72o"
    };
    

    //-----------------
    // Valores del Enum
    //-----------------

    public static final E_Rankings SKLANSKY_CHUBUKOV = new E_Rankings(t_SKLANSKY_CHUBUKOV);
    public static final E_Rankings DESCONOCIDO = new E_Rankings(t_DESCONOCIDO);

    public String at(int cont) {
        return lista[cont];
    }
}
