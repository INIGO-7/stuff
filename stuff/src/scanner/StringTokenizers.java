package scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import programas.analizadorDeFrecuencia.AnalyzerException;

public class StringTokenizers {

	public StringTokenizers() {
		
		File f = new File("C:\\Users\\inigo\\git\\stuff\\stuff\\res\\files\\strngtknzer.txt");
		
		try (BufferedReader reader = new BufferedReader(new FileReader(f))){
			String line = reader.readLine(); //si el fichero está vacío o no hay mas líneas devuelve null
			
			while(line != null) {
				StringTokenizer tokenizer = new StringTokenizer(line, " ,;.-");
				
				while(tokenizer.hasMoreTokens()) {
					
					String word = tokenizer.nextToken();
					
					for(int s = 0; s < word.length(); s++) {
						
						
					}	
				}
				line = reader.readLine();
			}
			
		}catch (IOException e) {
			//
		}
	}
	
	public static void main(String[] args) {
		
	}
	

}