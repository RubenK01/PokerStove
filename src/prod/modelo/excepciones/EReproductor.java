package prod.modelo.excepciones;

@SuppressWarnings("serial")
public class EReproductor extends RuntimeException {

    public EReproductor(String message) {
        super(message);
    }
}
