package gui;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import player.Player;
import core.EventAdder;
import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import zlibrary.ZText;

/**
 * Sets up and holds all gui elements to the load game scenes
 * 
 * @author	Mattias Benngård	<mbengan@gmail.com> (Daniel Edsinger)
 * @version	1.0	
 * @since	2015-03-07
 */

public class LoadGameViewer extends ZContainer
{
	/**
	 * Constructor for CharacterCreationViewer
	 * @param image - background image of the scene
	 * @param x - where the scene begins in x
	 * @param y - where the scene begins in y
	 * @param eventAdder - reference to the event queue
	 * @param entities - copy of entity list, to add components
	 * @throws ClassNotFoundException
	 */
	public LoadGameViewer(Image image, int x, int y, EventAdder eventAdder, List<ZEntity> entities) throws ClassNotFoundException {
		super(image, x, y, eventAdder, entities);
		
		components.add(new ZText ("Save Files", 20, 30, 20));
		
		if (getProfileNames().size() == 0) {
			components.add(new ZText ("There is no saved game data avaible.", 20, 70, 16));
		} else {
			int i = 0;
			for (String s : getProfileNames()) {
				ZButton save = new ZButton("Load " + s, 20, 70 + i*100, 240, 60, eventAdder, "loadGame,"+s);
				components.add(save);
				entities.add(save);
				i++;
			}
		}
		
		ZButton btnBack = new ZButton("Back", 20, 520, 120, 60, eventAdder, "sceneMainMenu");
		components.add(btnBack);
		entities.add(btnBack);
	}
	
	/**
	 * Get all profile names from all savefiles in /saves directory
	 * @return List of names in the save files
	 */
	private List<String> getProfileNames () {
		
		List<String> profileNames = new ArrayList<>();
		
		File dir = new File("saves/");	//base directory
		File[] saves = dir.listFiles();	//List of files and maps in base directory

		for(File file : saves) {	//Checks through files in directory
			if(file != null && ( file.getName().endsWith("savefile.dat")) && file.isFile()){
				try {
					FileInputStream fis = new FileInputStream (file.getCanonicalPath());
					ObjectInputStream ois = new ObjectInputStream (fis);
					
					Player player = (Player) ois.readObject();
					profileNames.add( player.getPC().getName() );
					
					ois.close();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return profileNames;
	}
}