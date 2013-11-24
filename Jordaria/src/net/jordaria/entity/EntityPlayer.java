package net.jordaria.entity;

import net.jordaria.world.ChunkCoordinates;
import net.jordaria.world.World;

public class EntityPlayer extends EntityLiving{

	public float cameraYaw;
	private ChunkCoordinates spawnChunk;
	protected final String username;

	public EntityPlayer(World world, String name) {
		super(world);
		this.username = name;
	}
	public void preparePlayerToSpawn()
	{
		this.yOffset = 1.62F;
		this.setSize(0.6F, 1.8F);
		super.preparePlayerToSpawn();
		this.setHealthToMax();
	}


}
