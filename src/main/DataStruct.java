package main;

public class DataStruct {
	public float x;
	public float y;
	public float z;
	
	public DataStruct(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public String toString() {
		return "X: "+this.x+" ; "+"Y: "+this.y+" ; "+"Z: "+this.z+" ;";
	}
}