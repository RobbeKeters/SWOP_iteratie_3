package View.representation;

import java.awt.Graphics;

import Model.Canvas;
import Model.Party;

/**
 * A visual representation of an object in a sequence diagram.
 */
public class SeqObjectRepresentation extends ObjectRepresentation{
	
	/**
	 * Constructor.
	 * @param party			The object to represent.
	 * @param partyToDraw	The object to draw.
	 */
	public SeqObjectRepresentation(Party party, Representation partyToDraw) {
		super(party, partyToDraw);
		setX(party.getPosSeq().getX());
		setY(party.getPosSeq().getY());
	}
	
	/**
	 * Draws the object's representation.
	 * @param c		The canvas to which the actor belongs to.
	 * @param g		The graphics element used to draw.
	 */
	public void draw(Canvas c, Graphics g){
		super.draw(c, g);
	}

}
