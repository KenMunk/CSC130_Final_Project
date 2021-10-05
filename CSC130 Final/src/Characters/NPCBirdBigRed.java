package Characters;

import Data.Vector2D;
import logic.Control;
import Characters.SpriteData;

public class NPCBirdBigRed {
	
	private MovableSprite birdSprite;
	
	private Vector2D[] screenPositionsNext;
	private Vector2D[] screenPositionsPrev;
	
	private int frameCount;
	private int nextFrames;
	private int prevFrames;
	private int stepFrame;
	
	public NPCBirdBigRed(Vector2D start, Vector2D end, int frames) {
		
		this.screenPositionsNext = new Vector2D[frames];
		
		Vector2D temp = new Vector2D(start);
		Vector2D step = end.difference(start).stepsFor(frames);
		
		//getting the first position done
		screenPositionsNext[0] = new Vector2D(temp);
		
		for(int i = 1; i<frames; i++) {
			temp.adjust(step);
			screenPositionsNext[i] = new Vector2D(temp);
		}
		
		//this.stepFrame = 0;
		
		int tempframes = frames;
		this.frameCount = tempframes;
		this.nextFrames = tempframes;
		this.prevFrames = 0;
		
		String[] keyFrames = new String[16];
		
		keyFrames[00] = "b00";
		keyFrames[1] = "b01";
		keyFrames[2] = "b02";
		keyFrames[3] = "b03";
		keyFrames[4] = "b04";
		keyFrames[5] = "b05";
		keyFrames[6] = "b06";
		keyFrames[7] = "b07";
		keyFrames[8] = "b08";
		keyFrames[9] = "b09";
		keyFrames[10] = "b10";
		keyFrames[11] = "b11";
		keyFrames[12] = "b12";
		keyFrames[13] = "b13";
		keyFrames[14] = "b14";
		keyFrames[15] = "b15";
		
		this.birdSprite = new MovableSprite(keyFrames, 16, start);
		
	}
	
	public void nextFrame(boolean timer) {
		this.birdSprite.nextFrame(timer);
		
		if(timer) {
			/**
			//THIS IS TOTAL GARBAGE
			Vector2D[] temp = new Vector2D[this.stepFrame + 1];
			
			if(this.prevFrames > 0) {
				for(int i = 0; i<this.prevFrames; i++) {
					temp[i] = new Vector2D(this.screenPositionsPrev[i]);
				}
			}
			
			temp[this.stepFrame] = this.screenPositionsNext[0];
			
			this.birdSprite.setPosition(this.screenPositionsNext[0]);
			
			this.screenPositionsPrev = temp;
			
			if(this.nextFrames -1 > 0) {
				temp = new Vector2D[this.screenPositionsNext.length -1];
				
				for(int i = 1; i<this.screenPositionsNext.length; i++) {
					
					temp[i-1] = new Vector2D(this.screenPositionsNext[i]);
					
				}
				
			}
			else if(this.nextFrames -1 == 0) {
				temp = new Vector2D[this.screenPositionsNext.length -1];
			}
			
			
			if(this.nextFrames == 0) {
				this.screenPositionsNext = new Vector2D[this.prevFrames];
				
				for(int i = 0; i<this.prevFrames; i++) {
					
					this.screenPositionsNext[i] = new Vector2D(this.screenPositionsPrev[i]);
					
				}
				
			}
			
			this.prevFrames++;
			this.nextFrames--;
			
			this.stepFrame++;
			this.stepFrame = this.stepFrame%this.frameCount;
			
			if(this.stepFrame == 0) {
			}
			
			**/
			
			Vector2D tempPosition = this.screenPositionsNext[0];
			
			this.birdSprite.setPosition(tempPosition);
			
			this.nextFrames--;
			this.prevFrames++;

			Vector2D[] tempNext;
			Vector2D[] tempPrev;
			if(this.nextFrames >= 0) {
				tempNext = new Vector2D[this.nextFrames];
				if(this.nextFrames > 0) {
					for(int i = 0; i<this.nextFrames; i++) {
						tempNext[i] = this.screenPositionsNext[i+1];
					}
				}
				this.screenPositionsNext = tempNext;
			}
			if(this.prevFrames >= 0) {
				tempPrev = new Vector2D[this.prevFrames];
				if(this.prevFrames > 0) {
					tempPrev[this.prevFrames-1] = tempPosition;
					if(this.prevFrames - 1 > 0) {
						for(int i = 0; i<this.prevFrames-1; i++) {
							tempPrev[i] = this.screenPositionsPrev[i];
						}
					}
				}
				this.screenPositionsPrev = tempPrev;
			}
			
			if(this.nextFrames == 0) {
				Vector2D[] temp = new Vector2D[this.prevFrames];
				temp = this.screenPositionsPrev;
				
				this.screenPositionsNext = temp;
				this.screenPositionsPrev = null;
				this.nextFrames = this.prevFrames;
				this.prevFrames = 0;
			}
			
			
			
			
		}
	}
	
	public SpriteData render() {
		return(this.birdSprite.renderFrame());
	}
	
	
	
	
	
}
