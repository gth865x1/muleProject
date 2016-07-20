import static org.junit.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.Test;

/**
 * This class tests the player configuration screen
 * @author Greg "Based" Hendler
 *
 */
public class PlayerTestGreg {
	
	/**
	 * Tests that the name of each player is set correctly
	 * @throws AWTException
	 */
	@Test
	public void testName() throws AWTException {

		Player[] players = new Player[4];

		for (int i = 0; i < 4; i++) {
			players[i] = new Player(i, 0); // initialize each of the players
		}

		PlayerConfigScreen p = new PlayerConfigScreen(players);
		p.setEnabled(true);
		p.setAlwaysOnTop(true);
		p.setVisible(true);
		Robot bot = new Robot();

		// delete old name
		for (int i = 0; i < 9; i++) {
			bot.keyPress(KeyEvent.VK_DELETE);
			bot.delay(30);
			bot.keyRelease(KeyEvent.VK_DELETE);
			bot.delay(30);
		}

		// new name
		bot.keyPress(KeyEvent.VK_T);
		bot.delay(30);
		bot.keyRelease(KeyEvent.VK_T);
		bot.delay(30);
		bot.keyPress(KeyEvent.VK_E);
		bot.delay(30);
		bot.keyRelease(KeyEvent.VK_E);
		bot.delay(30);
		bot.keyPress(KeyEvent.VK_S);
		bot.delay(30);
		bot.keyRelease(KeyEvent.VK_S);
		bot.delay(30);
		bot.keyPress(KeyEvent.VK_T);
		bot.delay(30);
		bot.keyRelease(KeyEvent.VK_T);
		bot.delay(30);

		for (int i = 0; i < 24; i++) {
			bot.keyPress(9);
			bot.delay(30);
			bot.keyRelease(9);
			bot.delay(30);
		}

		bot.keyPress(32);
		bot.delay(30);
		bot.keyRelease(32);
		bot.delay(30);

		assertEquals("test", players[0].getName());
	}
    
	/**
	 * Tests that the color of the players is based correctly #raremethod
	 * @throws AWTException
	 */
	@Test
	public void testColor() throws AWTException {

		Player[] players = new Player[4];

		for (int i = 0; i < 4; i++) {
			players[i] = new Player(i, 0); // initialize each of the players
		}

		PlayerConfigScreen p = new PlayerConfigScreen(players);
		p.setEnabled(true);
		p.setAlwaysOnTop(true);
		p.setVisible(true);
		Robot bot = new Robot();

		for (int i = 0; i < 2; i++) {
			bot.keyPress(9);
			bot.delay(30);
			bot.keyRelease(9);
			bot.delay(30);
		}

		bot.keyPress(32);
		bot.delay(30);
		bot.keyRelease(32);
		bot.delay(30);

		for (int i = 0; i < 22; i++) {
			bot.keyPress(9);
			bot.delay(30);
			bot.keyRelease(9);
			bot.delay(30);
		}

		bot.keyPress(32);
		bot.delay(30);
		bot.keyRelease(32);
		bot.delay(30);

		assertEquals(1, players[0].getColor());
	}
	
	/**
	 * Tests that the rarity of the players races
	 * @throws AWTException
	 */
	@Test
	public void testRace() throws AWTException {

		Player[] players = new Player[4];

		for (int i = 0; i < 4; i++) {
			players[i] = new Player(i, 0); // initialize each of the players
		}

		PlayerConfigScreen p = new PlayerConfigScreen(players);
		p.setEnabled(true);
		p.setAlwaysOnTop(true);
		p.setVisible(true);
		Robot bot = new Robot();

		for (int i = 0; i < 5; i++) {
			bot.keyPress(9);
			bot.delay(30);
			bot.keyRelease(9);
			bot.delay(30);
		}

		bot.keyPress(32);
		bot.delay(30);
		bot.keyRelease(32);
		bot.delay(30);

		for (int i = 0; i < 2; i++) {
			bot.keyPress(KeyEvent.VK_DOWN);
			bot.delay(30);
			bot.keyRelease(KeyEvent.VK_DOWN);
			bot.delay(30);
		}

		bot.keyPress(KeyEvent.VK_ENTER);
		bot.delay(30);
		bot.keyRelease(KeyEvent.VK_ENTER);
		bot.delay(30);

		for (int i = 0; i < 19; i++) {
			bot.keyPress(9);
			bot.delay(30);
			bot.keyRelease(9);
			bot.delay(30);
		}

		bot.keyPress(32);
		bot.delay(30);
		bot.keyRelease(32);
		bot.delay(30);

		assertEquals(2, players[0].getRace());
	}
}
