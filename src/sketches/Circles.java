package sketches;

import java.util.HashMap;

import processing.core.PApplet;
import main.DataStruct;
import main.References;

public class Circles extends EmptySketch {
	
	private int baseSize = 35;
	private int randomSize = (int) (parent.random(0,15));
	
	private int addx;
	private int addy;
	
	HashMap<String, Point> points;
	
	public void setup () {
		points = new HashMap<String, Point>();
	}
	
	public void draw() {
		
		randomSize = (int) (parent.random(0,15));
		parent.noStroke();
		parent.fill(0,0,0,3);
		parent.rectMode(PApplet.CORNER);
		parent.rect(0,0,References.width, References.height);
		parent.colorMode(PApplet.HSB, 360, 100, 100, 100);
		parent.ellipseMode(PApplet.CENTER);
		
		int limitCount = 0;
		for (String id: References.data.keySet()) {
			DataStruct ds = References.data.get(id);
			Point p;
			if(points.containsKey(id)) {
				p = points.get(id);
			}
			else {
				p = new Point();
				points.put(id, p);
			}
			

			
			parent.fill(parent.color(PApplet.map(ds.x+ds.y, ds.minZ, ds.maxZ, 0, 360), 70, 85, 15));
			int X = (int) PApplet.map(ds.x, ds.minX, ds.maxX, 0, 50);//References.width);
			int Y = (int) PApplet.map(ds.y, ds.minY, ds.maxY, 0, 50);//References.height);
			baseSize = X + Y;
			if (limitCount<100)
			parent.ellipse(	PApplet.constrain(p.x+X+addx, 0, References.width), 
							PApplet.constrain(p.y+Y+addy, 0, References.height),
							baseSize+randomSize, 
							baseSize+randomSize);
			
			limitCount++;
		}

		addx = addx + (int) parent.random(-2,2);
		addy = addy + (int) parent.random(-2,2);

		
	}
	
	private class Point{
		public int x; 
		public int y;
		public Point() {
			x = (int) parent.random(0, References.width);
			y = (int) parent.random(0, References.height); 
		}

	}


}