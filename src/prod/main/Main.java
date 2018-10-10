package prod.main;

import java.util.Map;

import javax.swing.SwingUtilities;

import prod.controlador.Controlador;
import prod.modelo.util.LecturaEscritura;
import prod.vista.VentanaPrincipal;

public class Main {
	
	public static void main(String[] args) {
		
		Map<String, String[]> rankings = LecturaEscritura.leeRankings();
		Map<String, Float> rangos = LecturaEscritura.leeRangos();
		
		final Controlador cont = new Controlador(rankings, rangos);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new VentanaPrincipal(cont);
			}
		});
		
	}
	
}