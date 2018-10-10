package prod.vista;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import prod.controlador.Controlador;

@SuppressWarnings("serial")
public class VentanaReproductor extends JFrame {
    
    // Atributos
	private PanelIzquierdoReproductor panelIzquierdoReproductor;
	private PanelDerechoReproductor panelDerechoReproductor;
    
    // Constructor
    public VentanaReproductor(Controlador ctrl) {
        super("Analyser");
        this.getContentPane().setLayout(new BorderLayout());
              
        panelIzquierdoReproductor = new PanelIzquierdoReproductor(ctrl);
        panelDerechoReproductor = new PanelDerechoReproductor(ctrl);
        add(panelIzquierdoReproductor, BorderLayout.WEST);
        add(panelDerechoReproductor, BorderLayout.CENTER);
        
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
                  
}