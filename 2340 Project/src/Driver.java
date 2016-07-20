/**
 * The driver class starts the MULE game
 * 
 * @author Decidedly Undecided
 * 
 */
public class Driver {
	/**
	 * The main method makes and starts an instance of the game class
	 */
	public static void main(String[] args) {
		TitleScreen title = new TitleScreen();
		while (title.isRunning()) {
			title.setVisible(true);
		}
		title.setVisible(false);
		Game g = title.getGame();
		boolean loaded = title.getLoaded();
		if (!loaded) {
			g.initialize();
		}

		g.start();
	}

}
