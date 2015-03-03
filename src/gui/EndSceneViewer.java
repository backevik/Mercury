package gui;

import java.awt.Image;
import java.util.List;

import core.EventAdder;
import database.ImageDatabase;
import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import zlibrary.ZImage;
import zlibrary.ZText;

/**
 * 
 * @author Martin Claesson
 * @version 0.1
 * @since 2015-03-02
 * 
 */
public class EndSceneViewer extends ZContainer{

	private final static int BTN_WITH = 122;
    private final static int BTN_HEIGHT = 114;

    
    public EndSceneViewer(Image image, int x, int y, EventAdder eventAdder,List<ZEntity> entities, String player, String lvl) {
    	super(ImageDatabase.getInstance().getImage("endScene.jpg"),0,0,eventAdder, entities);
    	ZButton btnBacktoGame = new ZButton("Back to Game", 0, 600-BTN_HEIGHT, BTN_WITH, BTN_HEIGHT, eventAdder, "updateToServer");
    	components.add(btnBacktoGame);
    	entities.add(btnBacktoGame);
    	
    	ZImage bgTxt = new ZImage(ImageDatabase.getInstance().getImage("textruta.jpg"), 0, BTN_HEIGHT);
    	components.add(bgTxt);
    	
    	ZText txtInfo = new ZText("Thank you "+ player+"!/nThe main quest is over. You ended it at Lvl "+ lvl+"/nTo upp load highscore and go back to game click 'back to game'", BTN_WITH+4, BTN_HEIGHT-4, 18);
    	components.add(txtInfo);
	}

}
