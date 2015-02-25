package zlibrary;

import java.awt.Graphics;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import core.EventAdder;
import database.ImageDatabase;

public class ZPopup extends ZComponent
{
	private final static int PADDING = 10;
	
	private final static int X = (800-ImageDatabase.getInstance().getImage("popupWindow.jpg").getWidth(null))/2;
	private final static int Y = (600-ImageDatabase.getInstance().getImage("popupWindow.jpg").getHeight(null))/2;
	private final static Image BG = ImageDatabase.getInstance().getImage("popupWindow.jpg");
	
	private final static int buttonW = 80;
	private final static int buttonH = 30;
	private final static int buttonX = (800-buttonW)/2;
	private final static int buttonY = Y+ImageDatabase.getInstance().getImage("popupWindow.jpg").getHeight(null)-PADDING-buttonH;
	
	private ZImage background;
	private ZText text;
	private ZButton button;
	
	private final List<ZEntity> entities = new ArrayList<>();
	
	public ZPopup (String msg, String button, EventAdder eventAdder, List<ZEntity> entities) {

		for (ZEntity e : entities) {
			this.entities.add(e);
		}
		
		entities.clear();
		
		background = new ZImage (BG, X, Y);
		
		text = new ZText (msg, X+PADDING, Y+PADDING,
				BG.getWidth(null)-PADDING,
				BG.getHeight(null)-PADDING-buttonH-PADDING,
				12, "...");
		
		this.button = new ZButton (button, buttonX, buttonY, buttonW, buttonH, eventAdder, "popupWindowOff");
		entities.add (this.button);		
	}
	
	public List<ZEntity> remove () {
		background = null;
		text = null;
		entities.remove(button);
		button = null;
		return entities;
	}
	
	@Override
	public void render (Graphics g) {
		background.render(g);
		text.render(g);
		button.render(g);
	}
}
