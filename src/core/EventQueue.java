package core;

import java.util.LinkedList;

/**
 * Keeps track of the Events list and lets user retrieve the first item. Wraps around EventAdder
 * 
 * @author	Anton Andrén & Mattias Benngård
 * @version	1.0
 * @since	2015-02-16
 */

public class EventQueue
{
	private final LinkedList<String> events = new LinkedList<>();
	private final EventAdder eventAdder = new EventAdder (events);	
	
	public EventQueue () {}
	
	/**
	 * Returns the first event in the list and removes it.
	 * @return String name of first event
	 */
	public String get () {
		return events.pollFirst ();
	}
	
	/**
	 * Returns the EventAdder so that classes that need to both add and retrieve data for the list can do so.
	 * @return EventAdder reference to eventadder 
	 */
	public EventAdder getEventAdder () {
		return eventAdder;
	}
}
