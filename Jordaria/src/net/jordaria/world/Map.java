package net.jordaria.world;

/**
 * A set of {@link Tile tiles} representing a map.
 * 
 * @author Ches Burks
 *
 */
public class Map {
	public Tile[][] tiles;
	public int width;
	public int height;
	
	/**
	 * Construct a new Map with the given width and height.
	 * 
	 * @param width The height
	 * @param height The width
	 */
	public Map(int width, int height){
		this.tiles = new Tile[width][height];
		this.width = width;
		this.height = height;
		this.initTileArray();
	}
	
	/**
	 * Init the array of tiles with blank tiles.
	 */
	private void initTileArray(){
		int x;
		int y;
		for (x = 0; x < width; x++){
			for (y = 0; y < height; y++){
				this.tiles[x][y] = new Tile();
			}
		}
	}
	
	/**
	 * Set all {@link Tile tiles} in the map to the specified {@link TileType type}.
	 * 
	 * @param type The type to set
	 */
	public void setAllTiles(TileType type){
		int x;
		int y;
		for (x = 0; x < width; x++){
			for (y = 0; y < height; y++){
				this.setTile(x, y, type);
			}
		}
	}
	
	/**
	 * Set the specified {@link Tile tile} to the specified {@link TileType type}.
	 * @param x The tiles x position
	 * @param y The tiles y position
	 * @param type The type to set
	 */
	public void setTile(int x, int y, TileType type){
		tiles[x][y].setTileType(type);;
	}
	
	/**
	 * Returns the maps width.
	 * 
	 * @return The width
	 */
	public int getWidth(){
		return this.width;
	}
	
	/**
	 * Returns the maps height.
	 * 
	 * @return The height
	 */
	public int getHeight(){
		return this.height;
	}
	
	/**
	 * Returns the {@link Tile tile} at the specified position.
	 * 
	 * @param x The x position
	 * @param y The y position
	 * @return The tile
	 */
	public Tile getTile(int x, int y){
		
		if (tiles[x][y]==null){
			Tile tile = new Tile();
			tile.setTileType(TileType.ERROR);
			return tile;
		}
		else{
			return tiles[x][y];
		}
	}
}
