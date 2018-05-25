package Model;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A closing button used to close a subwindow.
 * 
 * @author Kevin Lavrijssen, Wout Mees, Florent Nander Meijer, Robbe Keters
 */
public class Button implements Control{
	
	private Type type;
	private String title;
	private boolean activated;
	private int width;
	private int height;
	private int OrigineX;
	private int OrigineY;
	private boolean selected;
	private boolean disabled;
	
	/**
	 * Constructor.
	 * @param xInput	The x coordinate of the origin of the new button.
	 * @param yInput	The y coordinate of the origin of the new button.
	 */
	public Button(int xInput,int yInput, Type type) {
		this.selected = false;
		this.OrigineX = xInput;
		this.OrigineY =yInput;
		this.width = 14;
		this.height = 14;
		setType(type);
	}
	
	public Button(String title, int xInput, int yInput, Type type){
		this(xInput, yInput, type);
		setTitle(title);
	}
	
	/**
	 * Return this button's width.
	 * @return	This button's width.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Sets this button's width to the given width.
	 * @param width		The given width.
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Return this button's height.
	 * @return		This button's height.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Sets this button's height to the given height.
	 * @param height	The given height.
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Draws a visual representation of this button.
	 * @param g		The graphics object used to draw.
	 */
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(OrigineX , OrigineY, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(OrigineX , OrigineY, width, height);
		g.drawLine(OrigineX, OrigineY, OrigineX+ width, OrigineY +height);
		g.drawLine(OrigineX , OrigineY + height , OrigineX + width , OrigineY);
	}
	
	/**
	 * Return this button's x coordinate of origin.
	 * @return		This button's x coordinate of origin.
	 */
	public int getOrigineX() {
		return OrigineX;
	}
	
	/**
	 * Sets this button's x coordinate of origin to the given x coordinate.
	 * @param origineX		The given x coordinate.
	 */
	public void setOrigineX(int origineX) {
		OrigineX = origineX;
	}
	
	/**
	 * Return this button's y coordinate of origin.
	 * @return		This button's y coordinate of origin.
	 */
	public int getOrigineY() {
		return OrigineY;
	}
	
	/**
	 * Sets this button's y coordinate of origin to the given y coordinate.
	 * @param origineY		The given y coordinate.
	 */
	public void setOrigineY(int origineY) {
		OrigineY = origineY;
	}
	
	/**
	 * An enumeration to indicate the type of button.
	 */
	public enum Type {
		CLOSE,
		TEXT,
		RADIO;
	}
	
	/**
	 * Sets this button's type to the given y type.
	 * @param t		The given type.
	 */
	private void setType(Type t){
		type = t;
	}
	
	/**
	 * Returns the this button's type.
	 * @return		This button's type.
	 */
	public Type getType(){
		return type;
	}
	
	/**
	 * Sets this button's title to the given title.
	 * @param title		This button's new title.
	 */
	private void setTitle(String title){
		this.title = title;
	}
	
	/**
	 * Returns this button's title.
	 * @return		This button's title.
	 */
	public String getTitle(){
		return title;
	}
	
	/**
	 * Sets this button's activation status to the given activation status.
	 * @param a		This button's new activation status.
	 */
	public void setActivated(boolean a){
		activated = a;
	}
	
	/**
	 * Returns this button's activation status.
	 * @return		This button's activation status.
	 */
	public boolean getActivated(){
		return activated;
	}
	
	/**
	 * Sets this button's selected control status title to the given selected control status.
	 * @param selected		This button's new selected control status.
	 */
	@Override
	public void setSelectedControl(Boolean selected) {
		this.selected= selected;		
	}
	
	/**
	 * Returns this button's selection status.
	 * @return		This button's selected control status.
	 */
	public boolean isSelected() {
		return selected;
	}
	
	/**
	 * Returns this button's selection status.
	 * @return		This button's selected control status.
	 */
	@Override
	public boolean isSelectedControl() {
		return this.selected;
	}
	
	/**
	 * Sets this button disabled status to the given status.
	 * @param d		The given disabled status.
	 */
	public void setDisabled(boolean d){
		this.disabled = d;
	}
	
	/**
	 * Returns this button's disabled status.
	 * @return		This button's disabled status.
	 */
	public boolean isDisabled() {
		return this.disabled;
	}

	/**
	 * Returns the control type of this class.
	 * @return 		The button control type
	 */
	@Override
	public TypeControl returnType() {
		return TypeControl.Button;
	}
	
	/**
	 * Returns whether or not the given coordinates are in the the button area.
	 * @param x		The given x coordinate.
	 * @param y 	The given y coordinate.
	 * @return 		Whether or not the given coordinates are in the button area.
	 */
	@Override
	public boolean inArea(int x, int y) {
		if ( x <= this.getOrigineX() + this.width && x >= this.getOrigineX() && y <= this.OrigineY+this.height && y >= this.OrigineY ) {
			return true;
		} 
		return false;
	}	
	
}
