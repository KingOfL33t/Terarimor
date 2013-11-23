package net.jordaria.world;

import java.util.ArrayList;
import java.util.List;

import net.jordaria.block.Block;

public class Biome {
	public static final Biome[] biomeList = new Biome[256];
	
	public short topBlockID;//the block on top of the biome
	public short fillerBlockID;
	
	public float temperature;//The standard temperature of the biome
	public float humidity;//Standard biome humidity
	public float magic;//how prevalent magic is in the biome
	public float technology;//how prevalent technology is in the biome
	
	protected List spawnableMobs;
	
	public final int biomeID;
	
	 protected Biome(int par1)
	    {
	        this.topBlockID = Block.grass.blockID;
	        this.fillerBlockID = Block.dirt.blockID;
	        this.temperature = 0.5F;
	        this.humidity = 0.5F;
	        this.magic = 0.5F;
	        this.technology = 0.5F;
	        
	        this.spawnableMobs = new ArrayList();
	        this.biomeID = par1;
	        biomeList[par1] = this;
	    }
}
