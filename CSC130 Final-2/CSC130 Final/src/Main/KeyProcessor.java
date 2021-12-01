/* This will handle the "Hot Key" system. */

package Main;

import logic.Control;
import timer.stopWatchX;

public class KeyProcessor{
	// Static Fields
	private static char last = ' ';			// For debouncing purposes
	private static stopWatchX sw = new stopWatchX(250);
	private static String triggerTemplate = "%s has been triggered";
	
	// Static Method(s)
	public static void processKey(char key){
		if(key == ' ')				return;
		// Debounce routine below...
		if(key == last)
			if(sw.isTimeUp() == false)			return;
		last = key;
		sw.resetWatch();
		
		/* TODO: You can modify values below here! */
		switch(key){
		//[Ken_Munk] Adding the option to use the escape key instead of using % simply because it's more intuitive for the time being
		//[Ken_Munk] Unicode table seen here: https://unicode-table.com/en/#001B
		case '\u001B':
		case '%':								// ESC key
			System.exit(0);
			break;
		
		//Added 2021-11-28 -- Checkpoint 5	
		// Adding only program triggers for lowercase w, a, s, d, and spacebar
			
		case 'w':
			Main.trigger = String.format(triggerTemplate, "w");
			break;
		case 'a':
			Main.trigger = String.format(triggerTemplate, "a");
			break;
		case 's':
			Main.trigger = String.format(triggerTemplate, "s");
			break;
		case 'd':
			Main.trigger = String.format(triggerTemplate, "d");
			break;
		case '$':
			Main.trigger = String.format(triggerTemplate, "space");
			break;
			
		//End of 2021-11-28 -- Checkpoint 5 Additions
		case 'm':
			// For mouse coordinates
			Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
			break;
		}
	}
}