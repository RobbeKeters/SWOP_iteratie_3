package View.representation;

import java.awt.Graphics;

import Model.Canvas;
import Model.Party;

/**
 * A visual representation of an object.
 */
public class ObjectRepresentation extends PartyRepresentation {
	
	/**
	 * Constructor.
	 * @param party			The object to represent.
	 * @param partyToDraw	The object to draw.
	 */
	public ObjectRepresentation(Party party, Representation partyToDraw) {
		super(party, partyToDraw);
	}
	
	/**
	 * Draws the object's representation.
	 * @param c		The canvas to which the actor belongs to.
	 * @param g		The graphics element used to draw.
	 */
	public void draw(Canvas c, Graphics g) {
		partyToDraw.draw(c, g);
	}
}

