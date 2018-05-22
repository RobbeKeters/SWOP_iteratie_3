package Model.Handler;

import Model.DialogBox;
import Model.Screen;

public class CloseDialogBoxHandler extends Handler{

	public static void handle(DialogBox db, Screen s) {
		s.getSubWindows().remove(db);
	}
	
}
