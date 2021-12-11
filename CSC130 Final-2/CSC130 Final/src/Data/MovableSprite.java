package Data;

import java.util.ArrayList;

public class MovableSprite {
	
	private int frame;
	private ArrayList<SpriteInfo> spriteFrames;
	private boolean collisionDetectionEnabled;
	private CollisionBox collider;
	
	public MovableSprite() {
		this.setDefaults();
	}

	public MovableSprite(ArrayList<SpriteInfo> spriteFrames) {
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
	
	public void setcollider(CollisionBox collider) {
		CollisionBox tempcollider = collider;
		this.collider = tempcollider;
	}
	
	public void setBoundingLengths(Vector2D lengths) {
		this.collider.setLengths(lengths);
	}
	
	public void setBoundingAnchor(Vector2D anchor) {
		this.collider.setAnchor(anchor);
	}
	
	public void setPosition(Vector2D position) {
		this.collider.setPosition(position);
	}
	
	public void setSpriteFrames(ArrayList<SpriteInfo> spriteFrames) {
		ArrayList<SpriteInfo> tempFrames = spriteFrames;
		this.spriteFrames = tempFrames;
	}
	
	public void setDefaults() {
		this.setFrame(0);
		this.setSpriteFrames(new ArrayList<SpriteInfo>());
	}
	
	public void addFrame(SpriteInfo frameInfo) {
		SpriteInfo tempInfo = frameInfo;
		this.spriteFrames.add(tempInfo);
	}
	
	//Outputs
	
	public SpriteInfo nextFrame() {
		this.frame = (this.frame + 1)%this.spriteFrames.size();
		return(this.spriteFrames.get(this.frame));
	}
	
	public CollisionBox getCollider() {
		CollisionBox tempOutput = this.collider;
		return(tempOutput);
	}
	
	public boolean collidesWith(MovableSprite somethingElse) {
		return(this.collisionDetectionEnabled && this.collider.collisionDetected(somethingElse.getCollider()));
	}
	
	public boolean moveCollides(Vector2D adjustment, MovableSprite somethingElse) {
		return(this.collisionDetectionEnabled && this.collider.collisionDetected(somethingElse.getCollider(),adjustment));
	}
	
	public void tryMove(Vector2D adjustment) {
		
	}
	
	//Utilities
	
}
