package gui;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import player.Playable;
import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import zlibrary.ZText;
import core.EventAdder;
import database.ImageDatabase;

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
	private final static int LINE_HEIGHT = 20;
	
	private final static int ATTRIBUTE_OFFSET = (int)(PADDING+TITLE_HEIGHT+LINE_HEIGHT*5.5);
	private final static int VITAL_OFFSET = PADDING+TITLE_HEIGHT+LINE_HEIGHT*10;
	
	private final List<ZEntity> entities = new ArrayList<>();
	@SuppressWarnings("unused")
	private Playable pc;
	
	public CharacterStatisticsViewer (Image image, int x, int y, EventAdder eventAdder, List<ZEntity> entities, Playable pc) {
		super(image, x, y, eventAdder, entities);
		
		for (ZEntity e : entities) {
			this.entities.add(e);
		}
		
		entities.clear();

		this.pc = pc;
		
		ZText title = new ZText ("Character Statistics", x+PADDING, y+PADDING, 20);
		components.add(title);
		
		ZButton backToGame = new ZButton (ImageDatabase.getInstance().getImage("bgQuestViewerQuit.jpg"), x+576,y+7, eventAdder, "characterStatisticsToggle");
    	components.add(backToGame);
    	entities.add(backToGame);
    	    	
		components.add(new ZText (pc.getName(), x+PADDING, y+PADDING+TITLE_HEIGHT, 16));

		components.add(new ZText ("Level " + pc.getLevel(), x+PADDING, y+PADDING+TITLE_HEIGHT+LINE_HEIGHT, 14));	
		components.add(new ZText ("Gender", x+PADDING, y+PADDING+TITLE_HEIGHT+LINE_HEIGHT*2, 14));		
		components.add(new ZText ("Race", x+PADDING, y+PADDING+TITLE_HEIGHT+LINE_HEIGHT*3, 14));		
		components.add(new ZText ("Class", x+PADDING, y+PADDING+TITLE_HEIGHT+LINE_HEIGHT*4, 14));
		
		components.add(new ZText ("Attributes", x+PADDING, y+ATTRIBUTE_OFFSET, 16));		
		for (int i = 0; i < constants.Attributes.getNames().size(); i++) {
			components.add(new ZText (constants.Attributes.getNames().get(i), x+PADDING, y+ATTRIBUTE_OFFSET+LINE_HEIGHT+i*18, 14));
			components.add(new ZText (pc.getValueOfAttribute(constants.Attributes.getNames().get(i))+"", x+PADDING+100, y+ATTRIBUTE_OFFSET+LINE_HEIGHT+i*18, 14));
		}
		
		components.add(new ZText ("Vitals", x+PADDING, y+VITAL_OFFSET, 16));		
		for (int i = 0; i < constants.Vitals.getNames().size(); i++) {
			components.add(new ZText (constants.Vitals.getNames().get(i), x+PADDING, y+LINE_HEIGHT+VITAL_OFFSET+i*18, 14));
			components.add(new ZText (pc.getValueOfVital(constants.Vitals.getNames().get(i))+"/"+pc.getMaxOfVital(constants.Vitals.getNames().get(i)), x+PADDING+100, y+LINE_HEIGHT+VITAL_OFFSET+i*18, 14));
		}
	}
	
	public List<ZEntity> getEntities () {
		return entities;
	}
}
