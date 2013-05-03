package sketches;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
import main.DataStruct;
import main.References;

public class Lines extends EmptySketch {
	
	private int maxStep = 100;
	
	private int [] colors = {
			0xFFFF0000, 
			0xFFFF7F00, 
			0xFFFFFF00,
			0XFF00FF00,
			0xFF0000FF,
			0xFF4B0082,
			0xFF8F00FF
		};
	

	HashMap<String, ArrayList<Point>> points;
	
	public void setup(){ points  = new HashMap<String, ArrayList<Lines.Point>>();}
	
	public void draw() {
		parent.noStroke();
		parent.colorMode(PApplet.RGB,255,255,255,255);
		parent.fill(0,0,0,20);
		parent.rectMode(PApplet.CORNER);
		parent.rect(0, 0, References.width, References.height);
		
		parent.strokeWeight(2);
		
		int j=0;
		for (String id: References.data.keySet()) {
			
			DataStruct ds = References.data.get(id);
			
			ArrayList<Point> ps;
			if(points.containsKey(id)) {
				ps = points.get(id);
			}
			else {
				ps = new ArrayList<Point>();
				points.put(id, ps);
			}
			
			float dx = PApplet.map(PApplet.abs(ds.x), 0, PApplet.max(PApplet.abs(ds.minX), ds.maxX), 0, 1);
			float dy = PApplet.map(PApplet.abs(ds.y), 0, PApplet.max(PApplet.abs(ds.minY), ds.maxY), 0, 1);
			float dz = PApplet.map(PApplet.abs(ds.y), 0, PApplet.max(PApplet.abs(ds.minZ), ds.maxZ), 0, 1);
			float t = dx+dy+dz;
			float d = PApplet.map( t, 0, 3, 0, 1) * maxStep;
			
			Point p = new Point();
			if (ps.size() > 0) {
				float angle = parent.random(-PApplet.PI, PApplet.PI);
				p.x = (int) (ps.get(ps.size()-1).x + d * PApplet.cos(angle));
				p.y = (int) (ps.get(ps.size()-1).y + d * PApplet.sin(angle));
			}
			ps.add(p);
			
			parent.stroke(colors[j%colors.length], 200);
			for (int i = 0; i<ps.size()-1;i++) {
				parent.line(ps.get(i).x, ps.get(i).y, ps.get(i+1).x, ps.get(i+1).y);
			}
				
			j++;
			
			if (Math.random() < 1 - Math.exp( - (double)(ps.size() / 20))) {
				points.remove(id);
			}
		}
		
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