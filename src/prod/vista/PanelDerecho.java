package prod.vista;

import javax.swing.*;

import prod.controlador.Controlador;
import prod.controlador.ResultadoSimulacion;
import prod.modelo.rangos.RangoSoloLectura;
import prod.modelo.reproductor.EstadoMesa;
import prod.observadores.I_Observador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("serial")
public class PanelDerecho extends JPanel implements I_Observador{
	
	private Controlador controlador;
	private char tipo;
	
	private JTextField txtfieldboard;
	private JTextField txtFieldDeadCards;
	
	private String[] tipos;
	private JComboBox<String> comboBox;

	/**
	 * Create the panel.
	 */
	public PanelDerecho(final Controlador controlador, char t) {
		
		this.controlador = controlador;
		this.tipo = t;
		
		this.controlador.addObservador(this);
		
		JLabel lblBoard = new JLabel("Board:");
		
		txtfieldboard = new JTextField();
		txtfieldboard.setColumns(10);
		txtfieldboard.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				boolean exitoParseo = true;
				String stringCartas = txtfieldboard.getText();
				String[] cartas = stringCartas.split(" ");
				String carta;
				
				if (cartas.length > 5)
					exitoParseo = false;
				
				int i = 0;
				while (i < cartas.length && exitoParseo) {
					carta = cartas[i];
					if (carta.length() != 2)
						exitoParseo = false;
					i++;
				}
				
				if (exitoParseo) {
					txtfieldboard.setBackground(Color.WHITE);
					controlador.marcaCartasBoard(stringCartas);
				}
				else
					txtfieldboard.setBackground(Color.RED);
			}
		});
		
		final VentanaElegirCartasBoard ventanaElegirCartasBoard = new VentanaElegirCartasBoard(controlador);
		JButton btnSelectBoard = new JButton("select");
		btnSelectBoard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ventanaElegirCartasBoard.setVisible(true);
            }
        });
		
		JLabel lblDeadCards = new JLabel("Dead cards:");
		
		txtFieldDeadCards = new JTextField();
		txtFieldDeadCards.setColumns(10);
		txtFieldDeadCards.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				boolean exitoParseo = true;
				String stringCartas = txtFieldDeadCards.getText();
				String[] cartas = stringCartas.split(" ");
				String carta;
				
				if (cartas.length > 12)
					exitoParseo = false;
				
				int i = 0;
				while (i < cartas.length && exitoParseo) {
					carta = cartas[i];
					if (carta.length() != 2)
						exitoParseo = false;
					i++;
				}
				
				if (exitoParseo) {
					txtFieldDeadCards.setBackground(Color.WHITE);
					controlador.marcaDeadCards(stringCartas);
				}
				else
					txtFieldDeadCards.setBackground(Color.RED);
			}
		});
		
		final VentanaElegirDeadCards ventanaElegirDeadCards = new VentanaElegirDeadCards(controlador);
		JButton btnSelectDeadCards = new JButton("select");
		btnSelectDeadCards.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ventanaElegirDeadCards.setVisible(true);
            }
        });
		
		JButton btnEvaluate = new JButton("Evaluate");
		btnEvaluate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Thread th = new Thread (new Runnable() {
					
					@Override
					public void run() {
						if (tipo == 'H') {
		                    controlador.evaluarHoldem();
		            	}
		            	else {
		            		controlador.evaluarOmaha();
		            	}
					}
				});
            	th.start();
            }
        });
		
		JButton btnClearAll = new JButton("Clear all");
		btnClearAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	controlador.clearAll();
            }
        });
		
		tipos = controlador.getStringRankings();
		comboBox = new JComboBox<String>(tipos);
		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controlador.cambiaRanking((String) comboBox.getSelectedItem());
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtFieldDeadCards, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblBoard, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
											.addGap(60))
										.addComponent(txtfieldboard, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnSelectDeadCards, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnSelectBoard, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblDeadCards, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(62)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(comboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnClearAll, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnEvaluate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))))
					.addContainerGap(54, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBoard)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtfieldboard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSelectBoard))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDeadCards)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFieldDeadCards, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSelectDeadCards))
					.addGap(18)
					.addComponent(btnEvaluate, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnClearAll)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(88))
		);
		setLayout(groupLayout);

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
	public void onActualizaOutput(char juego, String[] rangos, String board, String dead, double tiempo, ResultadoSimulacion resultado) {

	}

	@Override
	public void onCartasJugOmahaModificadas(String cartasDeJugadoresOmaha, int jugadorActual) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBoardModificado(String stringCartas) {
		this.txtfieldboard.setText(stringCartas);
		this.txtfieldboard.setBackground(Color.white);
	}

	@Override
	public void onDeadCardsModificadas(String stringCartas) {
		this.txtFieldDeadCards.setText(stringCartas);
		this.txtFieldDeadCards.setBackground(Color.white);
	}

	@Override
	public void onClearAll() {
		txtfieldboard.setText("");
		txtFieldDeadCards.setText("");
	}

	@Override
	public void onRangoInsertado() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRankingInsertado(String nuevoRanking) {
		comboBox.addItem(nuevoRanking);
		comboBox.updateUI();
	}

	@Override
	public void onEstadoMesaCambiado(EstadoMesa nuevoEstado, String nombreAccion) {

	}
}
