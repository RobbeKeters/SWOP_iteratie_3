package View.representation;

import java.awt.Graphics;

import Model.Canvas;
import Model.Party;

public class SeqPartyRepresentation extends PartyRepresentation {
	
	public SeqPartyRepresentation(Party party, Representation partyToDraw) {
		super(party, partyToDraw);
		setX(party.getPosSeq().getX());
		setY(party.getPosSeq().getY());
	}
	
	public void draw(Canvas c, Graphics g) {
		partyToDraw.draw(c, g);
		drawLifeline(c, g);
	}
	
	private void drawLifeline(Canvas c, Graphics g) {
		g.drawLine(	x, 
					c.getOrigineY() + (c.getHeight()/6) + 10 + c.getFramework().getBar().getHeight(), 
					x, 
					c.getOrigineY() + c.getHeight()-10);
	}
}
