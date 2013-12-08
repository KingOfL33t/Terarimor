package net.jordaria.entity;

public class Direction {
	//angles start facing the origin from the negative axis and increase as you move clockwise
	public float angle_x = 0;//the angle about the x axis. in degrees. 
	public float angle_y = 0;//the angle about the y axis, in degrees.
	public float angle_z = 0;//the angle about the z axis, in degrees.
	
	public Direction(float x, float y, float z){
		this.angle_x = x;
		this.angle_y = y;
		this.angle_z = z;
	}
	public float getAngleX(){
		return this.angle_x;
	}
	public float getAngleY(){
		return this.angle_y;
	}
	public float getAngleZ(){
		return this.angle_z;
	}
	
	public void addAngleX(float ammount){
		this.angle_x += ammount;
		while (angle_x > 360.0f){
			angle_x-=360.0f;//if it is above 360, reduce it down to 0<=x<=360
		}
	}
	public void addAngleY(float ammount){
		this.angle_y += ammount;
		while (angle_y > 360.0f){
			angle_y-=360.0f;//if it is above 360, reduce it down to 0<=y<=360
		}
	}
	public void addAngleZ(float ammount){
		this.angle_z += ammount;
		while (angle_z > 360.0f){
			angle_z-=360.0f;//if it is above 360, reduce it down to 0<=z<=360
		}
	}
	public void subtractAngleX(float ammount){
		this.angle_x -= ammount;
		while (angle_x < 0.0f){
			angle_x+=360.0f;//if it is below 0, increase it to 0<=x<=360
		}
	}
	public void subtractAngleY(float ammount){
		this.angle_y -= ammount;
		while (angle_y < 0.0f){
			angle_y+=360.0f;//if it is below 0, increase it to 0<=y<=360
		}
	}
	public void subtractAngleZ(float ammount){
		this.angle_z -= ammount;
		while (angle_z < 0.0f){
			angle_z+=360.0f;//if it is below 0, increase it to 0<=z<=360
		}
	}
	/*
	 * Returns a direction that is the offset direction in this frame of reference //This description sucks. please fix it. -original commenter
	 * eg: if offset is facing right, and this is facing left, then the result is facing forwards
	 */
	public Direction getRelativeDirection(Direction offset){
		Direction returnedDirection = new Direction(this.getAngleX(), this.getAngleY(), this.getAngleZ());
		returnedDirection.addAngleX(offset.getAngleX());
		returnedDirection.addAngleY(offset.getAngleY());
		returnedDirection.addAngleZ(offset.getAngleZ());
		return returnedDirection;
	}
}
