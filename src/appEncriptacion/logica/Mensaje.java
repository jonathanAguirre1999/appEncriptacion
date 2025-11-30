package appEncriptacion.logica;

public class Mensaje {
	private String mensajeIngresado;
	
	public Mensaje() {
	}
	
	public Mensaje(String mensajeIngresado) throws Exception {
		if(!validarMensaje(mensajeIngresado)) {
			throw new Exception("El mensaje contiene caracteres invalidos");
		} else {
			this.mensajeIngresado = mensajeIngresado;
		}
	}
	
	public boolean validarMensaje(String mensajeParaValidar){
		for (int i = 0; i < mensajeParaValidar.length(); i++) {
			char caracter = mensajeParaValidar.charAt(i);
			if(!Traductor.esCaracterAdmitido(caracter)) {
				return false;
			}
		}
		return true; 
	}
	
	public String getMensajeIngresado() {
		return mensajeIngresado;
	}
}
