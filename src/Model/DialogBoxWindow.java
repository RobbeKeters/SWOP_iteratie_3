package Model;

public class DialogBoxWindow extends DialogBox{

	Window source;
	
	public DialogBoxWindow(int origineX, int origineY, int width, int height, Window s) {
		super(origineX, origineY, width, height);
		source = s;
		
		int xI = origineX + 50 ;
		int yI = origineY + 50 ;
		Button sequence = new Button(xI, yI, Button.Type.RADIO);
		Button communication = new Button(xI+24, yI, Button.Type.RADIO);
		super.addButton(sequence);
		super.addButton(communication);
	}
	
	

}
