package net.jordaria.entity;


public class Location {
	
	public double posX;
	public double posY;

	public Location(double xPos, double yPos){
		this.posX = xPos;
		this.posY = yPos;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public void setPositions(double posX, double posY){
		this.posX = posX;
		this.posY = posY;
	}
	
}
