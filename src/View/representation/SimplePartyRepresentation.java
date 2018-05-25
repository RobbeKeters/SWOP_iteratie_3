package View.representation;

import java.awt.Graphics;

import Model.Canvas;
import Model.Party;

/**
 * A visual representation of simple party.
 */
public class SimplePartyRepresentation implements Representation{

	protected Party party;
	
	/**
	 * Constructor.
	 * @param p		The party to represent.
	 */
	public SimplePartyRepresentation(Party p) {
		this.party = p;
	}
	
	/**
	 * Draws the party's representation.
	 * @param c		The canvas to which the actor belongs to.
	 * @param g		The graphics element used to draw.
	 */
	public void draw(Canvas c, Graphics g) {}
	
	
}
