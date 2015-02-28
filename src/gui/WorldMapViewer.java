package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import worldmap.WorldMap;
import worldmap.Zone;
import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import core.EventAdder;
import core.GlobalStateManager;
import core.RealTime;
import database.ImageDatabase;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3.2
 * @since		2015-02-21
 */

public class WorldMapViewer extends ZContainer implements RealTime
{	
	private List<ZoneButton> zones = new ArrayList<>();
	
	public WorldMapViewer(EventAdder eventAdder, List<ZEntity> entities, WorldMap worldMap) {
		super(ImageDatabase.getInstance().getImage("bgWorldMap.jpg"), 0, 0, eventAdder, entities);

		// Create GUI elements for character menus
		ZButton tmpButton = new ZButton (ImageDatabase.getInstance().getImage("btnCharacterStatistics.jpg"), 800-35, 25, eventAdder, "characterStatisticsToggle");
		components.add(tmpButton);
		entities.add(tmpButton);
		tmpButton = new ZButton (ImageDatabase.getInstance().getImage("btnQuestLog.jpg"), 800-67, 25, eventAdder, "questLogToggle");
		components.add(tmpButton);
		entities.add(tmpButton);
		
		// Create all buttons
		for (Entry<String, Zone> z : worldMap.getZones().entrySet()) {
			ZoneButton tmpZoneButton = new ZoneButton (
					ImageDatabase.getInstance().getImage("btn"+z.getValue().getType()+".png"),
					z.getValue().getX(),
					z.getValue().getY(),
					eventAdder,
					"selectArea,"+z.getValue().getName(),
					z.getValue().getName(),
					z.getValue().getType());
			add(tmpZoneButton);
		}
	}
	
	private void add (ZoneButton z) {
		zones.add(z);
		components.add(z);
		entities.add(z);
	}

	@Override
	public void update () {
		for (ZoneButton z : zones) {
			if (GlobalStateManager.getInstance().getWorldState("Location").equals(z.getName())) {
				z.setImage(ImageDatabase.getInstance().getImage("btn"+z.getType()+"On.png"));
			} else {
				z.setImage(ImageDatabase.getInstance().getImage("btn"+z.getType()+".png"));
			}
		}		
	}
}