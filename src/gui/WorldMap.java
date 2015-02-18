package gui;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import core.Drawable;
import core.EventAdder;
import core.MouseObject;
import database.GameDataManager;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.*;
import java.awt.event.*;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3
 * @since		2015-02-17
 */

public class WorldMap extends ZContainer implements KeyListener,Drawable
{

	private Zone current;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public WorldMap(List<MouseObject> mouseObjects, EventAdder eventAdder) {
		super(eventAdder,GameDataManager.getInstance().getImage("bgDefault.jpg"), 0, 0, mouseObjects);
		initialize(mouseObjects,eventAdder);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(List<MouseObject> mouseObjects, EventAdder eventAdder) {
		
		//Create background
		//JLabel lblBackground = new JLabel("Background");
		//lblBackground.setIcon(new ImageIcon(getClass().getResource("/World/WorldBackground.jpg")));
		//lblBackground.setBounds(0, 0, 784, 562);
		//frame.getContentPane().add(lblBackground);

		/**
		 * Initiate buttons
		 */
		String fightOff = "/World/Fight.jpg";
		String fightOn = "/World/Fight on.jpg";
		
		//TownButton
		Zone btnTown = new Zone(eventAdder, "clickSelect, Zone", GameDataManager.getInstance().getImage("sprQuestIcon.jpg"), 16, 333,"a1");
		components.add(btnTown);
		mouseObjects.add(btnTown);
		
		Zone btnForest = new Zone(eventAdder, "clickSelect, Zone", GameDataManager.getInstance().getImage("sprQuestIcon.jpg"), 104, 104,"a2");
		components.add(btnForest);
		mouseObjects.add(btnForest);
		
		Zone btnGrass = new Zone(eventAdder, "clickSelect, Zone", GameDataManager.getInstance().getImage("sprQuestIcon.jpg"), 104, 104,"a3");
		components.add(btnGrass);
		mouseObjects.add(btnGrass);
		
		
		//ForestBattle
		//Zone btnForest = new Zone(104, 104, 64, 64, fightOff, fightOn);
		//addEvent(btnForest);
		//btnForest.setOff();
		
		//GrassBattle
		//Zone btnGrass = new Zone(408, 48, 64, 64, fightOff, fightOn);
		
		//addEvent(btnGrass);
		//btnGrass.setOff();
		//-----------------------------
		
		//Connect buttons to each other
		//btnTown.connect("north" , btnForest);
		//btnForest.connect("east" , btnGrass);
		
	}	
	/**
	 * Select zone when button is pressed with mouse
	 * 
	 * @param button
	 */
	public void clickSelect(Zone button) {
		
		//if(current.isConnected(button)){
		//	button.setOn();
		//	current.setOff();
		//	current = button;
		//}
		
	}
	
	/**
	 * Move to zone in the chosen direction if the zones are connected
	 * 
	 * @param direction
	 */
	public void moveSelect(String direction){
	//	Zone nextZone = current.getNextZone(direction);	//Return
		
	//	if( nextZone != null )
	//	{
	//		nextZone.setOn();
	//		current.setOff();
	//		current = nextZone;
	//	}
	}
	

	public void keyPressed(KeyEvent e) {

		switch(e.getKeyCode()){
			case KeyEvent.VK_UP: 	moveSelect("north");
									break;
			
			case KeyEvent.VK_DOWN: 	moveSelect("south");
									break;
			
			case KeyEvent.VK_RIGHT: moveSelect("east");
									break;
			
			case KeyEvent.VK_LEFT: 	moveSelect("west");
									break;
		}
	}

	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
}
