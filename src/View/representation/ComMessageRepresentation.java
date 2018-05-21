package View.representation;

import java.awt.Graphics;

import Model.Canvas;
import Model.Message;

public class ComMessageRepresentation extends MessageRepresentation {

	public ComMessageRepresentation(Message m, Representation messageToDraw) {
		super(m, messageToDraw);
	}

	public void draw(Canvas c, Graphics g) {
		messageToDraw.draw(c, g);
		drawLine(c, g);
		drawArrow(c, g);
	}
	
	private void drawLine(Canvas c, Graphics g) {
		int x1 = message.getReicevedBy().getPosComm().getX();
		int x2 = message.getSentBy().getPosComm().getX();
		int y1 = message.getReicevedBy().getPosComm().getY();
		int y2 = message.getSentBy().getPosComm().getY();
		g.drawLine(x1,y1,x2,y2);
	}
	
	private void drawArrow(Canvas c, Graphics g) {
		// Length Vector
		
		int x1 = message.getReicevedBy().getPosComm().getX();
		int x2 = message.getSentBy().getPosComm().getX();
		int y1 = message.getReicevedBy().getPosComm().getY();
		int y2 = message.getSentBy().getPosComm().getY();
		
		int x3 = message.getLabel().getLabelPositionComm().getX() - 10;
		int y3 = message.getLabel().getLabelPositionComm().getY() - (message.getLabel().getHeight()/2);
		
		double  lengthArrow = Math.sqrt(  (Math.pow(Math.abs(x2-x1),2)) +  (Math.pow(Math.abs(y2-y1),2))  );
		double A = Math.abs(x2-x1);
		
		double angle =  (Math.acos(A/lengthArrow));
		
		// decide which Quadrant
		if( x1>x2 && y2>y1) { 
			angle = Math.PI - angle;
		} else if ( x1>x2 && y1>y2 ) {
			angle = Math.PI + angle;
		} else if (x2>x1 && y1>y2){
			angle *= -1;
		} else if (x1>x2 && y1==y2) { // Carefull with calculation sin,cos....
			angle = Math.PI;
		} else if (x2==x1 && y1>y2) {
			angle = Math.PI*-0.5;
		}

		int xStart = 15;
		int yStart = 5;
		double xArrow1 = x3 + ((xStart * Math.cos(angle)) - (yStart * Math.sin(angle)));
		double yArrow1 = y3 + ((xStart * Math.sin(angle)) + (yStart * Math.cos(angle)));
		g.drawLine(x3, y3, (int)xArrow1, (int)yArrow1);	
		xStart = 15;
		yStart = -5;
		double xArrow2 = x3 + ((xStart * Math.cos(angle)) - (yStart * Math.sin(angle)));
		double yArrow2 = y3 + ((xStart * Math.sin(angle)) + (yStart * Math.cos(angle)));
		g.drawLine(x3, y3, (int)xArrow2, (int)yArrow2);	
		
	}
	
	

}
