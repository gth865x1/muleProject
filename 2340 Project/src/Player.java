import java.io.Serializable;

/**
 * The Player class represents a player's character in a game of MULE. It holds
 * their resources and basic information
 * 
 * @author Decidedly Undecided
 * 
 */

public class Player implements Serializable {

	int playerNumber;

	private String name;
	private int color; // 0-blue 1-green 2-red 3-yellow
	private int race; // 0-packer 1-spheroid 2-humanoid 3-leggite 4-flapper
						// 5-bonzoid 6-mechtron 7-gollumer
	private int money;
	private int ore;
	private int food;
	private int energy;
	private int land;
	private int freeLand;
	private MULE mule;
	private boolean hasMule;

	/**
	 * Creates a player with a player number and sets default resource values
	 * 
	 * @param playerNumber
	 *            an used to identify the player
	 */

	Player(int playerNumber, int d) {
		this.name = ("Player " + playerNumber);
		this.color = 0;
		this.race = 0;
		this.money = 0;
		this.ore = 0;
		if (d == 0) {
			this.food = 8; // default for beginner
			this.energy = 4;
		} else {
			this.food = 4; // default for standard/ tournament
			this.energy = 2;
		}
		this.playerNumber = playerNumber;
		this.freeLand = 2;
	}

	/**
	 * Gets the amount of money a player has
	 * 
	 * @return the amount of money a player has
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * Change the amount of money the player has
	 * 
	 * @param howMuch
	 *            the amount the money was changed by
	 */
	public void changeMoney(int howMuch) {
		money += howMuch;
	}

	/**
	 * Gets the amount of energy a player has
	 * 
	 * @return the amount of energy a player has
	 */
	public int getEnergy() {
		return energy;
	}

	/**
	 * Change the amount of energy the player has
	 * 
	 * @param howMuch
	 *            the amount the energy was changed by
	 */
	public void changeEnergy(int howMuch) {
		energy += howMuch;
	}

	/**
	 * Gets the amount of food a player has
	 * 
	 * @return the amount of food a player has
	 */
	public int getFood() {
		return food;
	}

	/**
	 * Change the amount of food the player has
	 * 
	 * @param howMuch
	 *            the amount the food was changed by
	 */
	public void changeFood(int howMuch) {
		food += howMuch;
	}

	/**
	 * Gets the amount of ore a player has
	 * 
	 * @return the amount of ore a player has
	 */
	public int getOre() {
		return ore;
	}

	/**
	 * Change the amount of ore the player has
	 * 
	 * @param howMuch
	 *            the amount the ore was changed by
	 */
	public void changeOre(int howMuch) {
		ore += howMuch;
	}

	/**
	 * Gets the player's color
	 * 
	 * @return the player's color
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Sets the player's color
	 */
	public void setColor(int color) {
		this.color = color;
	}

	/**
	 * Gets the player's race
	 * 
	 * @return the player's race
	 */
	public int getRace() {
		return race;
	}

	/**
	 * Sets the player's color
	 * 
	 */
	public void setRace(int race) {
		this.race = race;
	}

	/**
	 * Gets the player's name
	 * 
	 * @return the player's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the player's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the value of the player's goods
	 * 
	 * @return the value of the player's goods
	 */
	public int getGoods() {
		return food * 30 + ore * 50 + energy * 25;
	}

	/**
	 * Changes how much land a player has
	 * 
	 * @param howMuch
	 *            The amount of land to increment by
	 */
	public void changeLand(int howMuch) {
		land += howMuch;
	}

	/**
	 * Gets the player's land
	 * 
	 * @return the amount of land the player owns
	 */
	public int getLand() {
		return land;
	}

	/**
	 * gets the player's score including their goods, money, and land
	 * 
	 * @return The player's score with all assets combined
	 */
	public int getScore() {
		return getGoods() + money + (land * 500);
	}

	/**
	 * Gets the number of plots of land the player can get for free
	 * 
	 * @return The number of free land plots the player has left
	 */
	public int getFreeLand() {
		return freeLand;
	}

	/**
	 * Reduces the number of free land plots the player has left
	 */
	public void decrementFreeLand() {
		freeLand--;
	}

	/**
	 * Sets whether the player has a mule or not.
	 * 
	 * @param m
	 *            a player's potential mule
	 */
	public void setMule(MULE m) {
		mule = m;
		if (m == null) {
			hasMule = false;
		} else {
			hasMule = true;
		}
	}

	/**
	 * Retrieves the player's mule
	 * 
	 * @return the mule the player owns
	 */
	public MULE getMule() {
		return mule;
	}

	/**
	 * Determines whether a player has a mule or not
	 * 
	 * @return hasMule True if the player has a mule, false otherwise
	 */
	public boolean hasMule() {
		return hasMule;
	}
}
