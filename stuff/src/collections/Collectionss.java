package collections;

import java.util.Collections;

import collections.List.Arraylist;

public class Collectionss {

	public static void main(String[] args) {
		
		Arraylist arl = new Arraylist();						//clase Arraylist, no confundir con colección ArrayList<>
		
		System.out.println("Maximum value of arraylist is: " + Collections.max(arl.getArraylist()));
		System.out.println("Minimum value of arraylist is: " + Collections.min(arl.getArraylist()));
		
		System.out.println(Collections.binarySearch(arl.getArraylist(), 30));			//binarySearch devuelve el index del elemento buscado si lo encuentra, y si no devuelve (-(insertion point) - 1)
																						//que es -(el index en el que se metería el elemento en nuestra coleccion) - 1	
		
		
	}
	
}
