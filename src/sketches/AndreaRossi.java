package sketches;

import java.util.ArrayList;

import toxi.geom.*;

import main.DataStruct;
import main.References;

public class AndreaRossi extends EmptySketch {
	
	
	ArrayList<Pt> points;
	
	public void setup () {
		
		
		// creating background rotating points
		points = new ArrayList<Pt>();
		
		for(int i = 0; i < 1000; i++){			
			float radius = parent.random(200, 350);			
			float angle = parent.random(parent.PI *2);			
			Pt p = new Pt(radius, angle);			
			points.add(p);		
		}
		
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
		
		
		
		for(int i = 0; i < dataAL.size(); i++){
			DataStruct ds = dataAL.get(i);
			
			
			float massAdd = ds.x + ds.y + ds.z;
			
			Pt p = points.get(parent.floor(parent.random(points.size())));
			
			
			parent.noStroke();
			
			float stSize = parent.map(massAdd, -2, 2, 5, 50);
			stSize = parent.constrain(stSize, 0, 100);
			parent.fill(parent.random(0, 150), 0, parent.random(100, 255), stSize*10);
			
			parent.ellipse(p.loc.x + p.center.x, p.loc.y + p.center.y, stSize, stSize);			
		}
		
		
		
		for(Pt p : points){
			p.run();
		}
		
		
		/*
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
		
		*/
	}
	
	
	
	private class Pt {
		
		Vec3D loc;
		float theta;
		
		float dim;
		
		Vec3D center = new Vec3D(parent.width/2, parent.height/2, 0);
		
		Pt(float radius, float angle){
			
			float px = (float) (Math.sin(angle)* radius);
			float py = (float) (Math.cos(angle)* radius);
			
			loc = new Vec3D(px, py, 0);
			
			float r = (float) parent.random(1);
			
			theta = parent.random(-0.02f, 0.02f);
			
			dim = parent.random(4);
		
		}
		
		void run(){
					
			display();
			spinAround();		
			
		}
		
		void spinAround(){
			
			loc.rotateZ(theta);
		}
		
		void display(){
			parent.stroke(255);
			parent.strokeWeight(dim);
			parent.point(loc.x + center.x, loc.y + center.y);
		}	
		
	}	
	
}