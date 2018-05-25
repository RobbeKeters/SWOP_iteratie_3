package Model;

import java.awt.event.KeyEvent;

import Controller.Mouse;

public class DialogBoxResultMessage extends DialogBox{

	ResultMessage source;
	
	public DialogBoxResultMessage(int width, int height, int origineX, int origineY, ResultMessage r) {
		super(width, height, origineX, origineY);
	
		source = r;
		
		int xI = origineX + width/2 ;
		int yI = origineY + height/2 ;
		
		Label result = new Label(r.getLabel().getLabelname(),xI, yI);
		super.addTextBox(result);
		result.setSelected(true);
		
		
		// Add all buttons en textboxes to one list 
		this.getListControls().addAll(this.getButtons());
		this.getListControls().addAll(this.getTextBoxes());
		
	}

	@Override
	public void handleMouse(Mouse id, int x, int y) {
		if ( id == Mouse.SINGLECLICK) {
			for ( Label l : this.getTextBoxes()) {
				if ( l.inArea(x, y)) {
					l.setSelected(true);
				} else {
					l.setSelected(false);
				}
			}
		}

	}

	@Override
	public void handleKey(int id, int keyCode, char keyChar) {
		Label selectedLabel = null;
		for ( Label l : this.getTextBoxes()) {
			if ( l.isSelectedControl()) {
				selectedLabel = l;
				break;
			}
		}
		if(id == KeyEvent.KEY_TYPED && keyChar == KeyEvent.VK_SPACE ) {
			for ( Label l : this.getTextBoxes()) {
				if ( !l.isSelectedControl()) {
					l.setSelected(true);
				} else {
					l.setSelected(false);
				}
			}
		} else if ( id== KeyEvent.KEY_TYPED && selectedLabel != null && keyChar != KeyEvent.VK_BACK_SPACE ) {
			source.getLabel().setLabelname(source.getLabel().getLabelname()+keyChar);
			selectedLabel.setLabelname(source.getLabel().getLabelname());
		} else if ( id== KeyEvent.KEY_TYPED && selectedLabel != null && keyChar == KeyEvent.VK_BACK_SPACE ) {
			source.getLabel().setLabelname(source.getLabel().getLabelname().substring(0, source.getLabel().getLabelname().length()-1));
			selectedLabel.setLabelname(source.getLabel().getLabelname());
		}
	}

}
