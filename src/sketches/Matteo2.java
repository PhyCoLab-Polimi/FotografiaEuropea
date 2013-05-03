package sketches;

import main.References;
import java.util.ArrayList;
import processing.core.PApplet;
import toxi.geom.*;
import main.DataStruct;


public class Matteo2 extends EmptySketch {
	
	private class Rcvr {
		String UDID;
		Vec3D acc= new Vec3D(0,0,0);
		int count=0;
		float countx=0;
		float county=0;
		ArrayList<Vec3D> accCollection;
		
		Rcvr(String _UDID, Vec3D _acc){
		    
		    UDID= _UDID;
		    acc= _acc;
		    accCollection= new ArrayList<Vec3D>();
		}
	}

	ArrayList<DataStruct> dataAL = new ArrayList<DataStruct>();
	ArrayList<Rcvr> recCollection= new ArrayList<Rcvr>();
	float theta= 0;
	float omega= (float) 3.1416 ;
	float scale= (float) 0;
	int countL=0;
	int limit;
	
	public void setup () {
		
	}
	
	public void draw() {
		
		scale+=0.1;
		countL++;
		limit =  Math.min(recCollection.size(), recCollection.size());
		parent.background(0);
		parent.smooth();
		dataAL.clear();
		for (String id : References.data.keySet()) {
			DataStruct ds = References.data.get(id);
		    dataAL.add(ds);
		}
		
		read();
		
		execute();
		
		
		
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
		

		  for (int i=0; i<limit; i++) {
		    Rcvr theRec= (Rcvr) recCollection.get(i);
		    theRec.count++;
		    
		    if(theRec.count>PApplet.map(recCollection.size(),0,500,200,20))theRec.accCollection.remove(0);

		    theRec.countx += (PApplet.map((float)theRec.acc.x, (float)-2, (float)2, (float)-2, (float)2));
		    theRec.county += (PApplet.map((float)theRec.acc.y, (float)-2, (float)2, (float)-0.8, (float)0.8));
            
		    float rotX= PApplet.cos(PApplet.radians(theRec.countx));
		    float rotY= PApplet.sin(PApplet.radians(theRec.county));
		    
		    
		    float cenX= PApplet.map(rotX,-1,1,0,References.width/2);
		    float cenY= PApplet.map(rotY,-1,1,0,References.height);

		    theRec.accCollection.add(new Vec3D(cenX,cenY,countL));
		    
		    
		    for (int j=0; j<recCollection.size(); j++) {
		    	Rcvr othRec= (Rcvr) recCollection.get(j);

		    	float rotOX= PApplet.cos(PApplet.radians(othRec.countx));
		        

		    	
		        float outa= PApplet.map(theRec.acc.z, -2, 2, -30, 30);
		        float outb= PApplet.map(othRec.acc.z, -2, 2, -30, 30);
		    	
		        int extr=0;
		        
		        
		        for (int u=0; u<theRec.accCollection.size(); u+=2) {
		            Vec3D va= (Vec3D) theRec.accCollection.get(u);
		        
		            for (int w=0; w<othRec.accCollection.size(); w+=2) {
		                Vec3D vb= (Vec3D) othRec.accCollection.get(w);
		    
		                //if (va.z == vb.z && theRec.acc.x> othRec.acc.x-0.1 && theRec.acc.x < othRec.acc.x+0.1 && w==u && i!=j) {
		                	if (va.z == vb.z && rotX> rotOX-0.05 && rotX < rotOX+0.05 ) {
		                    
		                    extr++;
		                
		                    parent.strokeWeight(parent.random((float)0.5,(float)1.5));
		                    
		                    parent.stroke(u, 6*u, 30*w, PApplet.map(PApplet.sin(PApplet.map(u,0,theRec.accCollection.size(),0,360)),0,theRec.accCollection.size(),10,20));

		                    parent.line(va.x, va.y, outa, vb.x, vb.y, outb);
		                    parent.line(References.width-va.x, va.y, outa, References.width-vb.x, vb.y, outb);
		                    parent.line(va.x, References.height-va.y, outa, vb.x, References.height-vb.y, outb);
		                    parent.line(References.width-va.x, References.height-va.y, outa, References.width-vb.x, References.height-vb.y, outb);
		                    
		                    
		                    if(extr>100) break;  
		                
		                
		                
		                
		                
		                
		                }
		            }
		        
		        
		        
		        
		        }
		        
		    	
		    }

		  
		
	}
}
}