import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * The TownScreen provides the visual representation for the four main areas in
 * the town.
 * 
 * @author Decidedly Undecided
 * 
 */

public class TownScreen extends JFrame implements Screen, Serializable {
	private boolean running, active;
	private TownPanel town;
	private JButton pub, store, assay, landoffice, backToMap;
	private Player p;
	private Round round;
	private JOptionPane box;
	private StoreScreen storeS;
	private MapScreen map;

	/**
	 * Creates a new town screen with a town panel on it.
	 * 
	 * @param m
	 *            The MapScreen being used in the game
	 */
	public TownScreen(MapScreen m, StoreScreen s) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		box = new JOptionPane();
		town = new TownPanel();
		add(town, BorderLayout.CENTER);
		backToMap = new JButton("Back to Map");
		backToMap.addActionListener(new BackListener());
		add(backToMap, BorderLayout.PAGE_END);
		map = m;
		storeS = s;
		active = false;
		addKeyListener(new CloseListener());
		setRunning(true);
	}

	/**
	 * Sets the current round of the game
	 * 
	 * @param r
	 *            the current round
	 */
	public void setRound(Round r) {
		round = r;
	}

	/**
	 * The TownPanel holds the representations of the four main sections in the
	 * town - the Pub, the Store, the Land Office, and the Assay.
	 * 
	 * @author Decidedly Undecided
	 * 
	 */
	private class TownPanel extends JPanel implements Serializable {
		private final int width = 800, height = 600;

		/**
		 * Creates a TownPanel with a black background
		 */
		private TownPanel() {
			setPreferredSize(new Dimension(width, height));
			setBackground(Color.black);
			setLayout(new GridLayout(2, 2));
			pub = new JButton("Pub");
			pub.addActionListener(new PubListener());
			store = new JButton("Store");
			store.addActionListener(new StoreListener());
			assay = new JButton("Assay");
			landoffice = new JButton("Land Office");
			add(pub);
			add(store);
			add(assay);
			add(landoffice);
		}
	}

	/**
	 * Sends the player back to the MapScreen for convenient MULE placement by
	 * setting the visibility for the town and the map appropriately
	 * 
	 * @author Cathey
	 * 
	 */
	private class BackListener implements ActionListener, Serializable {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			map.setVisible(true);
		}
	}

	/**
	 * Handles opening the store by setting the current player, updating the
	 * window, and setting the appropriate visibilities.
	 * 
	 * @author Cathey
	 * 
	 */
	private class StoreListener implements ActionListener, Serializable {
		public void actionPerformed(ActionEvent e) {
			storeS.setCurrentPlayer(p);
			storeS.updateWindow();
			storeS.pack();
			storeS.setVisible(true);
			setVisible(false);
		}
	}

	/**
	 * Handles the tasks for the Pub, including gambling, checking a player's
	 * time, and adding money to their score.
	 * 
	 * @author Decidedly Undecided
	 * 
	 */
	private class PubListener implements ActionListener, Serializable {
		public void actionPerformed(ActionEvent e) {
			int timeb = round.timeLeft();
			int roundb = round.getRoundNum();
			if (roundb >= 1 & roundb <= 3) {
				roundb = 50;
			} else if (roundb >= 4 & roundb <= 7) {
				roundb = 100;
			} else if (roundb >= 8 & roundb <= 11) {
				roundb = 150;
			} else {
				roundb = 200;
			}
			Random rand = new Random();
			int money = roundb * rand.nextInt(timeb + 1);
			if (money > 250) {
				money = 250;
			}
			p.changeMoney(money);
			box.showMessageDialog(getContentPane(), "You earned $" + money
					+ " dollars at the pub");
			round.setTurnOver(true);
		}
	}

	/**
	 * Sets the running parameter so the game knows if this screen should be
	 * displayed
	 * 
	 * @param run
	 *            A boolean value to keep the screen running
	 */
	private void setRunning(boolean run) {
		running = run;
	}

	/**
	 * Determines whether the screen is running
	 * 
	 * @return Whether the screen is running or not
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * sets the current player interacting in the town
	 * 
	 * @param current
	 *            the player currently interacting in the town
	 */
	public void setCurrentPlayer(Player current) {
		p = current;
	}

	/**
	 * Handles closing the TownScreen
	 * 
	 * @author Decidedly Undecided
	 * 
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
