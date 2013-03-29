package sketches;

import processing.core.PApplet;
import main.DataStruct;
import main.References;

public class DiegoMartinoia extends EmptySketch{
	
	short colorColdR = 0;
	short colorColdG = 0;
	short colorColdB = 255;
	short colorHotR = 255;
	short colorHotG = 0;
	short colorHotB = 0;
	
	float maxAmplitude;
	float minRadius = 10;
	float maxRadius = 300;
	
	public void setup() {
		maxAmplitude = (float) Math.sqrt(5); 
	}
	
	public void draw() {
		
		//Style related stuff
		parent.pushStyle();
		parent.ellipseMode(PApplet.CENTER);
		parent.fill(0);
		parent.strokeWeight(10);
		
		//Black background
		parent.background(0);
		
		float totalAmplitude = 0;
		
		for (String id: References.data.keySet()) {
			
			//Random location
			float x = (float) (Math.random() * References.width);
			float y = (float) (Math.random() * References.height);
			
			DataStruct ds = References.data.get(id);
			float amplitude = (float) Math.sqrt(Math.pow(ds.x,2) + Math.pow(ds.y,2) + Math.pow(ds.z,2));
			
			totalAmplitude += amplitude;
			
			//Compute color as gradient between cold and hot using amplitude as measure
			int colorR = (int) PApplet.map(amplitude, 0, maxAmplitude, colorColdR, colorHotR);
			colorR = PApplet.constrain(colorR, 0, 255);
			
			int colorG = (int) PApplet.map(amplitude, 0, maxAmplitude, colorColdG, colorHotG);
			colorG = PApplet.constrain(colorG, 0, 255);
			
			int colorB = (int) PApplet.map(amplitude, 0, maxAmplitude, colorColdB, colorHotB);
			colorB = PApplet.constrain(colorB, 0, 255);
				
			//Compute radius
			float radius = PApplet.map(amplitude, 0, maxAmplitude, minRadius, maxRadius);
			//Re-compose the color
			int realColor = 0xEE000000 + 0x00010000 * colorR  + 0x00000100 * colorG + 0x00000001 * colorB;
			
			parent.stroke(realColor);
			parent.ellipse(x, y, radius, radius);
		}
		
		//Pop the style
		parent.popStyle();
		
		//Sleep a little, proportional to the totalAmplitude
		int nextSleep = (int) PApplet.map(totalAmplitude, 0, maxAmplitude * References.data.size(), 50, 0);
		/*try {
			Thread.sleep(nextSleep);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
}
