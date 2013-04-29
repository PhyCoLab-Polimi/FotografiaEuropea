package sketches;

import processing.core.PApplet;
import main.DataStruct;
import main.References;

public class TomasoBaj extends EmptySketch {
	
	private int baseSize = 20;
	private int randomSize = 5;
	

	
	public void setup () {
		
	}
	
	public void draw() {
		parent.fill(0,0,0,4);
		parent.rectMode(PApplet.CORNER);
		parent.rect(0,0,References.width, References.height);
		parent.colorMode(PApplet.HSB, 360, 100, 100, 100);
		parent.noStroke();
		parent.ellipseMode(PApplet.CENTER);
		
		for (String id: References.data.keySet()) {
			DataStruct ds = References.data.get(id);
			parent.fill(parent.color(PApplet.map(ds.z, ds.minZ, ds.maxZ, 0, 360), 50, 50, 100));
			int X = (int) PApplet.map(ds.x, ds.minX, ds.maxX, 0, References.width);
			int Y = (int) PApplet.map(ds.y, ds.minY, ds.maxY, 0, References.height);
			parent.ellipse(X,Y,baseSize+parent.random(-randomSize,+randomSize), baseSize+parent.random(-randomSize,+randomSize));
		}
		
	}

}