package net.jordaria.entity;

import java.util.Random;

import net.jordaria.world.World;

public abstract class Entity {
	public int id;
	private static int nextEntityID;
	public World worldObj;

	public Random rand;

	public Location location;
	public double velX;//velocity in the x direction
	public double velY;//velocity in the y direction
	public double velZ;//velocity in the z direction
	public float width;
	public float height;
	public Direction direction;

	public Entity(World world)
	{
		this.id = nextEntityID++;
		this.width = 0.6F;
		this.height = 1.8F;
		this.rand = new Random();
		this.worldObj = world;
		this.location = new Location(0.0D, 0.0D, 0.0D);
		this.entityInit();
		this.direction = new Direction(0,0,0);
	}

	protected abstract void entityInit();//inherited for subclass initiation

	public void setPosition(double x, double y, double z) {
		this.location.setPositions(x, y, z);
	}

	public void setSize(float w, float h){
		this.width = w;
		this.height = h;
	}

	public int getId() {
		return id;
	}

	public World getWorldObj() {
		return worldObj;
	}

	public Random getRand() {
		return rand;
	}

	public Location getLocation() {
		return location;
	}

	public double getVelX() {
		return velX;
	}

	public double getVelY() {
		return velY;
	}

	public double getVelZ() {
		return velZ;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public Direction getDirection() {
		return direction;
	}
}
