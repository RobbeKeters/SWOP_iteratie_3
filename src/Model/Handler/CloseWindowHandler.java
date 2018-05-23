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
	public static void handle(ArrayList<Window> subWindows) {
		// Find any canvas objects that need to be closed/deleted!
		ArrayList<Window> toBeDeleted = new ArrayList<Window>();
		for( Window w :subWindows) {
			if(w.getMode()  == Mode.CLOSING) {
				toBeDeleted.add(w);
			}
		}
		for (Window w : toBeDeleted) {
			subWindows.remove(w);
		}
	}

	/**
	 * Closes all subwindows that need to be closed from the given stack of subwindows.
	 * 
	 * @param subWindows	The given stack of subwindows.
	 */
	public static void handle(Stack<Canvas> subWindows) {
		// Find any canvas objects that need to be closed/deleted!
		ArrayList<Window> toBeDeleted = new ArrayList<Window>();
		for( Canvas c :subWindows) {
			if(c.getClass()==Window.class) {
				Window w = (Window)c;
				if(w.getMode()  == Mode.CLOSING) {
					toBeDeleted.add(w);
				}
			}
		}
		for (Window w : toBeDeleted) {
			subWindows.remove(w);
		}
	}

}
