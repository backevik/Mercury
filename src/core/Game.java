package core;

import items.ItemPotion;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.KeyboardFocusManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import database.EnemyDatabase;
import database.ImageDatabase;
import database.SpellDatabase;
import character.Spell;
import combat.Combat;
import combat.Encounter;
import combat.Enemy;
import player.Playable;
import player.Player;
import worldmap.WorldMap;
import zlibrary.ZContainer;
import zlibrary.ZDrawable;
import zlibrary.ZEntity;
import zlibrary.ZPopup;
import gui.CharacterCreationViewer;
import gui.CharacterStatisticsViewer;
import gui.CombatViewer;
import gui.CreditsViewer;
import gui.LoadGameViewer;
import gui.MainMenuViewer;
import gui.QuestLogViewer;
import gui.TownViewer;
import gui.WorldMapViewer;
import vendor.Vendor;

/**
 * Game Class for project Mercury, holds main method. Instantiates itself.
 * 
 * @author	Anton Andrén		<@>
 * @author	Mattias Benngård	<mbengan@gmail.com>
 * @author 	Andreas Bäckevik	<@>
 * @author	Martin Claesson		<@>
 * @author 	Daniel Edsinger		<@>
 * @version	0.8					<2015-05-25>
 * @since	2015-02-21
 * 
 * Main Class for the mercury project.
 * 
 * public static void main (String[])		Instantiates Game and runs the gameLoop initiation
 * private Game ()							a
 * 
 * private void startGameLoop ()			a
 * private void gameLoop ()					a
 * 
 * private  void render ()					to render all ZDrawable
 * private  void update ()					to update all ZRealTime
 * private void eventHandler (String s)		to use reflection invocation
 * 
 * To-Do:
 *	- Save
 *	- Load
 *	- Credits
 *	- Load Game
 *	- Retreat Bug? Where you retreat and the opponent doesn't attack. Intended? As in spam retreat and succeed eventually without any consequence. 
 */

public class Game implements MouseListener
{
	public static final int FRAME_X							= 800;
	public static final int FRAME_Y							= 600;
	
	private final Frame frame = new Frame ();
	
	private boolean isRunning = true;
    
    //Rate for updates/s and expected time per update.
	public static final double GAME_UPDATE_RATE			= 30.0;
	public static final double TARGET_TIME_BETWEEN_UPDATES = 1000_000_000.0 / GAME_UPDATE_RATE;
    
    //Allow update to play catch up for MAX times.
	public static final int MAX_UPDATES_BEFORE_RENDER 		= 7;
    
    //Rate for renders/s and expected time per render
	public static final double TARGET_GAME_RENDER_RATE 	= 24.0;
	public static final double TARGET_TIME_BETWEEN_RENDERS = 1000_000_000.0 / TARGET_GAME_RENDER_RATE;
    
    // interface lists
    private final List<ZDrawable> drawables = new ArrayList<>();
    private final List<ZEntity> entities  = new ArrayList<>();
    private final List<RealTime> realTimes = new ArrayList<>();
    private final EventQueue eventQueue = new EventQueue ();
    
    // instance of KeyBindManager
	private final KeyBindManager keyBindManager = new KeyBindManager(eventQueue.getEventAdder());
    
	// player
	private Player player;
	private Combat combat;
	private Enemy enemy;
	private Vendor vendor;
	private final WorldMap worldMap = new WorldMap ();
	
	// popup window
	private ZPopup popup;
	
    // gui containers
	private MainMenuViewer mainMenuViewer;
	private CharacterCreationViewer characterCreationViewer;
	private LoadGameViewer loadGameViewer;
	private CreditsViewer creditsViewer;
	
	private WorldMapViewer worldMapViewer;
	private TownViewer townViewer;
	
	private CombatViewer combatViewer;
	
	private CharacterStatisticsViewer characterStatisticsViewer;
	private QuestLogViewer questLogViewer;
	
	private List<Playable> players;
	
	private Encounter encounter;
	    
    /**
     * public Game
     * 
     * Settings for the Frame.
     */
    private Game () {
    	frame.setSize (FRAME_X, FRAME_Y);
    	frame.setResizable (false);    	
    	frame.setTitle ("Mercury 0.1");    	
    	frame.setVisible (true);
    	
    	// add listeners
    	frame.addMouseListener(this);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyBindManager);
        frame.addWindowListener (new WindowAdapter () {
			public void windowClosing(WindowEvent we){
				eventQueue.getEventAdder().add("exitGame");
			}
		});
    }
    
    /**
     * public static void main (String[] args)
     * @param args - System parameters, none are used.
     */
    public static void main (String[] args) {
        Game game = new Game();
        game.startGameLoop();
    }
    
    /**
     * private void startGameLoop()
     * Creates Thread gameLoopThread with private void gameLoop() nested inside.
     * Starts the new thread.
     */
    private void startGameLoop () {
    	eventQueue.getEventAdder().add("sceneMainMenu");
    	
        Thread gameLoopThread = new Thread(){
            public void run(){
                gameLoop();
            }
        };
        gameLoopThread.start();
    }
    
    /**
     * private void gameLoop()
     * The main game loop. Runs in the gameLoopThread thread.
     * Disposes the time in exec by giving update as much time as possible and allways render after
     * MAX_UPDATES_BEFORE_RENDER. IF time is left over yield it to other processes.
     */
    private void gameLoop () {
        double lastUpdateTime = System.nanoTime();
        double lastRenderTime = System.nanoTime();
        double currentTime;
        int updateCount;
        
        while (isRunning) {
            currentTime = System.nanoTime();
            updateCount = 0;
            
            // all events and execute them
            String currentEvent;
            while ((currentEvent = eventQueue.get()) != null ) {
            	eventHandler (currentEvent);
            }
            
            // while loop for update() in ZEntity interface. Has a catch up function if need be.
            while(((currentTime - lastUpdateTime) > TARGET_TIME_BETWEEN_UPDATES) && (updateCount < MAX_UPDATES_BEFORE_RENDER)){
                update ();
                lastUpdateTime += TARGET_TIME_BETWEEN_UPDATES;
                updateCount++;
            }

            render();
            lastRenderTime = currentTime;

            while (currentTime - lastUpdateTime < TARGET_TIME_BETWEEN_UPDATES && currentTime - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS){
                Thread.yield();
                try {
                	Thread.sleep(1);
                } catch(Exception e) {}
                currentTime = System.nanoTime();
            }
        }
    }
    
    /**
     * Renders all objects to be render using a bufferStrategy.
     */
    private void render () {
    	BufferStrategy bs = frame.getBufferStrategy();
    	
    	if (bs == null) {
    		frame.createBufferStrategy(3);
    		return;
    	}
    	
    	Graphics g = bs.getDrawGraphics();
    	g.clearRect(0, 0, FRAME_X, FRAME_Y);
    	
    	for (ZDrawable d : drawables) {
    		d.render(g);
    	}
    	
    	g.dispose ();
    	bs.show ();
    }
    
    /**
     * Updates all entities to be updated
     */
    private  void update () {
    	for (RealTime r : realTimes) {
    		r.update();
    	}
    }
    
    /**
     * Handles events by using s in reflection
     * @param s
     */
	private void eventHandler (String s) {
		System.out.println("Event: " + s);
		String[] a = s.split(",");
		
		switch (a.length){
			case 1:
				try {
					this.getClass().getMethod(s).invoke(this);
				} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
				break;
			
			case 2:	//Method with string parameter
				try {
					this.getClass().getMethod(a[0], String.class).invoke(this, a[1]); //ex: methodName = a[0]  stringArg = a[1],  methodName(stringArg);
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException
						| SecurityException e) {
					e.printStackTrace();
				}
			
			default:
				break;
		}		
	}
	
	private void removeContainer (ZContainer z) {
		if (z != null ) {
			drawables.remove(z);
			z.remove ();
		}
	}
	
	private void removeWorldMap () {
	    removeContainer(worldMapViewer);
		realTimes.remove(worldMapViewer);
		worldMapViewer = null;
    }
	
	/**
	 * 
	 */
	public void saveGame () {
		
	}
	
	/**
	 * 
	 */
	public void loadGame () {
		
	}
	
	/**
     * Method for popup window, removes all active events when turned on. Returns them when turned off.
     */
    public void popupWindow (String s) {
    	popup = new ZPopup (s, "ok", eventQueue.getEventAdder(), entities);
    	drawables.add(popup);
    }    
    public void popupWindowOff () {
    	for (ZEntity e : popup.remove ()) {
    		entities.add(e);
    	}
    	drawables.remove(popup);
    	popup = null;
    }
    
    /**
	 * Title Scene
	 */
	public void sceneMainMenu () {
		removeContainer (characterCreationViewer);
    	characterCreationViewer = null;
    	removeContainer (loadGameViewer);
    	loadGameViewer = null;
    	removeContainer (creditsViewer);
    	characterCreationViewer = null;
    	mainMenuViewer = new MainMenuViewer(null, 0, 0, eventQueue.getEventAdder(), entities);
    	drawables.add(mainMenuViewer);
    }
    
    /**
	 * Create Character Scene
	 */
    public void sceneCharacterCreation () {
    	removeContainer (mainMenuViewer);
    	mainMenuViewer = null;
    	characterCreationViewer = new CharacterCreationViewer(null, 0, 0, eventQueue.getEventAdder(), entities);
    	drawables.add(characterCreationViewer);
    }

    /**
	 * Load Game Scene
	 */
    public void sceneLoadGame () {
    	removeContainer (mainMenuViewer);
    	mainMenuViewer = null;
    	//loadGameViewer = new CharacterCreationViewer(null, 0, 0, eventQueue.getEventAdder(), entities);
    	//drawables.add (loadGameViewer);
    }
    
    /**
	 * Credits Scene
	 */
    public void sceneCredits () {
    	removeContainer (mainMenuViewer);
    	mainMenuViewer = null;
    	//creditsViewer = new CharacterCreationViewer(null, 0, 0, eventQueue.getEventAdder(), entities);
    	//drawables.add (creditsViewer);
    }
    
    /**
     * Creates the player, adds the first quest.
     */
    public void createCharacter (String playerName) {
    	removeContainer (characterCreationViewer);
    	characterCreationViewer = null;
    	player = new Player(playerName);
    	
    	GlobalStateManager.getInstance().updateWorldState("CharacterExists", "true");
    	GlobalStateManager.getInstance().updateWorldState("Location", "WORLD_MAP");
    	
    	eventQueue.getEventAdder().add("sceneWorldMap");
    }
    
    /**
	 * Render world map when returning from combat
	 */
    public void sceneWorldMap () {
    	switch (GlobalStateManager.getInstance().getCurrentState()) {
			case "COMBAT":
				removeContainer(combatViewer);
				combatViewer = null;
				break;
			case "TOWN":		
				removeContainer(townViewer);
				townViewer = null;
				break;
			case "WORLD_MAP":
				removeContainer(characterCreationViewer);
				characterCreationViewer = null;
			default:			
				break;
		}
		worldMapViewer = new WorldMapViewer(eventQueue.getEventAdder(), entities, worldMap);
		drawables.add(worldMapViewer);
		realTimes.add(worldMapViewer);
	}
    
    /**
     * Select zone from map. If you are currently on selected area, enter it.
     * @param area
     */
    public void selectArea(String area) {    	
		if (GlobalStateManager.getInstance().getWorldState("Location").equals(area)) {
			System.out.println("You entered: " + area);
			if (GlobalStateManager.getInstance().getWorldState("Location").startsWith("combat")) {
				sceneCombat();
			} else if (GlobalStateManager.getInstance().getWorldState("Location").startsWith("town")){
				sceneTown();
			}
		} else {
			if (!worldMap.selectArea(area)) {
				eventQueue.getEventAdder().add("popupWindow,Unreachable area!");
			}
		}
	}
	
    /**
     * 
     */
    public void sceneTown() {
    	switch(GlobalStateManager.getInstance().getCurrentState()){
    		case "WorldMap":
    			removeWorldMap ();
    			break;
    		case "inCombat_dead":
    			removeContainer(combatViewer);
    			combatViewer = null;
    			break;
    	}
    	townViewer = new TownViewer (entities,eventQueue.getEventAdder());
    	drawables.add(townViewer);
    }
    
    /**
     * 
     */
    public void sceneCombat(){
    	removeWorldMap ();
    	worldMapViewer = null;
    	players = new ArrayList<>();
    	player.getPC().getSpellBook().addSpell(SpellDatabase.getInstance().getSpells("fireball"));
    	player.getPC().getSpellBook().addSpell(SpellDatabase.getInstance().getSpells("heal"));
    	player.getPC().getInventory().addItem(ItemDatabase.getInstance().getItems("HealingPotion1"), 1);
    	players.add(player.getPC());
    	encounter = new Encounter("Victory", EnemyDatabase.getInstance().getEnemy("Big evil bengan"));
    	combat = new Combat(players, encounter, eventQueue.getEventAdder());
    	combatViewer = new CombatViewer(entities,eventQueue.getEventAdder(),player.getPC(),EnemyDatabase.getInstance().getEnemy("Big evil bengan"));
    	drawables.add(combatViewer);
    }
    
    /**
     * Toggles Character Statistics page on and off
     * 
     * Also turns off Quest Log page if on
     */
    public void characterStatisticsToggle () {
    	if (characterStatisticsViewer == null) {
    		characterStatisticsViewer = new CharacterStatisticsViewer(ImageDatabase.getInstance().getImage("bgQuestViewer.jpg"), 100, 75, eventQueue.getEventAdder(), entities, player.getPC());
    		drawables.add(characterStatisticsViewer);
    	} else {
    		for (ZEntity e : characterStatisticsViewer.getEntities ()) {
        		entities.add(e);
        	}
    		removeContainer (characterStatisticsViewer);
    		characterStatisticsViewer = null;
    	}
    }
    
    /**
     * Toggles Quest Log page on and off
     * 
     * Also turns off Character Statistics page if on
     */
	public void questLogToggle () {
    	if (questLogViewer == null) {
    		questLogViewer = new QuestLogViewer(ImageDatabase.getInstance().getImage("bgQuestViewer.jpg"), 100, 75, eventQueue.getEventAdder(), entities, player.getQuestLog());
    		drawables.add(questLogViewer);
    	} else {
    		for (ZEntity e : questLogViewer.getEntities ()) {
        		entities.add(e);
        	}
    		removeContainer (questLogViewer);
    		questLogViewer = null;
    	}
    }
	
	/**
	 * TEMP
	 */
	public void attack (){
		combat.attackCheck(player.getPC(), encounter.getEnemies().get(0));
	}
	public void spell(String spell){
		combat.spellCheck(player.getPC(),encounter.getEnemies().get(0),spell);
		combatViewer.clickedSpell();
	}
	public void nextTurn(){
		combat.nextTurn();
	}
	public void item(String item){
		combat.itemCheck(item);
		combatViewer.clickedItem();
	}
	public void retreat(){
		combat.retreatCheck();
	}
	public void spellMenu(){
		combatViewer.spellMenu();
	}
	public void itemMenu(){
		combatViewer.itemMenu();
	}
	public void addTextToLog(String s){
		combatViewer.addText(s);
	}
	/**
	 * 
	 * @param s name of item to buy
	 */
	public void buyItem(String s){
		ZPopup buyInfo;
		if(vendor.buyItem(s)){
			buyInfo = new ZPopup("You bought one "+ s, "ok",eventQueue.getEventAdder(), entities);
			townViewer.updateCurrency(player);
		}
		else{
			buyInfo = new ZPopup("You don't have enough money for a "+s, "ok",eventQueue.getEventAdder(), entities);
		}
		drawables.add(buyInfo);
	}
	/**
	 * method for entering vendor GUI
	 */
	public void enterVendor(){
		townViewer.updateCurrency(player);
		townViewer.enterVendor();
	}
	/**
	 * method for leaving vendor GUI
	 */
	public void leaveVendor(){
		townViewer.leaveVendor();
	}
	/**
	 * method for showing items in vendor
	 */
	public void enterVendorBuy(){
		vendor = new Vendor(player);
		townViewer.enterTownVendorBuy(vendor);
	}
    /**
	 * Exits Game
	 */
	public void exitGame () {
    	System.exit(0);
    }

    /**
     * When the mouse is clicked anywhere mouseClicked checks if an action is required by notifying all active entities
     */
	@Override
	public void mouseClicked(MouseEvent me) {
		for (ZEntity m : entities) {
    		m.onClick(me.getX(), me.getY());
    	}
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		for (ZEntity m : entities) {
    		m.onHover(me.getX(), me.getY());
    	}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
