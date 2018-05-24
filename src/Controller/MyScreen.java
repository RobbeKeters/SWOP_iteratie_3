package Controller;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Stack;

import Model.Canvas;
import Model.Interaction;
import Model.InvocationMessage;
import Model.Message;
import Model.Party;
import Model.ResultMessage;
import Model.Screen;
import Model.Window;
import Model.Handler.AddInteractionHandler;
import Model.Handler.AddWindowHandler;
import Model.Handler.CloseWindowHandler;
import Model.Handler.EditLabelHandler;
import Model.Handler.OpenDialogBoxHandler;

/**
 * A class that processes input related to the Screen class.
 * 
 * @author Kevin Lavrijssen, Wout Mees, Florent Nander Meijer, Robbe Keters
 */
public class MyScreen {

	/////////////////// Singleton ///////////////////
	/*private static MyScreen instance = null;
	
	private MyScreen() {}
	
	private synchronized static void createInstance() {
	if(instance == null) {instance = new MyScreen();}
	}
	
	public static MyScreen getInstance() {
	if(instance==null) {createInstance();}
	return instance;
	}*/
	
	////////////////////////////////////////////////
	
	private boolean ctrlPressed =  false;
	
	/**
	 * Handles actions affecting a certain Screen initiated by a mouse event.
	 * @param id		The kind of mouse event.
	 * @param x			The x coordinate of the mouse event.
	 * @param y			The y coordinate of the mouse event.
	 * @param screen	The screen to be handled and edited.
	 */
	public void mouseClicked(Mouse id, int x, int y, Screen screen) {
		
		ctrlPressed = false;
		
		// First check if a party label is left in a valid state + check if there are any interactions
		if (!screen.getInteractions().isEmpty() && !EditLabelHandler.editLabelModeParty((Window)screen.getSubWindows().lastElement())) {
			// Determine Canvas 
			Canvas canvas = null;
			
			
			Stack<Canvas> findList = new Stack<Canvas>();
			findList.addAll(screen.getSubWindows());
			boolean found = false;
			Canvas top = findList.lastElement();
			while(!findList.isEmpty() && !found) {
				canvas = findList.pop();
				if( isInArea(x, y, canvas)) {
					found = true;
				}
			}
			// Selected SubWindow must be placed on top of the stack of subWindows
			screen.getSubWindows().remove(canvas);
			screen.getSubWindows().push(canvas);
			
			// Check if the selected canvas was on top of the stack(active canvas)
			if( top == canvas) {
				// Delegate to Interaction
				MyInteraction.mouseClicked(id, x, y, (Window) canvas,((Window) canvas).getInteraction() );
			}
		}
		
		CloseWindowHandler.handle(screen.getSubWindows());
		
		// Find any Empty Interaction(empty interaction =  canvas left) ==> delete empty Interaction
		ArrayList<Interaction> toBeDeletedInteraction = new ArrayList<Interaction>();
		for( Interaction i :screen.getInteractions()) {
			if(i.getSubWindows().isEmpty()) {
				toBeDeletedInteraction.add(i);
			}
		}
		for (Interaction i : toBeDeletedInteraction) {
			screen.getInteractions().remove(i);
		}
	}
	
	/**
	 * Handles actions affecting a certain Screen initiated by a key event.
	 * @param id		The kind of key event.
	 * @param keyCode	The code of the key pressed.
	 * @param keyChar	The key pressed.
	 * @param screen	The screen to be handled and edited.
	 */
	public void keyPressed(int id, int keyCode, char keyChar, Screen screen) {
		
		// ctrl + n
		if( ctrlPressed && keyCode == 78 && (id == KeyEvent.KEY_PRESSED || id == KeyEvent.KEY_TYPED)) {
			AddInteractionHandler.handle(screen.getInteractions(), screen.getSubWindows());
		
		}
		// ctrl + d
		else if ( !screen.getInteractions().isEmpty() && ctrlPressed && keyCode == 68 && (id == KeyEvent.KEY_PRESSED || id == KeyEvent.KEY_TYPED) ) {
			AddWindowHandler.handle(screen.getSubWindows());
		} 
		// ctrl + ENTER
		else if (ctrlPressed && keyCode == 10 && (id == KeyEvent.KEY_PRESSED || id == KeyEvent.KEY_TYPED)){
			Window c = (Window) screen.getSubWindows().lastElement();
			//TODO: voorlopig staat alles hier in, maar ik denk niet dat dat zo correct is...
			boolean found = false;
			if(!c.getParties().isEmpty()){
				for(Party p : c.getParties()){
					if(p.getSelected() || p.getLabel().getSelected()){
						found = true;
						OpenDialogBoxHandler.handle(p, screen);
						break;
					}
				}
			}
			if (!c.getMessages().isEmpty()) {
				System.out.println("test");
				for(Message m : c.getMessages()){
					System.out.println("Message class: " + m.getClass());
					if(m.getSelected() || m.getLabel().getSelected()){
						found = true;
						if(m.getClass().equals(InvocationMessage.class)){
							OpenDialogBoxHandler.handle((InvocationMessage) m, screen);
							break;
						} else {
							OpenDialogBoxHandler.handle((ResultMessage) m, screen);
							break;
						}
					}
				}
			} 
			if(!found)
				OpenDialogBoxHandler.handle(c, screen);
		}
		else {
			ctrlPressed = false;
		}		
		if( id == KeyEvent.KEY_PRESSED && keyCode == 17) {
			ctrlPressed = true;
		} else if(!screen.getInteractions().isEmpty() && (id == KeyEvent.KEY_PRESSED || id == KeyEvent.KEY_TYPED)) {
			if( screen.getSubWindows().lastElement().getClass() == Model.Window.class) { // Alleen naar interaction als het een window is
				MyInteraction.keyPressed(id, keyCode, keyChar, (Window)screen.getSubWindows().lastElement());
			}
		}
	}
	
	/**
	 * Returns if the given coordinate is situated in the area of the window of the given Canvas.
	 * @param x				The given x coordinate.
	 * @param y				The given y coordinate.
	 * @param lastElement	The given canvas.
	 */
	private static boolean isInArea(int x, int y, Canvas lastElement) {
		int xLow = lastElement.getOrigineX() - 4 ;
		int yLow = lastElement.getOrigineY() - 4;
		// "+4" is for resize -> canvas.resize methods
		int xHigh = xLow + lastElement.getWidth() + 8 ;
		int yHigh = yLow + lastElement.getHeight() + 8;
		
		if( x >= xLow && x <= xHigh && y >= yLow && y <= yHigh) {
			return true;
		}
		return false;
	}
}
