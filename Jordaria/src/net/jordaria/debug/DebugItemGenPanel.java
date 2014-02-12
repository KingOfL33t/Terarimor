package net.jordaria.debug;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.jordaria.Jordaria;
import net.jordaria.event.EventHandler;
import net.jordaria.event.EventPriority;
import net.jordaria.event.Listener;
import net.jordaria.event.events.ShuttingDown;
import net.jordaria.item.Element;
import net.jordaria.item.Item;
import net.jordaria.item.ItemArmor;
import net.jordaria.item.ItemNothing;
import net.jordaria.item.ItemPotion;
import net.jordaria.item.ItemWeapon;
import net.jordaria.item.Quality;
import net.jordaria.math.ItemGeneration;

/**
 * A panel for testing generation of various items.
 * 
 * @author Ches Burks
 *
 */
public class DebugItemGenPanel implements ActionListener, Listener{

	public Jordaria jd;

	public JFrame frame;
	public JButton b_genItem;
	public JButton b_genWeapon;
	public JButton b_genArmor;
	public JButton b_genPotion;

	public JLabel l_itemName;
	public JLabel l_itemDamage;
	public JLabel l_itemQuality;
	public JLabel l_itemLore;
	public JLabel l_itemType;

	public JTextField t_itemName;
	public JTextArea t_itemDamage;
	public JTextField t_itemQuality;
	public JTextArea t_itemLore;
	public JTextField t_itemType;

	public Item item;
	public ItemGeneration generator;

	/**
	 * Constructs a new DebugItemGenPanel and sets up the components.
	 */
	public DebugItemGenPanel(){
		frame = new JFrame("Debug Panel");
		frame.setSize(400,500);
		frame.setLayout(new GridLayout(7, 2));

		item = new ItemNothing();
		generator = new ItemGeneration();

		b_genItem = new JButton("Gen Item");
		b_genWeapon = new JButton("Gen Weapon");
		b_genArmor = new JButton("Gen Armor");
		b_genPotion = new JButton("Gen Potion");

		b_genItem.addActionListener(this);
		b_genWeapon.addActionListener(this);
		b_genArmor.addActionListener(this);
		b_genPotion.addActionListener(this);


		l_itemName = new JLabel("Name");
		l_itemDamage = new JLabel("Damage");
		l_itemQuality = new JLabel("Quality");
		l_itemLore = new JLabel("Lore");
		l_itemType = new JLabel("Type");

		t_itemName = new JTextField();
		t_itemDamage = new JTextArea(9, 1);
		t_itemQuality = new JTextField();
		t_itemLore = new JTextArea(4, 1);
		t_itemType = new JTextField();

		frame.add(b_genItem);
		frame.add(b_genWeapon);
		frame.add(b_genArmor);
		frame.add(b_genPotion);

		frame.add(l_itemName);
		frame.add(t_itemName);
		frame.add(l_itemDamage);
		frame.add(t_itemDamage);
		frame.add(l_itemQuality);
		frame.add(t_itemQuality);
		frame.add(l_itemLore);
		frame.add(t_itemLore);
		frame.add(l_itemType);
		frame.add(t_itemType);


		frame.setVisible(true);
	}
	
	/**
	 * Sets the reference to the main program.
	 * 
	 * @param jordaria A reference to the main program
	 */
	public void setJordariaVar(Jordaria jordaria){
		this.jd = jordaria;
	}

	/**
	 * Removes all components, hides the frame, 
	 * and removes the reference to the frame for 
	 * garbage collection to clear out.
	 * 
	 * @param event The event that was fired
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onShutdown(ShuttingDown event){
		this.jd = null;
		this.frame.removeAll();
		this.frame.setVisible(false);
		this.frame = null;
	}

	/**
	 * Determines what action was performed, and 
	 * generates the requested item if a button was 
	 * pressed
	 * 
	 * @param e The ActionEvent that occurred
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(b_genItem)){
			item = generator.generateRandomItem();
			updateFields();
		}
		else if (e.getSource().equals(b_genWeapon)){
			item = generator.generateWeapon();
			updateFields();
		}
		else if (e.getSource().equals(b_genArmor)){
			item = generator.generateArmor();
			updateFields();
		}
		else if (e.getSource().equals(b_genPotion)){
			item = generator.generatePotion();
			updateFields();
		}

	}


	/**
	 * Updates all fields to contain their respective values
	 */
	private void updateFields(){
		String type = "Nothing";
		if (item instanceof ItemArmor){
			type = "Armor";
		}
		else if (item instanceof ItemWeapon){
			type = "Weapon";
		}
		else if (item instanceof ItemPotion){
			type = "Potion";
		}

		if (type == "Nothing"){
			//nothing
			resetFields();
			t_itemName.setText("???");
			t_itemDamage.append("+0");
			t_itemQuality.setText(Quality.NORMAL.getName());
			t_itemLore.append("N/A");
			t_itemType.setText("Nothing");
		}
		else if (type == "Armor"){
			ItemArmor theItem = (ItemArmor) item;
			resetFields();
			t_itemName.setText(item.getItemName().getText());
			for (Element element: theItem.getProtection().getProtections().keySet()){
				t_itemDamage.append("+"+theItem.getProtection().getProtection(element)+" "+element.getName()+"\n");
			}
			t_itemQuality.setText(theItem.getQuality().getName());
			for (int i=1;i<=3;i++){
				t_itemLore.append(theItem.getLore().getLine(i).getText());
			}
			t_itemType.setText("Armor");
		}
		else if (type == "Weapon"){
			ItemWeapon theItem = (ItemWeapon) item;
			resetFields();
			t_itemName.setText(item.getItemName().getText());
			for (Element element: theItem.getDamage().getDamages().keySet()){
				t_itemDamage.append("+"+theItem.getDamage().getDamage(element)+" "+element.getName()+"\n");
			}
			t_itemQuality.setText(theItem.getQuality().getName());
			for (int i=1;i<=3;i++){
				t_itemLore.append(theItem.getLore().getLine(i).getText());
			}
			t_itemType.setText("Weapon");
		}
		else if (type == "Potion"){
			ItemPotion theItem = (ItemPotion) item;
			resetFields();
			t_itemName.setText(theItem.getItemName().getText());
			t_itemDamage.append("+0");
			t_itemQuality.setText(Quality.NORMAL.getName());
			t_itemLore.append("N/A");
			t_itemType.setText("Potion");
		}
	}
	
	/**
	 * Resets all text fields
	 */
	private void resetFields(){
		t_itemName.setText("");
		t_itemDamage.setText("");
		t_itemQuality.setText("");
		t_itemLore.setText("");
		t_itemType.setText("");
	}

}
