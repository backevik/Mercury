package database;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3
 * @since		2015-02-17
 */	

public class ImageDatabase
{
	private final HashMap<String, Image> images = new HashMap<String, Image>();
	
	public ImageDatabase() {		
		initialize();
	}
	
	public void initialize() {
		try {
			File directory = new File("img/World/");
			File[] f = directory.listFiles();
			
			if(directory.isDirectory()) {
				
				for(File file : f) {
					
					System.out.println("Absolute path:  " + file.getAbsolutePath() + "\nCanonical path: " + file.getCanonicalPath() + "\n");
					
					if(file != null && file.getName().toLowerCase().endsWith(".jpg"))
					{
						images.put( file.getName(), ImageIO.read( new File(file.getCanonicalPath())) );
					}
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public Image getImage(String imgName) {
		return images.get(imgName);
	}
}
