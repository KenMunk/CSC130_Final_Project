package Data;

import Data.Vector2D;

public class CollisionBox {
	
	private Vector2D anchor;
	private Vector2D lengths;
	private Vector2D position;
	
	public CollisionBox(Vector2D position, Vector2D anchor, Vector2D lengths) {
		this.setEverything(position, anchor, lengths);
	}
	
	public CollisionBox(Vector2D position, Vector2D lengths) {
		this.setDefaults();
		this.setPosition(position);
		this.setLengths(lengths);
	}
	
	//Modifiers
	
	public void setDefaults() {
		Vector2D defaultValues = new Vector2D();
		this.setEverything(defaultValues, defaultValues, defaultValues);
	}
	
	public void setEverything(Vector2D position, Vector2D anchor, Vector2D lengths) {
		this.setAnchor(anchor);
		this.setPosition(position);
		this.setLengths(lengths);
	}
	
	public void setAnchor(Vector2D anchor) {
		
		//Forces the anchor points to be 0 or 1s
		
		Vector2D tempAnchor = new Vector2D(0,0);
		if(anchor.getX()>0) {
			tempAnchor.setX(1);
		}if(anchor.getY()>0) {
			tempAnchor.setY(1);
		}
		
		this.anchor = tempAnchor;
	}
	
	public void setLengths(Vector2D lengths) {
		Vector2D tempLengths = lengths;
		this.lengths = tempLengths;
	}
	
	public void setPosition(Vector2D position) {
		Vector2D tempPosition = position;
		this.position = tempPosition;
	}
	
	public void adjustPositionBy(Vector2D adjustment) {
		this.position.adjust(adjustment);
	}
	
	//Outputs
	public Vector2D getPosition() {
		Vector2D output = this.position;
		return(output);
	}
	
	public Vector2D getLengths() {
		Vector2D output = this.lengths;
		return(output);
		
	}
	
	
	public Vector2D getAnchor() {
		Vector2D output = this.anchor;
		return(output);
	}
	
	public Vector2D lowerBound() {
		int lowerX = this.position.getX() - this.lengths.getX()*(this.anchor.getX()-1);
		int lowerY = this.position.getY() - this.lengths.getY()*(this.anchor.getY()-1);
		return(new Vector2D(lowerX, lowerY));
	}
	
	public Vector2D upperBound() {
		int lowerX = this.position.getX() + this.lengths.getX()*(this.anchor.getX());
		int lowerY = this.position.getY() + this.lengths.getY()*(this.anchor.getY());
		return(new Vector2D(lowerX, lowerY));
	}
	
	//CollisionDetection
	public boolean coordinateCollides(Vector2D coordinate) {
		boolean output = false;
		
		boolean xCollides = (this.lowerBound().getX() <= coordinate.getX()) && (coordinate.getX() <= this.upperBound().getX()); 
		boolean yCollides = (this.lowerBound().getY() <= coordinate.getY()) && (coordinate.getY() <= this.upperBound().getY()); 
		
		output = xCollides && yCollides;
		
		return(output);
	}
	
	public boolean collisionDetected(CollisionBox boundingBox) {
		boolean output = false;
		
		boolean boxCollides = (this.coordinateCollides(boundingBox.upperBound()) || this.coordinateCollides(boundingBox.lowerBound()));
		boolean thisCollides = (boundingBox.coordinateCollides(this.lowerBound()) || boundingBox.coordinateCollides(this.upperBound()));
		
		output = boxCollides || thisCollides;
		
		return(output);
	}
	
	
	
}
