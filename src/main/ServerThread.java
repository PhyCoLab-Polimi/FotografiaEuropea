package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerThread extends Thread {
    DatagramSocket socket;
    private boolean running = true;
    
    public ServerThread() {
    	try {
			socket = new DatagramSocket(References.socketPort);
		} catch (SocketException e) {
			System.out.println("Error, can't start the socket!");
			e.printStackTrace();
		}
    }

    public void run() {
    	
		byte[] buffer = new byte[1024];
    	while (running) {
    		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);	
    	    try {
				socket.receive(packet);
	    	    String dataString = new String(packet.getData());
	    	    processString(dataString);
	    	    
			} catch (IOException e) {
				System.out.println("Error, can't receive the packet via the socket, continuing to next iteration");
				e.printStackTrace();
			}    		
    	}
    }
    
    private void processString(String dataString) {
    	String[] fields = dataString.split(" ");
    	if (References.data.containsKey(fields[0])) {    		
    		References.data.get(fields[0]).updateHistory(Float.parseFloat(fields[1]), Float.parseFloat(fields[2]), Float.parseFloat(fields[3]));    		
    	}
    	else {
    		DataStruct newDS = new DataStruct(Float.parseFloat(fields[1]), Float.parseFloat(fields[2]), Float.parseFloat(fields[3]), fields[0]);
        	References.data.put(fields[0], newDS);	
    	}
    	
    	//System.out.println(dataString);
    }
    
    public void stopListening() {
    	this.running = false;
    }
}