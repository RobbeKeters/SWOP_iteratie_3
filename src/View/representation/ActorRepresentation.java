package View.representation;

import java.awt.Color;
import java.awt.Graphics;

import Model.Canvas;
import Model.Party;

/**
 * A visual representation of an actor.
 */
public class ActorRepresentation extends PartyRepresentation {
	
	/**
	 * Constructor.
	 * @param party			The actor to represent.
	 * @param partyToDraw	The actor to draw.
	 */
	public ActorRepresentation(Party party, Representation partyToDraw) {
		super(party, partyToDraw);
	}
	
	/**
	 * Draws the actor's representation.
	 * @param c		The canvas to which the actor belongs to.
	 * @param g		The graphics element used to draw.
	 */
	public void draw(Canvas c, Graphics g) {
		partyToDraw.draw(c, g);
		drawStickFigure(g);
	}
	
	protected void drawStickFigure(Graphics g) {
	if(  this.party.getSelected()) {
		g.setColor(Color.RED);
	}
		g.drawLine(x, y-10, x, y+10);
		g.drawLine(x, y+10, x+5, y+15);
		g.drawLine(x, y+10, x-5, y+15);
		g.drawLine(x, y-3, x+5, y+2);
		g.drawLine(x, y-3, x-5, y+2);
		g.drawOval(x-5, y-20, 10, 10);
		
		g.setColor(Color.BLACK);

	}
}
