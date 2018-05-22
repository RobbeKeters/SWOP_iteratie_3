package Model.Handler;

import java.util.Random;
import java.util.Stack;

import Model.Canvas;
import Model.Interaction;
import Model.Party;
import Model.Window;

/**
 * A handler that handles the actions of a window being added.
 */
public class AddWindowHandler extends Handler{
	
	// private static Random randNumberPos = new Random();
	private static final int c = 10;
	
	/**
	 * Handles a window being added to the given stack of subwindows.
	 * 
	 * @param subWindows 	The given stack of subwindows.
	 */
	public static void handle(Stack<Canvas> subWindows)throws IllegalArgumentException {
		
		Window last = null;
		if(subWindows.lastElement().getClass()==Window.class) {
			last = (Window)subWindows.lastElement();
		}
		else {throw new IllegalArgumentException("Type of active canvas not recognized");}

		// Add new Subwindow to current Interaction
		Interaction i = last.getInteraction();
		//int xOrigineRandom = randNumberPos.nextInt(250);
		//int yOrigineRandom = randNumberPos.nextInt(250);
		
		int origin = c*subWindows.size();
		
		Window w = new Window(subWindows.lastElement().getWidth(),subWindows.lastElement().getHeight(),origin,origin,i);
		
		// Clone Parties 
		for ( Party p : last.getParties()) {
			Party partyToAdd = (Party) p.clone();
			w.addParty(partyToAdd);
		}
		
		int oldXorigine = subWindows.lastElement().getOrigineX();
		int oldYorigine = subWindows.lastElement().getOrigineY();
		int newXorigine = w.getOrigineX();
		int newYorigine = w.getOrigineY();
		MoveWindowHandler.updatePartyPositions(w , oldXorigine, oldYorigine, newXorigine, newYorigine);
		
		Interaction.copyMessages(last, w);
		
		/**
		// Clone Messages
		for( Message m : subWindows.lastElement().getMessages()) {
			Message messageToAdd = (Message) m.clone();
			c.addMessage(messageToAdd);
		}
		SelectElementHandler.updateMessagePositions(c , oldXorigine, oldYorigine, newXorigine, newYorigine);
		for( Message m : c.getMessages()) {
			Interaction.updateMessagePropertiesAfterClone(subWindows.lastElement(),c, m);
		}
		*/
		
		i.addWindow(w);
		subWindows.push(w);
	}

}
