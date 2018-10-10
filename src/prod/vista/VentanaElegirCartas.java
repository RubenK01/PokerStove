package prod.vista;

import prod.modelo.util.Utilidades;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public abstract class VentanaElegirCartas extends JFrame{

	private JButton[][] buttons;
	private JButton botonAceptar;
    private int maxCartas;
    private int cartasSeleccionadas;

    public VentanaElegirCartas(int maxCartas) {
        super("Cartas");
        this.maxCartas = maxCartas;
        this.cartasSeleccionadas = 0;

        this.getContentPane().setLayout(new BorderLayout());
        initComponentes();
        
        this.pack();
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    private void initComponentes() {
    	JPanel panelBotones = new JPanel();
    	panelBotones.setLayout(new GridLayout(13,4));
    	buttons = new JButton[13][13];
        for (int i = 12; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j] = createButton(i,j);
                panelBotones.add(buttons[i][j]);
            }
        }
        
        this.botonAceptar = new JButton("Aceptar");
        botonAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String cartas = "";
            	
            	for (int i = 0; i < 13; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (buttons[i][j].getBackground() == Color.yellow)
                        	cartas += pos2String(i, j) + " ";
                    }
                }
            	
            	llamadaAlControlador(cartas);
            }
        });
        
        this.getContentPane().add(panelBotones, BorderLayout.CENTER);
        this.getContentPane().add(botonAceptar, BorderLayout.SOUTH);
    }
    
    protected abstract void llamadaAlControlador(String cartas);
    
    protected void marcaCartas(String stringCartas) {
    	repintaBotones();
		String[] cartas = stringCartas.split(" ");
		String carta;
		this.cartasSeleccionadas = 0;
		
		for (int i = 0; i < cartas.length; i++) {
			carta = cartas[i];
			buttons[Utilidades.char2int(carta.charAt(0))][Utilidades.charPalo2int(carta.charAt(1))].setBackground(Color.yellow);
			cartasSeleccionadas++;
		}
    }
    
    private void repintaBotones() {
    	Color color;
    	for (int i = 12; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
            	if (j == 0)
                	color = Color.green;
                else if (j == 1)
                	color = Color.lightGray;
                else if (j == 2)
                	color = Color.red;
                else
                	color = Color.blue;
                buttons[i][j].setBackground(color);
            }
        }
    }

    private JButton createButton(int i, int j) {
        String nombre = Utilidades.int2String(i);
        final Color color;
        if (j == 0) {
        	nombre += "c";
        	color = Color.green;
        }
        else if (j == 1) {
        	nombre += "d";
        	color = Color.lightGray;
        }
        else if (j == 2) {
        	nombre += "h";
        	color = Color.red;
        }
        else {
        	nombre += "s";
        	color = Color.blue;
        }

        final JButton boton = new JButton(nombre);
        boton.setBackground(color);

        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (boton.getBackground() != Color.yellow) {
	            	if (cartasSeleccionadas < maxCartas) {
	            		cartasSeleccionadas++;
	            		boton.setBackground(Color.yellow);
	            	}
            	}
	            else {
	            	cartasSeleccionadas--;
	            	boton.setBackground(color);
	            }
            }
        });
        return boton;
    }
    
    String pos2String(int i, int j) {
    	String cad = Utilidades.int2String(i);
    	if (j == 0) {
    		cad += "c";
        }
        else if (j == 1) {
        	cad += "d";
        }
        else if (j == 2) {
        	cad += "h";
        }
        else {
        	cad += "s";
        }
    	return cad;
    }

}
