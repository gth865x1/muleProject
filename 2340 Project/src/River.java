import java.io.Serializable;

/**
 * The River class is a subclass of Tile, created specifically to handle a river
 * tile's responsibilities.
 * 
 * @author Decidedly Undecided
 * 
 */

public class River extends Tile implements Serializable {
	private MULE mule;
	private Player ownership; // 1, 2, 3, 4 based on player
	int border; // 0 - blue 1-green 2-red 3-yellow 4 - grey
	String icon;

	/**
	 * Constructs a river tile with a river icon.
	 */
	public River() {
		icon = "R.png";
	}

	/**
	 * Produces the reources specific to a river by first making sure the river
	 * is owned by a player. Resources produced by the river are then added to a
	 * player's resource totals.
	 */
	public void produceResources() {
		if (ownership == null) {
		} else {
			if ((mule != null) && (ownership.getEnergy() > 0)) {
				ownership.changeEnergy(-1);
				switch (mule.getType()) {
				case ENERGY:
					ownership.changeEnergy(2);
					break;
				case FOOD:
					ownership.changeFood(4);
					break;
				case ORE:
					break;
				}
			}
		}
	}

	/**
	 * Gets the png representation of Plain Tile
	 * 
	 * @return the icon for the Plain Tile
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * Sets the ownership for a River tile to the specified player and changes
	 * the tile's border color to that player's chosen color
	 * 
	 * @param p
	 *            The player who owns the tile
	 */
	public void setOwnership(Player p) {
		ownership = p;
		border = p.getColor();
	}

	/**
	 * Retrieves the ownership of a River tile for use in determining if the
	 * tile is available or is already owned.
	 * 
	 * @return The player who owns said River tile
	 */
	public Player getOwnership() {
		return ownership;
	}

	/**
	 * sets the Mule for the particular river tile with specific icons for each
	 * type
	 * 
	 * @param m
	 *            the mule being placed on the tile
	 */
	public void setMule(MULE m) {
		mule = m;
		switch (mule.getType()) {
		case ENERGY:
			icon = "REnergy.png";
			break;
		case FOOD:
			icon = "RFood.png";
			break;
		case ORE:
			icon = "ROre.png";
		}
	}

	/**
	 * gets the mule for reference in other classes
	 * 
	 * @return the mule on the river tile
	 */
	public MULE getMule() {
		return mule;
	}
}