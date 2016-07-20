import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The MainScreen class makes the first screen for the MULE game
 * 
 * @author Khameron Mitchem
 * 
 */
public class MainScreen extends JFrame implements Screen, Serializable {
	private boolean running;
	private JPanel player1P, player2P, player3P, player4P, frameP, buttonFrame;
	private JLabel summary, p1stats, p2stats, p3stats, p4stats, colony;

	/**
	 * The MainScreen Class is created here that displays the current standing
	 * of players in the game
	 * 
	 * @param p
	 *            the array of Players
	 */
	public MainScreen(Player[] p, final Game g) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Default setting for
														// window
		setSize(600, 300);
		setLayout(new FlowLayout());
		addKeyListener(new CloseListener());
		setFocusable(true);
		summary = new JLabel("Status Summary", SwingConstants.CENTER); // Summary
																		// text
																		// block

		summary.setForeground(Color.white);
		player1P = new JPanel(); // Player 1's info
		player1P.setBackground(Color.black);
		p1stats = new JLabel("-", SwingConstants.CENTER);
		config(p[0], p1stats);
		player1P.add(p1stats);

		player2P = new JPanel();// Player 2's info
		player2P.setBackground(Color.black);
		p2stats = new JLabel("-", SwingConstants.CENTER);
		config(p[1], p2stats);
		player2P.add(p2stats);

		player3P = new JPanel();// player 3's info
		player3P.setBackground(Color.black);
		p3stats = new JLabel("-", SwingConstants.CENTER);
		config(p[2], p3stats);
		player3P.add(p3stats);

		player4P = new JPanel();// Player 4's info
		player4P.setBackground(Color.black);
		p4stats = new JLabel("-", SwingConstants.CENTER);
		config(p[3], p4stats);
		player4P.add(p4stats);

		colony = new JLabel("Colony-----"
				+ (p[0].getMoney() + p[0].getGoods() + p[1].getMoney()
						+ p[1].getGoods() + p[2].getMoney() + p[2].getGoods()
						+ p[3].getMoney() + p[3].getGoods()),
				SwingConstants.CENTER);
		colony.setForeground(Color.white);

		frameP = new JPanel();
		frameP.setLayout(new BoxLayout(frameP, BoxLayout.Y_AXIS));
		frameP.setBackground(Color.black);
		frameP.add(summary);
		frameP.add(player1P);
		frameP.add(player2P);
		frameP.add(player3P);
		frameP.add(player4P);
		frameP.add(colony);

		buttonFrame = new JPanel();
		buttonFrame.setBackground(Color.BLACK);

		JButton saveBtn = new JButton("Save and Quit");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g.saveGame();
			}
		});
		JButton contBtn = new JButton("Continue");
		contBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setRunning(false);
				setVisible(false);
			}
		});
		buttonFrame.add(contBtn);
		buttonFrame.add(saveBtn);
		frameP.add(buttonFrame);
		add(frameP);
		setRunning(true);
	}

	/**
	 * Takes in a boolean to turn off visibility when the keyListener triggers
	 * 
	 * @param run
	 */
	private void setRunning(boolean run) {
		running = run;
	}

	/**
	 * Method to take a player and their JLabel of stats and get the correct
	 * information from the player for the label
	 * 
	 * @param p
	 * @param pstats
	 */
	private void config(Player p, JLabel pstats) {
		pstats.setText("<html>Name							" + p.getName() + "<br>"
				+ "Money-----" + p.getMoney() + "<br>" + "Land-----"+ p.getLand() + "<br>"
				+ "Goods-----" + p.getGoods() + "<br>" + "Total-----"
				+ (p.getMoney() + p.getGoods() + "</html>"));
		int pcolor = p.getColor();
		if (pcolor == 0) {
			pstats.setForeground(Color.blue);
		} else if (pcolor == 1) {
			pstats.setForeground(Color.green);
		} else if (pcolor == 2) {
			pstats.setForeground(Color.red);
		} else if (pcolor == 3) {
			pstats.setForeground(Color.yellow);
		}
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
	private class CloseListener implements KeyListener, Serializable {
		public void keyPressed(KeyEvent event) {
			setRunning(false);
			setVisible(false);
		}

		public void keyTyped(KeyEvent event) {
		}

		public void keyReleased(KeyEvent event) {
		}
	}
}
