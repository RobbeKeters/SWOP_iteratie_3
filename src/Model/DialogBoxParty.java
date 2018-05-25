package Model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Controller.Mouse;
import Model.Handler.EditLabelHandler;

public class DialogBoxParty extends DialogBox{

	public Party source; 
	
	public DialogBoxParty(int origineX, int origineY, int width, int height,  Party p) {
		super(origineX, origineY, width, height);
		
		source = p;

		int xI = origineX + width/2 ;
		int yI = origineY + height/2 ;
		Button actor = new Button("actor: ",xI - 50, yI + 25, Button.Type.RADIO);
		Button object = new Button("object: ",xI + 50, yI + 25, Button.Type.RADIO);
		
		// actor is geselecteerd bij instantiatie
		actor.setSelectedControl(true);
		
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
		
		// Add all buttons en textboxes to one list 
		this.getListControls().addAll(this.getButtons());
		this.getListControls().addAll(this.getTextBoxes());
		System.out.println(this.getListControls().size() + " size ======== ");
	}

	@Override
	public void handleMouse(Mouse id, int x, int y) {
		this.getListControls().get(0).setSelectedControl(false);
		if ( id == Mouse.SINGLECLICK) {
			Control c = null;
			for ( Control d :this.getListControls()) {
				if ( d.inArea(x, y)) {
					c = d; break;
				}
			}
			if ( c != null) {
				this.getListControls().remove(c);
				this.getListControls().add(0, c);
				if( c.returnType() == TypeControl.Button) {
					// deActivate the other 
					for ( Button b :this.getButtons()) {
						if ( c == b	) {
							source.adjustedThroughDialog =diaLogAdjusted.TYPEADJUSTED;
							b.setActivated(true);
						} else {
							b.setActivated(false);
						}
					}
				}
			}
		}
		this.getListControls().get(0).setSelectedControl(true);
	}

	@Override
	public void handleKey(int id, int keyCode, char keyChar) {
		Control top = this.getListControls().get(0);
		top.setSelectedControl(false);

		if(id  == KeyEvent.KEY_PRESSED && keyCode == KeyEvent.VK_TAB ) {
			// Switch to next Control (Button or textBox)
			ArrayList<Control> listControls = this.getListControls();
			Control activeControl = listControls.get(0);
			if( listControls.size() > 1) {
				listControls.remove(activeControl);
				Control nextControl = listControls.get(0);
				listControls.add(listControls.size(), activeControl);
			}
		}
		else if(id == KeyEvent.KEY_PRESSED && keyCode == KeyEvent.VK_SPACE ) {
			Control c =this.getListControls().get(0);
			if ( c.returnType() == TypeControl.Button	) {
				// deActivate the other 
				for ( Button b :this.getButtons()) {
					if ( c == b	) {
						source.adjustedThroughDialog =diaLogAdjusted.TYPEADJUSTED;
						b.setActivated(true);
					} else {
						b.setActivated(false);
					}
				}
			} else if (c.returnType() == TypeControl.Label) {
				// EDIT LABEL van Party
			}
		}
		
		else if ( id == KeyEvent.KEY_TYPED && keyChar != KeyEvent.VK_TAB) {
			Control c = this.getListControls().get(0);
			Label label = source.getLabel();
			// LET OP: IN Dialog wordt de label van een party opgedeeld in 2 Labels
			Label instance = searchForLabelName("instance: ");
			Label classs = searchForLabelName("class: ");
			
			// Lelijke If statements .......
			if( c.returnType() == TypeControl.Label && keyChar != KeyEvent.VK_BACK_SPACE ) {
				if ( instance == c ) {
					instance.setLabelname(instance.getLabelname().replace("|", "") + keyChar );
				} else {
					classs.setLabelname(classs.getLabelname().replace("|", "") + keyChar );
				}
					
			} else if ( c.returnType() == TypeControl.Label && keyChar == KeyEvent.VK_BACK_SPACE) {
				if ( instance == c ) {
					instance.setLabelname(instance.getLabelname().substring(0, instance.getLabelname().length()-1));
				} else {
					classs.setLabelname(classs.getLabelname().substring(0, classs.getLabelname().length()-1));
				}
			}
			source.getLabel().setLabelname(classs.getLabelname()+":"+instance.getLabelname());
			source.adjustedThroughDialog = diaLogAdjusted.LABELADJUSTED;
			
			int width = 8*instance.getLabelname().length();
			if (width == 0)
				width = 11;
			instance.setWidth(width);
			
			width = 8*classs.getLabelname().length();
			if (width == 0)
				width = 11;
			classs.setWidth(width);
			
			width = 8*source.getLabel().getLabelname().length();
			if (width == 0)
				width = 11;
			source.getLabel().setWidth(width);
			
		}
		this.getListControls().get(0).setSelectedControl(true);
	}
	private Label searchForLabelName(String toSearchFor) {
		for( Label l: this.getTextBoxes()) {
			if( l.getTitle().equals( toSearchFor )) {
				return l; 
			}
		}
		return null;
	}
	
	
}

