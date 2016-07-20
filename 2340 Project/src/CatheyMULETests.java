import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This class tests the MULE class
 * 
 * @author Cathey Chapman
 * 
 */
public class CatheyMULETests {

	/**
	 * Tests that the type of the MULE is set to FOOD
	 */
	@Test
	public void testgetType1() {
		Player p = new Player(0, 0);
		MULE m = new MULE(p, 1);

		assertEquals(MuleType.FOOD, m.getType());
	}

	/**
	 * Tests that the type of the MULE is set to ORE
	 */
	@Test
	public void testgetType2() {
		Player p = new Player(0, 0);
		MULE m = new MULE(p, 2);

		assertEquals(MuleType.ORE, m.getType());
	}
	
	/**
	 * Tests that the type of the MULE is set to ENERGY
	 */
	@Test
	public void testgetType3() {
		Player p = new Player(0, 0);
		MULE m = new MULE(p, 3);

		assertEquals(MuleType.ENERGY, m.getType());
	}
    
	/**
	 * Tests that the owner of the MULE is set correctly
	 */
	@Test
	public void testgetOwner() {
		Player p = new Player(0, 0);
		MULE m = new MULE(p, 1);

		assertEquals(p, m.getOwner());
	}

}
