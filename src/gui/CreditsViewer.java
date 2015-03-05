package gui;

import java.util.List;

import core.EventAdder;
import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import zlibrary.ZTextArea;

/**
 * @author	Anton Andrén
 * @version	0.1	
 * @since	015-03-01
 * 
 * Class for setting up the scene for the credits
 * 
 * public CreditsViewer(EventAdder eventAdder, List<ZEntity> entities)
 * Creates the GUI elements for the CreditsViewer.
 */

public class CreditsViewer extends ZContainer
{
	public CreditsViewer(EventAdder eventAdder, List<ZEntity> entities) {
		super(null, 0, 0, eventAdder, entities);
		
		ZTextArea txtAreaCredits = new ZTextArea(285, 200, 400, 300, 30);
		txtAreaCredits.addText("Developers: \n Anton Andrén \n Mattias Benngård \n Andreas Bäckevik \n Martin Claesson \n Daniel Edsinger");
		components.add(txtAreaCredits);
		
		ZButton btnBack = new ZButton("Back to menu", 300, 400, 200, 60, eventAdder, "sceneMainMenu");
		components.add(btnBack);
		entities.add(btnBack);

	}
}