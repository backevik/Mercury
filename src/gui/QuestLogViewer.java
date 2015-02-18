package gui;

import java.awt.Image;
import java.util.List;
import player.QuestLog;
import core.EventAdder;
import core.MouseObject;

/**
 * @author	Anton Andrén & Mattias Benngård
 * @version	0.4
 * @since	2015-02-17
 * 
 * Class for displaying the questlog to the player.
 * 
 * public questLogViewer (EventAdder, Image, int, int, List<MouseObject>, QuestLogg)
 * Creates the GUI elements for the questLogViewer depending on how many quest the player currently has.
 */
public class QuestLogViewer extends ZContainer
{
	@SuppressWarnings("unused")
	private QuestLog questLog;
	
	public QuestLogViewer (EventAdder eventAdder, Image image, int x, int y, List<MouseObject> mouseObjects, QuestLog questLog) {
		super(eventAdder, image, x, y, mouseObjects);
		

    	for (int i = 0; i != questLog.getQuests().size(); ++i) {
    		ZButton zb = new ZButton (null, i + "q", "?", x+10, y+10+i*70, 64, 64);    		
    		components.add(zb);
    		mouseObjects.add(zb);
    	}
    	
    	ZButton backToGame = new ZButton (eventAdder, "toggleGui", "Back to Game", 500,500, 200, 50);
    	components.add(backToGame);
    	mouseObjects.add(backToGame);
	}
}
