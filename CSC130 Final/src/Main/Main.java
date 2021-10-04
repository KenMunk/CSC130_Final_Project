package Main;

import java.awt.Color;
//import java.awt.Toolkit;

import logic.Control;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	public static stopWatchX frameTimer = new stopWatchX(12);
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(){
		// TODO: Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		int displayWidth = 1280;
		int displayHeight = 720;
		//int displayWidth = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
		//int displayHeight = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		
		Color customColor = new Color(0,200,255);
		
		ctrl.addSpriteToFrontBuffer((displayWidth/2)-64, displayHeight-170, "f0");						 				// Add a tester sprite to render list by tag (Remove later! Test only!)
		ctrl.drawString((displayWidth/2)-180, displayHeight-20, "Kenneth Munk says 'Hi'", customColor);		// Test drawing text on screen where you want (Remove later! Test only!)
		
		if(frameTimer.isTimeUp()) {
			
			
			
		}
		
		
		
	}
	
	// Additional Static methods below...(if needed)

}
