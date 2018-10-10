package prod.vista;

import javax.swing.JPanel;

import prod.controlador.Controlador;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class PanelIzquierdoReproductor extends JPanel {
	
	public PanelIzquierdoReproductor(final Controlador controlador) {
		
		setBackground(Color.BLACK);
		
		JButton btnManoSiguiente = new JButton("MANO SIGUIENTE");
		btnManoSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.avanzarMano();
			}
		});
		
		JButton btnManoAnterior = new JButton("MANO ANTERIOR");
		btnManoAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.retrocederMano();
			}
		});
		
		JButton btnReiniciarManos = new JButton("REINICIAR MANOS");
		btnReiniciarManos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.resetManos();
			}
		});
		
		JButton btnAccionSiguiente = new JButton("ACCION SIGUIENTE");
		btnAccionSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.avanzarAccion();
			}
		});
		
		JButton btnAccionAnterior = new JButton("ACCION ANTERIOR");
		btnAccionAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.retrocederAccion();
			}
		});
		
		JButton btnReiniciarAcciones = new JButton("REINICIAR ACCIONES");
		btnReiniciarAcciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.resetAcciones();
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnReiniciarAcciones, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAccionAnterior, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAccionSiguiente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnReiniciarManos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnManoAnterior, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnManoSiguiente, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
					.addGap(193))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(56)
					.addComponent(btnManoSiguiente, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnManoAnterior, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnReiniciarManos, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
					.addComponent(btnAccionSiguiente, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAccionAnterior, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnReiniciarAcciones, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(68))
		);
		setLayout(groupLayout);
		this.setPreferredSize(new Dimension(190, 555));

	}

}
