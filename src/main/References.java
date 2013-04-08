package main;

import java.util.HashMap;

import processing.core.PApplet;

public class References {
	
	public static PApplet parent;
	
	public static final int socketPort = 9930;
	
	public static HashMap<String, DataStruct> data;
	
	public static ServerThread serverThread;
	public static DataSenderFromFileThread senderThread;

	public static int width = 1024;
	public static int height = 768;
	
	public static int currentSketch = 0;
	
	public static void initServer() {
		References.data = new HashMap<String, DataStruct>();		
		References.serverThread = new ServerThread();
		serverThread.start();
	}
	
	public static void stopServer() {
		if (serverThread!= null) {
			serverThread.stopListening();
		}
	}
	
	public static void initSender() {
		References.senderThread =  new DataSenderFromFileThread();
		senderThread.start();
	}
}
