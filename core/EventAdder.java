package core;

import java.util.LinkedList;

public class EventAdder {

	private LinkedList<String> events;
	
	public EventAdder (LinkedList<String> events) {
		this.events = events;
	}
	
	public void add (String s) {
		events.addLast(s);
	}
}
