package zlibrary;

import core.EventAdder;

/**
 * @author	Mattias Benngård
 * @version	1.0
 * @since	2015-02-16
 * 
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
