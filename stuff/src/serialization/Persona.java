package serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Persona implements Serializable{

	private static final long serialVersionUID = 7380592166295675352L;

	private String nombre, apellido;
	private int edad, tlf;
	
	private String check = "check";
	
	public Persona(String nombre, String apellido, int edad, int tlf) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.tlf = tlf;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getTlf() {
		return tlf;
	}

	public void setTlf(int tlf) {
		this.tlf = tlf;
	}
	
	public String getCheck() {
		return this.check;
	}
	
	public static void main(String[] args) {
		
		Persona p = new Persona("Federico", "Garcia", 43, 603475869);
		
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("datos.bin"));
			os.writeObject(p);
			System.out.println("object has been serialized");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("datos.bin"));
			Persona p1 = (Persona) is.readObject();
			
			System.out.println(p1.getNombre());
			System.out.println(p1.getCheck());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}	
	
}
