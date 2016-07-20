import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The MainScreen class makes the first screen for the MULE game
 * 
 * @author Khameron Mitchem
 * 
 */
public class TitleScreen extends JFrame implements Screen {
	private boolean running;
	private JPanel titlePanel, creditsPanel, frameP, buttons;
	private JLabel title, team, names1, names2;
	private JButton loadButton, gameButton;
	private Game game;
	private boolean loaded;
	private JOptionPane box;

	/**
	 * Creates a title screen to open the MULE game
	 */
	public TitleScreen() {
		box = new JOptionPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 400);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setFocusable(true);
		setBackground(Color.yellow);
		titlePanel = new JPanel();
		titlePanel.setBackground(Color.yellow);

		creditsPanel = new JPanel();
		creditsPanel.setBackground(Color.yellow);
		creditsPanel.setLayout(new BoxLayout(creditsPanel, BoxLayout.Y_AXIS));

		title = new JLabel("M.U.L.E", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 72));

		loadButton = new JButton("Load Game");
		loadButton.addActionListener(new loadButtonListener());
		gameButton = new JButton("New Game");
		gameButton.addActionListener(new gameButtonListener());

		team = new JLabel(
				"-----------------------Decidedly Undecided-------------------------",
				SwingConstants.CENTER);
		names1 = new JLabel(
				"-James Leopold     Catherine Chapman     Greg Hendler-",
				SwingConstants.CENTER);
		names2 = new JLabel(
				"------------Matthew Eziashi     Khameron Mitchem-----------",
				SwingConstants.CENTER);

		titlePanel.add(title);
		creditsPanel.add(team);
		creditsPanel.add(names1);
		creditsPanel.add(names2);
		frameP = new JPanel();
		frameP.setLayout(new BoxLayout(frameP, BoxLayout.Y_AXIS));
		frameP.setBackground(Color.yellow);
		frameP.add(titlePanel);
		frameP.add(creditsPanel);
		add(frameP);

		buttons = new JPanel();
		buttons.setBackground(Color.yellow);
		buttons.add(gameButton);
		buttons.add(loadButton);
		frameP.add(buttons);
		setRunning(true);
		setVisible(true);
	}

	/**
	 * Sets whether the screen is running or not
	 * 
	 * @param run
	 *            true if the screen is running, false otherwise
	 */
	private void setRunning(boolean run) {
		running = run;
	}

	/**
	 * Tells whether the screen is running or not
	 * 
	 * @return whether the screen is running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * Handles how the title screen closes and can be closed.
	 * 
	 * @author Decidedly Undecided
	 * 
	 */

	private class gameButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			game = new Game();
			loaded = false;
			setRunning(false);
		}
	}

	/**
	 * Loads a previously saved game instead of creating a new one
	 * 
	 */
	private class loadButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			game = Game.loadGame();
			if (game == null) {
				loaded = false;
				setRunning(true);
				box.showMessageDialog(getContentPane(), "No Saved Game");
			} else {
				loaded = true;
				setRunning(false);
			}
		}
	}

	/**
	 * gets the current game
	 * 
	 * @return the current game instance
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * determines whether a game is loaded or not
	 * 
	 * @return true if a game is loaded.
	 */
	public boolean getLoaded() {
		return loaded;
	}
}