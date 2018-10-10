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
public class PanelJugadoresHoldem extends JPanel implements I_Observador{
	
	// Atributo
	private int numJugadores;
	private JButton[] botonesRangoJugador;
	private JTextField[] jTextFieldsRangoJugador;
	private JTextField[] jTextFieldsEquityJugador;
	private VentanaRango[] ventanasRango;

	private Controlador controlador;

    // Constructor
    public PanelJugadoresHoldem(Controlador controlador) {
    	
        this.controlador = controlador;
        this.numJugadores = 10;
        
		this.setLayout(new FlowLayout());
              
        initComponentes();

		this.controlador.addObservador(this);

        this.setPreferredSize(new Dimension(400, 800));
    }
    
    public void initComponentes() {
    	
    	JLabel cabecera = new JLabel("Hand Distribution"
    			+ "                                                           Equity");
    	this.add(cabecera);
    	
    	botonesRangoJugador = new JButton[numJugadores];
    	jTextFieldsRangoJugador = new JTextField[numJugadores];
    	jTextFieldsEquityJugador = new JTextField[numJugadores];
    	ventanasRango = new VentanaRango[numJugadores];
    	
    	for (int i = 0; i < numJugadores; i++) {
			final int index = i;

    		ventanasRango[i] = new VentanaRango(this.controlador, i);
    		jTextFieldsRangoJugador[i] = new JTextField(15);
			jTextFieldsRangoJugador[i].addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					super.focusGained(e);
					controlador.cambiaJugadorActual(index);
				}
			});

			jTextFieldsRangoJugador[i].addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent ke) {
                    boolean exitoParseo = true;
                    String rangoEntero = jTextFieldsRangoJugador[index].getText();
                    String[] manos = rangoEntero.split(",");
                    String mano;

					if (!rangoEntero.equals("random")) {
						if (!rangoEntero.equals("")) {
                            int i = 0;
                            while (i < manos.length && exitoParseo) {
                                mano = manos[i];
                                if (!(mano.matches("([AKQJT2-9][hcsd][AKQJT2-9][hdsc])") ||
                                        mano.matches("((([AKQJT2-9][AKQJT2-9][s])[-+]{0,2})+|(([AKQJT2-9][AKQJT2-9][o])[-+]{0,2})+|((AA|KK|QQ|JJ|TT|99|88|77|66|55|44|33|22)[-+]{0,2})+)")))
                                    exitoParseo = false;
                                // mas parseo
                                i++;
                            }
                        }

						if (exitoParseo) {
                            jTextFieldsRangoJugador[index].setBackground(Color.WHITE);

                            //Marcar el rango
                            StringBuilder rangoAMarcar = new StringBuilder();
                            for (String m : manos) {
                                if (!(m.matches("([AKQJT2-9][hcsd][AKQJT2-9][hdsc])"))) {
                                    // Es un rango
                                    if (!rangoAMarcar.toString().equals("")) {
                                        rangoAMarcar.append(",");
                                    }
                                    rangoAMarcar.append(m);
                                }
                            }
                            controlador.marcarRangoJugadorActual(rangoAMarcar.toString());

                            //Marcar el texto
                            controlador.marcarTextoRangoJugadorActual(rangoEntero);

                        } else
                            jTextFieldsRangoJugador[index].setBackground(Color.RED);
					} else {
						controlador.marcaRangoJugadorActual(100.0f);
						jTextFieldsRangoJugador[index].setBackground(Color.WHITE);
					}
				}
            });

    		botonesRangoJugador[i] = new JButton("Player " + (i+1));
    		botonesRangoJugador[i].setPreferredSize(new Dimension(88, 22));
    		botonesRangoJugador[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ventanasRango[index].setVisible(true);
					controlador.cambiaJugadorActual(index);
				}
			});
    		
    		jTextFieldsEquityJugador[i] = new JTextField(4);
    		//jTextFieldsEquityJugador[i].setEditable(false);
    		
	    	this.add(botonesRangoJugador[i]);
	        this.add(jTextFieldsRangoJugador[i]);
	        this.add(jTextFieldsEquityJugador[i]);
    	}
    }

	@Override
	public void onRangoModificado(RangoSoloLectura r, int jugadorActual) {
		String s = r.toString();

		if (!this.jTextFieldsRangoJugador[jugadorActual].getText().equals(s)) {
			if (!this.jTextFieldsRangoJugador[jugadorActual].getText().equals("random")) {
				this.jTextFieldsRangoJugador[jugadorActual].setText(s);
			}
			controlador.marcarTextoRangoJugadorActual(s);
		}
	}


	@Override
	public void onAttach(RangoSoloLectura r, int jugadorActual) {
		this.jTextFieldsRangoJugador[jugadorActual].setText(r.toString());
	}

	@Override
	public void onActualizaOutput(char juego, String[] rangos, String board, String dead, double tiempo, ResultadoSimulacion resultado) {
		if (juego == 'H') {
			double[] resultados = resultado.getResultados();
			double res;
			String resStr;
			
			int index = 0;
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
	public void onStringRangoModificado(String entero, int jugadorActual) {
		if (!this.jTextFieldsRangoJugador[jugadorActual].getText().equals("random")) {
			this.jTextFieldsRangoJugador[jugadorActual].setText(entero);
		}
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
		for (JTextField t : jTextFieldsEquityJugador) {
			t.setText("");
			t.setBackground(Color.white);
		}
		for (JTextField t : jTextFieldsRangoJugador) {
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