package Characters;

import Data.Vector2D;

public class SpriteData {
	
	private Vector2D position;
	private String frame;
	
	public SpriteData(Vector2D position, String frame) {
		this.position = new Vector2D(position);
		String temp = frame;
		this.frame = temp;
	}
	
	public Vector2D getPosition() {
		return(new Vector2D(this.position));
	}
	
	public String getFrame() {
		
		String temp = frame;
		return(temp);
		
	}
	
}
