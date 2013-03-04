package main;

import processing.core.PApplet;

@SuppressWarnings("serial")
public class Program extends PApplet {
	
	static public void main(String args[]) {
		System.out.println("pre-main");
		PApplet.main(new String[] { "main.Program" });
		System.out.println("post-main");
	}	
	
	public void setup() {
		
		//Initialize the reference to the PApplet
		References.parent = this;
		
	}
	
	public void draw() {}

}


