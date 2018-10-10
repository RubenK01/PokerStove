package prod.observadores;

public interface I_Observable{

    void addObservador(I_Observador observador);
    void removeObservador(I_Observador observador);
}
