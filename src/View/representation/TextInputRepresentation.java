package View.representation;

import java.awt.Color;
import java.awt.Graphics;

import Model.Canvas;
import Model.Label;

public class TextInputRepresentation extends InputRepresentation{

	private String text;
	
	public TextInputRepresentation(String text, int x, int y, int width, int height){
		super(x, y, width, height);
		this.text = text;
	}
	
	public void draw(Canvas c, Graphics g) {
		super.draw(c, g);
//		drawLabel(c, g);
		drawText(c, g);
	}
	
	
	private void drawLabel(Canvas c, Graphics g){
//		TODO: FIX THIS --> 2 labels toevoegen wnr dialog geopend wordt.
		Label label = new Label("");
		LabelRepresentation labelRep = new LabelRepresentation(label);
		labelRep.draw(c, g);
	}
	
	private void drawText(Canvas c, Graphics g){
		int stringWidth = g.getFontMetrics().stringWidth(text);
		int stringHeight = g.getFontMetrics().getHeight();
		int margin = 2;
		g.drawString(text, x + c.getOrigineX() - stringWidth - margin, y + c.getOrigineY() + stringHeight/2 + 5);
	}

}
