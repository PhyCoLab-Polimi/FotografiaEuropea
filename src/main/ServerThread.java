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
    	while (running) {
    		byte[] buffer = new byte[1024];
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
    	References.data.put(fields[0], new DataStruct(Float.parseFloat(fields[1]), Float.parseFloat(fields[2]), Float.parseFloat(fields[3])));
    }
    
    public void stopListening() {
    	this.running = false;
    }
}