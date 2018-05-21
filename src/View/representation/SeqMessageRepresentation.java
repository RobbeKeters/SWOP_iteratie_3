package View.representation;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;

import Model.Canvas;
import Model.InvocationMessage;
import Model.Message;
import Model.Party;
import Model.ResultMessage;

public class SeqMessageRepresentation extends MessageRepresentation {

	public SeqMessageRepresentation(Message m, Representation messageToDraw) {
		super(m, messageToDraw);
	}
	
	public void draw(Canvas c, Graphics g){
		messageToDraw.draw(c, g);
		drawActivationBar(c, g);
	}
	
	private void drawActivationBar(Canvas c, Graphics g) {
		int rectangleWidth = 6;
		int outward = 3;
		
		int sendX = message.getSentBy().getPosSeq().getX();
		int reciX = message.getReicevedBy().getPosSeq().getX();
		
		// TODO:
		// niet zo netjes dat het hier nog eens gevraagd wordt.
		if(message.getClass().equals(InvocationMessage.class)) {
			int y1 = c.getOrigineY() + c.getHeight()/6 + 50 + (50 * c.getAmountPredecessors(message));
			int y2 = c.getOrigineY() + c.getHeight()/6 + 50 + (50 * c.getAmountPredecessors(message.getResult()));
			 
			System.out.println("y1: " + y1 + " y2: " + y2);
			g.fillRect(sendX - (rectangleWidth/2), y1-outward, rectangleWidth, (y2-y1)+(2*outward));
			g.fillRect(reciX - (rectangleWidth/2), y1-outward, rectangleWidth, (y2-y1)+(2*outward));
		}
		
		
	}
	
}
