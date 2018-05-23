package Model;

public class DialogBoxInvocationMessage extends DialogBox{

	InvocationMessage source;
	
	public DialogBoxInvocationMessage(int width, int height, int origineX, int origineY, InvocationMessage i) {
		super(width, height, origineX, origineY);
		source = i;
		
		Button add = new Button(origineX+50, origineY+50, Button.Type.TEXT);
		Button remove = new Button(origineX+50, origineY+74, Button.Type.TEXT);
		Button moveUp = new Button(origineX+74, origineY+74, Button.Type.TEXT);
		Button moveDown = new Button(origineX+74, origineY+50, Button.Type.TEXT);
		
		super.addButton(moveDown);
		super.addButton(moveUp);
		super.addButton(remove);
		super.addButton(add);
		
		String methodCall = i.getLabel().getLabelname();
		
		boolean methodName = true;
		int index = 1;
		for(int j=0; j<methodCall.length();j++) {
			String var = "";
			for(int k=j; k<methodCall.length();k++) {
				if(methodName) {
					if(methodCall.charAt(k)=='(') {methodName=false;j=k+1;index++;break;}
					else {var = var + methodCall.charAt(k);}
				}
				else {
					if(methodCall.charAt(k)==',') {j=k+1;index++;break;}
					else {var = var + methodCall.charAt(k);}
				}
			}
			super.addTextBox(new Label(var, 50, 64+14*index));
		}
		
	}

}
