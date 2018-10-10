package prod.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import prod.controlador.Controlador;
import prod.controlador.ResultadoSimulacion;
import prod.modelo.rangos.RangoSoloLectura;
import prod.modelo.reproductor.EstadoMesa;
import prod.observadores.I_Observador;

@SuppressWarnings("serial")
public class PanelJugadoresOmaha extends JPanel implements I_Observador{
	
	// Atributo
	private int numJugadores;
	private JButton[] botonesCartasJugador;
	private JTextField[] jTextFieldsCartasJugador;
	private JTextField[] jTextFieldsEquityJugador;
	private VentanaElegirCartas[] ventanasElegirCartas;

	private Controlador controlador;

    // Constructor
    public PanelJugadoresOmaha(Controlador controlador) {
    	
        this.controlador = controlador;
        this.numJugadores = 2;
        
		this.setLayout(new FlowLayout());
              
        initComponentes();

		this.controlador.addObservador(this);

        this.setPreferredSize(new Dimension(400, 800));
    }
    
    public void initComponentes() {
    	
    	JLabel cabecera = new JLabel("Hand Distribution"
    			+ "                                                           Equity");
    	this.add(cabecera);
    	
    	botonesCartasJugador = new JButton[numJugadores];
    	jTextFieldsCartasJugador = new JTextField[numJugadores];
    	jTextFieldsEquityJugador = new JTextField[numJugadores];
    	ventanasElegirCartas = new VentanaElegirCartas[numJugadores];
    	
    	for (int i = 0; i < numJugadores; i++) {
			final int index = i;

    		ventanasElegirCartas[i] = new VentanaElegirCartasJugOmaha(controlador, index);
    		jTextFieldsCartasJugador[i] = new JTextField(15);
			jTextFieldsCartasJugador[i].addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					super.focusGained(e);
					controlador.cambiaJugadorActual(index + 10);
				}
			});
			jTextFieldsCartasJugador[i].addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent ke) {
					boolean exitoParseo = true;
					String stringCartas = jTextFieldsCartasJugador[index].getText();
					String[] cartas = stringCartas.split(" ");
					String carta;
					
					if (cartas.length != 4)
						exitoParseo = false;
					
					int i = 0;
					while (i < cartas.length && exitoParseo) {
						carta = cartas[i];
						if (carta.length() != 2)
							exitoParseo = false;
						i++;
					}
					
					if (exitoParseo) {
						jTextFieldsCartasJugador[index].setBackground(Color.WHITE);
						controlador.marcaCartasJugadorOmaha(stringCartas);
					}
					else
						jTextFieldsCartasJugador[index].setBackground(Color.RED);
				}
			});

    		botonesCartasJugador[i] = new JButton("Player " + (i+1));
    		botonesCartasJugador[i].setPreferredSize(new Dimension(88, 22));
    		botonesCartasJugador[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ventanasElegirCartas[index].setVisible(true);
					controlador.cambiaJugadorActual(index+10);
				}
			});
    		
    		jTextFieldsEquityJugador[i] = new JTextField(4);
    		//jTextFieldsEquityJugador[i].setEditable(false);
    		
	    	this.add(botonesCartasJugador[i]);
	        this.add(jTextFieldsCartasJugador[i]);
	        this.add(jTextFieldsEquityJugador[i]);
    	}
    }

	@Override
	public void onRangoModificado(RangoSoloLectura r, int jugadorActual) {}

	@Override
	public void onAttach(RangoSoloLectura r, int jugadorActual) {}

	@Override
	public void onActualizaOutput(char juego, String[] rangos, String board, String dead, double tiempo, ResultadoSimulacion resultado) {
		if (juego == 'O') {
			double [] resultados = resultado.getResultados();
			int index = 0;
			double res;
			String resStr;
			for (JTextField field : jTextFieldsEquityJugador) {
				if (resultados[index] > 0.0) {
					res = resultados[index];
					resStr = Double.toString(res);					
					field.setText(resStr.substring(0, Integer.min(5, resStr.length())) + "%");
					
					if (res >= 33.0 && res < 66.00) {
						field.setBackground(Color.orange);
					} else if (res >= 66.0) {
						field.setBackground(Color.green);
					} else {
						field.setBackground(Color.red);
					}
				} else {
					field.setText("");
				}
				index += 3;
			}
		}
	}

	@Override
	public void onStringRangoModificado(String entero, int jugadorActual) {}

	@Override
	public void onCartasJugOmahaModificadas(String cartas, int jugadorActual) {
		jTextFieldsCartasJugador[jugadorActual].setText(cartas);
		jTextFieldsCartasJugador[jugadorActual].setBackground(Color.white);
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
		for (JTextField t : jTextFieldsEquityJugador) {
			t.setText("");
			t.setBackground(Color.white);
		}
		for (JTextField t : jTextFieldsCartasJugador) {
			t.setText("");
		}
		
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