package View;

import java.awt.Graphics;

import Model.Canvas;
import Model.Window;

public class ViewContext {

	private ViewState myView;
	
	public ViewContext() {
		setState(new SequenceState());
	}
	
	public void draw(Window window, Graphics graphics) {
		myView.draw(this, window, graphics);
	}
	
	public void setState(ViewState viewState) {
		myView = viewState;
	}
}
