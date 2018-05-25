package View;

import java.awt.Graphics;

import Model.Canvas;
import Model.Window;

/**
 * The view context of a diagram.
 */
public class ViewContext {

	private ViewState myView;
	
	/**
	 * Constructor.
	 */
	public ViewContext() {
		setState(new SequenceState());
	}
	
	/**
	 * Draws a window based on the view context.
	 * @param window		The window to draw.
	 * @param graphics		The graphics element used to draw.
	 */
	public void draw(Window window, Graphics graphics) {
		myView.draw(this, window, graphics);
	}
	
	/**
	 * Sets the context's state to the given state.
	 * @param viewState		The given state.
	 */
	public void setState(ViewState viewState) {
		myView = viewState;
	}
}
