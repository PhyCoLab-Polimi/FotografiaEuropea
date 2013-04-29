package main;

import java.util.Date;

public class DataStruct {
	public float x;
	public float minX;
	public float maxX;
	
	public float y;
	public float minY;
	public float maxY;
	
	public float z;
	public float maxZ;
	public float minZ;
	
	public String id;
	
	public Date timestamp;
	
	public DataStruct(float x, float y, float z, String id) {
		this.x = x;
		this.minX = this.x;
		this.maxX = this.x;
		
		this.y = y;
		this.minY = this.y;
		this.maxY = this.y;
		
		this.z = z;
		this.minZ = this.z;
		this.maxZ = this.z;
		
		this.id = id;
		
		this.timestamp = new Date();
	}
	
	public String toString() {
		return id+" -> X: "+this.x+" ; "+"Y: "+this.y+" ; "+"Z: "+this.z+" ; Received at "+this.timestamp.toString();
	}
	
	public void updateHistory(float newX, float newY, float newZ) {
		this.x = newX;
		if (this.x > this.maxX) this.maxX = this.x;
		if (this.x < this.minX) this.minX = this.x;
		
		this.y = newY;
		if (this.y > this.maxY) this.maxY = this.y;
		if (this.y < this.minY) this.minY = this.y;
		
		this.z = newZ;
		if (this.z > this.maxZ) this.maxZ = this.z;
		if (this.z < this.minZ) this.minZ = this.z;
		
		this.timestamp = new Date();
	}
}
