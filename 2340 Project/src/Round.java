import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Round extends Game implements Serializable {
	/**
	 * The Round class determines when a "round" is over, i.e. all players have
	 * chosen appropriate actions.
	 * 
	 * @author Decidedly Undecided
	 * 
	 */
	private Player[] order;
	boolean check;
	private static boolean turnOver;
	private static Timer timer;
	private int secCounter;
	private static int turnTime, roundNum;
	private TownScreen town;
	private Player lowestP;

	/**
	 * The constructor for the Round
	 * 
	 * @param p
	 *            an array containing the players in the mule game
	 * @param map
	 *            the MapScreen being used throughout the round for land
	 *            purchases and mule placement
	 */
	public Round(Player[] p, MapScreen map, int round) {

		turnTime = 50;

		secCounter = 0;

		roundNum = round;

		timer = new Timer(1000, taskPerformer);
		timer.setRepeats(true);

		town = map.getTown();
		town.setRound(this);

		map.produceResources();

		this.order = p;
		lowestP = order[3];
		landGrant(map);
		playerTurn(order[0], map);
		playerTurn(order[1], map);
		playerTurn(order[2], map);
		playerTurn(order[3], map);
		order = p;

	}

	/**
	 * The land grant portion of the round, where every player picks a plot of
	 * land
	 * 
	 * @param m
	 *            the mapScreen from the game
	 */
	private void landGrant(MapScreen m) {
		for (Player p : order) {
			pickLand(p, m);
		}
	}

	/**
	 * Lets a player pick a plot of land
	 * 
	 * @param p
	 *            the player picking the land
	 * @param map
	 *            the mapScreen from the game
	 */
	private void pickLand(Player p, MapScreen map) {
		JOptionPane.showMessageDialog(map, p.getName()
				+ " : click to pick your land! Or press enter to pass.");
		check = true;
		map.requestFocus();
		map.setChoose(check);
		map.setCurrentPlayer(p);
		while (map.getChoose()) {
			map.setVisible(true);
		}
		return;
	}

	/**
	 * Runs the player's turn where they can build mules and modify land etc
	 * 
	 * @param p
	 *            The player whose turn it is
	 * @param map
	 *            The MapScreen being used
	 */
	private void playerTurn(Player p, MapScreen map) {
		JOptionPane.showMessageDialog(map, p.getName()
				+ ", get ready for ur turn dawg");
		map.setCurrentPlayer(p);
		generateEvent(p, map);
		turnOver = false;
		calculatePlayerTime(p);
		timer.restart();
		while (!turnOver) {
			try {
				Thread.currentThread();
				Thread.sleep(50);
			} catch (Exception e) {

			}
		}
		JOptionPane.showMessageDialog(map, p.getName() + ", Ur turnz over");
		map.byeByeTown();
		map.setVisible(true);
	}

	/**
	 * Generates random events to be applied to random players during the round
	 * 
	 * @param p
	 *            the player whose turn it is
	 * @param map
	 *            the current instance of the map
	 */
	private void generateEvent(Player p, MapScreen map) {
		Random rand = new Random();
		int eventChance = rand.nextInt(100);
		boolean pass = !(p == lowestP);
		if (eventChance < 28) {
			int eventNum = rand.nextInt(7);
			int m = 0;
			if (roundNum <= 3 && roundNum >= 1) {
				m = 25;
			} else if (roundNum <= 7 && roundNum >= 4) {
				m = 50;
			} else if (roundNum <= 11 && roundNum >= 8) {

			} else {
				m = 100;
			}
			switch (eventNum) {
			case 0:
				JOptionPane
						.showMessageDialog(
								map,
								"You Just Received A Package From The GT Alumni Containing 3 Food And 2 Energy Units.");
				p.changeFood(3);
				p.changeEnergy(2);
				break;
			case 1:
				JOptionPane
						.showMessageDialog(map,
								"A Wandering Tech Student Repaid Your Hospitality By Leaving Two Bars Of Ore.");
				p.changeOre(2);
				break;
			case 2:
				JOptionPane.showMessageDialog(map,
						"The Museum Bought Your Antique Personal Computer For $"
								+ 8 * m + ".");
				p.changeMoney(8 * m);
				break;
			case 3:
				JOptionPane.showMessageDialog(map,
						"You Found A Dead Moose Rat And Sold The Hide For $"
								+ 2 * m + ".");
				p.changeMoney(2 * m);
				break;
			case 4:
				if (pass) {
					JOptionPane.showMessageDialog(map,
							"Flying Cat-Bugs Ate The Roof Off Your House. Repairs Cost $"
									+ 4 * m + ".");
					p.changeMoney(-4 * m);
				}
				break;
			case 5:
				if (pass) {
					JOptionPane
							.showMessageDialog(
									map,
									"Mischevious UGA Students Broke Into Your Storage Shed And Stole Half Your Food.");
					int total = p.getFood();
					p.changeFood(-(total / 2));
				}
				break;
			case 6:
				if (pass) {
					JOptionPane.showMessageDialog(map,
							"Your Space Gypsy Inlaws Made A Mess Of The Town. It Cost You $"
									+ 6 * m + " To Clean It Up.");
					p.changeMoney(-6 * m);
				}
				break;
			}

		}
	}

	/**
	 * Checks the amount of time left in the player's turn
	 */
	public boolean isTurnOver() {
		return turnOver;
	}

	/**
	 * Determines when a turn is over
	 * 
	 * @param over
	 *            true if turn is over, false otherwise
	 */
	public static void setTurnOver(boolean over) {
		turnOver = over;
	}

	/**
	 * Calculates the player's turn time based on their available resources
	 * 
	 * @param p
	 *            the player currently taking their turn
	 */
	private void calculatePlayerTime(Player p) {
		int food = p.getFood();
		if (food == 0) {
			turnTime = 5;
		} else {
			if (roundNum >= 1 && roundNum <= 4) {
				if (food >= 1 && food <= 2) {
					turnTime = 30;
				} else {
					turnTime = 50;
				}
			} else if (roundNum >= 5 && roundNum <= 8) {
				if (food >= 1 && food <= 3) {
					turnTime = 30;
				} else {
					turnTime = 50;
				}
			} else if (roundNum >= 9 && roundNum <= 12) {
				if (food >= 1 && food <= 4) {
					turnTime = 30;
				} else {
					turnTime = 50;
				}
			}

		}
	}

	/**
	 * Determines how much time is left in a player's turn
	 * 
	 * @return the number of seconds left in the turn
	 */
	public int timeLeft() {
		return turnTime - secCounter;
	}

	/**
	 * Gets the round number
	 * 
	 * @return The current round number
	 */
	public int getRoundNum() {
		return roundNum;
	}

	/**
	 * Handles the timer tasks for the round.
	 */
	private static ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			turnTime--;
			if (turnTime < 0) {
				setTurnOver(true);
				timer.stop();

			}

		}
	};
}
