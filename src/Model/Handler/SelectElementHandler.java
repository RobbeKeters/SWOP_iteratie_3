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
			if (closeCanvas(window,x,y)) {
				window.setMode(Mode.CLOSING);
			}
			
			if(p == null && l == null) {deselectAll(window);}
			
			if(l!=null) {l.setSelected(true); return;}
			if(p!=null) {p.setSelected(true);}
		}
		
		else if(id == Mouse.PRESSED) {
			
			if(existsSender(window)) {return;}
			Party p = getPartyAt(x, y, window); if(p==null) {System.out.println("NUll_1");}
			Party lifeLine = approxLifeLine(x, window); if(lifeLine==null) {System.out.println("NULL_2");}
			
			int newXorigine = (x-(window.getFramework().getBar().getWidth(window)/2));
			int newYorigine = (y-(window.getFramework().getBar().getHeight()/2));
			
			
			if (resizeLowerRightCornerCanvas(window, x, y)) {
				ResizeWindowHandler.handle(window, x, y, ResizeWindow.ResizeLowerRightCorner);
				// Update Party and message Positions!!
				MoveWindowHandler.updatePartyPositions(window ,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
				MoveWindowHandler.updateMessagePositions(window,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
			}
			else if (resizeLowerLeftCornerCanvas(window, x, y)) {
				ResizeWindowHandler.handle(window, x, y, ResizeWindow.ResizeLowerLeftCorner);
				// Update Party and message Positions!!
				MoveWindowHandler.updatePartyPositions(window ,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
				MoveWindowHandler.updateMessagePositions(window,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
			}
			else if (resizeTopLeftCornerCanvas(window, x, y)) {
				ResizeWindowHandler.handle(window, x, y, ResizeWindow.ResizeTopLeftCorner);
				// Update Party and message Positions!!
				MoveWindowHandler.updatePartyPositions(window ,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
				MoveWindowHandler.updateMessagePositions(window,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
			}
			else if (resizeTopRightCornerCanvas(window, x, y)) {
				ResizeWindowHandler.handle(window, x, y, ResizeWindow.ResizeTopRightCorner);
				// Update Party and message Positions!!
				MoveWindowHandler.updatePartyPositions(window ,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
				MoveWindowHandler.updateMessagePositions(window,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
			}
			else if (resizeXRightCanvas(window, x, y)  ) {
				ResizeWindowHandler.handle(window, x, y, ResizeWindow.ResizeXRight);
				// Update Party and message Positions!!
				MoveWindowHandler.updatePartyPositions(window ,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
				MoveWindowHandler.updateMessagePositions(window,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
			}
			else if (resizeXLeftCanvas(window, x, y)  ) {
				ResizeWindowHandler.handle(window, x, y, ResizeWindow.ResizeXLeft);
				// Update Party and message Positions!!
				MoveWindowHandler.updatePartyPositions(window ,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
				MoveWindowHandler.updateMessagePositions(window,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
			}
			else if (resizeYTopCanvas(window, x, y)  ) {
				ResizeWindowHandler.handle(window, x, y, ResizeWindow.ResizeYTop);
				// Update Party and message Positions!!
				MoveWindowHandler.updatePartyPositions(window ,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
				MoveWindowHandler.updateMessagePositions(window,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
			}
			else if (resizeYLowerCanvas(window, x, y)  ) {
				ResizeWindowHandler.handle(window, x, y, ResizeWindow.ResizeYLower);
				// Update Party and message Positions!!
				MoveWindowHandler.updatePartyPositions(window ,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
				MoveWindowHandler.updateMessagePositions(window,oldXorigine,oldYorigine, window.getOrigineX(),window.getOrigineY());
			}
			else if( moveCanvas(window, x,y)) {
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
			if(m.getClass()!=ResultMessage.class) {	m.getLabel().setSelected(false);}
		}
	}
	
	private static boolean existsSender(Window window) {
		
		for(Party p : window.getParties()) {
			if(p.getRole()==PartyRole.SENDER) {return true;}
		}
		
		return false;
	}
	
	private static boolean closeCanvas(Window window, int xMouse, int yMouse) {
		Button button = window.getFramework().getBar().getButton();
		int buttonOrigineX = button.getOrigineX();
		int buttonOrigineY = button.getOrigineY();
		int upperX = buttonOrigineX + button.getWidth();
		int upperY = buttonOrigineY + button.getHeight();
		if( xMouse >= buttonOrigineX && xMouse <= upperX && yMouse >= buttonOrigineY && yMouse <= upperY ) {
			return true;
		}
		return false;
	}
	
	private static boolean moveCanvas(Window window,int xMouse, int yMouse) {
		// If cursor is in button Area => wait for id==Mouse.SINGLECLICK in handle Method
		if(closeCanvas(window, xMouse,yMouse)) {
			return false;
		}
		
		titleBar bar = window.getFramework().getBar();
		int barOrigineX = bar.getOrigineX();
		int barOrigineY = bar.getOrigineY();
		int upperX = barOrigineX + window.getFramework().getBar().getWidth(window);
		int upperY = barOrigineY + window.getFramework().getBar().getHeight();
		if( xMouse >= barOrigineX && xMouse <= upperX && yMouse >= barOrigineY && yMouse <= upperY ) {
			return true;
		}
		return false;
	}
	
	private static boolean resizeXRightCanvas(Canvas canvas, int xMouse, int yMouse ) {		
		int origineX = canvas.getOrigineX();
		int origineY = canvas.getOrigineY();
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		// Right check 
		if ( (origineX + width -4) <= xMouse && (origineX + width +4) >= xMouse	 && (origineY -4) <= yMouse && (origineY +4 + height) >= yMouse ) {
			return true;
		}
		return false;		
	}
	private static boolean resizeXLeftCanvas(Canvas canvas, int xMouse, int yMouse) {
		int origineX = canvas.getOrigineX();
		int origineY = canvas.getOrigineY();
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		// Left check
		if( (origineX-4) <= xMouse && (origineX+4) >= xMouse && (origineY -4) <= origineY && (origineY + height+4) >= yMouse) {
			return true;
		}
		return false;
	}
	private static boolean resizeYTopCanvas(Canvas canvas, int xMouse, int yMouse) {
		int origineX = canvas.getOrigineX();
		int origineY = canvas.getOrigineY();
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		// Top check
		if( (origineX-4) <= xMouse && xMouse <= (origineX + width +4) && (origineY -4) <= yMouse && (origineY +4) >= yMouse) {
			return true;
		}
		return false;
	}
	private static boolean resizeYLowerCanvas(Canvas canvas,int xMouse, int yMouse) {
		int origineX = canvas.getOrigineX();
		int origineY = canvas.getOrigineY();
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		// Lower check
		if( (origineX-4) <= xMouse && (origineX + width +4) >= xMouse && (origineY +height -4) <= yMouse && (origineY+height+4)>= yMouse ) {
			return true;
		}
		return  false;
	}
	private static boolean resizeLowerRightCornerCanvas(Canvas canvas, int xMouse, int yMouse) {
		int xLow = canvas.getOrigineX() + canvas.getWidth() - 4;
		int xHigh = xLow + 8;
		int yLow = canvas.getOrigineY() + canvas.getHeight() -4;
		int yHigh = yLow + 8;
		if ( xMouse >= xLow && xMouse <= xHigh && yMouse  >= yLow && yMouse <= yHigh) {
			return true;
		}
		return false;
	}
	private static boolean resizeLowerLeftCornerCanvas(Canvas canvas, int xMouse, int yMouse) {
		int xLow = canvas.getOrigineX() - 4;
		int xHigh = xLow + 8;
		int yLow = canvas.getOrigineY() + canvas.getHeight() -4;
		int yHigh = yLow + 8;
		if ( xMouse >= xLow && xMouse <= xHigh && yMouse  >= yLow && yMouse <= yHigh) {
			return true;
		}
		return false;
	}
	private static boolean resizeTopLeftCornerCanvas(Canvas canvas, int xMouse, int yMouse) {
		int xLow = canvas.getOrigineX() - 4;
		int xHigh = xLow + 8;
		int yLow = canvas.getOrigineY()  -4;
		int yHigh = yLow + 8;
		if ( xMouse >= xLow && xMouse <= xHigh && yMouse  >= yLow && yMouse <= yHigh) {
			return true;
		}
		return false;
	}
	private static boolean resizeTopRightCornerCanvas(Canvas canvas, int xMouse, int yMouse) {
		int xLow = canvas.getOrigineX() + canvas.getWidth() - 4;
		int xHigh = xLow + 8;
		int yLow = canvas.getOrigineY() -4;
		int yHigh = yLow + 8;
		if ( xMouse >= xLow && xMouse <= xHigh && yMouse  >= yLow && yMouse <= yHigh) {
			return true;
		}
		return false;
	}
}
