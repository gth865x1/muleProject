import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The Store Screen allows players to see a current inventory of goods, purchase
 * them, and sell goods to the store.
 * 
 * @author Decidedly Undecided
 * 
 */
public class StoreScreen extends JFrame implements Screen, Serializable {

	private boolean running;
	private JPanel pricePanel, yourQuantity, yourStockPanel;
	private JLabel howManyFood, howManyEnrg, howManyOre, muleLabel, foodLabel,
			energyLabel, oreLabel, muleType, welcomeLabel, currStockLabel,
			purchLabel, spaceLabel2, yourStockLabel, yourMuleLabel,
			yourFoodLabel, yourEnergyLabel, yourOreLabel, yourBalanceLabel;
	private JTextField quantFood, quantEnergy, quantOre;
	private JComboBox<String> muleTypes;
	private JButton buyNow;

	private int muleStock, oreStock, energyStock, foodStock;
	private Player currentPlayer;
	private TownScreen town;

	private final int BASE_MULE_PRICE = 100, FOOD_MULE_BONUS = 25,
			ORE_MULE_BONUS = 75, ENERGY_MULE_BONUS = 50, ORE_PRICE = 50,
			ENERGY_PRICE = 25, FOOD_PRICE = 30;

	/**
	 * Creates a StoreScreen with the current store and player inventory levels,
	 * and prompts the player whose turn it is to purchase or sell something.
	 */
	public StoreScreen(int difficulty) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(5, 1));

		setRunning(true);
		welcomeLabel = new JLabel("Welcome  to the MULE Store!", JLabel.CENTER);
		add(welcomeLabel);
		if (difficulty == 0) {
			muleStock = 25;
			oreStock = 0;
			energyStock = 16;
			foodStock = 16;
		} else {
			muleStock = 14;
			oreStock = 8;
			energyStock = 8;
			foodStock = 8;
		}

		currStockLabel = new JLabel("Here's our current stock: ");
		muleLabel = new JLabel(
				"<html>MULEs in Stock: "
						+ muleStock
						+ " for "
						+ BASE_MULE_PRICE
						+ " credits each. *Subject to additional pricing based on MULE type."
						+ "* . <br>(Food mules cost an additional: 25, Ore Mules additional: 75, Energy Mules additional: 50) </html>");
		foodLabel = new JLabel("Food in Stock: " + foodStock + " for "
				+ FOOD_PRICE + " credits each. ");
		oreLabel = new JLabel("Ore in Stock: " + oreStock + " for " + ORE_PRICE
				+ " credits each. ");
		energyLabel = new JLabel("<html>Energy in Stock: " + energyStock
				+ " for " + ENERGY_PRICE + " credits each. <br><br></html>");

		yourStockLabel = new JLabel("Your stock: ");
		yourMuleLabel = new JLabel("You have no MULEs!");
		yourFoodLabel = new JLabel("Your food in Stock: 0");
		yourOreLabel = new JLabel("Your ore in Stock: 0");
		yourEnergyLabel = new JLabel("Your energy in Stock: 0");
		yourBalanceLabel = new JLabel("Your current balance: 0");

		pricePanel = new JPanel(new GridLayout(5, 1));
		pricePanel.add(currStockLabel);
		pricePanel.add(muleLabel);
		pricePanel.add(foodLabel);
		pricePanel.add(oreLabel);
		pricePanel.add(energyLabel);

		add(pricePanel);

		yourStockPanel = new JPanel(new GridLayout(6, 1));
		yourStockPanel.add(yourStockLabel);
		yourStockPanel.add(yourMuleLabel);
		yourStockPanel.add(yourFoodLabel);
		yourStockPanel.add(yourOreLabel);
		yourStockPanel.add(yourEnergyLabel);
		yourStockPanel.add(yourBalanceLabel);

		add(yourStockPanel);

		muleType = new JLabel("If you wanted a MULE, Select your MULE type : ");
		String[] mules = { "", "Energy", "Food", "Ore" };
		muleTypes = new JComboBox<String>(mules);

		howManyFood = new JLabel(
				"How much food would you like? (Enter a positive number to buy. Negative number to sell)");
		quantFood = new JTextField(10);

		howManyOre = new JLabel(
				"How much ore would you like? (Enter a positive number to buy. Negative number to sell)");
		quantOre = new JTextField(10);

		howManyEnrg = new JLabel(
				"How much energy would you like? (Enter a positive number to buy. Negative number to sell)");
		quantEnergy = new JTextField(10);

		yourQuantity = new JPanel(new GridLayout(6, 2));
		purchLabel = new JLabel("Make A Purchase!");
		yourQuantity.add(purchLabel);

		spaceLabel2 = new JLabel("");
		yourQuantity.add(spaceLabel2);
		yourQuantity.add(muleType);
		yourQuantity.add(muleTypes);
		yourQuantity.add(howManyFood);
		yourQuantity.add(quantFood);
		yourQuantity.add(howManyOre);
		yourQuantity.add(quantOre);
		yourQuantity.add(howManyEnrg);
		yourQuantity.add(quantEnergy);

		add(yourQuantity);

		buyNow = new JButton("Buy Now!");
		buyNow.addActionListener(new BuyListener());
		add(buyNow);
	}

	/**
	 * Determines whether the screen is running or not
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * Sets the screen's running
	 * 
	 * @param run
	 *            a boolean, true if the screen is running, false otherwise
	 */
	public void setRunning(boolean run) {
		running = run;

	}

	/**
	 * Handles all purchases, deducts money from the player's totals and adds to
	 * their resources.
	 * 
	 * @author Decidedly Undecided
	 * 
	 */
	private class BuyListener implements ActionListener, Serializable {
		public void actionPerformed(ActionEvent e) {
			int total = 0;

			int muleCost = 0;
			int muleBought = 0;
			int type = 0;
			int mt = muleTypes.getSelectedIndex();

			if (!currentPlayer.hasMule()) {
				if (mt == 1) { // energy
					muleCost = BASE_MULE_PRICE + ENERGY_MULE_BONUS;
					muleBought = 1;
					type = 3;
				} else if (mt == 2) { // food
					muleCost = BASE_MULE_PRICE + FOOD_MULE_BONUS;
					muleBought = 1;
					type = 1;
				} else if (mt == 3) { // ore
					muleCost = BASE_MULE_PRICE + ORE_MULE_BONUS;
					muleBought = 1;
					type = 2;
				}
			}
			int foodBought = 0, oreBought = 0, energyBought = 0;
			if (!quantFood.getText().equals("")) {
				foodBought = Integer.parseInt(quantFood.getText());
			}
			if (foodBought > foodStock) {
				JOptionPane.showMessageDialog(getContentPane(),
						"You can't buy that much!");
				return;
			} else if (-foodBought > currentPlayer.getFood()) {
				JOptionPane.showMessageDialog(getContentPane(),
						"You don't own that much!");
				return;
			}
			if (!quantOre.getText().equals("")) {
				oreBought = Integer.parseInt(quantOre.getText());
			}
			if (oreBought > oreStock) {
				JOptionPane.showMessageDialog(getContentPane(),
						"You can't buy that much!");
				return;
			} else if (-oreBought > currentPlayer.getOre()) {
				JOptionPane.showMessageDialog(getContentPane(),
						"You don't own that much!");
				return;
			}
			if (!quantEnergy.getText().equals("")) {
				energyBought = Integer.parseInt(quantEnergy.getText());
			}
			if (energyBought > energyStock) {
				JOptionPane.showMessageDialog(getContentPane(),
						"You can't buy that much!");
				return;
			} else if (-energyBought > currentPlayer.getEnergy()) {
				JOptionPane.showMessageDialog(getContentPane(),
						"You don't own that much!");
				return;
			}

			int foodCost, oreCost, energyCost;
			foodCost = foodBought * FOOD_PRICE;
			oreCost = oreBought * ORE_PRICE;
			energyCost = energyBought * ENERGY_PRICE;

			total = total + foodCost + oreCost + energyCost + muleCost;
			// System.out.println(total);
			if (currentPlayer.getMoney() >= total) {
				currentPlayer.changeFood(foodBought);
				currentPlayer.changeEnergy(energyBought);
				currentPlayer.changeOre(oreBought);
				currentPlayer.changeMoney(-1 * total);
				foodStock -= foodBought;
				energyStock -= energyBought;
				oreStock -= oreBought;
				muleStock -= muleBought;
				if (type != 0) {
					MULE mule = new MULE(currentPlayer, type);
				}
				JOptionPane.showMessageDialog(getContentPane(),
						"You just made a purchase for $" + total);
				setVisible(false);
				town.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(getContentPane(),
						"You can't afford that!");
				return;
			}

		}

	}

	/**
	 * Updates the window with the current stock levels for both the store and
	 * the player. useful should a player choose to make a second trip to the
	 * store during their turn.
	 */
	public void updateWindow() {
		this.setSize(new Dimension(900, 900));
		muleLabel
				.setText("<html>MULEs in Stock: "
						+ muleStock
						+ " for "
						+ BASE_MULE_PRICE
						+ " credits each. *Subject to additional pricing based on MULE type."
						+ "* . <br>(Food mules cost an additional: 25, Ore Mules additional: 75, Energy Mules additional: 50) </html>");
		foodLabel.setText("Food in Stock: " + foodStock + " for " + FOOD_PRICE
				+ " credits each. ");
		oreLabel.setText("Ore in Stock: " + oreStock + " for " + ORE_PRICE
				+ " credits each. ");
		energyLabel.setText("<html>Energy in Stock: " + energyStock + " for "
				+ ENERGY_PRICE + " credits each. <br><br></html>");
		if (currentPlayer.hasMule()) {
			yourMuleLabel.setText("You have a MULE!");
		} else {
			yourMuleLabel.setText("You have no MULEs!");
		}
		yourFoodLabel.setText("Your food in Stock: " + currentPlayer.getFood());
		yourOreLabel.setText("Your ore in Stock: " + currentPlayer.getOre());
		yourEnergyLabel.setText("Your energy in Stock: "
				+ currentPlayer.getEnergy());
		yourBalanceLabel.setText("Your current balance is "
				+ currentPlayer.getMoney());

		quantFood.setText("");
		quantEnergy.setText("");
		quantOre.setText("");
		muleTypes.setSelectedIndex(0);
	}

	/**
	 * Sets the player whose turn it is so that their resources and funds may be
	 * accessed for purchases and replenishments
	 * 
	 * @param current
	 */
	public void setCurrentPlayer(Player current) {
		currentPlayer = current;
	}

	/**
	 * Sets the TownScreen for easy return to town after making a purchase.
	 * 
	 * @param t
	 *            the town in MuleLandia
	 */
	public void setTownScreen(TownScreen t) {
		town = t;
	}
}
