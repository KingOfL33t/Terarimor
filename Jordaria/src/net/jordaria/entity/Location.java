package net.jordaria.entity;


public class Location {
	
	public double posX;
	public double posY;
	public double posZ;

	public Location(double xPos, double yPos, double zPos){
		this.posX = xPos;
		this.posY = yPos;
		this.posZ = zPos;
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

	public double getPosZ() {
		return posZ;
	}

	public void setPosZ(double posZ) {
		this.posZ = posZ;
	}
	public void setPositions(double posX, double posY, double posZ){
		this.posX = posZ;
		this.posY = posY;
		this.posZ = posZ;
	}
	
}
