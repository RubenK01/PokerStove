package prod.modelo.rangos;

import prod.modelo.excepciones.ERangoPredefinido;

public class RangoPredefinido extends Rango {

    @SuppressWarnings("unused")
	private E_Rango_Predefinido rangoTipo;

    public RangoPredefinido(E_Rango_Predefinido rango) {
    	super();
        this.rangoTipo = rango;
    }

    @Override
    public void marcarMano(int x, int y) {
        throw new ERangoPredefinido();
    }

    @Override
    public void desmarcarMano(int x, int y) {
        throw new ERangoPredefinido();
    }


    @Override
    public void cambiarMano(int x, int y) {
        
    }

	@Override
	public void marcarRango(String rango) {
		throw new ERangoPredefinido();
	}
}
