package net.jordaria.world;

public enum WorldType {
	MAGICAL(0),
	FUTURISTIC(1),
	FOREST(2),
	RUINS(3);
	
	private int typeID;
	private WorldType(int id){
		this.typeID = id;
	}
	public int getID(){
		return typeID;
	}
}
