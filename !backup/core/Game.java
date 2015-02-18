package core;

import gui.ZButton;

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

/**
 * Game Class for project Mercury, holds main method.
 * 
 * @author	Anton Andrén & Martin Claesson & Mattias Benngård
 * @version	0.0 pre-alpha
 * @since	2015-02-16
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
    private final List<Entity> entities = new ArrayList<>();
    private final List<MouseObject> mouseObjects = new ArrayList<>();
    private final EventQueue eventQueue = new EventQueue ();
    
    // instance of keybindmanager
	private final KeyBindManager keyBindManager = new KeyBindManager(eventQueue.getEventAdder());
    
    // some variables
    private boolean questGuiShow = false;
    
    /**
     * public Game
     * 
     * Settings for the Frame.
     */
    public Game () {
    	frame.setSize (FRAME_X, FRAME_Y);
    	frame.setResizable (false);    	
    	frame.setTitle ("Mercury 0.1");    	
    	frame.setVisible (true);
    	
    	// add listeners
    	frame.addMouseListener(this);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyBindManager);
        frame.addWindowListener (new WindowAdapter () {
			public void windowClosing(WindowEvent we){
				System.exit(0);
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
    	eventQueue.getEventAdder().add("initializeGame");
    	
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
        
        ZButton b = new ZButton (eventQueue.getEventAdder(), "initializeGame", "Hello", 200, 100, 300, 50);
        drawables.add(b);
        mouseObjects.add(b);
        
        while (isRunning) {
            currentTime = System.nanoTime();
            updateCount = 0;
            
            // poll the first event and execute it
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
    
    private void render () {
    	BufferStrategy bs = frame.getBufferStrategy();
    	
    	if (bs == null) {
    		frame.createBufferStrategy(3);
    		return;
    	}
    	
    	Graphics g = bs.getDrawGraphics();
    	
    	for (Drawable d : drawables) {
    		d.render(g);
    	}
    	
    	g.dispose ();
    	bs.show ();
    }
    
    private  void update () {
    	for (Entity e : entities) {
    		e.update();
    	}
    }
    
	private void eventHandler (String s) {
		try {
			this.getClass().getMethod(s).invoke(this);
		} catch (NoSuchMethodException nsme) {
			System.out.println(s);
			nsme.printStackTrace();			
		} catch (IllegalAccessException iae) {
			iae.printStackTrace();
		} catch (InvocationTargetException ite) {
			ite.printStackTrace();
		}
	}
    
    public void initializeGame () {
    	System.out.println("Init...");
    }
    
    public void foo () {
    	System.out.println("bar");
    }
    
    /**
     * 
     */
    @SuppressWarnings("unused")
	private void questLogViewerToggle () {
    	questGuiShow = !questGuiShow;
    }

	@Override
	public void mouseClicked(MouseEvent me) {
		for (MouseObject m : mouseObjects) {
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