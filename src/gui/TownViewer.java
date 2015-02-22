package gui;
import items.Item;

import java.util.List;

import vendor.Vendor;
import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZEntity;
import zlibrary.ZImage;
import zlibrary.ZText;
import core.EventAdder;
import core.GlobalStateManager;
import database.GameDataManager;
/**
 * Town is a subclass to JFrame
 *
 * @author Martin Claesson
 * @version 0.3, 2015-02-13
 *
 */

public class TownViewer extends ZContainer {
    private final static int windowBottom = 565;
    private final static int btnWith = 122;
    private final static int btnHeight = 82;
	private final static int PADDING = 10;
	private final static int ITEM_ICON_SIZE = GameDataManager.getInstance().getImage("bgQuestViewerIcon.jpg").getHeight(null);
	private final static int TITLE_HEIGHT = 30;
	private final static int ITEM_DISTANCE = PADDING*2 + ITEM_ICON_SIZE + GameDataManager.getInstance().getImage("bgQuestViewerSeparator.jpg").getHeight(null);
    
	private ZButton btnVendor;
    private ZButton btnLeaveTown;
    private ZButton btnBuy;
    private ZButton btnLeaveVendor;
    private Vendor 	vendor;// can be removed if reflection can use objects
    private EventAdder eventAdder; // can be removed if reflection can use objects
    
    /**
     * Constructor creates all components and adds them to drawable and clickable lists
     */
    public TownViewer( List<ZEntity> entities, EventAdder eventAdder){//Vendor vendor needs to be added 
        super(GameDataManager.getInstance().getImage("bgDefault.jpg"),0,0,eventAdder, entities);
        
        //this.vendor = vendor;// can be removed if reflection can use objects
        this.eventAdder = eventAdder;// can be removed if reflection can use objects

        btnVendor = new ZButton("Vendor", 0, 0, btnWith, btnHeight, eventAdder, "enterVendor");
        components.add(btnVendor);
        entities.add(btnVendor); 
        
        btnLeaveTown = new ZButton( "Leave Town", 0, btnHeight, btnWith, btnHeight, eventAdder, "sceneWorldMap");
        components.add(btnLeaveTown);
        entities.add(btnLeaveTown);
        
        btnBuy = new ZButton("Buy", 0, windowBottom-btnHeight, btnWith, (btnHeight/2), eventAdder, "townViewer,enterTownVendorBuy");
        
        btnLeaveVendor = new ZButton( "Leave Vendor", 0, windowBottom-(btnHeight/2), btnWith, btnHeight/2, eventAdder, "leaveVendor");
        
        GlobalStateManager.getInstance().updateCurrentState("town");    
    }
    
    /**
     * Removes town GUI and opens Worldmap GUI sets game state to worldmap sholud be moved to game
     */
    public void leaveTown() {
    	remove();
    	GlobalStateManager.getInstance().updateCurrentState("WorldMap");
    }
    /**
     * sets the game state to inside town
     */
    public void leaveVendor(){
    	remove();
    	components.add(btnVendor);
    	components.add(btnLeaveTown);
    	entities.add(btnVendor);
    	entities.add(btnLeaveTown);
    	GlobalStateManager.getInstance().updateCurrentState("town");
    }
    
    /**
     * Sets the game state to inside vendor 
     */
    public void enterVendor(){
    	remove();
    	components.add(btnBuy);
    	components.add(btnLeaveVendor);
    	entities.add(btnBuy);
    	entities.add(btnLeaveVendor);
    	//add text area when component is done
    	GlobalStateManager.getInstance().updateCurrentState("townVendor");
    }
    
    /**
     * sets the game state to inside TownVendorBuy
     */
    public void enterTownVendorBuy(){
    	ZText title = new ZText ("Vendor", PADDING, PADDING, 20);
		components.add(title);
		
		ZButton backToTownVendor = new ZButton (GameDataManager.getInstance().getImage("bgQuestViewerQuit.jpg"), 476, 7, eventAdder, "townViewer,enterVendor");//not sure of how reflection works
    	components.add(backToTownVendor);
    	entities.add(backToTownVendor);
		int i =0;
		String itemName;// used in btnItem
    	for (Item item : vendor.getItems().keySet()) {
    		itemName = item.getName();//to send the item name to buyItem method in reflection
    		ZButton btnItem = new ZButton (GameDataManager.getInstance().getImage("bgQuestViewerIcon.jpg"), PADDING, PADDING + TITLE_HEIGHT + i*ITEM_DISTANCE, eventAdder,"vendor,buyItem,itemName");
    		components.add(btnItem);
    		
    		ZText itemNameText = new ZText (item.getName(), PADDING*2 + ITEM_ICON_SIZE, PADDING + TITLE_HEIGHT + i*ITEM_DISTANCE, 16);
    		components.add(itemNameText);
    		
    		ZText questDescription = new ZText (item.getDescription(), PADDING*2+ ITEM_ICON_SIZE, PADDING + TITLE_HEIGHT+20 + i*ITEM_DISTANCE, 12);
    		components.add(questDescription);
    		if (i != vendor.getItems().size()){
    			ZImage itemBorder = new ZImage (GameDataManager.getInstance().getImage("bgQuestViewerSeparator.jpg"), 0, PADDING*2 + TITLE_HEIGHT +ITEM_ICON_SIZE +i*ITEM_DISTANCE);
    			components.add(itemBorder);
    		}
    		i++;
    	}
    	GlobalStateManager.getInstance().updateCurrentState("townVendorBuy");
    }
} 