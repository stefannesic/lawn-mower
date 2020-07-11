import java.util.List;

public class Simulator {
	private Lawn lawn;
	private List<Mower> mowers;
	
	public Simulator(Lawn l, List<Mower> m) {
		this.lawn = l;
		this.mowers = m;
	}
	
	public void go() {
		for(Mower m: mowers) {
			int x = m.getX();
			int y = m.getY();
			// place a mower on the lawn
			if (lawn.isValidMove(x, y))
				lawn.placeMower(x, y, m.getId());
			// execute its movements
			executeInstructions(m);
		}
		
		// display final positions of mowers
		for(Mower m: mowers) {
			int x = m.getX();
			int y = m.getY();
			Direction o = m.getOrientation();
			
			System.out.println(x + " " + y + " " + o);
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
			m.setX(x);
			m.setY(y);
			
			lawn.placeMower(x, y, m.getId());
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
	}
}
