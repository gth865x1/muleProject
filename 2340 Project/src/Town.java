import java.io.Serializable;

/**
 * Town is a subclass of Tile. It contains a unique icon denoting the Town on
 * the Map
 * 
 * @author Decidedly Undecided
 * 
 */

public class Town extends Tile implements Serializable {

	private Player ownership; // 1, 2, 3, 4 based on player
	int border; // 0 - blue 1-green 2-red 3-yellow 4 - grey
	String icon;

	/**
	 * Creates a Town Tile with the specified Town icon
	 */
	public Town() {
		icon = "T.png";
		ownership = new Player(0, 0);
	}

	/**
	 * gets the icon to be displayed on the tile
	 * 
	 * @return the name of the icon to be displayed on the tile
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * Sets which player owns the tile and changes the color of the tile's
	 * border accordingly
	 * 
	 * @param The
	 *            player that owns the tile
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
}