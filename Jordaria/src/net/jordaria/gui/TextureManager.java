package net.jordaria.gui;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.HashMap;

import javax.imageio.ImageIO;

import net.jordaria.FileIO;

/**
 * Handles loading, storing, and retrieving textures.
 * 
 * @author Ches Burks
 *
 */
public class TextureManager {

	HashMap<String, BufferedImage> textures = new HashMap<String, BufferedImage>();
	
	private String fileSeperator = System.getProperty("file.separator");

	/**
	 * Constructs a new TextureManager and loads a test png.
	 */
	public TextureManager() {
		loadTexture("test");
		loadTexture("floor");
		loadTexture("floorBloody");
		loadTexture("floorBroken");
		loadTexture("floorCracked");
		loadTexture("floorPlant");
		loadTexture("floorRocks");
		loadTexture("walls");
		loadTexture("roof");
		loadTexture("doorN");
		loadTexture("notexture");
	}

	/**
	 * Loads the texture located in the given path.
	 * Assumes .png filetype.
	 * 
	 * @param name The name of the file to load
	 */
	public void loadTexture(String name){
		try {
			BufferedImage img;
			String path = fileSeperator+"assets"+fileSeperator+"textures"+fileSeperator;
			path.replaceAll("\\." , "/");
			img = ImageIO.read(FileIO.class.getResource(path+name+".png"));
			textures.put(name, img);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Gets the {@link ByteBuffer ByteBuffer} of the specified image name.
	 * 
	 * @param name The name of the image
	 * @return The ByteBuffer for the image
	 */
	public BufferedImage getTextureBuffer(String name){
		if (textures.containsKey(name)){
			return textures.get(name);
		}
		else {
			return textures.get("test.png");
		}
	}
}
