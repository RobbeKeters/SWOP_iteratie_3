package View.representation;

import java.awt.Graphics;

import Model.Canvas;
import Model.Message;

/**
 * A visual representation of a simple message in a sequence diagram.
 */
public class SeqSimpleMessageRepresentation extends SimpleMessageRepresentation{
	
	/**
	 * Constructor.
	 * @param m		The message to represent.
	 */
	public SeqSimpleMessageRepresentation(Message m) {
		super(m);
	}
	
	/**
	 * Draws the message's representation.
	 * @param c		The canvas to which the actor belongs to.
	 * @param g		The graphics element used to draw.
	 */
	public void draw(Canvas c, Graphics g) {}
}
