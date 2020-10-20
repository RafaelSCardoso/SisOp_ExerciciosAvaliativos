import java.util.concurrent.Semaphore;

public class Producer extends Thread{
 
    private static final String espaco = "";
    private Buffer buffer;
    private Semaphore smp;

    public Producer (Buffer buffer, Semaphore smp){
        this.buffer = buffer;
        this.smp = smp;
    }

    public void to(Consumer consumer){
    }

    @Override
    public void run() {
        System.out.println(espaco + "Começa a produzir...");            
        boolean still = false;

        for(int produz = 0; produz < Main.pacotes;) {
            
            // Solicita acesso ao buffer        
            try{ smp.acquire(); }
            catch(InterruptedException ie){}

            // Se o buffer não for capaz de receber, volta a
            // esperar até que o buffer esteja livre
            if(!buffer.isFull()){
                buffer.insere();
                System.out.println(espaco + "insere -> buffer");
                produz++;
                still = false;
            } else if(!still){
                System.out.println(espaco + " Aguardando buffer [cheio]");
                still=true;
            }

            // Libera o acesso ao buffer
            smp.release();
        }
    }
}
