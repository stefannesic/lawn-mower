import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class SimulatorTest {

	@Test
	void testExecuteInstructions() {
		// example test case is executed correctly

		Lawn l = new Lawn(6,6);
		Instruction[] instructions =  {Instruction.L,
									   Instruction.F,
									   Instruction.L,
									   Instruction.F,
									   Instruction.L,
									   Instruction.F,
									   Instruction.L,
									   Instruction.F,
									   Instruction.F};
		
		Instruction[] instructions2 =  {Instruction.F,
									    Instruction.F,
									    Instruction.R,
									    Instruction.F,
									    Instruction.F,
									    Instruction.R,
									    Instruction.F,
									    Instruction.R,
									    Instruction.R,
									    Instruction.F};
		
		
		Mower m = new Mower(1, 1, 2, Direction.N, instructions);
		Mower m2 = new Mower(2, 3, 3, Direction.E, instructions2);
		
		Simulator s = new Simulator(l, null);
		l.placeMower(1, 2, 1);
		s.executeInstructions(m);
		l.placeMower(3, 3, 2);

		s.executeInstructions(m2);
		
		// test lawn has changed correctly
		assertTrue(l.isOccupied(1, 3));
		assertTrue(l.isOccupied(5, 1));

		assertFalse(l.isOccupied(0, 0));
		assertFalse(l.isOccupied(0, 1));
		assertFalse(l.isOccupied(1, 2));
		assertFalse(l.isOccupied(3, 3));
		assertFalse(l.isOccupied(3, 4));
		
		// test mowers contain correct position
		assertEquals(1, m.getX(), "Mower1 x position has been updated.");
		assertEquals(3, m.getY(), "Mower1 y position has been updated.");
		assertEquals(5, m2.getX(), "Mower2 x position has been updated.");
		assertEquals(1, m2.getY(), "Mower2 y position has been updated.");

		// test mowers orientation
		assertEquals(Direction.N, m.getOrientation(), "Mower1 orientation has been updated.");
		assertEquals(Direction.E, m2.getOrientation(), "Mower2 orientation has been updated.");
	}

	
	@Test
	void testAdvance() {
		Mower m = new Mower(1, 1, 2, Direction.W, new Instruction[0]);
		Mower m2 = new Mower(1, 0, 3, Direction.S, new Instruction[0]);

		Lawn l = new Lawn(5,5);
		l.placeMower(1, 2, 1);
		Simulator s = new Simulator(l, null);
		
		// mower correctly leaves old cell to a new one
		assertFalse(l.isOccupied(0, 2));
		s.advance(m);
		assertTrue(l.isOccupied(0, 2));
		assertEquals(0, m.getX(), "Mower x position has been updated.");
		assertEquals(2, m.getY(), "Mower y position has been updated.");
		
		// mower cannot leave grid
		s.advance(m);
		assertEquals(0, m.getX(), "Mower x position has stayed the same.");
		assertEquals(2, m.getY(), "Mower y position has stayed the same.");
		
		// mower cannot move in the cell of another
		l.placeMower(0, 3, 1);
		s.advance(m2);
		assertEquals(0, m2.getX(), "Mower x position has stayed the same.");
		assertEquals(3, m2.getY(), "Mower y position has stayed the same.");

	}

	@Test
	void testTurnLeft() {
		Mower m = new Mower(1, 1, 2, Direction.W, new Instruction[0]);
		Simulator s = new Simulator(null, null);
		s.turnLeft(m);
		s.turnLeft(m);
		s.turnLeft(m);
		assertEquals(Direction.N, m.getOrientation(), "Left turn works.");
		
	}

	@Test
	void testTurnRight() {
		Mower m = new Mower(1, 1, 2, Direction.W, new Instruction[0]);
		Simulator s = new Simulator(null, null);
		s.turnRight(m);
		s.turnRight(m);
		assertEquals(Direction.E, m.getOrientation(), "Right turn works.");
	}

}
