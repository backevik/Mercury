package database;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
	
	public ImageDatabase() throws IOException {
		
		File directory = new File("img");
		File[] f = directory.listFiles();
		
		for(File file : f)
		{
			if(file != null && file.getName().toLowerCase().endsWith(".jpeg"))
			{
				images.put( file.getName(), ImageIO.read( new File(file.getCanonicalPath())) );
			}
		}
	}
	
	public Image getImage(String imgName) {
		return images.get(imgName);
	}
}
