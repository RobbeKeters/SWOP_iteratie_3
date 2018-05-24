package Model;

public class DialogBoxInvocationMessage extends DialogBox{

	InvocationMessage source;
	
	public DialogBoxInvocationMessage(int origineX, int origineY, int width, int height, InvocationMessage i) {
		super(origineX, origineY, width, height);
		
		int xI = origineX + width/2;
		int yI = origineY + height/2;
				
		source = i;
		
		Button add = new Button("add", xI+50, yI+50, Button.Type.TEXT);
		Button remove = new Button("X", xI+50, yI-75, Button.Type.TEXT);
		Button moveUp = new Button("/\\", xI+50, yI-50, Button.Type.TEXT);
		Button moveDown = new Button("\\/",xI+50, yI-25, Button.Type.TEXT);
		
		super.addButton(moveDown);
		super.addButton(moveUp);
		super.addButton(remove);
		super.addButton(add);
		
		int index = 0;
		
		Label method = new Label(i.getMethodName(), xI - 75, yI - 125);
		method.setWidth(150);
		super.addTextBox(method);
		
		for(String s : i.getArguments()){
			Label arg = new Label(s, xI - 75, yI - 75 + i.getLabel().getHeight()*index);
			arg.setWidth(150);
			super.addTextBox(arg);
			index++;
		}
		
		Label newArg = new Label("", xI - 75, yI - 25 + i.getLabel().getHeight()*index);
		newArg.setWidth(150);
		super.addTextBox(newArg);
		
//		String methodCall = i.getLabel().getLabelname();
		/*
		boolean methodName = true;
		int index = 0;
		boolean stop = false;
		for(int j=0; j<methodCall.length();j++) {
			if(stop) break;
			String var = "";
			for(int k=j; k<methodCall.length();k++) {
				if(methodName) {
					if(methodCall.charAt(k)=='(') {
						methodName=false;
						j=k;
						index++;
						Label l = new Label(var, xI - 75, yI - 125 + i.getLabel().getHeight()*index);
						l.setWidth(150);
						super.addTextBox(l);
						break;
					}
					else {
						var = var + methodCall.charAt(k);
						}
				}
				else {
					if(methodCall.charAt(k)==',') {
						j=k;
						index++;
						break;
					} else if (methodCall.charAt(k) == ')'){
						stop = true;
						index++;
						break;
					}
					else {
						var = var + methodCall.charAt(k);
					}
				}
			}
			Label l = new Label(var, xI - 75, yI - 75 + i.getLabel().getHeight()*index);
			l.setWidth(150);
			super.addTextBox(l);
		}
		Label newArg = new Label("", xI - 75, yI - 50 + i.getLabel().getHeight()*index);
		newArg.setWidth(150);
		super.addTextBox(newArg);
		*/
	}

}
