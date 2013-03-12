package main;

import java.util.HashMap;

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
		
		//Start the server listener
		References.initServer();		
	}
	
	public void draw() {
		background(255,0,0);
	}

}


