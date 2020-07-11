public class Lawn {
	private int width;
	private int height;
	private int[][] grid;
	
	public Lawn(int w, int h) {
		this.width = w;
		this.height = h;
		this.grid = new int[width][height];
	}
	
	// checks if another mower is present on the cell
	public boolean isOccupied(int x, int y) {
		if (grid[x][y] != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	// checks if coordinates are valid
	public boolean isCell(int x, int y) {
		if (x < width && y < height && x >= 0 && y >= 0) {
			return true;
		}
		else 
			return false;
	}
	
	public boolean isValidMove(int x, int y) {
		if (isCell(x,y) && !isOccupied(x,y))
			return true;
		else
			return false;
	}
	
	// places a mower on cell 
	public void placeMower(int x, int y, int id) {
		grid[x][y] = id;
	}
	
	// removes a mower from a cell
	public void removeMower(int x, int y) {
		grid[x][y] = 0;
	}
	
	// moves mower from one cell to another
	public void moveMower(int oldX, int oldY, int newX, int newY, int id) {
		this.removeMower(oldX, oldY);
		this.placeMower(newX, newY, id);
	}
	
	
}
