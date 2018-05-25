package Controller;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Stack;

import Model.ADJUSTED_TYPE;
import Model.Canvas;
import Model.DialogBox;
import Model.DialogBoxParty;
import Model.DialogBoxResultMessage;
import Model.DialogBoxWindow;
import Model.Interaction;
import Model.InvocationMessage;
import Model.diaLogAdjusted;
import Model.Message;
import Model.Party;
import Model.ResultMessage;
import Model.Screen;
import Model.Window;
import Model.Handler.AddInteractionHandler;
import Model.Handler.AddWindowHandler;
import Model.Handler.CloseWindowHandler;
import Model.Handler.OpenDialogBoxHandler;
import Model.Handler.SelectElementHandlerDialogBox;
import Model.Handler.SetPartyTypeHandler;

/**
 * A class that processes input related to the Screen class.
 * 
 * @author Kevin Lavrijssen, Wout Mees, Florent Nander Meijer, Robbe Keters
 */
public class MyScreen {

	/////////////////// Singleton ///////////////////
	/*private static MyScreen instance = null;
	
	private MyScreen() {}
	
	private synchronized static void createInstance() {
	if(instance == null) {instance = new MyScreen();}
	}
	
	public static MyScreen getInstance() {
	if(instance==null) {createInstance();}
	return instance;
	}*/
	
	////////////////////////////////////////////////
	
	private boolean ctrlPressed =  false;
	
	/**
	 * Handles actions affecting a certain Screen initiated by a mouse event.
	 * @param id		The kind of mouse event.
	 * @param x			The x coordinate of the mouse event.
	 * @param y			The y coordinate of the mouse event.
	 * @param screen	The screen to be handled and edited.
	 */
	public void mouseClicked(Mouse id, int x, int y, Screen screen) {
			
		ctrlPressed = false;
		
// 		if (!screen.getInteractions().isEmpty() && (screen.getSubWindows().lastElement().getClass() == Model.DialogBox.class  ||!EditLabelHandler.editLabelModeParty((Window)screen.getSubWindows().lastElement()))) {
		
		if (!screen.getInteractions().isEmpty()) {
			// Determine Canvas 
			Canvas canvas = null;
					
			Stack<Canvas> findList = new Stack<Canvas>(); 
			findList.addAll(screen.getSubWindows());
			boolean found = false;
			Canvas top = findList.lastElement();
			while(!findList.isEmpty() && !found) {
				canvas = findList.pop();
				if( isInArea(x, y, canvas)) {
					found = true;
				}
			}
			// Selected SubWindow must be placed on top of the stack of subWindows
			screen.getSubWindows().remove(canvas);
			screen.getSubWindows().push(canvas);
			
			// Check if the selected canvas was on top of the stack(active canvas)
			if( top == canvas) {
				// Delegate to Interaction ( if top of the stack is a window!)
				if( screen.getSubWindows().lastElement().getClass() == Model.Window.class) {
					MyInteraction.mouseClicked(id, x, y, (Window) canvas,((Window) canvas).getInteraction() );
				} else { 
					// Top is DialogBox
					SelectElementHandlerDialogBox.handle((DialogBox) canvas, x, y, id);
				}
			}
		}
		
		CloseWindowHandler.handle(screen.getSubWindows());
		
		// Find any Empty Interaction(empty interaction =  window left) ==> delete empty Interaction
		ArrayList<Interaction> toBeDeletedInteraction = new ArrayList<Interaction>();
		for( Interaction i :screen.getInteractions()) {
			if(i.getSubWindows().isEmpty()) {
				toBeDeletedInteraction.add(i);
			}
		}
		for (Interaction i : toBeDeletedInteraction) {
			screen.getInteractions().remove(i);
		}
		
		this.updateDiaLogs(screen);
		this.checkDialogForUpdatedParties(screen);

	}
	
	/**
	 * Handles actions affecting a certain Screen initiated by a key event.
	 * @param id		The kind of key event.
	 * @param keyCode	The code of the key pressed.
	 * @param keyChar	The key pressed.
	 * @param screen	The screen to be handled and edited.
	 */
	public void keyPressed(int id, int keyCode, char keyChar, Screen screen) {
		
		// ctrl + n
		if( ctrlPressed && keyCode == 78 && (id == KeyEvent.KEY_PRESSED || id == KeyEvent.KEY_TYPED)) {
			AddInteractionHandler.handle(screen.getInteractions(), screen.getSubWindows());
		
		}
		// ctrl + d
		else if ( !screen.getInteractions().isEmpty() && ctrlPressed && keyCode == 68 && (id == KeyEvent.KEY_PRESSED || id == KeyEvent.KEY_TYPED) ) {
			AddWindowHandler.handle(screen.getSubWindows());
		} 
		// ctrl + ENTER
		else if (ctrlPressed && keyCode == 10 && (id == KeyEvent.KEY_PRESSED || id == KeyEvent.KEY_TYPED)){
			Window c = (Window) screen.getSubWindows().lastElement();
			//TODO: voorlopig staat alles hier in, maar ik denk niet dat dat zo correct is...
			boolean found = false;
			if(!c.getParties().isEmpty()){
				for(Party p : c.getParties()){
					if(p.getSelected() || p.getLabel().getSelected()){
						found = true;
						OpenDialogBoxHandler.handle(p, screen);
						break;
					}
				}
			}
			if (!c.getMessages().isEmpty()) {
				for(Message m : c.getMessages()){
					if(m.getSelected() || m.getLabel().getSelected()){
						found = true;
						if(m.getClass().equals(InvocationMessage.class)){
							OpenDialogBoxHandler.handle((InvocationMessage) m, screen);
							break;
						} else {
							OpenDialogBoxHandler.handle((ResultMessage) m, screen);
							break;
						}
					}
				}
			} 
			if(!found)
				OpenDialogBoxHandler.handle(c, screen);
		}
		else {
			ctrlPressed = false;
		}		
		if( id == KeyEvent.KEY_PRESSED && keyCode == 17) {
			ctrlPressed = true;
		} else if(!screen.getInteractions().isEmpty() && (id == KeyEvent.KEY_PRESSED || id == KeyEvent.KEY_TYPED)) {
			if( screen.getSubWindows().lastElement().getClass() == Model.Window.class) { // Alleen naar interaction als het een window is
				MyInteraction.keyPressed(id, keyCode, keyChar, (Window)screen.getSubWindows().lastElement());
			} else {
				SelectElementHandlerDialogBox.handleKey(id, keyCode, keyChar, (DialogBox)screen.getSubWindows().lastElement());
			}
		}
		this.updateDiaLogs(screen);
		this.checkDialogForUpdatedParties(screen);
	}
	
	/**
	 * Returns if the given coordinate is situated in the area of the window of the given Canvas.
	 * @param x				The given x coordinate.
	 * @param y				The given y coordinate.
	 * @param lastElement	The given canvas.
	 */
	private static boolean isInArea(int x, int y, Canvas lastElement) {
		int xLow = lastElement.getOrigineX() - 4 ;
		int yLow = lastElement.getOrigineY() - 4;
		// "+4" is for resize -> canvas.resize methods
		int xHigh = xLow + lastElement.getWidth() + 8 ;
		int yHigh = yLow + lastElement.getHeight() + 8;
			
		if( x >= xLow && x <= xHigh && y >= yLow && y <= yHigh) {
			return true;
		}
		return false;
	}
	public void checkDialogForUpdatedParties(Screen screen) {
		// Observer Parties ( check if a party label is updated with a diaLog)
				Window updatedWindow = null;
				Interaction updatedInteraction =null;
				diaLogAdjusted type = null;
				Party adjustedParty = null;
				for (Interaction i : screen.getInteractions() ) {
					for ( Window w: i.getSubWindows()) {
						for ( Party p: w.getParties()) {
							if ( p.adjustedThroughDialog == diaLogAdjusted.LABELADJUSTED) {
								p.adjustedThroughDialog = diaLogAdjusted.NOTADJUSTED;
								adjustedParty = p;
								type = diaLogAdjusted.LABELADJUSTED;
								break;
							}
							if ( p.adjustedThroughDialog == diaLogAdjusted.TYPEADJUSTED ) {
								adjustedParty = p;
								p.adjustedThroughDialog = diaLogAdjusted.NOTADJUSTED;
								type = diaLogAdjusted.TYPEADJUSTED;
								break;
							}
						}
						updatedInteraction = i;
						updatedWindow = w;
					}
				}
				if ( adjustedParty != null) {
					if( type.equals(diaLogAdjusted.LABELADJUSTED)) {
						updatedInteraction.adjusted(ADJUSTED_TYPE.PARTY_LABEL, updatedWindow);
					} else if ( type.equals(diaLogAdjusted.TYPEADJUSTED)) {
						if( updatedWindow.getView() == Window.View.SEQUENCE){
							SetPartyTypeHandler.handle(updatedWindow, adjustedParty.getPosSeq().getX(), adjustedParty.getPosSeq().getY() +adjustedParty.getHeight() + 10);
						} else {
							SetPartyTypeHandler.handle(updatedWindow, adjustedParty.getPosComm().getX(), adjustedParty.getPosComm().getY());
						}
					}
				}
	}
	public void updateDiaLogs(Screen screen) {
		Interaction interaction = null;
		for ( Interaction i : screen.getInteractions()) {
			if( i.adjustedDialog != diaLogAdjusted.NOTADJUSTED) {
				interaction = i;
				break;
			}
		}
		DialogBox canvasToUpdated = null;
		if ( interaction  != null) {
			for( Canvas c : screen.getSubWindows()) {
				if ( c.getClass().equals(Model.DialogBoxParty.class)) {
					DialogBoxParty db = (DialogBoxParty) c;
					if( db.source == interaction.oldParty) {
						canvasToUpdated = (DialogBox) c;
						break;
					} 
				} else if (c.getClass().equals(Model.DialogBoxWindow.class)){
					DialogBoxWindow db = (DialogBoxWindow)c;
					db.updateButtons(); //
				} else if (c.getClass().equals(Model.DialogBoxResultMessage.class)) {
					DialogBoxResultMessage db = (DialogBoxResultMessage)c;
				
				}
			}
			int indexCanvasInStack;
			if ( canvasToUpdated != null && (interaction.adjustedDialog == diaLogAdjusted.LABELADJUSTED || interaction.adjustedDialog == diaLogAdjusted.TYPEADJUSTED)) {
				indexCanvasInStack = screen.getSubWindows().indexOf(canvasToUpdated);
				screen.getSubWindows().remove(canvasToUpdated);
				screen.getSubWindows().add(indexCanvasInStack, new DialogBoxParty(canvasToUpdated.getOrigineX(), canvasToUpdated.getOrigineY(), canvasToUpdated.getWidth(), canvasToUpdated.getHeight(), interaction.newParty));
			} 
			interaction.adjustedDialog = diaLogAdjusted.NOTADJUSTED;
		}
	}
}
























