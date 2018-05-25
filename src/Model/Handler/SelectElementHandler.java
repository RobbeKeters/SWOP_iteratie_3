package Model.Handler;
import Controller.Mouse;
import Model.Button;
import Model.Canvas;
import Model.Label;
import Model.Message;
import Model.Mode;
import Model.Party;
import Model.PartyRole;
import Model.ResultMessage;
import Model.Window;
import Model.ResizeWindow;
import Model.titleBar;

/**
 * A handler that handles the actions of an element being selected.
 */
public class SelectElementHandler extends Handler {
	
	/*
	 * Assuming label coordinates refer to upper left corner
	 */
	
	/*
	 * id = 0 : Mouse Clicked 
	 * -> If mouse coordinates in label
	 * 		Then select label
	 * 	  Else do nothing
	 * 
	 * 
	 * id = 1 : Mouse Pressed
	 * -> If mouse coordinates in party
	 * 		Then select party
	 * 	  Else If x is on party lifeline
	 * 		Then select lifeline
	 * 	  Else do nothing
	 * 
	 * id = 2 : Mouse Released
	 * -> If party is selected
	 * 		Then deselect
	 *    Else do nothing
	 *
	 */
	
	/**
	 * Handles an element being selected.
	 * @param window		The canvas to edit.
	 * @param x				The x coordinate of the mouse event used to handle these events.
	 * @param y				The y coordinate of the mouse event used to handle these events.
	 * @param id			The kind of mouse event.
	 */
	public static void handle(Window window, int x, int y, Mouse id) {
		
		int oldXorigine = window.getOrigineX();
		int oldYorigine = window.getOrigineY();
		
		
		if(id==Mouse.SINGLECLICK) {
			
			// LET OP!
			deselectAll(window);
			
			Label l = getLabelAt(x, y, window);
			Party p = getPartyAt(x, y, window);
			
			// Closing Canvas 
			if (Canvas.closeCanvas(window,x,y)) {
				window.setMode(Mode.CLOSING);
			}
			
			if(p == null && l == null) {deselectAll(window);}
			
			if(l!=null) {l.setSelected(true); return;}
			if(p!=null) {p.setSelected(true);}
		}
		
		else if(id == Mouse.PRESSED || id == Mouse.DRAGGED) {
			
			if(existsSender(window)) {return;}
			Party p = getPartyAt(x, y, window); if(p==null) {System.out.println("NUll_1");}
			Party lifeLine = approxLifeLine(x, window); if(lifeLine==null) {System.out.println("NULL_2");}
			
			int newXorigine = (x-(window.getFramework().getBar().getWidth(window)/2));
			int newYorigine = (y-(window.getFramework().getBar().getHeight()/2));
			
			
			if (Canvas.resizeLowerRightCornerCanvas(window, x, y)) {
				ResizeCanvasHandler.handle(window, x, y, ResizeWindow.ResizeLowerRightCorner);
				// Update Party and message Positions!!
				MoveWindowHandler.updatePartyPositions(window ,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
				MoveWindowHandler.updateMessagePositions(window,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
			}
			else if (Canvas.resizeLowerLeftCornerCanvas(window, x, y)) {
				ResizeCanvasHandler.handle(window, x, y, ResizeWindow.ResizeLowerLeftCorner);
				// Update Party and message Positions!!
				MoveWindowHandler.updatePartyPositions(window ,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
				MoveWindowHandler.updateMessagePositions(window,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
			}
			else if (Canvas.resizeTopLeftCornerCanvas(window, x, y)) {
				ResizeCanvasHandler.handle(window, x, y, ResizeWindow.ResizeTopLeftCorner);
				// Update Party and message Positions!!
				MoveWindowHandler.updatePartyPositions(window ,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
				MoveWindowHandler.updateMessagePositions(window,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
			}
			else if (Canvas.resizeTopRightCornerCanvas(window, x, y)) {
				ResizeCanvasHandler.handle(window, x, y, ResizeWindow.ResizeTopRightCorner);
				// Update Party and message Positions!!
				MoveWindowHandler.updatePartyPositions(window ,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
				MoveWindowHandler.updateMessagePositions(window,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
			}
			else if (Canvas.resizeXRightCanvas(window, x, y)  ) {
				ResizeCanvasHandler.handle(window, x, y, ResizeWindow.ResizeXRight);
				// Update Party and message Positions!!
				MoveWindowHandler.updatePartyPositions(window ,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
				MoveWindowHandler.updateMessagePositions(window,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
			}
			else if (Canvas.resizeXLeftCanvas(window, x, y)  ) {
				ResizeCanvasHandler.handle(window, x, y, ResizeWindow.ResizeXLeft);
				// Update Party and message Positions!!
				MoveWindowHandler.updatePartyPositions(window ,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
				MoveWindowHandler.updateMessagePositions(window,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
			}
			else if (Canvas.resizeYTopCanvas(window, x, y)  ) {
				ResizeCanvasHandler.handle(window, x, y, ResizeWindow.ResizeYTop);
				// Update Party and message Positions!!
				MoveWindowHandler.updatePartyPositions(window ,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
				MoveWindowHandler.updateMessagePositions(window,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
			}
			else if (Canvas.resizeYLowerCanvas(window, x, y)  ) {
				ResizeCanvasHandler.handle(window, x, y, ResizeWindow.ResizeYLower);
				// Update Party and message Positions!!
				MoveWindowHandler.updatePartyPositions(window ,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
				MoveWindowHandler.updateMessagePositions(window,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
			}
			else if( Canvas.moveCanvas(window, x,y)) {
				MoveWindowHandler.handle(window ,oldXorigine,oldYorigine, newXorigine,newYorigine);
			}
			else if(p  != null) {
				window.setMovePartyMode();
				p.setSelected(true);
				System.out.println("MovePartyMode");
				System.out.println("PARTY SELECTED: "+p.getSelected());}
			else if(lifeLine != null){
				window.setAddMessageMode();
				lifeLine.setSelected(true);
				lifeLine.makeSender();
				lifeLine.setSelectedYPosition(y);
				System.out.println("AddMessageMode");
				System.out.println("LifeLine SELECTED: "+lifeLine.getSelected());}
		}
		
		else if ( id == Mouse.RELEASED && window.getMode() == Mode.ADDMESSAGE){
			resetRoles(window);
			window.setDefaultMode();
			System.out.println("######## Releasing Button + Editing Label Message ########");
		}
		else if(id == Mouse.RELEASED && window.getMode()!=Mode.DEFAULT) {
			deselectAll(window);
			resetRoles(window);
			window.setDefaultMode();
			System.out.println("######## Releasing Button ########");
		}
	}
	
	/**
	 * Deselects all elements in th given window.
	 * @param window	The given window.
	 */
	public static void deselectAll(Window window) {
		deselectParties(window);
		deselectMessages(window);
	}
	
	private static void deselectParties(Window window) {
		for(Party p : window.getParties()) {
				p.setSelected(false);
				p.getLabel().setSelected(false);
		}
	}

	private static void deselectMessages(Window window) {
		for(Message m : window.getMessages()) {
			m.setSelected(false);
			m.getLabel().setSelected(false);
		}
	}
	
	private static boolean existsSender(Window window) {
		
		for(Party p : window.getParties()) {
			if(p.getRole()==PartyRole.SENDER) {return true;}
		}
		
		return false;
	}
}
