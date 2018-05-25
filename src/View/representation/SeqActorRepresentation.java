package View.representation;

import java.awt.Graphics;

import Model.Canvas;
import Model.Party;

/**
 * A visual representation of an actor in a sequence diagram.
 */
public class SeqActorRepresentation extends ActorRepresentation{

	/**
	 * Constructor.
	 * @param party			The actor to represent.
	 * @param partyToDraw	The actor to draw.
	 */
	public SeqActorRepresentation(Party party, Representation partyToDraw) {
		super(party, partyToDraw);
		setX(party.getPosSeq().getX());
		setY(party.getPosSeq().getY()+10);
	}
	
	/**
	 * Draws the actor's representation.
	 * @param c		The canvas to which the actor belongs to.
	 * @param g		The graphics element used to draw.
	 */
	public void draw(Canvas c, Graphics g){
		super.draw(c, g);
	}

}
