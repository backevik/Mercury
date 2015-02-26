package gui;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import core.EventAdder;
import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import zlibrary.ZText;

public class LoadGameViewer extends ZContainer
{
	public LoadGameViewer(Image image, int x, int y, EventAdder eventAdder, List<ZEntity> entities) {
		super(image, x, y, eventAdder, entities);
		
		components.add(new ZText ("Save Files", 20, 30, 20));
		
		if (getProfileNames().size() == 0) {
			components.add(new ZText ("There is no saved game data avaible.", 20, 70, 16));
		} else {
			for (String s : getProfileNames()) {
				
			}
		}		
		
		ZButton btnBack = new ZButton("Back", 20, 520, 120, 60, eventAdder, "sceneMainMenu");
		components.add(btnBack);
		entities.add(btnBack);
	}
	
	private List<String> getProfileNames () {
		List<String> profileNames = new ArrayList<>();
		
		return profileNames;
	}
}