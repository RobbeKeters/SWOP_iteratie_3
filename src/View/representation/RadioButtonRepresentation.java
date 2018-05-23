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
	
	public void draw(Window w, Graphics g) {		
		if(selected)
			g.setColor(Color.blue);
		else
			g.setColor(Color.black);
		drawCircle(w , g);
		drawTitle(w , g);
		
		g.setColor(Color.black);
		if(activated){
			drawFilledCircle(w , g);
		}
	}
	
	private void drawCircle(Window w , Graphics g){
		int diameter = 10;
		g.drawOval(x + w .getOrigineX(), y + w .getOrigineY(), diameter, diameter);
	}
	
	private void drawFilledCircle(Window w , Graphics g){
		int diameter = 8;
		g.setColor(Color.BLACK);
		g.fillOval(x+w .getOrigineX() + 1, y+w .getOrigineY() + 1, diameter, diameter);
	}
	
	private void drawTitle(Window w , Graphics g){
		int margin = 2;
		int stringWidth = g.getFontMetrics().stringWidth(text);
		int stringHeight = g.getFontMetrics().getHeight();
		g.drawString(text, x + w .getOrigineX() - stringWidth - margin, y + w .getOrigineY() + stringHeight/2);
	}

	@Override
	public void draw(Canvas c, Graphics g) {
		// TODO Auto-generated method stub
		
	}
}
