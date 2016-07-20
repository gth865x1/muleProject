import java.io.Serializable;

/**
 * The Tile class lays the foundation for the Map, as well as denotes which
 * player owns it, and which resources it should produce.
 * 
 * @author Decidedly Undecided
 * 
 */
public class Tile implements Serializable {
	private MULE mule;
	private Player ownership; // based on player
	int border; // 0 - blue 1-green 2-red 3-yellow 4-grey defualt
	String icon;

	/**
	 * Constructs a basic tile with a default grey border. Grey indicates the
	 * tile is unowned.
	 */
	public Tile() {
		border = 4;
	}

	/**
	 * Produces resources for the specified type of tile. More useful for
	 * subclasses.
	 */
	public void produceResources() {
	}

	/**
	 * Sets the ownership of a tile and sets the border with that player's
	 * chosen color
	 * 
	 * @param p
	 *            The player taking ownership of the tile
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
	 * A helpful method for building the GUI portion of the Tile Map
	 * 
	 * @return the string name of the Tile's icon.
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * Sets a mule on that particular tile
	 * 
	 * @param m
	 *            the Mule being placed on the tile
	 */
	public void setMule(MULE m) {
		mule = m;
	}

	/**
	 * Retrieves the mule on the tile
	 * 
	 * @return the mule on the tile currently
	 */
	public MULE getMule() {
		return mule;
	}

}