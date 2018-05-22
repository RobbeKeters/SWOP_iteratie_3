package View;

import java.awt.Graphics;

import Model.Canvas;
import Model.Window;

public interface ViewState {

	void draw(ViewContext viewContext, Window window, Graphics g);
	
}

