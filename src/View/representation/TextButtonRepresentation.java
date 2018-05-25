package View.representation;

import java.awt.Color;
import java.awt.Graphics;

import Model.Canvas;

public class TextButtonRepresentation {

	private String text;
	protected int x, y, width, height;
	private boolean selected, disabled;
	
	/**
	 * Constructor.
	 * @param text		The text that belongs to the button.
	 * @param x			The x coordinate.
	 * @param y			The y coordinate.
	 */
	public TextButtonRepresentation(String text, int x, int y, int width, int height){
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Draws the actor's representation.
	 * @param c		The canvas to which the button belongs to.
	 * @param g		The graphics element used to draw.
	 */
	public void draw(Canvas c, Graphics g){
		if(selected)
			g.setColor(Color.BLUE);
		else if (disabled)
			g.setColor(Color.LIGHT_GRAY);
		else
			g.setColor(Color.BLACK);
		drawRectangle(c, g);
		drawString(c, g);
		g.setColor(Color.BLACK);
	}
	
	private void drawRectangle(Canvas c, Graphics g){
		g.drawRect(x, y, width, height);
	}
	
	private void drawString(Canvas c, Graphics g){
		g.drawString(text, x + width/2-g.getFontMetrics().stringWidth(text)/2, y + g.getFontMetrics().getHeight()-2);
	}
	
	/**
	 * Sets the selection status of this representation to the given status.
	 * @param a		The given status.
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	/**
	 * Sets the disabled status of this representation to the given status.
	 * @param a		The given status.
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
}
