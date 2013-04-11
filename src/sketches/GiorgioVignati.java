package sketches;

import main.References;


public class GiorgioVignati extends EmptySketch {
	public void setup () {
		parent.size(720, 720);
		parent.noCursor();
	}
	
	public void draw() {
		  colorMode(HSB, 360, 100, 100);
		  rectMode(CENTER); 
		  parent.noStroke();
		  parent.background(parent.mouseY/2, 100, 100);

		  parent.fill(360-parent.mouseY/2, 100, 100);
		  parent.rect(360, 360, parent.mouseX+1, parent.mouseX+1);
	}

} 