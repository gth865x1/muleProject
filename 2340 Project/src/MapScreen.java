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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Creates the MapScreen that is used to display all the property tiles and the
 * town tile for use MULE game play.
 * 
 * @author Decidedly Undecided
 * 
 */

public class MapScreen extends JFrame implements Screen, Serializable {
	private boolean running;
	private MapButton[][] buttonMap;
	private JPanel[][] panelMap;
	private MapButton currentButton;
	private Tile[][] Map;
	private JPanel mapPanel, currentPanel, sidePanel, topPanel;
	private Player currentPlayer, Town;
	private boolean choose;
	private TownScreen town;
	private StoreScreen store;
	private Random rand;

	/**
	 * Constructor for the MapScreen. Creates the map of tiles, and assigns them
	 * to buttons for creating the map
	 */
	public MapScreen(int randMap) {// 0 standard map, 1 random
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1350, 750);
		setFocusable(true);
		addKeyListener(new CloseListener());
		mapPanel = new JPanel();
		sidePanel = new JPanel();
		topPanel = new JPanel();
		sidePanel.setPreferredSize(new Dimension(10, 150));
		topPanel.setPreferredSize(new Dimension(170, 10));
		choose = false;
		mapPanel.setLayout(new GridLayout(5, 9));
		Map = new Tile[5][9];
		buttonMap = new MapButton[5][9];
		panelMap = new JPanel[5][9];
		rand = new Random();

		if (randMap == 1) { // we have a random map
			for (int m = 0; m < 5; m++) {
				for (int n = 0; n < 9; n++) {
					if (m == 2 && n == 4) { // this is the town index
						Map[m][n] = new Town();
						currentButton = new MapButton();
						currentButton.assignTile(Map[m][n]);
						ImageIcon icon = new ImageIcon(
								currentButton.mine.getIcon());
						currentButton.setIcon(icon);
						currentButton.addActionListener(new ButtonListener());
						buttonMap[m][n] = currentButton;
						currentPanel = new JPanel();
						currentButton.setPanel(currentPanel);
						currentPanel.setPreferredSize(new Dimension(170, 170));
						currentPanel.add(currentButton, BorderLayout.CENTER);
						currentPanel.add(sidePanel, BorderLayout.LINE_START);
						currentPanel.add(sidePanel, BorderLayout.LINE_END);
						currentPanel.add(topPanel, BorderLayout.PAGE_START);
						currentPanel.add(topPanel, BorderLayout.PAGE_END);
						panelMap[m][n] = currentPanel;
						mapPanel.add(currentPanel);
					} else { // anything but the town index
						int randNum = rand.nextInt(5);

						if (randNum == 0) {
							Map[m][n] = new Plain();
						} else if (randNum == 1) {
							Map[m][n] = new Mountain1();
						} else if (randNum == 2) {
							Map[m][n] = new Mountain2();
						} else if (randNum == 3) {
							Map[m][n] = new Mountain3();
						} else if (randNum == 4) {
							Map[m][n] = new River();
						}
						currentButton = new MapButton();
						currentButton.assignTile(Map[m][n]);
						ImageIcon icon = new ImageIcon(
								currentButton.mine.getIcon());
						currentButton.setIcon(icon);
						currentButton.addActionListener(new ButtonListener());
						buttonMap[m][n] = currentButton;
						currentPanel = new JPanel();
						currentButton.setPanel(currentPanel);
						currentPanel.setPreferredSize(new Dimension(170, 170));
						currentPanel.add(currentButton, BorderLayout.CENTER);
						currentPanel.add(sidePanel, BorderLayout.LINE_START);
						currentPanel.add(sidePanel, BorderLayout.LINE_END);
						currentPanel.add(topPanel, BorderLayout.PAGE_START);
						currentPanel.add(topPanel, BorderLayout.PAGE_END);
						panelMap[m][n] = currentPanel;
						mapPanel.add(currentPanel);
					}
				}
			}

		} else { // we don't have a random map
			int i = 0;
			int j = 0;
			String[] stringMap = { "P", "P", "M1", "P", "R", "P", "M3", "P",
					"P", "P", "M1", "P", "P", "R", "P", "P", "P", "M3", "M3",
					"P", "P", "P", "Town", "P", "P", "P", "M1", "P", "M2", "P",
					"P", "R", "P", "M2", "P", "P", "P", "P", "M2", "P", "R",
					"P", "P", "P", "M2" };
			for (String s : stringMap) {
				if (s.equals("P")) {
					Map[i][j] = new Plain();
				} else if (s.equals("M1")) {
					Map[i][j] = new Mountain1();
				} else if (s.equals("M2")) {
					Map[i][j] = new Mountain2();
				} else if (s.equals("M3")) {
					Map[i][j] = new Mountain3();
				} else if (s.equals("R")) {
					Map[i][j] = new River();
				} else {
					Map[i][j] = new Town();
				}

				currentButton = new MapButton();
				currentButton.assignTile(Map[i][j]);
				ImageIcon icon = new ImageIcon(currentButton.mine.getIcon());
				currentButton.setIcon(icon);
				currentButton.addActionListener(new ButtonListener());
				buttonMap[i][j] = currentButton;
				currentPanel = new JPanel();
				currentButton.setPanel(currentPanel);
				currentPanel.setPreferredSize(new Dimension(170, 170));
				currentPanel.add(currentButton, BorderLayout.CENTER);
				currentPanel.add(sidePanel, BorderLayout.LINE_START);
				currentPanel.add(sidePanel, BorderLayout.LINE_END);
				currentPanel.add(topPanel, BorderLayout.PAGE_START);
				currentPanel.add(topPanel, BorderLayout.PAGE_END);
				panelMap[i][j] = currentPanel;
				mapPanel.add(currentPanel);

				if (j < 8) {
					j++;
				} else {
					j = 0;
					i++;
				}
			}
		}

		add(mapPanel);
		Town = Map[2][4].getOwnership();
		setRunning(true);
	}

	/**
	 * Creates a townScreen for a particular round
	 * 
	 * @param d
	 *            the game difficulty
	 */
	public void createTownScreen(int d) {
		store = new StoreScreen(d);
		town = new TownScreen(this, store);
		town.pack();
		store.setTownScreen(town);
	}

	/**
	 * Gets the TownScreen for reference and use in other classes
	 * 
	 * @return the town screen
	 */
	public TownScreen getTown() {
		return town;
	}

	/**
	 * Goes through the Tiles and has them produce resources for their owner
	 */
	public void produceResources() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				Map[i][j].produceResources();
			}
		}
	}

	/**
	 * Key listener that finds if the player has passed on their land grant
	 * 
	 * @author khamo
	 * 
	 */
	private class CloseListener implements KeyListener, Serializable {
		public void keyPressed(KeyEvent event) {
			if (choose && (event.getKeyCode() == (KeyEvent.VK_ENTER))) {
				JOptionPane.showMessageDialog(getContentPane(),
						"You skipped ur turn");
				setChoose(false);

			}
		}

		public void keyTyped(KeyEvent event) {
		}

		public void keyReleased(KeyEvent event) {
		}
	}

	/**
	 * Listener that assigns tiles to players during land grants
	 * 
	 * @author khamo
	 * 
	 */
	private class ButtonListener implements ActionListener, Serializable {
		public void actionPerformed(ActionEvent e) {
			MapButton source = (MapButton) e.getSource();
			if (choose) {
				if (source.mine.getOwnership() == null) {
					int landPrice = 0;
					if (currentPlayer.getFreeLand() <= 0
							&& currentPlayer.getMoney() >= 300) {
						landPrice = 300;
						currentPlayer.changeMoney(-300);
					}
					source.mine.setOwnership(currentPlayer);
					currentPlayer.changeLand(1);
					currentPlayer.decrementFreeLand();
					JPanel p = source.getPanel();
					switch (currentPlayer.getColor()) {
					case 0:
						p.setBackground(Color.blue);
						break;
					case 1:
						p.setBackground(Color.green);
						break;
					case 2:
						p.setBackground(Color.red);
						break;
					case 3:
						p.setBackground(Color.yellow);
						break;
					}
					JOptionPane.showMessageDialog(
							getContentPane(),
							"You just bought land for " + landPrice
									+ " and you now have $ "
									+ currentPlayer.getMoney());
					setChoose(false);
				} else {
					JOptionPane.showMessageDialog(getContentPane(),
							"You cannot buy that tile");
				}
			} else {
				if (source.mine.getOwnership() == Town) {
					setVisible(false);
					town.setVisible(true);
					town.setExtendedState(town.getExtendedState()
							| JFrame.MAXIMIZED_BOTH);
					town.pack();
				} else if (source.mine.getOwnership() == currentPlayer) {
					if ((source.mine.getMule() == null)
							&& (currentPlayer.getMule() != null)) {
						source.mine.setMule(currentPlayer.getMule());
						source.setIcon(new ImageIcon(source.mine.getIcon()));
						currentPlayer.setMule(null);
					} else {
						currentPlayer.setMule(null);
						JOptionPane.showMessageDialog(getContentPane(),
								"You aint got no mule");
					}
				} else if (source.mine.getOwnership() != currentPlayer) {
					currentPlayer.setMule(null);
					JOptionPane.showMessageDialog(getContentPane(),
							"Thats not your Tile");
				}
			}
		}
	}

	/**
	 * Closes the town screen by setting its visibility to false
	 */
	public void byeByeTown() {
		town.setVisible(false);
	}

	/**
	 * Closes the store screen by setting visibility to false
	 */
	public void byebyeStore() {
		store.setVisible(false);
	}

	/**
	 * Lets round tell MapScreen who the current player is
	 * 
	 * @param p
	 *            some input Player
	 */
	public void setCurrentPlayer(Player p) {
		currentPlayer = p;
		town.setCurrentPlayer(p);
	}

	/**
	 * lets round know if the current player has finished picking their land
	 * 
	 * @return boolean of whether or not the player is picking their land
	 */
	public boolean getChoose() {
		return choose;
	}

	/**
	 * private class that is the same as JButton but has a private Tile and
	 * JPanel value
	 * 
	 * @author khamo
	 * 
	 */
	private class MapButton extends JButton implements Serializable {
		private Tile mine;

		private MapButton() {
		}

		private JPanel panel;

		/**
		 * assigns a Tile to the MapButton
		 * 
		 * @param t
		 */
		public void assignTile(Tile t) {
			mine = t;
		}

		/**
		 * sets the MapButton's icon to the input icon i
		 * 
		 * @param i
		 */
		public void setIcon(ImageIcon i) {
			super.setIcon(i);
		}

		/**
		 * assigns a JPanel to the MapButton
		 * 
		 * @param p
		 */
		public void setPanel(JPanel p) {
			panel = p;
		}

		/**
		 * returns what the assigned JPanel is
		 * 
		 * @return
		 */
		public JPanel getPanel() {
			return panel;
		}
	}

	/**
	 * returns if the MapScreen is still running
	 * 
	 * @return boolean of whether or not its running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * flags MapScreen to be turned on or killed
	 * 
	 * @param run
	 */
	private void setRunning(boolean run) {
		running = run;
	}

	/**
	 * sets whether or not the player has chosen their land or not
	 * 
	 * @param chose
	 */
	public void setChoose(boolean chose) {
		choose = chose;
	}
}
