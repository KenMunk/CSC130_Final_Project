package Data;

import java.util.ArrayList;

public class CollisionCollection {
	
	private ArrayList<CollisionBox> collisionBoxes;
	private Vector2D position;
	
	public CollisionCollection() {
		this.collisionBoxes = new ArrayList<CollisionBox>();
		this.position = new Vector2D(0,0);
	}
	
	public void updatePosition(Vector2D newPosition) {
		Vector2D tempPosition = new Vector2D(newPosition);
		this.position = tempPosition;
	}
	
	public void setPosition(Vector2D newPosition) {
		this.updatePosition(newPosition);
	}
	
	public Vector2D getPosition() {
		return(this.position);
	}
	
	public void addCollisionBox(CollisionBox oneBox) {
		this.collisionBoxes.add(oneBox);
	}
	
	public boolean removeCollisionBox(int boxID) {
		if(boxID >= 0 && boxID < this.collisionBoxes.size()) {
			
			this.collisionBoxes.remove(boxID);
			
			return(true);
		}
		else {
			return(false);
		}
	}
	
	public int size() {
		return(this.collisionBoxes.size());
	}
	
	public CollisionBox getCollider(int id) {
		CollisionBox outputCollider = this.collisionBoxes.get(id);
		outputCollider.adjustPositionBy(this.position);
		return(outputCollider);
	}
	
	public boolean collidesWith(CollisionBox aCollisionBox) {
		boolean output = false;
		
		for(int id = 0; id<this.size(); id++) {
			if(this.getCollider(id).collisionDetected(aCollisionBox)) {
				output = true;
			}
		}
				
		return(output);
	}
	
	public boolean collidesWith(CollisionCollection anotherCollection) {
		boolean output = false;
		
		for(int id = 0; id<anotherCollection.size(); id++) {
			if(this.collidesWith(anotherCollection.getCollider(id))) {
				output = true;
			}
		}
		
		return(output);
	}

}
