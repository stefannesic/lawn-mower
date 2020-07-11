
public enum Direction {
	N, E, S, W;
	
	public static Direction StringtoDirection(String d) throws Exception {
		switch (d) {
		case "N":
			return N;
		case "E":
			return E;
		case "S":
			return S;
		case "W":
			return W;
		default:
			throw new Exception("String does not correspond to a direction");
		}
	}
}
