package core;

import java.util.LinkedList;
/**
 * @author	Anton Andr�n & Mattias Benng�rd
 * @version	0.4
 * @since	2015-02-16
 * 
 * Keeps track of the Events list and lets user retrieve the first item.
 * 
 * Instantiates EventAdder
 */
public class EventQueue
{
	private final LinkedList<String> events = new LinkedList<>();
	private final EventAdder eventAdder = new EventAdder (events);	
	
	public EventQueue () {}
	
	/**
	 * Returns the first event in the list and removes it.
	 * @return String
	 */
	public String get () {
		return events.pollFirst ();
	}
	
	/**
	 * Returns the EventAdder so that classes that need to both add and retrieve data for the list can do so.
	 * @return EventAdder
	 */
	public EventAdder getEventAdder () {
		return eventAdder;
	}
}
