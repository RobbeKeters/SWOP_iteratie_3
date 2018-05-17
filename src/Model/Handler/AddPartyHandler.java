package Model.Handler;

import Model.ADJUSTED_TYPE;
import Model.Canvas;
import Model.Label;
import Model.Object;
import Model.Point;

/**
 * A handler that handles the actions of a party being added to a canvas.
 */
public class AddPartyHandler extends Handler{
	
	/**
	 * Handles a party being added to the canvas.
	 * @param canvas		The canvas to edit.
	 * @param x			The x coordinate of the mouse event used to initiate this action.
	 * @param y			The y coordinate of the mouse event used to initiate this action.
	 */
	public static void handle(Canvas canvas, int x, int y) {
		String defaultName = "";		
		Object party = new Object(defaultName,Canvas.getAvailablePartyNumber(canvas));
		
		int seqYCoordinate = canvas.getOrigineY() +canvas.getHeight()/12;
		int seqYLabel = seqYCoordinate +party.getHeight() + 10;
		
		party.setPosSeq(x, seqYCoordinate);
		party.setPosComm(x, y);
		
		Label label = new Label(defaultName);
		label.setSelected(true);
		
		// EditLabelHandler.handle(canvas, label, x, y);
		
		label.setLabelPositionSeq(new Point(x, seqYLabel));
		label.setLabelPositionComm(new Point(x, y));
		
		party.setLabel(label);
		canvas.addParty(party);
		
		// Notify Interaction
		canvas.getInteraction().adjusted(ADJUSTED_TYPE.ADDED_PARTY, canvas);
	}

}
