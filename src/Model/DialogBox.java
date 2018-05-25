package Model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Stack;

import Controller.Mouse;

public abstract class DialogBox extends Canvas {

	private ArrayList<Button> buttons = new ArrayList<Button>();
	private ArrayList<Label> textBoxes = new ArrayList<Label>();
	private ArrayList<ListBox> listBoxes = new ArrayList<ListBox>();
	private ArrayList<Control> listControls = new ArrayList<Control>();
	
	public DialogBox(int origineX, int origineY, int width, int height) {
		super(width, height, origineX, origineY);
	}
	
	public abstract void handleMouse(Mouse id, int x, int y );
	public abstract void handleKey(int id, int keyCode, char keyChar);
	
	public ArrayList<Button> getButtons(){
		return buttons;
	}
	
	protected void addButton(Button b) {
		buttons.add(b);
	}
	
	protected void deleteButton(Button b) {
		buttons.remove(b);
	}
	
	public Button getButton(int i) {
		return buttons.get(i);
	}
	
	public int getIndex(Button b) {
		return buttons.indexOf(b);
	}
	
	public ArrayList<Label> getTextBoxes(){
		return textBoxes;
	}

	protected void addTextBox(Label l) {
		textBoxes.add(l);
	}
	
	protected void deleteTextBox(Label l) {
		textBoxes.remove(l);
	}
	
	public Label getTextBox(int i) {
		return textBoxes.get(i);
	}
	
	public int getIndex(Label l) {
		return textBoxes.indexOf(l);
	}
	
	public ArrayList<ListBox> getListBoxes() {
		return listBoxes;
	}
	
	protected void addListBox(ListBox lb){
		listBoxes.add(lb);
	}
	
	protected void removeListBox(ListBox lb){
		listBoxes.remove(lb);
	}
	
	public ListBox getListBox(int i) {
		return listBoxes.get(i);
	}
	
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

	public ArrayList<Control> getListControls() {
		return listControls;
	}

	public void setListControls(ArrayList<Control> listControls) {
		this.listControls = listControls;
	}
}
