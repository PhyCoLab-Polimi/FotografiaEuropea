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
 
// non copiate questa linea, definisce l'identit‡ univoca di ciascuno dei vostri sketches
public class Fireworks extends EmptySketch { 
static int lWidth;
static int xAmpl ;
static int yAmpl ;
static int aChan ;
static int accAmpl ;
 
 
 
// definizione della classe ricevitore, che andr‡ a collezionare i dati da utilizzare per la grafica (da copiare)
private class Rcvr {
String UDID;
Vec3D acc= new Vec3D(0,0,0);
 
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
    public void setup() {
      parent.background(255);
      parent.smooth();
      parent.cursor(PApplet.CROSS);
      lWidth = 1;
      yAmpl = 100;
      xAmpl = 400;
      aChan = 10;
      accAmpl = 100;
    }
 
 
//draw
public void draw() {
parent.noStroke();
parent.fill(0, aChan);
parent.rect(0, 0, parent.width, parent.height);
 
//definite in ciascuno dei vostri sketch le modalit‡ di disegno (da copiare)
//  parent.background(0); // colore del background
parent.colorMode(PApplet.RGB, 255, 255, 255, 100); // modalit‡ colore: RGB o HSV e relativiu valori massimi per ciascun canale
//  parent.rectMode(PApplet.CORNER); // modalit‡ di disegno dei rettangoli: CENTER o CORNER
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
float angle = 0;
    int angleSpeed = 1;
 
// for loop che permette di selezionare i vari IPhones uno per uno e generare le grafiche ad esso connesse (da copiare)
for (int i=0; i<Math.min(100, recCollection.size()); i++) {
    Rcvr theRec= (Rcvr) recCollection.get(i);
   
//      float x = parent.pow(theRec.acc.x, 2); //accedete ai dati di accelerazione all'interno della classe Rcvr con la formula theRec.acc.x o .y o .z
//      float y = parent.pow(theRec.acc.y, 2); //accedete ai dati di accelerazione all'interno della classe Rcvr con la formula theRec.acc.x o .y o .z
    float x = theRec.acc.x*accAmpl+parent.random(-10,10); //accedete ai dati di accelerazione all'interno della classe Rcvr con la formula theRec.acc.x o .y o .z
    float y = theRec.acc.y*accAmpl+parent.random(-10,10); //accedete ai dati di accelerazione all'interno della classe Rcvr con la formula theRec.acc.x o .y o .z
    int z = (int) theRec.acc.z*accAmpl; //accedete ai dati di accelerazione all'interno della classe Rcvr con la formula theRec.acc.x o .y o .z
 
    int currR = (int) PApplet.map(x, -3, 3, 100, 255);
    int currG = (int) PApplet.map(y, -3, 3, 100, 255);
    int currB = (int) PApplet.map(z, -3, 3, 100, 255);
   
//      parent.point(x*100, y*100);
 
    ///// inserite qui il vostro codice per la grafica che verr‡ generata da ciascun Iphone
    /////
   
    parent.pushMatrix();
    parent.strokeWeight(lWidth);
    parent.noFill();
    parent.stroke(currR,currG,currB);
//      parent.translate(parent.mouseX,parent.mouseY);
    parent.translate(PApplet.cos((float)x)*xAmpl+parent.width/2,
         PApplet.sin((float)y)*yAmpl+parent.height/2);
    for(int j = 0; j < 50; j++){
    parent.rotate(angle);
    parent.line(0, 0, (x+y+z)/(Math.min(100,  recCollection.size())/10), 0);
    angle += angleSpeed;
    }
    parent.popMatrix();
       
  }
/////
 
// inserite qui il vostro codice per la grafica generale comune a tutto lo sketch
   
   
   
    //
 
 
}
 
//questa funzione decodica i cartteri (alcuni) della tstiera per
//controlare lcuni attrbui visivi
static public void getKey(int myKey){
 
switch(myKey){
//spessore linee
case  'q':
lWidth += 1;
break;
 
case  'w':
lWidth -= 1;
break;
// fattore aplificazine x ,  che regola  ralazione oggetto
//usato in funzione  traslate()
case  'a':
xAmpl += 50;
break;
 
case  's':
xAmpl -= 50;
break;
 
case  'z':
yAmpl += 50;
break;
 
case  'x':
yAmpl -= 50;
break;
// fattore modifica alfa channel
case  'e':
aChan += 10;
break;
 
case  'r':
aChan -= 10;
break;
// fattore motiplicativo accelerazione x,y,z
case  'd':
accAmpl += 25;
break;
 
case  'f':
accAmpl -= 25;
break;
//reset parameters 
case 'p':
    lWidth = 1;
    xAmpl = yAmpl =50;
    aChan = 10;
    accAmpl = 100;
    break;
 
 
 
}
};
 
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