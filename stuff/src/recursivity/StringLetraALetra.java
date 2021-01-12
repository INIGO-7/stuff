package recursivity;

import java.util.Scanner;

public class StringLetraALetra {

	private String cadenaImprimir, cadenaOriginal;
	
	public StringLetraALetra(String analyze) {
		
		this.cadenaOriginal = analyze;
		System.out.println(imprimirString(0));
		
	}
	
	public String imprimirString(int i){
		if(i == cadenaOriginal.length() - 1) return String.valueOf(cadenaOriginal.charAt(i));
		else cadenaImprimir = cadenaOriginal.charAt(i) + imprimirString(i + 1); 
		
		return cadenaImprimir;
	}
	
	public static void main(String[] args) {
		StringLetraALetra st = new StringLetraALetra("holaquetal");
	}
	
}