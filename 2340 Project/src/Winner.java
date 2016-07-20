import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This screen congratulates the winner of the game at the completion of all
 * rounds
 * 
 * @author Decidedly Undecided
 * 
 */

public class Winner extends JFrame implements Screen {
	private JPanel panel;
	private boolean running;
	private JLabel label;
	private JButton button;

	/**
	 * Creates a winner screen with the winning player's name
	 * 
	 * @param p
	 *            the winning player
	 */
	public Winner(Player p) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 300);
		addKeyListener(new CloseListener());
		setFocusable(true);
		setVisible(true);

		panel = new JPanel();
		label = new JLabel();
		button = new JButton("Quit");
		button.addActionListener(new ButtonListener());
		add(panel);
		panel.add(label);
		panel.add(button);

		label.setText("Congrats " + p.getName() + ", you WON THE GAME!");

	}

	/**
	 * Method for Game class to check if screen is still running
	 * 
	 * @return the boolean of if the screen is still running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * private keyListener that stops the window when the user presses any key
	 */
	private class CloseListener implements KeyListener {
		public void keyPressed(KeyEvent event) {
			setRunning(false);
			setVisible(false);
		}

		public void keyTyped(KeyEvent event) {
		}

		public void keyReleased(KeyEvent event) {
		}
	}

	/**
	 * Sets whether the screen is running
	 * 
	 * @param run
	 *            true if the screen is running, false otherwise
	 */
	private void setRunning(boolean run) {
		running = run;
	}

	/**
	 * Exits the game once a player stops celebrating.
	 * 
	 */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}
