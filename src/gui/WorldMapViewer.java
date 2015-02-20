package gui;

import java.util.List;

import worldmap.Zone;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import core.EventAdder;
import core.GlobalStateManager;
import database.GameDataManager;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3.1
 * @since		2015-02-18
 */

public class WorldMapViewer extends ZContainer {

	private Zone btnTown;
	private Zone btnForest;
	private Zone btnGrass;
	
	public WorldMapViewer(EventAdder eventAdder,List<ZEntity> entities) {
		super(GameDataManager.getInstance().getImage("bgWorldMap.jpg"), 0, 0, eventAdder, entities);
		
		GlobalStateManager.getInstance().updateCurrentState("WorldMap");
		
		btnTown = new Zone(eventAdder, "sceneTown", GameDataManager.getInstance().getImage("btnTown.jpg"), 16, 352,"a1");
		components.add(btnTown);
		entities.add(btnTown);
				
		btnForest = new Zone(eventAdder, "sceneCombat", GameDataManager.getInstance().getImage("btnZone.jpg"), 104, 128,"a2");
		components.add(btnForest);
		entities.add(btnForest);
		
		btnGrass = new Zone(eventAdder, "clickSelectZone", GameDataManager.getInstance().getImage("btnZone.jpg"), 184, 104,"a3");
		components.add(btnGrass);
		entities.add(btnGrass);
	}
}
