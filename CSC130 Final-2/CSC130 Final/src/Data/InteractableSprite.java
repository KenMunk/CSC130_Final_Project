package Data;

import java.util.ArrayList;

import logic.Control;

public class InteractableSprite {
	
	private int frame;
	private int hashFrame;
	private int frameGroup;
	private int frameGroups;
	private ArrayList<SpriteInfo> spriteFrames;
	private ArrayList<String> descriptionHash;
	private boolean collisionDetectionEnabled;
	private boolean showBounds;
	private CollisionCollection colliders;
	
	
	public InteractableSprite() {
		this.setDefaults();
	}
	
	public InteractableSprite(SpriteInfo spriteFrame) {
		this.setDefaults();
		ArrayList<SpriteInfo> tempSprite = new ArrayList<SpriteInfo>();
		tempSprite.add(spriteFrame);
		this.setSpriteFrames(tempSprite);
	}

	public InteractableSprite(ArrayList<SpriteInfo> spriteFrames) {
		this.setDefaults();
		this.setSpriteFrames(spriteFrames);
	}

	public InteractableSprite(InteractableSprite referenceSprite) {
		this.setDefaults();
		this.setFrame(referenceSprite.getCurrentFrameNumber());
		this.setHashFrame(referenceSprite.getHashFrameNumber());
		this.setFrameGroup(referenceSprite.getFrameGroup());
		this.setFrameGroups(referenceSprite.getFrameGroups());
		this.setSpriteFrames(referenceSprite.getSpriteFrames());
		this.setHashFrame(referenceSprite.getDescriptionHash());
		this.enableCollisionDetection(referenceSprite.isCollisionDetectionEnabled());
		this.setColliders(referenceSprite.getColliders());
		
	}
	
	//Modifiers
	
	public void setFrame(int frameNumber) {
		int tempFrame = frameNumber;
		//tempFrame %=(this.spriteFrames.size()/this.frameGroups);
		this.frame = tempFrame;
	}
	
	public void setFrameGroup(int frameGroup) {
		int tempFrameGroup = frameGroup;
		//tempFrameGroup %= this.frameGroups;
		this.frameGroup = tempFrameGroup;
	}
	
	public void setFrameGroups(int frameGroups) {
		int tempFrameGroups = frameGroups;
		this.frameGroups = tempFrameGroups;
	}
	
	public void enableCollisionDetection(boolean enable) {
		this.collisionDetectionEnabled = false;
		if(enable) {
			this.collisionDetectionEnabled = true;
		}
	}
	
	public void setColliders(CollisionCollection colliders) {
		CollisionCollection tempColliders = colliders;
		this.colliders = tempColliders;
	}
	
	public void addColliders(ArrayList<CollisionBox> colliders) {
		for(int i = 0; i<colliders.size(); i++) {
			this.colliders.addCollisionBox(colliders.get(i));
		}
	}
	
	public void addCollider(CollisionBox collider) {
		this.colliders.addCollisionBox(collider);
	}
	
	public void setPosition(Vector2D position) {
		this.colliders.setPosition(position);
	}
	
	public void setSpriteFrames(ArrayList<SpriteInfo> spriteFrames) {
		ArrayList<SpriteInfo> tempFrames = spriteFrames;
		this.spriteFrames = tempFrames;
	}
	
	public void setDefaults() {
		this.setFrameGroups(1);
		this.setSpriteFrames(new ArrayList<SpriteInfo>());
		this.colliders = new CollisionCollection();
		this.descriptionHash = new ArrayList<String>();
		this.hashFrame = 0;
		this.collisionDetectionEnabled = true;
		this.setFrame(0);
		this.setFrameGroup(0);
		this.showBounds = false;
	}
	
	public void addFrame(SpriteInfo frameInfo) {
		SpriteInfo tempInfo = frameInfo;
		this.spriteFrames.add(tempInfo);
	}
	
	public void addHash(String hash) {
		this.descriptionHash.add(hash);
	}
	
	public void nextHashFrame() {
		this.hashFrame++;
		this.hashFrame%=this.descriptionHash.size();
	}
	
	public void setHashFrame(int hashFrame) {
		int tempHashFrame = hashFrame;
		this.hashFrame = tempHashFrame ;
	}
	
	public void setHashFrame(ArrayList<String> hashFrames) {
		for(int i = 0; i<hashFrames.size(); i++) {
			this.descriptionHash.add(hashFrames.get(i));
		}
	}
	
	public void enableShowBounds(boolean state) {
		boolean tempState = state;
		this.showBounds = tempState;
	}
	
	//Outputs
	
	public SpriteInfo nextFrame() {
		this.frame = (this.frame + 1)%(this.spriteFrames.size());
		this.frame = (this.frame)%(this.spriteFrames.size()/this.frameGroups);
		this.frame = this.frame + ((this.spriteFrames.size()/this.frameGroups) * this.frameGroup);
		return(this.spriteFrames.get(this.frame));
	}
	
	public int getCurrentFrameNumber() {
		int tempFrameNumber = this.frame;
		return(tempFrameNumber);
	}

	public String getCurrentDescriptionHash() {
		return(this.descriptionHash.get(this.hashFrame));
	}

	public Vector2D getPosition() {
		return(new Vector2D(this.colliders.getPosition()));
	}
	
	public int getHashFrameNumber() {
		int tempframeNumber = this.hashFrame;
		return(tempframeNumber);
	}
	
	public int getFrameGroup() {
		int tempFrameGroup = this.frameGroup;
		return(tempFrameGroup);
	}
	
	public int getFrameGroups() {
		int tempFrameGroups = this.frameGroups;
		return(tempFrameGroups);
	}
	
	public ArrayList<SpriteInfo> getSpriteFrames(){
		ArrayList<SpriteInfo> tempSpriteFrames = this.spriteFrames;
		return(tempSpriteFrames);
	}
	
	public ArrayList<String> getDescriptionHash(){
		ArrayList<String> tempDescriptionHash = this.descriptionHash;
		return(tempDescriptionHash);
	}
	
	public boolean isCollisionDetectionEnabled() {
		boolean state = this.collisionDetectionEnabled;
		return(state);
	}
	
	public CollisionCollection getColliders() {
		CollisionCollection tempColliders = this.colliders;
		return(tempColliders);
	}
	
	//Utilities
	public String getDescriptionAt(Vector2D pointer) {
		if(this.colliders.collidesWith(pointer)) {
			if(!this.descriptionHash.isEmpty()) {
				String output = this.getCurrentDescriptionHash();
				this.nextHashFrame();
				return(output);	
			}
		}
		return("null");
	}
	
	public boolean collidesWith(InteractableSprite anotherSprite) {
		boolean output = false;
		
		output = this.collisionDetectionEnabled && this.colliders.collidesWith(anotherSprite.getColliders());
		
		return(output);
	}
	
	public boolean collidesWith(ArrayList<InteractableSprite> otherSprites) {
		boolean output = false;
		
		for(int i = 0; i<otherSprites.size(); i++) {
			output = output || this.collidesWith(otherSprites.get(i));
		}
		
		return(output);
	}
	
	public void renderSprite(Control controller) {
		
		controller.addSpriteToFrontBuffer(this.colliders.getPosition().getX(), this.colliders.getPosition().getY(), this.spriteFrames.get(this.frame).getTag());
		
		if(this.showBounds) {
			this.colliders.previewBounds();
		}
	}
}
