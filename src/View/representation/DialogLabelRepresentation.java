package View.representation;

import java.awt.Color;
import java.awt.Graphics;

import Model.Canvas;
import Model.Label;

/**
 * A visual representation of a dialog label.
 */
public class DialogLabelRepresentation extends LabelRepresentation {

	private int x, y, width, height;
	
	/**
	 * Constructor.
	 * @param l		The label to represent.
	 */
	public DialogLabelRepresentation(Label l) {
		super(l);
		this.x = l.getLabelPositionDialog().getX();
		this.y = l.getLabelPositionDialog().getY();
		this.width = l.getWidth();
		this.height = l.getHeight();
	}
	
	/**
	 * Draws the dialog label's representation.
	 * @param c		The canvas to which the actor belongs to.
	 * @param g		The graphics element used to draw.
	 */
	public void draw(Canvas c, Graphics g){
		if (label.getSelected())
			g.setColor(Color.RED);

		char[] name = label.getLabelname().toCharArray();
		g.drawChars(name, 0, name.length, x, y);
		label.setWidth(width);
		g.setColor(Color.BLACK);
		
		drawRectangle(c, g);
		if(label.getTitle() != null)
			drawTitle(c, g);
	}
	
	private void drawTitle(Canvas c, Graphics g) {
		String title = label.getTitle();
		g.drawString(title, x - g.getFontMetrics().stringWidth(title) - 10, y);
	}

	private void drawRectangle(Canvas c, Graphics g){
		g.setColor(Color.BLACK);
		g.drawRect(x - 5, y - g.getFontMetrics().getHeight(), width, height);
	}

}
