package gui;
import items.Item;

import java.util.List;

import player.Player;
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
    private final static int BTN_HEIGHT = 57;
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
    private ZText	currency;
    private ZImage	bgText;
    ZImage bgCurrency;
    private EventAdder eventAdder; 
    private List<ZEntity> entities;
    
    /**
     * Constructor creates all components and adds them to drawable and clickable lists
     */
    public TownViewer( List<ZEntity> entities, EventAdder eventAdder){
        super(ImageDatabase.getInstance().getImage("bgDefault.jpg"),0,0,eventAdder, entities);
        
        this.eventAdder = eventAdder;
        this.entities = entities;
        
        btnVendor = new ZButton("Vendor", 3, 32, BTN_WITH, BTN_HEIGHT, eventAdder, "enterVendor");
        components.add(btnVendor);
        entities.add(btnVendor); 
        
        btnLeaveTown = new ZButton( "Leave Town", 3, 32+BTN_HEIGHT, BTN_WITH, BTN_HEIGHT, eventAdder, "sceneWorldMap");
        components.add(btnLeaveTown);
        entities.add(btnLeaveTown);
        
        txtVendor = new ZText("Welcome to Vendor! What can I do for you?", BTN_WITH+9, WINDOW_BOTTOM-BTN_HEIGHT*2+10, 16);
       
        bgText = new ZImage(ImageDatabase.getInstance().getImage("textruta.jpg"), 3+BTN_WITH, WINDOW_BOTTOM-2*BTN_HEIGHT);
        
        btnBuy = new ZButton("Buy", 3, WINDOW_BOTTOM-2*BTN_HEIGHT, BTN_WITH, BTN_HEIGHT, eventAdder, "enterVendorBuy");
        
        btnLeaveVendor = new ZButton( "Exit Vendor", 3, WINDOW_BOTTOM-BTN_HEIGHT, BTN_WITH, BTN_HEIGHT, eventAdder, "leaveVendor");
        
        bgCurrency = new ZImage(ImageDatabase.getInstance().getImage("coin.jpg"), 674, 32);
        
        GlobalStateManager.getInstance().updateCurrentState("TOWN");  
    }
    
    /**
     * sets the game state to inside town
     */
    public void leaveVendor(){
    	remove();
    	components.removeAll(components);
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
    	remove();
    	components.removeAll(components);
    	components.add(btnBuy);
    	components.add(btnLeaveVendor);
    	components.add(bgText);
    	components.add(txtVendor);
    	components.add(bgCurrency);
    	components.add(currency);
    	entities.add(btnBuy);
    	entities.add(btnLeaveVendor);

    	GlobalStateManager.getInstance().updateCurrentState("TOWN_VENDOR");
    }
    
    /**
     * sets the game state to inside TownVendorBuy
     */
    public void enterTownVendorBuy(Vendor vendor){
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
    		
    		ZText itemDescription = new ZText (item.getDescription(), 3+PADDING*2+ ITEM_ICON_SIZE, 32+PADDING + TITLE_HEIGHT+20 + i*ITEM_DISTANCE, 12);
    		components.add(itemDescription);
    		
    		ZText itemPrice = new ZText("Price: "+Integer.toString(item.getSellPrice()),3+PADDING*2+ ITEM_ICON_SIZE, 32+PADDING + TITLE_HEIGHT+36 + i*ITEM_DISTANCE, 12);
    		components.add(itemPrice);
    		
    		if (i != vendor.getItems().size()){
    			ZImage itemBorder = new ZImage (ImageDatabase.getInstance().getImage("bgQuestViewerSeparator.jpg"), 3, 32+PADDING*2 + TITLE_HEIGHT +ITEM_ICON_SIZE +i*ITEM_DISTANCE);
    			components.add(itemBorder);
    		}
    		i++;
    	}
    	GlobalStateManager.getInstance().updateCurrentState("TOWN_VENDOR_BUY");
    	
    }
    /**
     * updates the currency in GUI
     */
    public void updateCurrency(Player player){
    	currency = new ZText(Double.toString(player.getPC().getCurrency()),742,50,14);
    }
} 