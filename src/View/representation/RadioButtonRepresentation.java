package View.representation;

import java.awt.Color;
import java.awt.Graphics;

import Model.Canvas;

public class RadioButtonRepresentation implements Representation{
	
	protected String text;
	protected int x, y;
	protected boolean selected = false;
	
	public RadioButtonRepresentation(String text, int x, int y) {
		this.text = text;
		this.x = x;
		this.y = y;
	}
	
	public void draw(Canvas c, Graphics g) {
//		TODO: selected betekenis geven, bijhouden in de model van radiobutton (Met TAB veranderlijk).
		
		if(selected)
			g.setColor(Color.blue);
		else
			g.setColor(Color.black);
		drawCircle(c, g);
		drawTitle(c, g);
		
		g.setColor(Color.black);
//		TODO: activated betekenis geven 
//		if(activated){
			drawFilledCircle(c, g);
//		}
	}
	
	private void drawCircle(Canvas c, Graphics g){
		int diameter = 10;
		g.drawOval(x + c.getOrigineX(), y + c.getOrigineY(), diameter, diameter);
	}
	
	private void drawFilledCircle(Canvas c, Graphics g){
		int diameter = 8;
		g.setColor(Color.BLACK);
		g.fillOval(x+c.getOrigineX() + 1, y+c.getOrigineY() + 1, diameter, diameter);
	}
	
	private void drawTitle(Canvas c, Graphics g){
		int margin = 2;
		int stringWidth = g.getFontMetrics().stringWidth(text);
		int stringHeight = g.getFontMetrics().getHeight();
		g.drawString(text, x + c.getOrigineX() - stringWidth - margin, y + c.getOrigineY() + stringHeight/2);
	}
}
