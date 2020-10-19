import java.util.concurrent.Semaphore;

public class Consumer extends Thread{
    
    private static final String espaco = "\t\t\t\t\t\t|";
    private Buffer buffer;
    private Semaphore smp;
    
    public Consumer (Buffer buffer, Semaphore smp){
        this.buffer = buffer;
        this.smp = smp;
    }

    @Override
    public void run() {
        System.out.println(espaco + "Começa a consumir...");
        boolean still = false;
        for(int consome = 0; consome < Main.pacotes;) {
            // Solicita acesso ao buffer
            try{ smp.acquire(); }
            catch(InterruptedException ie){}
            
            // Se o buffer não for capaz de receber o valor, volta
            // a esperar até que o buffer esteja livre
            if(!buffer.isEmpty()){
                buffer.retira();
                System.out.println(espaco + "buffer -> retira");
                consome++;
                still = false;
            } else if (!still){
                System.out.println(espaco + " Aguardando buffer [vazio]");
                still=true;
            }

            // Libera acesso ao buffer
            smp.release();
        }
    }

}
