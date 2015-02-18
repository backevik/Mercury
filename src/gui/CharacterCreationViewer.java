package gui;

import java.awt.Image;
import java.util.List;

import core.Drawable;
import core.EventAdder;
import core.MouseObject;

public class CharacterCreationViewer extends ZContainer implements Drawable {

	private ZTextField textFieldUserName;
	private ZButton btnDone;
	
	public CharacterCreationViewer(EventAdder eventAdder, Image image, int x, int y, List<MouseObject> mouseObjects) {
		super(eventAdder, image, x, y, mouseObjects);
		
		textFieldUserName = new ZTextField("Default", x,  y,  200, 20);
		components.add(textFieldUserName);
		mouseObjects.add(textFieldUserName);
		
		btnDone = new ZButton(eventAdder, "createCharacter", "Done!", 360, 330, 120, 60);
		components.add(btnDone);
		mouseObjects.add(btnDone);
	}
}