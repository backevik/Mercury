package gui;

import java.util.List;

import zlibrary.ZContainer;

import core.Drawable;
import core.EventAdder;
import core.GlobalStateManager;
import core.Entity;
import database.GameDataManager;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3.1
 * @since		2015-02-18
 */

public class WorldMapViewer extends ZContainer implements Drawable{

	private Zone btnTown;
	private Zone btnForest;
	private Zone btnGrass;
	
	public WorldMapViewer(EventAdder eventAdder,List<Entity> mouseObjects) {
		super(eventAdder,GameDataManager.getInstance().getImage("bgWorldMap.jpg"), 0, 0, mouseObjects);
		
		GlobalStateManager.getInstance().updateCurrentState("WorldMap");
		
		btnTown = new Zone(eventAdder, "sceneTown", GameDataManager.getInstance().getImage("btnTown.jpg"), 16, 352,"a1");
		components.add(btnTown);
		mouseObjects.add(btnTown);
				
		btnForest = new Zone(eventAdder, "sceneCombat", GameDataManager.getInstance().getImage("btnZone.jpg"), 104, 128,"a2");
		components.add(btnForest);
		mouseObjects.add(btnForest);
		
		btnGrass = new Zone(eventAdder, "clickSelectZone", GameDataManager.getInstance().getImage("btnZone.jpg"), 184, 104,"a3");
		components.add(btnGrass);
		mouseObjects.add(btnGrass);
	}
}
