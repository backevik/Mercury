package gui;

import java.awt.Image;
import java.util.List;

import gui.ZContainer;
import core.Drawable;
import core.EventAdder;
import core.Entity;

public class MainMenuViewer extends ZContainer implements Drawable {

	private ZButton btnNewGame;
	private ZButton btnLoadGame;
	private ZButton btnCredits;
	private ZButton btnExitGame;
	
	public MainMenuViewer(EventAdder eventAdder, Image image, int x, int y, List<Entity> mouseObjects) {
		super(eventAdder, image, x, y, mouseObjects);
		
		btnNewGame = new ZButton(eventAdder, "sceneCharacterCreation", "New Game", 300, 100, 180, 80);
		components.add(btnNewGame);
		mouseObjects.add(btnNewGame);
		
		btnLoadGame = new ZButton(eventAdder, "sceneLoadGame", "Load Game", 300, 200, 180, 80);
		components.add(btnLoadGame);
		mouseObjects.add(btnLoadGame);
		
		btnCredits = new ZButton(eventAdder, "sceneShowCredits", "Credits", 300, 350, 180, 80);
		components.add(btnCredits);
		mouseObjects.add(btnCredits);
		
		btnExitGame = new ZButton(eventAdder, "exitGame", "Exit Game", 300, 500, 180, 80);
		components.add(btnExitGame);
		mouseObjects.add(btnExitGame);		
	}
}
