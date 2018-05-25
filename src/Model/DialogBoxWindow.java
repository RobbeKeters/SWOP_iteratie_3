package Model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Controller.Mouse;

public class DialogBoxWindow extends DialogBox{
	
	/**
	 * The linked window.
	 */
	public Window source;
	
	/**
	 * Constructor.
	 * @param origineX		The x coordinate of origin.
	 * @param origineY		The y coordinate of origin.
	 * @param width			The width.
	 * @param height		The height.
	 * @param s				The linked window.
	 */
	public DialogBoxWindow(int origineX, int origineY, int width, int height, Window s) {
		super(origineX, origineY, width, height);
		source = s;
		
		int xI = origineX + width/2 ;
		int yI = origineY + height/2 ;
		Button sequence = new Button("SEQUENCE: ", xI - 50, yI, Button.Type.RADIO);
		Button communication = new Button("COMMUNICATION: ",xI+100, yI, Button.Type.RADIO);
		
		// sequence is geselecteerd bij instantiatie
		sequence.setSelectedControl(true);
		
		if(s.getView().equals(Window.View.SEQUENCE)){
			sequence.setActivated(true);
			communication.setActivated(false);
		} else {
			sequence.setActivated(false);
			communication.setActivated(true);
		}
		
		super.addButton(sequence);
		super.addButton(communication);
		
		// Add all buttons en textboxes to one list 
		this.getListControls().addAll(this.getButtons());
		this.getListControls().addAll(this.getTextBoxes());
	}

	@Override
	public void handleMouse(Mouse id, int x, int y) {
		if ( id == Mouse.SINGLECLICK) {
			Button clickedButton = null;
			for ( Button b : this.getButtons() ) {
				if( b.inArea(x, y)) {
					b.setSelectedControl(true);
					b.setActivated(true);
					source.switchView();
					clickedButton = b;
					break;
				} 
			}
			if ( clickedButton != null ) {
				for ( Button b : this.getButtons())	{
					if ( b != clickedButton) {
						b.setSelectedControl(false);
						b.setActivated(false);
					}
				}
			}
		}
	}

	@Override
	public void handleKey(int id, int keyCode, char keyChar) {
		if(id  == KeyEvent.KEY_TYPED && keyChar == KeyEvent.VK_TAB ) {
			Button clickedButton = null;
			for ( Button b : this.getButtons() ) {
				if ( !b.isSelected()) { // only 2 options
					b.setSelectedControl(true);
					clickedButton = b;
				}
			}
			if( clickedButton != null	) {
				for ( Button b: this.getButtons()) {
					if ( b != clickedButton) {
						b.setSelectedControl(false);
					}
				}
			}
		}
		if( id == KeyEvent.KEY_TYPED && keyChar == KeyEvent.VK_SPACE) {
			for ( Button b: this.getButtons()) {
				if ( b.isSelected()) {
					b.setActivated(true);
				} else {
					b.setActivated(false);
				}
			}
			source.switchView();
		}
	}
	
	/**
	 * Updates the data of the buttons.
	 */
	public void updateButtons() {
		for ( Button b : this.getButtons()) {
			b.setActivated(false);
		}
		for ( Button b : this.getButtons()) {
			if( b.getTitle().equals("SEQUENCE: ") && source.getView() == Window.View.SEQUENCE) {
				b.setActivated(true);
			} else if (  b.getTitle().equals("COMMUNICATION: ") && source.getView() == Window.View.COMMUNICATION) {
				b.setActivated(true);
			}
		}
	}

}
