package View;

import java.awt.Color;
import java.awt.Graphics;

import Model.Actor;
import Model.Canvas;
import Model.InvocationMessage;
import Model.Message;
import Model.Party;
import Model.Window;
import View.representation.ComActorRepresentation;
import View.representation.ComLabelRepresentation;
import View.representation.ComMessageRepresentation;
import View.representation.ComObjectRepresentation;
import View.representation.ComSimplePartyRepresentation;
import View.representation.LabelRepresentation;
import View.representation.MessageRepresentation;
import View.representation.Representation;
import View.representation.SimpleMessageRepresentation;

public class CommunicationState implements ViewState {
	
	private Window window;
	private Graphics graphics;
	
	@Override
	public void draw(ViewContext viewContext, Window window, Graphics graphics) {
		this.window = window;
		this.graphics = graphics;
		
		drawSubWindow();
		
		drawParties();
		drawMessages();
	}
	
	private void drawSubWindow() {
		graphics.setClip(window.getOrigineX(), window.getOrigineY(), window.getWidth(), window.getHeight());
		
		graphics.setColor(Color.WHITE);
		graphics.fillRect(window.getOrigineX(), window.getOrigineY(), window.getWidth(), window.getHeight());
		graphics.setColor(Color.BLACK);

		window.getFramework().draw(window, graphics);
	}

	private void drawParties() {
		
		for(Party p : window.getParties()) {
			Representation partyRep;
			
			// TODO:
			// Better way of getting which party it is.
			if (p.getClass().equals(Actor.class)) 
				partyRep = new ComActorRepresentation(p, new ComSimplePartyRepresentation(p));
				
			 else 
				partyRep = new ComObjectRepresentation(p, new ComSimplePartyRepresentation(p));
			
			partyRep.draw(window, graphics);
			drawLabel(p);
			
		}
	}

	private void drawMessages() {
		for(Message m : window.getSortedMessages()){
			MessageRepresentation messageRep;
			
			messageRep = new ComMessageRepresentation(m, new SimpleMessageRepresentation(m));
			
			messageRep.draw(window, graphics);
			if(m.getClass().equals(InvocationMessage.class))
				drawLabel(m);
			
		}
	}
	
	private void drawLabel(Party p) {
		LabelRepresentation labelRep = new ComLabelRepresentation(p.getLabel());
		labelRep.draw(window, graphics);
	}
	
	private void drawLabel(Message m) {
		LabelRepresentation labelRep = new ComLabelRepresentation(m.getLabel());
		labelRep.draw(window, graphics);
	}
}
