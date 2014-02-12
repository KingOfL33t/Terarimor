package net.jordaria.item;

import net.jordaria.text.Color;

/**
 * An enum of qualities for {@link Item items}.
 * Each quality contains stat chances, item text colors, and names of the quality.
 * 
 * @author Ches Burks
 *
 */
public enum Quality {
	NORMAL(0.25f,0.1f,0.025f,0.01f,5,0.0333f, Color.WHITE,"Normal"),
	RARE(0.5f,0.2f,0.05f,0.02f,10,0.0666f, Color.RED,"Rare"),
	LEGENDARY(0.99f,0.75f,0.5f,0.25f,20,0.2f, Color.CYAN,"Legendary");
	
	private float firstTypeChance;//chance of having an elemental damage/protection
	private float secondTypeChance;//chance of having a second elemental damage/protection
	private float thirdTypeChance;//chance of having a third elemental damage/protection
	private float fourthTypeChance;//chance of having a fourth type of damage/protection
	private int normalPower;//the normal damage or protection offered
	private float criticalChance;//chance of critical hit
	private Color color;
	private String name;
	
	/**
	 * Constructs a new {@link Quality}.
	 * 
	 * @param firstType chance of having an elemental damage/protection
	 * @param secondType chance of having a second elemental damage/protection
	 * @param thirdType chance of having a third elemental damage/protection
	 * @param fourthType chance of having a fourth type of damage/protection
	 * @param normal the base damage or protection offered
	 * @param crit /chance of critical hit
	 * @param color The color of the items name
	 * @param name The name of this quality
	 */
	private Quality(float firstType, float secondType, float thirdType, float fourthType, int normal, float crit, Color color, String name){
		this.firstTypeChance = firstType;
		this.secondTypeChance = secondType;
		this.thirdTypeChance = thirdType;
		this.fourthTypeChance = fourthType;
		this.normalPower = normal;
		this.criticalChance = crit;
		this.color = color;
		this.name = name;
	}

	/**
	 * Returns the chance of having an elemental damage/protection.
	 * This is a float value from 0.0 to 1.0, with 1.0 as a 100% chance 
	 * of having at least one element.
	 * 
	 * @return The chance of having the first elemental type
	 */
	public float getFirstTypeChance() {
		return firstTypeChance;
	}

	/**
	 * Returns the chance of having a second elemental damage/protection.
	 * This is a float value from 0.0 to 1.0, with 1.0 as a 100% chance 
	 * of having a second element.
	 * 
	 * @return The chance of having the second elemental type
	 */
	public float getSecondTypeChance() {
		return secondTypeChance;
	}

	/**
	 * Returns the chance of having a third elemental damage/protection.
	 * This is a float value from 0.0 to 1.0, with 1.0 as a 100% chance 
	 * of having a second element.
	 * 
	 * @return The chance of having the third elemental type
	 */
	public float getThirdTypeChance() {
		return thirdTypeChance;
	}

	/**
	 * Returns the chance of having a fourth elemental damage/protection.
	 * This is a float value from 0.0 to 1.0, with 1.0 as a 100% chance 
	 * of having a second element.
	 * 
	 * @return The chance of having the fourth elemental type
	 */
	public float getFourthTypeChance() {
		return fourthTypeChance;
	}

	/**
	 * Returns the maximum normal (base) power of the item.
	 * In {@link ItemArmor armor} this is the normal {@link Protection}.
	 * In {@link ItemWeapon weapons} this is the normal {@link Damage}.
	 * 
	 * @return The normal power
	 */
	public int getNormalPower() {
		return normalPower;
	}

	/**
	 * Returns the chance of a critical hit.
	 * This is a float value from 0.0 to 1.0, with 1.0 as a 100% chance 
	 * of a crit.
	 * 
	 * @return The chance of getting a critical hit
	 */
	public float getCriticalChance() {
		return criticalChance;
	}
	
	/**
	 * Returns the {@link Color color} of the {@link Item items} name.
	 * 
	 * @return The item name's color
	 */
	public Color getColor(){
		return color;
	}
	
	/**
	 * Return the name of the {@link Quality}. This is a human readable version.
	 * 
	 * @return The quality name
	 */
	public String getName(){
		return name;
	}
}
