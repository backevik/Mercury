package gui;

import highscore.Client;

import java.awt.Image;
import java.util.List;

import core.EventAdder;
import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import zlibrary.ZText;
import zlibrary.ZTextArea;

/**
 * Sets up and holds all gui elements to the character creation
 * 
 * @author	Mattias Benngård	<mbengan@gmail.com>
 * @version	0.1					<2015-02-27>
 * @since	2015-02-15
 */

public class HighScoreViewer extends ZContainer
{
	public HighScoreViewer (Image image, int x, int y, EventAdder eventAdder, List<ZEntity> entities) {
		super(image, x, y, eventAdder, entities);
		
		Client client = new Client("localhost");
		ZButton btnBack = new ZButton("Back", 20, 520, 120, 60, eventAdder, "sceneMainMenu");
		components.add(btnBack);
		entities.add(btnBack);
		
		ZText text = new ZText("Level, Name",320,120,40);
		components.add(text);
		
		ZTextArea scoreArea = new ZTextArea(320,200,300,300,30);
		components.add(scoreArea);
		for(String s : client.getScore()){
			scoreArea.addText(s);
		}
	}
}