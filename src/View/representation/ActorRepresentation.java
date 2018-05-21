package View.representation;

import java.awt.Graphics;

import Model.Canvas;
import Model.Party;

public class ActorRepresentation extends PartyRepresentation {
	
	public ActorRepresentation(Party party, Representation partyToDraw) {
		super(party, partyToDraw);
	}
	
	public void draw(Canvas c, Graphics g) {
		partyToDraw.draw(c, g);
		drawStickFigure(g);
	}
	
	protected void drawStickFigure(Graphics g) {
		
		g.drawLine(x, y-10, x, y+10);
		g.drawLine(x, y+10, x+5, y+15);
		g.drawLine(x, y+10, x-5, y+15);
		g.drawLine(x, y-3, x+5, y+2);
		g.drawLine(x, y-3, x-5, y+2);
		g.drawOval(x-5, y-20, 10, 10);
	}

}
