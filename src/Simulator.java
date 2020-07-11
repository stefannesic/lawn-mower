import java.util.List;

// executes interactions between mowers and lawn
public class Simulator {
	private Lawn lawn;
	private List<Mower> mowers;
	
	public Simulator(Lawn l, List<Mower> m) {
		this.lawn = l;
		this.mowers = m;
	}
	
	// each executor runs on a separate thread
	public class Executor extends Thread {
		private Mower mower;
		
		Executor(Mower m) {
		      this.mower = m;
		      System.out.println("Creating " +  m );
		}
	   
	    public void run() {
	    	System.out.println("Running " +  this.getMower() );
	  
	    	executeInstructions(this.getMower());
	    	
	    	System.out.println(this.getMower() + " exiting.");
	    }

		public Mower getMower() {
			return mower;
		}
	}
	
	// runs the simulation with mowers on lawn
	public void go() throws Exception { 
		Thread[] threads = new Thread[this.getMowers().size()];
		int i = 0;
		for(Mower m: this.getMowers()) {
			// check initials coordinates
			int x = m.getX();
			int y = m.getY();
			if (lawn.isValidMove(x, y))
				// place a mower on the lawn
				lawn.placeMower(x, y, m.getId());
			else 
				throw new Exception("Cannot intialize Mower...initial position is the same as that of another.");
			// execute its movements
			threads[i] = new Executor(m);
			threads[i].start();
			i++;
		}
		
		// wait until all threads have completed their tasks
		for (int j = 0; j < threads.length; j++) {
		        threads[j].join();
		}
		
		System.out.println("All Done");
		
		// display final positions of mowers in order of appearance
		for(Mower m: mowers) {
			System.out.println("Final Position..."+m);
		}
	}
	
	// controller associates instruction to proper method
	public void executeInstructions(Mower m) {
		Instruction[] instr = m.getInstructions();
		for(int i = 0; i < instr.length; i++) {
			switch(instr[i]) {
			case F:
				advance(m);
				break;
			case L:
				turnLeft(m);
				break;
			case R:
				turnRight(m);
				break;
			}
		}
	}
	
	// F instruction 
	public void advance(Mower m) {
		int x = 0, y = 0;
		
		// get current position
		int currentX = m.getX();
		int currentY = m.getY();
		
		switch(m.getOrientation()) {
		case N:
			y = 1;
			break;
		case E:
			x = 1;
			break;
		case S:
			y = -1;
			break;
		case W:
			x = -1;
			break;
		}
		// get planned position
		x = m.getX()+x;
		y = m.getY()+y;
		
		// check if planned position is possible
		// synchronized block here in case two or more try to move to the same cell
		synchronized(lawn) {
			if (lawn.isValidMove(x, y)) {
				System.out.println("FORWARD move started for "+m);
				// move on lawn
				lawn.moveMower(currentX, currentY, x, y, m.getId());
				// update position in object
				m.setX(x);
				m.setY(y);
				System.out.println("FORWARD move completed for "+m);
			} else {
				System.out.println("FORWARD move SKIPPED for "+m);
			}
		}
	}
	
	// L instruction
	public void turnLeft(Mower m){
		switch(m.getOrientation()) {
			case N:
				m.setOrientation(Direction.W);
				break;
			case E:
				m.setOrientation(Direction.N);
				break;
			case S:
				m.setOrientation(Direction.E);
				break;
			case W:
				m.setOrientation(Direction.S);
				break;
			default:
				break;
		}
		System.out.println("LEFT turn completed for "+m);
	}
	
	// R instruction
	public void turnRight(Mower m){
		switch(m.getOrientation()) {
			case N:
				m.setOrientation(Direction.E);
				break;
			case E:
				m.setOrientation(Direction.S);
				break;
			case S:
				m.setOrientation(Direction.W);
				break;
			case W:
				m.setOrientation(Direction.N);
				break;
			default:
				break;
		}
		System.out.println("RIGHT turn completed for "+m);
	}
	
	public List<Mower> getMowers() {
		return this.mowers;
	}
}
