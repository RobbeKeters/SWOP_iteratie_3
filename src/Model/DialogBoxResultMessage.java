package Model;

import java.awt.event.KeyEvent;

import Controller.Mouse;

public class DialogBoxResultMessage extends DialogBox{

	ResultMessage source;
	
	public DialogBoxResultMessage(int origineX, int origineY, int width, int height, ResultMessage r) {
		super(origineX, origineY, width, height);
	
		source = r;
		
		int xI = origineX + width/2;
		int yI = origineY + height/2;
		
		Label result = new Label(r.getLabel().getLabelname().replace("|", ""),xI, yI);
		result.setTitle("result: ");
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
		} else if (id == KeyEvent.KEY_TYPED && keyChar == KeyEvent.VK_TAB ) {
			for ( Label l : this.getTextBoxes()) {
				if ( !l.isSelectedControl()) {
					l.setSelected(true);
				}
			}
		} else if ( id== KeyEvent.KEY_TYPED && selectedLabel != null && keyChar != KeyEvent.VK_BACK_SPACE ) {
			source.getLabel().setLabelname(source.getLabel().getLabelname().replace("|", "")+keyChar);
			selectedLabel.setLabelname(source.getLabel().getLabelname().replace("|", "")+"|");
			source.adjustedThroughDialog = diaLogAdjusted.LABELADJUSTED;
		} else if ( id== KeyEvent.KEY_TYPED && selectedLabel != null && keyChar == KeyEvent.VK_BACK_SPACE ) {
			source.getLabel().setLabelname(source.getLabel().getLabelname().replace("|", "").substring(0, source.getLabel().getLabelname().length()-1));
			selectedLabel.setLabelname(source.getLabel().getLabelname().replace("|", "")+"|");
			source.adjustedThroughDialog = diaLogAdjusted.LABELADJUSTED;
		}
	}

}
