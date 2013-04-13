package sketches;

import java.util.ArrayList;

import processing.core.PApplet;

import toxi.geom.*;
import processing.opengl.*;

import main.DataStruct;
import main.References;

public class AndreaRossi extends EmptySketch {
	
	private class Rcvr {
		String UDID;
		Vec3D acc= new Vec3D(0,0,0);
		float countx=0;
		float county=0;
		
		Rcvr(String _UDID, Vec3D _acc){
		    
		    UDID= _UDID;
		    acc= _acc;
		}
	}

	ArrayList<DataStruct> dataAL = new ArrayList<DataStruct>();
	ArrayList<Rcvr> recCollection= new ArrayList<Rcvr>();
	
	
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
		
		dataAL.clear();
		for (String id : References.data.keySet()) {
			DataStruct ds = References.data.get(id);
		    dataAL.add(ds);
		}
		
		read();
		
		
		float totAdd = 0;
		
		for (int i=0; i<recCollection.size(); i++) {
		    Rcvr theRec= (Rcvr) recCollection.get(i);
			
			
			float massAdd = theRec.acc.x + theRec.acc.y + theRec.acc.z;
			totAdd += Math.abs(massAdd);
			
			Pt p = points.get(parent.floor(parent.random(points.size())));
			
			
			parent.noStroke();
			
			float stSize = parent.map(massAdd, -2, 2, 5, 50);
			stSize = parent.constrain(stSize, 0, 100);
			parent.fill(parent.random(0, 150), 0, parent.random(100, 255), stSize*20);
			
			parent.ellipse(p.loc.x + p.center.x, p.loc.y + p.center.y, stSize, stSize);			
		}
		
		totAdd = PApplet.map(totAdd, 0, 4*count, 0.935f, 1.22f);
		
		
		
		for(Pt p : points){
			p.run(totAdd);
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
		float radius;
		float theta;
		float dim;
		
		Vec3D[] tail;
		int tailLenght = 10;
		
		
		
		Vec3D center = new Vec3D(parent.width/2, parent.height/2, 0);
		
		Pt(float _radius, float angle){
			
			radius = _radius;
			
			float px = (float) (Math.sin(angle)* radius);
			float py = (float) (Math.cos(angle)* radius);
			
			loc = new Vec3D(px, py, 0);
			
			float r = (float) parent.random(1);
			
			theta = parent.random(-0.04f, 0.02f);
			
			dim = parent.random(4);
			
			tail = new Vec3D[tailLenght];
			for(int i = 0; i < tailLenght; i++){
				tail[i] = new Vec3D(loc.x, loc.y, loc.z);
			}
			
		
		}
		
		void run(float _scaling){
					
			display();
			spinAround();
			constrainScaling(_scaling);
			if(parent.frameCount % 1 == 0){
				updateTail();
			}
			
				
			
		}
		
		void updateTail(){
			for(int i = 0; i < tail.length - 1; i++){
				tail[i] = tail[i+1];
			}
			
			tail[tail.length - 1] = new Vec3D(loc.x, loc.y, loc.z);		
		}
		
		void constrainScaling(float scaling){
			if(loc.magnitude() > radius + 50){
				if(scaling < 1){
					loc.scaleSelf(scaling);
				}			
			}
			else if(loc.magnitude() < radius - 200){
				if(scaling > 1){
					loc.scaleSelf(scaling);
				}
			}
			else{
				loc.scaleSelf(scaling);
			}	
		}
		
		void spinAround(){			
			loc.rotateZ(theta);
		}
		
		void display(){
			
			float stAlpha = PApplet.map(loc.magnitude(), 0, 300, 50, 255);
			parent.stroke(255, stAlpha);
			parent.strokeWeight(dim);
			parent.point(loc.x + center.x, loc.y + center.y);
			
			
			float partDim = dim/tail.length;
			float partAlpha = stAlpha/tail.length;
			
			for(int i = 0; i < tail.length; i++){
				parent.stroke(255, partAlpha*i);
				parent.strokeWeight(partDim *i);
				parent.point(tail[i].x + center.x, tail[i].y + center.y);
			}
		}	
		
	}	
	
	
private void read () {
		
		if(dataAL.size()!=0){
		
		for(int i = 0; i < dataAL.size(); i++){
			DataStruct ds = dataAL.get(i);
			
			
			if(recCollection.size()==0){
				Rcvr nuRec= new Rcvr(ds.id,new Vec3D(ds.x,ds.y,ds.z));
				recCollection.add(nuRec);
			}
			else{
				
				boolean addNew=true;
				for (int j=0; j<recCollection.size(); j++) {
			          Rcvr checkRec= (Rcvr) recCollection.get(j);
			          if (ds.id.equals(checkRec.UDID)==true) {
			            addNew= false;
			            checkRec.acc= new Vec3D(ds.x,ds.y,ds.z);
			            break;
			          }
				}
		        if (addNew==true) {
		            Rcvr nuRec= new Rcvr(ds.id, new Vec3D(ds.x,ds.y,ds.z));    
		            recCollection.add(nuRec);
		          }
			}

		}

	}
	}

	
	
}