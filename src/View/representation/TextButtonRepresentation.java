package View.representation;

import java.awt.Color;
import java.awt.Graphics;

import Model.Canvas;

public class TextButtonRepresentation {

	private String text;
	protected int x, y, width, height;
	private boolean selected;
	
	public TextButtonRepresentation(String text, int x, int y, int width, int height){
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw(Canvas c, Graphics g){
		if(selected)
			g.setColor(Color.RED);
		else
			g.setColor(Color.BLACK);
		drawRectangle(c, g);
		drawString(c, g);
		g.setColor(Color.BLACK);
	}
	
	private void drawRectangle(Canvas c, Graphics g){
		g.drawRect(x + c.getOrigineX(), y + c.getOrigineY(), width, height);
	}
	
	private void drawString(Canvas c, Graphics g){
		g.drawString(text, x + c.getOrigineX() + width/2-g.getFontMetrics().stringWidth(text)/2, y + c.getOrigineY() + g.getFontMetrics().getHeight()-2);
	}
}
