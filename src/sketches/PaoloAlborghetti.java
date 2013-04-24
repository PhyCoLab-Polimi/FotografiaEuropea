package sketches;


///////////////////////////////////////////////////////////////TEMPLATE PER TUTTI GLI SKETCH

//se iniziate a sviluppare un nuovo sketch, copiate ed incollate le parti indicate 

//import di tutte le librerie necessarie per far funzionare lo sketch (da copiare)
import java.util.ArrayList;
import processing.core.PApplet;
import toxi.geom.Vec3D;
import main.DataStruct;
import main.References;
/////

// non copiate questa linea, definisce l'identità univoca di ciascuno dei vostri sketches
public class PaoloAlborghetti extends EmptySketch {	
	
	
	// definizione della classe ricevitore, che andrà a collezionare i dati da utilizzare per la grafica (da copiare)
	private class Rcvr {
		String UDID;
		Vec3D acc= new Vec3D(0,0,0);
		float countx=0;
		float county=0;
		
		Rcvr(String _UDID, Vec3D _acc){
		    
		    UDID= _UDID;
		    acc= _acc;
		}
	}
	/////
	
	
	// arraylists in cui andremo a salvare le diverse istanze celle classi contenenti i dati (da copiare)
	ArrayList<DataStruct> dataAL = new ArrayList<DataStruct>();
	ArrayList<Rcvr> recCollection= new ArrayList<Rcvr>();
	/////
	
	
	//setup
	public void setup () {
		
	}
	
	
	//draw
	public void draw() {
		
		//definite in ciascuno dei vostri sketch le modalità di disegno (da copiare)
		parent.background(0); // colore del background
		parent.colorMode(PApplet.RGB, 255, 255, 255, 255); // modalità colore: RGB o HSV e relativiu valori massimi per ciascun canale
		parent.rectMode(PApplet.CORNER); // modalità di disegno dei rettangoli: CENTER o CORNER
		/////
		
		// calcolo numero di Iphones attualmente connessi (da copiare)
		int count = References.data.size();
		/////
		
		// trasformazione dati da hashmap ad arraylist (da copiare) 
		dataAL.clear();
		for (String id : References.data.keySet()) {
			DataStruct ds = References.data.get(id);
		    dataAL.add(ds);
		}
		/////
		
		// funzione che riordina gli iphones in modo univoco in base all'id (da copiare)
		read();
		/////
		
		// for loop che permette di selezionare i vari IPhones uno per uno e generare le grafiche ad esso connesse (da copiare)
		for (int i=0; i<recCollection.size(); i++) {
		    Rcvr theRec= (Rcvr) recCollection.get(i);
		    
		    
		    ///// inserite qui il vostro codice per la grafica che verrà generata da ciascun Iphone
		    
		    
		    
		    /////
		    
		    // esempio
		    parent.stroke(255); //inserite parent. prima di ogni comando Processing
		    parent.strokeWeight(10);
		    
		    float x = parent.pow(theRec.acc.x, 2); //accedete ai dati di accelerazione all'interno della classe Rcvr con la formula theRec.acc.x o .y o .z
		    float y = parent.pow(theRec.acc.y, 2); //accedete ai dati di accelerazione all'interno della classe Rcvr con la formula theRec.acc.x o .y o .z
		    
		    parent.point(x*100, y*100); 
		    
		}
		/////
		
		// inserite qui il vostro codice per la grafica generale comune a tutto lo sketch
	    
	    
	    
	    //
		
		
	}
	
	
	
	//funzione per il riordino dei dati (da copiare)
	private void read () {
			
		if(dataAL.size()!=0){
			
			for(int i = 0; i < dataAL.size(); i++){
				DataStruct ds = dataAL.get(i);
								
				if(recCollection.size()==0){
					Rcvr nuRec= new Rcvr(ds.id,new Vec3D(ds.x,ds.y,ds.z));
					recCollection.add(nuRec);
				}
				else{
					
					boolean addNew=true;
					for (int j=0; j<recCollection.size(); j++) {
			          Rcvr checkRec= (Rcvr) recCollection.get(j);
			          if (ds.id.equals(checkRec.UDID)==true) {
			            addNew= false;
			            checkRec.acc= new Vec3D(ds.x,ds.y,ds.z);
			            break;
			          }
					}
			        if (addNew==true) {
			            Rcvr nuRec= new Rcvr(ds.id, new Vec3D(ds.x,ds.y,ds.z));    
			            recCollection.add(nuRec);
		            }
				}
			}
		}
	}
	/////
}