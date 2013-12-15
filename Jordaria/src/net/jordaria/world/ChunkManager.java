package net.jordaria.world;

import java.util.HashMap;
import java.util.HashSet;

public class ChunkManager {
	public HashMap<ChunkCoordinates, Chunk> chunkMap = new HashMap<ChunkCoordinates, Chunk>();
	
	public HashSet<Chunk> chunksToLoad = new HashSet<Chunk>();
	public HashSet<Chunk> chunksToUnload = new HashSet<Chunk>();
	
	private World worldObj;

	private Chunk blankChunk;
	
	public ChunkManager(World theWorld)
	{
		this.blankChunk = new Chunk(theWorld, 0, 0).makeEmptyChunk();
		this.worldObj = theWorld;
	}
	
	/**
     * loads or generates the chunk at the chunk location specified
     */
    public Chunk loadChunk(int xPos, int yPos)
    {
        Chunk theChunk = new Chunk(this.worldObj, xPos, yPos);
        this.chunkMap.put(new ChunkCoordinates(xPos, yPos), theChunk);
        theChunk.isChunkLoaded = true;
        return theChunk;
    }
    
    /**
     * Unload chunk from ChunkProviderClient's hashmap.
     */
    public void unloadChunk(int xPos, int yPos)
    {
        Chunk theChunk = this.provideChunk(xPos, yPos);

        if (!theChunk.isEmpty())
        {
        	theChunk.onChunkUnload();
        }

        this.chunkMap.remove(new ChunkCoordinates(xPos, yPos));
    }
    
    public Chunk provideChunk(int xPos, int yPos)
    {
        Chunk theChunk = (Chunk)this.chunkMap.get(new ChunkCoordinates(xPos, yPos));
        return theChunk == null ? this.blankChunk : theChunk;//if the chunk is null return the blank chunk, else return the chunk
    }
    
    public int getLoadedChunkCount()
    {
        return this.chunkMap.size();
    }
}
