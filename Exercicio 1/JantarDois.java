import java.util.concurrent.Semaphore;

public class JantarDois {
    
    private int comeQuantos;
    public JantarDois(int i) { comeQuantos = i; }

    public void run() {
        int FIL = 5;

        System.out.println("\nSegunda ceia");
            
        Semaphore garfo[] = new Semaphore[FIL]; 
        for (int i=0; i< FIL; i++) {
            garfo[i]= new Semaphore(1); 
        }
        for (int i = 0; i < FIL; i++) {
            (new Filosofo(i,garfo[i],garfo[(i+1)%(FIL)])).start();
        }
        
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
            Caso o próximo garfo não esteja disponível, o filosofo só aguarda.
        */
        public void run() {
            for(int comeu = 0; comeu < comeQuantos; comeu++) {
                // pensa
                System.out.println(espaco+ i + ": Pensa ");
                
                // pega um garfo
                try{if (g2.availablePermits() == 0){
                        g1.acquire();
                        System.out.println(espaco+ i + ": Pegou um ");
                    }
                }
                catch(InterruptedException ie){}
                // pega outro garfo
                try{ g2.acquire();
                    System.out.println(espaco+ i + ": Pegou dois, come ");	
                }
                catch(InterruptedException ie){}
                        	   
                // come
                // solta garfos
                g1.release();
                g2.release();
            }
            System.out.println(espaco+ i + ": De panca cheia.");
        }
    }

}
