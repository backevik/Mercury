package gui;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import player.QuestLog;
import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import zlibrary.ZImage;
import zlibrary.ZText;
//import player.QuestStatus;
import core.EventAdder;
import database.GameDataManager;

/**
 * @author	Anton Andrén & Mattias Benngård
 * @version	0.8
 * @since	2015-02-17
 * 
 * Class for displaying the quest log to the player.
 * 
 * public questLogViewer (EventAdder, Image, int, int, List<MouseObject>, QuestLog)
 * Creates the GUI elements for the questLogViewer depending on how many quest the player currently has.
 * 
 * To-Do:
 *	- Add Support for Displaying only Active or Completed
 *  - Different Icon based on Quest State
 *  - Scroll through quests, if more than room for.
 */

public class QuestLogViewer extends ZContainer
{
	private final static int PADDING = 10;
	private final static int QUEST_ICON_SIZE = GameDataManager.getInstance().getImage("bgQuestViewerIcon.jpg").getHeight(null);
	private final static int TITLE_HEIGHT = 30;
	private final static int QUEST_DISTANCE = PADDING*2 + QUEST_ICON_SIZE + GameDataManager.getInstance().getImage("bgQuestViewerSeparator.jpg").getHeight(null);
	
	private final List<ZEntity> entities = new ArrayList<>();
	
	@SuppressWarnings("unused")
	private QuestLog questLog;
	
	public QuestLogViewer (Image image, int x, int y, EventAdder eventAdder, List<ZEntity> entities, QuestLog questLog) {
		super(image, x, y, eventAdder, entities);
		
		for (ZEntity e : entities) {
			this.entities.add(e);
		}
		
		entities.clear();

		components.add(new ZText ("Quest Log", x+PADDING, y+PADDING, 20));
		
		ZButton backToGame = new ZButton (GameDataManager.getInstance().getImage("bgQuestViewerQuit.jpg"), x+576,y+7, eventAdder, "questLogToggle");
    	components.add(backToGame);
    	entities.add(backToGame);
		
    	for (int i = 0; i != questLog.getQuests().size(); ++i) {
    		components.add(new ZImage (GameDataManager.getInstance().getImage("bgQuestViewerIcon.jpg"), x+PADDING, y+PADDING + TITLE_HEIGHT + i*QUEST_DISTANCE));
    		components.add(new ZText (questLog.getQuests().get(i).getName(), x+PADDING + QUEST_ICON_SIZE+PADDING, y+PADDING + TITLE_HEIGHT + i*QUEST_DISTANCE, 16));
    		components.add(new ZText (questLog.getQuests().get(i).getDescription(), x+PADDING + QUEST_ICON_SIZE+PADDING, y+PADDING + TITLE_HEIGHT+20 + i*QUEST_DISTANCE, 12));
    		if (i != questLog.getQuests().size()) {
    			components.add(new ZImage (GameDataManager.getInstance().getImage("bgQuestViewerSeparator.jpg"), x, y+PADDING + TITLE_HEIGHT + PADDING+QUEST_ICON_SIZE +i*QUEST_DISTANCE));
    		}
    	}
    	
    	if (questLog.getQuests().size() == 0) {
    		components.add(new ZText ("You have no active quests.", x+PADDING, y+PADDING + TITLE_HEIGHT, 16));
    	}
	}
	
	public List<ZEntity> getEntities () {
		return entities;
	}
}
