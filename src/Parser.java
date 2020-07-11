import java.io.File;  
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; 

public class Parser {
	private String dimensions[];
	private List<String[]> mowers;
	private List<String[]> instructions;
	private final String filename;
	
	public Parser(String f) {
		this.filename = f;
		this.mowers = new ArrayList<String[]>();
		this.instructions = new ArrayList<String[]>();
	}
	
	public void Parse() throws Exception {
	
		  // open file with path of the filename variable
	      File myFile = new File(this.getFilename());
	      
	      Scanner myReader = new Scanner(myFile);
	      
	      // check if file is empty
	      if (myFile.length() == 0) {
		      myReader.close();
	          throw new Exception("File is empty.");
	      }
	      
	      // read the first line and set the dimensions
	      String line = myReader.nextLine();
	      if(!this.validDimensions(line)) {
		      myReader.close();
	    	  throw new Exception("Dimensions on first line are not valid.");
	      } else {
	    	  // split the dimensions using the space character into an array 
	    	  this.setDimensions(line.split(" "));
	      }
	      
	      // while there is a line to be read
	      while (myReader.hasNextLine()) {
		      line = myReader.nextLine();
		      
		      // check if mower is valid
	    	  if(!this.validMower(line)) {
			      myReader.close();
		    	  throw new Exception("Mower is not valid."+line);
		      } else {
			      // read the mower position by splitting again on the space character
		    	  this.addMower(line.split(" "));
		      }
	
		      // check if there are instructions to accompany it
		      if (myReader.hasNextLine()) {
			      line = myReader.nextLine();

			      // check if instructions are valid
		    	  if(!this.validInstructions(line)) {
				      myReader.close();
			    	  throw new Exception("Instructions are not valid.");
			      } else {
				      // read the instructions by splitting the characters into a string array
			    	  this.setInstructions(line.split("(?!^)"));
			      }
		      } else {
			      myReader.close();
		    	  throw new Exception("Instructions for a mower are missing.");
		      }
	      }
	      
	      myReader.close();

	      // check if at least one mower was set
	      if (mowers == null || mowers.size() == 0) 
    	  	  throw new Exception("A mower is missing.");
		      
	}
	
	public List<String[]> getMowers() {
		return this.mowers;
	}
	
	private void addMower(String[] line) {
		this.mowers.add(line);
	}

	// checks if dimensions are a digit followed by another 
	public boolean validDimensions(String line) {
        return line.matches("\\d+ \\d+");
    }
	
	// checks if dimensions are two digits followed by the letters N, E, S or W
    public boolean validMower(String line) {
        return line.matches("\\d+ \\d+ [NESW]");
    }    
    
    // checks if instructions are a sequence of the following characters: L, R, F
    public boolean validInstructions(String line) {
    	return line.matches("[LRF]+");
    }

	public String[] getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions[]) {
		this.dimensions = dimensions;
	}


	public String getFilename() {
		return filename;
	}

	public List<String[]> getInstructions() {
		return instructions;
	}


	public void setInstructions(String instructions[]) {
		this.instructions.add(instructions);

	}
}
