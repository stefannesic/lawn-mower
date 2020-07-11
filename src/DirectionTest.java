import static org.junit.Assert.*;

import org.junit.Test;

public class DirectionTest {

	@Test
	public void testStringtoDirection() {
		try {
			assertEquals("N must return Direction.N", Direction.N, Direction.StringtoDirection("N"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
