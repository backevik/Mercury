package core;

import java.util.LinkedList;

/**
 * EventAdder lets objects add events without being able to modify the event list.
 * 
 * @author	Anton Andrén & Mattias Benngård
 * @version	1.0
 * @since	2015-02-16
 */
public class EventAdder
{
	private LinkedList<String> events;
	
	/**
	 * Constructor for EventAdder
	 * @param events reference to eventlist in eventqueue
	 */
	public EventAdder (LinkedList<String> events) {
		this.events = events;
	}
	
	/**
	 * Adds s last to the eventlist
	 * @param s name of event to be added last in event list 
	 */
	public void add (String s) {
		events.addLast(s);
	}
}
