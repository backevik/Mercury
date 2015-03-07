package database;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * A singleton that handles all images in game. Image file formats supported are .jpg and .png.
 * 
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		1.0
 * @since		2015-03-07
 */
	

public class ImageDatabase
{
	private final HashMap<String, Image> images = new HashMap<String, Image>();
	private static ImageDatabase instance = null;
	
	/**
	 * Search through all maps in directory and saves all images in a hashmap for later use.
	 */
	private ImageDatabase(){
		
		File baseDirectory = new File("img/");	//base dir
		File[] f = baseDirectory.listFiles();	//List of files and maps in base dir
		
		if(baseDirectory.isDirectory()) {
			for(File file : f) {	//Checks through all maps and images within base dir
				
				if(file != null && ( file.getName().toLowerCase().endsWith(".jpg") || file.getName().toLowerCase().endsWith(".png") ) && file.isFile()){
					try {
						images.put( file.getName(), ImageIO.read( new File(file.getCanonicalPath())) );	//add file in base dir
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					
				}else if( !file.isFile() )	//If directory is a map
					addFilesFromMap(file);
			}
		}
	}
	
	/**
	 * Add files from directory map. May reuse itself if there's a map within the map
	 * @param dir
	 */
	public void addFilesFromMap(File map) {
		
		try {
			if(map.isDirectory()) {
				File[] f = map.listFiles();

				for(File file : f) {
					if( !file.isFile() ) {
						addFilesFromMap(file);
						
					}else if(file != null && ( file.getName().toLowerCase().endsWith(".jpg") || file.getName().toLowerCase().endsWith(".png") ) && file.isFile()) {
						images.put( file.getName(), ImageIO.read( new File(file.getCanonicalPath())) );	//Add files from map
					}
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Singleton initializing
	 */
	public static ImageDatabase getInstance() {
		if(instance == null) {
			instance = new ImageDatabase();
		}
		return instance;
			
	}
	
	/**
	 * Get an image from the database by providing the key as a String
	 * @param imgName - key
	 * @return Image corresponding to key
	 */
	public Image getImage(String imgName) {
		return images.get(imgName);
	}
}
