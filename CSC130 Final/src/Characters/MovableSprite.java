package Characters;

import Data.Vector2D;
import logic.Control;
import Characters.SpriteData;

public class MovableSprite {
	
	private int currentFrame;
	private int frames;
	private String[] spriteArray;
	private Vector2D spritePosition;
	
	public MovableSprite(String[] spriteArray, int frameCount, Vector2D spritePosition) {
		
		String[] temp = spriteArray;
		this.spriteArray = temp;
		
		int tempCount = frameCount;
		this.frames = tempCount;
		
		Vector2D tempPositon = spritePosition;
		this.spritePosition = new Vector2D(tempPositon.getX(), tempPositon.getY());
		
		this.currentFrame = 0;
		
	}
	
	public void nextFrame(boolean timer) {
		if(timer) {
			this.currentFrame = (this.currentFrame + 1) % this.frames;
		}
	}
	
	public SpriteData renderFrame() {
		
		return(new SpriteData(new Vector2D(this.spritePosition.getX(), this.spritePosition.getY()), this.spriteArray[this.currentFrame]));
		
	}
	
	public void setPosition(Vector2D spritePosition) {
		
		this.spritePosition = new Vector2D(spritePosition.getX(), spritePosition.getY());
		
	}
	
	public void adjustPosition(Vector2D spriteAdjustment) {
		
		this.spritePosition.adjustX(spriteAdjustment.getX());
		this.spritePosition.adjustY(spriteAdjustment.getY());
		
	}
	
}
