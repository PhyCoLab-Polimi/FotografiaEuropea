package main;

import java.util.Date;

public class DataStruct {
	public float x;
	public float y;
	public float z;
	public String id;
	public Date timestamp;
	
	public DataStruct(float x, float y, float z, String id) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.id = id;
		this.timestamp = new Date();
	}
	
	public String toString() {
		return id+" -> X: "+this.x+" ; "+"Y: "+this.y+" ; "+"Z: "+this.z+" ; Received at "+this.timestamp.toString();
	}
}
