package net.jordaria.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.jordaria.entity.Entity;
import net.jordaria.entity.EntityPlayer;

public class World {
	public Random rng = new Random();

	public List<Object> playerEntities = new ArrayList<Object>();
	public List<Object> loadedEntityList = new ArrayList<Object>();//Entities loaded in the world
	protected List<Object> unloadedEntityList = new ArrayList<Object>();

	public ChunkManager chunkManager;

	public String worldName;

	public World(String name){
		this.worldName = name;
		this.chunkManager = new ChunkManager(this);
	}

	public void updateEntities(){
	}
	public Chunk getChunkFromChunkCoords(int xPos, int yPos, int zPos)
	{
		return this.chunkManager.provideChunk(xPos, yPos, zPos);
	}
	
	public boolean spawnEntityInWorld(Entity entity)
    {
        int x = entity.getLocation().getChunkLocation().getPosX();
        int y = entity.getLocation().getChunkLocation().getPosY();
        int z = entity.getLocation().getChunkLocation().getPosZ();
        boolean isPlayer = false;

        if (entity instanceof EntityPlayer)
        {
            isPlayer = true;
        }

        if (!isPlayer)
        {
            return false;
        }
        else
        {
            if (entity instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer)entity;
                this.playerEntities.add(player);
            }

            this.getChunkFromChunkCoords(x,y,z).addEntity(entity);
            this.loadedEntityList.add(entity);
            return true;
        }
    }

}
