package gui;

import java.util.List;

import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import zlibrary.ZImage;
import zlibrary.ZText;
import core.EventAdder;
import database.ImageDatabase;

/**
 * Sets up and holds all gui elements to the main menu
 * 
 * @author	Mattias Benngard	<mbengan@gmail.com>
 * @version	1.0					<2015-03-07>
 * @since	2015-02-15
 */

public class MainMenuViewer extends ZContainer
{	
	/**
	 * Constructor initializes all the components and adds them to the drawable and clickable list
	 * @param eventAdder reference to the event queue
	 * @param entities copy of entity list, to add components
	 */
	public MainMenuViewer(EventAdder eventAdder, List<ZEntity> entities) {
		super(null, 0, 0, eventAdder, entities);
		
		components.add(new ZImage(ImageDatabase.getInstance().getImage("title.png"), (800-ImageDatabase.getInstance().getImage("title.png").getWidth(null))/2, 40));
		
		ZButton btnNewGame = new ZButton("New Game", 310, 120, 180, 60, eventAdder, "sceneCharacterCreation");
		components.add(btnNewGame);
		entities.add(btnNewGame);
		
		ZButton btnLoadGame = new ZButton("Load Game", 310, 200, 180, 60, eventAdder, "sceneLoadGame");
		components.add(btnLoadGame);
		entities.add(btnLoadGame);
		
		ZButton btnCredits = new ZButton("Credits", 310, 320, 180, 60, eventAdder, "sceneCredits");
		components.add(btnCredits);
		entities.add(btnCredits);
		
		ZButton btnHighScore = new ZButton("High Score", 310, 400, 180, 60, eventAdder, "sceneHighScore");
		components.add(btnHighScore);
		entities.add(btnHighScore);
		
		ZButton btnExitGame = new ZButton("Exit Game", 310, 520, 180, 60, eventAdder, "exitGame");
		components.add(btnExitGame);
		entities.add(btnExitGame);
		
		components.add(new ZText ("Copyright 2015 - Chalmers University of Technology", 530, 582, 10));
	}
}
