import java.io.Serializable;

/**
 * Extends the Tile class to create a third form of Mountain and produces
 * resources specific to said Mountain3.
 * 
 * @author Decidedly Undecided
 * 
 */

public class Mountain3 extends Tile implements Serializable {
	private MULE mule;
	private Player ownership; // 1, 2, 3, 4 based on player
	int border; // 0 - blue 1-green 2-red 3-yellow 4 - grey
	String icon;

	/**
	 * Creates the Mountain3 tile and denotes the specific icon for it.
	 */
	public Mountain3() {
		icon = "M3.png";
	}

	/**
	 * After checking for ownership, Mountain3 specific resources are produced
	 * and added to the owning player's resource totals.
	 */
	public void produceResources() {
		if (ownership == null) {
		} else {
			if ((mule != null) && (ownership.getEnergy() > 0)) {
				ownership.changeEnergy(-1);
				switch (mule.getType()) {
				case ENERGY:
					ownership.changeEnergy(1);
					break;
				case FOOD:
					ownership.changeFood(1);
					break;
				case ORE:
					ownership.changeOre(4);
				}
			}
		}
	}

	/**
	 * Gets the png representation of Mountain3 Tile
	 * 
	 * @return the icon for Mountain3 Tile
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * Sets the ownership for a Mountain3 tile to the specified player and
	 * changes the tile's border color to their chosen color
	 * 
	 * @param p
	 *            The player who owns the Mountain3 tile
	 */
	public void setOwnership(Player p) {
		ownership = p;
		border = p.getColor();
	}

	/**
	 * Retrieves the ownership of a tile for use in determining if the tile is
	 * available or is already owned.
	 * 
	 * @return The player who owns said tile
	 */
	public Player getOwnership() {
		return ownership;
	}

	/**
	 * sets the Mule for the particular mountain 3 tile with specific icons for
	 * each type
	 * 
	 * @param m
	 *            the mule being placed on the tile
	 */
	public void setMule(MULE m) {
		mule = m;
		switch (mule.getType()) {
		case ENERGY:
			icon = "M3Energy.png";
			break;
		case FOOD:
			icon = "M3Food.png";
			break;
		case ORE:
			icon = "M3Ore.png";
		}
	}

	/**
	 * gets the mule for reference in other classes
	 * 
	 * @return the mule on the mountain 3 tile
	 */
	public MULE getMule() {
		return mule;
	}
}