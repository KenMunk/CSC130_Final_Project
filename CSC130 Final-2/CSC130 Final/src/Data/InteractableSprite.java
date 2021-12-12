package Data;

import java.util.ArrayList;

public class InteractableSprite {
	
	private int frame;
	private ArrayList<SpriteInfo> spriteFrames;
	private boolean collisionDetectionEnabled;
	private CollisionCollection colliders;
	
	public InteractableSprite() {
		this.setDefaults();
	}

	public InteractableSprite(ArrayList<SpriteInfo> spriteFrames) {
		this.setDefaults();
		this.setSpriteFrames(spriteFrames);
	}
	
	//Modifiers
	
	public void setFrame(int frameNumber) {
		int tempFrame = frameNumber;
		this.frame = tempFrame;
	}
	
	public void enableCollisionDetection(boolean enable) {
		this.collisionDetectionEnabled = false;
		if(enable) {
			this.collisionDetectionEnabled = true;
		}
	}
	
	public void addColliders(ArrayList<CollisionBox> colliders) {
		for(int i = 0; i<colliders.size(); i++) {
			this.colliders.addCollisionBox(colliders.get(i));
		}
	}
	
	public void setPosition(Vector2D position) {
		this.colliders.setPosition(position);
	}
	
	public Vector2D getPosition(Vector2D position) {
		return(this.colliders.getPosition());
	}
	
	public void setSpriteFrames(ArrayList<SpriteInfo> spriteFrames) {
		ArrayList<SpriteInfo> tempFrames = spriteFrames;
		this.spriteFrames = tempFrames;
	}
	
	public void setDefaults() {
		this.setFrame(0);
		this.setSpriteFrames(new ArrayList<SpriteInfo>());
		this.colliders = new CollisionCollection();
	}
	
	public void addFrame(SpriteInfo frameInfo) {
		SpriteInfo tempInfo = frameInfo;
		this.spriteFrames.add(tempInfo);
	}
	
	public CollisionCollection getColliders() {
		return(this.colliders);
	}
	
	//Outputs
	
	public SpriteInfo nextFrame() {
		this.frame = (this.frame + 1)%this.spriteFrames.size();
		return(this.spriteFrames.get(this.frame));
	}
	
	//Utilities
	public boolean collidesWith(InteractableSprite anotherSprite) {
		boolean output = false;
		
		output = this.collisionDetectionEnabled && this.colliders.collidesWith(anotherSprite.getColliders());
		
		return(output);
	}
}
