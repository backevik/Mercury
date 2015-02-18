package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import player.QuestLog;
import core.Drawable;
import core.Game;
import core.MouseObject;

public class QuestLogViewer implements Drawable {
	private ZImage bg;
	private final List<ZButton> questIcons = new ArrayList<>();
	private ZButton backToGame;
	@SuppressWarnings("unused")
	private QuestLog questLog;
	@SuppressWarnings("unused")
	private List<MouseObject> mouseObjects;

	private int x;
	private int y;
	
	public QuestLogViewer (QuestLog questLog, List<MouseObject> mouseObjects) {
		this.mouseObjects = mouseObjects;
    	Image image = null;
    	Image image2 = null;
    	Image image3 = null;
    	try {
    		image = ImageIO.read(new File("C:\\Users\\Emzeror\\Workspaces\\ActionBasedRPG\\img\\bgQuestViewer.bmp"));
    		image2 = ImageIO.read(new File("C:\\Users\\Emzeror\\Workspaces\\ActionBasedRPG\\img\\sprQuestIcon.bmp"));
    		image3 = ImageIO.read(new File("C:\\Users\\Emzeror\\Workspaces\\ActionBasedRPG\\img\\sprQuestIcon.bmp"));
    	} catch (IOException e) {System.out.println("Image(s) not found!");}
    	
    	x = (Game.FRAME_X-image.getWidth(null))/2;
    	y = (Game.FRAME_Y-image.getHeight(null))/2;
    	
    	bg = new ZImage (image, x, y);
    	for (int i = 0; i != questLog.getQuests().size(); ++i) {
    		questIcons.add(new ZButton (null, i + "q", image2, x+10, y+10+i*70));
    		mouseObjects.add(questIcons.get(i));
    	}
    	
    	backToGame = new ZButton (null, "toggleGui", image3, 500,500);
    	mouseObjects.add(backToGame);
	}
	
	@Override
	public void render(Graphics g) {
		bg.render(g);
		for (ZButton zb : questIcons) {
			zb.render(g);
		}
		backToGame.render(g);
	}
}
