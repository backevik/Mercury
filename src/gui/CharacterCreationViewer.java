package gui;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZDrawable;
import zlibrary.ZEntity;
import zlibrary.ZText;
import zlibrary.ZTextField;
import core.EventAdder;

public class CharacterCreationViewer extends ZContainer implements ZDrawable {

	private ZText header;
	
	private ZText textName;
	private ZTextField textFieldName;
	
	private ZText textGender;
	
	private ZText textRace;
	
	private ZText textClass;
	
	private ZText textAttribute;
	
	private List<ZText> textAttributes = new ArrayList<>();
	
	private ZButton btnDone;
	
	public CharacterCreationViewer(Image image, int x, int y, EventAdder eventAdder, List<ZEntity> entities) {
		super(image, x, y, eventAdder, entities);
		
		textName = new ZText ("Character Creation", 20, 30, 20);
		components.add(textName);
		
		textName = new ZText ("Character Name", 20, 70, 16);
		components.add(textName);
		/*Temp*/ components.add(new ZText ("John Doe", 170, 70, 16));
		/*textFieldUserName = new ZTextField("Default", x,  y,  200, 20);
		components.add(textFieldUserName);
		entities.add(textFieldUserName);*/
		
		textGender = new ZText ("Gender", 20, 110, 16);
		components.add(textGender);
		/*Temp*/ components.add(new ZText ("No Genders Implemented", 170, 110, 16));
		
		textRace = new ZText ("Race", 20, 150, 16);
		components.add(textRace);
		/*Temp*/ components.add(new ZText ("No Races Implemented", 170, 150, 16));
		
		textClass = new ZText ("Class", 20, 190, 16);
		components.add(textClass);
		/*Temp*/ components.add(new ZText ("No Classes Implemented", 170, 190, 16));
		
		textAttribute = new ZText ("Attributes", 20, 230, 16);
		components.add(textAttribute);
		
		for (int i = 0; i < constants.Attributes.getNames().size(); i++) {
			ZText tmpAttribute = new ZText (constants.Attributes.getNames().get(i), 20, 250+i*18, 14);
			textAttributes.add(tmpAttribute);
			components.add(tmpAttribute);
			/*Temp*/ components.add(new ZText ("5", 170, 250+i*18, 14));
		}		
		
		btnDone = new ZButton("Back", 20, 520, 120, 60, eventAdder, "sceneMainMenu");
		components.add(btnDone);
		entities.add(btnDone);
		
		btnDone = new ZButton("Done", 660, 520, 120, 60, eventAdder, "createCharacter");
		components.add(btnDone);
		entities.add(btnDone);
	}
}