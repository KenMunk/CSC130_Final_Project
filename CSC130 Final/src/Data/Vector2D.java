/* This class is used to give you a handy way to pass a pair of 2D coordinates as a single object. The behavior (methods) of the class should allow for robust options in the future. */

package Data;

public class Vector2D {
	// Fields
	private int x,y;
	
	// Constructor
	public Vector2D(int x, int y){
		int tempX = x;
		int tempY = y;
		
		this.x = tempX;
		this.y = tempY;
	}
	
	// Methods
	private int getValue(int value) {
		int temp = value;
		return temp;
	}
	
	public int getX(){
		return this.getValue(this.x);
	}
	
	public int getY(){
		return this.getValue(this.y);
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
}
