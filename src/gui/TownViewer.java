package gui;
import java.awt.Graphics;
import java.util.List;

import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import core.EventAdder;
import core.GlobalStateManager;
import database.GameDataManager;
/**
 * Town is a subclass to JFrame
 *
 * @author Martin Claesson
 * @version 0.1, 2015-02-13
 *
 */

public class TownViewer extends ZContainer {
    private final static int windowBottom = 565;
    private final static int btnWith = 122;
    private final static int btnHeight = 82;

    private ZButton btnVendor;
    private ZButton btnLeaveTown;
    private ZButton btnBuy;
    private ZButton btnLeaveVendor;
    private List<ZEntity> entities;
    
    /**
     * Constructor creates all components and adds them to drawable and clickable lists
     */
    public TownViewer( List<ZEntity> entities, EventAdder eventAdder){
        super(eventAdder, GameDataManager.getInstance().getImage("bgDefault.jpg"),0,0,entities);
       
        btnVendor = new ZButton(eventAdder, "enterVendor", "Vendor", 0, 0, btnWith, btnHeight);
        components.add(btnVendor);
        entities.add(btnVendor); 
        
        btnLeaveTown = new ZButton(eventAdder, "sceneWorldMap", "Leave Town", 0, btnHeight, btnWith, btnHeight);
        components.add(btnLeaveTown);
        entities.add(btnLeaveTown);
        
        btnBuy = new ZButton(eventAdder, "enterTownVendorBuy", "Buy", 0, windowBottom-btnHeight, btnWith, (btnHeight/2));
        
        btnLeaveVendor = new ZButton(eventAdder, "leaveVendor", "Leave Vendor", 0, windowBottom-(btnHeight/2), btnWith, btnHeight/2);
        
        GlobalStateManager.getInstance().updateCurrentState("town");    
    }
    
    /**
     * Removes town GUI and opens Worldmap GUI sets game state to worldmap sholud be moved to game
     */
    public void leaveTown() {
    	//removeContainer(TownRef);
    	//worldmap = new Worldmap(arg);
    	//drawables.add(worldmap);
    	GlobalStateManager.getInstance().updateCurrentState("WorldMap");
    }
    /**
     * sets the game state to inside town
     */
    public void leaveVendor(){
    	GlobalStateManager.getInstance().updateCurrentState("town");
    	entities.add(btnVendor);
    	entities.add(btnLeaveTown);
    	entities.remove(btnBuy);
    	entities.remove(btnLeaveVendor);
    	//remove text area
    }
    
    /**
     * Sets the game state to inside vendor
     */
    public void enterVendor(){
    	entities.add(btnBuy);
    	entities.add(btnLeaveVendor);
    	entities.remove(btnVendor);
    	entities.remove(btnLeaveTown);
    	//add text area
    	GlobalStateManager.getInstance().updateCurrentState("townVendor");
    }
    
    /**
     * sets the game state to inside TownVendorBuy
     */
    public void enterTownVendorBuy(){
    	if(GlobalStateManager.getInstance().getCurrentState().equals("townVendorBuy")){
    		entities.add(btnLeaveVendor);
    		GlobalStateManager.getInstance().updateCurrentState("townVendor");
    		//remove buttons for items
    	}else{
        	GlobalStateManager.getInstance().updateCurrentState("townVendorBuy");
        	entities.remove(btnLeaveVendor);
        	//add buttons for items
    		
    	}
    }
    
    /**
     * render
     */
    @Override
    public void render(Graphics g) {
        if(GlobalStateManager.getInstance().getCurrentState().equals("townVendorBuy")){
            super.render(g);
	        btnBuy.render(g);
	        btnLeaveVendor.render(g);
	        //add text area and popup menu
        }
        else if(GlobalStateManager.getInstance().getCurrentState().equals("townVendor")){
	        super.render(g);
	        btnBuy.render(g);
	        btnLeaveVendor.render(g);
	        //add text area
        }
        else{
        	super.render(g);
        }
    }
} 