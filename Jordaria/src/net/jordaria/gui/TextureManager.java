package net.jordaria.gui;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;

import javax.imageio.ImageIO;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

/**
 * Handles loading, storing, and retrieving textures.
 * 
 * @author Ches Burks
 *
 */
public class TextureManager {

	HashMap<String, BufferedImage> textures = new HashMap<String, BufferedImage>();

	/**
	 * Constructs a new TextureManager and loads a test png.
	 */
	public TextureManager() {
		loadTexture("test.png");
		loadTexture("floor.png");
	}
	
	/**
	 * Loads the texture located in the given path.
	 * 
	 * @param path The path of the file to load
	 */
	public void loadTexture(String path){
		try {
			BufferedImage img;
			img = ImageIO.read(this.getClass().getResource(path));
			textures.put("floor", img);
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
