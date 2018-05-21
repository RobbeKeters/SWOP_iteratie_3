package View.representation;

import java.awt.Graphics;
import Model.Canvas;
import Model.Message;

public class MessageRepresentation implements Representation{

	protected Representation messageToDraw;
	protected Message message;
	
	public MessageRepresentation(Message m, Representation messageToDraw){
		this.messageToDraw = messageToDraw;
		this.message = m;
	}
	
	public void draw(Canvas c, Graphics g) {
		
	}

}
