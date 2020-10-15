import java.util.ArrayList;
import java.util.Random;




public class Barreiras {
    private final static int MATRIZ_SIZE = 5;
    private static int MAX_RANDOM = 51;
    private static double [][] matriz = null;
    private static ArrayList fila = new ArrayList<>();

    public Barreiras() {
        Random random = new Random();
        matriz = new double[MATRIZ_SIZE][MATRIZ_SIZE];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = random.nextInt(MAX_RANDOM);
            }
        }
    }

    public void run() {
        Thread[][] matrizThread = new Thread[MATRIZ_SIZE][MATRIZ_SIZE];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matrizThread[i][j] = new Thread(i, j, matriz[i][j]);
            }
        }
        print();

        do {
            System.out.println("Calculando...");
        } while (!fila.isEmpty()); // todos os threads ja calculados
        

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = matrizThread[i][j].media;
            }
        }
        print();

    }

    public void print() {
        String print = "";
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.printf("%2.2f", matriz[i][j]);
                System.out.printf(" | ");
            }
            System.out.println();
        }
        
    }

    public static boolean isValid(int i, int j) {
        if (i>=0 && i<matriz.length && j>=0 && j<matriz[0].length)
            return true;

        return false;
    }

    /**
     * Thread
     */
    public static class Thread {
        public int i;
        public int j;
        public double valor;
        public double media;

        public Thread(int i, int j, double valor) {
            this.i = i;
            this.j = j;
            this.valor = valor;
            calcula();
        }

        public void calcula() {
            int count = 0;
            double soma = 0;
            this.addFila();

            // calcula a media
            for (int i = this.i - 1; i <= this.i + 1; i++) {
                for (int j = this.j - 1; j <= this.j + 1; j++) {
                    if (isValid(i, j)) {
                        count++;
                        soma += matriz[i][j];
                    }
                }
            }
            this.media = soma / count;

            this.tiraFila();
        }

        public void addFila() {
            fila.add(this);
        }

        public void tiraFila(){
            fila.remove(this);
        }
    
        
    }


}
