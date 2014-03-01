package net.jordaria.world;

import java.util.ArrayList;
import java.util.HashSet;

import net.jordaria.physics.AxisAlignedBoundingBox;

/**
 * A set of {@link Tile tiles} representing a map.
 * 
 * @author Ches Burks
 *
 */
public class Map {
	public Tile[][] tiles;//arranged as [height][width]
	public int width;
	public int height;
	/* An array of collision AABB's which is updated 
	 * whenever the map is changed.
	 */
	private HashSet<AxisAlignedBoundingBox> collisionBoxes;
	
	
	/**
	 * Construct a new Map with the given width and height.
	 * 
	 * @param width The height
	 * @param height The width
	 */
	public Map(int width, int height){
		this.tiles = new Tile[height][width];
		this.collisionBoxes = new HashSet<AxisAlignedBoundingBox>();
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
				this.tiles[y][x] = new Tile();
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
	 * 
	 * @param x The tiles x position
	 * @param y The tiles y position
	 * @param type The type to set
	 */
	public void setTile(int x, int y, TileType type){
		tiles[y][x].setTileType(type);
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

		if (tiles[y][x]==null){
			Tile tile = new Tile();
			tile.setTileType(TileType.ERROR);
			return tile;
		}
		else{
			return tiles[y][x];
		}
	}

	/**
	 * Adds the structure to the map. This will replace anything already placed.
	 * This methods automatically repopulates the collision boxes after placing.
	 * 
	 * @param struct The structure to add
	 * @param x The x position to start at
	 * @param y The y position to start at
	 */
	public void addStructure(Structure struct, int x, int y){
		int i,j;
		for (j = 0; j < struct.getHeight(); j++){
			for (i = 0; i < struct.getWidth(); i++){
				setTile(i+x, j+y, struct.getTileAt(i, j).getTileType());
			}
		}
		repopulateCollisionBoxes();
	}

	/**
	 * Populates the array of collision boxes for solid tiles.
	 * This creates {@link AxisAlignedBoundingBox AABB's} for each tile, 
	 * and tries to create the largest AABB for groups of solid tiles as it can.
	 * This may not find any boxes.
	 */
	public void repopulateCollisionBoxes(){
		ArrayList<AxisAlignedBoundingBox> boxes = new ArrayList<AxisAlignedBoundingBox>();
		AxisAlignedBoundingBox tmp = new AxisAlignedBoundingBox(0, 0, 0, 0, 0, 0);
		//true if the block at that pos is solid, false otherwise.
		Boolean[][] solid = new Boolean[getHeight()][getWidth()];

		int x, y, i, j;

		//fill the solid array
		for (y = 0; y < this.height; y++){
			for (x = 0; x < this.width; x++){
				solid[y][x] = tiles[y][x].getTileType().isSolid();
			}
		}
		for (y = 0; y < this.height; y++){
			for (x = 0; x < this.width; x++){
				if (solid[y][x]){
					tmp = getLargestRectStartingAtCoord(solid, x, y);//gets the largest rect
					boxes.add(tmp);//add the box to the list
					//resets loops
					for(j = (int)tmp.minY; j < (int)tmp.maxY; j++){
						for(i = (int)tmp.minX; i < (int)tmp.maxX; i++){
							solid[y][x] = false;//clear out the values from the rectangle
						}
					}
				}
			}
		}
		
		clearCollisionBoxes();//Clears out the AABB's
		this.collisionBoxes.addAll(boxes);//Add all of the boxes to the map's list
	}
	/**
	 * Finds and returns the largest AABB that has the corner at the given pos.
	 * 
	 * @param solidArray An array of booleans representing the solidity of the tiles
	 * @param x The x position
	 * @param y The y position
	 * @return The largest rectangles AABB
	 */
	private AxisAlignedBoundingBox getLargestRectStartingAtCoord(Boolean[][] solidArray, int x, int y){
		AxisAlignedBoundingBox largestRect = new AxisAlignedBoundingBox(x, y, 0, x, y, 0);

		int arrayWidth = 0;
		int arrayHeight = solidArray.length;

		int maxWidth = 0;
		int maxHeight = 0;

		int i, j;

		boolean xIsDominant = false;//True if xmax is longer, false if ymax is longer.

		//figure out how wide the array is
		for (i = 0; i<tiles.length; i++){
			if (tiles[i].length > arrayWidth ){
				arrayWidth = tiles[i].length;
			}
		}

		//find the maximum widths and heights of a rectangle starting at (X,Y)
		for (i = x; i < arrayWidth; i++){
			if (solidArray[y][i]){//The value to the right is true
				maxWidth++;
			}
			else{
				break;//The value to the right is false, so stop counting
			}
		}
		for (j = x; j < arrayHeight; j++){
			if (solidArray[j][x]){//The value under is true
				maxHeight++;
			}
			else{
				break;//The value under is false, so stop counting
			}
		}

		if (maxWidth > maxHeight){
			xIsDominant = true;
		}

		if (xIsDominant){
			for (i = x; i < x+maxWidth; i++){
				for (j = y; j < y+maxHeight; j++){
					if (!solidArray[i][j]){//it is white here
						maxHeight = j - y;//the max height is now above the white tile

						j = y;//start the loops over again
						i = x;

					}
				}
			}
		}
		else{
			for (j = y; j < y+maxHeight; j++){
				for (i = x; i < x+maxWidth; i++){
					if (!solidArray[i][j]){//it is white here
						maxWidth = i - x;//the max width is now left of the white tile
						i = x;
						j = y;//start the loops over again

					}
				}
			}

		}
		
		largestRect.setBounds(x, y, 0, x+maxWidth, y+maxHeight, 0);

		return largestRect;

	}
	
	/**
	 * Clears out the {@link HashSet} of collision boxes.
	 */
	public void clearCollisionBoxes(){
		this.collisionBoxes.clear();
	}
	
	/**
	 * Returns the array of collision boxes for solid tiles.
	 * This may be empty.
	 * 
	 * @return The array of collision boxes
	 */
	public AxisAlignedBoundingBox[] getCollisionBoxes(){
		AxisAlignedBoundingBox[] toReturn = new AxisAlignedBoundingBox[collisionBoxes.size()];
		int index = 0;
		for (AxisAlignedBoundingBox box : collisionBoxes){
			toReturn[index] = box;
			index++;
		}
		return toReturn;
	}
	
	/**
	 * Returns the {@link HashSet} of collision boxes for solid tiles.
	 * This may be empty. This method is faster than {@link #getCollisionBoxes()}, 
	 * as it is stored internally as a hashset and thus does not need conversion.
	 * 
	 * @return The array of collision boxes
	 */
	public HashSet<AxisAlignedBoundingBox> getCollisionBoxesHashSet(){
		return collisionBoxes;
	}
}
