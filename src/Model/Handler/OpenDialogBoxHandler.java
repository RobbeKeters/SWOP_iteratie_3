package Model.Handler;

import Model.DialogBoxInvocationMessage;
import Model.DialogBoxParty;
import Model.DialogBoxResultMessage;
import Model.DialogBoxWindow;
import Model.InvocationMessage;
import Model.Party;
import Model.ResultMessage;
import Model.Screen;
import Model.Window;

/**
 * A handler that handles the actions of a dialog box being opened.
 */
public class OpenDialogBoxHandler extends Handler{
	
	private static final int xOrigin = 50;
	private static final int yOrigin = 50;
	private static final int height = 300;
	private static final int width = 300;
	
	/**
	 * Handles a party dialog box being opened.
	 * @param p		The party to which the dialog box belongs to.
	 * @param s		The screen to edit.
	 */
	public static void handle(Party p, Screen s) {
		DialogBoxParty db = new DialogBoxParty(xOrigin,yOrigin,height,width,p); 
		s.getSubWindows().add(db);
	}
	
	/**
	 * Handles an invocation message dialog box being opened.
	 * @param i		The invocation message to which the dialog box belongs to.
	 * @param s		The screen to edit.
	 */
	public static void handle(InvocationMessage i, Screen s) {
		DialogBoxInvocationMessage db = new DialogBoxInvocationMessage(xOrigin,yOrigin,height,width,i);
		s.getSubWindows().add(db);
	}
	
	/**
	 * Handles a result message dialog box being opened.
	 * @param i		The result message to which the dialog box belongs to.
	 * @param s		The screen to edit.
	 */
	public static void handle(ResultMessage r, Screen s) {
		DialogBoxResultMessage db = new DialogBoxResultMessage(xOrigin,yOrigin,height,width,r);
		s.getSubWindows().add(db);
	}
	
	/**
	 * Handles an window dialog box being opened.
	 * @param i		The window to which the dialog box belongs to.
	 * @param s		The screen to edit.
	 */
	public static void handle(Window w, Screen s) {
		DialogBoxWindow db = new DialogBoxWindow(xOrigin,yOrigin,height,width,w);
		s.getSubWindows().add(db);
	}
	
}
