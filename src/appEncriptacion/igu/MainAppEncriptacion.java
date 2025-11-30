package appEncriptacion.igu;

import appEncriptacion.logica.*;
import java.awt.EventQueue;

public class MainAppEncriptacion {

	public static void main(String[] args) {
		Traductor traductor = new Traductor();
		Mensaje mensaje = new Mensaje();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal menu = new MenuPrincipal(traductor, mensaje);
					menu.setVisible(true);
					menu.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
	}

}
