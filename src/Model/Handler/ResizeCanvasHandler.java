package Model.Handler;

import Model.Canvas;
import Model.Message;
import Model.Party;
import Model.ResizeWindow;
import Model.Window;

/**
 * A handler that handles the actions of a window being resized.
 */
public class ResizeCanvasHandler extends Handler{

	/**
	 * Handles the resizing of a window
	 * 
	 * @param canvas	The canvas being resized.
	 * @param x			The new x coordinate of the part being resized.
	 * @param y			The new y coordinate of the part being resized.
	 * @param action	The kind of resize being applied.
	 */
	public static void handle(Canvas canvas, int x, int y, ResizeWindow action) {
		switch(action) {
		case ResizeXRight:
			canvas.resizeXRightCanvas(x);
			break;
		case ResizeXLeft:
			canvas.resizeXLeftCanvas(x);
			break;
		case ResizeYTop:
			canvas.resizeYTopCanvas(y);
			break;
		case ResizeYLower:
			canvas.resizeYLowerCanvas(y);
			break;
		case ResizeLowerRightCorner:
			canvas.resizeXRightCanvas(x);
			canvas.resizeYLowerCanvas(y);
			break;
		case ResizeLowerLeftCorner:
			canvas.resizeXLeftCanvas(x);
			canvas.resizeYLowerCanvas(y);
			break;
		case ResizeTopLeftCorner:
			canvas.resizeXLeftCanvas(x);
			canvas.resizeYTopCanvas(y);
			break;
		case ResizeTopRightCorner:
			canvas.resizeXRightCanvas(x);
			canvas.resizeYTopCanvas(y);
			break;
		}
	}
	
	
}
