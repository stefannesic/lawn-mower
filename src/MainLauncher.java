
public class MainLauncher {

	public static void main(String[] args) {
		
		String filename = "instructions.txt";
		
		if (args.length > 0) {
			// use filename from argument
			filename = args[0];
			System.out.println(filename);
		}
		
		Parser myParser = new Parser(filename);
		try {
			myParser.Parse();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(myParser.getDimensions()[0]);
		System.out.println(myParser.getMowers().get(0)[0]);
		System.out.println(myParser.getInstructions().get(0)[0]);


	}

}
