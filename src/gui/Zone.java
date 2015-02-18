package gui;

import core.EventAdder;
import core.MouseObject;

import java.awt.Image;
import java.util.HashMap;
import java.util.List;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3
 * @since		2015-02-17
 */

public class Zone extends ZButton
{
	
	private String zoneName;
	
	public Zone(EventAdder eventAdder, String eventOnClick, Image image, int x, int y, String name){
		super(eventAdder, eventOnClick, image, x, y);
		zoneName = name;
	}

}
