package gui;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import zlibrary.ZAnimation;
import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import core.EventAdder;
import core.GlobalStateManager;
import database.GameDataManager;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3.2
 * @since		2015-02-21
 */

public class WorldMapViewer extends ZContainer {

	private ZButton btnTown;
	private ZButton btnForest;
	private ZButton btnGrass;
	
	public WorldMapViewer(EventAdder eventAdder,List<ZEntity> entities) {
		super(GameDataManager.getInstance().getImage("bgWorldMap.jpg"), 0, 0, eventAdder, entities);
		
		GlobalStateManager.getInstance().updateCurrentState("WorldMap");
		
		//Create all buttons
		btnTown = new ZButton(GameDataManager.getInstance().getImage("btnTown.jpg"), 16, 352, eventAdder, "selectArea,townTown");
		components.add(btnTown);
		entities.add(btnTown);
		
		btnForest = new ZButton(GameDataManager.getInstance().getImage("btnZone.jpg"), 104, 128, eventAdder, "selectArea,combatForest");
		components.add(btnForest);
		entities.add(btnForest);
		
		btnGrass = new ZButton(GameDataManager.getInstance().getImage("btnZone.jpg"), 184, 104, eventAdder, "selectArea,combatGrass");
		components.add(btnGrass);
		entities.add(btnGrass);

		
		
		
		List<Image> kek = new ArrayList<>();
		kek.add(GameDataManager.getInstance().getImage("btnZone.jpg"));
		kek.add(GameDataManager.getInstance().getImage("btnZoneOn.jpg"));
		ZAnimation bur = new ZAnimation(kek, 300, 300, 0);
		components.add(bur);
	}
}
