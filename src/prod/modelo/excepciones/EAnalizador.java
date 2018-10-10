package prod.modelo.excepciones;

@SuppressWarnings("serial")
public class EAnalizador extends RuntimeException {

    public EAnalizador(String msg) {
        super(msg);
    }
}
