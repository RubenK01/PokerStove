package prod.modelo.rankings;

public class Ranking {

    private String [] lista;

    public Ranking(String [] lista) {
        this.lista = lista;
    }
    
    public String at(int cont) {
        return lista[cont];
    }

}
