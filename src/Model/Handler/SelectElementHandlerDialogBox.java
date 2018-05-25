package Model.Handler;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Controller.Mouse;
import Model.Canvas;
import Model.Control;
import Model.Mode;
import Model.ResizeWindow;
import Model.DialogBox;

/**
 * A handler tht handles the actions of an element within a dialog box being selected.
 */
public class SelectElementHandlerDialogBox extends Handler {
	
	/**
	 * Handles a mouse event in a given dialog box.
	 * @param db	The given dialog box.
	 * @param x		The x coordinate of the mouse event.
	 * @param y		The y coordinate of the mouse event.
	 * @param id	The type of mouse event.
	 */
	public static void handle(DialogBox db, int x, int y, Mouse id) {
		
		int oldXorigine = db.getOrigineX();
		int oldYorigine = db.getOrigineY();
		
		
		if(id==Mouse.SINGLECLICK) {
			
			// Closing Canvas 
			if (Canvas.closeCanvas(db,x,y)) {
				db.setMode(Mode.CLOSING);
			} else {
				db.handleMouse(id, x,y);
			}
		}
		
		else if(id == Mouse.PRESSED || id == Mouse.DRAGGED) {
		
			int newXorigine = (x-(db.getFramework().getBar().getWidth(db)/2));
			int newYorigine = (y-(db.getFramework().getBar().getHeight()/2));
		
			if (Canvas.resizeLowerRightCornerCanvas(db, x, y)) {
				ResizeCanvasHandler.handle(db, x, y, ResizeWindow.ResizeLowerRightCorner);
			}
			else if (Canvas.resizeLowerLeftCornerCanvas(db, x, y)) {
				ResizeCanvasHandler.handle(db, x, y, ResizeWindow.ResizeLowerLeftCorner);
			}
			else if (Canvas.resizeTopLeftCornerCanvas(db, x, y)) {
				ResizeCanvasHandler.handle(db, x, y, ResizeWindow.ResizeTopLeftCorner);
			}
			else if (Canvas.resizeTopRightCornerCanvas(db, x, y)) {
				ResizeCanvasHandler.handle(db, x, y, ResizeWindow.ResizeTopRightCorner);
			}
			else if (Canvas.resizeXRightCanvas(db, x, y)  ) {
				ResizeCanvasHandler.handle(db, x, y, ResizeWindow.ResizeXRight);
			}
			else if (Canvas.resizeXLeftCanvas(db, x, y)  ) {
				ResizeCanvasHandler.handle(db, x, y, ResizeWindow.ResizeXLeft);
			}
			else if (Canvas.resizeYTopCanvas(db, x, y)  ) {
				ResizeCanvasHandler.handle(db, x, y, ResizeWindow.ResizeYTop);
			}
			else if (Canvas.resizeYLowerCanvas(db, x, y)  ) {
				ResizeCanvasHandler.handle(db, x, y, ResizeWindow.ResizeYLower);
			}
			else if( Canvas.moveCanvas(db, x,y)) {
				DialogBox.updatePositionsAttributes(db, newXorigine, newYorigine);
			}
		} else {
			db.handleMouse(id, x, y);
		}
	}
	
	/**
	 * Handles a key event in a given dialog box.
	 * @param id		The type of key event.
	 * @param keyCode	The key code of the key event.
	 * @param keyChar	The key character of the key event.
	 * @param db		The given dialog box.
	 */
	public static void handleKey(int id, int keyCode, char keyChar, DialogBox db) {
		db.handleKey(id, keyCode, keyChar); // let the specific DB object handle it
	}
}
