package threading;

public class SimpleThread {

	public SimpleThread() {
	
		Thread t = new Thread(new Runnable() {

	        @Override
	        public void run() {
	            for (int i = 10; i >= 0; i--) {
	                System.out.println(Thread.currentThread().getName() + " -> " + i);
	                try {
	                    Thread.sleep(1000);
	                } catch (InterruptedException e) {
	                    //
	                }
	            }

	            System.out.println(Thread.currentThread().getName() + " finalizado");
	        }
	    });

	    // Se debe iniciar el thread.
	    t.start();
		
	    for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " -> " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //
            }
        }
	    
	}
	
	public static void main(String args) {
		new SimpleThread();
	}
		
}
