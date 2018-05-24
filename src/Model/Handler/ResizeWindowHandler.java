package Model.Handler;

import Model.Canvas;
import Model.Message;
import Model.Party;
import Model.ResizeWindow;
import Model.Window;

/**
 * A handler that handles the actions of a window being resized.
 */
public class ResizeWindowHandler extends Handler{

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
	
	/**
	 * Updates the position of the messages of the given canvas.
	 * @param window	Canvas for which to update the message positions.
	 * 
	 */
	
	//TODO Move to Window?
	
	public static void updateYPositionLMessageLabelsSequenceDiagram(Window window) {
		for (Message m : window.getMessages()) {
			int newY = window.getOrigineY() +window.getHeight()/6 + 42 + (50 * AddMessageHandler.getAmountPredecessors(window, m));
			m.getLabel().setLabelPositionSeq(m.getLabel().getLabelPositionSequence().getX(), newY);
		}
	}
	
	/**
	 * Updates the position of the parties of the given canvas.
	 * @param window	Canvas for which to update the party positions.
	 * 
	 */
	
	//TODO Move to window?
	
	private static void updateYPositionPartySequenceDiagram(Window window) {
		for( Party p : window.getParties()) {
			int newY = window.getOrigineY() +window.getHeight()/12;
			p.setPosSeq(p.getPosSeq().getX(), newY);
			p.getLabel().setLabelPositionSeq(p.getLabel().getLabelPositionSequence().getX(),newY +p.getHeight() + 10);
		}
	}
	
}
