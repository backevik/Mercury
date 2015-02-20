package gui;

import java.awt.Image;
import java.util.List;

import player.QuestLog;
import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZImage;
import zlibrary.ZText;
//import player.QuestStatus;
import core.EventAdder;
import core.Entity;
import database.GameDataManager;

/**
 * @author	Anton Andrén & Mattias Benngård
 * @version	0.4
 * @since	2015-02-17
 * 
 * Class for displaying the quest log to the player.
 * 
 * public questLogViewer (EventAdder, Image, int, int, List<MouseObject>, QuestLog)
 * Creates the GUI elements for the questLogViewer depending on how many quest the player currently has.
 */

public class QuestLogViewer extends ZContainer
{
	private final static int PADDING = 10;
	private final static int QUEST_ICON_SIZE = GameDataManager.getInstance().getImage("bgQuestViewerIcon.jpg").getHeight(null);
	private final static int TITLE_HEIGHT = 30;
	private final static int QUEST_DISTANCE = PADDING*2 + QUEST_ICON_SIZE + GameDataManager.getInstance().getImage("bgQuestViewerSeparator.jpg").getHeight(null);
	
	@SuppressWarnings("unused")
	private QuestLog questLog;
	
	public QuestLogViewer (Image image, int x, int y, EventAdder eventAdder, List<Entity> mouseObjects, QuestLog questLog) {
		super(image, x, y, eventAdder, mouseObjects);

		ZText title = new ZText ("Quest Log", x+PADDING, y+PADDING, 20);
		components.add(title);
		
		ZButton backToGame = new ZButton (GameDataManager.getInstance().getImage("bgQuestViewerQuit.jpg"), x+576,y+7, eventAdder, "questLogViewerToggle");
    	components.add(backToGame);
    	mouseObjects.add(backToGame);
		
    	for (int i = 0; i != questLog.getQuests().size(); ++i) {
    		ZImage questIcon = new ZImage (GameDataManager.getInstance().getImage("bgQuestViewerIcon.jpg"), x+PADDING, y+PADDING + TITLE_HEIGHT + i*QUEST_DISTANCE);
    		components.add(questIcon);
    		ZText questName = new ZText (questLog.getQuests().get(i).getName(), x+PADDING + QUEST_ICON_SIZE+PADDING, y+PADDING + TITLE_HEIGHT + i*QUEST_DISTANCE, 16);
    		components.add(questName);
    		ZText questDescription = new ZText (questLog.getQuests().get(i).getDescription(), x+PADDING + QUEST_ICON_SIZE+PADDING, y+PADDING + TITLE_HEIGHT+20 + i*QUEST_DISTANCE, 12);
    		components.add(questDescription);
    		if (i != questLog.getQuests().size()) {
    			ZImage questBorder = new ZImage (GameDataManager.getInstance().getImage("bgQuestViewerSeparator.jpg"), x, y+PADDING + TITLE_HEIGHT + PADDING+QUEST_ICON_SIZE +i*QUEST_DISTANCE);
    			components.add(questBorder);
    		}
    	}
	}
}
