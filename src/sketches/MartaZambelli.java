package sketches;

import processing.core.PApplet;
import main.DataStruct;
import main.References;

public class MartaZambelli extends EmptySketch {
	
	private int numberOfLines = 5;
	private int maxHeight = References.height/numberOfLines;
	
	public void setup () {
		
	}
	
	public void draw() {
		
		parent.background(0);
		parent.rectMode(PApplet.CORNER);
		parent.colorMode(PApplet.RGB,255,255,255);
		parent.fill(128);
		parent.rect(0,0,References.width, References.height);
		

		Object[] data = References.data.values().toArray();
		
		for (int i=0; i<numberOfLines;i++) {
			parent.fill(255-(i%2)*255);
			
			int numberOfPoints = (data.length / numberOfLines);
			float stepX = References.width / (numberOfPoints+1);
			parent.beginShape();
			parent.vertex(0,maxHeight * (1+i));
			for (int j=0; j<numberOfPoints;j++) {
				DataStruct ds = (DataStruct) data[i*numberOfPoints+j];
				float intX = PApplet.map(PApplet.abs(ds.x), 0, PApplet.max(PApplet.abs(ds.minX), ds.maxX), 0, 1);
				float intY = PApplet.map(PApplet.abs(ds.y), 0, PApplet.max(PApplet.abs(ds.minY), ds.maxY), 0, 1);
				float intZ = PApplet.map(PApplet.abs(ds.y), 0, PApplet.max(PApplet.abs(ds.minZ), ds.maxZ), 0, 1);
				float t = intX+intY+intZ;
				float intensity = PApplet.map( t, 0, 3, 0, 1);
				
				float height = PApplet.map(intensity, 0, 1, 0, maxHeight*2);
				parent.vertex((j+1) * stepX, maxHeight * (1+i) - height);
			}
			parent.vertex(References.width,maxHeight * (1+i));
			parent.endShape();
		}
		
	}

}