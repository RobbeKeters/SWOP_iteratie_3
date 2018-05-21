package View.representation;

import java.awt.Graphics;

import Model.Canvas;
import Model.Party;

public class ObjectRepresentation extends PartyRepresentation {

	public ObjectRepresentation(Party party, Representation partyToDraw) {
		super(party, partyToDraw);
	}

	public void draw(Canvas c, Graphics g) {
		partyToDraw.draw(c, g);
	}
}

