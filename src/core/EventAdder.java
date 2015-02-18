package core;

import java.util.LinkedList;

/**
 * @author	Anton Andrén & Mattias Benngård
 * @version	0.4
 * @since	2015-02-16
 * 
 * EventAdder lets objects add events without being able to modify the event list.
 * 
 * public void add (String)
 * adds the string last in the eventlist
 *
 */
public class EventAdder
{
	private LinkedList<String> events;
	
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
