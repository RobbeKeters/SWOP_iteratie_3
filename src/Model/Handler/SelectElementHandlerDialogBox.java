package Model.Handler;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Controller.Mouse;
import Model.Canvas;
import Model.Control;
import Model.Mode;
import Model.ResizeWindow;
import Model.DialogBox;

public class SelectElementHandlerDialogBox extends Handler {
	
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
		}
	}

	public static void handleKey(int id, int keyCode, char keyChar, DialogBox db) {;
		if(id == KeyEvent.KEY_PRESSED && keyCode == KeyEvent.VK_TAB ) {
			// Switch to next Control (Button or textBox)
			Control activeControl = db.findActiveControl();
			ArrayList<Control> listControls = db.getListControls();
			if( listControls.size() > 1) {
				listControls.remove(activeControl);
				activeControl.setSelectedControl(false);
				Control nextControl = listControls.get(0);
				nextControl.setSelectedControl(true);
				listControls.add(listControls.size()-1, activeControl);
			}
		}
	}
}
