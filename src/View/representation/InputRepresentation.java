package View.representation;

import java.awt.Color;
import java.awt.Graphics;

import Model.Canvas;

/**
 * A visual representation of input.
 */
public class InputRepresentation {
	
	protected int x, y, width, height;
	
	/**
	 * Constructor.
	 * @param x			The given x coordinate.
	 * @param y			The given y coordinate.
	 * @param width		The given width.
	 * @param height	The given height.
	 */
	public InputRepresentation(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Draws the input's representation.
	 * @param c		The canvas to which the actor belongs to.
	 * @param g		The graphics element used to draw.
	 */
	public void draw(Canvas c, Graphics g){
		drawRectangle(c, g);
//		drawLabel(c, g);
	}
	
	private void drawRectangle(Canvas c, Graphics g){
//		int height = ;
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
	}
	
	
}
