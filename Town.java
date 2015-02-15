import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
/**
 * Town is a subclass to JFrame 
 * 
 * @author Martin Claesson
 * @version 0.1, 2015-02-13
 * 
 */

public class Town {
	private final static int windowHeight = 600;
	private final static int windowWith = 800;
	private final static int windowBottom = 565;
	private final static int btnWith = 122;
	private final static int btnHeight = 82;
	private final static int textWith = windowWith-btnWith-6;
	private final static int textHeight = btnHeight;

	private JFrame frame;
	private JTextArea textArea;
	private JButton vendorBtn;
	private JButton leaveTownBtn;
	private JButton buyBtn;
	private JButton leaveVendorBtn;
	private ArrayList<String> vendorItems;
	private JPopupMenu vendorMenu;
	private JMenuItem tempItem;
	
	
	
	
	/**
	 * Main for testing 
	 */
	public static void main(String[] args) { 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Town town = new Town();
					town.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Constructor creates all components and adds listener to the buttons
	 */
	public Town(){
		vendorItems = new ArrayList<>(); //should be added from data base of item 
		vendorItems.add("Helth Potion"); //temp item
		vendorItems.add("Mana Potion"); //temp item
		
		frame = new JFrame();
		frame.setTitle("Game");
		frame.setBounds(100, 100, windowWith, windowHeight);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new JLabel(new ImageIcon("town.jpg")));
		
		vendorMenu = new JPopupMenu("vendorMenu");
		
		vendorBtn = new JButton("Vendor");
		vendorBtn.setBounds(0, 0, btnWith, btnHeight);
		//listener should be moved to controller 
		vendorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				enterVendor();
			}
		});
		frame.getContentPane().add(vendorBtn);
		
		leaveTownBtn = new JButton("LeaveTown");
		leaveTownBtn.setBounds(0, btnHeight, btnWith, btnHeight);
		frame.getContentPane().add(leaveTownBtn);
		//listener should be moved to controller 
		/*
		buyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//showWorldmap(); updates GUI to worldmap
			}
		});
		*/
		
		buyBtn = new JButton("Buy");
		buyBtn.setBounds(0,windowBottom-btnHeight, btnWith, (btnHeight/2));
		buyBtn.setToolTipText("Shows all buyable items");
		//listener should be moved to controller 
		buyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				showItems();
			}
		});
		
		
		leaveVendorBtn = new JButton("Leave Vendor");
		leaveVendorBtn.setBounds(0, windowBottom-(btnHeight/2), btnWith, btnHeight/2);
		//listener should be moved to controller 
		leaveVendorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				leaveVendor();
			}
		});
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(SystemColor.controlHighlight);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBounds(btnWith, windowBottom-textHeight, textWith, textHeight);
		textArea.setFont(new Font("Serif",Font.PLAIN,20));
		textArea.setText("Welcome! What can I do for you?");
		
		//constructing the popup menu 
		for(String item : vendorItems){
			tempItem = new JMenuItem(item);
			tempItem.setToolTipText("Price: 5 Coins");
			//add listener that runs add item method to the button 
			vendorMenu.add(tempItem);
			
		}
	}
	/**
	 * Sets all components for vendor viable 
	 */
	public void enterVendor(){
		frame.getContentPane().add(buyBtn);
		frame.getContentPane().add(leaveVendorBtn);
		frame.getContentPane().add(textArea);
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
		
	}
	/**
	 * hides the vendor components
	 */
	public void leaveVendor(){
		frame.getContentPane().remove(buyBtn);
		frame.getContentPane().remove(leaveVendorBtn);
		frame.getContentPane().remove(textArea);
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
	}
	/**
	 * Shows the JMenu created for scalability. If more items are added the layout can be changed her. 
	 */
	private void showItems(){
		vendorMenu.show(buyBtn, btnWith, 0);
	}
}