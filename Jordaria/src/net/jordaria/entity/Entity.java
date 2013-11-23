package net.jordaria.entity;

import net.jordaria.world.World;

public abstract class Entity {
	public int id;
	public World worldObj;
	
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
    
    public boolean addedToChunk;//set to true when it has been added to its current chunk
}
