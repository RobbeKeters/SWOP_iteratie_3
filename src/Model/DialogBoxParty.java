package Model;

public class DialogBoxParty extends DialogBox{

	Party source; 
	
	public DialogBoxParty(int origineX, int origineY, int width, int height,  Party p) {
		super(origineX, origineY, width, height);
		
		source = p;

		int xI = origineX + width/2 ;
		int yI = origineY + height/2 ;
		Button actor = new Button("actor: ",xI - 50, yI + 25, Button.Type.RADIO);
		Button object = new Button("object: ",xI + 50, yI + 25, Button.Type.RADIO);
		
		if(p.getClass().equals(Actor.class)){
			actor.setActivated(true);
			object.setActivated(false);
		} else {
			actor.setActivated(false);
			object.setActivated(true);
		}
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
		
		Label instanceName = new Label(_instance,xI, yI-25 );
		Label className = new Label(_class,xI, yI);
		
		instanceName.setTitle("instance: ");
		className.setTitle("class: ");
		super.addTextBox(className);
		super.addTextBox(instanceName);
		
	}

}

