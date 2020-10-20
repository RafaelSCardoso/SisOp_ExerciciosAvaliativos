import java.util.concurrent.Semaphore;

public class Main {

	public static final int writers = 10;
	public static final int readers = 5;

	public static final int times = -1;

    public static void main(String[] args) {

    	for(int i = 0; i < writers; i++)
	    	new Writer().start();
	 	for(int i = 0; i < readers; i++)
	    	new Reader().start();   
    }
}