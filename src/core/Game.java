package core;

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

import database.GameDataManager;
import character.Spell;
import combat.Combat;
import combat.Enemy;
import player.Player;
import player.Quest;
import gui.CharacterCreationViewer;
import gui.CombatViewer;
import gui.MainMenuViewer;
import gui.QuestLogViewer;
import gui.TownViewer;
import gui.WorldMapViewer;
import gui.ZContainer;

/**
 * Game Class for project Mercury, holds main method.
 * 
 * @author	Anton Andr�n & Mattias Benng�rd & Martin Claesson
 * @version	0.35 pre-alpha
 * @since	2015-02-16
 * 
 * Main Class for the mercury project.
 * 
 * public static void main (String[])
 * Instantiates Game and runs the gameLoop initiation
 * 
 */
public class Game implements MouseListener
{
	public static final int FRAME_X							= 800;
	public static final int FRAME_Y							= 600;
	
	private final Frame frame = new Frame ();
	
	private boolean isRunning = true;
    
    //Rate for updates/s and expected time per update.
    private static final double GAME_UPDATE_RATE			= 30.0;
    private static final double TARGET_TIME_BETWEEN_UPDATES = 1000_000_000.0 / GAME_UPDATE_RATE;
    
    //Allow update to play catch up for MAX times.
    private static final int MAX_UPDATES_BEFORE_RENDER 		= 7;
    
    //Rate for renders/s and expected time per render
    private static final double TARGET_GAME_RENDER_RATE 	= 24.0;
    private static final double TARGET_TIME_BETWEEN_RENDERS = 1000_000_000.0 / TARGET_GAME_RENDER_RATE;
    
    // interface lists
    private final List<Drawable> drawables = new ArrayList<>();
    private final List<RealTime> realTimes = new ArrayList<>();
    private final List<Entity> entities  = new ArrayList<>();
    private final EventQueue eventQueue = new EventQueue ();
    
    // instance of KeyBindManager
	private final KeyBindManager keyBindManager = new KeyBindManager(eventQueue.getEventAdder());
    
	// player
	private Player player;
	private Combat combat;
	private Enemy enemy;
	
    // gui containers
	private MainMenuViewer mainMenuViewer;
	private CharacterCreationViewer characterCreationViewer;
	//private LoadGameViewer loadGameViewer;
	//private ShowCreditsViewer showCreditsViewer;
	
	private WorldMapViewer worldMapViewer;
	private TownViewer townViewer;
	
	private CombatViewer combatViewer;
	
	private QuestLogViewer questLogViewer;
	    
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
            
            //while loop for update() in entity interface. Has a catch up function if need be.
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
    	
    	for (Drawable d : drawables) {
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
		try {
			this.getClass().getMethod(s).invoke(this);
		} catch (NoSuchMethodException nsme) {
			nsme.printStackTrace();			
		} catch (IllegalAccessException iae) {
			iae.printStackTrace();
		} catch (InvocationTargetException ite) {
			ite.printStackTrace();
		}
	}
	
	private void removeContainer (ZContainer z) {
		drawables.remove(z);
		z.remove ();
	}
    
	/**
	 * 
	 */
	public void sceneMainMenu () {
    	mainMenuViewer = new MainMenuViewer(eventQueue.getEventAdder(), null, 0, 0, entities);
    	drawables.add(mainMenuViewer);
    }
    
    /**
	 * 
	 */
    public void sceneCharacterCreation () {
    	removeContainer (mainMenuViewer);
    	mainMenuViewer = null;
    	characterCreationViewer = new CharacterCreationViewer(eventQueue.getEventAdder(), null, 0, 0, entities);
    	drawables.add(characterCreationViewer);
    }
    
    /**
	 * 
	 */
    public void sceneLoadGame () {
    	removeContainer (mainMenuViewer);
    	mainMenuViewer = null;
    	//characterCreationViewer = new CharacterCreationViewer(eventQueue.getEventAdder(), null, 0, 0, mouseObjects);
    	//drawables.add(characterCreationViewer);
    }
    
    /**
	 * 
	 */
    public void sceneShowCredits () {
    	removeContainer (mainMenuViewer);
    	mainMenuViewer = null;
    	//characterCreationViewer = new CharacterCreationViewer(eventQueue.getEventAdder(), null, 0, 0, mouseObjects);
    	//drawables.add(characterCreationViewer);
    }
    
    /**
     * 
     */
    public void createCharacter () {
    	removeContainer (characterCreationViewer);
    	player = new Player("John Doe");
    	player.getQuestLog().addQuest(new Quest("First Quest", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."));
    	player.getQuestLog().addQuest(new Quest("Second Quest", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."));
    	player.getQuestLog().addQuest(new Quest("Third Quest", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."));
    	GlobalStateManager.getInstance().updateWorldState("CharacterExists", "true");
    	eventQueue.getEventAdder().add("sceneWorldMap");
    }
    
	/**
	 * Render world map when returning from combat
	 */
    public void sceneWorldMap (){
    	switch(GlobalStateManager.getInstance().getCurrentState()){
			case "InCombat":	
				removeContainer(combatViewer);
				combatViewer = null;
				break;
			case "town":		
				removeContainer(townViewer);
				townViewer = null;
				break;
			default:			
				removeContainer(characterCreationViewer);
				characterCreationViewer = null;
				break;
		}
		worldMapViewer = new WorldMapViewer(eventQueue.getEventAdder(), entities);
		drawables.add(worldMapViewer);
	}
	    
    /**
     * 
     */
    public void sceneTown(){
    	switch(GlobalStateManager.getInstance().getCurrentState()){
    		case "WorldMap":
    			removeContainer(worldMapViewer);
    			break;
    		case "inCombat_dead":
    			removeContainer(combatViewer);
    			break;
    	}
    	townViewer = new TownViewer (entities,eventQueue.getEventAdder());
    	drawables.add(townViewer);
    }
    
    /**
     * 
     */
    public void sceneCombat(){
    	removeContainer(worldMapViewer);
    	worldMapViewer = null;
    	//if(zoneName.equals("a2")){
    		enemy = new Enemy("Bengan",2);
    	//}
    	combat = new Combat(player.getPC(), enemy, eventQueue.getEventAdder());
    	combatViewer = new CombatViewer(entities,eventQueue.getEventAdder(),player.getPC(),enemy);
    	drawables.add(combatViewer);
    }
    
    /**
     * 
     */
	public void questLogViewerToggle () {
    	if (questLogViewer == null) {
    		questLogViewer = new QuestLogViewer(eventQueue.getEventAdder(), GameDataManager.getInstance().getImage("bgQuestViewer.jpg"), 100, 75, entities, player.getQuestLog());
    		drawables.add(questLogViewer);
    	} else {
    		removeContainer (questLogViewer);
    		questLogViewer = null;
    	}
    }
	
	/**
	 * TEMP
	 */
	public void attack(){
		combat.playerAttack();
	}
	public void spell(){
		player.getPC().getSpellBook().addSpell(new Spell("fireball","eld av boll",10,"damage",20));
		combat.playerSpell("fireball");
	}
	
	/**
	 * 
	 */
	public void leaveTown () {
	}
    
    /**
	 *
	 */
	public void exitGame () {
    	System.exit(0);
    }

    /**
     * When the mouse is clicked anywhere mouseClicked checks if an action is required by notifying all active mouseObjects
     */
	@Override
	public void mouseClicked(MouseEvent me) {
		for (Entity m : entities) {
    		m.onClick(me.getX(), me.getY());
    	}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}