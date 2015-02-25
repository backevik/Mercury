package gui;

import core.EventAdder;
import core.GlobalStateManager;

import java.awt.Image;

import zlibrary.ZButton;

/**
 * ZoneButton is a subclass of ZButton
 * It also holds name and type of the zones.
 * Elgibile Types are; "Town", "Combat"
 *  
 * @author	Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @author	Mattias Benngård 	<mbengan@gmail.com>
 * @version	1.0					<2015-02-25>
 * @since	2015-02-18
 */

public class ZoneButton extends ZButton
{
	private String zoneName;
	private String zoneType;
	
	public ZoneButton (Image image, int x, int y, EventAdder eventAdder, String eventOnClick, String zoneName, String zoneType) {
		super(image, x, y, eventAdder, eventOnClick);
		this.zoneName = zoneName;
		this.zoneType = zoneType;
		GlobalStateManager.getInstance().updateWorldState("Location", zoneName);
	}
	
	/**
	 * public getter for zoneName
	 * @return name of the zone
	 */
	public String getName () {
		return zoneName;
	}
	
	/**
	 * public getter for zoneType
	 * @return type of the zone
	 */
	public String getType () {
		return zoneType;
	}
}
