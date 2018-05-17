package View;

import java.awt.Color;
import java.awt.Graphics;
import Model.Canvas;
import View.representation.InputRepresentation;
import View.representation.ListInputRepresentation;
import View.representation.RadioButtonRepresentation;
import View.representation.TextButtonRepresentation;
import View.representation.TextInputRepresentation;

public class DialogBox {

	protected Canvas canvas;
	protected Graphics graphics;
	private int fontHeight;
	
	protected enum SelectedElement {
		VIEW, 
		PARTY, 
		INVOCATION_MESSAGE, 
		RESULT_MESSAGE;
	}
	
	public void draw(SelectedElement element, Canvas c, Graphics g){
		this.canvas = c;
		this.graphics = g;
		fontHeight = graphics.getFontMetrics().getHeight();
		
		drawSubWindow();
		switch(element){
		case VIEW:
			drawViewControls();
			break;
		case PARTY:
			drawPartyControls();
			break;
		case INVOCATION_MESSAGE:
			drawInvocMessageControls();
			break;
		case RESULT_MESSAGE:
			drawResultMessageControls();
			break;
		}

	}
	
	private void drawSubWindow() {
		graphics.setClip(canvas.getOrigineX(), canvas.getOrigineY(), canvas.getWidth(), canvas.getHeight());
		
		graphics.setColor(Color.WHITE);
		graphics.fillRect(canvas.getOrigineX(), canvas.getOrigineY(), canvas.getWidth(), canvas.getHeight());
		graphics.setColor(Color.BLACK);

		canvas.getFramework().draw(canvas, graphics);
	}
	
	private void drawViewControls(){
		RadioButtonRepresentation sequenceB = new RadioButtonRepresentation("SEQUENCE: ", canvas.getWidth()/2 - 50, canvas.getHeight()/2);
		RadioButtonRepresentation communicationB = new RadioButtonRepresentation("COMMUNICATION: ", canvas.getWidth()/2 + 100, canvas.getHeight()/2);
		sequenceB.draw(canvas, graphics);
		communicationB.draw(canvas, graphics);
	}
	
	private void drawPartyControls(){
		TextInputRepresentation instanceI = new TextInputRepresentation("Instance: ", canvas.getWidth()/2, (canvas.getHeight()/2)-50, 50, fontHeight);
		TextInputRepresentation classI = new TextInputRepresentation("Class: ", canvas.getWidth()/2, (canvas.getHeight()/2)-25, 50, fontHeight);
		RadioButtonRepresentation actorB = new RadioButtonRepresentation("Actor: ", canvas.getWidth()/2 - 35, canvas.getHeight()/2);
		RadioButtonRepresentation objectB = new RadioButtonRepresentation("Object: ", canvas.getWidth()/2 + 35, canvas.getHeight()/2);
		instanceI.draw(canvas, graphics);
		classI.draw(canvas, graphics);
		actorB.draw(canvas, graphics);
		objectB.draw(canvas, graphics);
		
	}
	
	private void drawInvocMessageControls() {
		TextInputRepresentation methodI = new TextInputRepresentation("Method: ", canvas.getWidth()/2-25, canvas.getHeight()/2-100, 100, fontHeight+2);
		InputRepresentation argumentI = new InputRepresentation(canvas.getWidth()/2-50, canvas.getHeight()/2+50, 100, fontHeight + 2);
		TextButtonRepresentation addB = new TextButtonRepresentation("Add", canvas.getWidth()/2 + 55, canvas.getHeight()/2 + 50, 30, fontHeight + 2);

		ListInputRepresentation argumentsI = new ListInputRepresentation("Arguments: ", canvas.getWidth()/2-100, canvas.getHeight()/2-50, 125, (fontHeight+2)*5);
		TextButtonRepresentation upB = new TextButtonRepresentation("/\\", canvas.getWidth()/2 + 55, canvas.getHeight()/2-50, 30, fontHeight);
		TextButtonRepresentation downB = new TextButtonRepresentation("\\/", canvas.getWidth()/2 + 55, canvas.getHeight()/2-50 + fontHeight, 30, fontHeight);
		TextButtonRepresentation delB = new TextButtonRepresentation("x", canvas.getWidth()/2 + 55, canvas.getHeight()/2-50 + fontHeight*2, 30, fontHeight);
		
		methodI.draw(canvas, graphics);
		argumentI.draw(canvas, graphics);
		addB.draw(canvas, graphics);
		argumentsI.draw(canvas, graphics);
		upB.draw(canvas, graphics);
		downB.draw(canvas, graphics);
		delB.draw(canvas, graphics);
	}

	private void drawResultMessageControls() {
		TextInputRepresentation labelI = new TextInputRepresentation("Label: ", canvas.getWidth()/2, canvas.getHeight()/2, 50, fontHeight);
		labelI.draw(canvas, graphics);
	}
}
