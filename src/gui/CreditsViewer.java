package gui;

import java.util.List;

import core.EventAdder;
import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import zlibrary.ZTextArea;

/**
 * Class for setting up the scene for the credits with all its GUI elements
 * 
 * @author	Anton Andrén
 * @version	0.1	
 * @since	015-03-01
 */

public class CreditsViewer extends ZContainer
{
	/**
	 * public CreditsViewer(EventAdder eventAdder, List<ZEntity> entities)
	 * Creates the GUI elements for the CreditsViewer.
	 * 
	 * @param eventAdder	-	Used to add events in the eventQueue to be processed by the game loop.
	 * @param entities		-	Used to track player interactions with an object.
	 */
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