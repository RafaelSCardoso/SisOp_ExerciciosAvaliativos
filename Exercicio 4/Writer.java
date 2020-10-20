import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Writer
	extends Thread
	implements Person {

	private static int serialId = 0;

	private int id;
	private Room room;
	private Semaphore doorKnob;
	private Semaphore entryBook;

	public Writer() {
		this.room = Room.getInstance();
		doorKnob = room.getDoorAccessControl();
		entryBook = room.signEntryControl(this);
		id = serialId;
		serialId++;
	}

	@Override
	public void run(){
		int times = Main.times;
		do{
			if(room.isLocked(this)){	
				try {
					if(doorKnob.tryAcquire(10, TimeUnit.NANOSECONDS))
						room.lock(this);
				} catch(InterruptedException ie) {}
			} else {
				try{ entryBook.acquire(); System.out.println("-> "+toString()+" join the Room."); }
				catch(InterruptedException ie ) {}

				System.out.println(toString() + " is Writing.");
				room.recurso.escrita(toString() + ": Writed here.\n");
		
				times--;	
				room.unlock(this);
			}

		}while(times != 0);
	}


	public void leave(){
		entryBook.release();
		System.out.println("<- Writer left the room");
	}

	@Override
	public String toString(){
		return "Writer " + id; 
	}
}