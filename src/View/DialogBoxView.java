package View;

import java.awt.Color;
import java.awt.Graphics;

import Model.Button;
import Model.Canvas;
import Model.Label;
import Model.Window;
import View.representation.DialogLabelRepresentation;
import View.representation.InputRepresentation;
import View.representation.LabelRepresentation;
import View.representation.ListInputRepresentation;
import View.representation.RadioButtonRepresentation;
import View.representation.TextButtonRepresentation;
import View.representation.TextInputRepresentation;

public class DialogBoxView {

	protected Model.DialogBox dialogBox;
	protected Graphics graphics;
	private int fontHeight;
	
	
	public void draw(SelectedElement element, Model.DialogBox db, Graphics g){
		this.dialogBox = db;
		this.graphics = g;
		fontHeight = graphics.getFontMetrics().getHeight();
		
		drawSubWindow();
		drawButtons();
		drawLabels();
//		switch(element){
//		case VIEW:
//			drawViewControls();
//			break;
//		case PARTY:
//			drawPartyControls();
//			break;
//		case INVOCATION_MESSAGE:
//			drawInvocMessageControls();
//			break;
//		case RESULT_MESSAGE:
//			drawResultMessageControls();
//			break;
//		}

	}
	
	private void drawSubWindow() {
		graphics.setClip(dialogBox.getOrigineX(), dialogBox.getOrigineY(), dialogBox.getWidth(), dialogBox.getHeight());
		
		graphics.setColor(Color.WHITE);
		graphics.fillRect(dialogBox.getOrigineX(), dialogBox.getOrigineY(), dialogBox.getWidth(), dialogBox.getHeight());
		graphics.setColor(Color.BLACK);

		dialogBox.getFramework().draw(dialogBox, graphics);
	}
	
	private void drawButtons(){
		for(Button b : dialogBox.getButtons()){
			switch(b.getType()){
			case RADIO:				
				RadioButtonRepresentation r = new RadioButtonRepresentation(b.getTitle(), b.getOrigineX(), b.getOrigineY());
				r.setActivated(b.getActivated());
				r.draw(dialogBox, graphics);
				break;
			case TEXT:
				TextButtonRepresentation t = new TextButtonRepresentation(b.getTitle(), b.getOrigineX(), b.getOrigineY(), b.getWidth(), b.getHeight());
				t.draw(dialogBox, graphics);
				break;
			case CLOSE:
				break;
			}
		}
	}
		
	private void drawLabels(){
		for(Label l : dialogBox.getTextBoxes()){
			DialogLabelRepresentation lRep = new DialogLabelRepresentation(l);
//			TextInputRepresentation tRep = new TextInputRepresentation(l.getLabelname(), l.getLabelPositionDialog().getX() - graphics.getFontMetrics().stringWidth(l.getLabelname()), l.getLabelPositionDialog().getY(), l.getWidth(), l.getHeight());
//			tRep.draw(dialogBox, graphics);
			lRep.draw(dialogBox, graphics);
		}
	}
		
		
//		RadioButtonRepresentation sequenceB = new RadioButtonRepresentation("SEQUENCE: ", window.getWidth()/2 - 50, window.getHeight()/2);
//		RadioButtonRepresentation communicationB = new RadioButtonRepresentation("COMMUNICATION: ", window.getWidth()/2 + 100, window.getHeight()/2);
//		
//		if(window.getView().equals(Window.View.SEQUENCE)){
//			sequenceB.setActivated(true);
//			communicationB.setActivated(false);
//		} else {
//			sequenceB.setActivated(false);
//			communicationB.setActivated(true);
//		}
//		
//		sequenceB.draw(window, graphics);
//		communicationB.draw(window, graphics);
//	}
/*	
	private void drawPartyControls(){
		TextInputRepresentation instanceI = new TextInputRepresentation("Instance: ", window.getWidth()/2, (window.getHeight()/2)-50, 50, fontHeight);
		TextInputRepresentation classI = new TextInputRepresentation("Class: ", window.getWidth()/2, (window.getHeight()/2)-25, 50, fontHeight);
		RadioButtonRepresentation actorB = new RadioButtonRepresentation("Actor: ", window.getWidth()/2 - 35, window.getHeight()/2);
		RadioButtonRepresentation objectB = new RadioButtonRepresentation("Object: ", window.getWidth()/2 + 35, window.getHeight()/2);
		instanceI.draw(window, graphics);
		classI.draw(window, graphics);
		actorB.draw(window, graphics);
		objectB.draw(window, graphics);
		
	}
	
	private void drawInvocMessageControls() {
		TextInputRepresentation methodI = new TextInputRepresentation("Method: ", window.getWidth()/2-25, window.getHeight()/2-100, 100, fontHeight+2);
		InputRepresentation argumentI = new InputRepresentation(window.getWidth()/2-50, window.getHeight()/2+50, 100, fontHeight + 2);
		TextButtonRepresentation addB = new TextButtonRepresentation("Add", window.getWidth()/2 + 55, window.getHeight()/2 + 50, 30, fontHeight + 2);

		ListInputRepresentation argumentsI = new ListInputRepresentation("Arguments: ", window.getWidth()/2-100, window.getHeight()/2-50, 125, (fontHeight+2)*5);
		TextButtonRepresentation upB = new TextButtonRepresentation("/\\", window.getWidth()/2 + 55, window.getHeight()/2-50, 30, fontHeight);
		TextButtonRepresentation downB = new TextButtonRepresentation("\\/", window.getWidth()/2 + 55, window.getHeight()/2-50 + fontHeight, 30, fontHeight);
		TextButtonRepresentation delB = new TextButtonRepresentation("x", window.getWidth()/2 + 55, window.getHeight()/2-50 + fontHeight*2, 30, fontHeight);
		
		methodI.draw(window, graphics);
		argumentI.draw(window, graphics);
		addB.draw(window, graphics);
		argumentsI.draw(window, graphics);
		upB.draw(window, graphics);
		downB.draw(window, graphics);
		delB.draw(window, graphics);
	}

	private void drawResultMessageControls() {
		TextInputRepresentation labelI = new TextInputRepresentation("Label: ", window.getWidth()/2, window.getHeight()/2, 50, fontHeight);
		labelI.draw(window, graphics);
	}
	*/
}
