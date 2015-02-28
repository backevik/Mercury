package gui;

import java.awt.Image;
import java.util.List;

import core.EventAdder;
import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;

/**
 * @author	Mattias Benngård	<mbengan@gmail.com>
 * @version	0.1					<2015-02-27>
 * @since	2015-02-15
 * 
 * Class for setting up the scene for Load Game
 * 
 * public LoadGameViewer (Image, int, int, EventAdder, List<ZEntity>)
 * Creates the GUI elements for the loadGameViewer.
 */

public class HighScoreViewer extends ZContainer
{
	public HighScoreViewer (Image image, int x, int y, EventAdder eventAdder, List<ZEntity> entities) {
		super(image, x, y, eventAdder, entities);
		
		ZButton btnBack = new ZButton("Back", 20, 520, 120, 60, eventAdder, "sceneMainMenu");
		components.add(btnBack);
		entities.add(btnBack);
	}
}