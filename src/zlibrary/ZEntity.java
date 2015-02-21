package zlibrary;

import core.EventAdder;

/**
 * @author	Anton Andr�n & Mattias Benng�rd
 * @version	0.4
 * @since	2015-02-16
 * 
 * Class for creating buttons that are viewable for the player.
 * Buttons can implement events on mouse click.
 * ZButtons are ZImages.
 * 
 * Can be constructed with an Image or using a default appearance.
 */

public abstract class ZEntity extends ZComponent implements ZDrawable
{
	private EventAdder eventAdder;
	private String eventOnClick;
	@SuppressWarnings("unused")
	private boolean hover;
	
	protected void setEventAdder (EventAdder eventAdder) {
		this.eventAdder = eventAdder;
	}
	
	protected String getEventOnClick () {
		return eventOnClick;
	}
	
	protected void setEventOnClick (String eventOnClick) {
		this.eventOnClick = eventOnClick;
	}
	
	protected void addEvent (String s) {
		eventAdder.add(s);
	}
	
	/**
	 * Adds event if the object was clicked and has an active listener.
	 */
	public void onClick (int mouseX, int mouseY) {
		if (isMouseInside (mouseX, mouseY)) {
			eventAdder.add(eventOnClick);
		}
	}
	
	/**
	 * Adds event if the object was clicked and has an active listener.
	 */
	public void onHover (int mouseX, int mouseY) {
		hover = isMouseInside (mouseX, mouseY);		
	}
	
	/**
	 * Checks if the button is in the area where the mouse click was detected using parameters x & y.
	 * If it was returns true otherwise false.
	 * 
	 * @param mouseX
	 * @param mouseY
	 * @return boolean
	 */
	protected boolean isMouseInside (int mouseX, int mouseY) {
		return (mouseX >= getX() && mouseY >= getY() &&
				mouseX <= getX() + getImage().getWidth(null) &&
				mouseY <= getY() + getImage().getHeight(null));
	}
}
