package Controller;

import java.awt.event.KeyEvent;

import Model.Canvas;
import Model.Interaction;
import Model.Message;
import Model.Mode;
import Model.Party;
import Model.Window;
import Model.Handler.AddMessageHandler;
import Model.Handler.AddPartyHandler;
import Model.Handler.CloseWindowHandler;
import Model.Handler.DeleteElementHandler;
import Model.Handler.EditLabelHandler;
import Model.Handler.Handler;
import Model.Handler.MovePartyHandler;
import Model.Handler.SelectElementHandler;
import Model.Handler.SetPartyTypeHandler;
import Model.Handler.SwitchViewHandler;

/**
 * A class that processes input related to the Interaction class.
 * 
 * @author Kevin Lavrijssen, Wout Mees, Florent Nander Meijer, Robbe Keters
 */
public class MyInteraction {
	
	/**
	 * Handles actions affecting a certain Screen and a certain canvas initiated by a mouse event.
	 * @param id			The kind of mouse event.
	 * @param x				The x coordinate of the mouse event.
	 * @param y				The y coordinate of the mouse event.
	 * @param window		The canvas to be handled and edited.
	 * @param interaction	The interaction to be handled and edited.
	 */
	public static void mouseClicked(Mouse id, int x, int y, Window window, Interaction interaction) {
		
		if(window.getMode()==Mode.ADDMESSAGE) {SelectElementHandler.deselectAll(window);}
		
		switch(id){
		
		case RELEASED:
			if(window.getMode()==Mode.ADDMESSAGE) {System.out.println("######## Handling Message ########");AddMessageHandler.handle(window, x, y);}
			if(window.getMode()==Mode.ADDMESSAGE || window.getMode()==Mode.MOVEPARTY) {SelectElementHandler.handle(window, x, y, Mouse.RELEASED);break;}
		case DRAGGED:
			if(window.getMode()==Mode.MOVEPARTY) {MovePartyHandler.handle(window, x, y);break;}		
		case PRESSED:
			SelectElementHandler.handle(window, x, y, Mouse.PRESSED);break;	
		
		case SINGLECLICK:
			SelectElementHandler.handle(window, x, y, Mouse.SINGLECLICK);break; 
			
		case DOUBLECLICK:
				if(!EditLabelHandler.editLabelModeMessage(window)) {
					if(Handler.getPartyAt(x, y, window)!=null){SetPartyTypeHandler.handle(window, x, y);break;}
					else{AddPartyHandler.handle(window, x, y);break;}
				}
		}
		
		CloseWindowHandler.handle(interaction.getSubWindows());
		
	}
	
	/**
	 * Handles actions affecting a certain canvas initiated by a mouse event.
	 * @param id		The kind of key event.
	 * @param keyCode	The code of the key pressed.
	 * @param keyChar	The key pressed.
	 * @param window	The canvas to be handled and edited.
	 */
	public static void keyPressed(int id, int keyCode, char keyChar, Window window) {
		
		if(id == KeyEvent.KEY_PRESSED && !EditLabelHandler.editLabelModeParty(window)) {
			switch(keyCode){
			case KeyEvent.VK_TAB:
				System.out.println("TAB");
				SwitchViewHandler.handle(window);
				break;
			case KeyEvent.VK_ENTER:
				System.out.println("ENTER");
				break;

			case KeyEvent.VK_DELETE:
				System.out.println("DELETE");
				DeleteElementHandler.handle(window);
			default:
				break;
			}
		} else if (id == KeyEvent.KEY_TYPED) {
			for(Party p : window.getParties()){
				if(p.getLabel().getSelected()) {
					if(window.getView() == Window.View.SEQUENCE)
						EditLabelHandler.handle(window, p.getLabel(), p, keyChar, p.getLabel().getLabelPositionSequence().getX(), p.getLabel().getLabelPositionSequence().getY());
					else 
						EditLabelHandler.handle(window, p.getLabel(), p, keyChar, p.getLabel().getLabelPositionComm().getX(), p.getLabel().getLabelPositionComm().getY());
					break;
				}
			}
			for(Message m : window.getMessages()){
				if(m.getLabel().getSelected()) {
					if(window.getView() == Window.View.SEQUENCE)
						EditLabelHandler.handle(window, m.getLabel(), m, keyChar, m.getLabel().getLabelPositionSequence().getX(), m.getLabel().getLabelPositionSequence().getY());
					else 
						EditLabelHandler.handle(window, m.getLabel(), m, keyChar, m.getLabel().getLabelPositionComm().getX(), m.getLabel().getLabelPositionComm().getY());
					break;
				}
			}
		}
	}
	
}
