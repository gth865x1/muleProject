import java.io.Serializable;

/**
 * Creates the MULE with a type and owner to be referenced throughout the game
 * 
 * @author Decidedly Undecided
 * 
 */
public class MULE implements Serializable {
	private Player owner;
	private MuleType type;

	/**
	 * Constructs a mule and references the MuleType class to enumerate its type
	 * 
	 * @param p
	 *            the player who owns the mule
	 * @param t
	 *            an integer value that is used to translate into enumerated
	 *            MuleTypes
	 */
	public MULE(Player p, int t) {
		owner = p;
		p.setMule(this);
		if (t == 1) {
			type = MuleType.FOOD;
		} else if (t == 2) {
			type = MuleType.ORE;
		} else if (t == 3) {
			type = MuleType.ENERGY;
		}
	}

	/**
	 * Gets the player who owns the mule
	 * 
	 * @return the player who owns the mule
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * Gets the type of mule, used in resource production and cost calculations
	 * 
	 * @return the type of MULE (food, ore, or energy)
	 */
	public MuleType getType() {
		return type;
	}
}
