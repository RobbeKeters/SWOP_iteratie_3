package Model;

import java.util.ArrayList;

public abstract class DialogBox extends Canvas {

	ArrayList<Button> buttons = new ArrayList<Button>();
	ArrayList<Label> textBoxes = new ArrayList<Label>();
	
	
	public DialogBox(int width, int height, int origineX, int origineY) {
		super(width, height, origineX, origineY);
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
	
}
