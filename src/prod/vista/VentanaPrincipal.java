package prod.vista;

import prod.controlador.Controlador;
import prod.controlador.ResultadoSimulacion;
import prod.modelo.rangos.RangoSoloLectura;
import prod.modelo.reproductor.EstadoMesa;
import prod.modelo.util.LecturaEscritura;
import prod.observadores.I_Observador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame implements I_Observador{
    
    private Controlador controlador;
    private JTextArea output;
    private JTextArea textAreaAyuda;
    
    public VentanaPrincipal(Controlador controlador) {
    	 super("Practica 4 HJA");
    	 
    	 this.controlador = controlador;
    	 this.controlador.addObservador(this);
    	 initComponentes();
    	 
    	 this.setPreferredSize(new Dimension(650, 680));
	     this.pack();
	     this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	     this.setResizable(false);
	     this.setLocationRelativeTo(null);
	     this.setVisible(true);
    }

	private void initComponentes() {
		
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		JTabbedPane pestanasPanelPrincipal = new JTabbedPane();
		
		JPanel panelHoldem = new JPanel(new BorderLayout());
		PanelJugadoresHoldem panelJugHoldem = new PanelJugadoresHoldem(controlador);
		PanelDerecho panelDerHoldem = new PanelDerecho(controlador, 'H');
		panelHoldem.add(panelJugHoldem, BorderLayout.CENTER);
		panelHoldem.add(panelDerHoldem, BorderLayout.EAST);
		pestanasPanelPrincipal.add("Hold'em", panelHoldem);
		
		JPanel panelOmaha = new JPanel(new BorderLayout());
		PanelJugadoresOmaha panelJugOmaha = new PanelJugadoresOmaha(controlador);
		PanelDerecho panelDerOmaha = new PanelDerecho(controlador, 'O');
		panelOmaha.add(panelJugOmaha, BorderLayout.CENTER);
		panelOmaha.add(panelDerOmaha, BorderLayout.EAST);
		pestanasPanelPrincipal.add("Omaha", panelOmaha);
		
		PanelApartadoOpcional panelOpcional = new PanelApartadoOpcional(controlador);
		pestanasPanelPrincipal.add("Crear Rankings y Rangos", panelOpcional);
		
		PanelReproductor panelReproductor = new PanelReproductor(controlador);
		pestanasPanelPrincipal.add("Analyser", panelReproductor);
		
		textAreaAyuda = new JTextArea();
		JScrollPane scrollAyuda = new JScrollPane(textAreaAyuda);
		scrollAyuda.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textAreaAyuda.setEditable(false);
		textAreaAyuda.setText(LecturaEscritura.leeAyuda());
		pestanasPanelPrincipal.add("Ayuda", scrollAyuda);
		
		panelPrincipal.add(pestanasPanelPrincipal);
		
		output = new JTextArea();
		
		JScrollPane scrollOutput = new JScrollPane(output);
		scrollOutput.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		//output.setPreferredSize(new Dimension(600, 280));
		output.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		output.setEditable(false);
		
		
		//scrollOutput.add(output);
		scrollOutput.setPreferredSize(new Dimension(900, 280));
		//JPanel panelOutput = new JPanel();
		scrollOutput.setBorder(new TitledBorder("Output"));
		
		//panelOutput.add(output);
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		this.getContentPane().add(scrollOutput, BorderLayout.SOUTH);
		
	}

	
	@Override
	public void onRangoModificado(RangoSoloLectura r, int jugadorActual) {}

	@Override
	public void onStringRangoModificado(String entero, int jugadorActual) {}

	@Override
	public void onAttach(RangoSoloLectura r, int jugadorActual) {}
	
	@Override
	public void onActualizaOutput(char juego, String[] rangos, String board, String dead, double tiempo, ResultadoSimulacion resultado) {
		String text = "";
		double[] resultados = resultado.getResultados();
		int[] ganadas = resultado.getGanadas();
		int[] empatadas = resultado.getEmpates();
		
		int i = 0;
		
		text += " " + resultado.getEjecuciones() + " games " + tiempo + "  secs  " ;
		text += Double.toString((double)resultado.getEjecuciones()/tiempo).substring(0, 4) + " games/sec";
		text += "\n\n";
		
		text += " Board: {" + board + "}\n";
		text += " Dead: {" + dead +"}\n";
		text += "\n";

		text += "                equity       win            tie                 pots won     pots tied\n";
		
		while (resultados[i] > 0.0) {
			String eq = Double.toString(resultados[i]);
			String win = Double.toString(resultados[i+1]);
			String tie = Double.toString(resultados[i+2]);
			
			text += " Hand " + (i+1)/3 + ": " + eq.substring(0,Integer.min(6, eq.length())) + "%  ";
			text += win.substring(0,Integer.min(6, win.length())) + "%  ";
			text += tie.substring(0,Integer.min(6, tie.length())) + "%       ";
			text += Integer.toString(ganadas[i/3]) + "        ";
			text += Integer.toString(empatadas[i/3]) + "   ";
			text += "{"+(!rangos[i/3].equals("22+,A2s+,A2o+,K2s+,K2o+,Q2s+,Q2o+,J2s+,J2o+,T2s+,T2o+,92s+,92o+,82s+,82o+,72s+,72o+,62s+,62o+,52s+,52o+") ? rangos[i/3] : "random")+"}";			
			text += "\n";
			i = i + 3;
			
		}
		
		output.setText(text + "\n" + " ==================================="
				+ "\n\n" + output.getText());				
	}

	@Override
	public void onCartasJugOmahaModificadas(String cartasDeJugadoresOmaha, int jugadorActual) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBoardModificado(String stringCartas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeadCardsModificadas(String stringCartas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClearAll() {
		output.setText("");
	}

	@Override
	public void onRangoInsertado() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRankingInsertado(String nuevoRanking) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEstadoMesaCambiado(EstadoMesa nuevoEstado, String nombreAccion) {

	}

}