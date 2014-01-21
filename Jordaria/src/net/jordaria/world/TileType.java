package net.jordaria.world;

public enum TileType {
	AIR(0),
	FLOOR(1),
	FLOOR_ROCKY(2),
	FLOOR_CRACKED(3),
	FLOOR_BROKEN(4),
	FLOOR_BLOOD(5),
	FLOOR_PLANT(6),
	WALL(7),
	LEDGE_N(8),
	LEDGE_NW(9),
	LEDGE_NE(10),
	LEDGE_E(11),
	LEDGE_W(12),
	DOOR_N(13),
	DOOR_E(14),
	DOOR_S(15),
	DOOR_W(16),
	STAIRS_N(17),
	STAIRS_E(18),
	STAIRS_S(19),
	STAIRS_W(20),
	ERROR(-1);
	private int tileID;
	private TileType(int id){
		this.tileID = id;
	}
	public int getID(){
		return tileID;
	}
}
