package net.jordaria.entity;

/**
 * An angle from one to 360 which contains methods for modifying the angle.
 * 
 * @author Ches Burks
 *
 */
public class Direction {
	public float angle = 0;
	
	/**
	 * Constructs a new Direction with the given angle.
	 * If the angle is outside the allowed range, it is 
	 * set to an equivalent value inside the range.
	 * 
	 * @param degrees The angle in degrees. Decimals are allowed.
	 */
	public Direction(float degrees){
		this.angle = degrees;
		this.validateAngle();
	}
	
	/**
	 * Returns the angle as a float.
	 * 
	 * @return The current angle
	 */
	public float getAngle(){
		return this.angle;
	}
	
	/**
	 * Ensure the angle is within the allowed range
	 */
	private void validateAngle(){
		if (this.angle>360){
			while (angle > 360.0f){
				angle-=360.0f;//if it is above 360, reduce it down to 0<=x<=360
			}
		}
		else if (this.angle < 0){
			while (angle < 0.0f){
				angle+=360.0f;//if it is below 0, increase it to 0<=x<=360
			}
		}
	}
	
	/**
	 * Adds the specified amount to the angle. 
	 * If the new angle is more than 360, it wraps 
	 * around back past zero so it is in the allowed range.
	 * 
	 * @param amount The amount to increase the angle by in degrees. Decimals are allowed.
	 */
	public void addAngle(float amount){
		this.angle += amount;
		this.validateAngle();
	}
	
	/**
	 * Subtracts the specified amount to the angle. 
	 * If the new angle is less than 0, it wraps 
	 * around back past 360 so it is in the allowed range.
	 * 
	 * @param amount The amount to decrease the angle by in degrees. Decimals are allowed.
	 */
	public void subtractAngle(float amount){
		this.angle -= amount;
		this.validateAngle();
	}
	
	/**
	 * Combines the two directions to form a new direction. 
	 * The new direction has an angle equal to this one and the offset 
	 * added together and confined to the allowed range of angles.
	 * 
	 * @param offset The direction to offset this one by
	 * @return A new direction with the offset angle
	 */
	public Direction getRelativeDirection(Direction offset){
		Direction returnedDirection = new Direction(this.getAngle());
		returnedDirection.addAngle(offset.getAngle());
		return returnedDirection;
	}
}
