package sketches;


import processing.core.PApplet;
import processing.core.PFont;

import main.DataStruct;
import main.References;

public class Names extends EmptySketch{
	
	String[] names = {"Gianni Berengo Gardin","Philip-Lorca di Corcia", "Henry Cartier-Bresson", "Jean Gaumy", "Martine Frank", "Martin Parr", "Jeanloup Sieff","Bert Hardy","Richard Avedon","Kenro Izu","Harold Eugene Doc Edgerton","Francesc Català Roca", "Izis Bidermanas", "Nobuyoshi Araky", "Joan Colom", "Daido Moriyama","Cristina Garcia Rodero","Misha Gordin","Misha Gordin","Robert Mapplethorpe","Robert Mapplethorpe","Wynn Bullock","John French","John Heartfield","David Seymour","Franco Fontana","Erwin Blumenfeld","Aaron Suskind","Frederick Sommer","Minor White","Frank Horvat","Tina Modotti","Otto Emil Hoppe","Elliott Erwitt","Max Dupain","Arthur Fellig","Lillian Bassman","Dmitri Baltermants","Alexadr Rodchenko","Man Ray","Mimmo Jodice","Andre Kertesz","Manuel Alvarez Bravo","Sergio Larrain","Bill Brandt","Philippe Halsman","Gordon Parks","James Nachtwey","Ray K. Metzker","Horacio Coppola","Jean Jacques Andrè","William Eugene Smith","Jan Saudek","William Klein","Rodney Smith","Pedro Luis Raota","Henry Peach Robinson","Horst P. Hors","Chema Madoz","Sally Man","Guy Bourdin","Alfred Stieglitz","Sebastiao Salgado","Isabel Muñoz","Alberto Garcia-Alix","Jerry Uelsmann","Edward Weston"};
	String[] specialNames = 
			{
				"Giulia Agostini",
				"Samanta Braga",
				"Annalisa Brambilla",
				"Annalisa Califano",
				"Francesca Cirilli",
				"Giuseppe de Mattia",
				"Massimiliano Gatti",
				"Giuseppe Maldera",
				"Francesco Merlini",
				"Guido Meschiari",
				"Valentina Scaletti",
				"Anna Trento"
			};
	PFont font;
	
	float fontSizeMin = 5;
	float fontSizeMax = 40;
	int maxNames = 10;
	
	int frameCount = 1;
	
	public void setup() {
		  font = parent.createFont("American Typewriter",fontSizeMax);
		  //font = createFont("ArnhemFineTT-Normal",10);
		  parent.textFont(font,fontSizeMin);
		  parent.textAlign(PApplet.LEFT);
		
	}
	public void draw() {
		//if (frameCount % 10 ==0) {		
			parent.fill(0,0,0,20);
			parent.rectMode(PApplet.CORNER);
			parent.rect(0,0,References.width,References.height);
			parent.colorMode(PApplet.RGB,255,255,255);
			parent.fill(255,255,255);
			
	
			int counter = 0;
			for (String id: References.data.keySet()) {
				DataStruct ds = References.data.get(id);
				float x = PApplet.map(PApplet.abs(ds.x), 0, PApplet.max(PApplet.abs(ds.minX), ds.maxX), 0, 1);
				float y = PApplet.map(PApplet.abs(ds.y), 0, PApplet.max(PApplet.abs(ds.minY), ds.maxY), 0, 1);
				float z = PApplet.map(PApplet.abs(ds.y), 0, PApplet.max(PApplet.abs(ds.minZ), ds.maxZ), 0, 1);
				float t = x+y+z;
				float d = PApplet.map( t, 0, 3, fontSizeMin, fontSizeMax);
				int index = (int)parent.random(names.length+specialNames.length);
				float angle = parent.random(-PApplet.PI, PApplet.PI);
				parent.textSize(d);
				
				parent.rotate(angle);
				String name = "";
				if (index < names.length) {
					parent.fill(255,255,255);
					name = names[index];
				}
				else {
					parent.fill(255,0,255);
					name = specialNames[index-names.length];
				}
				parent.text(name,parent.random(References.width), parent.random(References.height));
				
				counter++;
				if (counter>=maxNames) {counter = 0; break;}
			}
			frameCount = 1;
		//}
		//else
		//	frameCount++;
		try {
			Thread.sleep(70);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
