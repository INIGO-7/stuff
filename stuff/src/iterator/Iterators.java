package iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Iterators {

	public static void main(String[] args) {
		
		List<String> languages = new ArrayList<String>();
		
		languages.add("Java");
		languages.add("JavaScript");
		languages.add("C++");
		languages.add("PHP");
		languages.add("Python");
		languages.add("Scala");
		languages.add("Haskell");
		
		Iterator<String> it = languages.iterator();
		
		while(it.hasNext()) {
			System.out.print(it.next() + ", ");
		}
		
		//we can't remove an item while a for each loop is running, so to remove items while looping we must use an iterator instead of for each.
		
	}
	
}
