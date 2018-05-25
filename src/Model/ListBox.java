package Model;

import java.util.ArrayList;

public class ListBox implements Control{

	private boolean selected = false;
	private TypeControl type = TypeControl.ListBox;
	private int originX = 0;
	private int originY = 0;
	private int height = 0;
	private int width = 150;
	private static int labelHeight = 30;
	ArrayList<Label> arguments = new ArrayList<Label>();
	Label selectedLabel = null;
	
	/**
	 * Constructor.
	 * @param x		The x coordinate.
	 * @param y		The y coordinate.
	 * @param labels		The labels.
	 */
	public ListBox(int x, int y, ArrayList<Label> labels) {
		originX = x;
		originY = y;
		arguments = labels;
		height = labelHeight*labels.size();
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
	
	/**
	 * Select the next label.
	 */
	public void selectNext() {
		int i = arguments.indexOf(selectedLabel);
		if(i<arguments.size()-1) {arguments.get(i+1).setSelected(true);selectedLabel.setSelected(false);selectedLabel = arguments.get(i+1);}
		else {arguments.get(0).setSelected(true);selectedLabel.setSelected(false);selectedLabel = arguments.get(0);}
	}
	
	/**
	 * Select the previous label.
	 */
	public void selectPrevious() {
		int i = arguments.indexOf(selectedLabel);
		if(i>0) {arguments.get(i-1).setSelected(true);selectedLabel.setSelected(false);selectedLabel = arguments.get(i-1);}
		else {arguments.get(arguments.size()-1).setSelected(true);selectedLabel.setSelected(false); selectedLabel = arguments.get(arguments.size()-1);}
	}
	
	/**
	 * Adds an argument.
	 * @param l		The argument to be added.
	 */
	public void addArgument(Label l) {
		arguments.add(l);
		height+=labelHeight;
	}
	
	/**
	 * Deletes an argument.
	 * @param l		The argument to be deleted.
	 */
	public void deleteArgument(Label l) {
		arguments.remove(l);
		height-=labelHeight;
	}
	
	/**
	 * Returns the selected label.
	 * @return		The selected label.
	 */
	public Label getSelectedLabel() {
		return selectedLabel;
	}
	
	/**
	 * Select the given label
	 * @param l		The given label.
	 */
	public void setSelectedLabel(Label l) {
		selectedLabel.setSelected(false);
		l.setSelected(true);
		selectedLabel = l;
	}
	
	/**
	 * Returns this list box's arguments.
	 * @return		The arguments.
	 */
	public ArrayList<Label> getArguments() {
		return arguments;
	}
	
	/**
	 * Returns this list box's selection status.
	 * @return		The selection status.
	 */
	public boolean getSelected() {
		return selected;
	}
	
	/**
	 * Sets this dialog box's selection status to the given selection status.
	 * @param b		The given selection status.
	 */
	public void setSelected(boolean b) {
		selected = b;
		selectedLabel = arguments.get(0);
		selectedLabel.setSelected(true);
	}
	
	/**
	 * Return this dialog box's x coordinate.
	 * @return		The x coordinate.
	 */
	public int getX(){
		return originX;
	}
	
	/**
	 * Return this dialog box's y coordinate.
	 * @return		The y coordinate.
	 */
	public int getY(){
		return originY;
	}
	
	/**
	 * Return this dialog box's height.
	 * @return		The height.
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * Sets this dialog box's height to the given height.
	 * @param h		The given height.
	 */
	public void setHeight(int h) {
		height = h;
	}
	
	/**
	 * Return this dialog box's width.
	 * @return		The width.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Sets this dialog box's width to the given width.
	 * @param w		The given width.
	 */
	public void setWidth(int w) {
		width = w;
	}
	
	/**
	 * Return this dialog box's x coordinate of origin.
	 * @return		The x coordinate of origin.
	 */
	public int getOriginX() {
		return originX;
	}
	
	/**
	 * Sets this dialog box's x coordinate of origin to the given x coordinate of origin.
	 * @param x		The given x coordinate of origin.
	 */
	public void setOriginX(int x) {
		originX = x;
	}
	
	/**
	 * Return this dialog box's y coordinate of origin.
	 * @return		The y coordinate of origin.
	 */
	public int getOriginY() {
		return originY;
	}
	
	/**
	 * Sets this dialog box's y coordinate of origin to the given y coordinate of origin.
	 * @param y		The given y coordinate of origin.
	 */
	public void setOriginY(int y) {
		originX = y;
	}
	
}
