package net.jordaria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import net.jordaria.event.events.DebugMessage;
import net.jordaria.event.events.Error;

/**
 * Controls file input and output.
 * 
 * @author Ches Burks
 *
 */
public class FileIO {

	private Jordaria jd;

	/**
	 * Constructs a new fileIO.
	 * 
	 * @param jordaria A reference to the main program
	 */
	public FileIO(Jordaria jordaria){
		this.jd = jordaria;
	}

	/**
	 * Creates folders in the application data for storing data.
	 * 
	 * @param homeDir The directory where app data is stored
	 */
	public void createMainDirectories(String homeDir){
		String seperator = File.separator;
		File jdRootDir = new File(homeDir.concat(seperator+"Jordaria"+seperator));
		File savesDir = new File(homeDir.concat(seperator+"Jordaria"+seperator+"saves"+seperator));
		File assetsDir = new File(homeDir.concat(seperator+"Jordaria"+seperator+"assets"+seperator));
		File languageDir = new File(homeDir.concat(seperator+"Jordaria"+seperator+"assets"+seperator+"lang"+seperator));
		File texturesDir = new File(homeDir.concat(seperator+"Jordaria"+seperator+"assets"+seperator+"textures"+seperator));
		File blockTextureDir = new File(homeDir.concat(seperator+"Jordaria"+seperator+"assets"+seperator+"textures"+seperator+"block"+seperator));
		File itemTextureDir = new File(homeDir.concat(seperator+"Jordaria"+seperator+"assets"+seperator+"textures"+seperator+"item"+seperator));
		File entityTextureDir = new File(homeDir.concat(seperator+"Jordaria"+seperator+"assets"+seperator+"textures"+seperator+"entity"+seperator));

		jdRootDir.mkdirs();
		assetsDir.mkdirs();
		savesDir.mkdirs();
		languageDir.mkdirs();
		texturesDir.mkdirs();
		blockTextureDir.mkdirs();
		itemTextureDir.mkdirs();
		entityTextureDir.mkdirs();

	}

	/**
	 * Copies files to the folders created in the Jordaria app data folder.
	 * Fires an error event if there is a problem.
	 * 
	 * @param homeDir The directory where app data is stored
	 */
	public void copyFilesToDisk(String homeDir){
		try {
			String seperator = "\\";//File.separator;


			final String path;

			final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
			//jd.eventManager.fireEvent(new DebugMessage(getClass().getProtectionDomain().getCodeSource().getLocation().getPath()));
			if(jarFile.isFile()) {  // Run with JAR file
				seperator = "/";
				path = jarFile.getAbsolutePath()+seperator+"assets"+seperator+"textures";
				path.replaceAll("\\." , "/");

				final JarFile jar = new JarFile(jarFile);
				final Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
				while(entries.hasMoreElements()) {

					final String name = entries.nextElement().getName();
					if (name.startsWith(path)) { //filter according to the path
						//System.out.println(name);
						jd.eventManager.fireEvent(new DebugMessage(name));
					}
				}

				jar.close();
			}
			//Uncomment to output dirs
			/*
			else { // Run with IDE
				path = seperator+"assets"+seperator+"textures";
				path.replaceAll("\\." , "/");
			    final URL url = FileIO.class.getResource(path);
			    if (url != null) {
			        try {
			            final File apps = new File(url.toURI());
			            for (File app : apps.listFiles()) {
			                System.out.println(app);
			                //jd.eventManager.fireEvent(new DebugMessage(app.getAbsolutePath()));
			            }
			        } catch (URISyntaxException ex) {
			            // never happens
			        }
			    }
			}*/
		} catch (IOException e) {
			jd.eventManager.fireEvent(new Error("Error copying file to disk ("+e.getMessage()+")"));
		}

	}

	/**
	 * Creates a bufferedReader for the specified file, and returns it or null if it cannot be found.
	 * Fires an error event if the file is not found.
	 * 
	 * @param filepath The path to the file to open a BufferedReader to
	 * @return A bufferedReader for the file requested
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
	/**
	 * Creates a BufferedWriter for the specified file, and returns it or null if it cannot be found.
	 * Fires an error event if the file is not found.
	 * 
	 * @param filepath The path to the file to open a BufferedWriter to
	 * @return A bufferedWriter for the file requested
	 */
	public BufferedWriter getBufferedWriterForFile(String filepath){
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(filepath));

		} catch (IOException e) {
			jd.eventManager.fireEvent(new Error("Error creating bufferedWriter for "+filepath));
		}
		return writer;
	}
}
