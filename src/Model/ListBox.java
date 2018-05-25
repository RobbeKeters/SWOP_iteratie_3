package Model;

import java.util.ArrayList;

public class ListBox implements Control{

	boolean selected = false;
	TypeControl type = TypeControl.ListBox;
	int x = 0;
	int y = 0;
	ArrayList<Label> arguments = new ArrayList<Label>();
	
	public ListBox() {
		
	}
	
	@Override
	public void setSelectedControl(Boolean selected) {
		this.selected = selected;
	}

	@Override
	public boolean isSelectedControl() {
		return selected;
	}

	@Override
	public TypeControl returnType() {
		return type;
	}

	@Override
	public boolean inArea(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	public Label getNext() {
		return null;
	}
	
	public Label getPrevious() {
		return null;
	}
	
}
