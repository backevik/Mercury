package worldmap;

import core.EventAdder;
import core.GlobalStateManager;

import java.awt.Image;

import zlibrary.ZButton;

/**
* @author	Daniel Edsinger <danieledsinger@hotmail.com>
* @version	0.3.1
* @since	2015-02-18
*/
public class Zone extends ZButton
{
	private String zoneName;
	
	public Zone(EventAdder eventAdder, String eventOnClick, Image image, int x, int y, String name){
		super(image, x, y, eventAdder, eventOnClick);
		this.zoneName = name;
		GlobalStateManager.getInstance().updateWorldState("Location", zoneName);
	}
	
	public String getName(){
		return zoneName;
	}
}
