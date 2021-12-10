package Data;

import Data.SpriteInfo;
import Data.Vector2D;
import java.util.ArrayList;

public class MovableSprite {
	
	private int frame;
	private ArrayList<SpriteInfo> spriteFrames;
	private Vector2D position;
	
	public MovableSprite() {
		this.setDefaults();
	}

	public MovableSprite(ArrayList<SpriteInfo> spriteFrames) {
		this.setDefaults();
		this.setSpriteFrames(spriteFrames);
	}
	
	public void setFrame(int frameNumber) {
		int tempFrame = frameNumber;
		this.frame = tempFrame;
	}
	
	public void setPosition(Vector2D position) {
		Vector2D tempPosition = position;
		this.position = tempPosition;
	}
	
	public void setSpriteFrames(ArrayList<SpriteInfo> spriteFrames) {
		ArrayList<SpriteInfo> tempFrames = spriteFrames;
		this.spriteFrames = tempFrames;
	}
	
	public void setDefaults() {
		this.setFrame(0);
		this.setPosition(new Vector2D(0,0));
		this.setSpriteFrames(new ArrayList<SpriteInfo>());
	}
	
	public void addFrame(SpriteInfo frameInfo) {
		SpriteInfo tempInfo = frameInfo;
		this.spriteFrames.add(tempInfo);
	}
	
	public SpriteInfo nextFrame() {
		this.frame = (this.frame + 1)%this.spriteFrames.size();
		return(this.spriteFrames.get(this.frame));
	}
	
	public Vector2D moveBy(Vector2D increment) {
		this.position.adjust(increment);
		return(this.position);
	}
	
}
