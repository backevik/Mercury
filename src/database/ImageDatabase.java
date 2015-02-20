package database;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3.2
 * @since		2015-02-20
 */
	

public class ImageDatabase
{
	private final HashMap<String, Image> images = new HashMap<String, Image>();
	
	public ImageDatabase(){
		
		File baseDirectory = new File("img/");	//base dir
		File[] f = baseDirectory.listFiles();	//List of files and maps in base dir
		
		if(baseDirectory.isDirectory()) {
			for(File file : f) {	//Checks through all maps and images within base dir
				
				if(file != null && file.getName().toLowerCase().endsWith(".jpg") && file.isFile()){
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
						
					}else if(file != null && file.getName().toLowerCase().endsWith(".jpg") && file.isFile()) {
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
	 * Return an image from hashmap of images
	 * @param imgName
	 * @return	if image exists, return that image
	 * 			else return null
	 */
	public Image getImage(String imgName) {
		return images.get(imgName);
	}
}
