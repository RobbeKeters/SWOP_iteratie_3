package View.representation;

import java.awt.Graphics;
import Model.Canvas;
import Model.Party;

public abstract class PartyRepresentation implements Representation{

	protected Representation partyToDraw;
	protected Party party;
	protected int x, y;
	
	public PartyRepresentation(Party party, Representation partyToDraw){
		this.partyToDraw = partyToDraw;
		this.party = party;
	}
	
	@Override
	public void draw(Canvas c, Graphics g){
		
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	
	
}
