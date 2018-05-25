package Model;

/**
 * A control for a dialog box.
 */
public interface Control {
	/**
	 * Sets this control's selection status to the given selection status.
	 * @param selected		The given selection status.
	 */
	void setSelectedControl(Boolean selected);
	
	/**
	 * Returns this control's selection status.
	 * @return		This control's selection status.
	 */
	boolean isSelectedControl();
	
	/**
	 * Returns the type of control.
	 * @return		The type of control.
	 */
	TypeControl returnType();
	
	/**
	 * Returns whether or not the given coordinates are in the the control area.
	 * @param x		The given x coordinate.
	 * @param y 	The given y coordinate.
	 * @return 		Whether or not the given coordinates are in the control area.
	 */
	boolean inArea(int x,int y);
}
