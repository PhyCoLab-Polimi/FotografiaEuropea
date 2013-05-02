package main;

import java.io.FileWriter;
import java.util.Date;

import processing.core.PApplet;

import sketches.*;

@SuppressWarnings("serial")
public class Program extends PApplet {
	
	static public void main(String args[]) {
		System.out.println("pre-main");
		PApplet.main(new String[] {"main.Program" });
		//PApplet.main(new String[] {"--present", "main.Program" });
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
		References.initSender();
		
		//INSERT SETUP AFTER THIS LINE
		// ------------------------------------------------
		
		sketches = new EmptySketch[8];
		sketches[0] = new DiegoMartinoia2(); sketches[0].setup();
		sketches[1] = new AndreaRossi(); sketches[1].setup();
		sketches[2] = new AndreaRossi2(); sketches[2].setup();
		sketches[3] = new Gv_231_01(); sketches[3].setup();
		sketches[4] = new MartaZambelli(); sketches[4].setup();
		sketches[5] = new MatteoTaramelli(); sketches[5].setup();
		sketches[6] = new PaoloAlborghetti(); sketches[6].setup();
		sketches[7] = new TomasoBaj(); sketches[7].setup();
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
			this.fill(0);
			this.rect(0, 0, References.width, References.height);
			References.currentSketch = i;
		}
		else
			Gv_231_01.getKey(key);
	}

}


