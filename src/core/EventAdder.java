package core;

import java.util.LinkedList;

/**
 * EventAdder lets objects add events without being able to modify the event list.
 * 
 * @author	Anton Andrén & Mattias Benngård
 * @version	0.4
 * @since	2015-02-16
 */
public class EventAdder
{
	private LinkedList<String> events;
	
	/**
	 * Constructor for EventAdder
	 * @param events
	 */
	public EventAdder (LinkedList<String> events) {
		this.events = events;
	}
	
	/**
	 * Adds s last to the eventlist
	 * @param s
	 */
	public void add (String s) {
		events.addLast(s);
	}
}
