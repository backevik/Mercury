package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import gui.ZContainer;
import core.Drawable;
import core.EventAdder;
import core.MouseObject;

public class MainMenuViewer extends ZContainer implements Drawable {

	private ZButton btnNewGame;
	private ZButton btnLoadGame;
	private ZButton btnCredits;
	private ZButton btnExitGame;
	
	public MainMenuViewer(EventAdder eventAdder, Image image, int x, int y, List<MouseObject> mouseObjects) {
		super(eventAdder, image, x, y, mouseObjects);
		
		btnNewGame = new ZButton(eventAdder, "initNewGame", "New Game", 320, 100, 140, 80);
		components.add(btnNewGame);
		mouseObjects.add(btnNewGame);
		
		btnLoadGame = new ZButton(eventAdder, "loadGame", "Load Game", 320, 200, 140, 80);
		components.add(btnLoadGame);
		mouseObjects.add(btnLoadGame);
		
		btnCredits = new ZButton(eventAdder, "showCredits", "Credits", 320, 400, 140, 80);
		components.add(btnCredits);
		mouseObjects.add(btnCredits);
		
		btnExitGame = new ZButton(eventAdder, "exitGame", "Exit Game", 320, 500, 140, 80);
		components.add(btnExitGame);
		mouseObjects.add(btnExitGame);		
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
	}

	@Override
	public void remove() {
		btnNewGame = null;
		mouseObjects.remove(btnNewGame);
		btnLoadGame = null;
		mouseObjects.remove(btnLoadGame);
		btnCredits = null;
		mouseObjects.remove(btnCredits);
		btnExitGame = null;
		mouseObjects.remove(btnExitGame);
	}
}
