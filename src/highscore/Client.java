package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * 
 * @author Andreas B�ckevik & Daniel Edsinger
 * @version 0.8 alpha
 * @since 2015-02-26
 */
public class Client {

    private String hostname;
    private int port;
    Socket socketClient;

    public Client(String hostname){
        this.hostname = hostname;
        port = 9990;
    }

    /**
     * Sends name and score of character to server
     * @param name - Name of character
     * @param level - level of character
     */
    public void sendScore(String name, int level) {
    	try {      
    		connect();
            PrintWriter out = new PrintWriter(socketClient.getOutputStream(), true);
            out.println("s");
            out.flush();
            out.println(level+" "+name+",");
            out.close();

            
        } catch (UnknownHostException e) {
            System.err.println("Host unknown. Cannot establish connection");
        } catch (IOException e) {
            System.err.println("Cannot establish connection. Server may not be up."+e.getMessage());
        }
    }
    
    /**
     * Retrieves highscore from server
     * @return list - list of highscore, each index is a character with level
     */
    public String[] getScore() {
    	String[] list = null;
    	try {
    		connect();
    		PrintWriter out = new PrintWriter(socketClient.getOutputStream(), true);
            out.println("g");
    		
            //if successful, read response from server
    		String userInput;
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

            System.out.println("Response from server:");
            if ((userInput = stdIn.readLine()) != null) {
            	list = userInput.split(",");
            }
            
        } catch (UnknownHostException e) {
            System.err.println("Host unknown. Cannot establish connection");
        } catch (IOException e) {
            System.err.println("Cannot establish connection. Server may not be up."+e.getMessage());
        }
    	return list;
    }
    /**
     * Connects to the server
     * @throws UnknownHostException
     * @throws IOException
     */
    public void connect() throws UnknownHostException, IOException{
        System.out.println("Attempting to connect to "+hostname+":"+port);
        socketClient = new Socket(hostname,port);
        System.out.println("Connection Established");
    }
 /*   
    public static void main(String arg[]) throws UnknownHostException, IOException{
    	//Creating a Client object
    	Client client = new Client ("localhost");
        //send score to server
		client.sendScore("Daniel", 9);
		client.sendScore("Bangan", 7);
		client.sendScore("Olaf", 4);
		
		//get score from server
		int i =1;
		System.out.println(client.getScore().length);
		for(String s : client.getScore()) {
			System.out.println(""+ i + ": "+ s);
			i++;
		}
    }
   */
}