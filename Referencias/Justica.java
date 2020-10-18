/* PUCRS - Programacao Concorrente - Fernando Dotti */

class Justica extends Thread {
    static volatile int n = 1;

    public void run() {
        System.out.println("Tentando!");
        n=-1;
        System.out.println("Justo!");
    }

    public static void main(String[] args) {
      Justica p = new Justica();
      p.start();

      while (n>0) {
		  n++;
		  System.out.println(".");
      }

      System.out.println("Fim");
    }	
}

