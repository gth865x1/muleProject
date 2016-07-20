import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.awt.AWTException;
import java.awt.Robot;

import org.junit.Test;

/**
 * This class tests the GameConfigScreen
 * 
 * @author James Leopold
 * 
 */
public class JamesTests {
 
	/**
	 * Tests if the difficulty is initially set to beginner = 0;
	 * @throws AWTException
	 */
	@Test
	public void testBeginnerDifficulty() throws AWTException {
		GameConfigScreen g = new GameConfigScreen();
		assertEquals(0, g.getDifficulty());
	}

	/**
	 * Tests if the standard difficulty button sets the difficulty correctly
	 * @throws AWTException
	 */
	@Test
	public void testStandardDifficulty() throws AWTException {
		GameConfigScreen g = new GameConfigScreen();
		g.setEnabled(true);
		g.setAlwaysOnTop(true);
		Robot bot = new Robot();

		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);
		bot.keyPress(32);
		bot.delay(30);
		bot.keyRelease(32);
		bot.delay(30);

		assertEquals(1, g.getDifficulty());
	}

	/**
	 * Tests if the tournament difficulty button sets the difficulty correctly
	 * @throws AWTException
	 */
	@Test
	public void testTournamentDifficulty() throws AWTException {
		GameConfigScreen g = new GameConfigScreen();
		g.setEnabled(true);
		g.setAlwaysOnTop(true);
		Robot bot = new Robot();

		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);
		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);
		bot.keyPress(32);
		bot.delay(30);
		bot.keyRelease(32);
		bot.delay(30);

		assertEquals(2, g.getDifficulty());
	}

	/**
	 * Tests that there are 4 players when the 4 player button is selected
	 * @throws AWTException
	 */
	@Test
	public void testPlayers() throws AWTException {
		GameConfigScreen g = new GameConfigScreen();
		g.setEnabled(true);
		g.setAlwaysOnTop(true);
		Robot bot = new Robot();

		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);
		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);
		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);
		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);
		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);
		bot.keyPress(32);
		bot.delay(30);
		bot.keyRelease(32);
		bot.delay(30);

		assertEquals(4, g.getNumPlayers());
	}


	/**
	 * Tests that the standard map is selected
	 * @throws AWTException
	 */
	@Test
	public void testStandardMap() throws AWTException {
		GameConfigScreen g = new GameConfigScreen();
		assertEquals(0, g.getMap());
	}

		
	/**
	 * Tests that the random map is set correctly
	 * @throws AWTException
	 */
	@Test
	public void testRandomMap() throws AWTException {
		GameConfigScreen g = new GameConfigScreen();
		g.setEnabled(true);
		g.setAlwaysOnTop(true);
		Robot bot = new Robot();

		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);

		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);

		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);

		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);

		bot.keyPress(32);
		bot.delay(30);
		bot.keyRelease(32);
		bot.delay(30);

		assertEquals(1, g.getMap());
	}

	/**
	 * Tests that there is an error if the user puts invalid input
	 * Such as not setting the players to 4
	 * @throws AWTException
	 */
	@Test
	public void testErrorCase() throws AWTException {
		GameConfigScreen g = new GameConfigScreen();
		g.setEnabled(true);
		g.setAlwaysOnTop(true);
		Robot bot = new Robot();

		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);

		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);

		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);

		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);

		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);

		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);

		bot.keyPress(32);
		bot.delay(30);
		bot.keyRelease(32);
		bot.delay(30);

		assertNotNull("error box is up", g.getErrorBox());

		bot.keyPress(32);
		bot.delay(30);
		bot.keyRelease(32);
		bot.delay(30);
	}

	/**
	 * Checks that the error box does not show up if there is not an hour
	 * @throws AWTException
	 */
	@Test
	public void testErrorCase2() throws AWTException {
		GameConfigScreen g = new GameConfigScreen();
		g.setEnabled(true);
		g.setAlwaysOnTop(true);
		Robot bot = new Robot();

		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);

		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);

		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);

		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);

		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);

		bot.keyPress(32);
		bot.delay(30);
		bot.keyRelease(32);
		bot.delay(30);

		bot.keyPress(9);
		bot.delay(30);
		bot.keyRelease(9);
		bot.delay(30);

		bot.keyPress(32);
		bot.delay(30);
		bot.keyRelease(32);
		bot.delay(30);

		assertNull("error box is not up", g.getErrorBox());
	}
}
