package gui;

import java.awt.Image;
import java.util.List;

import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import core.EventAdder;

public class MainMenuViewer extends ZContainer {

	private ZButton btnNewGame;
	private ZButton btnLoadGame;
	private ZButton btnCredits;
	private ZButton btnExitGame;
	
	public MainMenuViewer(Image image, int x, int y, EventAdder eventAdder, List<ZEntity> entities) {
		super(image, x, y, eventAdder, entities);
		
		btnNewGame = new ZButton("New Game", 310, 100, 180, 80, eventAdder, "sceneCharacterCreation");
		components.add(btnNewGame);
		entities.add(btnNewGame);
		
		btnLoadGame = new ZButton("Load Game", 310, 200, 180, 80, eventAdder, "sceneLoadGame");
		components.add(btnLoadGame);
		entities.add(btnLoadGame);
		
		btnCredits = new ZButton("Credits", 310, 350, 180, 80, eventAdder, "sceneCredits");
		components.add(btnCredits);
		entities.add(btnCredits);
		
		btnExitGame = new ZButton("Exit Game", 310, 500, 180, 80, eventAdder, "exitGame");
		components.add(btnExitGame);
		entities.add(btnExitGame);
	}
}
