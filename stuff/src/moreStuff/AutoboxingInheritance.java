package moreStuff;

class A{
	void method(int i) {
		System.out.println("A1 int");
	}	
}

class B extends A{
	
	//can't @Override because int is treated different than Integer.
	void method(Integer i) {
		System.out.println("B1 Integer");
	}	
}

public class AutoboxingInheritance {

	@SuppressWarnings("deprecation")
	
	public static void main(String[] args) {
		A a = new B();
		a.method(new Integer(10));	//autoboxes the integer to int and uses the A class method. Java uses the method in A first, thats why the Integer gets converted to int and then the method is used.
		a.method(10);				//uses the A method because its a primitive type int.
		
		B b = new B();
		b.method(new Integer(10));	//uses the B method not to autobox.
		b.method(10);				//uses the B
	}
	
}

//courtesy of journaldev.com website