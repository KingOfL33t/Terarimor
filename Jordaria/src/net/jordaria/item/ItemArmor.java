package net.jordaria.item;

public class ItemArmor extends Item{
	public Protection protection;
	public ArmorSlot slot;
	public Lore lore;
	public Quality quality;
	
	public Protection getProtection() {
		return protection;
	}
	public void setProtection(Protection protection) {
		this.protection = protection;
	}
	public ArmorSlot getSlot() {
		return slot;
	}
	public void setSlot(ArmorSlot slot) {
		this.slot = slot;
	}
	public Lore getLore() {
		return lore;
	}
	public void setLore(Lore lore) {
		this.lore = lore;
	}
	public Quality getQuality() {
		return quality;
	}
	public void setQuality(Quality quality) {
		this.quality = quality;
	}
	
}
