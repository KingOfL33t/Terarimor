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
import net.jordaria.event.ShuttingDown;
import net.jordaria.item.Item;
import net.jordaria.item.ItemArmor;
import net.jordaria.item.ItemNothing;
import net.jordaria.item.ItemWeapon;
import net.jordaria.math.ItemGeneration;

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

		public DebugItemGenPanel(){
			frame = new JFrame("Debug Panel");
			frame.setSize(400,500);
			frame.setLayout(new GridLayout(2, 7));

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
		public void setJordariaVar(Jordaria jordaria){
			this.jd = jordaria;
		}

		@EventHandler(priority = EventPriority.MONITOR)
		public void onShutdown(ShuttingDown event){
			this.jd = null;
			this.frame.removeAll();
			this.frame.setVisible(false);
			this.frame = null;
		}

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

		private void updateFields(){
			String type = "Nothing";
			if (item instanceof ItemArmor){
				type = "Armor";
			}
			else if (item instanceof ItemWeapon){
				type = "Weapon";//TODO finishme 
			}
		}

	}
