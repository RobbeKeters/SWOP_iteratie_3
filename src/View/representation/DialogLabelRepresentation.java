package View.representation;

import java.awt.Color;
import java.awt.Graphics;

import Model.Canvas;
import Model.Label;

public class DialogLabelRepresentation extends LabelRepresentation {

	private int x, y, width, height;
	
	public DialogLabelRepresentation(Label l) {
		super(l);
		this.x = l.getLabelPositionDialog().getX();
		this.y = l.getLabelPositionDialog().getY();
		this.width = l.getWidth();
		this.height = l.getHeight();
	}
	
	public void draw(Canvas c, Graphics g){
		if (label.getSelected())
			g.setColor(Color.RED);

		char[] name = label.getLabelname().toCharArray();
		g.drawChars(name, 0, name.length, x-(width/2)+5, y);
		label.setWidth(width);
		g.setColor(Color.BLACK);
	}

}
