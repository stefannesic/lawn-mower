
public class Mower {
	private int id;
	private int X;
	private int Y;
	private Direction orientation;
	private Instruction[] instructions;
	
	public Mower(int id, int x, int y, Direction o, Instruction[] i) {
		this.id = id;
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
	
	public Instruction[] getInstructions() {
		return this.instructions;
	}

	public int getId() {
		return id;
	}
}
