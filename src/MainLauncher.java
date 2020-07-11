import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		
		// initialize simulator
		
		// create lawn
		int width = Integer.parseInt(myParser.getDimensions()[0])+1;
		int height =  Integer.parseInt(myParser.getDimensions()[0])+1;
		Lawn l = new Lawn(width, height);
		
		// create list of mowers
		List<Mower> mowers= new ArrayList<Mower>();
		
		Iterator<String[]> iterator = myParser.getMowers().iterator();
		int counter = 1;
	    while(iterator.hasNext()) {
	    	int id =  counter;
	    	String[] mowerString = iterator.next();
	    	
	    	// convert orientation to Direction type
	    	try {
	    		
	    		Direction d = Mower.StringtoDirection(mowerString[2]);
	    		Mower m = new Mower(id, 
						Integer.parseInt(mowerString[0]), 
						Integer.parseInt(mowerString[1]), 
						d, 
						myParser.getInstructions().get(counter-1));
	    		
	    		mowers.add(m);
				counter++;
	    	}
	    	catch (Exception e) {
	    		e.printStackTrace();
	    	}			
	    }
	    
	    // create simulator
	    Simulator s = new Simulator(l, mowers);
	    
	    // execute simulation
	    s.go();

	}

}
