package core;

import java.awt.Canvas;
import java.awt.Dimension;
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

import database.EncounterDatabase;
import database.EnemyDatabase;
import database.ImageDatabase;
import database.ItemDatabase;
import database.QuestDatabase;
import database.SpellDatabase;
import database.ZoneDatabase;
import combat.Combat;
import combat.Encounter;
import player.Playable;
import player.Player;
import zlibrary.ZContainer;
import zlibrary.ZDrawable;
import zlibrary.ZEntity;
import zlibrary.ZPopup;
import gui.ArenaEntranceViewer;
import gui.CharacterCreationViewer;
import gui.CharacterStatisticsViewer;
import gui.CombatViewer;
import gui.CreditsViewer;
import gui.EndSceneViewer;
import gui.HighScoreViewer;
import gui.LoadGameViewer;
import gui.MainMenuViewer;
import gui.QuestLogViewer;
import gui.TownViewer;
import gui.WorldMapViewer;
import highscore.Client;
import vendor.Vendor;

/**
 * Game Class for project Mercury, holds main method. Instantiates itself.
 * 
 * @author	Anton Andrén		<@>
 * @author	Mattias Benngård	<mbengan@gmail.com>
 * @author 	Andreas Bäckevik	<@>
 * @author	Martin Claesson		<@>
 * @author 	Daniel Edsinger		<@>
 * @version	0.9					<2015-05-25>
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
 *	- Update Event Handling to not Lag Behind
 *	- Synchronize AWT Events
 *	- Cleanup Reflection Code
 *	- Change Frame layout to be consistent with pixel 0,0 to FRAME_X,FRAME_Y
 */

public class Game extends Canvas implements Runnable, MouseListener
{
	private static final long serialVersionUID = 6545236699133411291L;
	public static final int FRAME_X							= 800;
	public static final int FRAME_Y							= 600;
	public static final String TITLE						= "Mercury";
	
	private Frame frame;
	
	public Thread thread;
	private boolean isRunning = true;
    
    //Rate for updates/s and expected time per update.
	public static final double GAME_UPDATE_RATE				= 30.0;
	public static final double TARGET_TIME_BETWEEN_UPDATES = 1000_000_000.0 / GAME_UPDATE_RATE;
    
    //Allow update to play catch up for MAX times.
	public static final int MAX_UPDATES_BEFORE_RENDER 		= 7;
    
    //Rate for renders/s and expected time per render
	public static final double TARGET_GAME_RENDER_RATE 		= 24.0;
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
	private Vendor vendor;
	
	// popup window
	private ZPopup popup;
	
    // gui containers
	private MainMenuViewer mainMenuViewer;
	private CharacterCreationViewer characterCreationViewer;
	private LoadGameViewer loadGameViewer;
	private CreditsViewer creditsViewer;
	private HighScoreViewer highScoreViewer;
	private ArenaEntranceViewer arenaEntranceViewer;
	
	private WorldMapViewer worldMapViewer;
	private TownViewer townViewer;
	
	private CombatViewer combatViewer;
		
	private CharacterStatisticsViewer characterStatisticsViewer;
	private QuestLogViewer questLogViewer;
	
	private EndSceneViewer endSceneViewer;
	
	private List<Playable> players;
	
    /**
     * private void gameLoop()
     * The main game loop. Runs in the gameLoopThread thread.
     * Disposes the time in exec by giving update as much time as possible and allways render after
     * MAX_UPDATES_BEFORE_RENDER. IF time is left over yield it to other processes.
     */
    public void run () {
        double lastUpdateTime = System.nanoTime();
        double lastRenderTime = System.nanoTime();
        double currentTime;
        int updateCount;
        
        eventQueue.getEventAdder().add("sceneMainMenu");
        
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
    	BufferStrategy bs = getBufferStrategy();
    	
    	if (bs == null) {
    		createBufferStrategy(3);
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
	private void eventHandler (String event) {
		System.out.println("Event: " + event);
		String method = (event.indexOf(",") == -1) ? event : event.substring(0, event.indexOf(","));
		String arguments = (event.indexOf(",") == -1) ? null : event.substring(event.indexOf(",")+1);
		
		try {
			if (arguments == null) {
				this.getClass().getMethod(method).invoke(this); 
			} else {
				this.getClass().getMethod(method, String.class).invoke(this, arguments); 
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
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
	public void saveGame (String filename) {
		GlobalStateManager.getInstance().save(player, filename);
	}
	
	/**
	 * 
	 */
	public void loadGame (String filename) {
		
	}
	
	/**
     * Method for popup window, removes all active events when turned on. Returns them when turned off.
     */
    public void popupWindow (String s) {
    	popup = new ZPopup (s, "ok", eventQueue.getEventAdder(), entities);
    	drawables.add(popup);
    }    
    public void popupWindowOff () {
    	if (popup != null) {
	    	for (ZEntity e : popup.remove ()) {
	    		entities.add(e);
	    	}
	    	drawables.remove(popup);
	    	popup = null;
	    }
    }
    
    /**
	 * Title Scene
	 */
	public void sceneMainMenu () {
		removeContainer(highScoreViewer);
		highScoreViewer = null;
		removeContainer (characterCreationViewer);
    	characterCreationViewer = null;
    	removeContainer (loadGameViewer);
    	loadGameViewer = null;
    	removeContainer (creditsViewer);
    	characterCreationViewer = null;
    	mainMenuViewer = new MainMenuViewer(eventQueue.getEventAdder(), entities);
    	drawables.add(mainMenuViewer);
    }
    
    /**
	 * Create Character Scene
	 */
    public void sceneCharacterCreation () {
    	removeContainer (mainMenuViewer);
    	mainMenuViewer = null;
    	characterCreationViewer = new CharacterCreationViewer (null, 0, 0, eventQueue.getEventAdder(), entities);
    	drawables.add(characterCreationViewer);
    }

    /**
	 * Load Game Scene
	 */
    public void sceneLoadGame () {
    	removeContainer (mainMenuViewer);
    	mainMenuViewer = null;
    	loadGameViewer = new LoadGameViewer (null, 0, 0, eventQueue.getEventAdder(), entities);
    	drawables.add (loadGameViewer);
    }
    
    /**
	 * Credits Scene
	 */
    public void sceneCredits () {
    	removeContainer (mainMenuViewer);
    	mainMenuViewer = null;
    	creditsViewer = new CreditsViewer (eventQueue.getEventAdder(), entities);
    	drawables.add (creditsViewer);
    }
    
    
    public void sceneHighScore(){
    	removeContainer (mainMenuViewer);
    	mainMenuViewer = null;
    	highScoreViewer = new HighScoreViewer(null, 0, 0, eventQueue.getEventAdder(), entities);
    	drawables.add(highScoreViewer);
    }
    /**
     * Creates the player, adds the first quest.
     */
    public void createCharacter (String playerName) {
    	removeContainer (characterCreationViewer);
    	characterCreationViewer = null;
    	player = new Player(playerName);
    	
    	eventQueue.getEventAdder().add("addQuest,First Quest");
    	eventQueue.getEventAdder().add("addItem,HealingPotion2,5");
    	eventQueue.getEventAdder().add("addItem,HealingPotion1,5");
    	player.getPC().getSpellBook().addSpell(SpellDatabase.getInstance().getSpells("fireball"));
    	player.getPC().getSpellBook().addSpell(SpellDatabase.getInstance().getSpells("heal"));
    	
    	GlobalStateManager.getInstance().updateWorldState("CHARACTER_IS_ALIVE", "TRUE");
    	GlobalStateManager.getInstance().updateWorldState("LOCATION", "Town0001");
    	
    	eventQueue.getEventAdder().add("sceneWorldMap");
    }
    
    /**
     * Adds an active quest to the player
     */
    public void addQuest (String questName) {
    	player.getQuestLog().addQuest(QuestDatabase.getInstance().getQuest(questName));
    }
    
    /**
     * Adds an active quest to the player
     */
    public void completeQuest (String questName) {
    	player.getQuestLog().completeQuest(QuestDatabase.getInstance().getQuest(questName));
    }
    
    /**
     * 
     */
    public void addItem (String itemInfo) {
    	String name = itemInfo.substring(0, itemInfo.indexOf(","));
    	int quantity = Integer.parseInt(itemInfo.substring(itemInfo.indexOf(",")+1));
    	player.getPC().getInventory().addItem(ItemDatabase.getInstance().getItem(name), quantity);
    }
    
    /**
	 * Render world map when returning from combat
	 */
    public void sceneWorldMap () {
		worldMapViewer = new WorldMapViewer(eventQueue.getEventAdder(), entities);
		drawables.add(worldMapViewer);
		realTimes.add(worldMapViewer);
	}
    
    /**
     * Select zone from map. If you are currently on selected area, enter it.
     * @param area
     */
    public void selectArea(String area) {
    	// enter area
    	if (GlobalStateManager.getInstance().getWorldState("LOCATION").equals(area)) {
	    	if (GlobalStateManager.getInstance().getWorldState(area).equals("CLEAR")) {
	    		eventQueue.getEventAdder().add("popupWindow,Completed zone!");
	    	} else {
	    		System.out.println("You entered: " + area);
		    	removeWorldMap ();	
		    	eventQueue.getEventAdder().add(ZoneDatabase.getInstance().getZone(area).getEvent());
	    	}
	    	return;
    	}    	
    	
    	// unreachable
    	if (!ZoneDatabase.getInstance().getZone(GlobalStateManager.getInstance().getWorldState("LOCATION")).isConnected(ZoneDatabase.getInstance().getZone(area))) {
			eventQueue.getEventAdder().add("popupWindow,Unreachable area!");
			return;
    	}
    	
    	// move to area
    	if (!GlobalStateManager.getInstance().getWorldState("LOCATION").equals(area)) {
    		GlobalStateManager.getInstance().updateWorldState("LOCATION", area);
    		System.out.println("Change globalWorldState from: " + GlobalStateManager.getInstance().getWorldState("LOCATION") + " To: "+ area);
    		return;
    	}
    	
    	eventQueue.getEventAdder().add("popupWindow,Completed area!");
    }
	
    /**
     * 
     */
    public void sceneTown() {
    	townViewer = new TownViewer (entities,eventQueue.getEventAdder());
    	drawables.add(townViewer);
    }
    
    /**
     * 
     */
    public void leaveTown () {
    	removeContainer(townViewer);
    	townViewer = null;
    	eventQueue.getEventAdder().add("sceneWorldMap");
    }
    
    /**
     * Default method to catch victory in combat
     */
    public void defaultVictory () {
    	GlobalStateManager.getInstance().updateWorldState(GlobalStateManager.getInstance().getWorldState("LOCATION"), "CLEAR");
		
    	removeContainer(combatViewer);
    	combatViewer = null;
    	
    	sceneWorldMap ();
    }
    
    /**
     * End Game Scene
     */
    public void darkLordDefeated () {
    	endSceneViewer = new EndSceneViewer (eventQueue.getEventAdder(), entities, player.getPC().getName(), player.getPC().getLevel());
    	drawables.add(endSceneViewer);
    }
    
    public void updateToServer () {
    	removeContainer (endSceneViewer);
    	endSceneViewer = null;
    	Client client = new Client ("localhost");
    	client.sendScore(player.getPC().getName(), player.getPC().getLevel());
    	eventQueue.getEventAdder().add("sceneWorldMap");
    }
    
    /**
     * Default method to catch defeat in combat
     */
    public void defaultLose () {
    	player.getPC().healVital("Health", player.getPC().getMaxOfVital("Health"));
    	player.getPC().healVital("Energy", player.getPC().getMaxOfVital("Energy"));
    	
    	removeContainer(combatViewer);
    	combatViewer = null;
    	
    	GlobalStateManager.getInstance().updateWorldState("LOCATION", "Town0001");
    	
    	sceneTown ();
    }
    
    /**
     * 
     */
    public void sceneCombat (String encounter){
    	players = new ArrayList<>();
    	players.add(player.getPC());
    	combat = new Combat (
    			players,
    			new Encounter(EncounterDatabase.getInstance().getEncounter(encounter)),
    			eventQueue.getEventAdder(),
    			EncounterDatabase.getInstance().getEncounter(encounter).getWinEvent(),
    			EncounterDatabase.getInstance().getEncounter(encounter).getLoseEvent()
    		);
    	combatViewer = new CombatViewer(entities,eventQueue.getEventAdder(),player.getPC(), EncounterDatabase.getInstance().getEncounter(encounter));
    	drawables.add(combatViewer);
    }
    
    public void runFromBattle(){
    	removeContainer(combatViewer);
    	combatViewer = null;
    	
    	eventQueue.getEventAdder().add("sceneWorldMap");
    }
    
    /**
     * Loads the arenaEntranceViewer
     */
    public void sceneArenaEntranceViewer(){
    	arenaEntranceViewer = new ArenaEntranceViewer(eventQueue.getEventAdder(), entities);
    	drawables.add(arenaEntranceViewer);
    }
    
    /**
     * Removes the arenaEntranceViewer and loads the arena battle in the combatViewer.
     */
    public void enterArena (){
    	removeContainer(arenaEntranceViewer);
    	arenaEntranceViewer = null;
    	eventQueue.getEventAdder().add("sceneCombat,Arena");
    }
    
    /**
     * Removes the arenaEntranceViewer and loads the worldMapViewer
     */
    public void leaveArena(){
    	removeContainer(arenaEntranceViewer);
    	arenaEntranceViewer = null;
    	eventQueue.getEventAdder().add("sceneWorldMap");
    }
    
    /**
     * Increases the gladiator's level (by one) in the EnemyDatabase and load the next battle.
     */
    public void arenaWon(){
		EnemyDatabase.getInstance().getEnemy("Gladiator").setLevel(EnemyDatabase.getInstance().getEnemy("Gladiator").getLevel() + 1);
		
		removeContainer(combatViewer);
		combatViewer = null;
		
    	eventQueue.getEventAdder().add("sceneCombat,Arena");
    }
    
    /**
     * Resets the gladiator to level one, resotres players health and energy and updates the current LOCATION and loads the TownViewer.
     */
    public void arenaLost(){
		EnemyDatabase.getInstance().getEnemy("Gladiator").setLevel(1);
    	
    	player.getPC().healVital("Health", player.getPC().getMaxOfVital("Health"));
    	player.getPC().healVital("Energy", player.getPC().getMaxOfVital("Energy"));
    	
    	removeContainer(combatViewer);
    	combatViewer = null;
    	
    	GlobalStateManager.getInstance().updateWorldState("LOCATION", "Town0001");
    	
    	eventQueue.getEventAdder().add("sceneTown");
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
		combat.attackCheck(player.getPC());
	}
	public void spell(String spell){
		combat.spellCheck(player.getPC(),spell);
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
	public void updateVisuals(){
		combatViewer.updateVisuals();
	}
	
	/**
	 * 
	 * @param s name of item to buy
	 */
	public void buyItem(String s){
		if(vendor.buyItem(s)){
			townViewer.updateCurrency(player);
			eventQueue.getEventAdder().add("popupWindow,You bought one "+ s);
		} else {
			eventQueue.getEventAdder().add("popupWindow,You don't have enough money for a "+s);
		}
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
	 * 
	 */
    private void initWindow() {
    	setPreferredSize(new Dimension(FRAME_X, FRAME_Y));
    	setMinimumSize(new Dimension(FRAME_X, FRAME_Y));
    	setMaximumSize(new Dimension(FRAME_X, FRAME_Y));
    	
    	frame = new Frame (TITLE);
    	frame.setResizable (false);    	
    	frame.add(this);
    	frame.pack();
    	frame.setVisible (true);
    	
    	
    	// add listeners
    	addMouseListener(this);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyBindManager);
        frame.addWindowListener (new WindowAdapter () {
			public void windowClosing(WindowEvent we){
				eventQueue.getEventAdder().add("exitGame");
			}
		});		
	}
    
    /**
     * public Game
     */
    public Game () {
    	initWindow ();
    	
    	thread = new Thread(this);
    	thread.start();
    }

	/**
     * public static void main (String[] args)
     * @param args - System parameters, none are used.
     */
    public static void main (String[] args) {
        new Game();
    }

    /**
     * When the mouse is clicked anywhere mouseClicked checks if an action is required by notifying all active entities
     */
	@Override
	public void mouseClicked(MouseEvent me) {}

	@Override
	public void mouseEntered(MouseEvent me) {
		for (ZEntity m : entities) {
    		m.onHover(me.getX(), me.getY());
    	}
	}

	@Override
	public void mouseExited(MouseEvent me) {}

	@Override
	public void mousePressed(MouseEvent me) {
		for (ZEntity m : entities) {
    		m.onClick(me.getX(), me.getY());
    	}
	}

	@Override
	public void mouseReleased(MouseEvent me) {}
}
