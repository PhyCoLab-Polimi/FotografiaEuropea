package sketches;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;

import toxi.geom.Vec3D;

import main.DataStruct;
import main.References;

public class PurpleUniverse extends EmptySketch {
	
	private class Rcvr {
		String UDID;
		Vec3D acc= new Vec3D(0,0,0);
		
		int pointID;
		
		Rcvr(String _UDID, Vec3D _acc, int _pointID){
		    
		    UDID= _UDID;
		    acc= _acc;
		    pointID = _pointID;
		}
	}
	

	ArrayList<DataStruct> dataAL = new ArrayList<DataStruct>();
	ArrayList<Rcvr> recCollection= new ArrayList<Rcvr>();
	
	
	ArrayList<Pt> points;
	
	float innerScale =  (float) 2.8; //controlla la dimensione delle geometrie interne
	
	float minAccScale = (float) 1;
	float maxAccScale = (float) 3;
	
	float numConnScale = (float) 2.5; //controlla il numero di connessioni tra i vari punti interni
	
	float transparency = (float) 7.5; //controlla la trasparenza dei cerchi purple
	
	
	public void setup () {
		
		
		// creating background rotating points
		points = new ArrayList<Pt>();
		
		for(int i = 0; i < 500; i++){			
			float radius = parent.random(References.height/3, (float) (References.height/1.5));			
			float angle = parent.random(PConstants.PI *2);			
			Pt p = new Pt(radius, angle);			
			points.add(p);		
		}
		
	}
	
	public void draw() {
		
		parent.background(0);
		parent.colorMode(PApplet.RGB, 255, 255, 255, 255);
		parent.rectMode(PApplet.CORNER);
		parent.ellipseMode(PApplet.CENTER);
		
		int count = References.data.size();
		
		dataAL.clear();
		for (String id : References.data.keySet()) {
			DataStruct ds = References.data.get(id);
		    dataAL.add(ds);
		}
		
		read();
		
		
		int limit =  Math.min(recCollection.size()+1, 100);
		
		
		if(limit > 1){
			
			for (int i=0; i < limit - 1; i++) {
			    Rcvr theRec= (Rcvr) recCollection.get(i);
						
				float massAdd = theRec.acc.x + theRec.acc.y + theRec.acc.z;
							
				Pt p = points.get(theRec.pointID);
				
				float accScale;
				
				if(massAdd > 0){
					accScale = PApplet.map(massAdd, 0, 6, minAccScale, maxAccScale);			
				}
				else{
					accScale = PApplet.map(massAdd, -6, 0, -maxAccScale, -minAccScale);
				}
				
				Vec3D innerPt = new Vec3D(p.loc.x/(innerScale*accScale), p.loc.y/(innerScale*accScale), p.loc.z/(innerScale*accScale));
									
				
				int numConnections = PApplet.floor(PApplet.map(massAdd, -2, 2, 3, (float) (limit/numConnScale)));
				
				numConnections = PApplet.constrain(numConnections, 3, PApplet.floor((float) (limit/numConnScale)));
				
				for (int j=0; j < limit - 1; j++) {
					if(i != j){
						Rcvr theRec2= (Rcvr) recCollection.get(j);
							
						float massAdd2 = theRec2.acc.x + theRec2.acc.y + theRec2.acc.z;
							
						float accScale2;
							
						if(massAdd2> 0){
							accScale2 = PApplet.map(massAdd2, 0, 6, minAccScale, maxAccScale);			
						}
						else{
							accScale2 = PApplet.map(massAdd2, -6, 0, -maxAccScale, -minAccScale);
						}
							
						Pt p2 = points.get(theRec2.pointID);
							
						Vec3D innerPt2 = new Vec3D(p2.loc.x/(innerScale*accScale2), p2.loc.y/(innerScale*accScale2), p2.loc.z/(innerScale*accScale2));
							
						parent.stroke(255, 50);
						parent.strokeWeight(1);
							
						parent.line(innerPt.x + p.center.x, innerPt.y + p.center.y, innerPt2.x + p.center.x, innerPt2.y + p.center.y);
	
					}			    		    
				}			
			}
		}
		
		
		
		float totAdd = 0;
		
		for (int i=0; i < limit - 1; i++) {
			Rcvr theRec= (Rcvr) recCollection.get(i);
			
			float massAdd = theRec.acc.x + theRec.acc.y + theRec.acc.z;
			totAdd += Math.abs(massAdd);
			
			Pt p = points.get(theRec.pointID);
			
			p.isActive = true;
			
			float accScale;
			
			if(massAdd > 0){
				accScale = PApplet.map(massAdd, 0, 6, minAccScale, maxAccScale);			
			}
			else{
				accScale = PApplet.map(massAdd, -6, 0, -maxAccScale, -minAccScale);
			}
			
			Vec3D innerPt = new Vec3D(p.loc.x/(innerScale*accScale), p.loc.y/(innerScale*accScale), p.loc.z/(innerScale*accScale));
			
			parent.noStroke();
			float stSize = PApplet.map(Math.abs(massAdd), 0, 6, 20, 50);
			stSize = PApplet.constrain(stSize, 20, 50);
			
			parent.fill(parent.random(0, 150), 0, parent.random(100, 255), (float) (stSize*transparency));
			
			parent.ellipse(innerPt.x + p.center.x, innerPt.y + p.center.y, stSize, stSize);
			
		}		
		
		
		totAdd = PApplet.map(totAdd, 0, 4*count, 0.935f, 1.22f);		
				
		for(Pt p : points){
			p.run(totAdd);
		}
	}
	
	
	
	private class Pt {
				
		Vec3D loc;
		float radius;
		float theta;
		float dim;
		
		Vec3D[] tail;
		int tailLenght = 5;
		
		boolean isActive = false;
		
		
		Vec3D center = new Vec3D(parent.width/2, parent.height/2, 0);
		
		Pt(float _radius, float angle){
			
			radius = _radius;
			
			float px = (float) (Math.sin(angle)* radius);
			float py = (float) (Math.cos(angle)* radius);
			
			loc = new Vec3D(px, py, 0);
			
			theta = parent.random(-0.06f, 0.02f);
			
			dim = parent.random(6);
			
			tail = new Vec3D[tailLenght];
			for(int i = 0; i < tailLenght; i++){
				tail[i] = new Vec3D(loc.x, loc.y, loc.z);
			}		
		
		}
		
		
		void run(float _scaling){
			
			if(!isActive){
				display();
				spinAround();
				constrainScaling(_scaling);
				if(parent.frameCount % 2 == 0){
					updateTail();
				}				
			}
			
					
			isActive = false;	
			
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
			else if(loc.magnitude() < radius - 500){
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

	
	
	
	
	//reading data and create new receiver objetcs
private void read () {
		
		if(dataAL.size()!=0){
		
		for(int i = 0; i < dataAL.size(); i++){
			DataStruct ds = dataAL.get(i);
			
			
			if(recCollection.size()==0){
				Rcvr nuRec= new Rcvr(ds.id,new Vec3D(ds.x,ds.y,ds.z), PApplet.floor(parent.random(points.size())));
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
		            Rcvr nuRec= new Rcvr(ds.id, new Vec3D(ds.x,ds.y,ds.z), PApplet.floor(parent.random(points.size())));    
		            recCollection.add(nuRec);
		          }
			}

		}

	}
	}	
}