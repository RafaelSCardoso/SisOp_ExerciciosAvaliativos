import java.util.concurrent.Semaphore;

public class Main extends Thread{

    public final static int pacotes = 50;
    public static final int bSize = 20;
     
    public static void main(String[] args)
    throws InterruptedException {
        new Main().run();
    }

    @Override
    public void run() {
        Semaphore smp = new Semaphore(1);
        Buffer buffer = new Buffer(bSize);
        Producer producer = new Producer(buffer, smp);
        Consumer consumer = new Consumer(buffer, smp);
        System.out.println("PRODUTOR \t\t\t\t\t| CONSUMIDOR");
        System.out.println("___________________________________________________________________________________");
        producer.start();
        consumer.start();
    }

}