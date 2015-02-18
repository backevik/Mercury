package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import core.Drawable;
import core.EventAdder;
import core.MouseObject;

public class CharacterCreationViewer extends ZContainer implements Drawable {

	public CharacterCreationViewer(EventAdder eventAdder, Image image, int x, int y, List<MouseObject> mouseObjects) {
		super(eventAdder, image, x, y, mouseObjects);
		
		ZTextField textFieldUserName = new ZTextField("",  x,  y,  200, 20);
		components.add(textFieldUserName);
		
		ZButton btnDone = new ZButton(eventAdder, "launchGame", "Done!", 360, 330, 120, 60);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
	}
}