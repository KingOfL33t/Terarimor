package net.jordaria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import net.jordaria.event.DebugMessage;
import net.jordaria.event.Error;

public class FileIO {

	private Jordaria jd;

	public FileIO(Jordaria jordaria){
		this.jd = jordaria;
	}

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

	public void copyFilesToDisk(String homeDir){
		try {
			String seperator = File.separator;
			File lang_en_US = new File(homeDir.concat(seperator+"Jordaria"+seperator+"assets"+seperator+"lang"+seperator+"en_US.lang"));
			try {
				lang_en_US.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			final String path = "assets/lang";
			final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());

			if(jarFile.isFile()) {  // Run with JAR file
			    final JarFile jar = new JarFile(jarFile);
			    final Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
			    while(entries.hasMoreElements()) {
			        final String name = entries.nextElement().getName();
			        if (name.startsWith(path + "/")) { //filter according to the path
			            System.out.println(name);
			            jd.eventManager.fireEvent(new DebugMessage(name));
			        }
			    }
			    jar.close();
			} else { // Run with IDE
			    final URL url = FileIO.class.getResource("/" + path);
			    if (url != null) {
			        try {
			            final File apps = new File(url.toURI());
			            for (File app : apps.listFiles()) {
			                System.out.println(app);
			                jd.eventManager.fireEvent(new DebugMessage(app.getAbsolutePath()));
			            }
			        } catch (URISyntaxException ex) {
			            // never happens
			        }
			    }
			}
			InputStream en_US_stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("/assets/lang/en_US.lang");
			//BufferedReader en_US_reader = new BufferedReader(new InputStreamReader(en_US_stream, "UTF-8"));
			BufferedWriter en_US_writer = new BufferedWriter(getBufferedWriterForFile(lang_en_US.getAbsolutePath()));


			//while(en_US_reader.ready()){
			//	en_US_writer.write(en_US_reader.read());
			//}
		} catch (IOException e) {
			jd.eventManager.fireEvent(new Error("Error copying file to disk ("+e.getMessage()+")"));
		}

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
	/*
	 * Creates a bufferedReader for the specified file, and returns it or null if it cannot be found
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
