package net.jordaria.item;

import net.jordaria.text.Color;

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

	public float getFirstTypeChance() {
		return firstTypeChance;
	}

	public float getSecondTypeChance() {
		return secondTypeChance;
	}

	public float getThirdTypeChance() {
		return thirdTypeChance;
	}

	public float getFourthTypeChance() {
		return fourthTypeChance;
	}

	public int getNormalPower() {
		return normalPower;
	}

	public float getCriticalChance() {
		return criticalChance;
	}
	
	public Color getColor(){
		return color;
	}
	
	public String getName(){
		return name;
	}
}
