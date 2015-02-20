package gui;

import java.awt.Image;
import java.util.List;

import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZDrawable;
import zlibrary.ZEntity;
import zlibrary.ZTextField;
import core.EventAdder;

public class CharacterCreationViewer extends ZContainer implements ZDrawable {

	private ZTextField textFieldUserName;
	private ZButton btnDone;
	
	public CharacterCreationViewer(Image image, int x, int y, EventAdder eventAdder, List<ZEntity> entities) {
		super(image, x, y, eventAdder, entities);
		
		/*textFieldUserName = new ZTextField("Default", x,  y,  200, 20);
		components.add(textFieldUserName);
		entities.add(textFieldUserName);*/
		
		btnDone = new ZButton("Back", 20, 520, 120, 60, eventAdder, "sceneMainMenu");
		components.add(btnDone);
		entities.add(btnDone);
		
		btnDone = new ZButton("Done", 660, 520, 120, 60, eventAdder, "createCharacter");
		components.add(btnDone);
		entities.add(btnDone);
	}
}