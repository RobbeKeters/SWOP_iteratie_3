package View.representation;

import java.awt.Color;
import java.awt.Graphics;

import Model.Canvas;

public class InputRepresentation {
	
	protected int x, y, width, height;
	
	public InputRepresentation(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw(Canvas c, Graphics g){
		drawRectangle(c, g);
//		drawLabel(c, g);
	}
	
	private void drawRectangle(Canvas c, Graphics g){
//		int height = ;
		g.setColor(Color.BLACK);
		g.drawRect(x + c.getOrigineX(), y + c.getOrigineY(), width, height);
	}
	
	
}
