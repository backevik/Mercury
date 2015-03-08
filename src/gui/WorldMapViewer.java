package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import worldmap.Zone;
import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import core.EventAdder;
import core.GlobalStateManager;
import core.RealTime;
import database.ImageDatabase;
import database.ZoneDatabase;

/**
 * Sets up and holds all gui elements to the world map
 * 
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3.2
 * @since		2015-02-21
 */

public class WorldMapViewer extends ZContainer implements RealTime
{	
	private List<ZoneButton> zones = new ArrayList<>();
	
	/**
	 * Constructor for WorldMapViewer
	 * @param eventAdder reference to the event queue 
	 * @param entities copy of entity list, to add components
	 */
	public WorldMapViewer(EventAdder eventAdder, List<ZEntity> entities) {
		super(ImageDatabase.getInstance().getImage("bgWorldMap.jpg"), 0, 0, eventAdder, entities);

		// Create GUI elements for character menus
		ZButton tmpButton = new ZButton (ImageDatabase.getInstance().getImage("btnCharacterStatistics.jpg"), 800-32, 0, eventAdder, "characterStatisticsToggle");
		components.add(tmpButton);
		entities.add(tmpButton);
		tmpButton = new ZButton (ImageDatabase.getInstance().getImage("btnQuestLog.jpg"), 800-64, 0, eventAdder, "questLogToggle");
		components.add(tmpButton);
		entities.add(tmpButton);
		
		// Create all buttons 
		for (Entry<String, Zone> z : ZoneDatabase.getInstance().getZones().entrySet()) {
			ZoneButton tmpZoneButton = new ZoneButton (
					ImageDatabase.getInstance().getImage("btn"+z.getValue().getType()+"Zone.png"),
					z.getValue().getX(),
					z.getValue().getY(),
					eventAdder,
					"selectArea,"+z.getValue().getName(),
					z.getValue().getName());
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
			boolean onZone = GlobalStateManager.getInstance().getWorldState("LOCATION").equals(z.getName());
			boolean clearZone = GlobalStateManager.getInstance().getWorldState(z.getName()).equals("CLEAR");
			
			if (onZone && clearZone) {
				z.setImage(ImageDatabase.getInstance().getImage("btn"+ZoneDatabase.getInstance().getZone(z.getName()).getType()+"ClearedOn.png"));
			} else if (onZone) {
				z.setImage(ImageDatabase.getInstance().getImage("btn"+ZoneDatabase.getInstance().getZone(z.getName()).getType()+"On.png"));
			} else if (clearZone) {
				z.setImage(ImageDatabase.getInstance().getImage("btn"+ZoneDatabase.getInstance().getZone(z.getName()).getType()+"Cleared.png"));
			} else {
				z.setImage(ImageDatabase.getInstance().getImage("btn"+ZoneDatabase.getInstance().getZone(z.getName()).getType()+".png"));
			}
		}		
	}
}