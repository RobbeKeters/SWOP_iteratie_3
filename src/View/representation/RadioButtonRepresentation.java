package View.representation;

import java.awt.Color;
import java.awt.Graphics;

import Model.Canvas;
import Model.Window;

/**
 * A visual representation of a radio button.
 */
public class RadioButtonRepresentation implements Representation{
	
	protected String text;
	protected int x, y;
	protected boolean activated = false;
	protected boolean selected;
	
	/**
	 * Constructor.
	 * @param text		The text that belongs to the button.
	 * @param x			The x coordinate.
	 * @param y			The y coordinate.
	 */
	public RadioButtonRepresentation(String text, int x, int y) {
		this.text = text;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Sets the activation status of this representation to the given status.
	 * @param a		The given status.
	 */
	public void setActivated(boolean a) {
		activated = a;
	}
	
	/**
	 * Sets the selection status of this representation to the given status.
	 * @param a		The given status.
	 */
	public void setSelected(boolean s) {
		selected = s;
	}
	
	/**
	 * Draws the actor's representation.
	 * @param c		The canvas to which the button belongs to.
	 * @param g		The graphics element used to draw.
	 */
	public void draw(Canvas c, Graphics g) {		
		if(selected)
			g.setColor(Color.blue);
		else
			g.setColor(Color.black);
		drawCircle(c , g);
		drawTitle(c, g);
		
		g.setColor(Color.black);
		if(activated){
			drawFilledCircle(c , g);
		}
	}
	
	private void drawCircle(Canvas w , Graphics g){
		int diameter = 10;
		g.drawOval(x, y, diameter, diameter);
	}
	
	private void drawFilledCircle(Canvas w , Graphics g){
		int diameter = 8;
		g.setColor(Color.BLACK);
		g.fillOval(x + 1, y+ 1, diameter, diameter);
	}
	
	private void drawTitle(Canvas w , Graphics g){
		int margin = 2;
		int stringWidth = g.getFontMetrics().stringWidth(text);
		int stringHeight = g.getFontMetrics().getHeight();
		g.drawString(text, x - stringWidth - margin, y + stringHeight/2);
	}

}
