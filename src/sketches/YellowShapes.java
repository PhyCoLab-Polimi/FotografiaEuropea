package sketches;


import processing.core.PApplet;

import main.DataStruct;

import main.References;



public class YellowShapes extends EmptySketch {



protected PApplet parent = References.parent;





Ball[] myBall;



int counter;

int ballAmount = 50;

int distance = 100;

int distance_line=200;



    int radius_light = 12;

int radius = 8;



int speed = 1;



//mappatura movimento/velocit‡

float maxAmp = (float)2.3;

float minAmp = (float)-2.3;



float maxSpeed = 10;

float minSpeed = 1;

float calcSpeed;



public void setup() {



      parent.colorMode(PApplet.RGB, 255, 255, 255, 255);

  parent.ellipseMode(PApplet.CENTER);



  parent.background(0,0,0);

  parent.smooth();



  //class setup

  myBall = new Ball[ballAmount];

  for (int i = 0; i < ballAmount; i++) {

  myBall[i] = new Ball();

  myBall[i].setup();

  }



}



public void draw() {



  parent.background(0,0,0);





  int numCell=0;



  for (String id: References.data.keySet()) {



DataStruct ds = References.data.get(id);


float acc = PApplet.map(PApplet.abs(ds.x), 0, PApplet.max(PApplet.abs(ds.minX), ds.maxX), minSpeed, maxSpeed);
float acc2 = PApplet.map(PApplet.abs(ds.y), 0, PApplet.max(PApplet.abs(ds.minY), ds.maxY), minSpeed, maxSpeed);
float acc3 = PApplet.map(PApplet.abs(ds.z), 0, PApplet.max(PApplet.abs(ds.minZ), ds.maxZ), minSpeed, maxSpeed);



calcSpeed = acc+acc2+acc3+calcSpeed;



numCell++;



}



calcSpeed= calcSpeed/numCell/3;






speed = (int) calcSpeed;

calcSpeed = 0;





  for (int i = 0; i < ballAmount; i++) {

  myBall[i].step();

  myBall[i].drawBall();







    for (int j = 0; j < i; j++) {



      if (i != j) {



        float distBalls = PApplet.dist(myBall[i].x, myBall[i].y, myBall[j].x, myBall[j].y);



        if (distBalls <= 20) parent.ellipse(myBall[i].x, myBall[i].y, parent.random(40,50),parent.random(40,50));



        if(distBalls<distance_line) {

         parent.strokeWeight(1);

         float alpy=PApplet.map(distBalls, 0, distance_line, 255, 0);

         parent.stroke(200,200,200,alpy);



         parent.line(myBall[i].x, myBall[i].y, myBall[j].x, myBall[j].y);

         parent.noStroke();

         parent.ellipse(myBall[i].x, myBall[i].y, radius_light,radius_light);

         parent.ellipse(myBall[i].x, myBall[i].y, radius_light/3*2,radius_light/3);

         parent.ellipse(myBall[i].x, myBall[i].y, radius_light/3,radius_light/3);

        }



        for (int k = 0; k < ballAmount; k++) {





        if (k!= j && k!=i) {

        float distBalls2 = PApplet.dist(myBall[k].x, myBall[k].y, myBall[i].x, myBall[i].y);

        float distBalls3 = PApplet.dist(myBall[k].x, myBall[k].y, myBall[j].x, myBall[j].y);







          if (distBalls <= distance && distBalls2<= distance && distBalls3<= distance) {







           parent.fill(myBall[i].myColor,myBall[j].myColor,0, 100);

           parent.noStroke();

           parent.triangle(myBall[k].x, myBall[k].y, myBall[i].x, myBall[i].y,  myBall[j].x, myBall[j].y);





           }







           }







        if (distBalls <= radius) {

          myBall[i].setInc(1);

          myBall[j].setInc(1);

        }

      }

         }

    }







  }



}













//THE CLASS

class Ball {

  float x;

  float y;

  float incX;

  float incY;

  float myColor;

  int number;

  int lighton;



  void setup() {

    x = parent.random(References.width);

    y = parent.random(References.height);

    myColor=parent.random(40,200);

    setInc(1);

    number=number+counter;

    counter++;

  }



  //speed

  void setInc(float acc) {

    incX = parent.random(-speed, speed)*acc;

    incY = parent.random(-speed, speed)*acc;

  }





  //the ball

  void drawBall() {



   parent.noStroke();



   parent.fill(60,60,60,100);

   parent.ellipse(x, y, parent.random(30,40), parent.random(30,40));

   parent.ellipse(x, y, radius_light,radius_light);

   parent.ellipse(x, y, radius_light/3,radius_light/3);

   parent.ellipse(x, y, radius_light/2,radius_light/2);

   parent.textSize(7);

   parent.fill(240,240,240,70);

   parent.text(number+"."+(int)x,x+10,y+10);

   parent.fill(45,45,45,70);

  }



  //direction

  void step() {



    //controllo incremento still in canvas

    if (x > References.width-20 || x < 20) {

      incX = -incX;

    }

    if (y > References.height-20 || y < 20) {

      incY = -incY;

    }



    //PASSO!

    x = x + incX;

    y = y + incY;



//controllo variabili still in canvas

    if (x >  References.width-20) {

      x =  References.width-25;

      setInc(1);

    }

    if (x < 20) {

      x = 25;

      setInc(1);

    }

    if (y > References.height-20) {

      y = References.height-25;

      setInc(1);

    }

    if (y < 20) {

      y = 25;

      setInc(1);

    }

  }

}



}