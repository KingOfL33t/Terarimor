package net.jordaria.world;

import java.util.ArrayList;
import java.util.List;

import net.jordaria.block.Block;

public class Biome {
	public static final Biome[] biomeList = new Biome[256];
	
	public static final Biome flat = (new BiomeFlat(0)).setBiomeName("Flat").setFillerBlockID(Block.dirt.blockID).setTopBlockID(Block.grass.blockID);
	
	public short topBlockID;//the block on top of the biome
	public short fillerBlockID;
	
	public float temperature;//The standard temperature of the biome
	public float humidity;//Standard biome humidity
	public float magic;//how prevalent magic is in the biome
	public float technology;//how prevalent technology is in the biome
	
	protected List<Object> spawnableMobs;
	
	public final int biomeID;
	public String biomeName;
	
	 protected Biome(int par1)
	    {
	        this.topBlockID = Block.grass.blockID;
	        this.fillerBlockID = Block.dirt.blockID;
	        this.temperature = 0.5F;
	        this.humidity = 0.5F;
	        this.magic = 0.5F;
	        this.technology = 0.5F;
	        
	        this.spawnableMobs = new ArrayList<Object>();
	        this.biomeID = par1;
	        biomeList[par1] = this;
	    }

	public short getTopBlockID() {
		return topBlockID;
	}

	public short getFillerBlockID() {
		return fillerBlockID;
	}

	public float getTemperature() {
		return temperature;
	}

	public float getHumidity() {
		return humidity;
	}

	public float getMagic() {
		return magic;
	}

	public float getTechnology() {
		return technology;
	}

	private String getBiomeName() {
		return biomeName;
	}

	protected Biome setBiomeName(String biomeName) {
		this.biomeName = biomeName;
		return this;
	}

	private Biome setTopBlockID(short topBlockID) {
		this.topBlockID = topBlockID;
		return this;
	}

	private Biome setFillerBlockID(short fillerBlockID) {
		this.fillerBlockID = fillerBlockID;
		return this;
	}

	private Biome setTemperature(float temperature) {
		this.temperature = temperature;
		return this;
	}

	private Biome setHumidity(float humidity) {
		this.humidity = humidity;
		return this;
	}

	private Biome setMagic(float magic) {
		this.magic = magic;
		return this;
	}

	private Biome setTechnology(float technology) {
		this.technology = technology;
		return this;
	}
}
