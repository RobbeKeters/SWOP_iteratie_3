package View.representation;

import java.awt.Graphics;

import Model.Canvas;
import Model.Party;

public class SeqActorRepresentation extends ActorRepresentation{

	public SeqActorRepresentation(Party party, Representation partyToDraw) {
		super(party, partyToDraw);
		setX(party.getPosSeq().getX());
		setY(party.getPosSeq().getY()+10);
	}
	
	public void draw(Canvas c, Graphics g){
		super.draw(c, g);
	}

}
