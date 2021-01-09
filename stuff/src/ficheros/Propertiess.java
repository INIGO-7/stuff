package ficheros;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Propertiess {

	public static void main(String[] args) {
		
		Properties properties = new Properties();
		InputStream is = null;
		
		try {
			is = new FileInputStream("C:\\Users\\inigo\\git\\stuff\\stuff\\res\\files\\datos.properties");
			
			properties.load(is);
			
			
			int tlf = Integer.parseInt(properties.getProperty("telefono")); //para conseguir el numero de telefono como int en vez de string
			float flData = Float.parseFloat(properties.getProperty("floatData")); //prueba para ver si funciona con valores float 
			
			System.out.println(properties.getProperty("nombre"));
			System.out.println(properties.getProperty("apellido"));
			System.out.println(tlf);
			System.out.println(flData);
			System.out.println(properties.getProperty("direccion"));
			
			properties.setProperty("nombre", "Alberto");
			properties.store(new FileWriter("C:\\Users\\inigo\\git\\stuff\\stuff\\res\\files\\datos.properties"), 
							"Datos del usuario"); //este string de la dcha sirve para hacer un comentario en nuestro fichero, y pondrá la fecha en la que se ha hecho
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
