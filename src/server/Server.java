package server;

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
import java.util.List;

/**
 * Server to store information regarding high scores in.
 * 
 * @author Daniel Edsinger 
 * @version 1.0
 * @since 2015-02-26
 */

public class Server
{
	private static final int port 			= 9990;
	private ServerSocket serverSocket;
    private final List<String> entries		= new ArrayList<>();
    
    /**
     * Constructor for Server
     * @throws IOException
     */
    public Server () throws IOException {
    	serverSocket = new ServerSocket(port);    	
    }

    /**
     * Starts the server and waits for appropriate connection
     * @throws IOException
     */
    public void execute () throws IOException {
        while (true) {
        	Socket client = serverSocket.accept();

			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String alt = br.readLine();
			
			if (alt.equals("s") ) {
				getScore(client, br);
			} else if (alt.equals("g")) {
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
        for (String element : entries) {
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
    private void getScore(Socket client, BufferedReader br) throws IOException {
    	try {	
            //System.out.println("Response from client:");
            String userInput = br.readLine();
            br.close();
            entries.add(userInput);
            Collections.sort(entries);
            Collections.reverse(entries);
            
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
    private void writeToFile() throws IOException {
    	PrintWriter out = new PrintWriter("Score.txt");
    	for ( String element : entries )
    		out.println(element);
    	out.close();
    }

    /**
     * Load the highscore-list from file
     * @throws IOException
     */
    public void readFromFile() throws IOException {    	
    	BufferedReader in = null;
    	if(( in = new BufferedReader(new FileReader("Score.txt")) ) != null) {
    		String line = in.readLine();
    		while (line!=null) {
    			entries.add(line);
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
        try {
            // initializing the Socket Server
        	System.out.println("Starting the socket server at port:" + port);
            Server server = new Server();
            
            // reading all existing entries
            server.readFromFile();
            
            // starting the server, waiting for incoming client
            server.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}