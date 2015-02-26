import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author Daniel Edsinger & Andreas Bäckevik
 * @version 0.8 alpha
 * @since 2015-02-26
 */
public class Server {
    
    private ServerSocket serverSocket;
    private int port;
    private ArrayList<String> list;
    
    public Server(int port) {
        this.port = port;
        list = new ArrayList<>();
        try {
			readFromFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * Starts the server and waits for appropriate connection
     * @throws IOException
     */
    public void start() throws IOException {
        System.out.println("Starting the socket server at port:" + port);
        serverSocket = new ServerSocket(port);
        
        //Listen for clients. Block till one connects
        while(true) {
        	//System.out.println("Waiting for clients...");
        	Socket client = serverSocket.accept();

			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String alt = br.readLine();
			
			if( alt.equals("s") ) {
				getScore(client, br);
			}else if( alt.equals("g")) {
				sendScore(client);
			}
        }
    }
   /**
    * Sends every highscore to client
    * @param client - The client who connected
    * @throws IOException
    */
    private void sendScore(Socket client) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        //Write from file
        for(String element : list) {
        	writer.write(element);
        }
        writer.flush();
        writer.close();
    }
    /**
     * Retrieves a highscore add from client
     * @param client - The client who connected
     * @param br - a buffered reader
     * @throws IOException
     */
    private void getScore(Socket client, BufferedReader br) throws IOException{
    	try {	
            //System.out.println("Response from client:");
            String userInput = br.readLine();
            br.close();
            list.add(userInput);
            Collections.sort(list);
            Collections.reverse(list);
            
            writeToFile();

        } catch (UnknownHostException e) {
            System.err.println("Host unknown. Cannot establish connection");
        } catch (IOException e) {
            System.err.println("Cannot establish connection. Client not connected."+e.getMessage());
        }
    }
    
    /**
     * Save the highscore-list to file
     * @throws IOException
     */
    public void writeToFile() throws IOException {
    	PrintWriter out = new PrintWriter("Score.txt");
    	for ( String element : list )
    		out.println(element);
    	out.close();
    } 
    /**
     * Load the highscore-list from file
     * @throws IOException
     */
    public void readFromFile() throws IOException{
    	
    	BufferedReader in = null;
    	if(( in = new BufferedReader(new FileReader("Score.txt")) ) != null) {
    		String line = in.readLine();
    		while(line!=null) {
    			list.add(line);
    			line = in.readLine();
    		}
    	}
    	in.close();
    }

    /**
    * Creates a SocketServer object and starts the server.
    *
    * @param args
    */
    public static void main(String[] args) {
        // Setting a default port number.
        int portNumber = 9990;
        
        try {
            // initializing the Socket Server
            Server socketServer = new Server(portNumber);
            socketServer.start();
            
            } catch (IOException e) {
            e.printStackTrace();
        }
    }
}