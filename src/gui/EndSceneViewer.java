package gui;

import java.util.List;

import core.EventAdder;
import database.ImageDatabase;
import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import zlibrary.ZImage;
import zlibrary.ZText;

/**
 * Combat is a subclass to ZContainer and contains graphical components for the end scene
 * Displays the end scene GUI
 * @author	Martin Claesson
 * @version	1.0
 * @since	2015-03-02
 */

public class EndSceneViewer extends ZContainer
{
	private final static int BTN_WITH = 122;
	private final static int BTN_HEIGHT = 114;
	/**
	 * Constructor initializes the graphical components  
	 * @param eventAdder 
	 * @param entities
	 * @param player - String with the players name
	 * @param lvl - string with the players level
	 */
	public EndSceneViewer(EventAdder eventAdder,List<ZEntity> entities, String player, int lvl) {
		super(ImageDatabase.getInstance().getImage("endScene.jpg"),0,0,eventAdder, entities);
		ZButton btnBacktoGame = new ZButton("Back\n to Game", 0, 600-BTN_HEIGHT, BTN_WITH, BTN_HEIGHT, eventAdder, "updateToServer");
		
		components.add(btnBacktoGame);
		entities.add(btnBacktoGame);
		
		ZImage bgTxt = new ZImage(ImageDatabase.getInstance().getImage("textruta.jpg"), BTN_WITH, 600-BTN_HEIGHT);
		components.add(bgTxt);
		
		ZText txtInfo = new ZText("Thank you "+ player+"!\nThe main quest is over. You ended it at Lvl "+ lvl+"\nTo upp load highscore and go back to game click 'back to game'", BTN_WITH+10, 600-BTN_HEIGHT+10, 18);
		components.add(txtInfo);
	}
}