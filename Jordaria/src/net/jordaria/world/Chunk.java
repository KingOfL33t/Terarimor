package net.jordaria.world;

import net.jordaria.block.Block;

public class Chunk {
	static final int CHUNK_SIZE = 16;
	private short[][][] blocks;
	
	public void render(){
		
	}
	public void update(){
		
	}
	public Chunk(){
		blocks = new short[CHUNK_SIZE][CHUNK_SIZE][CHUNK_SIZE];
		for (int x = 0; x < CHUNK_SIZE; x++){
			for (int y = 0; y< CHUNK_SIZE; y++){
				for (int z = 0; z < CHUNK_SIZE; z++){
					blocks[x][y][z] = Block.stone.blockID;
				}
			}
		}
	}
}
