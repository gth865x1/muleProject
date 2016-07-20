import static org.junit.Assert.*;

import org.junit.Test;


public class PlayerTestKhameron {

	/**
	 * Tests that Player money transactions work correctly
	 */
	@Test
	public void test1() {
		Player p = new Player(0, 0);
		p.changeMoney(1000);
		assertEquals(1000, p.getMoney());
	}

	/**
	 * Tests that the Player name gets set correctly
	 */
	@Test
	public void test2() {
		Player p = new Player(0, 0);
		String name = "khameron";
		p.setName(name);
		assertEquals(name, p.getName());
	}
	
	/**
	 * Tests that MULEs are set to players correctly
	 */
	@Test
	public void test3() {
		Player p = new Player(0, 0);
		MULE m = new MULE(p, 3);
		p.setMule(m);
		assertEquals(m, p.getMule());
	}
    
	/**
	 * Tests that colors are set to a player correctly
	 */
	@Test
	public void test4() {
		Player p = new Player(0, 0);
		int color = 1;
		p.setColor(color);
		assertEquals(color, p.getColor());
	}
	/**
	 * Tests that races are set to a player correctly
	 */
	@Test
	public void test5() {
		Player p = new Player(0, 0);
		int race = 1;
		p.setColor(race);
		assertEquals(race, p.getRace());
	}
}

