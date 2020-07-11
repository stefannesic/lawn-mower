import static org.junit.Assert.*;

import org.junit.Test;

public class LawnTest {

	@Test
	public void testIsOccupied() {
		Lawn l = new Lawn(5, 4);
		l.placeMower(2, 3, 4);
		assertTrue(l.isOccupied(2, 3));
		assertFalse(l.isOccupied(0, 0));
	}

	@Test
	public void testIsCell() {
		Lawn l = new Lawn(3, 3);
		assertTrue(l.isCell(2, 2));
		assertFalse(l.isCell(3, 4));
	}

	@Test
	public void testIsValidMove() {
		Lawn l = new Lawn(2, 2);
		l.placeMower(0, 0, 1);
		l.placeMower(1, 0, 2);
		assertTrue(l.isValidMove(1, 1));
		assertFalse(l.isValidMove(1, 0));
		assertFalse(l.isValidMove(4, 4));
	}

	@Test
	public void testMoveMower() {
		Lawn l = new Lawn(2, 2);
		l.placeMower(0, 0, 8);
		l.moveMower(0, 0, 1, 0, 8);
		assertTrue(l.isOccupied(1, 0));
		assertFalse(l.isOccupied(0, 0));
	}

}
