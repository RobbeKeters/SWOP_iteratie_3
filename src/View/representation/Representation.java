package View.representation;

import java.awt.Graphics;

import Model.Canvas;
import Model.Party;

/**
 * A visual representation of an element of the model.
 */
public interface Representation {
	
	/**
	 * Draws the element's representation.
	 * @param c		The canvas to which the element belongs to.
	 * @param g		The graphics element used to draw.
	 */
	void draw(Canvas c, Graphics g);
	
}
