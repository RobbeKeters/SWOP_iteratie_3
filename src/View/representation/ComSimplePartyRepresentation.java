package View.representation;

import java.awt.Graphics;

import Model.Canvas;
import Model.Party;

/**
 * A visual representation of simple party in a communication diagram.
 */
public class ComSimplePartyRepresentation extends SimplePartyRepresentation {

	/**
	 * Constructor.
	 * @param p		The party to represent.
	 */
	public ComSimplePartyRepresentation(Party p) {
		super(p);
	}
	
	/**
	 * Draws the party's representation.
	 * @param c		The canvas to which the actor belongs to.
	 * @param g		The graphics element used to draw.
	 */
	public void draw(Canvas c, Graphics g) {
		drawRectangle(g);
	}
	
	private void drawRectangle(Graphics g) {
		g.drawRect(	party.getPosComm().getX() - party.getLabel().getWidth()/2, 
					party.getPosComm().getY() - party.getLabel().getHeight()/2, 
					party.getLabel().getWidth(), 
					party.getLabel().getHeight());
	}

}
