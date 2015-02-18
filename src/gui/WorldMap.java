package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.KeyEvent;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3
 * @since		2015-02-17
 */

public class WorldMap
{
	private JFrame frame;

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
		
		/*//TownButton
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
		btnForest.connect("east" , btnGrass);*/
		
	}
	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
}
