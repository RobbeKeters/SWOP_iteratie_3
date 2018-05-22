package Model.Handler;

import Model.Canvas;
import Model.Label;
import Model.Message;
import Model.Object;
import Model.Party;
import Model.Window;

/**
 * A class that handles a sequence of actions that change the state of a canvas.
 */
public abstract class Handler {
	
	/**
	 * Perform a sequence of actions that change the state of a canvas.
	 * @param window		The canvas to edit.
	 */
	public static Party getPartyAt(int x, int y, Window window) {
		if(window.getView() == Window.View.SEQUENCE) {return getPartySequenceDiagram(x,y,window);}
	    else {return getPartyCommunicationDiagram(x,y,window);}
	}

	private static Party getPartySequenceDiagram(int x, int y, Window window) {
		for(Party p : window.getParties()) {
			int xPos;
			int yPos;
			int width;
			int height;
			if(p.getClass() == Object.class) {
				xPos = p.getLabel().getLabelPositionSequence().getX();
				yPos = p.getLabel().getLabelPositionSequence().getY();
				width = p.getWidth()+3;
				height = p.getHeight()+6;
			} else {
				xPos = p.getLabel().getLabelPositionSequence().getX();
				yPos = p.getPosSeq().getY();
				width = p.getWidth();
				height = p.getPosSeq().getY() + p.getHeight() + 10 + p.getLabel().getHeight();
			}
			if( 
					isInAreaSequence(
							x,
							y,
							xPos,
							yPos,
							width,
							height
							)
					
				) {return p;} 
		}
		return null;
	}

	private static Party getPartyCommunicationDiagram(int x, int y, Window window) {
		for(Party p : window.getParties()) {
			int height;
			if(p.getClass() == Object.class) {
				height = (p.getLabel().getHeight()+12);
			} else {
				height = (p.getLabel().getHeight()+60);
			}
			if(
					isInAreaCommunication(
							x,
							y,
							(p.getPosComm().getX()-6),
							(p.getPosComm().getY()-(p.getLabel().getHeight()+6)),
							(p.getLabel().getWidth()+12),
							height
							)
					
				) {return p;} 
		}
		return null;
	}
	
	protected static boolean isInAreaSequence(int mouseX, int mouseY, int coordX, int coordY, int width, int height ) {
		 if(xAxis(mouseX, coordX, width) && yAxis(mouseY, coordY, height)) {return true;}
		 else{return false;}
	}
	
	private static boolean xAxis(int mouseX, int coordX, int width) {
		if((mouseX>=coordX-(width/2)) && (mouseX<=coordX+(width/2))) {return true;}
	
		else{return false;}
	}

	private static boolean yAxis(int mouseY, int coordY, int height) {
		if((mouseY>=coordY-(height/2)) && (mouseY<=coordY+(height/2))) {return true;}
		else{return false;}
	}
	
	private static boolean isInAreaCommunication(int mouseX, int mouseY, int coordX, int coordY, int width, int height) {
		return
				mouseX>=coordX &&
				mouseX<=coordX+width &&
				mouseY>=coordY &&
				mouseY<=coordY+height;
	}

	protected static boolean approxLifeLine(Party p, int x) {
		return (p.getPosSeq().xCoordinate-30)<x &&
				(p.getPosSeq().xCoordinate+30)>x;
	}
	
	protected static Party approxLifeLine(int x, Window window) {
		for(Party p : window.getParties()) {
			if(approxLifeLine(p, x)) {return p;}
		}
		return null;
	}

	protected static Label getLabelAt(int x, int y, Window window) {
		if(window.getView() == Window.View.SEQUENCE) {return getLabelSequenceDiagram(x,y,window);}
		else{return getLabelCommunicationDiagram(x,y,window);}
		
	}
	
	private static Label getLabelCommunicationDiagram(int x, int y, Window window) {
		// Check party labels
		for(Party p : window.getParties()) {
			if(
					isInAreaCommunication(
							x,
							y,
							p.getPosComm().getX()+3,
							((p.getPosComm().getY()-p.getLabel().getHeight())+3),
							(p.getLabel().getWidth()-6),
							(p.getLabel().getHeight()-6)
							)
				) 
				
			{return p.getLabel();}
		}
		
		// Check message labels
		for(Message m : window.getMessages()) {
			if(
					isInAreaCommunication(
							x,
							y,
							m.getLabel().getLabelPositionComm().xCoordinate,
							(m.getLabel().getLabelPositionComm().yCoordinate-m.getLabel().getHeight()),
							m.getLabel().getWidth(),
							m.getLabel().getHeight()
							)
				) 
				
			{return m.getLabel();}
			
		}
		
		return null;
	}
	
	private static Label getLabelSequenceDiagram(int x, int y, Window window) {
		// Check party labels
		for(Party p : window.getParties()) {
			if(
					isInAreaSequence(
							x,
							y,
							p.getLabel().getLabelPositionSequence().xCoordinate,
							p.getLabel().getLabelPositionSequence().yCoordinate,
							p.getLabel().getWidth()-3,
							p.getLabel().getHeight()-6
							)
				) 
				
			{return p.getLabel();}
		}
		
		// Check message labels
		for(Message m : window.getMessages()) {
			if(
					isInAreaSequence(
							x,
							y,
							m.getLabel().getLabelPositionSequence().xCoordinate,
							m.getLabel().getLabelPositionSequence().yCoordinate,
							m.getLabel().getWidth(),
							m.getLabel().getHeight()
							)
				) 
				
			{return m.getLabel();}
			
		}
		
		return null;
		
	}
	
	protected static void resetRoles(Window window) {
		for(Party p : window.getParties()) {
			p.makeNone();
		}
	}

}
