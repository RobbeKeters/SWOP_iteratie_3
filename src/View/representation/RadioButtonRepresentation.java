package View.representation;

import java.awt.Color;
import java.awt.Graphics;

import Model.Canvas;
import Model.Window;

public class RadioButtonRepresentation implements Representation{
	
	protected String text;
	protected int x, y;
	protected boolean activated = false;
	protected boolean selected;
	
	public RadioButtonRepresentation(String text, int x, int y) {
		this.text = text;
		this.x = x;
		this.y = y;
	}
	
	public void setActivated(boolean a) {
		activated = a;
	}

	public void setSelected(boolean s) {
		selected = s;
	}
	
	public void draw(Canvas c, Graphics g) {		
		if(selected)
			g.setColor(Color.blue);
		else
			g.setColor(Color.black);
		drawCircle(c , g);
		drawTitle(c, g);
		
		g.setColor(Color.black);
		if(activated){
			drawFilledCircle(c , g);
		}
	}
	
	private void drawCircle(Canvas w , Graphics g){
		int diameter = 10;
		g.drawOval(x, y, diameter, diameter);
	}
	
	private void drawFilledCircle(Canvas w , Graphics g){
		int diameter = 8;
		g.setColor(Color.BLACK);
		g.fillOval(x + 1, y+ 1, diameter, diameter);
	}
	
	private void drawTitle(Canvas w , Graphics g){
		int margin = 2;
		int stringWidth = g.getFontMetrics().stringWidth(text);
		int stringHeight = g.getFontMetrics().getHeight();
		g.drawString(text, x - stringWidth - margin, y + stringHeight/2);
	}

}
