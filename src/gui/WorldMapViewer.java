package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import worldmap.WorldMap;
import worldmap.Zone;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import core.EventAdder;
import core.GlobalStateManager;
import core.RealTime;
import database.GameDataManager;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3.2
 * @since		2015-02-21
 */

public class WorldMapViewer extends ZContainer implements RealTime
{	
	private List<ZoneButton> zones = new ArrayList<>();
	
	public WorldMapViewer(EventAdder eventAdder, List<ZEntity> entities, WorldMap worldMap) {
		super(GameDataManager.getInstance().getImage("bgWorldMap.jpg"), 0, 0, eventAdder, entities);

		// Create all buttons
		for (Entry<String, Zone> z : worldMap.getZones().entrySet()) {
			ZoneButton tmpZoneButton = new ZoneButton (
					GameDataManager.getInstance().getImage("btn"+z.getValue().getType()+".jpg"),
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
				z.setImage(GameDataManager.getInstance().getImage("btn"+z.getType()+"On.jpg"));
			} else {
				z.setImage(GameDataManager.getInstance().getImage("btn"+z.getType()+".jpg"));
			}
		}		
	}
}