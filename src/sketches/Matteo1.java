package sketches;

import main.References;
import java.util.ArrayList;
import processing.core.PApplet;
import toxi.geom.*;
import main.DataStruct;


public class Matteo1 extends EmptySketch {
	
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
	
	int limit;
	
	public void setup () {
		
	}
	
	public void draw() {
		
		scale+=0.1;

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
		
		  //float dim= PApplet.map(PApplet.sin(PApplet.radians(scale)),(float)-1,(float)1,(float)0.3,(float)0.5);
		  //float limitb=limit*(float)dim;
		  //float Rad= 3;

		  for (int i=0; i<limit; i++) {
		    Rcvr theRec= (Rcvr) recCollection.get(i);
		    theRec.count++;
		    
		    if(theRec.count>100)theRec.accCollection.remove(0);

		    theRec.countx += (PApplet.map((float)theRec.acc.x, (float)-2, (float)2, (float)-2, (float)2));
		    theRec.county += (PApplet.map((float)theRec.acc.y, (float)-2, (float)2, (float)-4, (float)4));
            
		    float rotX= PApplet.cos(PApplet.radians(theRec.countx));
		    float rotY= PApplet.sin(PApplet.radians(theRec.county));
		    
		    
		    float cenX= PApplet.map(rotX,-1,1,0,References.width);
		    float cenY= PApplet.map(rotY,-1,1,0,References.height/2);

		    theRec.accCollection.add(new Vec3D(cenX,cenY,0));
		    
		    
		    for(int k=1; k<theRec.accCollection.size(); k++){
		    
		    	Vec3D whtvr= (Vec3D) theRec.accCollection.get(k);
		    
		    	float cenXa= PApplet.map(whtvr.x,0,References.width,0,References.width/2);
		        float cenYa= PApplet.map(whtvr.y,0,References.height/2,0,References.height);
		    
		        Vec3D whtvrb= (Vec3D) theRec.accCollection.get(k-1);
		    
		        float cenXb= PApplet.map(whtvrb.x,0,References.width,0,References.width/2);
		        float cenYb= PApplet.map(whtvrb.y,0,References.height/2,0,References.height);
		        
		        parent.stroke(i*10, 20*k,i*theRec.count*20,600/(k+1));
		        parent.strokeWeight(PApplet.map(k,(float)0,(float)200,(float)2.5,(float)0.5));
		    
		        parent.line(whtvr.x,whtvr.y,0,whtvrb.x,whtvrb.y,0);
		        parent.line(cenXa,cenYa,0,cenXb,cenYb,0);
		        
		        parent.line(References.width-whtvr.x,whtvr.y,0,References.width-whtvrb.x,whtvrb.y,0);
		        parent.line(References.width-cenXa,cenYa,0,References.width-cenXb,cenYb,0);
		        
		        parent.line(whtvr.x,References.height-whtvr.y,0,whtvrb.x,References.height-whtvrb.y,0);
		        parent.line(cenXa,References.height-cenYa,0,cenXb,References.height-cenYb,0);
		        
		        parent.line(References.width-whtvr.x,References.height-whtvr.y,0,References.width-whtvrb.x,References.height-whtvrb.y,0);
		        parent.line(References.width-cenXa,References.height-cenYa,0,References.width-cenXb,References.height-cenYb,0);
		    
		    
		  }



		  
		
	}
}
}