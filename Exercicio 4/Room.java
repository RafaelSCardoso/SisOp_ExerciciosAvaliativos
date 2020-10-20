import java.util.concurrent.Semaphore;

public class Room {

	private static Room instance;
	private int people;

	private Semaphore writersSmp;
	private Semaphore readersSmp;
	private Semaphore doorKnob;
	
	private boolean writerIn;
	private boolean readerIn;

	public Recurso recurso;

	public static Room getInstance(){
		if(instance == null)
			instance = new Room();
		return instance;
	}

	private Room(){
		recurso = new Recurso();
		this.writersSmp = new Semaphore(Main.writers);
		this.readersSmp = new Semaphore(Main.readers);
		this.doorKnob = new Semaphore(1);
		writerIn = readerIn = false;
	}


	public boolean isLocked(Person p){
		if(p instanceof Writer) return writerIn;
		if(p instanceof Reader) return readerIn;
		throw new IllegalArgumentException();

	}

	public void lock(Person p){
		if(p instanceof Writer) writerIn = true;
		if(p instanceof Reader) readerIn = true;
		System.out.println("The ligths were turned on.");
	}
	public void unlock(Person p){
		p.leave();
		if(p instanceof Writer) if(lightSwitch(writersSmp, Main.writers)) writerIn = false;
		if(p instanceof Reader) if(lightSwitch(readersSmp, Main.readers)) readerIn = false;
	}
	private boolean lightSwitch(Semaphore smp, int total){
		if(smp.availablePermits() == total){
			doorKnob.release();
			System.out.println("No one left in the room. Turning down lights.");
			return true;
		}		
		return false;
	}

	public Semaphore getDoorAccessControl(){ return doorKnob; }
	public Semaphore signEntryControl(Person p){
		if(p instanceof Writer) return writersSmp;
		if(p instanceof Reader) return readersSmp;
		throw new IllegalArgumentException();
	}
}

class Recurso {

	private String recurso;

	public Recurso(){ recurso = ""; }

	public String leitura(){
		return recurso;
	}

	public void escrita( String in ){
		recurso += in;
	}

	public void borracha(){ recurso = ""; }
}