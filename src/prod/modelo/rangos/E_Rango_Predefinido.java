package prod.modelo.rangos;

public class E_Rango_Predefinido {

   private final float porcentaje;

    //-----------------
    // Constructor y metodos
    //-----------------

    private E_Rango_Predefinido(float porcentaje) {
       this.porcentaje = porcentaje;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public static E_Rango_Predefinido stringARango(String rangoSeleccionado, String posicionSeleccionada) {

        if (rangoSeleccionado == "Ma" && posicionSeleccionada == "UTG")
         return E_Rango_Predefinido.T_Ma_OR_UTG;
        else if (rangoSeleccionado == "Ma" && posicionSeleccionada == "MP")
         return E_Rango_Predefinido.T_Ma_OR_MP;
        else if (rangoSeleccionado == "Ma" && posicionSeleccionada == "CO")
         return E_Rango_Predefinido.T_Ma_OR_CO;
        else if (rangoSeleccionado == "Ma" && posicionSeleccionada == "BTN")
         return E_Rango_Predefinido.T_Ma_OR_BTN;
        else if (rangoSeleccionado == "Ma" && posicionSeleccionada == "SB")
         return E_Rango_Predefinido.T_Ma_OR_SB;

        else if (rangoSeleccionado == "Janda" && posicionSeleccionada == "UTG")
         return E_Rango_Predefinido.T_Janda_OR_UTG;
        else if (rangoSeleccionado == "Janda" && posicionSeleccionada == "MP")
         return E_Rango_Predefinido.T_Janda_OR_MP;
        else if (rangoSeleccionado == "Janda" && posicionSeleccionada == "CO")
         return E_Rango_Predefinido.T_Janda_OR_CO;
        else if (rangoSeleccionado == "Janda" && posicionSeleccionada == "BTN")
         return E_Rango_Predefinido.T_Janda_OR_BTN;
        else if (rangoSeleccionado == "Janda" && posicionSeleccionada == "SB")
         return E_Rango_Predefinido.T_Janda_OR_SB;
        else {
         return E_Rango_Predefinido.Nada;
        }
    }

    //-----------------
    // Valores del Enum
    //-----------------
    
    public static final E_Rango_Predefinido T_Ma_ROL_UTG = new E_Rango_Predefinido(17.5f);
    public static final E_Rango_Predefinido T_Ma_ROL_MP = new E_Rango_Predefinido(21.0f);
    public static final E_Rango_Predefinido T_Ma_ROL_CO = new E_Rango_Predefinido(30.2f);
    public static final E_Rango_Predefinido T_Ma_ROL_BTN = new E_Rango_Predefinido(55.8f);
    public static final E_Rango_Predefinido T_Ma_ROL_SB = new E_Rango_Predefinido(55.8f);

    public static final E_Rango_Predefinido T_Ma_OR_UTG = new E_Rango_Predefinido(17.5f);
    public static final E_Rango_Predefinido T_Ma_OR_MP = new E_Rango_Predefinido(21.0f);
    public static final E_Rango_Predefinido T_Ma_OR_CO = new E_Rango_Predefinido(30.2f);
    public static final E_Rango_Predefinido T_Ma_OR_BTN = new E_Rango_Predefinido(55.8f);
    public static final E_Rango_Predefinido T_Ma_OR_SB = new E_Rango_Predefinido(55.8f);
    
    public static final E_Rango_Predefinido T_Janda_OR_UTG = new E_Rango_Predefinido(13.9f);
    public static final E_Rango_Predefinido T_Janda_OR_MP = new E_Rango_Predefinido(17.9f);
    public static final E_Rango_Predefinido T_Janda_OR_CO = new E_Rango_Predefinido(23.7f);
    public static final E_Rango_Predefinido T_Janda_OR_BTN = new E_Rango_Predefinido(47.5f);
    public static final E_Rango_Predefinido T_Janda_OR_SB = new E_Rango_Predefinido(36.3f);
    public static final E_Rango_Predefinido Nada = new E_Rango_Predefinido(0.0f);
}
