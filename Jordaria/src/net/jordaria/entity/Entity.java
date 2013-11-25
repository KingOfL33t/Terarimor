package net.jordaria.entity;

import java.util.Random;

import net.jordaria.world.World;

public abstract class Entity {
	public int id;
	private static int nextEntityID;
	public World worldObj;

	public Random rand;

	public double posX;//x position
	public double posY;//y position
	public double posZ;//z position
	public float rotationYaw;
	public float rotationPitch;
	public double velX;//velocity in the x direction
	public double velY;//velocity in the y direction
	public double velZ;//velocity in the z direction
	public float width;
	public float height;
	public float yOffset;

	public Entity(World par1World)
	{
		this.id = nextEntityID++;
		this.width = 0.6F;
		this.height = 1.8F;
		this.rand = new Random();
		this.worldObj = par1World;
		this.setPosition(0.0D, 0.0D, 0.0D);
		this.entityInit();
	}

	protected abstract void entityInit();//inherited for subclass initiation

	public void setPosition(double x, double y, double z) {
		this.posX = x;
		this.posY = y;
		this.posZ = z;
	}

	public void setSize(float w, float h){
		this.width = w;
		this.height = h;
	}
}
