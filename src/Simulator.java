import java.util.List;

public class Simulator {
	private Lawn lawn;
	private List<Mower> mowers;
	
	public Simulator(Lawn l, List<Mower> m) {
		this.lawn = l;
		this.mowers = m;
	}
	
	public class Executor extends Thread {
		private Thread t;
		private Mower mower;
		
		Executor(Mower m) {
		      this.setMower(m);
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
	
		public void setMower(Mower mower) {
			this.mower = mower;
		}
		
		
	}
	
	public void go() throws Exception { 
		Thread[] threads = new Thread[this.getMowers().size()];
		int i = 0;
		for(Mower m: this.getMowers()) {
			int x = m.getX();
			int y = m.getY();
			// place a mower on the lawn
			if (lawn.isValidMove(x, y))
				lawn.placeMower(x, y, m.getId());
			else 
				throw new Exception("Cannot intialize Mower...initial position is the same as that of another.");
			// execute its movements
			threads[i] = new Executor(m);
			threads[i].start();
			i++;
		}
		
		for (int j = 0; j < threads.length; j++) {
		        threads[j].join();
		}
		System.out.println("All Done");
		// display final positions of mowers
		for(Mower m: mowers) {
			System.out.println("Final Position..."+m);
		}
	}
	
	public void executeInstructions(Mower m) {
		for(String i: m.getInstructions()) {
			// perhaps convert character to enum type
			switch(i) {
			case "F":
				advance(m);
				break;
			case "L":
				turnLeft(m);
				break;
			case "R":
				turnRight(m);
				break;
			}
		}
	}
	
	public void advance(Mower m) {
		int x = 0, y = 0;
		
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
		x = m.getX()+x;
		y = m.getY()+y;
		
		
		if (lawn.isValidMove(x, y)) {
			System.out.println("FORWARD move started for "+m);
			lawn.moveMower(currentX, currentY, x, y, m.getId());
			m.setX(x);
			m.setY(y);
			System.out.println("FORWARD move completed for "+m);
		} else {
			System.out.println("FORWARD move SKIPPED for "+m);
		}
	}
	
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
