package View.representation;

import java.awt.Graphics;

import Model.Canvas;
import Model.Party;

public class ComObjectRepresentation extends ObjectRepresentation {

	public ComObjectRepresentation(Party party, Representation partyToDraw) {
		super(party, partyToDraw);
		setX(party.getPosComm().getX());
		setY(party.getPosComm().getY());
	}
	
	public void draw(Canvas c, Graphics g) {
		super.draw(c, g);
	}

}
