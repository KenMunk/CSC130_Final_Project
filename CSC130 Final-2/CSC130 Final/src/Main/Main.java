package Main;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.HashMap;

import Data.spriteInfo;
import Data.Vector2D;
import logic.Control;
import timer.stopWatchX;
import FileIO.EZFileRead;

public class Main{
	// Fields (Static) below...

	public static stopWatchX frameTimer = new stopWatchX(140);
	
	/*// All of the stuff from the checkpoints
	public static stopWatchX lineTimer = new stopWatchX(3000);
	
	public static ArrayList<spriteInfo> sprites = new ArrayList<>();
	
	public static EZFileRead reader;
	
	public static HashMap<String, String> dodoLines = new HashMap<String, String>();
	public static StringTokenizer tokenizer;
	public static String key, value;
	public static int dodoCursor = 1;
	//*/
	
	public static Vector2D displayDimensions;
	
	//Added 2021-11-28
	public static String trigger = "";
	//End of additions 2021-11-28
	
	// End Static fields...
	
	public static void main(String[] args) {
		
		Control ctrl = new Control();				// Do NOT remove!
		
		
		ctrl.gameLoop();							// Do NOT remove!

		
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(){
		// TODO: Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		
		//displayWidth = 1280;
		//displayHeight = 720;
		
		//startingPosition = -128,580
		//endingPosition = 1408,100
		
		//totalTraversal = 1536,-100
		
		int displayWidth = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
		int displayHeight = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		displayDimensions = new Vector2D(displayWidth,displayHeight);
		/*
		Vector2D totalTraversal = new Vector2D(1536,-480);
		Vector2D traversal = new Vector2D(-128,580);
		int stepsTraversed = 160;
		//*/
		
		
		/*
		cursor = 0;

		sprites.add(new spriteInfo(traversal,String.format("d%d", 0)));
		
		for(int i = 0; i<stepsTraversed; i++) {
			traversal.adjustX(totalTraversal.stepsFor(stepsTraversed).getX()+(int)(1.5*(i%8))-(2*(4-(i%4))));
			traversal.adjustY(totalTraversal.stepsFor(stepsTraversed).getY());
			sprites.add(new spriteInfo(traversal,String.format("d%d", i%8)));
		}
		
		reader = new EZFileRead("SpecTalk.txt");
		for(int i = 0; i<reader.getNumLines(); i++) {
			tokenizer = new StringTokenizer(reader.getLine(i), "*");
			
			key = tokenizer.nextToken();
			value = tokenizer.nextToken();
			
			dodoLines.put(key, value);
		}//*/
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		/*
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		//int displayWidth = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
		//int displayHeight = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		
		Color customColor = new Color(0,200,255);
		
		if(frameTimer.isTimeUp()) {
			frameTimer.resetWatch();
			cursor++;
			cursor= cursor%sprites.size();
		}
		
		if(lineTimer.isTimeUp()) {
			lineTimer.resetWatch();
			dodoCursor = (dodoCursor%5)+1;
		}
		
		spriteInfo displaySprite = sprites.get(cursor);
		ctrl.addSpriteToFrontBuffer(displaySprite.getCoords().getX(), displaySprite.getCoords().getY(), displaySprite.getTag());
		ctrl.addSpriteToFrontBuffer(720,480, displaySprite.getTag());
		
		ctrl.addSpriteToFrontBuffer((displayDimensions.getX()/2)-64, displayDimensions.getY()-170, "f0");						 				// Add a tester sprite to render list by tag (Remove later! Test only!)
		ctrl.drawString((displayDimensions.getX()/2)-180, displayDimensions.getY()-20, String.format("Kenneth Munk says 'Should render %s at %s'",displaySprite.getTag(),displaySprite.getCoords().toString()), customColor);		// Test drawing text on screen where you want (Remove later! Test only!)
		ctrl.drawString((displayDimensions.getX()/2)-180, displayDimensions.getY()-200, trigger,customColor);
		
		//CHECKPOINT 4 Spec Line 5
		if(!dodoLines.isEmpty()) {
			ctrl.drawString(100, 250, dodoLines.get(String.format("line%d", dodoCursor)), customColor);	
		}
		///* */
		
		
		
		 
		
	}
	
	// Additional Static methods below...(if needed)

}
