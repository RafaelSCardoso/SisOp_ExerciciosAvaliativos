/* PUCRS - Programacao Concorrente - Fernando Dotti */

class Contadora extends Thread {
	private static int N = 100000;
    private int id;
    private String s;
	
    public Contadora(int _id, String _s){
    	id = _id;
 		s = _s;
    }
    public void run() {
       for (int i = 0; i < N; i++) {
	    System.out.println(s + " id: "+id+" -> "+i);   
        i++;
      }
    }
}

class TesteCria {
    public static void main(String[] args) {
      Contadora p = new Contadora(1, " ");
      Contadora q = new Contadora(2, "                    ");
      Contadora r = new Contadora(3, "                                        ");
      Contadora s = new Contadora(4, "                                                            ");
	  
	  p.start();
      q.start();
	  
      r.start();
      s.start();
   
      System.out.println("Fim");
    }
}

// Questoes a observar:
// 1) estruturas internas: PCB, stack, area do processo

// 2) inicio da thread - imediatamente depois a start();

// 3) escalonador - escolha arbitraria da thread que vai executar
//          nao podemos assumir NADA sobre velocidade relativa das threads
//          exceto JUSTICA: se uma thread esta continuamente habilitada a executar
//                          em algum momento o escalonador vai escolhe-la para execucao

// 3) esperar final
//  try { p.join(); q.join(); }
//      catch (InterruptedException e) { }

// 4) compartilhamento: variaveis de escopo global aas trhreads    
//    sao compartilhadas



