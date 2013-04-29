package sketches;

import java.util.ArrayList;
import java.util.Iterator;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;
import sketches.DiegoMartinoia.Particle;
import main.DataStruct;
import main.References;

public class DiegoMartinoia extends EmptySketch{
	/*
	short colorColdR = 0;
	short colorColdG = 0;
	short colorColdB = 255;
	short colorHotR = 255;
	short colorHotG = 0;
	short colorHotB = 0;
	
	float maxAmplitude;
	float minRadius = 10;
	float maxRadius = 300;
	*/
	public class Particle {

		  PVector pos;
		  int ttl;
		  PImage img;
		  
		  public Particle( PVector pos, int ttl) {
		    this.pos = pos;
		    this.ttl = ttl;
		  }

		 

		  

	}
		  

	private ArrayList<Particle> particles;
	private float oldX; private float oldY;
	
	public void setup() {
		//maxAmplitude = (float) Math.sqrt(5); 
		particles = new ArrayList<Particle>();
		oldX = References.width/2;
		oldY = References.height/2;
	}
	
	public void draw() {
		float X = 0;
		float Y = 0;
		
		for (String id: References.data.keySet()) {
			DataStruct ds = References.data.get(id);
			X += PApplet.map(ds.x, ds.minX, ds.maxX, -1, 1) / 2*References.data.size();
			Y += PApplet.map(ds.y, ds.minY, ds.maxY, -1, 1) / 2*References.data.size();
		}
		
		if (Float.isNaN(X)) X = 0;
		if (Float.isNaN(Y)) Y = 0;
		oldX+=10*X; oldX = PApplet.constrain(oldX, 0, References.width);
		oldY-=10*Y; oldY = PApplet.constrain(oldY, 0, References.height);
		System.out.println("   "+X+" "+Y);
		System.out.println(oldX+" "+oldY);

		Iterator<Particle> iter = particles.iterator();
		while (iter.hasNext()) {
		    if (iter.next().ttl <=0) iter.remove();
		}
		
		for (int i=0; i<2;i++){
			particles.add(new Particle( new PVector( oldX, oldY), 5));
		}
		
		parent.background(0);
		parent.fill(255,255,255);
		parent.ellipseMode(PApplet.CENTER);
		
		for(Particle p: particles) {
			parent.ellipse(p.pos.x, p.pos.y,10,10);
		    p.ttl--;
		}
		
		
		
	}
}
