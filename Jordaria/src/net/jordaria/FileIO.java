package net.jordaria;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import net.jordaria.event.Error;

public class FileIO {

	private Jordaria jd;

	public FileIO(Jordaria jordaria){
		this.jd = jordaria;
	}

	public void createMainDirectories(String homeDir){
		File jdRootDir = new File(homeDir.concat("\\Jordaria\\"));
		File savesDir = new File(homeDir.concat("\\Jordaria/saves\\"));
		File assetsDir = new File(homeDir.concat("\\Jordaria\\assets\\"));
		File languageDir = new File(homeDir.concat("\\Jordaria\\assets\\lang\\"));
		File texturesDir = new File(homeDir.concat("\\Jordaria\\assets\\textures\\"));
		File blockTextureDir = new File(homeDir.concat("\\Jordaria\\assets\\textures\\block\\"));
		File itemTextureDir = new File(homeDir.concat("\\Jordaria\\assets\\textures\\item\\"));
		File entityTextureDir = new File(homeDir.concat("\\Jordaria\\assets\\textures\\entity\\"));

		jdRootDir.mkdirs();
		assetsDir.mkdirs();
		savesDir.mkdirs();
		languageDir.mkdirs();
		texturesDir.mkdirs();
		blockTextureDir.mkdirs();
		itemTextureDir.mkdirs();
		entityTextureDir.mkdirs();

	}

	/*
	 * Creates a bufferedReader for the specified file, and returns it or null if it cannot be found
	 */
	public BufferedReader getBufferedReaderForFile(String filepath){
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filepath));

		} catch (FileNotFoundException e) {
			jd.eventManager.fireEvent(new Error("Error creating bufferedReader for "+filepath));
		}
		return reader;
	}
}
