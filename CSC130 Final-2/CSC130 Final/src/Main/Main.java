package Main;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.HashMap;

import Data.SpriteInfo;
import Data.Vector2D;
import Data.InteractableSprite;
import Data.CollisionBox;
import Data.CollisionCollection;

import logic.Control;
import timer.stopWatchX;
import FileIO.EZFileRead;

public class Main{
	// Fields (Static) below...

	public static stopWatchX frameTimer = new stopWatchX(50);
	
	/*// All of the stuff from the checkpoints
	public static stopWatchX lineTimer = new stopWatchX(3000);
	
	public static ArrayList<spriteInfo> sprites = new ArrayList<>();
	
	public static EZFileRead reader;
	
	public static HashMap<String, String> dodoLines = new HashMap<String, String>();
	public static StringTokenizer tokenizer;
	public static String key, value;
	public static int dodoCursor = 1;
	//*/
	
	//Game text
	public static HashMap<String, String> descriptionText = new HashMap<String, String>();
	
	//Stuff with colliders
	public static ArrayList<InteractableSprite> walls;
	public static ArrayList<InteractableSprite> interactableObjects;
	public static int[] percentDigitIndicatorIDs = new int[3];

	public static InteractableSprite gameGoal;
	public static InteractableSprite bugPlayer;
	public static InteractableSprite idlePlayer;
	
	public static Vector2D displayDimensions;
	
	public static boolean inputDetected = false;
	public static boolean isIdle = true;
	
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
		
		
		//display prep
		int displayWidth = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
		int displayHeight = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		displayDimensions = new Vector2D(displayWidth,displayHeight);
		
		/*
		Vector2D totalTraversal = new Vector2D(1536,-480);
		Vector2D traversal = new Vector2D(-128,580);
		int stepsTraversed = 160;
		//*/
		
		//Play Space Prep
		interactableObjects = new ArrayList<InteractableSprite>();
		
		InteractableSprite bsodSmile = new InteractableSprite(new SpriteInfo("smile"));
		bsodSmile.setPosition(new Vector2D(100,100));
		bsodSmile.addCollider(new CollisionBox(Vector2D.zero(),new Vector2D(64,128)));
		
		interactableObjects.add(bsodSmile);
		
		InteractableSprite bsodText = new InteractableSprite(new SpriteInfo("sText"));
		Vector2D bsodTextPosition = bsodSmile.getPosition();
		bsodTextPosition.adjustY(128+10);
		bsodText.setPosition(bsodTextPosition);
		bsodText.addCollider(new CollisionBox(Vector2D.zero(),new Vector2D(61,110)));
		bsodText.addCollider(new CollisionBox(Vector2D.zero(),new Vector2D(700,73)));
		
		interactableObjects.add(bsodText);
		
		for(int i = 0; i<3; i++) {
			initializePercentageGraphics(i);
			
		}
		
		InteractableSprite percentageLabel = new InteractableSprite(new SpriteInfo("pComplete"));
		Vector2D percentLabelPosition = interactableObjects.get(interactableObjects.size() - 1).getPosition();
		percentLabelPosition.adjustX(24);
		percentageLabel.setPosition(percentLabelPosition);
		percentageLabel.addCollider(new CollisionBox(Vector2D.zero(), new Vector2D(190,42)));
		
		interactableObjects.add(percentageLabel);
		
		
		
		//Walls Pprep
		
		
		//Player prep
		
		bugPlayer = new InteractableSprite(new SpriteInfo("qIdle"));
		bugPlayer.setPosition(new Vector2D(100,450));
		idlePlayer = new InteractableSprite(new SpriteInfo("qIdle"));
		idlePlayer.setPosition(new Vector2D(100,450));
		
		
		//GoalPrep
		
		gameGoal = new InteractableSprite(new SpriteInfo("goal"));
		gameGoal.setPosition(new Vector2D(900,200));
		gameGoal.addCollider(new CollisionBox(new Vector2D(100,100),new Vector2D(56,56)));
		
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
		
		//ctrl.addSpriteToFrontBuffer(150, 400, "n1");
		//ctrl.addSpriteToFrontBuffer(10, 10, "refPt");
		
		for(int i = 0; i<interactableObjects.size(); i++) {
			interactableObjects.get(i).renderSprite(ctrl);
		}
		
		
		
		
		if(frameTimer.isTimeUp()) {
			frameTimer.resetWatch();
			if(inputDetected) {
				isIdle = false;
				if(!trigger.equals("")) {
					Vector2D currentPosition, updatedPosition;
					currentPosition = bugPlayer.getPosition();
					updatedPosition = bugPlayer.getPosition();
					switch(trigger.charAt(0)) {
					case 'w':
						bugPlayer.setFrameGroup(0);
						updatedPosition.adjustY(-10);
						break;
					case 'a':
						bugPlayer.setFrameGroup(3);
						updatedPosition.adjustX(-10);
						break;
					case 's':
						bugPlayer.setFrameGroup(2);
						updatedPosition.adjustY(10);
						break;
					case 'd':
						bugPlayer.setFrameGroup(1);
						updatedPosition.adjustX(10);
						break;
					case '_':
						break;
					default:
						break;
					}

					bugPlayer.setPosition(updatedPosition);
					boolean collisionDetected = true;
					
					//collision detection is broken(/`A)/
					collisionDetected = collisionDetected && bugPlayer.collidesWith(interactableObjects);
					
					if(collisionDetected) {
						bugPlayer.setPosition(currentPosition);
					}
				}
				else {
					isIdle = true;
				}
				trigger = "";
				bugPlayer.nextFrame();
			}
		}
		
		if(!inputDetected) {
			if(!trigger.equals("")) {
				inputDetected = true;
				
				ArrayList<SpriteInfo> bugPlayerFrames = new ArrayList<SpriteInfo>();
				for(int direction = 0; direction < 4; direction++) {
					for(int frame = 0; frame < 8; frame++) {
						bugPlayerFrames.add(new SpriteInfo(String.format("q%d0%d", direction, frame)));
					}
				}
				
				bugPlayer = new InteractableSprite(bugPlayerFrames);
				bugPlayer.setFrameGroups(4);
				bugPlayer.enableCollisionDetection(true);
				bugPlayer.setPosition(new Vector2D(100,450));
				bugPlayer.addCollider(new CollisionBox(new Vector2D(10,10), new Vector2D(76,76)));
			}
			
		}
		
		if(!isIdle) {
			bugPlayer.renderSprite(ctrl);
		}
		else {
			
			idlePlayer.setPosition(bugPlayer.getPosition());
			idlePlayer.renderSprite(ctrl);
		}
		
		gameGoal.renderSprite(ctrl); 
		
	}
	
	// Additional Static methods below...(if needed)
	
	private static void initializePercentageGraphics(int i) {
		int tempI = i;
		System.gc();

		InteractableSprite firstDigit = new InteractableSprite();
		for(int d = 0; d<10; d++) {
			SpriteInfo tempDigitFrame = new SpriteInfo(String.format("n%d", d));
			firstDigit.addFrame(tempDigitFrame);
		}
		Vector2D numberPosition = new Vector2D(100,366);
		/*
		if(i==0) {
			numberPosition.adjustY(128);
		}
		//*/
		numberPosition.adjustX(24*tempI);
		firstDigit.setPosition(numberPosition);
		firstDigit.addCollider(new CollisionBox(Vector2D.zero(),new Vector2D(24,42)));

		interactableObjects.add(firstDigit);
		percentDigitIndicatorIDs[0] = interactableObjects.size() - 1;
		
		System.gc();
	}

}
