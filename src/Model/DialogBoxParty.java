package Model;

public class DialogBoxParty extends DialogBox{

	Party source; 
	
	public DialogBoxParty(int width, int height, int origineX, int origineY, Party p) {
		super(width, height, origineX, origineY);
		
		source = p;
		
		int xI = origineX + 50 ;
		int yI = origineY + 50 ;
		Button actor = new Button(xI, yI);
		Button object = new Button(xI, yI+24);
		super.addButton(actor);
		super.addButton(object);
		
		String name = p.getLabel().getLabelname();
		String _class = "";
		String _instance = "";
		
		boolean colon = false;
		for(int i = 0; i<name.length(); i++) {
			if(name.charAt(i)==':') {colon = true;}
			else if(!colon) {_instance=_instance+name.charAt(i);}
			else {_class = _class+name.charAt(i);}
		}
		
		Label instanceName = new Label(_instance,xI, yI+24 );
		Label className = new Label(_class,xI+24, yI+24);
		super.addTextBox(className);
		super.addTextBox(instanceName);
		
	}

}

