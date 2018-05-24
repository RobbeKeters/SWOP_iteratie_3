package View.representation;

import java.awt.Color;
import java.awt.Graphics;

import Model.Canvas;
import Model.Party;

public class SeqSimplePartyRepresentation extends SimplePartyRepresentation {


	public SeqSimplePartyRepresentation(Party p) {
		super(p);
	}
	
	public void draw(Canvas c, Graphics g) {
		drawRectangle(g);
	}
	
	private void drawRectangle(Graphics g) {
		if(	this.party.getSelected() ) {
			g.setColor(Color.RED);
		}
		g.drawRect(	party.getLabel().getLabelPositionSequence().getX() - party.getLabel().getWidth()/2, 
					party.getLabel().getLabelPositionSequence().getY() - party.getLabel().getHeight()/2, 
					party.getLabel().getWidth(), 
					party.getLabel().getHeight());
		g.setColor(Color.BLACK);
	}

}
