package scanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class BufferedWriters {
	
	public BufferedWriters(){
//		try (
//				BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\inigo\\git\\stuff\\stuff\\res\\files\\ficheroPrueba.txt"));
//				BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\inigo\\git\\stuff\\stuff\\res\\files\\ficheroPrueba.txt")) 		
//				){
//				
//				//Al inicializar el bufferedWriter se borra el contenido del fichero.
//				
//				//escribimos en el fichero
//				bw.write("Esto es una prueba de BufferedWriter");
//				bw.newLine();
//				bw.write("Segunda linea usando buffered, se ve bien fresco y va fluido (prueba)");
//				//guardamos los cambios en el fichero
//				bw.flush();
//				//y ahora leemos el fichero
//				
//				String linea;
//				
//				while((linea = br.readLine()) != null) {											//asignamos una nueva linea a la variable linea
//					System.out.println(linea);
//				}
//				
//			}catch (IOException e) {																//ponemos ioexception porque implementamos lectura de ficheros
//				// TODO Auto-generated catch block
//				System.out.println("Error en la entrada y salida de datos: " + e);
//		}// no tenemos que generar un finally para cerrar recursos ni otras operaciones al usar try with resources. try with resources can be used with any object that implements the AutoCloseable interface
		
	}
	
	public void writeNewLine() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\inigo\\git\\stuff\\stuff\\res\\files\\ficheroPrueba.txt"));
			bw.write("no funciona");
			bw.newLine();
			bw.write("a ver si esto funciona");
			bw.flush();
			
			Scanner sc = new Scanner("C:\\Users\\inigo\\git\\stuff\\stuff\\res\\files\\ficheroPrueba.txt");
			
			while(sc.hasNext()) {
				System.out.println("jeje");
				System.out.println(sc.next());
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		BufferedWriters bf = new BufferedWriters();
		bf.writeNewLine();
		
	}	
}	