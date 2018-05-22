package Model.Handler;

import java.awt.event.KeyEvent;

import Model.ADJUSTED_TYPE;
import Model.Canvas;
import Model.InvocationMessage;
import Model.Label;
import Model.Message;
import Model.Party;
import Model.Point;
import Model.Window;

/**
 * A handler that handles the actions of a label being edited.
 */
public class EditLabelHandler extends Handler{
	
	/**
	 * Handles a label being edited.
	 * @param window		The canvas to in which the label is present.
	 * @param label			The label being edited.
	 * @param x			The x coordinate of the mouse event used to handle this event.
	 * @param y			The y coordinate of the mouse event used to handle this event.
	 */
	public static void handle(Window window, Label label, int x, int y) {
		handle(window, label, Character.MIN_VALUE, x, y);
	}
	
	/**
	 * Handles a label being edited.
	 * @param window		The canvas to in which the label is present.
	 * @param label			The label being edited.
	 * @param party			The party which the given label belongs to.
	 * @param character		The key character used to edit the label.
	 * @param x				The x coordinate of the mouse event used to handle this event.
	 * @param y				The y coordinate of the mouse event used to handle this event.
	 */
	public static void handle(Window window, Label label, Party party, char character, int x, int y) {
		
		if(label.getSelected()) {
			if(character==KeyEvent.VK_DELETE) {return;}
			if (character == KeyEvent.VK_BACK_SPACE){
				handle(window, label, "BACKSPACE");
				return;	
			} else if (character == KeyEvent.VK_ENTER){
				if(isCorrectPartyLabel(label.getLabelname())) {
					handle(window, label, "ENTER");
				}
				return;
			} else if (character == KeyEvent.VK_ESCAPE)
				return;			
			if(window.getView() == Window.View.SEQUENCE)
				label.setLabelPositionSeq(new Point(x, y));
			else 
				label.setLabelPositionComm(new Point(x, y));
			String name = label.getLabelname().replace("|", "") + character + '|';
			label.setLabelname(name);
			int width = 8*name.length();
			if (width == 0)
				width = 11;
			label.setWidth(width);
			// Notify Interaction if Party is allowed !!!!!!!!!!!!!!!!!!!!
			if (isCorrectPartyLabel(label.getLabelname())) {
				window.getInteraction().adjusted(ADJUSTED_TYPE.PARTY_LABEL,window);
			}
		} else {
			label.setLabelname(label.getLabelname().replace("|", ""));
		}
	}
	
	/**
	 * Handle a label being edited.
	 * @param window		The canvas to edit.
	 * @param label			The label being edited.
	 * @param character			The key character used to edit the label.
	 * @param x			The x coordinate of the mouse event used to handle this event.
	 * @param y			The y coordinate of the mouse event used to handle this event.
	 */
	public static void handle(Window window, Label label, char character, int x, int y) {
		
		
		if(label.getSelected()){
			if(character==KeyEvent.VK_DELETE) {return;}
			if (character == KeyEvent.VK_BACK_SPACE){
				handle(window, label, "BACKSPACE");
				return;	
			} else if (character == KeyEvent.VK_ENTER) {
				handle(window, label, "ENTER");
				return;
			} else if (character == KeyEvent.VK_ESCAPE)
				return;			
			if(window.getView() == Window.View.SEQUENCE)
				label.setLabelPositionSeq(new Point(x, y));
			else 
				label.setLabelPositionComm(new Point(x, y));
			String name = label.getLabelname().replace("|", "") + character + '|';
			label.setLabelname(name);
			int width = 8*name.length();
			if (width == 0)
				width = 11;
			label.setWidth(width);
			
			
			// Notify Interaction
			window.getInteraction().adjusted(ADJUSTED_TYPE.MESSAGE_LABEL,window);
		} else {
			label.setLabelname(label.getLabelname().replace("|", ""));
		}
	}
	
	/**
	 * Handle a label being edited.
	 * @param window		The canvas to edit.
	 * @param label			The label being edited.
	 * @param keyCode		The kind of key event being handled.
	 */
	public static void handle(Window window, Label label, String keyCode){
		String name = label.getLabelname().replace("|", "");
		if(name.length() <= 0) return;
		switch(keyCode){
		case "BACKSPACE":
			name = name.substring(0, name.length()-1);
			label.setLabelname(name + '|');
			if (isCorrectPartyLabel(label.getLabelname())) 
				window.getInteraction().adjusted(ADJUSTED_TYPE.PARTY_LABEL,window);
			break;
		case "ENTER":
			label.setLabelname(name);
			label.setSelected(false);
		}
	}
	
	static private boolean isCorrectPartyLabel(String label){
		if(label.matches("([a-z][a-zA-Z]*)?:[A-Z][a-zA-Z]*\\|")){
			return true;
		}
		return false;
	}
	
	/**
	 * Checks whether a canvas is in the label editing mode of a party or not.
	 * 
	 * @param window 	The canvas to check.
	 * @return			True if the canvas is in the label editing mode for a certain party.
	 */
	static public boolean editLabelModeParty(Window  window) {
		for(Party p : window.getParties()){
			if(p.getLabel().getSelected()) {
				return !(isCorrectPartyLabel(p.getLabel().getLabelname()));
			}
		}
		return false;
	}
	
	/**
	 * Checks whether a canvas is in the label editing mode of a message or not.
	 * 
	 * @param window 	The canvas to check.
	 * @return			True if the canvas is in the label editing mode for a certain message.
	 */
	static public boolean editLabelModeMessage(Window window) {
		for( Message m : window.getMessages()) {
			if( m.getClass() == InvocationMessage.class && m.getLabel().getSelected()) {
				return true;
			}
		}
		return false;
	}
	
}