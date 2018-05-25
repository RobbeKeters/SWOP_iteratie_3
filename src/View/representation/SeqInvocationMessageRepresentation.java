package View.representation;

import java.awt.Color;
import java.awt.Graphics;

import Model.Canvas;
import Model.InvocationMessage;
import Model.Message;
import Model.Party;
import Model.Window;

/**
 * A visual representation of an invocation message in a sequence diagram.
 */
public class SeqInvocationMessageRepresentation extends MessageRepresentation {
	
	/**
	 * Constructor.
	 * @param m					The message to represent.
	 * @param messageToDraw		The message to draw.
	 */
	public SeqInvocationMessageRepresentation(Message m, Representation messageToDraw) {
		super(m, messageToDraw);
	}
	
	/**
	 * 
	 * @param w
	 * @param g
	 */
	public void draw(Window w, Graphics g) {
		messageToDraw.draw(w, g);
		drawMessage(w, g);
	}
	
	/**
	 * Draws the message's representation.
	 * @param w		The window to which the message belongs to.
	 * @param g		The graphics element used to draw.
	 */
	private void drawMessage(Window w, Graphics g) {
		Party sender = message.getSentBy();
		Party receiver = message.getReicevedBy();

		if (message.getSelected())
			g.setColor(Color.RED);
		
		int senderX = 0;
		int receiverX = 0;
		int y = w.getOrigineY() + w.getHeight()/6 + 50 + (50 * w.getAmountPredecessors(message));
		
		if (sender.getPosSeq().getX() < receiver.getPosSeq().getX()) {
			senderX = sender.getPosSeq().getX()+3;
			receiverX = receiver.getPosSeq().getX()-3;
		} else {
			senderX = sender.getPosSeq().getX()-3;
			receiverX = receiver.getPosSeq().getX()+3;
		}
		drawArrow(g, senderX, y, receiverX, y);
		g.setColor(Color.BLACK);
	}
	
	private void drawArrow(Graphics g, int sendX, int sendY, int recX, int recY) {
		int arrowLineLength = (recX - sendX)/20;
		g.drawLine(sendX, sendY, recX, recY);
		g.drawLine(recX, recY, recX - arrowLineLength, recY + arrowLineLength);
		g.drawLine(recX, recY, recX - arrowLineLength, recY - arrowLineLength);
	}

}
