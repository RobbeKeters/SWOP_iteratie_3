package View.representation;

import java.awt.Color;
import java.awt.Graphics;

import Model.Canvas;
import Model.InvocationMessage;
import Model.Message;
import Model.Party;
import Model.Window;

public class SeqInvocationMessageRepresentation extends MessageRepresentation {

	public SeqInvocationMessageRepresentation(Message m, Representation messageToDraw) {
		super(m, messageToDraw);
	}
	
	public void draw(Window w, Graphics g) {
		messageToDraw.draw(w, g);
		drawMessage(w, g);
	}
	
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
