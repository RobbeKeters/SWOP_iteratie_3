package View.representation;

import java.awt.Graphics;
import Model.Canvas;
import Model.Message;

/**
 * A visual representation of an message.
 */
public class MessageRepresentation implements Representation{

	protected Representation messageToDraw;
	protected Message message;
	
	/**
	 * Constructor.
	 * @param m					The message to represent.
	 * @param messageToDraw		The message to draw.
	 */
	public MessageRepresentation(Message m, Representation messageToDraw){
		this.messageToDraw = messageToDraw;
		this.message = m;
	}
	
	/**
	 * Draws the message's representation.
	 * @param c		The canvas to which the actor belongs to.
	 * @param g		The graphics element used to draw.
	 */
	public void draw(Canvas c, Graphics g) {
		
	}

}
