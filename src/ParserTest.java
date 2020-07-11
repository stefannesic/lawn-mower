import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ParserTest {

	@Test
	void testParse() {
		Exception exception = assertThrows(Exception.class, () -> {
	        Parser p = new Parser("toto");
	        p.Parse();
	    });
	 
	    String expectedMessage = "toto (No such file or directory)";
	    String actualMessage = exception.getMessage();	 
	    assertTrue(actualMessage.contains(expectedMessage));
	    
		exception = assertThrows(Exception.class, () -> {
	        Parser p = new Parser("tests/empty.txt");
	        p.Parse();
	    });
	 
	    expectedMessage = "File is empty.";
	    actualMessage = exception.getMessage();	 
	    assertTrue(actualMessage.contains(expectedMessage));
	    
	    exception = assertThrows(Exception.class, () -> {
	        Parser p = new Parser("tests/invalidDim.txt");
	        p.Parse();
	    });
	 
	    expectedMessage = "Dimensions on first line are not valid.";
	    actualMessage = exception.getMessage();	 
	    assertTrue(actualMessage.contains(expectedMessage));
	    
	    exception = assertThrows(Exception.class, () -> {
	        Parser p = new Parser("tests/invalidMower.txt");
	        p.Parse();
	    });
	 
	    expectedMessage = "Mower is not valid.";
	    actualMessage = exception.getMessage();	 
	    assertTrue(actualMessage.contains(expectedMessage));
	    
	    exception = assertThrows(Exception.class, () -> {
	        Parser p = new Parser("tests/invalidInstruct.txt");
	        p.Parse();
	    });
	 
	    expectedMessage = "Instructions are not valid.";
	    actualMessage = exception.getMessage();	 
	    assertTrue(actualMessage.contains(expectedMessage));
	    
	    exception = assertThrows(Exception.class, () -> {
	        Parser p = new Parser("tests/noInstruct.txt");
	        p.Parse();
	    });
	 
	    expectedMessage = "Instructions for a mower are missing.";
	    actualMessage = exception.getMessage();	 
	    assertTrue(actualMessage.contains(expectedMessage));
	    
	    
	    exception = assertThrows(Exception.class, () -> {
	        Parser p = new Parser("tests/noMower.txt");
	        p.Parse();
	    });
	 
	    expectedMessage = "A mower is missing.";
	    actualMessage = exception.getMessage();	 
	    assertTrue(actualMessage.contains(expectedMessage));
	    
	    Parser p = new Parser("tests/simple.txt");
	    try {
			p.Parse();
			
			String[] testDim = {"4", "4"};
			String[] testMower1 = {"1", "2", "W"};
			String[] testMower2 = {"3", "1", "N"};
			String[] testInstruct1 = {"L", "R", "F"};
			String[] testInstruct2 = {"F", "L", "R"};
			
			assertArrayEquals(testDim, p.getDimensions(), "Dimensions are parsed correctly.");
			assertArrayEquals(testMower1, p.getMowers().get(0), "Mower 1 is parsed correctly.");
			assertArrayEquals(testMower2, p.getMowers().get(1), "Mower 2 is parsed correctly.");
			assertEquals(2, p.getMowers().size(), "There are two mowers.");
			
			assertArrayEquals(testInstruct1, p.getInstructions().get(0), "Instruct 1 is parsed correctly.");
			assertArrayEquals(testInstruct2, p.getInstructions().get(1), "Instruct 2 is parsed correctly.");
			assertEquals(2, p.getInstructions().size(), "There are two instructions.");

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    
	    
		
	}

	@Test
	void testValidDimensions() {
		assertTrue(Parser.validDimensions("5 3"));
		assertTrue(Parser.validDimensions("3 3"));
		assertFalse(Parser.validDimensions("5 3 "));
		assertFalse(Parser.validDimensions("5 w"));
		assertFalse(Parser.validDimensions("/ *"));		
		assertFalse(Parser.validDimensions(" "));
	}

	@Test
	void testValidMower() {
		assertTrue(Parser.validMower("5 3 N"));
		assertTrue(Parser.validMower("3 3 W"));
		assertTrue(Parser.validMower("3 3 E"));
		assertTrue(Parser.validMower("0 0 S"));
		assertFalse(Parser.validMower("5 3 H"));
		assertFalse(Parser.validMower("5 w N"));
		assertFalse(Parser.validMower("/"));		
		assertFalse(Parser.validMower("N 5 3"));	
	}
	
	@Test
	void testValidInstructions() {
		assertTrue(Parser.validInstructions("LRLRFFFFLRL"));
		assertTrue(Parser.validInstructions("L"));
		assertTrue(Parser.validInstructions("FFFFFFFFFFFFF"));
		assertTrue(Parser.validInstructions("LRRFLLRRF"));
		assertFalse(Parser.validInstructions("L R"));
		assertFalse(Parser.validInstructions("LUDF"));
		assertFalse(Parser.validInstructions("LRRRL "));		
		assertFalse(Parser.validInstructions(" RLRLRL"));	
	}


}
