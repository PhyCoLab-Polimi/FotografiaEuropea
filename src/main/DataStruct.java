package main;

public class DataStruct {
	public float x;
	public float y;
	public float z;
	public String id;
	
	public DataStruct(float x, float y, float z, String id) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.id = id;
	}
	
	public String toString() {
		return id+" -> X: "+this.x+" ; "+"Y: "+this.y+" ; "+"Z: "+this.z+" ;";
	}
}
