package View;

import java.awt.Graphics;

import Model.Canvas;
import Model.Window;

/**
 * A class created to draw a diagram.
 */
public interface ViewState {
	
	/**
	 * 
	 * @param viewContext		The view context that decides the type of drawing.
	 * @param window			The window to draw.
	 * @param g					The graphics element used to draw.
	 */
	void draw(ViewContext viewContext, Window window, Graphics g);
	
}

