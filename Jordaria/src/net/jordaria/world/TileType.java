package net.jordaria.world;

/**
 * An enum of different types of tiles and the associated id.
 * 
 * @author Ches Burks
 *
 */
public enum TileType {
	AIR(0),
	FLOOR(1),
	FLOOR_ROCKY(2),
	FLOOR_CRACKED(3),
	FLOOR_BROKEN(4),
	FLOOR_BLOOD(5),
	FLOOR_PLANT(6),
	WALL(7, true),
	LEDGE_N(8, true),
	LEDGE_NW(9, true),
	LEDGE_NE(10, true),
	LEDGE_E(11, true),
	LEDGE_W(12, true),
	DOOR_N(13),
	DOOR_E(14),
	DOOR_S(15),
	DOOR_W(16),
	STAIRS_N(17),
	STAIRS_E(18),
	STAIRS_S(19),
	STAIRS_W(20),
	ROOF(21, true),
	ERROR(-1);
	
	private int tileID;
	private boolean isSolid;
	
	/**
	 * Constructs a new {@link TileType} with the given ID.
	 * 
	 * @param id The ID to use
	 */
	private TileType(int id){
		this.tileID = id;
		this.isSolid = false;
	}
	
	/**
	 * Constructs a new {@link TileType} with the given ID. 
	 * The tile may also be solid.
	 * 
	 * @param id The ID to use
	 * @param isSolid If the tile causes collision
	 */
	private TileType(int id, boolean isSolid){
		this.tileID = id;
		this.isSolid = isSolid;
	}
	
	/**
	 * Returns the ID of the tile.
	 * 
	 * @return The ID
	 */
	public int getID(){
		return tileID;
	}
	
	/**
	 * Returns true if the tile causes collision.
	 * 
	 * @return True if it is solid, false otherwise
	 */
	public boolean isSolid(){
		return isSolid;
	}
}
