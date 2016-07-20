import java.io.Serializable;

/**
 * Creates a subclass of Tile for the Mountain2 version of Mountain, checks
 * ownership and produces resources for that Mountain.
 * 
 * @author Decidedly Undecided
 * 
 */

public class Mountain2 extends Tile implements Serializable {
	private MULE mule;
	private Player ownership; // 1, 2, 3, 4 based on player
	int border; // 0 - blue 1-green 2-red 3-yellow 4 - grey
	String icon;

	/**
	 * Constructs a Mountain2 version of a Mountain tile and sets the specific
	 * icon for this mountain.
	 */
	public Mountain2() {
		icon = "M2.png";
	}

	/**
	 * Provided the Mountain2 tile is owned, produces mountain2-specific
	 * resources and adds them to the owning player's resource total.
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
					ownership.changeOre(3);
				}
			}
		}
	}

	/**
	 * Gets the png representation of Mountain2 Tile
	 * 
	 * @return the icon for Mountain2 Tile
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * Sets the ownership for a Mountain2 tile to the specified player and
	 * changes the tile's border color to their chosen color
	 * 
	 * @param p
	 *            The player who owns the tile
	 */
	public void setOwnership(Player p) {
		ownership = p;
		border = p.getColor();
	}

	/**
	 * Retrieves the ownership of a Mountain2 for use in determining if the tile
	 * is available or is already owned.
	 * 
	 * @return The player who owns said tile
	 */
	public Player getOwnership() {
		return ownership;
	}

	/**
	 * sets the Mule for the particular mountain 2 tile with specific icons for
	 * each type
	 * 
	 * @param m
	 *            the mule being placed on the tile
	 */
	public void setMule(MULE m) {
		mule = m;
		switch (mule.getType()) {
		case ENERGY:
			icon = "M2Energy.png";
			break;
		case FOOD:
			icon = "M2Food.png";
			break;
		case ORE:
			icon = "M2Ore.png";
		}
	}

	/**
	 * gets the mule for reference in other classes
	 * 
	 * @return the mule on the mountain 2 tile
	 */
	public MULE getMule() {
		return mule;
	}
}