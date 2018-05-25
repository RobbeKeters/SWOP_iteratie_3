package View;

import java.awt.Color;
import java.awt.Graphics;

import Model.Button;
import Model.Canvas;
import Model.Label;
import Model.ListBox;
import Model.Window;
import View.representation.DialogLabelRepresentation;
import View.representation.InputRepresentation;
import View.representation.LabelRepresentation;
import View.representation.ListBoxRepresentation;
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
		drawListBoxes();
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
				r.setSelected(b.isSelected());
				r.draw(dialogBox, graphics);
				break;
			case TEXT:
				TextButtonRepresentation t = new TextButtonRepresentation(b.getTitle(), b.getOrigineX(), b.getOrigineY(), b.getWidth(), b.getHeight());
				t.setSelected(b.isSelected());
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
			lRep.draw(dialogBox, graphics);
		}
	}
	
	private void drawListBoxes(){
		for(ListBox lb : dialogBox.getListBoxes()){
			ListBoxRepresentation lbRep = new ListBoxRepresentation(lb);
			lbRep.setArguments(lb.getArguments());
			lbRep.draw(dialogBox, graphics);
			System.out.println("dialogBox pos: " + dialogBox.getOrigineX() + " " + dialogBox.getOrigineY() + " listbox: " + lb.getOriginX() + " " + lb.getOriginY());
			for(Label l : lb.getArguments()){
				DialogLabelRepresentation lRep = new DialogLabelRepresentation(l);
				lRep.draw(dialogBox, graphics);
			}
			
		}
	}
}
