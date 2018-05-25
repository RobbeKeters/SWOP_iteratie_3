package Model.Handler;

import Model.ADJUSTED_TYPE;
import Model.Actor;
import Model.Canvas;
import Model.Message;
import Model.Object;
import Model.Party;
import Model.Window;
import Model.diaLogAdjusted;

/**
 * A handler that handles the actions of a party changing its type.
 */
public class SetPartyTypeHandler extends Handler {
	
	/**
	 * Handles a party changing its type.
	 * @param window		The canvas to edit.
	 * @param x				The x coordinate of the mouse event used to handle these events.
	 * @param y				The y coordinate of the mouse event used to handle these events.
	 */
	public static void handle(Window window, int x, int y) {
		
		Party changingParty = getPartyAt(x, y, window);	
		if(changingParty==null) {return;}
		 
		Party partyToAdd;
		if( Object.class == changingParty.getClass() ) {	
			partyToAdd = new Actor(changingParty.getClassName(),changingParty.getPartyNumber());
		} else {
			partyToAdd =  new Object(changingParty.getClassName(),changingParty.getPartyNumber());
		}
		
		partyToAdd.setLabel(changingParty.getLabel());
		partyToAdd.setPosComm(changingParty.getPosComm().getX(), changingParty.getPosComm().getY());
		partyToAdd.setPosSeq(changingParty.getPosSeq().getX(), changingParty.getPosSeq().getY());
		partyToAdd.setSelected(false);
		
		for(Message m : window.getMessages()) {
			if(m.getSentBy().equals(changingParty))
				m.setSentBy(partyToAdd);
			if(m.getReicevedBy().equals(changingParty))
				m.setReicevedBy(partyToAdd);
		}
		
		// Add newly created party( Object or Actor )
		window.addParty(partyToAdd);
		// Delete "old" party ( Object or Actor )
		window.deleteParty(changingParty); 
		
		// Notify Interaction
		window.getInteraction().adjusted(ADJUSTED_TYPE.CHANGE_TYPE, window);
		
		// Notify Dialog
		window.getInteraction().oldParty = changingParty;
		window.getInteraction().newParty = partyToAdd;
		window.getInteraction().adjustedDialog = diaLogAdjusted.TYPEADJUSTED;
		
	}
}
