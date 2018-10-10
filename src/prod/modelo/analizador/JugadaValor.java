package prod.modelo.analizador;

import prod.baraja.Carta;

import java.util.List;

public class JugadaValor implements I_Jugada {

	private E_Jugada_Tipo tipo;
	private List<Carta> cartasImportantes = null;
	private List<Carta> mano = null;
	
	//Draws
	private boolean flushDraw;
	private boolean OESD;
	private boolean gutShot;
	private boolean straightDraw;
	
	public JugadaValor(E_Jugada_Tipo tipo, List<Carta> cartasImportantes, List<Carta> mano) {
		this.tipo = tipo;
		this.cartasImportantes = cartasImportantes;
		this.mano = mano;
	}

	public JugadaValor(E_Jugada_Tipo tipo) {
		this.tipo = tipo;
	}

	public List<Carta> getCartasImportantes() {
		return cartasImportantes;
	}
	
	public List<Carta> getMano() {
		return mano;
	}

	public E_Jugada_Tipo getTipo() {
		return tipo;
	}

	public void setFlushDraw(boolean hayFlushDraw) {
		this.flushDraw = hayFlushDraw;
	}

	public void setGutShot(boolean hayGutShot) {
		this.gutShot = hayGutShot;
	}

	public void setOESD(boolean hayOESD) {
		this.OESD = hayOESD;
	}
	
	public void setStraightDraw(boolean hayStraightDraw) {
		this.straightDraw = hayStraightDraw;
	}
	public boolean getFlushDraw() {
		return this.flushDraw;
	}
	
	public boolean getGutShot() {
		return this.gutShot;
	}
	
	public boolean getOESD() {
		return this.OESD;
	}	
	
	public boolean getStraightDraw() {
		return this.straightDraw;
	}
	
	public String toString() {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(tipo.toString());
		
		if (cartasImportantes != null) {
			strBuild.append(" (");
			for (int i = 0; i < cartasImportantes.size(); i++) {
				strBuild.append(cartasImportantes.get(i).getValor().toString());				
				
			}
			strBuild.append(") ");			
		}
		if (mano != null) {
			strBuild.append("with ");
			strBuild.append(mano.toString());
		}
		return strBuild.toString();
	}
	
	public String toStringWithDraws() {
		/*StringBuilder strBuild = new StringBuilder();
		strBuild.append(" - ");
		String bestHand = this.toString();
		bestHand += "\n";
		strBuild.append(bestHand);
		
		if (flushDraw)
			strBuild.append(" - Flush Draw\n");
		if (gutShot)
			strBuild.append(" - Straight Gut-shot Draw\n");
		if (OESD)
			strBuild.append(" - Open-ended Straight Draw\n");
		*/
		String cad = " - " + this.toString() + "\n";
		if (flushDraw)
			cad += " - Flush Draw\n";
		if (gutShot)
			cad += " - Straight Gut-shot Draw\n";
		if (OESD)
			cad += " - Open-ended Straight Draw\n";
		
		return cad;
	}

	

}
