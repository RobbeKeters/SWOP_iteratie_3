package Model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Controller.Mouse;
import Model.Handler.EditLabelHandler;

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
		
		ListBox listbox = new ListBox(xI - 75 + i.getLabel().getHeight(), yI - 100, i.getArguments());
		listBox.setTitle("arguments: ");
		listBox.setWidth(150);
		
		
		for(String s : i.getArguments()){
			Label arg = new Label(s, xI - 75, yI - 75 + i.getLabel().getHeight()*index);
			arg.setWidth(150);
			listBox.addArgument(arg);
			index++;
		}
		
		super.addListBox(listBox);
		
		Label newArg;
//		if(index > 2)
			newArg = new Label("", xI - 75, yI - 50 + listBox.getHeight());
//		else
//			newArg = new Label("", xI - 75, yI - 50 + i.getLabel().getHeight()*3);
		newArg.setTitle("add arg.:");
		newArg.setWidth(150);
		super.addTextBox(newArg);
		
		Button add;
//		if(index > 2)
			add = new Button("+", xI+75, yI - 50 + listBox.getHeight(), Button.Type.TEXT);
//		else
//			add = new Button("+", xI+75, yI - 65 + i.getLabel().getHeight()*3, Button.Type.TEXT);

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
				if(c.returnType() == TypeControl.Button) {
					// deActivate the other 
					for ( Button b :this.getButtons()) {
						if ( c == b	) {
							source.adjustedThroughDialog =diaLogAdjusted.TYPEADJUSTED;
							b.setActivated(true);
							b.setSelectedControl(true);
						} else {
							b.setActivated(false);
							b.setSelectedControl(false);
						}
					}
				} else if (c.returnType() == TypeControl.Label){
					for(Control ctrl : getListControls()){
						if(!c.equals(ctrl)) 
							ctrl.setSelectedControl(false);
						else 
							c.setSelectedControl(true);
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
			}
		}
		
//		else if ( id == KeyEvent.KEY_TYPED && keyChar != KeyEvent.VK_TAB) {
//			Control c = this.getListControls().get(0);
////			Label label = source.getLabel();
//			// LET OP: IN Dialog wordt de label van een party opgedeeld in 2 Labels
////			Label label = searchForLabelName("instance: ");
////			Label classs = searchForLabelName("class: ");
//			
//			// Lelijke If statements .......
//			if( c.returnType() == TypeControl.Label && keyChar != KeyEvent.VK_BACK_SPACE ) {
//				
//				label.
//				
////				if ( instance == c ) {
////					instance.setLabelname(instance.getLabelname().replace("|", "") + keyChar );
////				} else {
////					classs.setLabelname(classs.getLabelname().replace("|", "") + keyChar );
////				}
//					
//			} else if ( c.returnType() == TypeControl.Label && keyChar == KeyEvent.VK_BACK_SPACE) {
////				if ( instance == c ) {
////					instance.setLabelname(instance.getLabelname().substring(0, instance.getLabelname().length()-1));
////				} else {
////					classs.setLabelname(classs.getLabelname().substring(0, classs.getLabelname().length()-1));
////				}
//			}
//			source.getLabel().setLabelname(classs.getLabelname()+":"+instance.getLabelname());
//			source.adjustedThroughDialog = diaLogAdjusted.LABELADJUSTED;
//			
//			int width = 8*instance.getLabelname().length();
//			if (width == 0)
//				width = 11;
//			instance.setWidth(width);
//			
//			width = 8*classs.getLabelname().length();
//			if (width == 0)
//				width = 11;
//			classs.setWidth(width);
//			
//			width = 8*source.getLabel().getLabelname().length();
//			if (width == 0)
//				width = 11;
//			source.getLabel().setWidth(width);
//			
//		}
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
