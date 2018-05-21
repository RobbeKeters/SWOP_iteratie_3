package View.representation;

import java.awt.Color;
import java.awt.Graphics;
import Model.Canvas;
import Model.Label;

public class LabelRepresentation implements Representation {

	protected Label label;
	
	public LabelRepresentation(Label l) {
		this.label = l;
	}
	
	@Override
	public void draw(Canvas c, Graphics g) {}
}
