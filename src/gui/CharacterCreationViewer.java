package gui;

import java.awt.Image;
import java.util.List;

import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZTextField;

import core.Drawable;
import core.EventAdder;
import core.Entity;

public class CharacterCreationViewer extends ZContainer implements Drawable {

	private ZTextField textFieldUserName;
	private ZButton btnDone;
	
	public CharacterCreationViewer(Image image, int x, int y, EventAdder eventAdder, List<Entity> mouseObjects) {
		super(image, x, y, eventAdder, mouseObjects);
		
		/*textFieldUserName = new ZTextField("Default", x,  y,  200, 20);
		components.add(textFieldUserName);
		mouseObjects.add(textFieldUserName);*/
		
		btnDone = new ZButton("Back", 20, 520, 120, 60, eventAdder, "sceneMainMenu");
		components.add(btnDone);
		mouseObjects.add(btnDone);
		
		btnDone = new ZButton("Done", 660, 520, 120, 60, eventAdder, "createCharacter");
		components.add(btnDone);
		mouseObjects.add(btnDone);
	}
}