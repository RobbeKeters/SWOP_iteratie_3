package Model;

import java.util.ArrayList;

public abstract class DialogBox extends Canvas {

	private ArrayList<Button> buttons = new ArrayList<Button>();
	private ArrayList<Label> textBoxes = new ArrayList<Label>();
	
	
	public DialogBox(int origineX, int origineY, int width, int height) {
		super(width, height, origineX, origineY);
	}
	
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
	
}
