package prod.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import prod.baraja.Carta;
import prod.controlador.Controlador;
import prod.controlador.ResultadoSimulacion;
import prod.modelo.rangos.RangoSoloLectura;
import prod.modelo.reproductor.EstadoJugador;
import prod.modelo.reproductor.EstadoMesa;
import prod.observadores.I_Observador;

@SuppressWarnings("serial")
public class PanelDerechoReproductor extends JPanel implements I_Observador {

	// Atributos
	private Image fondo;
	private JTextArea textoResultado;
	
	private JLabel cartaMesa1;
	private JLabel cartaMesa2;
	private JLabel cartaMesa3;
	private JLabel cartaMesa4;
	private JLabel cartaMesa5;
	
	private JLabel carta1Jug1;
	private JLabel carta2Jug1;
	private JLabel carta1Jug2;
	private JLabel carta2Jug2;
	private JLabel carta1Jug3;
	private JLabel carta2Jug3;
	private JLabel carta1Jug4;
	private JLabel carta2Jug4;
	private JLabel carta1Jug5;
	private JLabel carta2Jug5;
	private JLabel carta1Jug6;
	private JLabel carta2Jug6;
	private JLabel carta1Jug7;
	private JLabel carta2Jug7;
	private JLabel carta1Jug8;
	private JLabel carta2Jug8;
	private JLabel carta1Jug9;
	private JLabel carta2Jug9;
	
	private int anchoCartaMesa = 75;
	private int altoCartaMesa = 110;
	private int anchoCartaJug = 60;
	private int altoCartaJug = 90;
	
	
    // Constructor
    public PanelDerechoReproductor(Controlador controlador) {
    	
    	controlador.addObservador(this);
    	
    	this.setPreferredSize(new Dimension(1000, 600));
    	setLayout(null);
    	this.setBackground(Color.BLACK);
    	fondo = new ImageIcon(getClass().getResource("/imagenes/mesa2.jpg")).getImage();
    	textoResultado = new JTextArea("");
    	textoResultado.setEditable(false);
    	textoResultado.setBackground(Color.lightGray);
    	textoResultado.setFont(new java.awt.Font("Tahoma", 0, 13)); 
    	
    	
        // PRUEBAS
    	
    	
        Carta carta1 = new Carta("Ah");
        Carta carta2 = new Carta("Qh");
        Carta carta3 = new Carta("Tc");
        Carta carta4 = new Carta("3h");
        Carta carta5 = new Carta("5h");
        
        List<Carta> mesa = new ArrayList<Carta>();
        mesa.add(carta1);
        mesa.add(carta2);
        mesa.add(carta3);
        mesa.add(carta4);
        mesa.add(carta5);
        
        List<EstadoJugador> jugadores = new ArrayList<EstadoJugador>();
        List<Carta> cartasJug1 = new ArrayList<Carta>();
        List<Carta> cartasJug2 = new ArrayList<Carta>();
        cartasJug1.add(carta1);
        cartasJug1.add(carta2);
        cartasJug2.add(carta3);
        cartasJug2.add(carta4);
        EstadoJugador jug1 = new EstadoJugador("Pepe", cartasJug1, 1000, 150, false, true);
        EstadoJugador jug2 = new EstadoJugador("Juan", cartasJug2, 2000, 300, false, true);
        EstadoJugador jug3 = new EstadoJugador("Pepe", cartasJug1, 1000, 150, false, true);
        EstadoJugador jug4 = new EstadoJugador("Juan", cartasJug2, 2000, 300, false, true);
        EstadoJugador jug5 = new EstadoJugador("Pepe", cartasJug1, 1000, 150, false, true);
        EstadoJugador jug6 = new EstadoJugador("Juan", cartasJug2, 2000, 300, false, true);
        jugadores.add(jug1);
        jugadores.add(jug2);
        jugadores.add(jug3);
        jugadores.add(jug4);
        jugadores.add(jug5);
        jugadores.add(jug6);
        
        EstadoMesa estadoMesa = new EstadoMesa(jugadores, 500, mesa);
        onEstadoMesaCambiado(estadoMesa, "Juan: reraises €150 to €300");
        
    }
    
    private JLabel creaJLabelConImagen(String nombreCarta, int x, int y, int anchoCarta, int altoCarta) {
		JLabel lbl = new JLabel();
		lbl.setBounds(x, y, anchoCarta, altoCarta);
		String ruta = "/imagenes/Cartas/" + nombreCarta + ".png";
		ImageIcon image1 = new ImageIcon(PanelDerecho.class.getResource(ruta));
		ImageIcon image2 = new ImageIcon(image1.getImage().getScaledInstance(anchoCarta, altoCarta, Image.SCALE_DEFAULT));
		lbl.setIcon(image2);
    	return lbl;
    }
    
    public void paint(Graphics g) {
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
        setOpaque(false);
        super.paint(g);
    }

	@Override
	public void onEstadoMesaCambiado(EstadoMesa nuevoEstado, String nombreAccion) {
		
    	removeAll();
		repaint();
		
		// Actualizar JTextArea con la nueva accion
		JScrollPane scrollPane = new JScrollPane(textoResultado);
    	scrollPane.setBounds(330, 30, 350, 175);
    	textoResultado.append("\n " + nombreAccion.replaceAll("â‚¬", "€"));
        add(scrollPane);
        
        // Bote
        JLabel labelPot = new JLabel(" Pot: €" + new BigDecimal(nuevoEstado.getPot()).setScale(2, BigDecimal.ROUND_HALF_UP) + " ");
        labelPot.setOpaque(true);
        labelPot.setBackground(Color.CYAN);
        labelPot.setBorder(BorderFactory.createLineBorder(Color.black));
        labelPot.setBounds(457, 220, 100, 30);
        add(labelPot);
        
    	// Cartas del board
        if (nuevoEstado.getMesa() != null) {
        	List<Carta> mesa = nuevoEstado.getMesa();
        	int cartasMesa = mesa.size();
        	
	    	if (cartasMesa > 0){
		    	cartaMesa1 = creaJLabelConImagen(mesa.get(0).toString(), 270, 270, anchoCartaMesa, altoCartaMesa);
		    	cartaMesa2 = creaJLabelConImagen(mesa.get(1).toString(), 370, 270, anchoCartaMesa, altoCartaMesa);
		    	cartaMesa3 = creaJLabelConImagen(mesa.get(2).toString(), 470, 270, anchoCartaMesa, altoCartaMesa);
		    	add(cartaMesa1);
		    	add(cartaMesa2);
		    	add(cartaMesa3);
	    	}
	    	if (cartasMesa > 3) {
	    		cartaMesa4 = creaJLabelConImagen(mesa.get(3).toString(), 570, 270, anchoCartaMesa, altoCartaMesa);
	    		add(cartaMesa4);
	    	}
	    	if (cartasMesa > 4) {
	    		cartaMesa5 = creaJLabelConImagen(mesa.get(4).toString(), 670, 270, anchoCartaMesa, altoCartaMesa);
	    		add(cartaMesa5);
	    	}
        }
    	
        // Jugadores
        List<EstadoJugador> listaJugadores = nuevoEstado.getJugadores();
		int numJugadores = listaJugadores.size();
    	EstadoJugador j;
    	if (numJugadores > 0) {
    		
    		j = listaJugadores.get(0);
    		
    		// Nombre del jugador y Stack
    		JLabel label = new JLabel(" " + j.getNombre() + " [€" + new BigDecimal(j.getStack()).setScale(2, BigDecimal.ROUND_HALF_UP) + "] ");
    		label.setOpaque(true);
    		label.setBackground(Color.lightGray);
    		label.setBorder(BorderFactory.createLineBorder(Color.black));
    		label.setBounds(720, 28, 122, 30);
            add(label);
            
            // Ficha de dealer
            if (j.isButton()) {
            	JLabel labelDealer = new JLabel(" D ");
            	labelDealer.setFont(new java.awt.Font("Tahoma", 0, 20)); 
            	labelDealer.setOpaque(true);
            	labelDealer.setBackground(Color.PINK);
            	labelDealer.setBorder(BorderFactory.createLineBorder(Color.black));
            	labelDealer.setBounds(790, 165, 30, 30);
                add(labelDealer);
            }
	  	    
	  	    // Apuesta
    		if (j.getBet() > 0) {
    			JLabel labelApuesta = new JLabel(" €" + new BigDecimal(j.getBet()).setScale(2, BigDecimal.ROUND_HALF_UP) + " ");
    			labelApuesta.setOpaque(true);
    			labelApuesta.setBackground(Color.orange);
    			labelApuesta.setBorder(BorderFactory.createLineBorder(Color.black));
    			labelApuesta.setBounds(715, 185, 60, 30);
                add(labelApuesta);
    		}
           
    		if (!j.hasFolded()) {
    			
    			// Cartas
    			if (j.getCartas() != null) {
		  	      	carta1Jug1 = creaJLabelConImagen(j.getCartas().get(0).toString(), 720, 65, anchoCartaJug, altoCartaJug);
		  	      	carta2Jug1 = creaJLabelConImagen(j.getCartas().get(1).toString(), 720+this.anchoCartaJug+2, 65, anchoCartaJug, altoCartaJug);
    			}
	    		else {
	    			carta1Jug1 = creaJLabelConImagen("black_joker", 720, 65, anchoCartaJug, altoCartaJug);
	    			carta2Jug1 = creaJLabelConImagen("black_joker", 720+this.anchoCartaJug+2, 65, anchoCartaJug, altoCartaJug);
	    		}
	    		add(carta1Jug1);
		  	    add(carta2Jug1);
    		}
            
    		
    	}
    	if (numJugadores > 1) {
    		j = listaJugadores.get(1);
    		
    		// Nombre del jugador y Stack
    		JLabel label = new JLabel(" " + j.getNombre() + " [€" + new BigDecimal(j.getStack()).setScale(2, BigDecimal.ROUND_HALF_UP) + "] ");
    		label.setOpaque(true);
    		label.setBackground(Color.lightGray);
    		label.setBorder(BorderFactory.createLineBorder(Color.black));
    		label.setBounds(850, 178, 122, 30);
            add(label);
            
            // Ficha de dealer
            if (j.isButton()) {
            	JLabel labelDealer = new JLabel(" D ");
            	labelDealer.setFont(new java.awt.Font("Tahoma", 0, 20)); 
            	labelDealer.setOpaque(true);
            	labelDealer.setBackground(Color.PINK);
            	labelDealer.setBorder(BorderFactory.createLineBorder(Color.black));
            	labelDealer.setBounds(810, 220, 30, 30);
                add(labelDealer);
            }
            
            // Apuesta
    		if (j.getBet() > 0) {
    			JLabel labelApuesta = new JLabel(" €" + new BigDecimal(j.getBet()).setScale(2, BigDecimal.ROUND_HALF_UP) + " ");
    			labelApuesta.setOpaque(true);
    			labelApuesta.setBackground(Color.orange);
    			labelApuesta.setBorder(BorderFactory.createLineBorder(Color.black));
    			labelApuesta.setBounds(775, 255, 60, 30);
                add(labelApuesta);
    		}
            
    		if (!j.hasFolded()) {
    			
    			// Cartas
    			if (j.getCartas() != null) {
		    	    carta1Jug2 = creaJLabelConImagen(j.getCartas().get(0).toString(), 850, 215, anchoCartaJug, altoCartaJug);
		    	    carta2Jug2 = creaJLabelConImagen(j.getCartas().get(1).toString(), 850+this.anchoCartaJug+2,215, anchoCartaJug, altoCartaJug);
    			}
    			else {
    				carta1Jug2 = creaJLabelConImagen("black_joker", 850, 215, anchoCartaJug, altoCartaJug);
		    	    carta2Jug2 = creaJLabelConImagen("black_joker", 850+this.anchoCartaJug+2,215, anchoCartaJug, altoCartaJug);
    			}
    			add(carta1Jug2);
		    	add(carta2Jug2);
    		}
    	}
    	if (numJugadores > 2) {
    		j = listaJugadores.get(2);
    		
    		// Nombre del jugador y Stack
    		JLabel label = new JLabel(" " + j.getNombre() + " [€" + new BigDecimal(j.getStack()).setScale(2, BigDecimal.ROUND_HALF_UP) + "] ");
    		label.setOpaque(true);
    		label.setBackground(Color.lightGray);
    		label.setBorder(BorderFactory.createLineBorder(Color.black));
    		label.setBounds(850, 338, 122, 30);
            add(label);
            
            // Ficha de dealer
            if (j.isButton()) {
            	JLabel labelDealer = new JLabel(" D ");
            	labelDealer.setFont(new java.awt.Font("Tahoma", 0, 20)); 
            	labelDealer.setOpaque(true);
            	labelDealer.setBackground(Color.PINK);
            	labelDealer.setBorder(BorderFactory.createLineBorder(Color.black));
            	labelDealer.setBounds(810, 425, 30, 30);
                add(labelDealer);
            }
            
            // Apuesta
    		if (j.getBet() > 0) {
    			JLabel labelApuesta = new JLabel(" €" + new BigDecimal(j.getBet()).setScale(2, BigDecimal.ROUND_HALF_UP) + " ");
    			labelApuesta.setOpaque(true);
    			labelApuesta.setBackground(Color.orange);
    			labelApuesta.setBorder(BorderFactory.createLineBorder(Color.black));
    			labelApuesta.setBounds(775, 385, 60, 30);
                add(labelApuesta);
    		}
            
    		if (!j.hasFolded()) {
    			
    			// Cartas
    			if (j.getCartas() != null) {
		            carta1Jug3= creaJLabelConImagen(j.getCartas().get(0).toString(), 850, 375, anchoCartaJug, altoCartaJug);
		            carta2Jug3 = creaJLabelConImagen(j.getCartas().get(1).toString(), 850+this.anchoCartaJug+2,375, anchoCartaJug, altoCartaJug);
    			}
    			else {
    				carta1Jug3= creaJLabelConImagen("black_joker", 850, 375, anchoCartaJug, altoCartaJug);
		            carta2Jug3 = creaJLabelConImagen("black_joker", 850+this.anchoCartaJug+2,375, anchoCartaJug, altoCartaJug);
    			}
		        add(carta1Jug3);
		        add(carta2Jug3);
    		}
    	}
    	if (numJugadores > 3) {
    		j = listaJugadores.get(3);
    		if (!j.hasFolded()) {
    			if (j.getCartas() != null) {
		            carta1Jug4= creaJLabelConImagen(j.getCartas().get(0).toString(), 720, 500, anchoCartaJug, altoCartaJug);
		            carta2Jug4 = creaJLabelConImagen(j.getCartas().get(1).toString(), 720+this.anchoCartaJug+2,500, anchoCartaJug, altoCartaJug);
    			}
    			else {
    				carta1Jug4= creaJLabelConImagen("black_joker", 720, 500, anchoCartaJug, altoCartaJug);
		            carta2Jug4 = creaJLabelConImagen("black_joker", 720+this.anchoCartaJug+2,500, anchoCartaJug, altoCartaJug);
		        }
	            add(carta1Jug4);
	            add(carta2Jug4);
    		}
    	}
    	if (numJugadores > 4) {
    		j = listaJugadores.get(4);
    		if (!j.hasFolded()) {
    			if (j.getCartas() != null) {
    				carta1Jug5= creaJLabelConImagen(j.getCartas().get(0).toString(), 440, 500, anchoCartaJug, altoCartaJug);
    				carta2Jug5 = creaJLabelConImagen(j.getCartas().get(1).toString(), 440+this.anchoCartaJug+2,500, anchoCartaJug, altoCartaJug);
    			}
    			else {
    				carta1Jug5= creaJLabelConImagen("black_joker", 440, 500, anchoCartaJug, altoCartaJug);
    				carta2Jug5 = creaJLabelConImagen("black_joker", 440+this.anchoCartaJug+2,500, anchoCartaJug, altoCartaJug);
    			}
	            add(carta1Jug5);
	            add(carta2Jug5);
    		}
    	}
    	if (numJugadores > 5) {
    		j = listaJugadores.get(5);
    		if (!j.hasFolded()) {
    			if (j.getCartas() != null) {
    				carta1Jug6= creaJLabelConImagen(j.getCartas().get(0).toString(), 160, 500, anchoCartaJug, altoCartaJug);
    				carta2Jug6 = creaJLabelConImagen(j.getCartas().get(1).toString(), 160+this.anchoCartaJug+2,500, anchoCartaJug, altoCartaJug);
    			}
    			else {
    				carta1Jug6= creaJLabelConImagen("black_joker", 160, 500, anchoCartaJug, altoCartaJug);
    				carta2Jug6 = creaJLabelConImagen("black_joker", 160+this.anchoCartaJug+2,500, anchoCartaJug, altoCartaJug);
    			}
	            add(carta1Jug6);
	            add(carta2Jug6);
    		}
    	}
    	if (numJugadores > 6) {
    		j = listaJugadores.get(6);
            carta1Jug7= creaJLabelConImagen(j.getCartas().get(0).toString(), 40, 375, anchoCartaJug, altoCartaJug);
            carta2Jug7 = creaJLabelConImagen(j.getCartas().get(1).toString(), 40+this.anchoCartaJug+2,375, anchoCartaJug, altoCartaJug);
            add(carta1Jug7);
            add(carta2Jug7);
    	}
    	if (numJugadores > 7) {
    		j = listaJugadores.get(7);
            carta1Jug8= creaJLabelConImagen(j.getCartas().get(0).toString(), 40, 215, anchoCartaJug, altoCartaJug);
            carta2Jug8 = creaJLabelConImagen(j.getCartas().get(1).toString(), 40+this.anchoCartaJug+2,215, anchoCartaJug, altoCartaJug);
            add(carta1Jug8);
            add(carta2Jug8);
    	}
    	if (numJugadores > 8) {
    		j = listaJugadores.get(8);
            carta1Jug9= creaJLabelConImagen(j.getCartas().get(0).toString(), 160, 65, anchoCartaJug, altoCartaJug);
            carta2Jug9 = creaJLabelConImagen(j.getCartas().get(1).toString(), 160+this.anchoCartaJug+2,65, anchoCartaJug, altoCartaJug);
            add(carta1Jug9);
            add(carta2Jug9);
    	}
    	
    	revalidate();
    }

	@Override
	public void onRangoModificado(RangoSoloLectura r, int jugadorActual) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStringRangoModificado(String entero, int jugadorActual) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAttach(RangoSoloLectura r, int jugadorActual) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onActualizaOutput(char juego, String[] rangos, String board, String dead, double tiempo,
			ResultadoSimulacion resultado) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRangoInsertado() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRankingInsertado(String nuevoRanking) {
		// TODO Auto-generated method stub
		
	}         
}
