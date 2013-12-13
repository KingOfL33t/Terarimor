package net.jordaria.item;

import net.jordaria.text.Color;

public class Lore {
	public String lore;
	public Color color;
	
	public Lore(String lore){
		this.lore = lore;
	}
	public Lore(String lore, Color loreColor){
		this.lore = lore;
		this.color = loreColor;
	}
}
