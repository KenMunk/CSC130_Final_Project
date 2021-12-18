/* This class is used to give you a handy way to pass a pair of 2D coordinates as a single object. The behavior (methods) of the class should allow for robust options in the future. */

package Data;

import java.lang.Math;

public class Vector2D {
	// Fields
	private int x,y;
	
	// Constructor
	public Vector2D() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2D(int x, int y){
		int tempX = x;
		int tempY = y;
		
		this.x = tempX;
		this.y = tempY;
	}
	
	public Vector2D(Vector2D source) {
		int tempX = source.getX();
		int tempY = source.getY();
		this.setX(tempX);
		this.setY(tempY);
	}
	
	// Methods
	
	public static Vector2D zero() {
		return(new Vector2D(0,0));
	}
	
	private int getValue(int value) {
		int temp = value;
		return temp;
	}
	
	public int getX(){
		int tempX = this.x;
		return tempX;
	}
	
	public int getY(){
		int tempY = this.y;
		return tempY;
	}
	
	public void setX(int newX){
		this.x = this.getValue(newX);
	}
	
	public void setY(int newY){
		this.y = this.getValue(newY);
	}
	
	public void adjustX(int adjustment){
		this.x += adjustment;
	}
	
	public void adjustY(int adjustment){
		this.y += adjustment;
	}
	
	public void adjust(Vector2D adjustment) {
		
		this.adjustX(adjustment.getX());
		this.adjustY(adjustment.getY());
		
	}
	
	public Vector2D difference(Vector2D next) {
		
		Vector2D output = new Vector2D(this.getX() - next.getX(), this.getY() - next.getY());
		
		return(output);
		
	}
	
	public int distance(Vector2D next) {
		int xSquare = next.getX()*next.getX();
		int ySquare = next.getY()*next.getY();
		double distSquare = (xSquare+ySquare);
		int dist = (int)Math.sqrt(distSquare);
		return(dist);
	}
	
	public Vector2D stepsFor(int steps) {
		return(new Vector2D(this.getX()/steps,this.getY()/steps));
	}
	
	public String toString() {
		return(String.format("(%d, %d)", this.getX(), this.getY()));
	}
}
