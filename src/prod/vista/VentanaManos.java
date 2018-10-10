package prod.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import prod.modelo.util.Utilidades;

@SuppressWarnings("serial")
public class VentanaManos extends JFrame {

	private String[] manos;
	private JButton[][] buttons;
	private GridBagConstraints c;
	private JPanel cuadriculaManos;
	private int contador;
	
	public VentanaManos() {
		manos = new String[169];
		this.contador = 0;
		
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
				final String nombre;
				Color color;
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
	
				boton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (boton.getBackground() != Color.yellow) {
							manos[contador] = nombre;
							contador++;
							boton.setBackground(Color.yellow);
						}
					}
				});
				buttons[i][j] = boton;
				c.gridy = 13 - i;
				c.gridx = 13 - j;
				this.cuadriculaManos.add(buttons[i][j], c);
			}
		}
		
		JButton botonAceptar = new JButton("Aceptar");
        botonAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (contador == 169) {
            		PanelApartadoOpcional.actualizaManos(manos);
            	}
            	else {
            		JOptionPane.showMessageDialog(null,
        				    "Sé paciente, solo te quedan " + (169-contador) + " manos por marcar");
            	}
            }
        });
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(cuadriculaManos, BorderLayout.CENTER);
		this.getContentPane().add(botonAceptar, BorderLayout.SOUTH);
		
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
	}

}
