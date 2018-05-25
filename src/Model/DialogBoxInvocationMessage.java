package Model;

import Controller.Mouse;

public class DialogBoxInvocationMessage extends DialogBox{

	InvocationMessage source;
	
	public DialogBoxInvocationMessage(int origineX, int origineY, int width, int height, InvocationMessage i) {
		super(origineX, origineY, width, height);
		
		int xI = origineX + width/2;
		int yI = origineY + height/2;
				
		source = i;
		
		int index = 0;
		
		Label method = new Label(i.getMethodName(), xI - 75, yI - 100);
		method.setTitle("method:");
		method.setWidth(150);
		super.addTextBox(method);
		
		for(String s : i.getArguments()){
			Label arg = new Label(s, xI - 75, yI - 75 + i.getLabel().getHeight()*index);
			arg.setWidth(150);
			super.addTextBox(arg);
			index++;
		}
		Label newArg;
		if(index > 2)
			newArg = new Label("", xI - 75, yI - 50 + i.getLabel().getHeight()*index);
		else
			newArg = new Label("", xI - 75, yI - 50 + i.getLabel().getHeight()*3);
		newArg.setTitle("add arg.:");
		newArg.setWidth(150);
		super.addTextBox(newArg);
		
		Button add;
		if(index > 2)
			add = new Button("+", xI+75, yI - 50 + i.getLabel().getHeight()*index, Button.Type.TEXT);
		else
			add = new Button("+", xI+75, yI - 65 + i.getLabel().getHeight()*3, Button.Type.TEXT);
		Button remove = new Button("X", xI+75, yI - 100, Button.Type.TEXT);
		Button moveUp = new Button("/\\", xI+75, yI- 100 + i.getLabel().getHeight(), Button.Type.TEXT);
		Button moveDown = new Button("\\/",xI+75, yI - 100 + i.getLabel().getHeight()*2, Button.Type.TEXT);
		
		// add is geselecteerd bij instantiatie 
		add.setSelectedControl(true);
		
		super.addButton(moveDown);
		super.addButton(moveUp);
		super.addButton(remove);
		super.addButton(add);
		
//		
		// Add all buttons en textboxes to one list 
		this.getListControls().addAll(this.getButtons());
		this.getListControls().addAll(this.getTextBoxes());
	}

	@Override
	public void handleMouse(Mouse id, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleKey(int id, int keyCode, char keyChar) {
		// TODO Auto-generated method stub
		
	}

}
