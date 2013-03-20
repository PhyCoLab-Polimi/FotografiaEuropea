package main;

import java.util.ArrayList;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class Program extends PApplet {
	
	static public void main(String args[]) {
		System.out.println("pre-main");
		PApplet.main(new String[] { "main.Program" });
		System.out.println("stopping server");
		//References.stopServer();
		System.out.println("exiting");
	}	
	
	public void setup() {
		//Initialize the reference to the PApplet
		References.parent = this;
		
		//Start the server listener
		References.initServer();	
		References.initSender();
		
		//INSERT SETUP AFTER THIS LINE
		// ------------------------------------------------
	}
	
	public void draw() {
		//Example of iterating over the data.
		
		//Get the number of data present:
		//int count = References.data.size();
		
		//Iteration: for every key (phone ID), pull the data object and print it out
		/*for (String id : References.data.keySet()) {
			//Pull the data object
			DataStruct ds = References.data.get(id);
			
			//Print it out
		    System.out.println(ds.toString());
		}*/
		
		//Conversion of hashmap in arrayList, commented out to avoid duplication:
		ArrayList<DataStruct> dataAL = new ArrayList<DataStruct>();
		for (String id : References.data.keySet()) {
			//Pull the data object
			DataStruct ds = References.data.get(id);
			
			//Add it to the list: 
			
			//--> NOTE: THE ORDER IS NOT GUARANTEED!!! <--
		    dataAL.add(ds);
		}
		
		//Print the arrayList content
		for (DataStruct ds: dataAL) {
			System.out.println(ds.toString());
		}
		
		
	}

}


