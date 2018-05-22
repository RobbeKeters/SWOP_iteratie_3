package Model;

public class DialogBoxResultMessage extends DialogBox{

	ResultMessage source;
	
	public DialogBoxResultMessage(int width, int height, int origineX, int origineY, ResultMessage r) {
		super(width, height, origineX, origineY);
	
		source = r;
		
		int xI = origineX + 50 ;
		int yI = origineY + 50 ;
		
		Label result = new Label(r.getLabel().getLabelname(),xI, yI);
		super.addTextBox(result);
		
	}

}