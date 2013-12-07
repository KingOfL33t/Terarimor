package net.jordaria;

import java.io.File;
import java.io.IOException;

import net.jordaria.event.Error;

public class FileIO {

	private Jordaria jd;
	public FileIO(Jordaria jordaria){
		this.jd = jordaria;
	}
	public void createMainDirectories(String homeDir){
		File jdRootDir = new File(homeDir.concat("Jordaria/"));
		File savesDir = new File(homeDir.concat("Jordaria/saves"));
		File assetsDir = new File(homeDir.concat("Jordaria/assets/"));
		File languageDir = new File(homeDir.concat("Jordaria/assets/lang/"));
		File texturesDir = new File(homeDir.concat("Jordaria/assets/textures"));
		File blockTextureDir = new File(homeDir.concat("Jordaria/assets/textures/block"));
		File itemTextureDir = new File(homeDir.concat("Jordaria/assets/textures/item"));
		File entityTextureDir = new File(homeDir.concat("Jordaria/assets/textures/entity"));

		try {
			jdRootDir.createNewFile();
			assetsDir.createNewFile();
			savesDir.createNewFile();
			languageDir.createNewFile();
			texturesDir.createNewFile();
			blockTextureDir.createNewFile();
			itemTextureDir.createNewFile();
			entityTextureDir.createNewFile();
		} catch (IOException e) {
			jd.eventManager.fireEvent(new Error("Error creating directories"));
		}

	}
}
