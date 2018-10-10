package prod.vista;

import prod.controlador.Controlador;
import prod.controlador.ResultadoSimulacion;
import prod.modelo.rangos.RangoSoloLectura;
import prod.modelo.reproductor.EstadoMesa;
import prod.modelo.util.Constantes;
import prod.modelo.util.Utilidades;
import prod.observadores.I_Observador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class VentanaRango extends JFrame implements I_Observador {
	
	private JPanel cuadriculaManos;
	private JPanel seleccionRango;
	private JTextField porcentaje;
    private JSlider slider;
    private JButton[][] buttons;
	private GridBagConstraints c;
	
	private JPanel seleccionRangoPolarizado;
	private JTextField txtFieldW;
	private JTextField txtFieldX;
	private JTextField txtFieldY;
	private JTextField txtFieldZ;
	private JLabel labelW;
	private JLabel labelX;
	private JLabel labelY;
	private JLabel labelZ;
	private JButton botonAceptar;
	
	@SuppressWarnings("rawtypes")
	private JList rangosFichero;
    private JScrollPane scrollPane;
	private JPanel panelDerecho;
	private JPanel panelIzquierdo;
	
	private Controlador controlador;
	private int numJugador;
	
	public VentanaRango(Controlador controlador, int i) {
		super("Ventana para elegir rangos");
		this.controlador = controlador;
		this.numJugador = i;
		
		this.getContentPane().setLayout(new BorderLayout());
		initComponentes();

		this.controlador.addObservador(this);
		this.pack();
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initComponentes() {
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		this.cuadriculaManos = new JPanel();
		this.cuadriculaManos.setLayout(new GridBagLayout());
		this.cuadriculaManos.setPreferredSize(new Dimension(750, 550));
		
		buttons = new JButton[13][13];
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 13; j++) {
				buttons[i][j] = createButton(i,j);
				c.gridy = 13 - i;
				c.gridx = 13 - j;
				this.cuadriculaManos.add(buttons[i][j], c);
			}
		}
		
		porcentaje = new JTextField(4);
		slider = new JSlider(0, 100, 0);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				porcentaje.setText(String.valueOf(slider.getValue()));
				controlador.marcaRangoJugadorActual(slider.getValue());
			}
		});
	    porcentaje.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				String typed = porcentaje.getText();
				slider.setValue(0);
				if (!typed.matches("\\d+") || typed.length() > 3) {
					return;
				}
				int value = Integer.parseInt(typed);
				slider.setValue(value);
			}
		});
	    
	    seleccionRango = new JPanel();
	    seleccionRango.add(slider);
	    seleccionRango.add(porcentaje);
	    
	    txtFieldW = new JTextField(3);
	    txtFieldX = new JTextField(3);
	    txtFieldY = new JTextField(3);
	    txtFieldZ = new JTextField(3);
	    
		labelW = new JLabel("W:");
		labelX = new JLabel("X:");;
		labelY = new JLabel("Y:");;
		labelZ = new JLabel("Z:");;
		botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int w, x, y, z;
				porcentaje.setText("-");
				try {
					w = Integer.parseInt(txtFieldW.getText());
					x = Integer.parseInt(txtFieldX.getText());
					y = Integer.parseInt(txtFieldY.getText());
					z = Integer.parseInt(txtFieldZ.getText());
					
					if (w >= 0 && x >= 0 && y >= 0 && z >= 0
							&& w <= 100 && x <= 100 && y <= 100 && z <= 100)
						controlador.marcaRangoPolarizadoJugadorActual(w, x, y, z);
					
				} catch(NumberFormatException e1) {
					System.out.println("Error formato JTextField");
				}
				
			}
		});
	    
	    seleccionRangoPolarizado = new JPanel();
	    seleccionRangoPolarizado.add(labelW);
	    seleccionRangoPolarizado.add(txtFieldW);
	    seleccionRangoPolarizado.add(labelX);
	    seleccionRangoPolarizado.add(txtFieldX);
	    seleccionRangoPolarizado.add(labelY);
	    seleccionRangoPolarizado.add(txtFieldY);
	    seleccionRangoPolarizado.add(labelZ);
	    seleccionRangoPolarizado.add(txtFieldZ);
	    seleccionRangoPolarizado.add(botonAceptar);
	    
	    
	    panelIzquierdo = new JPanel(new BorderLayout());
	    
	    panelIzquierdo.add(cuadriculaManos, BorderLayout.NORTH);
	    panelIzquierdo.add(seleccionRango, BorderLayout.CENTER);
	    panelIzquierdo.add(seleccionRangoPolarizado, BorderLayout.SOUTH);
	    
	    
	    rangosFichero = new JList(controlador.getStringRangos());
	    scrollPane = new JScrollPane(rangosFichero);
	    scrollPane.setPreferredSize(new Dimension(180, 600));
	    panelDerecho = new JPanel();
	    panelDerecho.add(scrollPane);
	    
	    add(panelIzquierdo, BorderLayout.CENTER);
	    add(panelDerecho, BorderLayout.EAST);
	}
	
	private JButton createButton(int i, int j) {
		String nombre;
		final Color color;
		if (i == j) {
			color = Color.green;
			nombre = Utilidades.int2String(i) + Utilidades.int2String(j);
		}
		else if (i > j) {
			color = Color.red;
			nombre = Utilidades.int2String(i) + Utilidades.int2String(j) + "s";
		}
		else {
			color = Color.lightGray;
			nombre = Utilidades.int2String(j) + Utilidades.int2String(i) + "o";
		}
		
		final JButton boton = new JButton(nombre);
		//boton.setFont(new Font("Arial", 8, 8));
		boton.setBackground(color);

		final int x = i, y = j;
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (boton.getBackground() != Color.yellow) {
					controlador.marcaManoJugadorActual(x, y);
					boton.setBackground(Color.yellow);
				} else {
					controlador.desmarcaManoJugadorActual(x, y);
					boton.setBackground(color);
				}
				porcentaje.setText("-");
			}
		});
		return boton;
	}

	@Override
	public void onRangoModificado(RangoSoloLectura r, int jugadorActual) {
		if (jugadorActual == numJugador) {
			for (int i = 0; i < Constantes.TABLA_RANGOS_X; i++) {
				for (int j = 0; j < Constantes.TABLA_RANGOS_X; j++) {
					if (r.getManoEnRango(i, j)) {
						this.buttons[i][j].setBackground(Color.yellow);
					}else {
						if (i == j)
							this.buttons[i][j].setBackground(Color.green);
						else if (i > j) {
							this.buttons[i][j].setBackground(Color.red);
						}
						else {
							this.buttons[i][j].setBackground(Color.lightGray);
						}
					}
				}
			}
		}
	}

	@Override
	public void onAttach(RangoSoloLectura r, int jugadorActual) {

	}

	@Override
	public void onActualizaOutput(char juego, String[] rangos, String board, String dead, double tiempo, ResultadoSimulacion resultado) {

	}

	@Override
	public void onStringRangoModificado(String entero, int jugadorActual) {
		/*if (jugadorActual == numJugador) {
			for (int i = 0; i < Constantes.TABLA_RANGOS_X; i++) {
				for (int j = 0; j < Constantes.TABLA_RANGOS_X; j++) {
					if (r.getManoEnRango(i, j)) {
						this.buttons[i][j].setBackground(Color.yellow);
					}else {
						if (i == j)
							this.buttons[i][j].setBackground(Color.green);
						else if (i > j) {
							this.buttons[i][j].setBackground(Color.red);
						}
						else {
							this.buttons[i][j].setBackground(Color.lightGray);
						}
					}
				}
			}
		}*/
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onRangoInsertado() {
		rangosFichero = new JList(controlador.getStringRangos());
	    scrollPane = new JScrollPane(rangosFichero);
	    scrollPane.setPreferredSize(new Dimension(180, 600));
	    panelDerecho.removeAll();
	    panelDerecho.add(scrollPane);
	    panelDerecho.updateUI();
	}

	@Override
	public void onRankingInsertado(String nuevoRanking) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEstadoMesaCambiado(EstadoMesa nuevoEstado, String nombreAccion) {

	}

}
