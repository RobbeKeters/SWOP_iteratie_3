package View.representation;

import java.awt.Color;
import java.awt.Graphics;

import Model.Canvas;
import Model.Label;
import Model.Window;

/**
 * A visual representation of an label in a communication diagram.
 */
public class ComLabelRepresentation extends LabelRepresentation {
	
	/**
	 * Constructor.
	 * @param l		The label to represent.
	 */
	public ComLabelRepresentation(Label l) {
		super(l);
	}
	
	/**
	 * Draws the label's representation.
	 * @param c		The window to which the label belongs to.
	 * @param g		The graphics element used to draw.
	 */
	public void draw(Window w, Graphics g) {
		w.updatePosComm();
		
		if (label.getSelected())
			g.setColor(Color.RED);
		int x = label.getLabelPositionComm().getX();
		int y = label.getLabelPositionComm().getY();
		int width = label.getWidth();
		int height = label.getHeight();

		System.out.println("label: " + label.getLabelname() + " x: " + x + " y: " + y);
		char[] name = label.getLabelname().toCharArray();
		g.drawChars(name, 0, name.length, x-(width/2)+5, y);
		label.setWidth(width);
		g.setColor(Color.BLACK);
		
	}

}