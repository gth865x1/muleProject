import java.io.Serializable;

/**
 * The Plain class is a subclass of Tile specializing in the resources and
 * iconography of a plain.
 * 
 * @author Decidedly Undecided
 * 
 */

public class Plain extends Tile implements Serializable {
	private MULE mule;
	private Player ownership; // 1, 2, 3, 4 based on player
	int border; // 0 - blue 1-green 2-red 3-yellow 4 - grey
	String icon;

	/**
	 * Constructs a Plain tile with the specific plain icon.
	 */
	public Plain() {
		icon = "P.png";
	}

	/**
	 * Checks to ensure the Plain tile has an owner before producing specific
	 * resources and adding them to the player's resource total.
	 */
	public void produceResources() {
		if (ownership == null) {
		} else {
			if ((mule != null) && (ownership.getEnergy() > 0)) {
				ownership.changeEnergy(-1);
				switch (mule.getType()) {
				case ENERGY:
					ownership.changeEnergy(3);
					break;
				case FOOD:
					ownership.changeFood(2);
					break;
				case ORE:
					ownership.changeOre(1);
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
	 * Sets the ownership for a Plain tile to the specified player and changes
	 * the tile's border color to that player's chosen color
	 * 
	 * @param p
	 *            The player who owns the Plain tile
	 */
	public void setOwnership(Player p) {
		ownership = p;
		border = p.getColor();
	}

	/**
	 * Retrieves the ownership of a Plain tile for use in determining if the
	 * tile is available or is already owned.
	 * 
	 * @return The player who owns said tile
	 */
	public Player getOwnership() {
		return ownership;
	}

	/**
	 * sets the Mule for the particular plain tile with specific icons for each
	 * type
	 * 
	 * @param m
	 *            the mule being placed on the tile
	 */
	public void setMule(MULE m) {
		mule = m;
		switch (mule.getType()) {
		case ENERGY:
			icon = "PEnergy.png";
			break;
		case FOOD:
			icon = "PFood.png";
			break;
		case ORE:
			icon = "POre.png";
		}
	}

	/**
	 * gets the mule for reference in other classes
	 * 
	 * @return the mule on the plain tile
	 */
	public MULE getMule() {
		return mule;
	}
}