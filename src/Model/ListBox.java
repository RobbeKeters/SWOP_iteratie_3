package Model;

import java.util.ArrayList;

public class ListBox implements Control{

	boolean selected = false;
	TypeControl type = TypeControl.ListBox;
	int x = 0;
	int y = 0;
	ArrayList<Label> arguments = new ArrayList<Label>();
	Label selectedLabel = null;
	
	public ListBox(int x, int y, ArrayList<Label> labels) {
		
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

	public void selectNext() {
		int i = arguments.indexOf(selectedLabel);
		if(i<arguments.size()-1) {arguments.get(i+1).setSelected(true);selectedLabel.setSelected(false);selectedLabel = arguments.get(i+1);}
		else {arguments.get(0).setSelected(true);selectedLabel.setSelected(false);selectedLabel = arguments.get(0);}
	}
	
	public void selectPrevious() {
		int i = arguments.indexOf(selectedLabel);
		if(i>0) {arguments.get(i-1).setSelected(true);selectedLabel.setSelected(false);selectedLabel = arguments.get(i-1);}
		else {arguments.get(arguments.size()-1).setSelected(true);selectedLabel.setSelected(false); selectedLabel = arguments.get(arguments.size()-1);}
	}
	
	public Label getSelectedLabel() {
		return selectedLabel;
	}
	
	public void setSelectedLabel(Label l) {
		selectedLabel.setSelected(false);
		l.setSelected(true);
		selectedLabel = l;
	}

	public ArrayList<Label> getArguments() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean getSelected() {
		return selected;
	}
	
	public void setSelected(boolean b) {
		selected = b;
	}
	
}
