package sketches;

import processing.core.PApplet;
import main.References;


public class GiorgioVignati extends EmptySketch {
	public void setup () {
		// NON USARE IL COMANDO SIZE!!
		// parent.size(720, 720);
		parent.noCursor();
	}
	
	public void draw() {
		//USA parent. per i metodi di finestra (color, font etc)
		//usa PApplet. per le costanti (center, hsb, etc)
		  parent.colorMode(PApplet.HSB, 360, 100, 100);
		  parent.rectMode(PApplet.CENTER); 
		  parent.noStroke();
		  parent.background(parent.mouseY/2, 100, 100);

		  parent.fill(360-parent.mouseY/2, 100, 100);
		  
		  //Non fare mai riferimenti assoluti alle dimensioni, ma sempre proporzionali
		  //cosi se le dimensioni cambiano non devi stare a rifare tutto
		  parent.rect(parent.width/2, parent.height/2, parent.mouseX+1, parent.mouseX+1);
	}

} 