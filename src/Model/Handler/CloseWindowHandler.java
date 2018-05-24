package Model.Handler;

import java.util.ArrayList;
import java.util.Stack;

import Model.Canvas;
import Model.Mode;
import Model.Window;

/**
 * A handler that handles the closing of a window.
 */
public class CloseWindowHandler extends Handler{

	/**
	 * Closes all subwindows that need to be closed from the given list of subwindows.
	 * 
	 * @param subWindows	The given list of subwindows.
	 */
	public static void handle(Stack<Canvas> subWindows) {
		// Find any canvas objects that need to be closed/deleted!
		ArrayList<Canvas> toBeDeleted = new ArrayList<Canvas>();
		for( Canvas canvas :subWindows) {
			if(canvas.getMode()  == Mode.CLOSING) {
				toBeDeleted.add(canvas);
			}
		}
		for (Canvas w : toBeDeleted) {
			subWindows.remove(w);
		}
	}
}
