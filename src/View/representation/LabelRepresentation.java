package View.representation;

import java.awt.Color;
import java.awt.Graphics;
import Model.Canvas;
import Model.Label;

/**
 * A visual representation of an label.
 */
public class LabelRepresentation implements Representation {

	protected Label label;
	
	/**
	 * Constructor.
	 * @param l		The label to represent.
	 */
	public LabelRepresentation(Label l) {
		this.label = l;
	}
	
	@Override
	public void draw(Canvas c, Graphics g) {}
}
