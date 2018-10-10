package prod.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import prod.controlador.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;

@SuppressWarnings("serial")
public class PanelReproductor extends JPanel {
	
	private Controlador controlador;
	private File archivo;

	public PanelReproductor(Controlador cntrl) {
		
		controlador = cntrl;
		archivo = null;
		initComponents();
		
	}
	
	public void initComponents() {
		
		JLabel lblArchivo = new JLabel("Archivo:");
		lblArchivo.setBounds(121, 82, 68, 14);
		
		final JTextField textArchivo = new JTextField();
		textArchivo.setBounds(173, 79, 196, 20);
		textArchivo.setEditable(false);
		textArchivo.setColumns(10);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
            	JFileChooser fc=new JFileChooser();
            	JPanel contentPane = new JPanel();
            	
            	fc.setCurrentDirectory(new File("src/archivos/manos/"));
            	fc.setDialogTitle("Selecciona el archivo de PokerStars");
            	fc.setFileFilter(new FileNameExtensionFilter("Documento de texto (.txt)", "txt"));

            	//Abrimos la ventana, guardamos la opcion seleccionada por el usuario
            	int seleccion=fc.showOpenDialog(contentPane);
            	if(seleccion==JFileChooser.APPROVE_OPTION){
            		//Seleccionamos el fichero
            		archivo = fc.getSelectedFile();
            		textArchivo.setText(archivo.getName());
            	}
			}
		});
		btnSeleccionar.setBounds(379, 78, 120, 23);
		
		JButton btnAbrir = new JButton("Abrir Reproductor");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (archivo != null) {
					controlador.iniciaReproductor(archivo);
					new VentanaReproductor(controlador);
				}
			}
		});
		btnAbrir.setBounds(210, 142, 196, 88);
		setLayout(null);
		add(lblArchivo);
		add(textArchivo);
		add(btnSeleccionar);
		add(btnAbrir);

	}
}
