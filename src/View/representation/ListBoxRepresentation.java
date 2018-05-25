package View.representation;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Model.Canvas;
import Model.Label;
import Model.ListBox;

public class ListBoxRepresentation {

	private String text;
	private ArrayList<Label> arguments = new ArrayList<Label>();
	private int x, y, width, height;
	
	public ListBoxRepresentation(ListBox lb) {
//		super(x, y, width, height);
		this.width = lb.getWidth();
		this.height = lb.getHeight();
		this.x = lb.getOriginX() + width/2;
		this.y = lb.getOriginY() + height/2;
//		this.text = text;
//		for(int i = 0; i < 10; i++){
//			Label label = new Label("");
//			label.setSelected(true);
//			arguments.add(label);
//			
//		}
	}
	
	public void draw(Canvas c, Graphics g){
//		super.draw(c, g);
//		drawText(c, g);
//		drawRectangle(c, g);
//		for(int i = 0; i < arguments.size(); i++){
////			if((g.getFontMetrics().getHeight() + 2)*(i+1) <= height){
//				if(arguments.get(i).getSelected())
//					g.setColor(Color.BLUE);
//				drawRectangle(c, g);	
//				g.setColor(Color.black);
////			}
//			
//		}
	}
	
//	private void drawRectangle(Canvas c, Graphics g){
//		g.drawRect(x + c.getOrigineX(), y + c.getOrigineY(), width, height);
//	}
	
	private void drawText(Canvas c, Graphics g){
//		int stringWidth = g.getFontMetrics().stringWidth(text);
//		int stringHeight = g.getFontMetrics().getHeight();
//		int margin = 2;
//		g.drawString(text, x + c.getOrigineX(), y + c.getOrigineY() - stringHeight/2);
	}
	
	public void setArguments(ArrayList<Label> args){
		this.arguments = args;
	}
}
