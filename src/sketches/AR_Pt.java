package sketches;

import processing.core.*;
import toxi.geom.*;

public class AR_Pt {
	
	Vec3D loc;
	int count;
	
	
	AR_Pt(Vec3D _loc){
		loc = _loc;
	
	}
	
	void run(){
		display();
		move();
	}
	
	void move(){
		loc.x++;
		loc.y++;
	}
	
	void display(){
		
	}
	
	
}
