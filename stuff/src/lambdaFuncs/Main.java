package lambdaFuncs;

public class Main {

    public static void main(String[] args){

        //Creation of chewbacca:
        Chewbacca chewbacca = new Chewbacca();

        //1. Normal implementation of chewbacca with interface
        chewbacca.print();

        //2. Use of the printSomething function, which utilizes the method
        //   print of objects that implement Printable interface
        printSomething(chewbacca);

        //3. Instead of sending an object that contains an action we send
        //   the object itself. This is a lambda function:
        printSomething(
             () -> {
                System.out.println("Not Chewbacca this time.");
            }
        );

        //4. Instead of sending an object that contains an action we send
        //   the object itself. This is a lambda function:
        printSomething(() -> System.out.println("Not Chewbacca this time."));

        //4. Instead of sending an object that contains an action we send
        //   the object itself. This is a lambda function:
        printSomething(() -> System.out.println("Not Chewbacca this time."));

        //5. Now we add a parameter to the lambda function, and have it return
        //   text + the parameter. The method printSomething and the Printable
        //   object have to reflect the change (PrintableWithSuffix created
        //   not to edit the Printable interface):
        printSomething((s) -> {
            System.out.println("Not Chewbacca this time" + s);
            return "Not Chewbacca this time" + s;
        });

        //6. This code does the same than the above, but it is clearer:
        printSomething( s -> "Not Chewbacca this time" + s );

        //The use of interfaces with lambda expressions here is possible because the
        //interfaces are so called "functional interfaces". This is a fancy name
        //for an interface that only has one function.

    }

    static void printSomething(Printable object){
        object.print();
    }

    static void printSomething(PrintableWithSuffix object){
        //this printable object now accepts parameters
        object.print(" ! ");
    }

}

