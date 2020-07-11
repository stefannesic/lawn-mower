import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainLauncher {

	public static void main(String[] args) {
		
		// default file to run 
		String filename = "tests/instructions8.txt";
		
		// use filename from argument if there is one
		if (args.length > 0) {
			filename = args[0];
			System.out.println(filename);
		}
		
		// parse input file
		Parser myParser = new Parser(filename);
		try {
			myParser.Parse();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		// initialize simulator
		
		// create lawn and mowers
		
		// add one since the dimensions are really the coordinates of the upper right cell
		int width = Integer.parseInt(myParser.getDimensions()[0])+1;
		int height =  Integer.parseInt(myParser.getDimensions()[1])+1;
		Lawn l = new Lawn(width, height);
		
		List<Mower> mowers= new ArrayList<Mower>();
		
		Iterator<String[]> iterator = myParser.getMowers().iterator();
		int counter = 1;
	    while(iterator.hasNext()) {
	    	int id =  counter;
	    	String[] mowerString = iterator.next();
	    	
	    	try {
	    		// convert direction to the Direction type
	    		Direction d = Direction.StringtoDirection(mowerString[2]);
	    		
	    		// convert instructions to Instruction array
				// counter starts at one above the lowest element
	    		Instruction[] i = Instruction.StringArraytoInstruction(myParser.getInstructions().get(counter-1));
	    		Mower m = new Mower(id, 
							Integer.parseInt(mowerString[0]), 
							Integer.parseInt(mowerString[1]), 
							d, 
							i);
	    		
	    		mowers.add(m);
				counter++;
	    	}
	    	catch (Exception e) {
	    		e.printStackTrace();
	    		System.exit(-1);
	    	}			
	    }
	    
	    // create simulator
	    Simulator s = new Simulator(l, mowers);
	    
	    try {
		    // execute simulation
			s.go();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

}
