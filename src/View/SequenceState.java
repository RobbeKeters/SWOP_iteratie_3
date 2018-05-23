package View;

import java.awt.Color;
import Model.Handler.OpenDialogBoxHandler;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;

import Model.Actor;
import Model.Canvas;
import Model.InvocationMessage;
import Model.Message;
import Model.Party;
import Model.ResultMessage;
import Model.Screen;
import Model.Window;
import View.representation.ActorRepresentation;
import View.representation.LabelRepresentation;
import View.representation.MessageRepresentation;
import View.representation.ObjectRepresentation;
import View.representation.PartyRepresentation;
import View.representation.Representation;
import View.representation.SeqActorRepresentation;
import View.representation.SeqInvocationMessageRepresentation;
import View.representation.SeqLabelRepresentation;
import View.representation.SeqMessageRepresentation;
import View.representation.SeqObjectRepresentation;
import View.representation.SeqPartyRepresentation;
import View.representation.SeqResultMessageRepresentation;
import View.representation.SeqSimpleMessageRepresentation;
import View.representation.SeqSimplePartyRepresentation;
import View.representation.SimplePartyRepresentation;

public class SequenceState implements ViewState {
	
	private Window window;
	private Graphics graphics;
	
	/**
	 * draw the basic 
	 */
	@Override
	public void draw(ViewContext viewContext, Window window, Graphics graphics) {
		this.window = window;
		this.graphics = graphics;
		
		drawSubWindow();
		drawPartiesBox();
		
		drawParties();
		drawMessages();
		
//		TESTEN VOOR INPUT
//		DialogBox db = new DialogBox();
//		db.draw(DialogBox.SelectedElement.VIEW, window, graphics);
		
	}
	
	/**
	 * Draws the subwindow.
	 */
	private void drawSubWindow() {
		graphics.setClip(window.getOrigineX(), window.getOrigineY(), window.getWidth(), window.getHeight());
		
		graphics.setColor(Color.WHITE);
		graphics.fillRect(window.getOrigineX(), window.getOrigineY(), window.getWidth(), window.getHeight());
		graphics.setColor(Color.BLACK);

		window.getFramework().draw(window, graphics);
	}
	
	/**
	 * draws the box in which parties resign.
	 */
	private void drawPartiesBox() {
		graphics.drawRect(window.getOrigineX()+10,  window.getOrigineY()+ 20, window.getWidth()-20,10+ window.getHeight()/6);
		graphics.drawString("Parties", window.getOrigineX()+ 20, window.getOrigineY()+ 40);
	}

	/**
	 * 
	 */
	private void drawParties() {
		// TODO:
		// Better way of getting which type of party it is.
		
		for(Party p : window.getParties()) {
			PartyRepresentation partyRep;
			
			if (p.getClass().equals(Actor.class)) {
				partyRep = new SeqActorRepresentation(p, new SeqPartyRepresentation(p, new SeqSimplePartyRepresentation(p)));
				
			} else 
				partyRep = new SeqObjectRepresentation(p, new SeqPartyRepresentation(p, new SeqSimplePartyRepresentation(p)));

			partyRep.draw(window, graphics);
			drawLabel(p);
			
		}
	}

	private ArrayList<Message> sortedMessages = new ArrayList<Message>();
	
	private void drawMessages() {
		for(Message m : window.getSortedMessages()){
			if(m.getClass() != ResultMessage.class) {
				SeqInvocationMessageRepresentation messageRep = new SeqInvocationMessageRepresentation(m, new SeqMessageRepresentation(m, new SeqSimpleMessageRepresentation(m)));
				messageRep.draw(window, graphics);
				drawLabel(m);
			} else {
				SeqResultMessageRepresentation messageRep = new SeqResultMessageRepresentation(m, new SeqMessageRepresentation(m, new SeqSimpleMessageRepresentation(m)));
				messageRep.draw(window, graphics);

			}
			
		}
	}
	
	private void drawLabel(Party p) {
		LabelRepresentation labelRep = new SeqLabelRepresentation(p.getLabel());
		labelRep.draw(window, graphics);
	}
	
	private void drawLabel(Message m) {
		LabelRepresentation labelRep = new SeqLabelRepresentation(m.getLabel());
		labelRep.draw(window, graphics);
	}
}
