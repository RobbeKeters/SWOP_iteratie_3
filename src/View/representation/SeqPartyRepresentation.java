package View.representation;

import java.awt.Graphics;

import Model.Canvas;
import Model.Party;

/**
 * A visual representation of a party in a sequence diagram.
 */
public class SeqPartyRepresentation extends PartyRepresentation {
	
	/**
	 * Constructor
	 * @param party			The party to represent.
	 * @param partyToDraw	The party to draw.
	 */
	public SeqPartyRepresentation(Party party, Representation partyToDraw) {
		super(party, partyToDraw);
		setX(party.getPosSeq().getX());
		setY(party.getPosSeq().getY());
	}
	
	/**
	 * Draws the party's representation.
	 * @param c		The canvas to which the actor belongs to.
	 * @param g		The graphics element used to draw.
	 */
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
