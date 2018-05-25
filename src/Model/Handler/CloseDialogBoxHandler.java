package Model.Handler;

import Model.DialogBox;
import Model.Screen;

/**
 * A handler that handles the actions of a dialog box closing.
 */
public class CloseDialogBoxHandler extends Handler{
	
	/**
	 * Handles a dialog box closing.
	 * @param db		The dialog box to close.
	 * @param s			The screen to edit.
	 */
	public static void handle(DialogBox db, Screen s) {
		s.getSubWindows().remove(db);
	}
	
}
