package gui;

import java.awt.Image;
import java.util.List;

import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import zlibrary.ZImage;
import zlibrary.ZText;
import core.EventAdder;
import database.ImageDatabase;

/**
 * @author	Mattias Benngård	<mbengan@gmail.com>
 * @version	1.0					<2015-02-27>
 * @since	2015-02-15
 * 
 * Class for setting up the scene for Main Menu
 * 
 * public MainMenuViewer (Image, int, int, EventAdder, List<ZEntity>, QuestLog)
 * Creates the GUI elements for the mainMenuViewer.
 */

public class MainMenuViewer extends ZContainer
{
	public MainMenuViewer(Image image, int x, int y, EventAdder eventAdder, List<ZEntity> entities) {
		super(image, x, y, eventAdder, entities);
		
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
