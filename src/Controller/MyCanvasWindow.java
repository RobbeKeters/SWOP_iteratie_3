package Controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;


import Controller.Mouse;
import Model.Canvas;
import Model.DialogBox;
import Model.DialogBoxInvocationMessage;
import Model.DialogBoxParty;
import Model.DialogBoxResultMessage;
import Model.DialogBoxWindow;
import Model.Screen;
import Model.Window;
import Model.Handler.EditLabelHandler;
import View.CommunicationDiagram;
import View.CommunicationState;
import View.SequenceDiagram;
import View.SequenceState;
import View.View;
import View.ViewContext;
import View.ViewState;
import View.DialogBoxView;
import View.SelectedElement;

/**
 * A window for custom drawing.
 * This class is an extension of the class CanvasWindow.
 * 
 * @author Kevin Lavrijssen, Wout Mees, Florent Nander Meijer, Robbe Keters
 * @version 2.0
 *
 */
public class MyCanvasWindow extends CanvasWindow{
	
	private Screen screen = new Screen(); //Screen.getInstance();
	private MyScreen myScreen = new MyScreen(); //MyScreen.getInstance();
	
	/**
	 * The constructor for MyCanvasWindow.
	 * 
	 * @param title 		The title for this window.
	 */
	public MyCanvasWindow(String title) {
		super(title);
	}
	
	/**
	 * Paints the current window using the canvas data.
	 * 
	 * @param g 		The Graphics object used for painting the window.
	 */
	@Override
	protected void paint(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 600, 600);
		
		Color textColor = Color.BLACK;
		Font font = new Font("Font", 1, 12);
		
		g.setColor(textColor);
		g.setFont(font);
				
		for(Canvas c : screen.getSubWindows()) {
			if(c.getClass()==Window.class) {
				Window w = (Window)c;
				ViewContext viewContext = new ViewContext();
				ViewState v;
				
				if(w.getView() == Window.View.SEQUENCE)
					v = new SequenceState();
				else 
					v = new CommunicationState();
					viewContext.setState(new CommunicationState());
				v.draw(viewContext, w, g);
			}
			//TODO
			else if(c.getClass().equals(DialogBoxWindow.class)) {
				DialogBoxView box = new DialogBoxView();
				box.draw(SelectedElement.VIEW, (DialogBox) c, g);
			}
			else if (c.getClass().equals(DialogBoxParty.class)) {
				DialogBoxView box = new DialogBoxView();
				box.draw(SelectedElement.PARTY, (DialogBox) c, g);
			}
			else if (c.getClass().equals(DialogBoxInvocationMessage.class)) {
				DialogBoxView box = new DialogBoxView();
				box.draw(SelectedElement.INVOCATION_MESSAGE, (DialogBox) c, g);
			}
			else if (c.getClass().equals(DialogBoxResultMessage.class)) {
				DialogBoxView box = new DialogBoxView();
				box.draw(SelectedElement.RESULT_MESSAGE, (DialogBox) c, g);
			}
		}
	}
	
	/**
	 * Handles a recorded mouse event.
	 * 
	 * @param id 			The kind of mouse event to be handled.
	 * @param x 			The x coordinate clicked.
	 * @param y 			The y coordinate clicked.
	 * @param clickCount 	The amount of times clicked.
	 */
	@Override
	protected void handleMouseEvent(int id, int x, int y, int clickCount){
		// 
		if( !screen.getInteractions().isEmpty()) {
			// TODO Handle DialogBox
			Canvas currentCanvas = screen.getSubWindows().lastElement();
			
			System.out.println("######## "+currentCanvas.getMode()+" ########");
			
			if(screen.getSubWindows().lastElement().getClass() == Model.Window.class && EditLabelHandler.editLabelModeParty((Window)currentCanvas)) {
				// Do nothing! -> Still in EditMode
			}	
			else {	
				switch(id) {
					
				case MouseEvent.MOUSE_CLICKED: 
					if(clickCount == 1) {myScreen.mouseClicked(Mouse.SINGLECLICK, x, y, screen);}
					if(clickCount == 2) {myScreen.mouseClicked(Mouse.DOUBLECLICK, x, y, screen);}
					break;
				case MouseEvent.MOUSE_PRESSED:
					myScreen.mouseClicked(Mouse.PRESSED, x, y, screen);
					break;
				case MouseEvent.MOUSE_DRAGGED:
					myScreen.mouseClicked(Mouse.DRAGGED, x, y, screen);
					break;
				case MouseEvent.MOUSE_RELEASED:
					myScreen.mouseClicked(Mouse.RELEASED, x, y, screen);
					break;
				}
			}
		}
		repaint();
	
	}
	
	/**
	 * Handles an event where a key on the keyboard is pressed.
	 * 
	 * @param id 		The kind of key event.
	 * @param keyCode		The code of the key pressed.
	 * @param keyChar		The key pressed.
	 */
	@Override
	protected void handleKeyEvent(int id, int keyCode, char keyChar){
		myScreen.keyPressed(id, keyCode, keyChar, screen);
		repaint();
	}
	
	/**
	 * The main method.
	 * 
	 * @param args		Unused input.
	 */
	
	public static void main(String[] args) {
		
		MyCanvasWindow myCanvas = new MyCanvasWindow("My Canvas Window");
		
//		2 -- Create a recording
//	    String pathPrefix =
//		 "C:\\Users\\robbe\\git\\SWOP_iteratie_3\\src\\tests\\recordings\\";
//		 String newFile = "moveParty/moveMultiplePartyCom";
//	     String filePath = pathPrefix + newFile;
//		 myCanvas.recordSession(filePath);
		
//		1 -- Run the interactrr
	     java.awt.EventQueue.invokeLater(() -> {
	        myCanvas.show();
		});
	 }
	
}
