package collections.Map;

import java.util.*;

public class Hashmap {
	
	private HashMap<String, Integer> hs;
	
	public Hashmap() {
		hs = new HashMap<>();
		
		hs.put("Inigo", 10);
		hs.put("Pedgo", 5);
		hs.put("Jorge", 9);
		hs.put("Jaime", 7);
		hs.put("Alberto", 0);
		
	}
	
	public void getNotas() {
		int notaInigo = hs.get("Inigo");
		int notaPedgo = hs.get("Pedgo");
		
		System.out.println("nota de Inigo: " + notaInigo + ", nota de Pedgo: " + notaPedgo);
	}
	
	public void getEveryThing() {
		
//		System.out.println(hs.toString());				//devuelve un toString de todos los valores del hashMap. Es un método ya implementado que hace algo parecido al método de abajo:
		
		if(!hs.isEmpty()) for (Map.Entry<String,Integer> entry : hs.entrySet())  System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		else System.out.println("El listado de las notas de alumnos está vacío.");
	} 
	
	public void getAllValues() {
		if(!hs.isEmpty()) for (int value : hs.values())  System.out.println("Value = " + value);
		else System.out.println("El listado de las notas de alumnos está vacío.");
	}
	
	public void getAllKeys() {
		if(!hs.isEmpty()) for (String key : hs.keySet())  System.out.println("Key = " + key);
		else System.out.println("El listado de las notas de alumnos está vacío.");
	}
	
	public void setNota(String alumno, int nota) {
		hs.putIfAbsent(alumno, nota);					//cambia la nota si el alumno no tenía una nota asociada previamente.
	}
	
	public void cambiarNota(String alumno, int nota) {
		hs.put(alumno, nota);							//si el alumno ya existía, se sustituye su nota, si no se crea el alumno y se asocia la nota especificada.
//		hs.replace(alumno, nota);						//reemplaza el valor sólo si ya tenía un valor asignado previamente.
//		hs.replace("Pedgo", 5, 6);						//reemplaza el valor sólo si el valor anterior era el especificado.
	}
	
	public void quitarAlumno(String alumno) {
		hs.remove(alumno);
	}
	
	public void eliminarNoPresentado(String alumno) {
		hs.remove(alumno, 0);							//se borra sólo si el alumno tiene un 0 asociado
		if(!hs.containsKey("alberto")) System.out.println("El alumno no presentado ha sido borrado.");
		else System.out.println("el alumno se ha presentado al examen.");
	}
	
	public void borrarAlumnos() {
		hs.clear();
	}
	
	public void getCantidadAlumnos() {
		System.out.println(hs.size());
	}
	
	public void setNotasFinales() {
		if(!hs.isEmpty()) {
			for (Map.Entry<String,Integer> entry : hs.entrySet()) {		//puede no especificarse la clase del entry eg: for(Map.Entry entry : hs.entrySet()) -> luego hay que hacer cast para los valores, pero funciona igualmente
		
				if(entry.getValue() < 5) System.out.println(entry.getKey() + " ha suspendido.");
				else if(entry.getValue() < 7) System.out.println(entry.getKey() + " ha aprobado.");
				else if(entry.getValue() < 9) System.out.println(entry.getKey() + " tiene una calificacion excelente.");
				else System.out.println(entry.getKey() + " tiene una calificacion sobresaliente.");
			}
		}else System.out.println("El listado de las notas de alumnos está vacío.");
	} 
	
	public HashMap<String, Integer> getMap(){
		return hs;
	}
	
	public static void main(String[] args) {
		Hashmap hmap = new Hashmap();
		
		hmap.getNotas();
		
		hmap.setNota("Rodrigo", 8);
		
		hmap.getNotas();
		
		hmap.getEveryThing();
		
		hmap.getAllValues();
		
		hmap.getAllKeys();
		
		hmap.setNotasFinales();
		
		hmap.eliminarNoPresentado("Alberto");
		
		hmap.borrarAlumnos();
		
		hmap.getAllKeys();
		
		hmap.getCantidadAlumnos();
		
	}
	
}
