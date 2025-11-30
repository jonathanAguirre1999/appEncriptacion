package appEncriptacion.igu;

import appEncriptacion.logica.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelBase;
	private JScrollPane scMensajeIngresado;
	private JScrollPane scMensajeDevuelto;
	private JTextArea txtMensajeIngresado;
	private JTextArea txtMensajeDevuelto;
	private JLabel lblResultado;
	private JButton btnAccion;
	private String [] opciones = {"ENCRIPTAR", "DESENCRIPTAR"};
	private JComboBox <String> cbOpciones;
	private JTextField txtSemilla;
	private Traductor traductor;
	private Mensaje mensaje;
	private JButton btnLimpiar;
	
	
	public MenuPrincipal(Traductor traductor, Mensaje mensaje) {
		this.traductor = traductor;
		this.mensaje = mensaje;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 791, 532);
		panelBase = new JPanel();
		panelBase.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelBase);
		panelBase.setLayout(null);
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(139, 195, 74));
		panelPrincipal.setBounds(0, 0, 777, 495);
		panelBase.add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JLabel lblTitulo = new JLabel("ENCRIPTACION Y DESENCRIPTACION DE MENSAJES");
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 16));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(195, 10, 407, 30);
		panelPrincipal.add(lblTitulo);
		
		JLabel lblNewLabel = new JLabel("INGRESAR MENSAJE");
		lblNewLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 44, 148, 23);
		panelPrincipal.add(lblNewLabel);
		
		txtMensajeIngresado = new JTextArea();
		txtMensajeIngresado.setBackground(new Color(241, 248, 233));
		txtMensajeIngresado.setLineWrap(true);
		txtMensajeIngresado.setWrapStyleWord(true);
		txtMensajeIngresado.setBounds(10, 77, 757, 159);
		scMensajeIngresado = new JScrollPane(txtMensajeIngresado);
		scMensajeIngresado.setBounds(10, 77, 757, 159);
		panelPrincipal.add(scMensajeIngresado);
		
		lblResultado = new JLabel("MENSAJE ENCRIPTADO");
		lblResultado.setHorizontalAlignment(SwingConstants.LEFT);
		lblResultado.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblResultado.setBounds(10, 248, 191, 23);
		panelPrincipal.add(lblResultado);

		txtMensajeDevuelto = new JTextArea();
		txtMensajeDevuelto.setBackground(new Color(220, 237, 200));
		txtMensajeDevuelto.setLineWrap(true);
		txtMensajeDevuelto.setWrapStyleWord(true);
		txtMensajeDevuelto.setBounds(10, 77, 757, 159);
		txtMensajeDevuelto.setEditable(false);
		scMensajeDevuelto = new JScrollPane(txtMensajeDevuelto);
		scMensajeDevuelto.setBounds(10, 280, 757, 159);
		panelPrincipal.add(scMensajeDevuelto);
		
		btnAccion= new JButton("ENCRIPTAR");
		btnAccion.setFont(new Font("Roboto", Font.PLAIN, 12));
		btnAccion.setBackground(new Color(118, 255, 3));
		btnAccion.setBounds(596, 449, 148, 36);
		btnAccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensajeIngresado = txtMensajeIngresado.getText();
				int semilla = Integer.parseInt(txtSemilla.getText());
				try {
					if(mensaje.validarMensaje(mensajeIngresado)) {
						String opcion = (String) cbOpciones.getSelectedItem();
						String mensajeProcesado = traductor.procesar(mensajeIngresado, opcion, semilla);
						txtMensajeDevuelto.setText(mensajeProcesado);
						JOptionPane.showMessageDialog(MenuPrincipal.this, "Operacion realizada exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
					}
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(MenuPrincipal.this, "Operacion fallida: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
				
			}
		});
		panelPrincipal.add(btnAccion);
		
		cbOpciones= new JComboBox<>(opciones);
		cbOpciones.setBackground(new Color(104, 159, 6));
		cbOpciones.setFont(new Font("Roboto", Font.BOLD, 12));
		cbOpciones.setBounds(596, 41, 148, 30);
		cbOpciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seleccion = cbOpciones.getSelectedItem().toString();
				btnAccion.setText(seleccion.toUpperCase());
				if(seleccion.equalsIgnoreCase("encriptar")) {
					lblResultado.setText("MENSAJE ENCRIPTADO");
				} else {
					lblResultado.setText("MENSAJE DESENCRIPTADO");
				}
			}
			
			
		});
		panelPrincipal.add(cbOpciones);
		
		txtSemilla = new JTextField();
		txtSemilla.setBackground(new Color(241, 248, 233));
		txtSemilla.setFont(new Font("Roboto", Font.PLAIN, 12));
		txtSemilla.setBounds(79, 455, 33, 26);
		panelPrincipal.add(txtSemilla);
		txtSemilla.setColumns(10);
		txtSemilla.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c)) {
					e.consume();
					return;
				}
				
				if(txtSemilla.getText().length() > 1) {
					e.consume();
				}
			}
		});
		txtSemilla.setHorizontalAlignment(SwingConstants.CENTER);
		txtSemilla.setText("3");
		
		JLabel lblSemilla = new JLabel("SEMILLA");
		lblSemilla.setHorizontalAlignment(SwingConstants.LEFT);
		lblSemilla.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblSemilla.setBounds(10, 456, 72, 23);
		panelPrincipal.add(lblSemilla);
		
		btnLimpiar = new JButton("LIMPIAR CAMPOS");
		btnLimpiar.setFont(new Font("Roboto", Font.PLAIN, 12));
		btnLimpiar.setBackground(new Color(118, 255, 3));
		btnLimpiar.setBounds(414, 449, 148, 36);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMensajeDevuelto.setText("");
				txtMensajeIngresado.setText("");
				txtSemilla.setText("3");
			}
		});
		panelPrincipal.add(btnLimpiar);
		
		
	}
	
}
