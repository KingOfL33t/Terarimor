package net.jordaria.world;

/**
 * A structure, such as a house or road, made of tiles.
 * 
 * @author Ches Burks
 *
 */
public class Structure {
	private int width;
	private int height;
	private Tile[][] tiles;

	/**
	 * Constructs a new {@link Structure}.
	 */
	public Structure(){
		width = 0;
		height = 0;
		tiles = new Tile[0][0];
	}

	/**
	 * Returns the width of the structure.
	 * 
	 * @return The width
	 */
	public int getWidth(){
		return width;
	}

	/**
	 * Returns the height of the structure.
	 * 
	 * @return The height
	 */
	public int getHeight(){
		return height;
	}

	/**
	 * Sets the tiles and width/height variables using the given 
	 * array of {@link Tile tiles}.
	 * 
	 * @param tiles The array to use
	 */
	public void setTiles(Tile[][] tiles){
		int w = 0;
		int i,j;
		Tile[] buffer;

		this.tiles = tiles;
		this.height = tiles.length;


		//find the maximum width
		for (i = 0; i < tiles.length; i++){
			if (tiles[i].length > w ){
				w = tiles[i].length;
			}
		}
		this.width = w;

		for (i = 0; i < height; i++){
			if (tiles[i].length < w){
				
				//creates a buffer with the proper length and
				//copies values onto it
				buffer = new Tile[w];
				for (j = 0; j<w; j++){
					buffer[j] = new Tile();
					if (tiles[i][j]!=null){
						buffer[j] = tiles[i][j];
					}
				}
				//then resets tiles[i] to the buffer, with the correct length
				this.tiles[i] = buffer;
			}
		}
	}

	/**
	 * Returns the array of {@link Tile tiles}.
	 * 
	 * @return The array
	 */
	public Tile[][] getTiles(){
		return tiles;
	}

	/**
	 * Returns the {@link Tile tile} at the given location.
	 * 
	 * @param x The x value
	 * @param y The y value
	 * @return
	 */
	public Tile getTileAt(int x, int y){
		
		return tiles[y][x];
	}

}
