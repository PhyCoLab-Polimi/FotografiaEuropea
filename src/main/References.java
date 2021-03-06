package main;

import java.util.concurrent.ConcurrentHashMap;

import processing.core.PApplet;

public class References {
	
	public static PApplet parent;
	
	public static final int socketPort = 9930;
	
	public static ConcurrentHashMap<String, DataStruct> data;
	
	public static ServerThread serverThread;
	public static DataSenderFromFileThread senderThread;

	public static int width = 1024;
	public static int height = 350;
	
	public static int currentSketch = 0;
	
	public static void initServer() {
		References.data = new ConcurrentHashMap<String, DataStruct>();		
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
	
	public static void stopSending() {
		if (senderThread!= null) {
			senderThread.stopSending();
		}
	}
	
}
