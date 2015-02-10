import java.security.acl.LastOwnerException;
import javax.swing.JFrame;

/**
 * Game Class for project xxx 
 * @Author	Anton Andrén & Martin claesson
 * @Date	2015-02-04
 * @Version	0.0 pre-alpha
 * 
 * public Game()
 * Constructor for Game Class
 * 
 * public Static Void Main()
 * Project main function. Instantiates Game and calls startGameLoop()
 * 
 * public void render()
 * help method for testing private void gameLoop()
 * 
 * public void update()
 * help method for testing private void gameLoop()
 */
public class Game extends JFrame{
    
private static final long serialVersionUID = 4143225983730697466L;

private boolean isRunning = true;
    
    //Rate for updates/s and expected time per update.
    private static final double GAME_UPDATE_RATE= 30.0;
    private static final double TARGET_TIME_BETWEEN_UPDATES = 1000000000 / GAME_UPDATE_RATE;
    //Allow update to play catch up for MAX times.
    private static final int MAX_UPDATES_BEFORE_RENDER = 7;
    //Rate for renders/s and expected time per render
    private static final double TARGET_GAME_RENDER_RATE = 24;
    private static final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_GAME_RENDER_RATE;
    
    // time played saved in seconds
    private double timePlayed;
    
    //for fps checking etc. Not relevant for the game itself.
    private int frameCount = 0;
    private int updatesCount = 0;

    public Game(){
        timePlayed = 0;
    //Init jframe. Logic. GUI. UpdateInterfaces??
    }
    
    public static void main(){
        Game game = new Game();
        game.startGameLoop();
    }
    
    /**
     * Private void startGameLoop()
     * Creates Thread gameLoopThread with private void gameLoop() nested inside.
     * Starts the new thread.
     */
    private void startGameLoop(){
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
    private void gameLoop(){
   
        //Last update time during exec.
        double lastUpdateTime = System.nanoTime();
        //Last render time during exec.
        double lastRenderTime = System.nanoTime();
        //current time at the start of loop according to JVM.
        double currentTime;
        //update counter to throttle updates from playing catchup too long
        int updateCount;
        
        //For fps checking. Not relevant for game itself.
        int previousSecond = (int) (lastUpdateTime / 1000000000);
        //int fps = 0;
        int thisSecond = 0;
        
        while(isRunning){
            currentTime = System.nanoTime();
            updateCount = 0;
            
            //while loop for update() in entity interface. Has a catch up function if need be.
            while(((currentTime - lastUpdateTime) > TARGET_TIME_BETWEEN_UPDATES) && (updateCount < MAX_UPDATES_BEFORE_RENDER)){
                update();
                lastUpdateTime += TARGET_TIME_BETWEEN_UPDATES;
                updateCount++;
            }

            render();
            lastRenderTime = currentTime;
            
            //method for checking if update is playing catch up and fps. In code for testing... remove later.
            thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > previousSecond){
                //System.out.println("New Second " + thisSecond + " Framecount (renders): " + frameCount + " Update Count: " + updatesCount);
                frameCount = 0;
                previousSecond = thisSecond;
                timePlayed++;
            }
            
            //Game time update
            timePlayed += ((System.nanoTime() - currentTime) / 1000000000);
            String s = getTimePlayed();
            System.out.println(s);
            // ALT 1 for sleep.
            while(currentTime - lastUpdateTime < TARGET_TIME_BETWEEN_UPDATES && currentTime - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS){
                Thread.yield();
                try{Thread.sleep(1);}
                catch(Exception e){}
                currentTime = System.nanoTime();
            }
            //ALT 2 for sleep.
            //double timeToGive = TARGET_TIME_BETWEEN_UPDATES - (lastUpdateTime - currentTime);
            //if(timeToGive > 0);
            //    sleep(TimeToGive);
        }
    }
    
    public void render(){
        frameCount++;
    }
    
    public void update(){
        updatesCount++;
        }
    
    /**
     * private void getTimePlayed()
     * converts the played time from seconds to days/hours/minutes/seconds
     */
    private String getTimePlayed(){
    return ("days: " + Integer.toString((int)(timePlayed / 60 / 60 / 24)) + 
    " Hours: " + Integer.toString((int)(timePlayed / 60 / 60 % 24)) + 
    " Minutes: " + Integer.toString((int)(timePlayed / 60 % 60)) + 
    " Seconds: " + Integer.toString((int)(timePlayed % 60)));
    }
}