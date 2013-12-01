package net.jordaria.world;

import java.util.HashMap;

public class ChunkManager {
	public HashMap<ChunkCoordinates, Chunk> chunkMap = new HashMap<ChunkCoordinates, Chunk>();

	private World worldObj;

	private Chunk blankChunk;
	
	public ChunkManager(World theWorld)
	{
		this.blankChunk = new Chunk(theWorld, 0, 0, 0).makeEmptyChunk();
		this.worldObj = theWorld;
	}
	
	/**
     * loads or generates the chunk at the chunk location specified
     */
    public Chunk loadChunk(int xPos, int yPos, int zPos)
    {
        Chunk theChunk = new Chunk(this.worldObj, xPos, yPos, zPos);
        this.chunkMap.put(new ChunkCoordinates(xPos, yPos, zPos), theChunk);
        theChunk.isChunkLoaded = true;
        return theChunk;
    }
    
    /**
     * Unload chunk from ChunkProviderClient's hashmap.
     */
    public void unloadChunk(int xPos, int yPos, int zPos)
    {
        Chunk theChunk = this.provideChunk(xPos, yPos, zPos);

        if (!theChunk.isEmpty())
        {
        	theChunk.onChunkUnload();
        }

        this.chunkMap.remove(new ChunkCoordinates(xPos, yPos, zPos));
    }
    
    public Chunk provideChunk(int xPos, int yPos, int zPos)
    {
        Chunk theChunk = (Chunk)this.chunkMap.get(new ChunkCoordinates(xPos, yPos, zPos));
        return theChunk == null ? this.blankChunk : theChunk;//if the chunk is null return the blank chunk, else return the chunk
    }
    
    public int getLoadedChunkCount()
    {
        return this.chunkMap.size();
    }
    
    
}
