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
import database.ImageDatabase;
/**
 * Town is a subclass to JFrame
 *
 * @author Martin Claesson
 * @version 0.3, 2015-02-13
 *
 */

public class TownViewer extends ZContainer {
    private final static int WINDOW_BOTTOM = 597;
    private final static int BTN_WITH = 122;
    private final static int BTN_HEIGHT = 82;
	private final static int PADDING = 10;
	private final static int ITEM_ICON_SIZE = ImageDatabase.getInstance().getImage("bgQuestViewerIcon.jpg").getHeight(null);
	private final static int ITEM_BG_SIZE = ImageDatabase.getInstance().getImage("bgQuestViewer.jpg").getWidth(null);
	private final static int TITLE_HEIGHT = 30;
	private final static int ITEM_DISTANCE = PADDING*2 + ITEM_ICON_SIZE + ImageDatabase.getInstance().getImage("bgQuestViewerSeparator.jpg").getHeight(null);
    
	private ZButton btnVendor;
    private ZButton btnLeaveTown;
    private ZButton btnBuy;
    private ZButton btnLeaveVendor;
    private ZText	txtVendor;
    private Vendor 	vendor;// can be removed if reflection can use objects
    private EventAdder eventAdder; // can be removed if reflection can use objects
    private List<ZEntity> entities;
    
    /**
     * Constructor creates all components and adds them to drawable and clickable lists
     */
    public TownViewer( List<ZEntity> entities, EventAdder eventAdder){
        super(ImageDatabase.getInstance().getImage("bgDefault.jpg"),0,0,eventAdder, entities);
        //player = new Player("pc");
        //vendor = new Vendor(player);
        
        this.eventAdder = eventAdder;// can be removed if reflection can use objects
        this.entities = entities;
        
        btnVendor = new ZButton("Vendor", 3, 32, BTN_WITH, BTN_HEIGHT, eventAdder, "enterVendor");
        components.add(btnVendor);
        entities.add(btnVendor); 
        
        btnLeaveTown = new ZButton( "Leave Town", 3, 32+BTN_HEIGHT, BTN_WITH, BTN_HEIGHT, eventAdder, "sceneWorldMap");
        components.add(btnLeaveTown);
        entities.add(btnLeaveTown);
        
        txtVendor = new ZText("Welcome! What can I do for you?", BTN_WITH+3, WINDOW_BOTTOM-BTN_HEIGHT, 16);
        //components.add(txtVendor);
        
        btnBuy = new ZButton("Buy", 3, WINDOW_BOTTOM-BTN_HEIGHT, BTN_WITH, (BTN_HEIGHT/2), eventAdder, "townViewer,enterTownVendorBuy");
        
        btnLeaveVendor = new ZButton( "Leave Vendor", 3, WINDOW_BOTTOM-(BTN_HEIGHT/2), BTN_WITH, BTN_HEIGHT/2, eventAdder, "leaveVendor");
        
        GlobalStateManager.getInstance().updateCurrentState("TOWN");  
        //enterVendor();
    }
    
    /**
     * Removes town GUI and opens Worldmap GUI sets game state to worldmap sholud be moved to game
     */
    public void leaveTown() {
    	remove();
    	//components.removeAll(components);
    	//entities.remove(btnVendor);
    	//entities.remove(btnLeaveTown);//should be done in remove method in zcontaioner
    	GlobalStateManager.getInstance().updateCurrentState("WORLD_MAP");
    }
    /**
     * sets the game state to inside town
     */
    public void leaveVendor(){
    	remove();
    	//components.removeAll(components);
    	components.add(btnVendor);
    	components.add(btnLeaveTown);
    	entities.add(btnVendor);
    	entities.add(btnLeaveTown);
    	GlobalStateManager.getInstance().updateCurrentState("TOWN");
    }
    
    /**
     * Sets the game state to inside vendor 
     */
    public void enterVendor(){
    	//remove();
    	//components.removeAll(components);
    	components.add(btnBuy);
    	components.add(btnLeaveVendor);
    	components.add(txtVendor);
    	entities.add(btnBuy);
    	entities.add(btnLeaveVendor);
    	//add text area when component is done
    	GlobalStateManager.getInstance().updateCurrentState("TOWN_VENDOR");
    }
    
    /**
     * sets the game state to inside TownVendorBuy
     */
    public void enterTownVendorBuy(){
		//components.add(txtVendor);
		ZImage bg = new ZImage(ImageDatabase.getInstance().getImage("bgQuestViewer.jpg"),3,32);
		components.add(bg);
    	
		ZText title = new ZText ("Vendor", 3+PADDING, 32+PADDING, 20);
		components.add(title);
		
		ZButton backToTownVendor = new ZButton (ImageDatabase.getInstance().getImage("bgQuestViewerQuit.jpg"), ITEM_BG_SIZE-2*PADDING-6, 32+PADDING, eventAdder, "enterVendor");//not sure of how reflection works
    	components.add(backToTownVendor);
    	entities.add(backToTownVendor);
		int i =0;
		
		String itemName;// used in btnItem
    	for (Item item : vendor.getItems().keySet()) {
    		itemName = item.getName();//to send the item name to buyItem method in reflection
    		ZButton btnItem = new ZButton (ImageDatabase.getInstance().getImage("bgQuestViewerIcon.jpg"), 3+PADDING, 32+PADDING + TITLE_HEIGHT + i*ITEM_DISTANCE, eventAdder,"buyItem,"+itemName);
    		components.add(btnItem);
    		entities.add(btnItem);
    		
    		ZText itemNameText = new ZText (item.getName(), 3+PADDING*2 + ITEM_ICON_SIZE, 32+PADDING + TITLE_HEIGHT + i*ITEM_DISTANCE, 16);
    		components.add(itemNameText);
    		
    		ZText questDescription = new ZText (item.getDescription(), 3+PADDING*2+ ITEM_ICON_SIZE, 32+PADDING + TITLE_HEIGHT+20 + i*ITEM_DISTANCE, 12);
    		components.add(questDescription);
    		if (i != vendor.getItems().size()){
    			ZImage itemBorder = new ZImage (ImageDatabase.getInstance().getImage("bgQuestViewerSeparator.jpg"), 3, 32+PADDING*2 + TITLE_HEIGHT +ITEM_ICON_SIZE +i*ITEM_DISTANCE);
    			components.add(itemBorder);
    		}
    		i++;
    	}
    	GlobalStateManager.getInstance().updateCurrentState("TOWN_VENDOR_BUY");
    	
    }
} 