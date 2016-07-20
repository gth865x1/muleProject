import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * The game class represents a new game of MULE
 * 
 * @author Decidedly Undecided
 * 
 */
public class Game implements Serializable {

	static final long serialVersionUID = 42L;

	protected Player[] players;
	protected int numPlayers;
	private int difficulty;
	private int mapType;
	private GameConfigScreen gcScreen;
	public MapScreen map;
	public int roundNum = 1;

	/**
	 * The start method starts the game and works through the different elements
	 * of the game from start to finish
	 */
	public void start() {
		round();
		Winner win = new Winner(getWinner());

	}

	/**
	 * The initialize method starts the game config screen then initializes the
	 * player config screen then the main screen
	 */
	public void initialize() {

		
		gcScreen = new GameConfigScreen();
		gcScreen.pack();
		while (gcScreen.isRunning()) {
			gcScreen.setVisible(true);
		}
		gcScreen.setVisible(false);
		setDifficulty(gcScreen.getDifficulty());
		setNumPlayers(gcScreen.getNumPlayers());
		setMapType(gcScreen.getMap());

		players = new Player[numPlayers];

		for (int i = 0; i < numPlayers; i++) {
			players[i] = new Player(i, difficulty);

		}
		PlayerConfigScreen pcScreen = new PlayerConfigScreen(players);
		pcScreen.pack();
		while (pcScreen.isRunning()) {
			pcScreen.setVisible(true);
		}
		pcScreen.setVisible(false);

		MainScreen main = new MainScreen(players, this);
		main.pack();
		while (main.isRunning()) {
			main.setVisible(true);
		}
		main.setVisible(false);
		map = new MapScreen(gcScreen.getMap());
		map.createTownScreen(difficulty);
		map.pack();
	}

	/**
	 * Sets up the basis for a round of the game and creates a MapScreen
	 */
	public void round() {
		if (map == null) {
			map = new MapScreen(gcScreen.getMap());
			map.createTownScreen(difficulty);
			map.pack();
		}
		while (roundNum < 13) {
			
			map.setVisible(true);
			makeOrder();
			Round round = new Round(players, map, roundNum);
			MainScreen main = new MainScreen(players, this);
			main.pack();
			while (main.isRunning()) {
				main.setVisible(true);
			}
			main.setVisible(false);
			map.setVisible(true);
			roundNum++;
		}
	}

	/**
	 * sets the difficulty
	 * 
	 * @param d
	 *            the difficulty
	 */
	private void setDifficulty(int d) {
		difficulty = d;
	}

	/**
	 * sets the number of players
	 * 
	 * @param p
	 *            the number of players
	 */
	private void setNumPlayers(int p) {
		numPlayers = p;
	}

	/**
	 * sets the map type
	 * 
	 * @param m
	 *            the map type
	 */
	private void setMapType(int m) {
		mapType = m;
	}

	/**
	 * Calculates the order in which players take their turns in the round
	 * 
	 * @return an array of players in order
	 */
	private void makeOrder() {
		for (int i = 1; i < 4; i++) {
			Player temp = players[i];
			int j = i - 1;
			//Checks if the next player has a higher score then the temp
			while (j >= 0 && players[j].getScore() > temp.getScore()) {
				players[j + 1] = players[j];
				j = j - 1;
			}
			players[j + 1] = temp;
		}
	}

	/**
	 * Gets the winning player after the completion of all rounds
	 * 
	 * @return the winning player, determined by score
	 */
	private Player getWinner() {
		Player winner = players[0];
		for (int i = 1; i < 4; i++) {
			if (winner.getScore() < players[i].getScore()) {
				winner = players[i];
			//checks the case of a score tie and gives the win the player with more goods
			} else if (winner.getScore() == players[i].getScore()
					&& winner.getGoods() < players[i].getGoods()) {
				winner = players[i];
			}
		}
		map.setVisible(false);
		return winner;

	}

	/**
	 * Saves the current game for the purpose of resuming at a later time
	 */
	public void saveGame() {
		try {
			FileOutputStream fileOut = new FileOutputStream("game.sav");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
			System.out.println("Game saved as game.sav");
			System.exit(0);
		} catch (FileNotFoundException e) {
			System.out.println("Can't save game");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Can't save game");
			e.printStackTrace();
		}
	}

	/**
	 * loads a previously saved game and resumes play
	 * 
	 * @return the previously saved game
	 */
	public static Game loadGame() {
		Game loaded = null;
		try {
			FileInputStream fileIn = new FileInputStream("game.sav");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			loaded = (Game) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("Game loaded from savedgame.sav");
		} catch (FileNotFoundException e) {
			// do nothing

		} catch (IOException e) {
			// do nothing

		} catch (ClassNotFoundException e) {
			// do nothing
		}
		return loaded;
	}
}
