package View.representation;

import java.awt.Color;
import java.awt.Graphics;

import Model.Canvas;
import Model.Label;

public class ComLabelRepresentation extends LabelRepresentation {

	public ComLabelRepresentation(Label l) {
		super(l);
	}
	
	public void draw(Canvas c, Graphics g) {
		c.updatePosComm();
		
		if (label.getSelected())
			g.setColor(Color.RED);
		int x = label.getLabelPositionComm().getX();
		int y = label.getLabelPositionComm().getY();
		int width = label.getWidth();
		int height = label.getHeight();

		System.out.println("label: " + label.getLabelname() + " x: " + x + " y: " + y);
		char[] name = label.getLabelname().toCharArray();
		g.drawChars(name, 0, name.length, x-(width/2)+5, y);
		label.setWidth(width);
		g.setColor(Color.BLACK);
		
	}

}