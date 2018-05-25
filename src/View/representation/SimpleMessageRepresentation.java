package View.representation;

import java.awt.Graphics;

import Model.Canvas;
import Model.Message;

/**
 * A visual representation of a simple message.
 */
public class SimpleMessageRepresentation implements Representation {

	protected Message message;
	
	/**
	 * Constructor.
	 * @param m		The message to represent.
	 */
	public SimpleMessageRepresentation(Message m){
		this.message = m;
	}
	
	@Override
	public void draw(Canvas c, Graphics g) {}

}
