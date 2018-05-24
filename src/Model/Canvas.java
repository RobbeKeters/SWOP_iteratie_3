package Model;

/**
 * A system of parties and messages.
 * 
 * @author Kevin Lavrijssen, Wout Mees, Florent Nander Meijer, Robbe Keters
 */
public abstract class Canvas {
	
	private int width;
	private int height;

	private int origineX;
	private int origineY;
	
	private frameWork framework;
	public boolean updated = false;
	protected Mode mode = Mode.DEFAULT;
	
	/**
	 * Constructor.
	 * 
	 * @param width		The new canvas's wodth.
	 * @param height	The new canvas's height.
	 * @param origineX	The new canvas's x coordinate of origin.
	 * @param origineY	The new canvas's y coordinate of origin.
	 * @param i			The new canvas's interaction.
	 */
	
	public Canvas(int width, int height, int origineX, int origineY) {
		this.width = width;
		this.height = height;
		this.framework = new frameWork(origineX,origineY);
		this.origineX= origineX;
		this.origineY= origineY;
	}
	
	/**
	 * Return this canvas's mode.
	 * @return	This canvas's mode.
	 */
	public Mode getMode() {
		return mode;
	}
	
	/**
	 * Sets this canvas's mode to default mode.
	 */
	public void setDefaultMode() {
		mode = Mode.DEFAULT;
	}

	/**
	 * Returns this canvas's width.
	 * 
	 * @return		This canvas's width.
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Returns this canvas's height.
	 * 
	 * @return		This canvas's height.
	 */
	public int getHeight() {
		return this.height;
	}
	

	
	/**
	 * Returns this canvas's the x coordinate of origin.
	 * @return		This canvas's x coordinate of origin.
	 */
	public int getOrigineX() {
		return origineX;
	}
	
	/**
	 * Sets this canvas's x coordinate of origin to the given x coordinate.
	 * @param origineX		The given x coordinate.
	 */
	public void setOrigineX(int origineX) {
		this.origineX = origineX;
	}
	
	/**
	 * Returns this canvas's the y coordinate of origin.
	 * @return		This canvas's y coordinate of origin.
	 */
	public int getOrigineY() {
		return origineY;
	}
	
	/**
	 * Sets this canvas's y coordinate of origin to the given y coordinate.
	 * @param origineY		The given y coordinate.
	 */
	public void setOrigineY(int origineY) {
		this.origineY = origineY;
	}
	
	/**
	 * Returns this canvas's framework.
	 * @return		This canvas's framework.
	 */
	public frameWork getFramework() {
		return framework;
	}
	
	/**
	 * Sets this canvas's framework to the given framework.
	 * @param framework		The given framework.
	 */
	public void setFramework(frameWork framework) {
		this.framework = framework;
	}
	
	/**
	 * Sets this canvas's mode to the given mode.
	 * @param m		The given mode.
	 */
	public void setMode(Mode m) {
		this.mode = m;
	}
	
	/**
	 * Resizes the right side of this canvas.
	 * @param xMouse		The x coordinate to resize to.
	 */
	public void resizeXRightCanvas(int xMouse) {
		this.width = Math.max(this.getFramework().getBar().getMinimumWidth()+4, xMouse - this.getOrigineX());
	}
	
	/**
	 * Resizes the lower side of this canvas.
	 * @param yMouse		The y coordinate to resize to.
	 */
	public void resizeYLowerCanvas(int yMouse) {
		this.height =  Math.max(this.getFramework().getBar().getHeight()+4, yMouse - this.getOrigineY());
	}
	
	/**
	 * Resizes the left side of this canvas.
	 * @param xMouse		The x coordinate to resize to.
	 */
	public void resizeXLeftCanvas(int xMouse) {
		int oldOrigine = this.getOrigineX();
		int oldWidth = this.getWidth();
		if( xMouse <= oldOrigine) {
			this.width += Math.abs(oldOrigine-xMouse);
			this.origineX = oldOrigine - Math.abs(oldOrigine -xMouse);
		} else {
			this.width -= Math.abs(oldOrigine-xMouse);
			this.origineX = oldOrigine + Math.abs(oldOrigine -xMouse);
		}
		if( this.getFramework().getBar().getMinimumWidth()+4 >= this.width	) {
			this.origineX = oldOrigine;
			this. width = oldWidth;
		}
	}
	
	/**
	 * Resizes the upper side of this canvas.
	 * @param yMouse		The y coordinate to resize to.
	 */
	public void resizeYTopCanvas(int yMouse) {
		int oldOrigine = this.getOrigineY();
		int oldHeight = this.height;
		if( yMouse <= oldOrigine) {
			this.height += Math.abs(oldOrigine-yMouse);
			this.origineY = oldOrigine - Math.abs(oldOrigine -yMouse);
		} else {
			this.height -= Math.abs(oldOrigine-yMouse);
			this.origineY = oldOrigine + Math.abs(oldOrigine -yMouse);
		}
		if (this.getFramework().getBar().getHeight()+4 >= this.height ) {
			this.origineY = oldOrigine;
			this.height = oldHeight;
		}
	}
	
	/**
	 * Sets this canvas's width to the given width.
	 * @param width		The given width.
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Sets this canvas's height to the given height.
	 * @param height		The given height.
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	public static boolean closeCanvas(Canvas window, int xMouse, int yMouse) {
		Button button = window.getFramework().getBar().getButton();
		int buttonOrigineX = button.getOrigineX();
		int buttonOrigineY = button.getOrigineY();
		int upperX = buttonOrigineX + button.getWidth();
		int upperY = buttonOrigineY + button.getHeight();
		if( xMouse >= buttonOrigineX && xMouse <= upperX && yMouse >= buttonOrigineY && yMouse <= upperY ) {
			return true;
		}
		return false;
	}
	
	public static boolean moveCanvas(Canvas window,int xMouse, int yMouse) {
		// If cursor is in button Area => wait for id==Mouse.SINGLECLICK in handle Method
		if(closeCanvas(window, xMouse,yMouse)) {
			return false;
		}
		
		titleBar bar = window.getFramework().getBar();
		int barOrigineX = bar.getOrigineX();
		int barOrigineY = bar.getOrigineY();
		int upperX = barOrigineX + window.getFramework().getBar().getWidth(window);
		int upperY = barOrigineY + window.getFramework().getBar().getHeight();
		if( xMouse >= barOrigineX && xMouse <= upperX && yMouse >= barOrigineY && yMouse <= upperY ) {
			return true;
		}
		return false;
	}
	
	public static boolean resizeXRightCanvas(Canvas canvas, int xMouse, int yMouse ) {		
		int origineX = canvas.getOrigineX();
		int origineY = canvas.getOrigineY();
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		// Right check 
		if ( (origineX + width -4) <= xMouse && (origineX + width +4) >= xMouse	 && (origineY -4) <= yMouse && (origineY +4 + height) >= yMouse ) {
			return true;
		}
		return false;		
	}
	public static boolean resizeXLeftCanvas(Canvas canvas, int xMouse, int yMouse) {
		int origineX = canvas.getOrigineX();
		int origineY = canvas.getOrigineY();
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		// Left check
		if( (origineX-4) <= xMouse && (origineX+4) >= xMouse && (origineY -4) <= origineY && (origineY + height+4) >= yMouse) {
			return true;
		}
		return false;
	}
	public static boolean resizeYTopCanvas(Canvas canvas, int xMouse, int yMouse) {
		int origineX = canvas.getOrigineX();
		int origineY = canvas.getOrigineY();
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		// Top check
		if( (origineX-4) <= xMouse && xMouse <= (origineX + width +4) && (origineY -4) <= yMouse && (origineY +4) >= yMouse) {
			return true;
		}
		return false;
	}
	public static boolean resizeYLowerCanvas(Canvas canvas,int xMouse, int yMouse) {
		int origineX = canvas.getOrigineX();
		int origineY = canvas.getOrigineY();
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		// Lower check
		if( (origineX-4) <= xMouse && (origineX + width +4) >= xMouse && (origineY +height -4) <= yMouse && (origineY+height+4)>= yMouse ) {
			return true;
		}
		return  false;
	}
	public static boolean resizeLowerRightCornerCanvas(Canvas canvas, int xMouse, int yMouse) {
		int xLow = canvas.getOrigineX() + canvas.getWidth() - 4;
		int xHigh = xLow + 8;
		int yLow = canvas.getOrigineY() + canvas.getHeight() -4;
		int yHigh = yLow + 8;
		if ( xMouse >= xLow && xMouse <= xHigh && yMouse  >= yLow && yMouse <= yHigh) {
			return true;
		}
		return false;
	}
	public static boolean resizeLowerLeftCornerCanvas(Canvas canvas, int xMouse, int yMouse) {
		int xLow = canvas.getOrigineX() - 4;
		int xHigh = xLow + 8;
		int yLow = canvas.getOrigineY() + canvas.getHeight() -4;
		int yHigh = yLow + 8;
		if ( xMouse >= xLow && xMouse <= xHigh && yMouse  >= yLow && yMouse <= yHigh) {
			return true;
		}
		return false;
	}
	public static boolean resizeTopLeftCornerCanvas(Canvas canvas, int xMouse, int yMouse) {
		int xLow = canvas.getOrigineX() - 4;
		int xHigh = xLow + 8;
		int yLow = canvas.getOrigineY()  -4;
		int yHigh = yLow + 8;
		if ( xMouse >= xLow && xMouse <= xHigh && yMouse  >= yLow && yMouse <= yHigh) {
			return true;
		}
		return false;
	}
	public static boolean resizeTopRightCornerCanvas(Canvas canvas, int xMouse, int yMouse) {
		int xLow = canvas.getOrigineX() + canvas.getWidth() - 4;
		int xHigh = xLow + 8;
		int yLow = canvas.getOrigineY() -4;
		int yHigh = yLow + 8;
		if ( xMouse >= xLow && xMouse <= xHigh && yMouse  >= yLow && yMouse <= yHigh) {
			return true;
		}
		return false;
	}
	
	
	

}