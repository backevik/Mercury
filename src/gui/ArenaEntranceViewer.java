package gui;

import java.util.List;

import core.EventAdder;
import database.ImageDatabase;
import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import zlibrary.ZText;

/**
 * Sets up the scene for the arena entrance with all its GUI elements.
 * 
 * @author	Anton Andrén
 * @version	1.0	
 * @since	2015-03-05
 */

public class ArenaEntranceViewer extends ZContainer
{
	/**
	 * Creates the GUI elements for the ArenaEntranceViewer
	 * @param eventAdder reference to the event queue
	 * @param entities copy of entity list, to add components
	 */
	public ArenaEntranceViewer(EventAdder eventAdder, List<ZEntity> entities) {
		super(ImageDatabase.getInstance().getImage("ArenaBG.jpg"), 0, 0, eventAdder, entities);
		
		ZText txtWelcome = new ZText("Welcome to the gladiator arena", 225, 130, 22);
		components.add(txtWelcome);
		
		ZText txtInfo = new ZText("Once you enter it only gets harder untill you lose", 250, 205, 12);
		components.add(txtInfo);
		
		ZButton btnStartArena = new ZButton("Enter Arena", 310, 300, 180, 60, eventAdder, "enterArena");
		components.add(btnStartArena);
		entities.add(btnStartArena);
		
		ZButton btnLeaveArena = new ZButton("Leave", 310, 380, 180, 60, eventAdder, "leaveArena");
		components.add(btnLeaveArena);
		entities.add(btnLeaveArena);
	}
}