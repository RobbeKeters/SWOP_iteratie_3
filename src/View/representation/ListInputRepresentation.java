package View.representation;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Model.Canvas;
import Model.Label;

public class ListInputRepresentation extends InputRepresentation {

	private String text;
	private ArrayList<Label> arguments = new ArrayList<Label>();
	
	public ListInputRepresentation(String text, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.text = text;
		for(int i = 0; i < 10; i++){
			Label label = new Label("");
			label.setSelected(true);
			arguments.add(label);
			
		}
	}
	
	public void draw(Canvas c, Graphics g){
		super.draw(c, g);
		drawText(c, g);
		for(int i = 0; i < arguments.size(); i++){
			if((g.getFontMetrics().getHeight() + 2)*(i+1) <= height){
				if(arguments.get(i).getSelected())
					g.setColor(Color.BLUE);
				drawItem(c, g, i);	
				g.setColor(Color.black);
			}
			
		}
	}
	
	private void drawItem(Canvas c, Graphics g, int i){
		int stringHeight = g.getFontMetrics().getHeight() + 2;
		g.drawRect(x + c.getOrigineX(), y + c.getOrigineY() + stringHeight * i, width, stringHeight);
	}
	
	private void drawText(Canvas c, Graphics g){
		int stringWidth = g.getFontMetrics().stringWidth(text);
		int stringHeight = g.getFontMetrics().getHeight();
		int margin = 2;
		g.drawString(text, x + c.getOrigineX(), y + c.getOrigineY() - stringHeight/2);
	}
}
