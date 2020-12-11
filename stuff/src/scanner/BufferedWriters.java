package scanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class BufferedWriters {
	
	public static void main(String[] args) {
		
		try (
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\inigo\\git\\stuff\\stuff\\res\\files\\ficheroPrueba.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\inigo\\git\\stuff\\stuff\\res\\files\\ficheroPrueba.txt")) 		
			){
			
			//escribimos en el fichero
			bw.write("Esto es una prueba de BufferedWriter");
			bw.newLine();
			bw.write("Segunda linea usando buffered, se ve bien fresco y va fluido (prueba)");
			//guardamos los cambios en el fichero
			bw.flush();
			//y ahora leemos el fichero
			
			String linea;
			
			while((linea = br.readLine()) != null) {											//asignamos una nueva linea a la variable linea
				System.out.println(linea);
			}
			
		}catch (IOException e) {																//ponemos ioexception porque implementamos lectura de ficheros
			// TODO Auto-generated catch block
			System.out.println("Error en la entrada y salida de datos: " + e);
		}	
	}
}	