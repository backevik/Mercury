package gui;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import player.QuestEntry;
import player.QuestLog;
import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import zlibrary.ZImage;
import zlibrary.ZText;
import core.EventAdder;
import database.ImageDatabase;

/**
 * Sets up and holds all gui elements to the quest log frame
 * 
 * @author	Mattias Benng�rd	
 * @version	1.0					
 * @since	2015-02-17
 */

public class QuestLogViewer extends ZContainer
{
	private final static int PADDING = 10;
	private final static int QUEST_ICON_SIZE = ImageDatabase.getInstance().getImage("bgQuestViewerIcon.jpg").getHeight(null);
	private final static int TITLE_HEIGHT = 60;
	private final static int QUEST_DISTANCE = PADDING*2 + QUEST_ICON_SIZE + ImageDatabase.getInstance().getImage("bgQuestViewerSeparator.jpg").getHeight(null);
	
	private final int QUESTS_TO_DISPLAY	= 4;
		
	private final List<ZEntity> entities = new ArrayList<>();
	private int questOffset		= 0;
	
	/**
	 * Constructor for QuestLogViewer
	 * @param image background image of the scene
	 * @param x  where the scene begins in x
	 * @param y  where the scene begins in y
	 * @param eventAdder  reference to the event queue
	 * @param entities  copy of entity list, to add components
	 * @param questLog  reference to the players quest log
	 */
	public QuestLogViewer (Image image, int x, int y, EventAdder eventAdder, List<ZEntity> entities, QuestLog questLog) {
		super(image, x, y, eventAdder, entities);
		
		for (ZEntity e : entities) {
			this.entities.add(e);
		}
		
		entities.clear();

		ZText title = new ZText ("Quest Log", x+PADDING, y+PADDING, 20);
		components.add(title);
		
		ZButton backToGame = new ZButton (ImageDatabase.getInstance().getImage("bgQuestViewerQuit.jpg"), x+576,y+7, eventAdder, "questLogToggle");
    	components.add(backToGame);
    	entities.add(backToGame);
		
    	// to do add decOffset() here as ZComponent
    	
    	List<QuestEntry> quests = questLog.getQuestEntries();    	
    	for (int i = questOffset; i < QUESTS_TO_DISPLAY; i++) {
    		
    		if (i == quests.size() || quests.get(i) == null || quests.get(i).getQuest() == null) {
    			break;
    		}
    		
    		components.add(new ZImage (ImageDatabase.getInstance().getImage("bgQuestViewerIcon.jpg"), x+PADDING, y+PADDING + TITLE_HEIGHT + (i-questOffset)*QUEST_DISTANCE));
    		components.add(new ZText (quests.get(i).getQuest().getName(), x+PADDING + QUEST_ICON_SIZE+PADDING, y+PADDING + TITLE_HEIGHT + (i-questOffset)*QUEST_DISTANCE, 16));
    		components.add(new ZText (quests.get(i).getQuest().getDescription(), x+PADDING + QUEST_ICON_SIZE+PADDING, y+PADDING + TITLE_HEIGHT+20 + (i-questOffset)*QUEST_DISTANCE, 12));
    		
    		// jump out of displaying quests
    		if (i == quests.size()-1) {
    			break;
    		}
    		
    		// seperator for quests
			components.add(new ZImage (ImageDatabase.getInstance().getImage("bgQuestViewerSeparator.jpg"), x, y+PADDING + TITLE_HEIGHT + PADDING+QUEST_ICON_SIZE +(i-questOffset)*QUEST_DISTANCE));
    	}
    	
    	// to do add incOffset() here as ZComponent
	}
	
	/* Not used
	public void incOffset () {
		questOffset++;
	}
	
	public void decOffset () {
		questOffset--;
	}*/
	
	/**
	 * Public getter for entities
	 * @return old entity list
	 */
	public List<ZEntity> getEntities () {
		return entities;
	}
}
