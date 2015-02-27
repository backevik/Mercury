package gui;

import java.awt.Image;
import java.util.List;

import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import zlibrary.ZText;
import core.EventAdder;

/**
 * @author	Mattias Benngård	<mbengan@gmail.com>
 * @version	0.1					<2015-02-27>
 * @since	2015-02-15
 * 
 * Class for setting up the scene for Character Creation
 * 
 * public CharacterCreationViewer (Image, int, int, EventAdder, List<ZEntity>)
 * Creates the GUI elements for the characterCreationViewer.
 */

public class CharacterCreationViewer extends ZContainer
{	
	public CharacterCreationViewer(Image image, int x, int y, EventAdder eventAdder, List<ZEntity> entities) {
		super(image, x, y, eventAdder, entities);
		
		components.add(new ZText ("Character Creation", 20, 30, 20));
		
		components.add(new ZText ("Character Name", 20, 70, 16));
		components.add(new ZText ("John Doe", 170, 70, 16));
		
		/*textFieldUserName = new ZTextField("Default", x,  y,  200, 20);
		components.add(textFieldUserName);
		entities.add(textFieldUserName);*/

		components.add(new ZText ("Gender", 20, 110, 16));
		components.add(new ZText ("No Genders Implemented", 170, 110, 16));
		
		components.add(new ZText ("Race", 20, 150, 16));
		components.add(new ZText ("No Races Implemented", 170, 150, 16));
		
		components.add(new ZText ("Class", 20, 190, 16));
		components.add(new ZText ("No Classes Implemented", 170, 190, 16));
		
		components.add(new ZText ("Attributes", 20, 230, 16));		
		for (int i = 0; i < constants.Attributes.getNames().size(); i++) {
			components.add(new ZText (constants.Attributes.getNames().get(i), 20, 250+i*18, 14));
			components.add(new ZText ("5", 170, 250+i*18, 14));
		}		
		
		ZButton btnBack = new ZButton("Back", 20, 520, 120, 60, eventAdder, "sceneMainMenu");
		components.add(btnBack);
		entities.add(btnBack);
		
		ZButton btnDone = new ZButton("Done", 660, 520, 120, 60, eventAdder, "createCharacter,John Doe");
		components.add(btnDone);
		entities.add(btnDone);
	}
}