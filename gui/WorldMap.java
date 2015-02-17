import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

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

public class WorldMap implements KeyListener
{

	private JFrame frame;
	private Zone current;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorldMap window = new WorldMap();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WorldMap() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();	//Create window frame
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addKeyListener(this);
		frame.setFocusable(true);
		
		//Create background
		JLabel lblBackground = new JLabel("Background");
		lblBackground.setIcon(new ImageIcon(getClass().getResource("/World/WorldBackground.jpg")));
		lblBackground.setBounds(0, 0, 784, 562);
		frame.getContentPane().add(lblBackground);
		
		
		/**
		 * Initiate buttons
		 */
		String fightOff = "/World/Fight.jpg";
		String fightOn = "/World/Fight on.jpg";
		
		//TownButton
		Zone btnTown = new Zone(16, 333, 176, 64, "/World/Town.jpg", "/World/Town on.jpg");
		addEvent(btnTown);
		btnTown.setOn();
		current = btnTown;
		
		//ForestBattle
		Zone btnForest = new Zone(104, 104, 64, 64, fightOff, fightOn);
		addEvent(btnForest);
		btnForest.setOff();
		
		//GrassBattle
		Zone btnGrass = new Zone(408, 48, 64, 64, fightOff, fightOn);
		addEvent(btnGrass);
		btnGrass.setOff();
		//-----------------------------
		
		//Connect buttons to each other
		btnTown.connect("north" , btnForest);
		btnForest.connect("east" , btnGrass);
		
	}
	
	/**
	 * Add button to the window with a actionEvent
	 * 
	 * @param button
	 */
	public void addEvent(Zone button) {
		button.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
            	clickSelect( (Zone)e.getSource());
            }
        });
		
		frame.getContentPane().add(button);
	}
	
	
	/**
	 * Select zone when button is pressed with mouse
	 * 
	 * @param button
	 */
	public void clickSelect(Zone button) {
		
		if(current.isConnected(button)){
			button.setOn();
			current.setOff();
			current = button;
		}
		
	}
	
	/**
	 * Move to zone in the chosen direction if the zones are connected
	 * 
	 * @param direction
	 */
	public void moveSelect(String direction) {
		Zone nextZone = current.getNextZone(direction);	//Return
		
		if( nextZone != null )
		{
			nextZone.setOn();
			current.setOff();
			current = nextZone;
		}
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
	
}
