import static org.junit.Assert.*;

import org.junit.Test;


public class MatthewMuleTests {

	@Test
	public void testIcon() {	
		Plain plain = new Plain();	
		
		assertEquals(plain.getIcon(),"P.png");
	}

	@Test
	public void testOwnership() {
		Player p = new Player(0, 0);
		Plain plain = new Plain();	
		plain.setOwnership(p);
		
		assertEquals(plain.getOwnership(), p);
	}

	@Test
	public void testMule() {
		Player p = new Player(0, 0);
		Plain plain = new Plain();	
		MULE mule = new MULE(p, 3);
		plain.setMule(mule);

		assertEquals(mule.getType(), plain.getMule().getType());
	}

	@Test
	public void testproduceResources() {
		Player p = new Player(0, 0);
		Plain plain = new Plain();
		
		MULE mule = new MULE(p, 3);
		plain.setMule(mule);
		plain.setOwnership(p);
		
		plain.produceResources();
		

		assertEquals(p.getEnergy(), 6);
	}


}
