package net.jordaria.item;

/**
 * An {@link Item} subclass representing armor ingame.
 * 
 * @author Ches Burks
 *
 */
public class ItemArmor extends Item{
	public Protection protection;
	public ArmorSlot slot;
	public Lore lore;
	public Quality quality;
	
	/**
	 * Returns the {@link Protection} used by the armor.
	 * 
	 * @return The protection
	 */
	public Protection getProtection() {
		return protection;
	}
	
	/**
	 * Sets the armors {@link Protection}.
	 * 
	 * @param protection The protection to use
	 */
	public void setProtection(Protection protection) {
		this.protection = protection;
	}
	
	/**
	 * Returns what {@link ArmorSlot} the armor belongs in.
	 * 
	 * @return The slot
	 */
	public ArmorSlot getSlot() {
		return slot;
	}
	
	/**
	 * Sets the {@link ArmorSlot slot} the armor belongs in.
	 * 
	 * @param slot The slot
	 */
	public void setSlot(ArmorSlot slot) {
		this.slot = slot;
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
