import static org.junit.Assert.*;

import org.junit.Test;

public class InstructionTest {

	@Test
	public void test() {
		try {
			assertEquals("F must return Instruction.F", Instruction.F, Instruction.StringtoInstruction("F"));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void test2() {
		Instruction[] testArray = {Instruction.F, Instruction.L, Instruction.R, Instruction.F};
		String[] testStringArray = {"F","L","R","F"};
		try {
			
			assertArrayEquals("String array is converted correctly", testArray, Instruction.StringArraytoInstruction(testStringArray));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
