package main;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;

import processing.core.PApplet;

import sketches.*;

@SuppressWarnings("serial")
public class Program extends PApplet {
	
	static public void main(String args[]) {
		System.out.println("pre-main");
		//PApplet.main(new String[] {"main.Program" });
		PApplet.main(new String[] {"--present", "main.Program" });
		System.out.println("post-main");
	}
	
	EmptySketch[] sketches;
	
	public void setup() {
		//Initialize the reference to the PApplet
		References.parent = this;
		
		//Set up size.
		References.parent.size(References.width, References.height, PApplet.OPENGL);
		
		//Start the server listener
		References.initServer();	
		//References.initSender();
		
		//INSERT SETUP AFTER THIS LINE
		// ------------------------------------------------
		
		sketches = new EmptySketch[10];
		sketches[0] = new Butterfly(); sketches[0].setup();
		sketches[1] = new BWHistogram(); sketches[1].setup();
		sketches[2] = new Circles(); sketches[2].setup();
		sketches[3] = new Fireworks(); sketches[3].setup();
		sketches[4] = new GreenLines(); sketches[4].setup();
		sketches[5] = new Lines(); sketches[5].setup();
		sketches[6] = new Names(); sketches[6].setup();
		sketches[7] = new PurpleUniverse(); sketches[7].setup();
		sketches[8] = new TreeMap(); sketches[8].setup();
		sketches[9] = new YellowShapes(); sketches[8].setup();
	}
	
	public void draw() {
		System.out.println(References.data.size()+" devices connected");
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
		/*ArrayList<DataStruct> dataAL = new ArrayList<DataStruct>();
		for (String id : References.data.keySet()) {
			//Pull the data object
			DataStruct ds = References.data.get(id);
			
			//Add it to the list: 
			
			//--> NOTE: THE ORDER IS NOT GUARANTEED!!! <--
		    dataAL.add(ds);
		}*/
		
		//Print the arrayList content
		//for (DataStruct ds: dataAL) {
			//System.out.println(ds.toString());
		//}
		
		
		//CHANGE THE NUMBER HERE TO REFLECT YOUR SKETCH!
		try {
			sketches[References.currentSketch].draw();
		}
		catch (Exception e) {
			System.out.println("ERROR IN MAIN! Writing log for "+ e.toString());
			e.printStackTrace();
			
			try {
				Date d = new Date();
				FileWriter f = new FileWriter("log_"+d.toString()+".txt");
				f.write(e.toString());
				f.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("ERROR IN WRITING LOG. Aborting logging.");
			}
			
		}
	}
	
	public void keyPressed() {
		int i = Character.getNumericValue(key);
		if (i>=0 && i< sketches.length) {
			this.rectMode(PApplet.CORNER);
			this.noStroke();
			this.fill(0,0,0,255);
			this.rect(0, 0, References.width, References.height);
			sketches[i].setup();
			References.currentSketch = i;
		}
		else if (key==' ') {
			/*ArrayList<String> dss = new ArrayList<String>();
			Date now = new Date();
			
			for (DataStruct ds: References.data.values()) {
				if ((now.getTime() - ds.timestamp.getTime())/1000 > 5*60) {
					dss.add(ds.id);
				}
			}
			for (String id: dss) {
				References.data.remove(id);
			}*/
			try {
				if (References.senderThread!=null && References.senderThread.isSending()) {
					References.stopSending();
					Thread.sleep(500); 
				}
				References.initSender();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Error with sender restart");
				e.printStackTrace();
			}
			
			
		}
		else
			Fireworks.getKey(key);
	}

}


