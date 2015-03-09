package core;

import java.util.HashSet;
import java.util.Set;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

/**
 * Debug tool, keybinds can trigger events or do funny things
 * 
 * @author	Mattias Benngård 	
 * @version	1.0					
 * @since	2015-02-18
 */

public class KeyBindManager implements KeyEventDispatcher
{
	private Set<String> keyPresses = new HashSet<>();
	private EventAdder eventAdder;
	
	/**
	 * Constructor for KeyBindManager
	 * @param eventAdder - reference to the event queue
	 */
	public KeyBindManager (EventAdder eventAdder) {
		this.eventAdder = eventAdder;
	}
	
    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
    	String pressed = keyToLowerCaseIfUpperCase(e.getKeyChar())+"";
    	
        if (e.getID() == KeyEvent.KEY_PRESSED) {
        	if (!keyPresses.contains(pressed)) {
	        	keyPresses.add(pressed);
	        	
	        	switch (keyToLowerCaseIfUpperCase(e.getKeyChar())) {
	        		case 'c':
	        			if (GlobalStateManager.getInstance().getWorldState("CHARACTER_IS_ALIVE").equals("TRUE")) {
	        				eventAdder.add("characterStatisticsToggle");
	        			}
	        			break;
	        		case 'q':
	        			if (GlobalStateManager.getInstance().getWorldState("CHARACTER_IS_ALIVE").equals("TRUE")) {
	        				eventAdder.add("questLogToggle");
	        			}
	        			break;
	        		case KeyEvent.VK_ESCAPE: eventAdder.add("exitGame");
	        		default: break;
	        	}
        	}
        } else if (e.getID() == KeyEvent.KEY_RELEASED){
	       	keyPresses.remove(pressed);
        }
        return false;
    }
    
    /**
     * Private helper to convert upper case to lower case
     * @param c - the character to be converted
     * @return the character lowered
     */
    private char keyToLowerCaseIfUpperCase (char c) {
    	if (c >= 'A' && c <= 'Z') {
    		c = Character.toLowerCase(c);
    	}
    		
    	return c;
    }
}