
public class Mower {
	private int id;
	private int X;
	private int Y;
	private Direction orientation;
	private String[] instructions;
	
	public Mower(int id, int x, int y, Direction o, String[] i) {
		this.setId(id);
		this.X = x;
		this.Y = y;
		this.orientation = o;
		this.instructions = i;
	}
	
	public String toString() {
		return "Mower ID:"+id+", X:"+this.getX()+", Y:"+this.getY()+", O:"+this.getOrientation();
	}
	
	public int getX() {
		return this.X;
	}
	
	public int getY() {
		return this.Y;
	}
	
	public void setX(int x) {
		this.X = x;
	}
	
	public void setY(int y) {
		this.Y = y;
	}
	
	public Direction getOrientation() {
		return this.orientation;
	}
	
	public void setOrientation(Direction o) {
		this.orientation = o;
	}
	
	public String[] getInstructions() {
		return this.instructions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	
	public static Direction StringtoDirection(String d) throws Exception {
		switch (d) {
		case "N":
			return Direction.N;
		case "E":
			return Direction.E;
		case "S":
			return Direction.S;
		case "W":
			return Direction.W;
		default:
			throw new Exception("String does not correspond to a direction");
		}
	}
}
