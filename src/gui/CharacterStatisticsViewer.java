package gui;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import player.Playable;
import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import zlibrary.ZImage;
import zlibrary.ZText;
import core.EventAdder;
import database.ImageDatabase;

/**
 * @author	Mattias Benngård	<mbengan@gmail.com
 * @version	1.0					<2015-02-27>
 * @since	2015-02-17
 * 
 * Class for displaying player characteristics, equipment and inventory.
 * Also handles 
 * 
 * public CharacterStatisticsViewer (Image, int, int, EventAdder, List<ZEntity>, Playable)
 * 
 * Should be instantiated from game.
 * Remember to add it to drawable.
 */

public class CharacterStatisticsViewer extends ZContainer
{
	private final static int PADDING 			= 10;
	
	private final int COLUMN1_X 				= getX()+PADDING;
	private final int COLUMN2_X 				= getX()+getImage().getWidth(null)/2 + PADDING;
	
	private final int ITEMS_PER_ROW 			= 8;
	private final int ITEM_ROWS 				= 4;
	
	private final int ITEM_SLOT_SIZE			= ImageDatabase.getInstance().getImage("bgItemSlot.jpg").getWidth(null);
	
	private final static int TITLE_HEIGHT 		= 30;
	private final static int LINE_HEIGHT 		= 20;
	
	private final static int ATTRIBUTE_OFFSET 	= (int)(PADDING+TITLE_HEIGHT+LINE_HEIGHT*5.5);
	private final static int VITAL_OFFSET 		= PADDING+TITLE_HEIGHT+LINE_HEIGHT*10;
	
	private final List<ZEntity> entities 		= new ArrayList<>();
	
	public CharacterStatisticsViewer (Image image, int x, int y, EventAdder eventAdder, List<ZEntity> entities, Playable pc) {
		super(image, x, y, eventAdder, entities);
		
		for (ZEntity e : entities) {
			this.entities.add(e);
		}

		entities.clear();
		
		ZText title = new ZText ("Character Statistics", COLUMN1_X, y+PADDING, 20);
		components.add(title);
		
		ZButton backToGame = new ZButton (ImageDatabase.getInstance().getImage("bgQuestViewerQuit.jpg"), x+576, y+7, eventAdder, "characterStatisticsToggle");
    	components.add(backToGame);
    	entities.add(backToGame);
    	
    	// name, level, gender, race class, attribute, vitals
		components.add(new ZText (pc.getName(), COLUMN1_X, y+PADDING+TITLE_HEIGHT, 16));

		components.add(new ZText ("Level " + pc.getLevel(), COLUMN1_X, y+PADDING+TITLE_HEIGHT+LINE_HEIGHT, 14));	
		components.add(new ZText ("Gender", COLUMN1_X, y+PADDING+TITLE_HEIGHT+LINE_HEIGHT*2, 14));		
		components.add(new ZText ("Race", COLUMN1_X, y+PADDING+TITLE_HEIGHT+LINE_HEIGHT*3, 14));		
		components.add(new ZText ("Class", COLUMN1_X, y+PADDING+TITLE_HEIGHT+LINE_HEIGHT*4, 14));
		
		components.add(new ZText ("Attributes", COLUMN1_X, y+ATTRIBUTE_OFFSET, 16));		
		for (int i = 0; i < constants.Attributes.getNames().size(); i++) {
			components.add(new ZText (constants.Attributes.getNames().get(i), COLUMN1_X, y+ATTRIBUTE_OFFSET+LINE_HEIGHT+i*18, 14));
			components.add(new ZText (pc.getValueOfAttribute(constants.Attributes.getNames().get(i))+"", COLUMN1_X+100, y+ATTRIBUTE_OFFSET+LINE_HEIGHT+i*18, 14));
		}
		
		components.add(new ZText ("Vitals", COLUMN1_X, y+VITAL_OFFSET, 16));		
		for (int i = 0; i < constants.Vitals.getNames().size(); i++) {
			components.add(new ZText (constants.Vitals.getNames().get(i), COLUMN1_X, y+LINE_HEIGHT+VITAL_OFFSET+i*18, 14));
			components.add(new ZText (pc.getValueOfVital(constants.Vitals.getNames().get(i))+"/"+pc.getMaxOfVital(constants.Vitals.getNames().get(i)), COLUMN1_X+100, y+LINE_HEIGHT+VITAL_OFFSET+i*18, 14));
		}
		
		// equipment, inventory
		components.add(new ZText ("Equipment", COLUMN2_X, y+PADDING+TITLE_HEIGHT, 16));
		
		components.add(new ZText ("Inventory", COLUMN2_X, y+PADDING+TITLE_HEIGHT+230, 16));
		for (int row = 0; row < ITEM_ROWS; row++) {
			for (int col = 0; col < ITEMS_PER_ROW; col++) {
				components.add(new ZImage (ImageDatabase.getInstance().getImage("bgItemSlot.jpg"), COLUMN2_X + ITEM_SLOT_SIZE*col, y+PADDING+TITLE_HEIGHT+230 + LINE_HEIGHT + ITEM_SLOT_SIZE*row));
				if (pc.getInventory().getItem(row*col + col) != null) {
					ZButton item = new ZButton (ImageDatabase.getInstance().getImage("item.jpg"), COLUMN2_X + ITEM_SLOT_SIZE*col, y+PADDING+TITLE_HEIGHT+230 + LINE_HEIGHT + ITEM_SLOT_SIZE*row, eventAdder, "characterStatisticsToggle");
			    	components.add(item);
			    	entities.add(item);
				}
			}
		}
	}
	
	public List<ZEntity> getEntities () {
		return entities;
	}
}
