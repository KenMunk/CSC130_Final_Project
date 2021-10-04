package Characters;

import Data.Vector2D;
import logic.Control;

public class MovableSprite {
	
	private int currentFrame;
	private String[] spriteArray;
	private Vector2D spritePosition;
	
	private Control existingControl;
	
	public void movableSprite(String[] spriteArray, Vector2D spritePosition, Control existingControl) {
		
		String[] temp = spriteArray;
		this.spriteArray = temp;
		
		Vector2D tempPositon = spritePosition;
		this.spritePosition = new Vector2D(tempPositon.getX(), tempPositon.getY());
		
		this.currentFrame = 0;
		
	}
	
	public void nextFrame(boolean timer) {
		if(timer) {
			this.currentFrame = (this.currentFrame + 1) % this.spriteArray.length;
		}
	}
	
	public void renderFrame() {
		

		this.existingControl.addSpriteToFrontBuffer(this.spritePosition.getX(), this.spritePosition.getY(), this.spriteArray[this.currentFrame]);
		
	}
	
	public void setPosition(Vector2D spritePosition) {
		
		this.spritePosition = new Vector2D(spritePosition.getX(), spritePosition.getY());
		
	}
	
	public void adjustPosition(Vector2D spriteAdjustment) {
		
		this.spritePosition.adjustX(spriteAdjustment.getX());
		this.spritePosition.adjustY(spriteAdjustment.getY());
		
	}
	
}
