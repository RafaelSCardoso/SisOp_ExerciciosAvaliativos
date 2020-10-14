package trab;

import java.util.Random;

public class Barreiras {
    private int MATRIZ_SIZE = 5;
    private int MAX_RANDOM = 51;
    private double [][] matriz = null;

    public Barreiras(){
        Random random = new Random();
        matriz = new double [MATRIZ_SIZE][MATRIZ_SIZE]; 
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = random.nextInt(MAX_RANDOM);
            }
        }
    }
    public void run(){



        do{

        }while();
    }

    public boolean isValid(int i, int j){
        if( i < 0    || i > matriz.length 
            || j < 0 || j > matriz.length)
                return false;
        return true;
    }
    /**
     * Thread
     */
    public class Thread{
        public int i;
        public int j;

        public Thread(int i, int j){
            this.i = i;
            this.j = j;
        }
    
        
    }


}
