import java.io.File;  
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; 

public class Parser {
	private String dimensions[];
	private List<String[]> mowers;
	private List<String[]> instructions;
	private String filename;
	
	public Parser(String f) {
		this.filename = f;
		this.mowers = new ArrayList<String[]>();
		this.instructions = new ArrayList<String[]>();
	}
	
	public void Parse() throws Exception {
		try {
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
		    	  this.setDimensions(line.split(" "));
		      }
		      
		      // check if there is 
		      while (myReader.hasNextLine()) {
			      line = myReader.nextLine();

		    	  if(!this.validMower(line)) {
				      myReader.close();
			    	  throw new Exception("Mower is not valid."+line);
			      } else {
				      // read the mower position 
			    	  this.addMower(line.split(" "));
			      }
		
			      // check if there is final line
			      if (myReader.hasNextLine()) {
				      line = myReader.nextLine();
	
			    	  if(!this.validInstructions(line)) {
					      myReader.close();
				    	  throw new Exception("Instructions are not valid.");
				      } else {
					      // read the instructions 
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
		      
		    } catch (FileNotFoundException e) {
		    	System.out.println("File not found.");
		    	e.printStackTrace();
		    }
	}
	
	public List<String[]> getMowers() {
		return this.mowers;
	}
	
	private void addMower(String[] line) {
		this.mowers.add(line);
	}

	public boolean validDimensions(String line) {
        return line.matches("\\d+ \\d+");
    }
	
    public boolean validMower(String line) {
        return line.matches("\\d+ \\d+ [NESW]");
    }    
    
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


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public List<String[]> getInstructions() {
		return instructions;
	}


	public void setInstructions(String instructions[]) {
		this.instructions.add(instructions);

	}
}
