package sketches;


import processing.core.PApplet;
import processing.core.PFont;

import main.DataStruct;
import main.References;

public class DiegoMartinoia extends EmptySketch{
	
	String[] names = {"Gianni Berengo Gardin","Philip-Lorca diCorcia", "Henry Cartier-Bresson", "Jean Gaumy", "Martine Frank", "Martin Parr", "Jeanloup Sieff","Bert Hardy","Richard Avedon","Kenro Izu","Harold Eugene Doc Edgerton","Francesc Catal� Roca", "Izis Bidermanas", "Nobuyoshi Araky", "Joan Colom", "Daido Moriyama","Cristina Garcia Rodero","Misha Gordin","Misha Gordin","Robert Mapplethorpe","Robert Mapplethorpe","Wynn Bullock","John French","John Heartfield","David Seymour","Franco Fontana","Erwin Blumenfeld","Aaron Suskind","Frederick Sommer","Minor White","Frank Horvat","Tina Modotti","Otto Emil Hoppe","Elliott Erwitt","Max Dupain","Arthur Fellig","Lillian Bassman","Dmitri Baltermants","Alexadr Rodchenko","Man Ray","Mimmo Jodice","Andre Kertesz","Manuel Alvarez Bravo","Sergio Larrain","Bill Brandt","Philippe Halsman","Gordon Parks","James Nachtwey","Ray K. Metzker","Horacio Coppola","Jean Jacques Andr�","William Eugene Smith","Jan Saudek","William Klein","Rodney Smith","Pedro Luis Raota","Henry Peach Robinson","Horst P. Hors","Chema Madoz","Sally Man","Guy Bourdin","Alfred Stieglitz","Sebastiao Salgado","Isabel Mu�oz","Alberto Garcia-Alix","Jerry Uelsmann","Edward Weston"};
	PFont font;
	
	float fontSizeMin = 5;
	float fontSizeMax = 40;
	int counter = 0;
	
	
	public void setup() {
		  font = parent.createFont("American Typewriter",fontSizeMax);
		  //font = createFont("ArnhemFineTT-Normal",10);
		  parent.textFont(font,fontSizeMin);
		  parent.textAlign(PApplet.LEFT);
		
	}
	public void draw() {
		parent.fill(0,0,0,20);
		parent.rectMode(PApplet.CORNER);
		parent.rect(0,0,References.width,References.height);
		parent.fill(255,255,255,255);  
		
		
		for (String id: References.data.keySet()) {
			DataStruct ds = References.data.get(id);
			float x = PApplet.map(PApplet.abs(ds.x), 0, PApplet.max(PApplet.abs(ds.minX), ds.maxX), 0, 1);
			float y = PApplet.map(PApplet.abs(ds.y), 0, PApplet.max(PApplet.abs(ds.minY), ds.maxY), 0, 1);
			float z = PApplet.map(PApplet.abs(ds.y), 0, PApplet.max(PApplet.abs(ds.minZ), ds.maxZ), 0, 1);
			float t = x+y+z;
			float d = PApplet.map( t, 0, 3, fontSizeMin, fontSizeMax);
			int index = (int)parent.random(names.length);
			float angle = parent.random(-PApplet.PI, PApplet.PI);
			parent.textSize(d);
			
			parent.rotate(angle);
			parent.text(names[index],parent.random(References.width), parent.random(References.height));
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
