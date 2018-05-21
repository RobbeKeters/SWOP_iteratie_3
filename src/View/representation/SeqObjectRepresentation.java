package View.representation;

import java.awt.Graphics;

import Model.Canvas;
import Model.Party;

public class SeqObjectRepresentation extends ObjectRepresentation{

	public SeqObjectRepresentation(Party party, Representation partyToDraw) {
		super(party, partyToDraw);
		setX(party.getPosSeq().getX());
		setY(party.getPosSeq().getY());
	}
	
	public void draw(Canvas c, Graphics g){
		super.draw(c, g);
	}

}
