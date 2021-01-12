package threading;

public class RunnableThread implements Runnable{

	public RunnableThread() {
		//iterate();
	}
	
	@Override
	public void run() {
		
		for (int i = 0; i < 1000000; i++) {
			System.out.println("adios");
		}
		
	}
	
	public void iterate() {
		for (int i = 0; i < 1000000; i++) {
			System.out.println("Hola");
		}
	}
	
	public static void main(String[] args) {
		RunnableThread rt;
		Thread t = new Thread(rt = new RunnableThread());
        t.start();
        rt.iterate();
	}

	
}
