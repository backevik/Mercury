package gui;

import java.awt.Image;
import java.util.List;

import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZText;

import core.Drawable;
import core.EventAdder;
import core.Entity;

public class MainMenuViewer extends ZContainer implements Drawable {

	private ZButton btnNewGame;
	private ZButton btnLoadGame;
	private ZButton btnCredits;
	private ZButton btnExitGame;
	
	public MainMenuViewer(Image image, int x, int y, EventAdder eventAdder, List<Entity> mouseObjects) {
		super(image, x, y, eventAdder, mouseObjects);
		
		btnNewGame = new ZButton("New Game", 310, 100, 180, 80, eventAdder, "sceneCharacterCreation");
		components.add(btnNewGame);
		mouseObjects.add(btnNewGame);
		
		btnLoadGame = new ZButton("Load Game", 310, 200, 180, 80, eventAdder, "popupWindow");
		components.add(btnLoadGame);
		mouseObjects.add(btnLoadGame);
		
		btnCredits = new ZButton("Credits", 310, 350, 180, 80, eventAdder, "sceneCredits");
		components.add(btnCredits);
		mouseObjects.add(btnCredits);
		
		btnExitGame = new ZButton("Exit Game", 310, 500, 180, 80, eventAdder, "exitGame");
		components.add(btnExitGame);
		mouseObjects.add(btnExitGame);
	}
}
