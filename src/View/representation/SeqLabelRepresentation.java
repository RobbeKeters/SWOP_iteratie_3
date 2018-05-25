package View.representation;

import java.awt.Color;
import java.awt.Graphics;

import Model.Canvas;
import Model.Label;

/**
 * A visual representation of an label in a sequence diagram.
 */
public class SeqLabelRepresentation extends LabelRepresentation {
	
	/**
	 * Constructor.
	 * @param l		The label to represent.
	 */
	public SeqLabelRepresentation(Label l) {
		super(l);
	}
	
	/**
	 * Draws the label's representation.
	 * @param c		The canvas to which the label belongs to.
	 * @param g		The graphics element used to draw.
	 */
	public void draw(Canvas c, Graphics g) {
		if (label.getSelected())
			g.setColor(Color.RED);
		int x = label.getLabelPositionSequence().getX();
		int y = label.getLabelPositionSequence().getY();
		int width = label.getWidth();
		int height = label.getHeight();

		char[] name = label.getLabelname().toCharArray();
		g.drawChars(name, 0, name.length, x-(width/2)+5, y);
		label.setWidth(width);
		g.setColor(Color.BLACK);
	}

}
