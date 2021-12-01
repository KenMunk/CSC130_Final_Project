/* This is a way to pass a sprite's key information in one entity. (x, y, tag) */

package Data;

public class spriteInfo {
	// Fields
		// DONE [KM]: Add private class fields to store x, y (use Vector2D for this) and tag (String) values given in class constructor
	
	private Vector2D coord;
	private String tag;
	
	// Constructor
	public spriteInfo(Vector2D v2d, String tag){
		// Done: Save the constructor parameters into class fields
		this.setTag(tag);
		//[KM] me being lazy and having forethought I already created a vector2D constructor for direct clones.
		//Implementing lazy code
		this.coord = new Vector2D(v2d);
		
	}
	
	// Methods
	public String getTag(){
		// Done: Remove my placeholder code below (which is there to prevent an error) and replace it with returning the value of your private field tag
		
		String temp = this.tag;
		
		return temp;
	}
	
	public Vector2D getCoords(){
		// Done: Remove my placeholder code below (which is there to prevent an error) and replace it with returning the value of your private field v2d
		
		Vector2D temp = new Vector2D(this.coord);
		
		return temp;
	}
	
	public void setTag(String newTag){
		// Done: Update the value of tag to be the value in newTag (Absolute assignment)
		
		String temp = newTag;
		this.tag = temp;
		
	}
	
	public void setCoords(Vector2D newV2D){
		// Done: Update the value of v2d to be the value in newV2D (Absolute assignment)
		
		this.coord = new Vector2D(newV2D);
		
	}
	
	public void setCoords(int x, int y){
		// Done: Overload the setCoords method to allow another way to set the coordinates. Place the x, y integers into v2d by changing the values of v2d to hold x and y (Absolute assignment)
		
		this.coord = new Vector2D(x,y);
		
	}
	
	public String toString(){
		// Done: Create a "toString" method to test. Assume an x, y of 100, 50 and a tag of "star", this should return: [100, 50, star]
			// Remove my placeholder code below (which is there to prevent an error) and replace it with your proper toString method with specifications above
		return String.format("[%d, %d, %s]", this.coord.getX(), this.coord.getY(), this.tag);
	}
}
