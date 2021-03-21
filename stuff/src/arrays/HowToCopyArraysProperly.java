package arrays;

import java.util.Arrays;

public class HowToCopyArraysProperly {

	public static void main(String[] args) {
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////					1					///////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//Using the system class -> arraycopy takes the arguments: array that is going to be copied, the starting position where to copy, the array where we are going to send the copy,
		//the starting position and the number of elements we want to copy from the original array.
		
		//If any of the array arguments is null, it throws a NullPointerException and if any of the integer arguments is negative or out of range, it throws an IndexOutOfBoundException.
		
		int[] array1 = {23, 43, 55};
		int[] copiedArray1 = new int[3];

		System.arraycopy(array1, 0, copiedArray1, 0, 3);
		
//		assertTrue(3 == copiedArray.length);
//		assertTrue(copiedArray[0] == array[2]);
//		assertTrue(copiedArray[1] == array[3]);
//		assertTrue(copiedArray[2] == array[4]);

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////					2					///////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//Using the arrays class.
		
		int[] array2 = {23, 43, 55, 12};
		int newLength = array2.length;

		int[] copiedArray2 = Arrays.copyOf(array2, newLength);
		
		//copiedArray takes the args: original array, the starting position from where to copy, and the final position
		
		int[] array = {23, 43, 55, 12, 65, 88, 92};

		int[] copiedArray = Arrays.copyOfRange(array, 1, 4);
		
//		assertTrue(3 == copiedArray.length);
//		assertTrue(copiedArray[0] == array[1]);
//		assertTrue(copiedArray[1] == array[2]);
//		assertTrue(copiedArray[2] == array[3]);
		
		//it's important to note that both of these methods do a shallow copy of the objects if applied on an array of non-primitive types. 
		//that is, if we modify the original array, the copied array will also be modified.	
		
//		Employee[] copiedArray = Arrays.copyOf(employees, employees.length);
//
//		employees[0].setName(employees[0].getName() + "_Changed");
//		 
//		assertArrayEquals(copiedArray, array);
//		Because the result is a shallow copy – a change in the employee name of an element of the original array caused the change in the copy array.
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////					3					///////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//Array copy with Object.clone()
		
		int[] array3 = {23, 43, 55, 12};
		 
		int[] copiedArray3 = array.clone();
		
//		assertArrayEquals(copiedArray, array);
//		array[0] = 9;
//
//		assertTrue(copiedArray[0] != array[0]);
		
		//They both have the same content after cloning but hold different references, a change in one of them won't affect the other.
		
		//On the other hand, if we clone an array of non-primitive types using the same method, then the results will be different.
		//It creates a shallow copy of the non-primitive type array elements, even if the enclosed object's class implements the Cloneable 
		//interface and overrides the clone() method from the Object class.
		
//		public class Address implements Cloneable {
//		    // [...]
//
//		    @Override
//		    protected Object clone() throws CloneNotSupportedException {
//		         super.clone();
//		         Address address = new Address();
//		         address.setCity(this.city);
//		        
//		         return address;
//		    }
//		}
		
//		Address[] addresses = createAddressArray();
//		Address[] copiedArray = addresses.clone();
//		addresses[0].setCity(addresses[0].getCity() + "_Changed");
//		assertArrayEquals(copiedArray, addresses);
		
		//a change in the original or copied array will cause a change in the other, even if the enclosed objects are cloneable (with the clone() method overriden).
		//Anyhow, we can still write a copy constructor or provide deep copy factory (Effective java, Override clone judiciously, Joshua Bloch), but in practical contexts 
		//it is cumbersome to spend that much time on thinking how to create a clone, that's why external libraries are a good option.
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////					4					///////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		//Using the stream API.
		
		String[] array4 = {"orange", "red", "green'"};
		String[] copiedArray4 = Arrays.stream(array4).toArray(String[]::new);
		
		//for non-primitive types, it will also do a shallow copy.
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////					5				///////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//Apache Commons 3 offers a utility class called SerializationUtils that provides a clone(…) method. 
		//It is very useful if we need to do a deep copy of an array of non-primitive types ( no more shallow copies :) ). It can be downloaded from here and its Maven dependency is:

//			<dependency>
//			    <groupId>org.apache.commons</groupId>
//			    <artifactId>commons-lang3</artifactId>
//			    <version>3.11</version>
//			</dependency>
		
		//practical case:

//			public class Employee implements Serializable {
//			    // fields
//			    // standard getters and setters
//			}

//			Employee[] employees = createEmployeesArray();
//			Employee[] copiedArray = SerializationUtils.clone(employees);
//			employees[0].setName(employees[0].getName() + "_Changed");
//			assertFalse(
//			  copiedArray[0].getName().equals(employees[0].getName()));
		
		//This class requires that each object should implement the Serializable interface. 
		//In terms of performance, it is slower than the clone methods written manually for each of the objects in our object graph to copy.
		
	}
	
}
