package sketches;

import processing.core.PApplet;
import main.DataStruct;
import main.References;

public class BWHistogram extends EmptySketch {
	
	private int numberOfLines = 2;
	
	private int [] colors = {
			0xFFFF0000, 
			0xFFFF7F00, 
			0xFFFFFF00,
			0XFF00FF00,
			0xFF0000FF,
			0xFF4B0082,
			0xFF8F00FF
		};
	
	private float firstStep = (float) (References.height * 0.15);
	
	private float maxHeight = (References.height-firstStep)/numberOfLines;
	
	private int alphaSat = 100;
	
	
	
	public void setup () {
		//parent.colorMode(PApplet.HSB);
		parent.colorMode(PApplet.RGB,255,255,255,255);
		parent.stroke(255,255,255,200);
	}
	
	public void draw() {
		
		parent.background(0);
		parent.rectMode(PApplet.CORNER);
		parent.colorMode(PApplet.RGB,255,255,255);
		parent.fill(0);
		parent.rect(0,0,References.width, References.height);
		

		Object[] data = References.data.values().toArray();
		
		for (int i=0; i<numberOfLines;i++) {
			//parent.fill(128,0,0,alphaSat+(i%2)*(255-alphaSat));
			//parent.fill(128,0,0,100+i*(255/numberOfLines));
			parent.fill(colors[i%colors.length], alphaSat+i*((255-alphaSat)/numberOfLines));
			
			int numberOfPoints = (data.length / numberOfLines);
			float stepX = References.width / (numberOfPoints+1);
			parent.beginShape();
			parent.vertex(0,firstStep + maxHeight * (1+i));
			for (int j=0; j<numberOfPoints;j++) {
				DataStruct ds = (DataStruct) data[i*numberOfPoints+j];
				float intX = PApplet.map(PApplet.abs(ds.x), 0, PApplet.max(PApplet.abs(ds.minX), ds.maxX), 0, 1);
				float intY = PApplet.map(PApplet.abs(ds.y), 0, PApplet.max(PApplet.abs(ds.minY), ds.maxY), 0, 1);
				float intZ = PApplet.map(PApplet.abs(ds.y), 0, PApplet.max(PApplet.abs(ds.minZ), ds.maxZ), 0, 1);
				float t = intX+intY+intZ;
				float intensity = PApplet.map( t, 0, 3, 0, 1);
				
				float height = PApplet.map(intensity, 0, 1, 0, 3*maxHeight);
				parent.vertex((j+1) * stepX, firstStep + maxHeight * (1+i) - height);
			}
			parent.vertex(References.width,firstStep + maxHeight * (1+i));
			parent.endShape();
		}
		
	}

}