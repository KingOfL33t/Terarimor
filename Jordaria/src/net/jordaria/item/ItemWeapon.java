package net.jordaria.item;

/**
 * An {@link Item} subclass representing a weapon ingame.
 * 
 * @author Ches Burks
 *
 */
public class ItemWeapon extends Item{
	public Damage damage;
	public Lore lore;
	public Quality quality;
	
	/**
	 * Returns the {@link Damage} for the weapon.
	 * 
	 * @return The damage
	 */
	public Damage getDamage() {
		return damage;
	}
	
	/**
	 * Sets the weapons {@link Damage}.
	 * 
	 * @param damage The damage to use
	 */
	public void setDamage(Damage damage) {
		this.damage = damage;
	}
	
	/**
	 * Returns the items {@link Lore lore}.
	 * 
	 * @return The lore
	 */
	public Lore getLore() {
		return lore;
	}
	
	/**
	 * Sets the items {@link Lore lore}.
	 * 
	 * @param lore The lore
	 */
	public void setLore(Lore lore) {
		this.lore = lore;
	}
	
	/**
	 * Returns the items {@link Quality quality}.
	 * 
	 * @return The quality
	 */
	public Quality getQuality() {
		return quality;
	}
	
	/**
	 * Sets the items {@link Quality quality}.
	 * 
	 * @param quality The quality
	 */
	public void setQuality(Quality quality) {
		this.quality = quality;
	}
	
}
