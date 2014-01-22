package net.jordaria.item;

public class ItemWeapon extends Item{
	public Damage damage;
	public Lore lore;
	public Quality quality;
	
	public Damage getDamage() {
		return damage;
	}
	public void setDamage(Damage damage) {
		this.damage = damage;
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
