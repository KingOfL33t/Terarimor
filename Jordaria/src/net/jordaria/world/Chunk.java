package net.jordaria.world;

import net.jordaria.block.Block;
import net.jordaria.Configuration;

public class Chunk {
	private Block[][][] blocks;
	public final int xPosition;
	public final int yPosition;
	public final int zPosition;

	
	public void update(){

	}

	public Chunk(int xPos, int yPos, int zPos){
		xPosition = xPos;
		yPosition = yPos;
		zPosition = zPos;
		blocks = new Block[Configuration.getCHUNK_SIZE()][Configuration.getCHUNK_SIZE()][Configuration.getCHUNK_SIZE()];
		for (int x = 0; x < Configuration.getCHUNK_SIZE(); x++){
			for (int y = 0; y< Configuration.getCHUNK_SIZE(); y++){
				for (int z = 0; z < Configuration.getCHUNK_SIZE(); z++){
					if (Math.random() < .5){
						blocks[x][y][z] = Block.stone;
					}
					else{
						blocks[x][y][z] = Block.grass;
					}

				}
			}
		}

	}


	

}
