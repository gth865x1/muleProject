import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * The GameConfigScreen class makes the starting game configuration screen for
 * the MULE game
 * 
 * @author James Leopold
 * 
 */
public class GameConfigScreen extends JFrame implements Screen, ActionListener {
	private int difficulty; // 0- beginner, 1-standard, 2-tournament
	private int map; // 0-standard, 1-random
	private int numPlayers; // always 4
	private JPanel difficultyPanel, mapPanel, playerPanel, userPanel,
			bottomPanel;
	private JLabel difficultyLabel, mapLabel, playerLabel;
	private JRadioButton playerButton;
	private JOptionPane error;
	private boolean running;

	/**
	 * The GameConfigScreen creates a new window where the players can set up
	 * the game
	 */
	public GameConfigScreen() {
		super("WELCOME TO MULE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 300);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JRadioButton beginnerButton = new JRadioButton("Beginner");
		beginnerButton.setActionCommand("Beginner");
		beginnerButton.setSelected(true);

		JRadioButton standardButton = new JRadioButton("Standard");
		standardButton.setActionCommand("Standard");

		JRadioButton tournamentButton = new JRadioButton("Tournament");
		tournamentButton.setActionCommand("Tournament");

		ButtonGroup difficultyGroup = new ButtonGroup();
		difficultyGroup.add(beginnerButton);
		difficultyGroup.add(standardButton);
		difficultyGroup.add(tournamentButton);

		beginnerButton.addActionListener(this);
		standardButton.addActionListener(this);
		tournamentButton.addActionListener(this);

		difficultyPanel = new JPanel();
		difficultyLabel = new JLabel("SELECT A DIFFICULTY:");
		difficultyPanel.setLayout(new BoxLayout(difficultyPanel,
				BoxLayout.Y_AXIS));
		difficultyPanel.add(difficultyLabel);
		difficultyPanel.add(beginnerButton);
		difficultyPanel.add(standardButton);
		difficultyPanel.add(tournamentButton);

		JRadioButton mapButton = new JRadioButton("Standard Map");
		mapButton.setActionCommand("Standard Map");
		mapButton.setSelected(true);

		JRadioButton randomButton = new JRadioButton("Random Map");
		randomButton.setActionCommand("Random Map");

		ButtonGroup mapGroup = new ButtonGroup();
		mapGroup.add(mapButton);
		mapGroup.add(randomButton);

		mapButton.addActionListener(this);
		randomButton.addActionListener(this);

		mapPanel = new JPanel();
		mapLabel = new JLabel("SELECT A MAP TYPE:");
		mapPanel.setLayout(new BoxLayout(mapPanel, BoxLayout.Y_AXIS));
		mapPanel.add(mapLabel);
		mapPanel.add(mapButton);
		mapPanel.add(randomButton);

		playerButton = new JRadioButton("4 Players");
		playerButton.setActionCommand("4 Players");

		playerButton.addActionListener(this);

		playerPanel = new JPanel();
		playerLabel = new JLabel("SELECT NUMBER OF PLAYERS:");
		playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
		playerPanel.add(playerLabel);
		playerPanel.add(playerButton);

		JButton okButton = new JButton("OK!");
		JButton cancelButton = new JButton("Cancel");

		okButton.setActionCommand("OK!");
		cancelButton.setActionCommand("Cancel");
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);

		bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		bottomPanel.add(okButton);
		bottomPanel.add(cancelButton);

		userPanel = new JPanel();
		userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));

		userPanel.add(difficultyPanel);
		userPanel.add(mapPanel);
		userPanel.add(playerPanel);

		add(userPanel);
		add(bottomPanel);
		setRunning(true);
		setVisible(true);
	}

	/**
	 * Action listeners for all the buttons, checks the string of each button
	 * and performs appropriate action.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Beginner")
			setDifficulty(0);
		else if (e.getActionCommand() == "Standard")
			setDifficulty(1);
		else if (e.getActionCommand() == "Tournament")
			setDifficulty(2);
		else if (e.getActionCommand() == "Standard Map")
			setMap(0);
		else if (e.getActionCommand() == "Random Map")
			setMap(1);
		else if (e.getActionCommand() == "4 Players")
			setNumPlayers(4);
		else if (e.getActionCommand() == "OK!") {
			if (playerButton.isSelected()) {
				// close window and go to next screen
				if (isRunning()) {
					setRunning(false);
					dispose();
				}
			} else {
				error = new JOptionPane();
				error.showMessageDialog(getContentPane(),
						"You must select a number of players!");
			}
		} else if (e.getActionCommand() == "Cancel") {
			System.exit(0);
		}
	}

	/**
	 * sets the difficulty of the game 0 is beginner 1 is standard 2 is
	 * tournament
	 * 
	 * @param the
	 *            difficulty
	 */
	private void setDifficulty(int d) {
		difficulty = d;
	}

	/**
	 * sets the map for the game 0 is standard map generation 1 is random map
	 * generation
	 * 
	 * @param the
	 *            map type
	 */
	private void setMap(int m) {
		map = m;
	}

	/**
	 * sets the number of players (always 4)
	 * 
	 * @param the
	 *            number of players
	 */
	private void setNumPlayers(int p) {
		numPlayers = p;
	}

	/**
	 * Sets whether the screen is running or not
	 * 
	 * @param run
	 *            a boolean for whether the screen is running
	 */
	private void setRunning(boolean run) {
		running = run;
	}

	/**
	 * Determines if the screen is running
	 * 
	 * @return whether the screen is running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * gets the difficulty of the game
	 * 
	 * @return the difficulty as an int
	 */
	public int getDifficulty() {
		return difficulty;
	}

	/**
	 * gets the map for the game
	 * 
	 * @return the map type as an int
	 */
	public int getMap() {
		return map;
	}

	/**
	 * gets the number of players (always 4)
	 * 
	 * @return the number of players
	 */
	public int getNumPlayers() {
		return numPlayers;
	}

	/**
	 * Gets the error box
	 * 
	 * @return the error box
	 */
	public JOptionPane getErrorBox() {
		return error;
	}
}
