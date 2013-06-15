import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
/*import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;*/

import javax.bluetooth.*;
import javax.microedition.io.*;
 
/**
* Class that implements an SPP Server which accepts single line of
* message from an SPP client and sends a single line of response to the client.
*/
public class SampleSPPServer {	
	public static void main(String[] args) throws IOException {
	       
        //display local device address and name
        LocalDevice localDevice = LocalDevice.getLocalDevice();
        System.out.println("Address: "+localDevice.getBluetoothAddress());
        System.out.println("Name: "+localDevice.getFriendlyName());
       
        SampleSPPServer sampleSPPServer=new SampleSPPServer();
        sampleSPPServer.startServer();       
    }
   
    //start server
    private void startServer() throws IOException{ 
        //Create a UUID for SPP
        UUID uuid = new UUID("1101", true);
        //Create the service url
        String connectionString = "btspp://localhost:" + uuid +";name=Sample SPP Server";       
        //open server url
        StreamConnectionNotifier streamConnNotifier = 
        		(StreamConnectionNotifier)Connector.open( connectionString );       
        //Wait for client connection
        System.out.println("\nServer Started. Waiting for clients to connect...");
        StreamConnection connection=streamConnNotifier.acceptAndOpen();
 
        RemoteDevice dev = RemoteDevice.getRemoteDevice(connection);
        System.out.println("Remote device address: "+dev.getBluetoothAddress());
        System.out.println("Remote device name: "+dev.getFriendlyName(true));
       
        //read string from spp client
        InputStream inStream = connection.openInputStream();
        System.out.println("waiting for input");
        
       while(true){
    	BufferedReader bReader=new BufferedReader(new InputStreamReader(inStream));    	      
        String lineRead=bReader.readLine();
        System.out.println(lineRead);
        switch(lineRead){
        case "up." :
        	Robot robot = null;
			try {
				robot = new Robot();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			robot.keyPress(KeyEvent.VK_UP);
			System.out.println("UP");
			break; 
			}      
        }
    }
}
    
       
