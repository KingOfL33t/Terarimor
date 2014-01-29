package net.jordaria.gui;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

public class TextureManager {

	HashMap<String, ByteBuffer> textures = new HashMap<String, ByteBuffer>();

	public TextureManager() {
		loadTexture("assets\\textures\\test.png");
	}
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
	
	public ByteBuffer getTextureBuffer(String name){
		if (textures.containsKey(name)){
			return textures.get(name);
		}
		else {
			return textures.get("test.png");
		}
	}
}
