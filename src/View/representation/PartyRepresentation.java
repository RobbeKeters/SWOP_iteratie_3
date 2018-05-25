package View.representation;

import java.awt.Graphics;
import Model.Canvas;
import Model.Party;

/**
 * A visual representation of a party.
 */
public abstract class PartyRepresentation implements Representation{

	protected Representation partyToDraw;
	protected Party party;
	protected int x, y;
	
	/**
	 * Constructor
	 * @param party			The party to represent.
	 * @param partyToDraw	The party to draw.
	 */
	public PartyRepresentation(Party party, Representation partyToDraw){
		this.partyToDraw = partyToDraw;
		this.party = party;
	}
	
	@Override
	public void draw(Canvas c, Graphics g){
		
	}
	
	/**
	 * Sets the x coordinate.
	 * @param x		The given x coordinate.
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * Sets the y coordinate.
	 * @param y		The given y coordinate.
	 */
	public void setY(int y){
		this.y = y;
	}
	
	
	
}
