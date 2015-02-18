package gui;

import java.awt.Graphics;
import java.util.List;

import combat.Enemy;

import core.Drawable;
import core.EventAdder;
import core.MouseObject;
import database.GameDataManager;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3.1
 * @since		2015-02-18
 */

public class WorldMap extends ZContainer implements Drawable
{
	private Zone btnTown;
	private Zone btnForest;
	private Zone btnGrass;

	public WorldMap( EventAdder eventAdder, List<MouseObject> mouseObjects) {
		
		super(eventAdder,GameDataManager.getInstance().getImage("WorldBackground.jpg"), 0, 0, mouseObjects);
		
		//TownButton
		btnTown = new Zone(eventAdder, "clickSelectZone", GameDataManager.getInstance().getImage("Town.jpg"), 16, 352,"a1");
		components.add(btnTown);
		mouseObjects.add(btnTown);
				
		btnForest = new Zone(eventAdder, "clickSelectZone", GameDataManager.getInstance().getImage("Fight.jpg"), 104, 128,"a2");
		components.add(btnForest);
		mouseObjects.add(btnForest);
		
		btnGrass = new Zone(eventAdder, "clickSelectZone", GameDataManager.getInstance().getImage("Fight.jpg"), 184, 104,"a3");
		components.add(btnGrass);
		mouseObjects.add(btnGrass);
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
	}
	
	@Override
	public void remove() {
		btnTown = null;
		mouseObjects.remove(btnTown);
		btnForest = null;
		mouseObjects.remove(btnForest);
		btnGrass = null;
		mouseObjects.remove(btnGrass);
		
	}
	
}
