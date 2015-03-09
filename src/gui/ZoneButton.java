package gui;

import core.EventAdder;

import java.awt.Image;

import zlibrary.ZButton;

/**
 * Wrapper for ZButton, contains a name for the Zone
 * 
 * @author	Daniel Edsinger 
 * @version	1.0				
 * @since	2015-02-18
 */

public class ZoneButton extends ZButton
{
	private String zoneName;
	
	/**
	 * Constructor for CharacterCreationViewer
	 * @param image background image of the scene
	 * @param x  where the scene begins in x
	 * @param y  where the scene begins in y
	 * @param eventAdder  reference to the event queue
	 * @param entities copy of entity list, to add components
	 * @param zoneName  name of the zone
	 */
	public ZoneButton (Image image, int x, int y, EventAdder eventAdder, String eventOnClick, String zoneName) {
		super(image, x, y, eventAdder, eventOnClick);
		this.zoneName = zoneName;
	}
	
	/**
	 * Public getter for zoneName
	 * @return name of the zone
	 */
	public String getName () {
		return zoneName;
	}
}
