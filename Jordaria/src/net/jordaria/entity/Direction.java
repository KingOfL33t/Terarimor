package net.jordaria.entity;

public class Direction {
	public float angle = 0;
	
	public Direction(float degrees){
		this.angle = degrees;
	}
	public float getAngle(){
		return this.angle;
	}
	
	public void addAngle(float ammount){
		this.angle += ammount;
		while (angle > 360.0f){
			angle-=360.0f;//if it is above 360, reduce it down to 0<=x<=360
		}
	}
	
	public void subtractAngle(float ammount){
		this.angle -= ammount;
		while (angle < 0.0f){
			angle+=360.0f;//if it is below 0, increase it to 0<=x<=360
		}
	}
	
	/*
	 * Combines the two directions to form a new direction
	 */
	public Direction getRelativeDirection(Direction offset){
		Direction returnedDirection = new Direction(this.getAngle());
		returnedDirection.addAngle(offset.getAngle());
		return returnedDirection;
	}
}
