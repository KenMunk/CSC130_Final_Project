package Data;


import Main.Main;

public class CollisionBox {
	
	private Vector2D lengths;
	private Vector2D localPosition;
	
	public CollisionBox(Vector2D localPosition, Vector2D lengths) {
		this.setDefaults();
		this.setPosition(localPosition);
		this.setLengths(lengths);
	}
	
	//Modifiers
	
	public void setDefaults() {
		Vector2D defaultValues = new Vector2D();
		this.setEverything(defaultValues, defaultValues);
	}
	
	public void setEverything(Vector2D localPosition, Vector2D lengths) {
		this.setPosition(localPosition);
		this.setLengths(lengths);
	}
	
	
	public void setLengths(Vector2D lengths) {
		Vector2D tempLengths = lengths;
		this.lengths = tempLengths;
	}
	
	public void setPosition(Vector2D localPosition) {
		Vector2D tempPosition = localPosition;
		this.localPosition = tempPosition;
	}
	
	public void adjustPositionBy(Vector2D referencePosition) {
		this.localPosition.adjust(referencePosition);
	}
	
	//Outputs
	public Vector2D getPosition() {
		Vector2D output = this.localPosition;
		return(output);
	}
	
	public Vector2D getPosition(Vector2D referencePosition) {
		Vector2D adjustedPosition = new Vector2D(this.localPosition.getX(), this.localPosition.getY());
		adjustedPosition.adjustX(referencePosition.getX());
		adjustedPosition.adjustY(referencePosition.getY());				
		return(adjustedPosition);
	}
	
	public Vector2D getLengths() {
		Vector2D output = new Vector2D(this.lengths);
		return(output);
		
	}
	
	public Vector2D getLocalPosition() {
		Vector2D output = new Vector2D(this.localPosition);
		return(output);
	}
	
	public Vector2D lowerBound() {
		int lowerX, lowerY;
		//*
		lowerX = this.getPosition().getX();
		lowerY = this.getPosition().getY();
		//*/
		return(new Vector2D(lowerX, lowerY));
	}
	
	public Vector2D lowerBound(Vector2D referencePosition) {
		//int lowerX, lowerY;
		/*
		lowerX = this.getPosition(referencePosition).getX() + this.lengths.getX()*(Math.abs(this.anchor.getX()-1));
		lowerY = this.getPosition(referencePosition).getY() + this.lengths.getY()*(Math.abs(this.anchor.getX()-1));
		//*/
		
		/*
		lowerX = this.getPosition(referencePosition).getX() + 100;
		lowerY = this.getPosition(referencePosition).getY() + 100;
		//*/
		
		Vector2D output = this.lowerBound();
		
		output.adjust(referencePosition);
		
		//return(new Vector2D(lowerX, lowerY));
		
		return(output);
	}
	
	public Vector2D upperBound() {
		int upperX, upperY;
		//*
		upperX = this.getPosition().getX() + this.lengths.getX();
		upperY = this.getPosition().getY() + this.lengths.getY();
		//*/
		
		return(new Vector2D(upperX, upperY));
	}

	public Vector2D upperBound(Vector2D referencePosition) {
		/*
		int upperX, upperY;
		//*
		upperX = this.getPosition().getX() + referencePosition.getX() + this.lengths.getX();
		upperY = this.getPosition(referencePosition).getY() + this.lengths.getY();
		//*/
		
		Vector2D output = this.upperBound();
		output.adjust(referencePosition);
		
		return(output);
		
		//return(new Vector2D(upperX, upperY));
	}
	
	//CollisionDetection
	public boolean coordinateCollides(Vector2D coordinate) {
		boolean output = false;
		
		boolean xCollides = (this.lowerBound().getX() <= coordinate.getX()) && (coordinate.getX() <= this.upperBound().getX()); 
		boolean yCollides = (this.lowerBound().getY() <= coordinate.getY()) && (coordinate.getY() <= this.upperBound().getY()); 
		
		output = xCollides && yCollides;
		
		return(output);
	}
	
	public boolean coordinateCollides(Vector2D coordinate, Vector2D referencePosition) {
		boolean output = false;
		
		boolean xCollides = (this.lowerBound(referencePosition).getX() <= coordinate.getX()) && (coordinate.getX() <= this.upperBound(referencePosition).getX()); 
		boolean yCollides = (this.lowerBound(referencePosition).getY() <= coordinate.getY()) && (coordinate.getY() <= this.upperBound(referencePosition).getY()); 
		
		output = xCollides && yCollides;
		
		return(output);
	}
	
	public boolean collisionDetected(CollisionBox boundingBox) {
		boolean output = false;
		
		Vector2D boundingBoxOppUpper, boundingBoxOppLower;
		Vector2D thisOppUpper, thisOppLower;
		
		boundingBoxOppUpper = new Vector2D(boundingBox.upperBound().getX(),boundingBox.lowerBound().getY());
		boundingBoxOppLower = new Vector2D(boundingBox.lowerBound().getX(),boundingBox.upperBound().getY());
		thisOppUpper = new Vector2D(this.upperBound().getX(), this.lowerBound().getY());
		thisOppLower = new Vector2D(this.lowerBound().getX(), this.upperBound().getY());
		
		boolean boxCollides = (this.coordinateCollides(boundingBox.upperBound()) || this.coordinateCollides(boundingBox.lowerBound()));
		boxCollides = boxCollides || (this.coordinateCollides(boundingBoxOppLower) || this.coordinateCollides(boundingBoxOppUpper));
		boolean thisCollides = (boundingBox.coordinateCollides(this.lowerBound()) || boundingBox.coordinateCollides(this.upperBound()));
		thisCollides = thisCollides || (boundingBox.coordinateCollides(thisOppLower));
		thisCollides = thisCollides || (boundingBox.coordinateCollides(thisOppUpper));
		//boolean thisCollides = false;
		
		output = boxCollides || thisCollides;
		
		return(output);
	}
	
	public boolean collisionDetected(CollisionBox boundingBox, Vector2D referencePosition) {
		boolean output = false;
		
		boolean boxCollides = (this.coordinateCollides(boundingBox.upperBound(),referencePosition) || this.coordinateCollides(boundingBox.lowerBound(),referencePosition));
		boolean thisCollides = (boundingBox.coordinateCollides(this.lowerBound(referencePosition)) || boundingBox.coordinateCollides(this.upperBound(referencePosition)));
		
		output = boxCollides || thisCollides;
		
		return(output);
	}
	
	public void renderBounds(Vector2D referencePosition) {
		Vector2D boundLower = this.lowerBound(referencePosition);
		Main.ctrl.addSpriteToFrontBuffer(this.getPosition(referencePosition).getX(), this.getPosition(referencePosition).getY(), "refPt");
		Main.ctrl.addSpriteToFrontBuffer(boundLower.getX(), boundLower.getY(), "bound_upper");
		//Main.ctrl.addSpriteToFrontBuffer(100, 100, "bound_lower");
		Vector2D boundUpper = this.upperBound(referencePosition);
		boundUpper.adjust(new Vector2D(-3,-3));
		Main.ctrl.addSpriteToFrontBuffer(boundUpper.getX(), boundUpper.getY(), "bound_lower");
		//Main.ctrl.addSpriteToFrontBuffer(200, 200, "bound_upper");
	}
	
}
