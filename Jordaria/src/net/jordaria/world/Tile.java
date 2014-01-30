package net.jordaria.world;

/**
 * Represents a single square on a {@link Map map}.
 * 
 * @author Ches Burks
 *
 */
public class Tile {
	public TileType tileType;
	
	/**
	 * Construct a tile with the given {@link TileType type}.
	 * 
	 * @param type The type of the tile
	 */
	public void setTileType(TileType type){
		this.tileType = type;
	}
	
	/**
	 * Returns the {@link TileType type} of the tile.
	 * 
	 * @return The type of the tile
	 */
	public TileType getTileType(){
		return tileType;
	}
}
