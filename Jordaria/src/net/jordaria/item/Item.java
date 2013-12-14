package net.jordaria.item;

import net.jordaria.text.ColoredText;

public class Item {
	public ColoredText itemName;
	
	public boolean isStackable(){
		return false;
	}
	public boolean canStackWith(Item other){
		return false;		
	}
	public int getMaxStackSize(){
		return 1;
	}
}
