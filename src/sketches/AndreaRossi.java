package sketches;

import java.util.ArrayList;

import main.DataStruct;
import main.References;

public class AndreaRossi extends EmptySketch {
	
	public void setup () {
		
	}
	
	public void draw() {
		
		parent.background(0);
		
		int count = References.data.size();
		
		//import data from hashmap to arraylist
		ArrayList<DataStruct> dataAL = new ArrayList<DataStruct>();
		for (String id : References.data.keySet()) {
			//Pull the data object
			DataStruct ds = References.data.get(id);
			
			//Add it to the list: 
			
			//--> NOTE: THE ORDER IS NOT GUARANTEED!!! <--
		    dataAL.add(ds);
		}
		
		
		parent.ellipseMode(2);
		
		for(int i = 0; i < dataAL.size(); i++){
			DataStruct ds = dataAL.get(i);
			
			// creating balls moving up and down, changing color and scaling according to acc values
			float massAdd = ds.x + ds.y + ds.z;
			
			float yPos = parent.map(ds.y, -2, 2, 200, 568);
			
			//float r = parent.map(ds.x, -2, 2, 100, 255);
			float g = parent.map(ds.y, -2, 2, 25, 255);
			float b = parent.map(ds.z, -2, 2, 25, 255);
			
			parent.noStroke();
			parent.fill(0, g, b);
			
			parent.ellipse(i*50 + 50, yPos, massAdd*7.5f, massAdd*7.5f);
			
			
			// creating polyline between points
			if(i != 0){
				DataStruct prevDs = dataAL.get(i - 1);
				
				float prevYPos = parent.map(prevDs.y, -2, 2, 200, 568);
				
				parent.stroke(255, 100);
				parent.strokeWeight(2);
				
				parent.line(i*50, prevYPos, i*50 + 50, yPos);
				
			}			
		}
	}
}