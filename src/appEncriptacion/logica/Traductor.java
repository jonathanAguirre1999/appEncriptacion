package appEncriptacion.logica;

import java.util.HashMap;
import java.util.Map;

public class Traductor {

	private static final String LETRAS = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz";
	private static final String NUMEROS = "0123456789";
	private static final String SIMBOLOS = " ä`á~!@Ä#Á$ë%É^&Ëé*í(ï)_-Í+=Ï[{ó]}ö|;Ó:/Ö?ü.ú><,Ú°¡Ü¿";
	
	private static String alfabeto = LETRAS + NUMEROS + SIMBOLOS;
	private static Map<Character, Integer> alfabetoMapeado;

	
	public Traductor() {	
	}
	
	static {
		alfabetoMapeado = new HashMap<>();
		for (int i = 0; i < alfabeto.length(); i++) {
			char caracter = alfabeto.charAt(i);
			alfabetoMapeado.put(caracter, i);
		}
	}
	
	public static boolean esCaracterAdmitido(char c) {
		return alfabetoMapeado.containsKey(c);
	}
	
	public String procesar(String mensaje, String opcion, int semilla) {
		StringBuilder sb = new StringBuilder();
		switch(opcion) {
			case "ENCRIPTAR":
				for(int i = 0; i < mensaje.length(); i++) {
					char c = mensaje.charAt(i);
					int indiceMapa = alfabetoMapeado.get(c);
					int nuevoCharIndice = encriptar(indiceMapa, semilla, alfabeto.length());
					sb.append(alfabeto.charAt(nuevoCharIndice));
				}
				break;
			case "DESENCRIPTAR":
				for(int i = 0; i < mensaje.length(); i++) {
					char c = mensaje.charAt(i);
					int indiceMapa = alfabetoMapeado.get(c);
					int nuevoCharIndice = desencriptar(indiceMapa, semilla, alfabeto.length());
					sb.append(alfabeto.charAt(nuevoCharIndice));
				}
				break;
		}
		return sb.toString();
	}
	
	private int encriptar(int indice, int semilla, int n) {
		int posFinal = (indice + semilla) % n;
		return posFinal;
	}
	
	private int desencriptar(int indice, int semilla, int n) {
		int posFinal = ((indice - semilla) + n) % n;
		return posFinal;
	}
}
