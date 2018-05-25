package Model;

import java.util.ArrayList;

public class ListBox implements Control{

	private boolean selected = false;
	private TypeControl type = TypeControl.ListBox;
	private int originX = 0;
	private int originY = 0;
	private int height = 0;
	private int width = 150;
	ArrayList<Label> arguments = new ArrayList<Label>();
	Label selectedLabel = null;
	
	public ListBox(int x, int y, ArrayList<Label> labels) {
		originX = x;
		originY = y;
		arguments = labels;
		height = 30*labels.size();
	}
	
	@Override
	public void setSelectedControl(Boolean selected) {
		this.selected = selected;
		if(selected) {selectedLabel = arguments.get(0); selectedLabel.setSelected(true);}
		else {selectedLabel.setSelected(false); selectedLabel = null;}
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
		return x <= originX + width && x >= originX && y <= originY+height && y >= originY;
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
	
	public void addArgument(Label l) {
		arguments.add(l);
	}
	
	public void deleteArgument(Label l) {
		arguments.remove(l);
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
		return arguments;
	}
	
	public boolean getSelected() {
		return selected;
	}
	
	public void setSelected(boolean b) {
		selected = b;
		selectedLabel = arguments.get(0);
		selectedLabel.setSelected(true);
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setHeight(int h) {
		height = h;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int w) {
		width = w;
	}
	
	public int getOriginX() {
		return originX;
	}
	
	public void setOriginX(int x) {
		originX = x;
	}
	
	public int getOriginY() {
		return originY;
	}
	
	public void setOriginY(int y) {
		originY = y;
	}
	
}
