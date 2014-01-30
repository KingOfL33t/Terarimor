package net.jordaria.gui;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

/**
 * Handles loading, storing, and retrieving textures.
 * 
 * @author Ches Burks
 *
 */
public class TextureManager {

	HashMap<String, ByteBuffer> textures = new HashMap<String, ByteBuffer>();

	/**
	 * Constructs a new TextureManager and loads a test png.
	 */
	public TextureManager() {
		loadTexture("assets\\textures\\test.png");
	}
	
	/**
	 * Loads the texture located in the given path.
	 * 
	 * @param path The path of the file to load
	 */
	public void loadTexture(String path){

		InputStream in;
		try {
			in = new FileInputStream(path);		

			PNGDecoder decoder = new PNGDecoder(in);

			ByteBuffer buf = ByteBuffer.allocateDirect(4*decoder.getWidth()*decoder.getHeight());
			decoder.decode(buf, decoder.getWidth()*4, Format.RGBA);
			buf.flip();
			textures.put(path.substring(path.lastIndexOf("\\")+1), buf);
			
			in.close();
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
	public ByteBuffer getTextureBuffer(String name){
		if (textures.containsKey(name)){
			return textures.get(name);
		}
		else {
			return textures.get("test.png");
		}
	}
}
