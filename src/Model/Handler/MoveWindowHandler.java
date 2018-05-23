package Model.Handler;

import Model.Canvas;
import Model.Message;
import Model.Party;
import Model.Point;
import Model.Window;

/**
 * A handler that handles the actions of a window being moved.
 */
public class MoveWindowHandler extends Handler {

	/**
	 * Handles a window being moved.
	 * 
	 * @param window			The window being moved.
	 * @param oldXorigine		Old origin of the window along the X-axis. 
	 * @param oldYorigine		Old origin of the window along the Y-axis. 
	 * @param newXorigine		New origin of the window along the X-axis. 
	 * @param newYorigine		New origin of the window along the Y-axis. 
	 */
	public static void handle(Window window, int oldXorigine, int oldYorigine, int newXorigine, int newYorigine) {
		window.setOrigineX(newXorigine);
		window.setOrigineY(newYorigine);
		updatePartyPositions(window ,oldXorigine,oldYorigine, newXorigine,newYorigine );
		updateMessagePositions(window,oldXorigine,oldYorigine, newXorigine,newYorigine);
	}
	
	/**
	 * Updates the positions of the parties on the given canvas.
	 * 
	 * @param window			Canvas to be updated.
	 * @param oldXorigine		Old origin of the window along the X-axis. 
	 * @param oldYorigine		Old origin of the window along the Y-axis. 
	 * @param newXorigine		New origin of the window along the X-axis. 
	 * @param newYorigine		New origin of the window along the Y-axis. 
	 */
	public static void updatePartyPositions(Window window ,int oldXorigine,int oldYorigine,int newXorigine,int newYorigine) {
		for( Party p :window.getParties()) {
			
			// Update Positions Sequence Diagram
			int xSeq = p.getPosSeq().getX();
			int ySeq = p.getPosSeq().getY();
			
			int dx = Math.abs((xSeq-oldXorigine));
			int dy = Math.abs((ySeq-oldYorigine));
			
			p.setPosSeq((newXorigine+dx),(newYorigine + dy));
			// Update Label Positin for  Sequence Diagram
			int yLabel = newYorigine + window.getHeight()/12 + +p.getHeight() + 10; // Needs to be in sync with AddPartyHandler
			p.getLabel().setLabelPositionSeq((newXorigine+dx), yLabel);

			// Update Positions Communication Diagram
			int xCom = p.getPosComm().getX();
			int yCom = p.getPosComm().getY();
			
			dx = Math.abs((xCom-oldXorigine));
			dy = Math.abs((yCom-oldYorigine));
			
			p.setPosComm((newXorigine + dx), (newYorigine+dy));
			// Update Label Postion for Communication Diagram
			// Label Postion in communication view is not used......... (See Add PartyHandler)
	
		}
	}
	
	/**
	 * Updates the positions of the messages on the given canvas.
	 * 
	 * @param window			Canvas to be updated.
	 * @param oldXorigine		Old origin of the window along the X-axis. 
	 * @param oldYorigine		Old origin of the window along the Y-axis. 
	 * @param newXorigine		New origin of the window along the X-axis. 
	 * @param newYorigine		New origin of the window along the Y-axis. 
	 */
	public static void updateMessagePositions(Window window,int oldXorigine,int oldYorigine,int newXorigine,int newYorigine) {
		for( Message m: window.getMessages()) {
			
			if( m.getClass().equals( Model.InvocationMessage.class) ){
				int invocLabelX = Math.max(m.getReicevedBy().getPosSeq().getX(), m.getSentBy().getPosSeq().getX()) - Math.abs( (m.getReicevedBy().getPosSeq().getX() - m.getSentBy().getPosSeq().getX() )/2);
				int invocLabelY = window.getOrigineY() +window.getHeight()/6 + 42 + (50 * AddMessageHandler.getAmountPredecessors(window, m));
				m.getLabel().setLabelPositionSeq(new Point(invocLabelX, invocLabelY));
			}
			
			// Update Label Positions Sequence Diagram
			int xSeq = m.getLabel().getLabelPositionSequence().getX();
			
			int dx = Math.abs((xSeq-oldXorigine));
			
			//m.getLabel().setLabelPositionSeq((newXorigine+dx),canvas.getOrigineY() +canvas.getHeight()/6 + 42 + (50 * AddMessageHandler.getAmountPredecessors(canvas, m)));
			// Update Label Positions Communication Diagram
			int xCom = m.getLabel().getLabelPositionComm().getX();
			int yCom = m.getLabel().getLabelPositionComm().getY();
			
			dx = Math.abs((xCom-oldXorigine));
			int dy = Math.abs((yCom-oldYorigine));
			
			m.getLabel().setLabelPositionComm((newXorigine+dx), (newYorigine+dy));
		}
	}
	
}
