package gui;
import core.EventAdder;
import core.GlobalStateManager;

import java.awt.Image;

import combat.Enemy;

/**
* @author	Daniel Edsinger <danieledsinger@hotmail.com>
* @version	0.3.1
* @since	2015-02-18
*/
public class Zone extends ZButton
{
	private String zoneName;
	private Enemy enemy;
	
	public Zone(EventAdder eventAdder, String eventOnClick, Image image, int x, int y, String name){
		super(eventAdder, eventOnClick, image, x, y);
		this.zoneName = name;
		this.enemy = enemy;
		GlobalStateManager.getInstance().updateWorldState(zoneName, "");
	}
	
	public String getname()
	{
		return zoneName;
	}

}
