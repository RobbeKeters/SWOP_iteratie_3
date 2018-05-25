package Model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Stack;

import Controller.Mouse;

/**
 * A dialog box that contains controls for editing data in the model separately.
 */
public abstract class DialogBox extends Canvas {

	private ArrayList<Button> buttons = new ArrayList<Button>();
	private ArrayList<Label> textBoxes = new ArrayList<Label>();
	private ArrayList<Control> listControls = new ArrayList<Control>();
	
	/**
	 * Constructor.
	 * @param origineX		The x coordinate of origin.
	 * @param origineY		The y coordinate of origin.
	 * @param width			The width.
	 * @param height		The height.
	 */
	public DialogBox(int origineX, int origineY, int width, int height) {
		super(width, height, origineX, origineY);
	}
	
	/**
	 * Handles a mouse event.
	 * @param id		The type of mouse event.
	 * @param x			The x coordinate of the mouse event.
	 * @param y			The y coordinate of the mouse event.
	 */
	public abstract void handleMouse(Mouse id, int x, int y );
	
	/**
	 * Handles a key event.
	 * @param id		The type of key event.
	 * @param keyCode	The keycode of the key event.
	 * @param keyChar	The key character of the key event.
	 */
	public abstract void handleKey(int id, int keyCode, char keyChar);
	
	/**
	 * Returns this dialog box's buttons.
	 * @return		This dialog box's buttons.
	 */
	public ArrayList<Button> getButtons(){
		return buttons;
	}
	
	protected void addButton(Button b) {
		buttons.add(b);
	}
	
	protected void deleteButton(Button b) {
		buttons.remove(b);
	}
	
	/**
	 * Returns one of this dialog box's buttons.
	 * @param i		The given index.
	 * @return		The corresponding button.
	 */
	public Button getButton(int i) {
		return buttons.get(i);
	}
	
	/**
	 * Returns the index of the given button in this dialog box's list of buttons.
	 * @param b		The given button.
	 * @return		The corresponding index.
	 */
	public int getIndex(Button b) {
		return buttons.indexOf(b);
	}
	
	/**
	 * Returns this dialog box's text boxes.
	 * @return		This dialog box's text boxes.
	 */
	public ArrayList<Label> getTextBoxes(){
		return textBoxes;
	}

	protected void addTextBox(Label l) {
		textBoxes.add(l);
	}
	
	protected void deleteTextBox(Label l) {
		textBoxes.remove(l);
	}
	
	/**
	 * Returns one of this dialog box's text boxes.
	 * @param 		The given index.
	 * @return		The corresponding text box.
	 */
	public Label getTextBox(int i) {
		return textBoxes.get(i);
	}
	
	/**
	 * Returns the index of the given text box in this dialog box's list of text boxes.
	 * @param l		The given text box.
	 * @return		The corresponding index.
	 */
	public int getIndex(Label l) {
		return textBoxes.indexOf(l);
	}
	
	/**
	 * Update the position of this dialog box's elements.
	 * @param db			The given dialog box.
	 * @param newXorigine	The new x coordinate of the dialog box.
	 * @param newYorigine	The new y coordinate of the dialog box.
	 */
	public static void updatePositionsAttributes(DialogBox db , int newXorigine, int newYorigine) {
		int oldOrigineX = db.getOrigineX();
		int oldOrigineY = db.getOrigineY();
		db.setOrigineX(newXorigine);
		db.setOrigineY(newYorigine);
		
		int xOld = 0;
		int yOld = 0;
		int dx = 0;
		int dy = 0;
		
		// update Buttons
		ArrayList<Button> buttons = db.getButtons();
		for( Button b : buttons) {
			xOld = b.getOrigineX();
			yOld = b.getOrigineY();
		
			dx = Math.abs((xOld-oldOrigineX));
			dy = Math.abs((yOld-oldOrigineY));
			
			b.setOrigineX(newXorigine+dx);
			b.setOrigineY(newYorigine+dy);
		}
		// update Labels
		ArrayList<Label> labels = db.getTextBoxes();
		for( Label l : labels) {
			xOld = l.getLabelPositionDialog().getX();
			yOld = l.getLabelPositionDialog().getY();
			
			dx = Math.abs((xOld-oldOrigineX));
			dy = Math.abs((yOld-oldOrigineY));
			
			l.setLabelPositionDialog(newXorigine+ dx, newYorigine+dy);
			
		}
		
	}
	
	/**
	 * Returns this dialog box's controls.
	 * @return		This dialog box's controls.
	 */
	public ArrayList<Control> getListControls() {
		return listControls;
	}
	
	/**
	 * Sets this dialog box's controls to the given controls.
	 * @param listControls		The given list of controls
	 */
	public void setListControls(ArrayList<Control> listControls) {
		this.listControls = listControls;
	}
}
