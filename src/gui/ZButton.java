package gui;

import java.awt.Image;

import core.Drawable;
import core.EventAdder;
import core.Entity;

/**
 * @author	Anton Andrén & Mattias Benngård
 * @version	0.4
 * @since	2015-02-16
 * 
 * Class for creating buttons that are viewable for the player.
 * Buttons can implement events on mouse click.
 * ZButtons are ZImages.
 * 
 * Can be constructed with an Image or using a default appearance.
 */
public class ZButton extends ZImage implements Entity, Drawable
{
	private EventAdder eventAdder;
	private String eventOnClick;
	
	public ZButton (EventAdder eventAdder, String eventOnClick, Image image, int x, int y) {
		super(image, x, y);
		this.eventAdder = eventAdder;
		this.eventOnClick = eventOnClick;
	}
	
	public ZButton (EventAdder eventAdder, String eventOnClick, String string, int x, int y, int w, int h) {
		super(string, x, y, w, h);
		this.eventAdder = eventAdder;
		this.eventOnClick = eventOnClick;
	}
	
	/**
	 * Adds event if the object was clicked and has an active listener.
	 */
	@Override
	public void onClick(int mouseX, int mouseY) {
		if (isMouseInside (mouseX, mouseY)) {
			eventAdder.add(eventOnClick);
		}
	}
	
	/**
	 * Checks if the button is in the area where the mouse click was detected using parameters x & y.
	 * If it was returns true otherwise false.
	 * 
	 * @param mouseX
	 * @param mouseY
	 * @return boolean
	 */
	private boolean isMouseInside (int mouseX, int mouseY) {
		return (mouseX >= x && mouseY >= y &&
				mouseX <= x+image.getWidth(null) &&
				mouseY <= y+image.getHeight(null));
	}
}
