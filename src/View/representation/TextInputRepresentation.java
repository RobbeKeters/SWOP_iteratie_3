package View.representation;

import java.awt.Color;
import java.awt.Graphics;

import Model.Canvas;
import Model.Label;

/**
 * A visual representation of text input.
 */
public class TextInputRepresentation extends InputRepresentation{

	private String text;
	
	/**
	 * Constructor.
	 * @param x			The given x coordinate.
	 * @param y			The given y coordinate.
	 * @param width		The given width.
	 * @param height	The given height.
	 */
	public TextInputRepresentation(String text, int x, int y, int width, int height){
		super(x, y, width, height);
		this.text = text;
	}
	
	/**
	 * Draws the text input's representation.
	 * @param c		The canvas to which the actor belongs to.
	 * @param g		The graphics element used to draw.
	 */
	public void draw(Canvas c, Graphics g) {
		super.draw(c, g);
//		drawLabel(c, g);
//		drawText(c, g);
	}
	
	
	private void drawLabel(Canvas c, Graphics g){
//		TODO: FIX THIS --> 2 labels toevoegen wnr dialog geopend wordt.
		Label label = new Label("");
		LabelRepresentation labelRep = new LabelRepresentation(label);
		labelRep.draw(c, g);
	}
	
	private void drawText(Canvas c, Graphics g){
		int stringWidth = g.getFontMetrics().stringWidth(text);
		int stringHeight = g.getFontMetrics().getHeight();
		int margin = 2;
		g.drawString(text, x + c.getOrigineX() - stringWidth - margin, y + c.getOrigineY() + stringHeight/2 + 5);
	}

}
