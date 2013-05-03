package main;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

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
				
				byte[] payload = Arrays.copyOf(packet.getData(), packet.getLength());
				
	    	    String dataString = new String(payload);
	    	    processString(dataString);
	    	    
			} catch (Exception e) {
				System.out.println("Error, can't receive the packet via the socket, continuing to next iteration");
				e.printStackTrace();
			}    		
    	}
    }
    
    private void processString(String dataString) {
    	//System.out.println(dataString);
    	dataString = dataString.replace(',','.');
    	String[] fields = dataString.split(" ");
    	//for (int i=0;i<fields.length;i++) System.out.print(fields[i]+"*");
    	if (References.data.containsKey(fields[0])) {
    		References.data.get(fields[0]).updateHistory(Float.parseFloat(fields[1]), Float.parseFloat(fields[2]), Float.parseFloat(fields[3]));    		
    	}
    	else if (References.data.size() < 300) {
    		DataStruct newDS = new DataStruct(Float.parseFloat(fields[1]), Float.parseFloat(fields[2]), Float.parseFloat(fields[3]), fields[0]);
        	References.data.put(fields[0], newDS);	
    	}
    	

    }
    
    public void stopListening() {
    	this.running = false;
    }
}