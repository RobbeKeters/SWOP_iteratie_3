package Model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Controller.Mouse;
import Model.Handler.EditLabelHandler;

public class DialogBoxInvocationMessage extends DialogBox{

	public InvocationMessage source;
	private Label listArgument;
	
	/**
	 * Constructor.
	 * @param origineX		The x coordinate of origin.
	 * @param origineY		The y coordinate of origin.
	 * @param width			The width.
	 * @param height		The height.
	 * @param i				The linked invocation message.
	 */
	public DialogBoxInvocationMessage(int origineX, int origineY, int width, int height, InvocationMessage i) {
		super(origineX, origineY, width, height);
		
		int xI = origineX + width/2;
		int yI = origineY + height/2;
				
		source = i;
		
		Label method = new Label(i.getMethodName(), xI - 75, yI - 100);
		method.setTitle("method:");
		method.setWidth(150);
		super.addTextBox(method);
		
		ArrayList<Label> arguments = new ArrayList<Label>();
		ListBox listBox = new ListBox(xI - 75, yI - 75, arguments);
//		listBox.setTitle("arguments: ");
		listBox.setWidth(150);
		
		int index = 0;
		for(String s : i.getArguments()){
			Label arg = new Label(s, xI - 75, yI - 75 + i.getLabel().getHeight()*index);
			arg.setWidth(150);
			listBox.addArgument(arg);
			index++;
		}
		
		super.addListBox(listBox);
		
		Label newArg = new Label("", xI - 75, yI - 50 + listBox.getHeight());
		newArg.setTitle("add arg.:");
		newArg.setWidth(150);
		super.addTextBox(newArg);
		
		Button add = new Button("+", xI+75, yI - 50 + listBox.getHeight(), Button.Type.TEXT);

		Button remove = new Button("X", xI+75, yI - 100, Button.Type.TEXT);
		Button moveUp = new Button("/\\", xI+75, yI- 100 + 30, Button.Type.TEXT);
		Button moveDown = new Button("\\/",xI+75, yI - 100 + 30*2, Button.Type.TEXT);
		
		if(!listBox.getSelected()){
			remove.setDisabled(false);
			moveUp.setDisabled(false);
			moveDown.setDisabled(false);
		} else {
			remove.setDisabled(true);
			moveUp.setDisabled(true);
			moveDown.setDisabled(true);
		}
		
		// add is geselecteerd bij instantiatie 
		add.setSelectedControl(true);
		
		super.addButton(add);
		super.addButton(moveDown);
		super.addButton(moveUp);
		super.addButton(remove);
		
		
//		
		// Add all buttons en textboxes to one list 
		this.getListControls().addAll(this.getButtons());
		this.getListControls().addAll(this.getTextBoxes());
		this.getListControls().addAll(this.getListBoxes());
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
							b.setActivated(true);
							b.setSelectedControl(true);
							if(b.getTitle().equals("+")){
								String var = searchForLabelName("add arg.:").getLabelname();
								System.out.println(var.toString());
								source.addArgument(var);
								source.adjustedThroughDialog = diaLogAdjusted.LABELADJUSTED;
							}
								
								
						} else {
							b.setActivated(false);
							b.setSelectedControl(false);
						}
					}
				} else if (c.returnType() == TypeControl.Label){
					for(Control ctrl : getListControls()){
						if(!c.equals(ctrl)) 
							ctrl.setSelectedControl(false);
						else {
							c.setSelectedControl(true);
						}
					}
				} else if (c.returnType() == TypeControl.ListBox){
					// listbox is selected
					ListBox lb = (ListBox) c;
					lb.setSelectedControl(true);
					// welke label is geselecteerd
//					for(Label arg : lb.getArguments()){
//						if(arg.inArea(x, y)){
//							lb.setSelectedLabel(arg);
//							break;
//						}
//					}
				}
			}
		}
		this.getListControls().get(0).setSelectedControl(true);
	}
	
	@Override
	public void handleKey(int id, int keyCode, char keyChar) {
		Control c = getSelectedControl();
		if(id  == KeyEvent.KEY_PRESSED && keyCode == KeyEvent.VK_TAB ) {
			// Switch to next Control (Button or textBox)
			Control previousSelectedControl = getSelectedControl();
			if (previousSelectedControl != null) {
				previousSelectedControl.setSelectedControl(false);
				getNextControl(previousSelectedControl).setSelectedControl(true);
			} else {
				this.getListControls().get(0).setSelectedControl(true);
			}
		}
		
		else if(id == KeyEvent.KEY_PRESSED && keyCode == KeyEvent.VK_DOWN && c.getClass()==ListBox.class) {
			ListBox lb = (ListBox) c;
			lb.selectPrevious();
		}
		
		else if(id == KeyEvent.KEY_PRESSED && keyCode == KeyEvent.VK_UP && c.getClass()==ListBox.class) {
			ListBox lb = (ListBox) c;
			lb.selectNext();
		}
		
		else if(id == KeyEvent.KEY_PRESSED && keyCode == KeyEvent.VK_SPACE ) {
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
		} else if(id == KeyEvent.KEY_PRESSED) {
			for(Label l : this.getTextBoxes()){
				
				if(l.isSelectedControl()){
					if(keyChar == KeyEvent.VK_BACK_SPACE){
						l.setLabelname(l.getLabelname().substring(0, l.getLabelname().length()-1));
					} else 
						l.setLabelname(l.getLabelname() + keyChar);
					System.out.println(l.getLabelname());
					source.adjustedThroughDialog = diaLogAdjusted.LABELADJUSTED;
				} 
			}
		}
	}
	private Label searchForLabelName(String toSearchFor) {
		for( Label l: this.getTextBoxes()) {
			if( l.getTitle().equals( toSearchFor )) {
				return l; 
			}
		}
		return null;
	}


	private Control getNextControl(Control previous) {
		ArrayList<Control> controls = this.getListControls();
		int index = 0;
		for (Control c : controls) {
			if (previous == c)
				break;
			index++;
		}
		if (index == controls.size()-1)
			return controls.get(0);
		else
			return controls.get(index+1);
	}
	
	private Control getSelectedControl() {
		Control c = null;
		for (Control d : this.getListControls()) {
			if (d.isSelectedControl()) {
				c = d;
				break;
			}
		}
		return c;
	}
}
