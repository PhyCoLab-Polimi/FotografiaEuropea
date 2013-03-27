package sketches;

import main.References;

public class AndreaRossi extends EmptySketch {
	
	public void setup () {
		
	}
	
	public void draw() {
		parent.background(0);
		parent.stroke(255);
		parent.strokeWeight(1);
		parent.fill(150);
		parent.ellipse(parent.random(1024), parent.random(768), 25, 25);
	}

}