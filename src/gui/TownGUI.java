package core;
import java.awt.image.ComponentSampleModel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import database.GameDataManager;
import database.ImageDatabase;
import gui.ZButton;
import gui.ZContainer;
/**
 * Town is a subclass to JFrame
 *
 * @author Martin Claesson
 * @version 0.1, 2015-02-13
 *
 */

public class TownGUI extends ZContainer{
    private final static int windowWith = 800;
    private final static int windowBottom = 565;
    private final static int btnWith = 122;
    private final static int btnHeight = 82;
    private final static int textWith = windowWith-btnWith-6;
    private final static int textHeight = btnHeight;

    /*
    private JFrame frame;
    private JTextArea textArea;
    private JButton vendorBtn;
    private JButton leaveTownBtn;
    private JButton buyBtn;
    private JButton leaveVendorBtn;
    private ArrayList<String> vendorItems;
    private JPopupMenu vendorMenu;
    private JMenuItem tempItem;
    */
    private ZButton btnVendor;
    private ZButton btnLeaveTown;
    private ZButton btnBuy;
    private ZButton btnLeaveVendor;
    

    /**
     * Constructor creates all components and adds them to drawable and clickable lists
     */
    public TownGUI( List<MouseObject> mouseObjects, EventAdder eventAdder){
        super(eventAdder, GameDataManager.getInstance().getImage("town.jpg"),0,0,mouseObjects);
        
        btnVendor = new ZButton(eventAdder, "enterVendor", "Vendor", 0, 0, btnWith, btnHeight);
        components.add(btnVendor);
        mouseObjects.add(btnVendor); 
        
        btnLeaveTown = new ZButton(eventAdder, "leaveTown", "Leave Town", 0, btnHeight, btnWith, btnHeight);
        components.add(btnLeaveTown);
        mouseObjects.add(btnLeaveTown);
        
        btnBuy = new ZButton(eventAdder, "openItemMenu", "Buy", 0, windowBottom-btnHeight, btnWith, (btnHeight/2));
        components.add(btnBuy);
        mouseObjects.add(btnBuy);
        
        btnLeaveVendor = new ZButton(eventAdder, "leaveVendor", "Leave Vendor", 0, windowBottom-(btnHeight/2), btnWith, btnHeight/2);
        components.add(btnLeaveVendor);
        mouseObjects.add(btnLeaveVendor);
    }
    
    /**
     * render
     */
    @Override
    public void render(Graphics g) {
        if(GlobalStateManager.getInstance().getCurrentState().equals("TownVendorBuy")){
            super.render(g);
        }
        else if(GlobalStateManager.getInstance().getCurrentState().equals("TownVendor")){
	        btnVendor.render(g);
	        btnLeaveTown.render(g);
	        btnBuy.render(g);
	        btnLeaveVendor.render(g);
        }
        else{
        	btnVendor.render(g);
        	btnLeaveTown.render(g);
        }
    }
    
    
    
    /**
     * Sets the gamestate to inside vendor
     */
    public void enterVendor(){
    	GlobalStateManager.getInstance().updateCurrentState("TownVendor");
    }
    /**
     * sets the gamestate to inside town
     */
    public void leaveVendor(){
    	GlobalStateManager.getInstance().updateCurrentState("Town");
    }
} 