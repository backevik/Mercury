package zlibrary;

import core.EventAdder;

/**
 * Abstract class for clickable objects
 * 
 * @author	Mattias Benngard	<mbengan@gmail.com>
 * @version	1.0					<2015-03-07>
 * @since	2015-02-16
 */

public abstract class ZEntity extends ZComponent implements ZDrawable
{
	private EventAdder eventAdder;
	private String eventOnClick;
	@SuppressWarnings("unused")
	private boolean hover;
	
	/**
	 * Protected setter for the reference to the event queue
	 * @param eventAdder - a reference to the event queue
	 */
	protected void setEventAdder (EventAdder eventAdder) {
		this.eventAdder = eventAdder;
	}
	
	/**
	 * Protected getter for what event is triggered on click
	 * @return - what event is stored
	 */
	protected String getEventOnClick () {
		return eventOnClick;
	}
	
	/**
	 * Protected setter for what event is triggered on click
	 * @param eventOnClick - which event to replace the current event with
	 */
	protected void setEventOnClick (String eventOnClick) {
		this.eventOnClick = eventOnClick;
	}
	
	/**
	 * Adds an event to the event queue
	 * @param s - what event to add to the event queue
	 */
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
	 * @param mouseX - current coordinate of mouse in x
	 * @param mouseY - current coordinate of mouse in y
	 * @return boolean - for if the mouse was inside
	 */
	protected boolean isMouseInside (int mouseX, int mouseY) {
		return (mouseX >= getX() && mouseY >= getY() &&
				mouseX <= getX() + getImage().getWidth(null) &&
				mouseY <= getY() + getImage().getHeight(null));
	}
}
