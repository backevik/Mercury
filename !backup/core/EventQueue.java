package core;

import java.util.LinkedList;

public class EventQueue
{
	private final LinkedList<String> events = new LinkedList<>();
	private final EventAdder eventAdder = new EventAdder (events);	
	
	public EventQueue () {}
	
	public String get () {
		return events.pollFirst ();
	}
	
	public EventAdder getEventAdder () {
		return eventAdder;
	}
}
