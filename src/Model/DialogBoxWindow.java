package Model;

public class DialogBoxWindow extends DialogBox{

	Window source;
	
	public DialogBoxWindow(int origineX, int origineY, int width, int height, Window s) {
		super(origineX, origineY, width, height);
		source = s;
		
		int xI = origineX + width/2 ;
		int yI = origineY + height/2 ;
		Button sequence = new Button("SEQUENCE: ", xI - 50, yI, Button.Type.RADIO);
		Button communication = new Button("COMMUNICATION: ",xI+100, yI, Button.Type.RADIO);
		
		// sequence is geselecteerd bij instantiatie
		sequence.setSelectedControl(true);
		
		if(s.getView().equals(Window.View.SEQUENCE)){
			sequence.setActivated(true);
			communication.setActivated(false);
		} else {
			sequence.setActivated(false);
			communication.setActivated(true);
		}
		
		super.addButton(sequence);
		super.addButton(communication);
		
		// Add all buttons en textboxes to one list 
		this.getListControls().addAll(this.getButtons());
		this.getListControls().addAll(this.getTextBoxes());
	}
	
	

}
