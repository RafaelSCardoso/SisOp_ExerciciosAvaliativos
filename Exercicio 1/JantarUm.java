import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class JantarUm {
    
    private int comeQuantos;
    public JantarUm(int i) { comeQuantos = i; }

    public void run() {
        int FIL = 5;

        System.out.println("Primeira ceia");
        
        Semaphore garfo[] = new Semaphore[FIL]; 
        for (int i=0; i< FIL; i++) {
            garfo[i]= new Semaphore(1); 
        }

        // Usando o ExecutorSevice para garantir que a ceia
        // vai terminar antes de voltar ao main e chamar a
        // segunda ceia. 
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < FIL; i++) {
            es.execute(new Filosofo(i,garfo[i],garfo[(i+1)%(FIL)]));	
        }
        // Isso faz com que o Executor não aceite mais runnables
        es.shutdown();
        
        // Aqui espera até todos terminarem. 
        try { es.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); }
        catch (InterruptedException e) {}
    }


    // ============= Filosofo ==============
    class Filosofo extends Thread {
        
        private int i;
        private Semaphore g1, g2;
        private String espaco;

        public Filosofo(int _i, Semaphore _g1, Semaphore _g2){		
            i = _i;   g1 = _g1;    g2 = _g2;	
            espaco = "|";
            for (int k=0; k<i; k++){
                espaco = espaco + "\t\t\t|";
            }
        }

        /*
            O filosofo pega o primeiro garfo. Quando tenta pegar o segundo garfo
            e algo o impede por mais de 100 milisegundos, o mesmo solta o primeiro
            garfo que havia pego, para que então seus colegas possam usar e lhe dar
            a chance posteriormente.
        */
        public void run() {
            for(int comeu = 0; comeu < comeQuantos;) {
                // pensa
                System.out.println(espaco+ i + ": Pensa ");
                
                // pega um garfo
                try{ g1.acquire(); }
                catch(InterruptedException ie){}
                System.out.println(espaco+ i + ": Pega um");

                try{if(g2.tryAcquire(100, TimeUnit.MILLISECONDS) == false){
                        g1.release();
                        System.out.println(espaco+ i + ": Solta um");
                    } else {
                        // come
                        // solta garfos
                        comeu++;
                        System.out.println(espaco+ i + ": Pega dois, come");
                        g1.release();
                        g2.release();
                    }
                }
                catch(InterruptedException ie){}
   
                // come
                // solta garfos
                // g1.release();
                // g2.release();
            }
            System.out.println(espaco+ i + ": Encheu o bucho.");
        }
    }

}
