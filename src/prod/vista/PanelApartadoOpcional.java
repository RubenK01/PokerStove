package prod.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import prod.controlador.Controlador;

@SuppressWarnings("serial")
public class PanelApartadoOpcional extends JPanel {

	private Controlador controlador;
	
	private JTextField textNombreRanking;
	private static JTextField textManos;
	private JTextField textFieldNombreRango;
	private JTextField textPorcentaje;
	
	private VentanaManos ventanaManos;
	private static String[] manos = null;
	
	public PanelApartadoOpcional(Controlador cont) {
		this.controlador = cont;
				
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Rankings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Rangos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JLabel lblNombreRango = new JLabel("Nombre:");
		
		textFieldNombreRango = new JTextField();
		textFieldNombreRango.setColumns(10);
		
		JLabel lblPorcentaje = new JLabel("Porcentaje:");
		
		textPorcentaje = new JTextField();
		textPorcentaje.setColumns(10);
		
		JButton btnAceptarRango = new JButton("Aceptar");
		btnAceptarRango.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
            		Float porcentaje = Float.parseFloat(textPorcentaje.getText());
            		if (porcentaje <= 100.0 && porcentaje >= 0.0) {
            			String nombreRango = textFieldNombreRango.getText();
	            		if (!controlador.getRangos().containsKey(nombreRango) && nombreRango != "" && nombreRango != null) {
	            			controlador.insertaRango(nombreRango, porcentaje);
	            			JOptionPane.showMessageDialog(null,
	            				    "Rango insertado con éxito");
	            		} else {
	            			JOptionPane.showMessageDialog(null,
	            				    "El nombre del rango no es válido.",
	            				    "Error",
	            				    JOptionPane.ERROR_MESSAGE);
	            		}
            		}
            		else {
            			JOptionPane.showMessageDialog(null,
            				    "Porcentaje no válido.",
            				    "Error",
            				    JOptionPane.ERROR_MESSAGE);
            		}
            		
            		
            	} catch (NumberFormatException e1) {
            		JOptionPane.showMessageDialog(null,
        				    "Porcentaje no válido.",
        				    "Error",
        				    JOptionPane.ERROR_MESSAGE);
            	}
            	
            }
        });
		
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldNombreRango, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
								.addComponent(lblNombreRango)
								.addComponent(lblPorcentaje)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(42)
							.addComponent(btnAceptarRango, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(textPorcentaje, 0, 0, Short.MAX_VALUE)
					.addGap(135))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNombreRango)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldNombreRango, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblPorcentaje)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textPorcentaje, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(23)
					.addComponent(btnAceptarRango, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(61, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNombreRanking = new JLabel("Nombre:");
		
		textNombreRanking = new JTextField();
		textNombreRanking.setColumns(10);
		
		JLabel lblManos = new JLabel("Manos:");
		
		JButton btnElegir = new JButton("Elegir");
		btnElegir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	manos = new String[169];
            	ventanaManos = new VentanaManos();
            	ventanaManos.setVisible(true);
            }
        });
		
		textManos = new JTextField();
		textManos.setColumns(10);
		textManos.setEditable(false);
		
		JButton btnAceptarRanking = new JButton("Aceptar");
		btnAceptarRanking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String nombreRanking = textNombreRanking.getText();
            	if (!controlador.getRankings().containsKey(nombreRanking) && nombreRanking != "" && manos != null) {
        			controlador.insertaRanking(nombreRanking, manos);
        			manos = null;
        			textManos.setText("");
        			JOptionPane.showMessageDialog(null,
        				    "Ranking insertado con éxito");
        		} else {
        			JOptionPane.showMessageDialog(null,
        				    "El nombre del ranking no es válido o no se han seleccionado las manos.",
        				    "Error",
        				    JOptionPane.ERROR_MESSAGE);
        		}
            }
        });
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textNombreRanking, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
								.addComponent(lblNombreRanking)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblManos)
									.addGap(29)
									.addComponent(btnElegir)))
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(textManos, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
							.addContainerGap())))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(79)
					.addComponent(btnAceptarRanking, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(83, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNombreRanking)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textNombreRanking, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblManos)
						.addComponent(btnElegir))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textManos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAceptarRanking, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(74, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
	}
	
	public static void actualizaManos(String[] stringManos) {
		String line = "";
		for (int i = 0; i < stringManos.length; i++) {
			line += stringManos[i] + " ";
		}
		textManos.setText(line);
		manos = stringManos;
	}
}

