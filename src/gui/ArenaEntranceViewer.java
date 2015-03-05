package gui;

import java.util.List;

import core.EventAdder;
import database.ImageDatabase;
import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import zlibrary.ZText;

/**
 * @author	Anton Andrén
 * @version	1.0	
 * @since	015-03-05
 * 
 * Class for setting up the scene for the arena entrance
 * 
 * public ArenaEntranceViewer(EventAdder eventAdder, List<ZEntity> entities)
 * Creates the GUI elements for the ArenaEntranceViewer.
 */

public class ArenaEntranceViewer extends ZContainer
{

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
