package gui;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import player.QuestLog;
import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import zlibrary.ZText;
import core.EventAdder;
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

public class CharacterStatisticsViewer extends ZContainer
{
	private final static int PADDING = 10;
	private final static int TITLE_HEIGHT = 30;
	
	private final List<ZEntity> entities = new ArrayList<>();
	
	public CharacterStatisticsViewer (Image image, int x, int y, EventAdder eventAdder, List<ZEntity> entities) {
		super(image, x, y, eventAdder, entities);
		
		for (ZEntity e : entities) {
			this.entities.add(e);
		}
		
		entities.clear();

		ZText title = new ZText ("Character Statistics", x+PADDING, y+PADDING, 20);
		components.add(title);
		
		ZButton backToGame = new ZButton (GameDataManager.getInstance().getImage("bgQuestViewerQuit.jpg"), x+576,y+7, eventAdder, "characterStatisticsToggle");
    	components.add(backToGame);
    	entities.add(backToGame);
	}
	
	public List<ZEntity> getEntities () {
		return entities;
	}
}
