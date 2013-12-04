package net.jordaria.entity;

public class Direction {
	public float angle_x;//the angle about the x axis. in degrees.
	public float angle_y;//the angle about the y axis, in degrees.
	public float angle_z;//the angle about the z axis, in degrees.
	
	public float getAngleX(){
		return this.angle_x;
	}
	public float getAngleY(){
		return this.angle_y;
	}
	public float getAngleZ(){
		return this.angle_z;
	}
	
	public void increaseAngleX(float ammount){
		this.angle_x += ammount;
		while (angle_x > 360.0f){
			angle_x-=360.0f;//if it is above 360, reduce it down to 0<=x<=360
		}
	}
	public void increaseAngleY(float ammount){
		this.angle_y += ammount;
		while (angle_y > 360.0f){
			angle_y-=360.0f;//if it is above 360, reduce it down to 0<=y<=360
		}
	}
	public void increaseAngleZ(float ammount){
		this.angle_z += ammount;
		while (angle_z > 360.0f){
			angle_z-=360.0f;//if it is above 360, reduce it down to 0<=z<=360
		}
	}
	public void decreaseAngleX(float ammount){
		this.angle_x -= ammount;
		while (angle_x < 0.0f){
			angle_x+=360.0f;//if it is below 0, increase it to 0<=x<=360
		}
	}
	public void decreaseAngleY(float ammount){
		this.angle_y -= ammount;
		while (angle_y < 0.0f){
			angle_y+=360.0f;//if it is below 0, increase it to 0<=y<=360
		}
	}
	public void decreaseAngleZ(float ammount){
		this.angle_z -= ammount;
		while (angle_z < 0.0f){
			angle_z+=360.0f;//if it is below 0, increase it to 0<=z<=360
		}
	}
}
