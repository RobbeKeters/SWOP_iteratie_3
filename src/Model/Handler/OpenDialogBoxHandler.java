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

public class OpenDialogBoxHandler extends Handler{
	
	private static final int xOrigin = 50;
	private static final int yOrigin = 50;
	private static final int height = 300;
	private static final int width = 300;

	public static void handle(Party p, Screen s) {
		DialogBoxParty db = new DialogBoxParty(xOrigin,yOrigin,height,width,p); 
		s.getSubWindows().add(db);
	}
	
	public static void handle(InvocationMessage i, Screen s) {
		DialogBoxInvocationMessage db = new DialogBoxInvocationMessage(xOrigin,yOrigin,height,width,i);
		s.getSubWindows().add(db);
	}
	
	public static void handle(ResultMessage r, Screen s) {
		DialogBoxResultMessage db = new DialogBoxResultMessage(xOrigin,yOrigin,height,width,r);
		s.getSubWindows().add(db);
	}
	
	public static void handle(Window w, Screen s) {
		DialogBoxWindow db = new DialogBoxWindow(xOrigin,yOrigin,height,width,w);
		s.getSubWindows().add(db);
	}
	
}
