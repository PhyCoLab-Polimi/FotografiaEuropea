package sketches;

import main.DataStruct;
import main.References;
import processing.core.PApplet;

public class TreeMap extends EmptySketch {

	private int numberOfRows = 20;
	private int numberOfColumns = 5;
	
	private float xStep;
	private float yStep;
	
	private int [] colors = {
			0xFFFF0000, 
			0xFFFF7F00, 
			0xFFFFFF00,
			0XFF00FF00,
			0xFF0000FF,
			0xFF4B0082,
			0xFF8F00FF
		};	
	
	public void setup(){
		
		parent.colorMode(PApplet.RGB,255,255,255,255);
		parent.stroke(255,255,255,200);
		parent.strokeWeight(3);
	}
	
	public void draw(){
		parent.colorMode(PApplet.RGB,255,255,255,255);
		parent.rectMode(PApplet.CORNER);
		parent.fill(0,0,0,255);
		parent.rect(0, 0, References.width, References.height);
		
	
		float total = 0;
		for (String id: References.data.keySet()) {
			
			DataStruct ds = References.data.get(id);
			
			float x = PApplet.map(PApplet.abs(ds.x), 0, PApplet.max(PApplet.abs(ds.minX), ds.maxX), 0, 1);
			float y = PApplet.map(PApplet.abs(ds.y), 0, PApplet.max(PApplet.abs(ds.minY), ds.maxY), 0, 1);
			float z = PApplet.map(PApplet.abs(ds.y), 0, PApplet.max(PApplet.abs(ds.minZ), ds.maxZ), 0, 1);
			float t = x+y+z;
			float d = PApplet.map( t, 0, 3, 0, 1);
			
			total+=d;
		}
		
		xStep = (float)References.width / numberOfColumns;
		yStep = (float)References.height / numberOfRows;
		
		int row = 0;
		int column = 0;
	
		int j=0;
		
		for (String id: References.data.keySet()) {
			parent.fill(colors[j%colors.length], 200);
			
			DataStruct ds = References.data.get(id);
			
			float x = PApplet.map(PApplet.abs(ds.x), 0, PApplet.max(PApplet.abs(ds.minX), ds.maxX), 0, 1);
			float y = PApplet.map(PApplet.abs(ds.y), 0, PApplet.max(PApplet.abs(ds.minY), ds.maxY), 0, 1);
			float z = PApplet.map(PApplet.abs(ds.y), 0, PApplet.max(PApplet.abs(ds.minZ), ds.maxZ), 0, 1);
			float t = x+y+z;
			float d = PApplet.map( t, 0, 3, 0, 1) / total;
			
			int numberOfSquares = (int) (numberOfRows * numberOfColumns * d) + 1;
			
			for (int i = 0; i<numberOfSquares; i++) {
				parent.rect(column*xStep, row*yStep, xStep, yStep);
				
				column++;
				if (column >= numberOfColumns) {column = 0; row++;}
				
			}
			j++;
		}
	}
	
}
