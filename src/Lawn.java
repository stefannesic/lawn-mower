public class Lawn {
	private int width;
	private int height;
	private int[][] grid;
	
	public Lawn(int w, int h) {
		this.setWidth(w);
		this.setHeight(h);
		this.grid = new int[width][height];
	}
	
	
	public Boolean isOccupied(int x, int y) {
		if (grid[x][y] != 0) {
			return true;
		}
		else 
			return false;
	}
	
	public Boolean isCell(int x, int y) {
		if (x < width && y < height && x >= 0 && y >= 0)
			return true;
		else 
			return false;
	}
	
	public Boolean isValidMove(int x, int y) {
		if (isCell(x,y) && !isOccupied(x,y))
			return true;
		else
			return false;
	}

	public int getWidth() {
		return width;
	}



	public void setWidth(int width) {
		this.width = width;
	}



	public int getHeight() {
		return height;
	}



	public void setHeight(int height) {
		this.height = height;
	}
	
	public void placeMower(int x, int y, int id) {
		grid[x][y] = id;
	}
	
	public void removeMower(int x, int y) {
		grid[x][y] = 0;
	}
	
	public synchronized void moveMower(int oldX, int oldY, int newX, int newY, int id) {
		this.removeMower(oldX, oldY);
		this.placeMower(newX, newY, id);
	}
	
	
}
