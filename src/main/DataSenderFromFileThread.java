package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class DataSenderFromFileThread extends Thread{
	
	BufferedReader[] files;
    DatagramSocket socket;
    
    public DataSenderFromFileThread() {
    	//Init the port
    	try {
			socket = new DatagramSocket(References.socketPort+1);
		} catch (SocketException e) {
			System.out.println("Error, can't start the socket!");
			e.printStackTrace();
		}
    	
    	//Open the files
    	files = new BufferedReader[19];
    	for (int i=0;i<files.length;i++) {
    		try {
    			files[i] = new BufferedReader(new FileReader("./simulation-data/"+i+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    }

    public void run() {
    	
    	for (int i=0;i<500;i++) {
    		try {
    			//Read next line and send the data string:
    			int id = 0;
    			for (int j=0; j<19;j++) {
    				
    				String s = files[j].readLine();
    				
    				String[] fields = s.split(",");
    				for (int k=0;k<1;k++) {
	    				String toSend = (id+" "+fields[0]+" "+fields[1]+" "+fields[2]);
	    				
	    				byte[] buf = toSend.getBytes();
	    				
	    				InetAddress address = InetAddress.getByName("localhost");
	    				
	    				DatagramPacket packet = new DatagramPacket(buf, buf.length, address, References.socketPort);
	    				
	    				socket.send(packet);
	    				
	    				id++;
    				}
    			}
    			
    			
    			//Sleep for 1 / 10th of second
				Thread.sleep(100);
			} catch (Exception e) {
				System.out.println("Something wrong with the data sending...");
				e.printStackTrace();
				continue;
			}
    	}
    }
    
    

}