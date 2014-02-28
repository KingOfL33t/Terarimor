package net.jordaria.entity;

import java.util.Random;

import net.jordaria.world.World;

/**
 * An abstract entity class containing variables and methods used by all entity subclasses.
 * 
 * @author Ches Burks
 *
 */
public abstract class Entity {
	public int id;
	private static int nextEntityID;
	public World worldObj;

	public Random rand;

	public Location location;
	public double velX;//velocity in the x direction
	public double velY;//velocity in the y direction
	public float width;
	public float height;
	public Direction direction;
	
	public String name;

	/**
	 * Constructs a new entity in the specified world with 
	 * default values.
	 * 
	 * @param world The world to place the entity in
	 */
	public Entity(World world)
	{
		this.id = nextEntityID++;
		this.width = 0.6F;
		this.height = 1.8F;
		this.rand = new Random();
		this.worldObj = world;
		this.location = new Location(0.0D, 0.0D);
		this.entityInit();
		this.direction = new Direction(0);
	}

	/**
	 * Inherited for subclass initiation.
	 */
	protected abstract void entityInit();

	/**
	 * Sets the position in the world to the specified position.
	 * 
	 * @param x The x position
	 * @param y The y position
	 */
	public void setPosition(double x, double y) {
		this.location.setPositions(x, y);
	}

	/**
	 * Sets the size of the entity to the given width and height.
	 * 
	 * @param w The width of the entity
	 * @param h The height of the entity
	 */
	public void setSize(float w, float h){
		this.width = w;
		this.height = h;
	}

	/**
	 * Returns the unique ID of the entity.
	 * 
	 * @return The entity ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the entities world object.
	 * 
	 * @return The world object
	 */
	public World getWorldObj() {
		return worldObj;
	}

	/**
	 * Returns the random number generator for the entity.
	 * 
	 * @return The random number generator
	 */
	public Random getRand() {
		return rand;
	}

	/**
	 * Returns the entities location object.
	 * 
	 * @return The location of the entity
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Returns the x velocity of the entity.
	 * 
	 * @return The x velocity
	 */
	public double getVelX() {
		return velX;
	}

	/**
	 * Returns the y velocity of the entity.
	 * 
	 * @return The y velocity
	 */
	public double getVelY() {
		return velY;
	}

	/**
	 * Returns the width of the entity.
	 * 
	 * @return The width
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * Returns the height of the entity.
	 * 
	 * @return The height
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * Returns the entities direction.
	 * 
	 * @return The direction
	 */
	public Direction getDirection() {
		return direction;
	}
	
	/**
	 * Sets the name of the entity.
	 * 
	 * @param name The new name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Returns the name of the entity.
	 * 
	 * @return The current name
	 */
	public String getName(){
		return this.name;
	}
}
