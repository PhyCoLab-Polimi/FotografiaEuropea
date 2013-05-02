package sketches;

import java.util.ArrayList;

import processing.core.PApplet;

import main.DataStruct;
import main.References;
import toxi.geom.Vec3D;

public class GreenLines extends EmptySketch{
	
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
	
	int lineCount = 0;
	
	public void setup () {
		parent.background(0);
	}
	
	public void draw() {
		
		parent.colorMode(PApplet.RGB, 255, 255, 255, 255);
		parent.rectMode(PApplet.CORNER);
		
		if(lineCount == 0 || lineCount % 500 == 0){
			parent.background(0);
		}
		
		lineCount++;
		
		
		
		parent.noStroke();
		parent.fill(0, 10);
		parent.rect(0, 0, parent.width, parent.height);
		
		
		dataAL.clear();
		for (String id : References.data.keySet()) {
			DataStruct ds = References.data.get(id);
		    dataAL.add(ds);
		}
		
		read();
		
		
				
		parent.ellipseMode(2);
		
		float totAcc = 0;
		
		float xDist = parent.width/(recCollection.size() + 1);
		
		for(int i = 0; i < recCollection.size(); i++){
			Rcvr theRec= (Rcvr) recCollection.get(i);
			
			// creating balls moving up and down, changing color and scaling according to acc values
			float massAdd = theRec.acc.x + theRec.acc.y + theRec.acc.z;
			totAcc += Math.abs(massAdd);
			
			float yPos = parent.map(theRec.acc.y, -2, 2, 0, References.height);
			
			//float r = parent.map(theRec.acc.x, -2, 2, 100, 255);
			float g = parent.map(theRec.acc.y, -2, 2, 25, 255);
			float b = parent.map(theRec.acc.z, -2, 2, 25, 255);
			
			parent.noStroke();
			parent.fill(0, g, b, 200);
			
			parent.ellipse(i*xDist + xDist, yPos, massAdd*7.5f, massAdd*7.5f);
			
			
			// creating polyline between points
			if(i != 0){
				Rcvr prevRec = (Rcvr) recCollection.get(i - 1);
				
				float prevYPos = parent.map(prevRec.acc.y, -2, 2, 0, References.height);
				
				parent.stroke(255, 100);
				parent.strokeWeight(2);
				
				parent.line(i*xDist, prevYPos, i*xDist + xDist, yPos);
				
			}			
		}
		
		
		if(totAcc/recCollection.size() > 1){			
			parent.stroke(255, parent.random(50, 200));			
			parent.strokeWeight((float) 0.5);			
			float yPos = parent.random(parent.height);			
			parent.line(0, yPos, parent.width, yPos);			
			
		}
	
		
	}
	
	
	private class Pt{
		
		Vec3D loc;
		
		Vec3D[] prevPos;
		
		
		Pt(){
			
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
