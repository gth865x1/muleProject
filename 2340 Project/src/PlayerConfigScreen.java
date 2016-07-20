import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * The player configuration screen allows players to customize their characters
 * in the MULE Game.
 * 
 * @author Decidedly Undecided
 * 
 */

public class PlayerConfigScreen extends JFrame implements Screen {
	private JPanel configPanel, playerPanel1, playerPanel2, playerPanel3,
			playerPanel4;
	private JTextField playerName0, playerName1, playerName2, playerName3;
	private JRadioButton colChoice0, colChoice1, colChoice2, colChoice3,
			colChoice01, colChoice11, colChoice21, colChoice31, colChoice02,
			colChoice12, colChoice22, colChoice32, colChoice03, colChoice13,
			colChoice23, colChoice33;
	private JComboBox<String> raceList0, raceList1, raceList2, raceList3;
	private JButton nextScreen;
	private JLabel name0, color0, race0, name1, color1, race1, name2, color2,
			race2, name3, color3, race3;

	private boolean running;

	private Player[] players;

	/**
	 * Creates a player configuration screen for four players and adds their
	 * name, race, and color information to the game.
	 * 
	 * @param players
	 *            An array of players whose information will be updated
	 */

	public PlayerConfigScreen(Player[] p) {
		players = p;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 300);
		setLayout(new FlowLayout());

		setRunning(true);

		configPanel = new JPanel(); // creates a panel to hold each player's
									// info panels.
		configPanel.setLayout(new GridLayout(4, 1));

		// creates labels for each playerPanel
		name0 = new JLabel("Player 1 - Enter your name: ");
		color0 = new JLabel("Choose a color: ");
		race0 = new JLabel("Choose your race: ");

		name1 = new JLabel("Player 2 -Enter your name: ");
		color1 = new JLabel("Choose a color: ");
		race1 = new JLabel("Choose your race: ");

		name2 = new JLabel("Player 3 -Enter your name: ");
		color2 = new JLabel("Choose a color: ");
		race2 = new JLabel("Choose your race: ");

		name3 = new JLabel("Player 4 -Enter your name: ");
		color3 = new JLabel("Choose a color: ");
		race3 = new JLabel("Choose your race: ");

		// creates a name field for each playerPanel
		playerName0 = new JTextField("player0", 10);
		playerName1 = new JTextField("player1", 10);
		playerName2 = new JTextField("player2", 10);
		playerName3 = new JTextField("player3", 10);

		String[] races = { "Packer", "Spheroid", "Humanoid", "Leggite",
				"Flapper", "Bonzoid", "Mechtron", "Gollumer" };

		// creates the dropdown box used to choose race for each playerPanel
		raceList0 = new JComboBox<String>(races);
		raceList1 = new JComboBox<String>(races);
		raceList2 = new JComboBox<String>(races);
		raceList3 = new JComboBox<String>(races);

		// create basic color choices for each playerPanel and add them to 4
		// ButtonGroups
		colChoice0 = new JRadioButton("Blue");
		colChoice1 = new JRadioButton("Green");
		colChoice2 = new JRadioButton("Red");
		colChoice3 = new JRadioButton("Yellow");

		ButtonGroup g1 = new ButtonGroup();
		g1.add(colChoice0);
		g1.add(colChoice1);
		g1.add(colChoice2);
		g1.add(colChoice3);
		g1.setSelected(colChoice0.getModel(), true);

		colChoice01 = new JRadioButton("Blue");
		colChoice11 = new JRadioButton("Green");
		colChoice21 = new JRadioButton("Red");
		colChoice31 = new JRadioButton("Yellow");

		ButtonGroup g2 = new ButtonGroup();
		g2.add(colChoice01);
		g2.add(colChoice11);
		g2.add(colChoice21);
		g2.add(colChoice31);
		g2.setSelected(colChoice11.getModel(), true);

		colChoice02 = new JRadioButton("Blue");
		colChoice12 = new JRadioButton("Green");
		colChoice22 = new JRadioButton("Red");
		colChoice32 = new JRadioButton("Yellow"); // creates basic color choices

		ButtonGroup g3 = new ButtonGroup();
		g3.add(colChoice02);
		g3.add(colChoice12);
		g3.add(colChoice22);
		g3.add(colChoice32);
		g3.setSelected(colChoice22.getModel(), true);

		colChoice03 = new JRadioButton("Blue");
		colChoice13 = new JRadioButton("Green");
		colChoice23 = new JRadioButton("Red");
		colChoice33 = new JRadioButton("Yellow");

		ButtonGroup g4 = new ButtonGroup();
		g4.add(colChoice03);
		g4.add(colChoice13);
		g4.add(colChoice23);
		g4.add(colChoice33);
		g4.setSelected(colChoice33.getModel(), true);

		// create buttons and add listeners for JButtons in each playerPanel

		// creates a button to continue the game to the next screen and adds
		// listener
		nextScreen = new JButton("Next Screen");
		nextScreen.addActionListener(new ButtonListener());

		/*
		 * create four player panels and add information sections to each of
		 * them before adding them to the configuration panel
		 */

		playerPanel1 = new JPanel();
		playerPanel1.add(name0);
		playerPanel1.add(playerName0);
		playerPanel1.add(color0);
		playerPanel1.add(colChoice0);
		playerPanel1.add(colChoice1);
		playerPanel1.add(colChoice2);
		playerPanel1.add(colChoice3);
		playerPanel1.add(race0);
		playerPanel1.add(raceList0);

		configPanel.add(playerPanel1);

		playerPanel2 = new JPanel();
		playerPanel2.add(name1);
		playerPanel2.add(playerName1);
		playerPanel2.add(color1);
		playerPanel2.add(colChoice01);
		playerPanel2.add(colChoice11);
		playerPanel2.add(colChoice21);
		playerPanel2.add(colChoice31);
		playerPanel2.add(race1);
		playerPanel2.add(raceList1);

		configPanel.add(playerPanel2);

		playerPanel3 = new JPanel();
		playerPanel3.add(name2);
		playerPanel3.add(playerName2);
		playerPanel3.add(color2);
		playerPanel3.add(colChoice02);
		playerPanel3.add(colChoice12);
		playerPanel3.add(colChoice22);
		playerPanel3.add(colChoice32);
		playerPanel3.add(race2);
		playerPanel3.add(raceList2);

		configPanel.add(playerPanel3);

		playerPanel4 = new JPanel();
		playerPanel4.add(name3);
		playerPanel4.add(playerName3);
		playerPanel4.add(color3);
		playerPanel4.add(colChoice03);
		playerPanel4.add(colChoice13);
		playerPanel4.add(colChoice23);
		playerPanel4.add(colChoice33);
		playerPanel4.add(race3);
		playerPanel4.add(raceList3);

		configPanel.add(playerPanel4);

		// adds the configuration panel to the JFrame, as well as the nextScreen
		// button
		add(configPanel);
		add(nextScreen);
	}

	/**
	 * Creates the listener for the "Next Screen" button by setting all player
	 * choices and exiting this screen and continuing with the game.
	 * 
	 * 
	 */
	private class ButtonListener implements ActionListener {
		/**
		 * Tells the game that this screen should no longer be running also sets
		 * all of the player choices to their respective players
		 * 
		 * @param e
		 */
		public void actionPerformed(ActionEvent e) {

			players[0].setName(playerName0.getText());

			if (colChoice0.isSelected()) {
				players[0].setColor(0);
			} else if (colChoice1.isSelected()) {
				players[0].setColor(1);
			} else if (colChoice2.isSelected()) {
				players[0].setColor(2);
			} else if (colChoice3.isSelected()) {
				players[0].setColor(3);
			}
			int raceSelect = raceList0.getSelectedIndex();
			players[0].setRace(raceList0.getSelectedIndex());
			if (raceSelect == 4) {
				players[0].changeMoney(1600);
			} else if (raceSelect == 2) {
				players[0].changeMoney(600);
			} else {
				players[0].changeMoney(1000);
			}

			players[1].setName(playerName1.getText());

			if (colChoice01.isSelected()) {
				players[1].setColor(0);
			} else if (colChoice11.isSelected()) {
				players[1].setColor(1);
			} else if (colChoice21.isSelected()) {
				players[1].setColor(2);
			} else if (colChoice31.isSelected()) {
				players[1].setColor(3);
			}

			raceSelect = raceList1.getSelectedIndex();
			players[1].setRace(raceList1.getSelectedIndex());
			if (raceSelect == 4) {
				players[1].changeMoney(1600);
			} else if (raceSelect == 2) {
				players[1].changeMoney(600);
			} else {
				players[1].changeMoney(1000);
			}

			players[2].setName(playerName2.getText());

			if (colChoice02.isSelected()) {
				players[2].setColor(0);
			} else if (colChoice12.isSelected()) {
				players[2].setColor(1);
			} else if (colChoice22.isSelected()) {
				players[2].setColor(2);
			} else if (colChoice32.isSelected()) {
				players[2].setColor(3);
			}

			raceSelect = raceList2.getSelectedIndex();
			players[2].setRace(raceList2.getSelectedIndex());
			if (raceSelect == 4) {
				players[2].changeMoney(1600);
			} else if (raceSelect == 2) {
				players[2].changeMoney(600);
			} else {
				players[2].changeMoney(1000);
			}

			players[3].setName(playerName3.getText());

			if (colChoice03.isSelected()) {
				players[3].setColor(0);
			} else if (colChoice13.isSelected()) {
				players[3].setColor(1);
			} else if (colChoice23.isSelected()) {
				players[3].setColor(2);
			} else if (colChoice33.isSelected()) {
				players[3].setColor(3);
			}

			raceSelect = raceList3.getSelectedIndex();
			players[3].setRace(raceList3.getSelectedIndex());
			if (raceSelect == 4) {
				players[3].changeMoney(1600);
			} else if (raceSelect == 2) {
				players[3].changeMoney(600);
			} else {
				players[3].changeMoney(1000);
			}

			setRunning(false);
		}
	}

	/**
	 * Sets the running variable as indicated in order to start or stop the
	 * screen
	 * 
	 * @param run
	 *            The variable to be set in order to display or stop the screen.
	 */
	private void setRunning(boolean run) {
		running = run;
	}

	/**
	 * Tells whether the PlayerConfigScreen is running or not.
	 * 
	 * @return True if PlayerConfigScreen is displayed, false otherwise
	 */
	public boolean isRunning() {
		return running;
	}
}
