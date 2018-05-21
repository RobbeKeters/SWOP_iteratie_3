package View.representation;

import java.awt.Graphics;

import Model.Canvas;
import Model.Party;

public class ComActorRepresentation extends ActorRepresentation {

	public ComActorRepresentation(Party party, Representation partyToDraw) {
		super(party, partyToDraw);
		setX(party.getPosComm().getX());
		setY(party.getPosComm().getY()-25);
	}
	
	public void draw(Canvas c, Graphics g) {
		super.draw(c, g);
	}

}
