import java.io.Serializable;

/**
 * Mountain 1 is a subclass of Tile used to produce resources specific to this
 * type of mountain. It also is denoted on the Map with a unique mountain1 icon.
 * 
 * @author Decidedly Undecided
 * 
 */

public class Mountain1 extends Tile implements Serializable {

	private Player ownership; // 1, 2, 3, 4 based on player
	private MULE mule;
	int border; // 0 - blue 1-green 2-red 3-yellow 4 - grey
	String icon;

	/**
	 * Creates a Mountain1 Tile with the specified mountain1 icon
	 */
	public Mountain1() {
		icon = "M1.png";
	}

	/**
	 * After checking for ownership, mountain1 resources are produced and added
	 * to the owning player's resource totals.
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
					ownership.changeOre(2);
				}
			}
		}
	}

	/**
	 * Gets the png representation of Mountain1
	 * 
	 * @return the icon for Mountain1
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * Sets the ownership for a Mountain1 tile to the specified player and
	 * changes the tile's border color to their chosen color
	 * 
	 * @param p
	 *            The player who owns the Mountain1 tile
	 */
	public void setOwnership(Player p) {
		ownership = p;
		border = p.getColor();
	}

	/**
	 * Retrieves the ownership of a Mountain1 tile for use in determining if the
	 * tile is available or is already owned.
	 * 
	 * @return The player who owns said Mountain 1 tile
	 */
	public Player getOwnership() {
		return ownership;
	}

	/**
	 * Used when a player has a mule and wishes to set it on the mountain 1
	 * tile.
	 * 
	 * @param m
	 *            the mule being placed on the tile
	 */
	public void setMule(MULE m) {
		mule = m;
		switch (mule.getType()) {
		case ENERGY:
			icon = "M1Energy.png";
			break;
		case FOOD:
			icon = "M1Food.png";
			break;
		case ORE:
			icon = "M1Ore.png";
		}
	}

	/**
	 * Gets the mule for reference in other classes
	 * 
	 * @return the mule on the mountain 1 tile
	 */
	public MULE getMule() {
		return mule;
	}
}