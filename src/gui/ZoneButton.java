package gui;

import core.EventAdder;

import java.awt.Image;

import zlibrary.ZButton;

/**
* @author	Daniel Edsinger <danieledsinger@hotmail.com>
* @author	Mattias Benngård <mbengan@gmail.com>
* @version	0.8
* @since	2015-02-18
*/

public class ZoneButton extends ZButton
{
	private String zoneName;
	
	public ZoneButton (Image image, int x, int y, EventAdder eventAdder, String eventOnClick, String zoneName) {
		super(image, x, y, eventAdder, eventOnClick);
		this.zoneName = zoneName;
	}
	
	public String getName () {
		return zoneName;
	}
}
