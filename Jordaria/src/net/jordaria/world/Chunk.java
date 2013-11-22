package net.jordaria.world;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import net.jordaria.block.Block;
import net.jordaria.Configuration;

public class Chunk {
	private Block[][][] blocks;
	public final int xPosition;
	public final int yPosition;
	public final int zPosition;
	int VBOColorHandle;
	int VBOVertexHandle;
	static final int CUBE_LENGTH=2;

	public void render(){
		GL11.glPushMatrix();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBOVertexHandle);
		GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, 0L);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBOColorHandle);
		GL11.glColorPointer(3, GL11.GL_FLOAT, 0, 0L);
		GL11.glDrawArrays(GL11.GL_QUADS, 0, Configuration.getCHUNK_SIZE() * Configuration.getCHUNK_SIZE()
				* Configuration.getCHUNK_SIZE() * 24);
		GL11.glPopMatrix();
	}
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
					if (x==0){
						blocks[x][y][z] = Block.stone;
					}
					else if (x==1){
						blocks[x][y][z] = Block.grass;
					}
					else{
						blocks[x][y][z] = Block.air;
					}
				}
			}
		}
		VBOColorHandle = GL15.glGenBuffers();
		VBOVertexHandle = GL15.glGenBuffers();
		RebuildMesh(xPos, yPos, zPos);
	}

	public void RebuildMesh(float startX, float startY, float startZ) {

		VBOColorHandle = GL15.glGenBuffers();
		VBOVertexHandle = GL15.glGenBuffers();
		FloatBuffer VertexPositionData = BufferUtils
				.createFloatBuffer((Configuration.getCHUNK_SIZE() * Configuration.getCHUNK_SIZE() * Configuration.getCHUNK_SIZE()) * 6 * 12);
		FloatBuffer VertexColorData = BufferUtils.createFloatBuffer((Configuration.getCHUNK_SIZE()
				* Configuration.getCHUNK_SIZE() * Configuration.getCHUNK_SIZE()) * 6 * 12);
		//Note that for above CHUNK_SIZE^3 was not working for me.

		for (float x = 0; x < Configuration.getCHUNK_SIZE(); x += 1) {
			for (float y = 0; y < Configuration.getCHUNK_SIZE(); y += 1) {
				for (float z = 0; z < Configuration.getCHUNK_SIZE(); z += 1) {

					VertexPositionData.put(CreateCube((float) startX + x * Configuration.getCHUNK_SIZE(), 
							(float) startY + y * Configuration.getCHUNK_SIZE(),
							(float) startZ + z * Configuration.getCHUNK_SIZE()));
					VertexColorData.put(CreateCubeVertexCol(GetCubeColor(blocks[(int) x][(int) y][(int) z])));
				}
			}
		}

		VertexColorData.flip();
		VertexPositionData.flip();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBOVertexHandle);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, VertexPositionData,
				GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBOColorHandle);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, VertexColorData,
				GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

	}
	public static float[] CreateCube(float x, float y, float z) {
		int offset = CUBE_LENGTH / 2;
		return new float[] {
				x + offset, y + offset,z,
				x - offset,y + offset,z,
				x - offset,y + offset,z - CUBE_LENGTH,
				x + offset,y + offset,z - CUBE_LENGTH,
				x + offset, y - offset, z - CUBE_LENGTH,
				x - offset,y - offset,z - CUBE_LENGTH,
				x - offset,y - offset,z,
				x + offset,y - offset,z,
				x + offset, y + offset, z - CUBE_LENGTH,
				x - offset,y + offset, z - CUBE_LENGTH,
				x - offset,y - offset,z - CUBE_LENGTH,
				x + offset,y - offset,z - CUBE_LENGTH,
				x + offset, y - offset, z,
				x - offset, y - offset, z,
				x - offset, y + offset, z,
				x + offset,y + offset, z,
				x - offset, y + offset, z - CUBE_LENGTH,
				x - offset,y + offset, z,
				x - offset, y - offset, z,
				x - offset,y - offset,z - CUBE_LENGTH,
				x + offset, y + offset, z,
				x + offset, y + offset,z - CUBE_LENGTH,
				x + offset, y - offset, z - CUBE_LENGTH,
				x + offset, y - offset, z };
	}
	private float[] GetCubeColor(Block block) {
		switch (block.getID()) {
		case 1:
			return new float[] { 0, 1, 0 };

		case 2:
			return new float[] { 1, 0.5f, 0 };
		case 3:
			return new float[] { 0, 0f, 1f };
		}
		return new float[] { 1, 1, 1 };
	}

	private float[] CreateCubeVertexCol(float[] CubeColorArray) {
		float[] cubeColors = new float[CubeColorArray.length * 4 * 6];
		for (int i = 0; i < cubeColors.length; i++) {
			cubeColors[i] = CubeColorArray[i % CubeColorArray.length];
		}
		return cubeColors;
	} 

}
