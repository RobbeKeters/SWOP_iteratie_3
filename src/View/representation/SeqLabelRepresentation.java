package View.representation;

import java.awt.Color;
import java.awt.Graphics;

import Model.Canvas;
import Model.Label;

public class SeqLabelRepresentation extends LabelRepresentation {

	public SeqLabelRepresentation(Label l) {
		super(l);
	}
	
	public void draw(Canvas c, Graphics g) {
		if (label.getSelected())
			g.setColor(Color.RED);
		int x = label.getLabelPositionSequence().getX();
		int y = label.getLabelPositionSequence().getY();
		int width = label.getWidth();
		int height = label.getHeight();

		char[] name = label.getLabelname().toCharArray();
		g.drawChars(name, 0, name.length, x-(width/2)+5, y);
		label.setWidth(width);
		g.setColor(Color.BLACK);
	}

}
