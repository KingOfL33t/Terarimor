package net.jordaria.world;

public class Map {
	public Tile[][] tiles;
	public int width;
	public int height;
	
	public Map(int width, int height){
		this.tiles = new Tile[width][height];
		this.width = width;
		this.height = height;
	}
	public void setAllTiles(TileType type){
		int x;
		int y;
		for (x = 0; x < width; x++){
			for (y = 0; y < height; y++){
				this.setTile(x, y, type);
			}
		}
	}
	public void setTile(int x, int y, TileType type){
		tiles[x][y].setTileType(type);;
	}
}
