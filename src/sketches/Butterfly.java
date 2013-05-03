package sketches;

import main.References;
import java.util.ArrayList;
import processing.core.PApplet;
import toxi.geom.*;
import main.DataStruct;


public class Butterfly extends EmptySketch {
	
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
	float theta= 0;
	float omega= (float) 3.1416 ;
	
	int limit;
	
	public void setup () {
		
	}
	
	public void draw() {
		

		limit =  Math.min(recCollection.size(), 30);
		parent.background(0);
		parent.smooth();
		dataAL.clear();
		for (String id : References.data.keySet()) {
			DataStruct ds = References.data.get(id);
		    dataAL.add(ds);
		}
		
		read();
		
		execute();
		
		//PApplet.println(recCollection.size());
		
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

		
	private void execute () {	
		
		  float Rad= 3;

		  for (int i=0; i<limit; i++) {
		    Rcvr theRec= (Rcvr) recCollection.get(i);


		    theRec.countx += theRec.acc.x/10;
		    theRec.county += theRec.acc.y/10;

		    float cenX= main.References.width/2;
		    float cenY= main.References.height/2- PApplet.sin(PApplet.radians(theRec.countx))*50;

		    parent.noFill();
		    parent.stroke(Math.abs(theRec.acc.z)*PApplet.sin(i/10)*100,parent.random(50,150));
		    parent.strokeWeight((float)0.5*Math.abs(theRec.acc.y));
		    parent.ellipse(cenX, cenY, References.height/5/limit*i*Rad, References.height/5/limit*i*Rad);
		    parent.ellipse(cenX, cenY, References.height/10/limit*i*Rad, References.height/10/limit*i*Rad);
		  }


		  for (int i=0; i<limit; i++) {
		    Rcvr theRec= (Rcvr) recCollection.get(i);

		    float rotX= PApplet.cos(PApplet.radians(theRec.county))*(References.height/5/limit*i*Rad);
		    float rotY= PApplet.sin(PApplet.radians(theRec.county))*(References.height/5/limit*i*Rad);
		    
		    float rotaX= PApplet.cos(PApplet.radians(theRec.county))*(References.height/10/limit*i*Rad);
		    float rotaY= PApplet.sin(PApplet.radians(theRec.county))*(References.height/10/limit*i*Rad);
		    
		    float cenX= References.width/2;
		    float cenY= References.height/2+ PApplet.sin(PApplet.radians(theRec.countx))*50;
		    float thick=PApplet.map(Math.abs(theRec.acc.z), 0, 2, 5, 30);
		    parent.noStroke();
		    parent.fill(20*i,1000/(i+1) , 300/(i+1), PApplet.map(Math.abs(theRec.acc.z), 0, 2, 50, 250));
		    parent.ellipse(cenX+rotX, cenY+rotY, thick*2, thick*2);
		    parent.ellipse(cenX-rotX, cenY+rotY, thick*2, thick*2);
		    parent.ellipse(cenX+rotaX, cenY-rotaY, thick, thick);
		    parent.ellipse(cenX-rotaX, cenY-rotaY, thick, thick);



		    for (int j=0; j<limit; j++) {
		      Rcvr othRec= (Rcvr) recCollection.get(j);

		      float rotOX= PApplet.cos(PApplet.radians(othRec.county))*(References.height/5/limit*j*2);
		      float rotOY= PApplet.sin(PApplet.radians(othRec.county))*(References.height/5/limit*j*2);
		      float rotRX= (float)(PApplet.cos(PApplet.radians(othRec.county))*(References.height/5/limit*j*2.05));
		      float rotRY= (float)(PApplet.sin(PApplet.radians(othRec.county))*(References.height/5/limit*j*2.05));

		      float rotaOX= PApplet.cos(PApplet.radians(othRec.county))*(References.height/10/limit*j*2);
		      float rotaOY= PApplet.sin(PApplet.radians(othRec.county))*(References.height/10/limit*j*2);
		      float rotaRX= (float)(PApplet.cos(PApplet.radians(othRec.county))*(References.height/10/limit*j*2.05));
		      float rotaRY= (float)(PApplet.sin(PApplet.radians(othRec.county))*(References.height/10/limit*j*2.05));

		      
		      
		      
		      
		      if (theRec.acc.y> othRec.acc.y-0.01 && theRec.acc.y < othRec.acc.y+0.01) {

		        parent.strokeWeight(1);
		        parent.stroke(Math.abs(othRec.acc.z)*50, 20*i, 20*j, Math.abs(theRec.acc.z)*50);
		        
		        parent.line(cenX+rotX, cenY+rotY, cenX+rotOX,cenY+rotOY);
		        parent.line(cenX-rotX, cenY+rotY, cenX-rotOX, cenY+rotOY);
		        
		        parent.line(cenX+rotaX, cenY+rotaY, cenX+rotaOX,cenY+rotaOY);
		        parent.line(cenX-rotaX, cenY+rotaY, cenX-rotaOX, cenY+rotaOY);
		        
		        parent.strokeWeight(3);
		        
		        parent.line(cenX+rotRX,cenY+rotRY, cenX+rotOX, cenY+rotOY);
		        parent.line(cenX-rotRX,cenY+rotRY, cenX-rotOX, cenY+rotOY);
		        
		        parent.line(cenX+rotaRX,cenY+rotaRY, cenX+rotaOX, cenY+rotaOY);
		        parent.line(cenX-rotaRX,cenY+rotaRY, cenX-rotaOX, cenY+rotaOY);
		      }
		    }
		  }
		
	}
}
