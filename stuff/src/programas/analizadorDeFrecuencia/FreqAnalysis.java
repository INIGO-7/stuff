package programas.analizadorDeFrecuencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FreqAnalysis{

	private File f; //file representa tanto directorios como ficheros.
	private HashMap<Character, Integer> lettersMap = new HashMap<Character, Integer>(), lettersMapCopy = new HashMap<Character, Integer>();
	private HashMap<Character, Integer> top = new HashMap<Character, Integer>();
	private int keyValue, value, valorTop;
	private float frecuenciaRelativa; //total = 0;
	private int letrasTotal = 0, maxValue = 0, valorActual;
	private char letra, caracterActual, maxKey;
	private float[] topTen = new float[10];
	
	Scanner sc = new Scanner(System.in);

	public FreqAnalysis(File f) throws AnalyzerException{
		this.f = f;
		
		if(f.isDirectory()) {					//comprobamos si tenemos un fichero o un directorio
			throw new AnalyzerException("Se esperaba un fichero");	
		}
		
		for(char i = 'a'; i <= 'z' ; i++) {
			lettersMap.put(i, 0);				//inicializamos el abecedario con los valores de todas las letras a cero.
		}
	}
	
	public void Analyze() throws AnalyzerException{			//repropagamos la excepción, este método puede fallar porque processFile puede fallar.
		System.out.println("Analizando el fichero: " + f);	//podríamos capturar la excepción aquí o en processFile, lo haremos en processFile.
		processFile(f);
	
	}
	
	private void processFile(File f) throws AnalyzerException{
		//FileReader reader = new FileReader(file); el reader no tiene métodos para leer lineas, solo caracteres, así que usamos bufferedReader y le metemos FileReader

		try (BufferedReader reader = new BufferedReader(new FileReader(f))){
			String line = reader.readLine(); //si el fichero está vacío o no hay mas líneas devuelve null
			
			while(line != null) {
				StringTokenizer tokenizer = new StringTokenizer(line, " ,;.-");
				
				while(tokenizer.hasMoreTokens()) {
					
					String word = tokenizer.nextToken();
					
					for(int s = 0; s < word.length(); s++) {
						
						if(Character.isUpperCase(word.charAt(s))) 	//si está en mayúsculas lo pasamos a minúscula para que la letra se tenga en cuenta en el hashmap
							letra = Character.toLowerCase(word.charAt(s));
						else
							letra = word.charAt(s);
							
						if(lettersMap.containsKey(letra)) {
							letrasTotal++;
							keyValue = lettersMap.get(letra);
							lettersMap.replace(letra, keyValue = keyValue + 1);	//no se por que no funciona incrementar el valor de la key con: keyValue++
						}
					}	
				}
				line = reader.readLine();
			}
			
		}catch (IOException e) {
			throw new AnalyzerException("No se ha podido leer el fichero: " + f, e); //además especificamos que el error ha sido un IOException, con el 2o constructor de nuestra clase AnalyzerException.
		}
		
		
	}
	
	public void printRelativeFreq(HashMap<Character, Integer> hm) {
		for(char i = 'a'; i <= 'z' ; i++) {
			value = hm.get(i);
			frecuenciaRelativa = (float) value/getLetrasTotal();
			System.out.println("Frecuencia de " + i + ": " + frecuenciaRelativa);
		}
		//total += frecuenciaRelativa;
	}
		//System.out.println(total);
			
	public void topDiez() {
		
		lettersMapCopy = lettersMap;
		
		for(int i = 0; i < 10; i++) {
			
			for (Map.Entry me : lettersMapCopy.entrySet()) {
				
				caracterActual = (char) me.getKey();
				valorActual = (int) me.getValue();
				
				if(valorActual > maxValue) {
					maxValue = valorActual;
					maxKey = caracterActual;		
				}
			}
			lettersMapCopy.remove(maxKey);
			top.put(maxKey, maxValue);
			maxValue = 0;
		}	
	}
		

	public HashMap<Character, Integer> getLettersMap() {
		return lettersMap;
	}

	public void setLettersMap(HashMap<Character, Integer> lettersMap) {
		this.lettersMap = lettersMap;
	}

	public int getValue() {
		return keyValue;
	}

	public void setValue(int keyValue) {
		this.keyValue = keyValue;
	}

	public int getLetrasTotal() {
		return letrasTotal;
	}

	public float[] getTopTen() {
		return topTen;
	}

	public HashMap<Character, Integer> getTop() {
		return top;
	}

}
