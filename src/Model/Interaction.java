package Model;
import java.util.ArrayList;
import Model.Handler.MoveWindowHandler;
import Model.Handler.*;

/**
 * A collection of canvases that all share the same message system.
 * 
 * @author Kevin Lavrijssen, Wout Mees, Florent Nander Meijer, Robbe Keters
 */
public class Interaction {
	
	/**
	 * Constructor.
	 * 
	 * @param i		The x and y coordinate of origin of the first canvas of the new interation.
	 */
	public Interaction(int i) {
		subWindows = new ArrayList<Window>();
		//int xOrigineRandom = randNumberPos.nextInt(250);
		//int yOrigineRandom = randNumberPos.nextInt(250);
		
		Window w = new Window(300, 300, i, i,this );
		subWindows.add(w);
	}

	private ArrayList<Window> subWindows = new ArrayList<Window>();
	//private Random randNumberPos = new Random();
	
	/**
	 * Returns a collection of this interaction's subwindows (canvases).
	 * @return	This interaction's subwindows.
	 */
	public ArrayList<Window> getSubWindows(){
		return subWindows;
	}
	
	/**
	 * Deletes the given canvas from this interaction.
	 * @param c		The canvas to delete.
	 */
	public void deleteCanvas(Window w) {
		subWindows.remove(w);
	}
	
	/**
	 * Adds the given canvas to this interaction.
	 * @param c		The canvas to add.
	 */
	public void addWindow(Window w) {
		w.setInteractioin(this);
		subWindows.add(w);
	}
	
	/**
	 * Adjust a given window of this interaction based on the given kind of adjustment.
	 * @param type				The given kind of adjustment
	 * @param updatedCanvas		The given canvas.
	 */
	public void adjusted(ADJUSTED_TYPE type, Window updatedWindow) {
		// dececide what is adjusted
		switch(type) {
		
		case ADDED_PARTY: 
			updateParties(updatedWindow);
			break;
		case PARTY_LABEL: 
			updatePartyLabels(updatedWindow);
			break;
		case ADDED_MESSAGE: 
			updateMessages(updatedWindow);
			break;
		case MESSAGE_LABEL: 
			updateMessageLabels(updatedWindow);
			break;
		case CHANGE_TYPE:
			updatePartyTypes(updatedWindow);
			break;
		case DELETE_MESSAGE:
			updateDeletePartyOrMessage(updatedWindow);
		}	
	}
	
	private void updateParties(Window updatedWindow) {
		if( subWindows.size() > 1 ) {
			Window usedForFindingPartyNumber=null;
			for(Window w : subWindows) {
				if( !w.equals(updatedWindow)) {
					usedForFindingPartyNumber = w;
				}
			}
			// this found partyNumber is the partyNumber of the latest added party in updatedCanvas
			int partyNumber = Window.getAvailablePartyNumber(usedForFindingPartyNumber);
			Party partyJustAdded= Window.findPartyByNumber(updatedWindow, partyNumber);
			// Add Party to every Canvas in the Interaction!
			for(Window w : subWindows) {
				Party partyToAdd = (Party) partyJustAdded.clone();
				if( !w.equals(updatedWindow)) {
					
					w.addParty(partyToAdd);
					
					// See SelectElementHandler.updatePartyPositions (Same principle)
					
					int oldXorigine = updatedWindow.getOrigineX();
					int oldYorigine = updatedWindow.getOrigineY();
					int newXorigine = w.getOrigineX();
					int newYorigine = w.getOrigineY();
					
					// Update Positions Sequence Diagram
					int xSeq = partyToAdd.getPosSeq().getX();
					int ySeq = partyToAdd.getPosSeq().getY();
					
					int dx = Math.abs((xSeq-oldXorigine));
					int dy = Math.abs((ySeq-oldYorigine));
					
					partyToAdd.setPosSeq((newXorigine+dx),(newYorigine + dy));
					// Update Label Position for  Sequence Diagram
					int yLabel = newYorigine + w.getHeight()/12 + +partyToAdd.getHeight() + 10; // Needs to be in sync with AddPartyHandler
					partyToAdd.getLabel().setLabelPositionSeq((newXorigine+dx), yLabel);
					
					// Update Positions Communication Diagram
					int xCom = partyToAdd.getPosComm().getX();
					int yCom = partyToAdd.getPosComm().getY();
					
					dx = Math.abs((xCom-oldXorigine));
					dy = Math.abs((yCom-oldYorigine));
					
					partyToAdd.setPosComm((newXorigine + dx), (newYorigine+dy));
					// Update Label Postion for Communication Diagram
					// Label Postion in communication view is not used......... (See Add PartyHandler)
					
				}
			}
		}
	}
	
	private void updatePartyLabels(Window updatedWindow) {
		for(Window w : subWindows) {
			for( Party p : w.getParties()) {
				// Get updated Label name from "updatedCanvas"
				String newLabelName = Window.findPartyByNumber(updatedWindow, p.getPartyNumber()).getLabel().getLabelname();
				p.getLabel().setLabelname(newLabelName);
				p.getLabel().setLabelname(newLabelName);
				String name = p.getLabel().getLabelname();
				p.getLabel().setWidth(8*name.length());
			}
		}
	}
	
	private void updateMessages(Window updatedWindow) {
		if( subWindows.size() > 1 ) {
			
			for( Window w : subWindows) {
				if( !w.equals(updatedWindow)) {
					w.deleteAllMessages();
					copyMessages(updatedWindow, w);
				}
			}
			/**
			Canvas usedForFindingMessageNumber=null;
			for(Canvas c : subWindows) {
				if( !c.equals(updatedCanvas)) {
					usedForFindingMessageNumber = c;
				}
			}
			int messageNumber = Canvas.getAvailableMessageNumber(usedForFindingMessageNumber);
			Message messageJustAdded= Canvas.findMessageByNumber(updatedCanvas, messageNumber);
			// Add Message to every Canvas in the Interaction!
			for(Canvas c : subWindows) {
				Message messageToAdd = (Message) messageJustAdded.clone();
				if( !c.equals(updatedCanvas)) {
					c.addMessage(messageToAdd);
					
					// See SelectElementHandler.updateMessagePositions (Same principle)
	
					int oldXorigine = updatedCanvas.getOrigineX();
					int oldYorigine = updatedCanvas.getOrigineY();
					int newXorigine = c.getOrigineX();
					int newYorigine = c.getOrigineY();
					
					// Update Label Positions Sequence Diagram
					int xSeq = messageToAdd.getLabel().getLabelPositionSequence().getX();
					int ySeq = messageToAdd.getLabel().getLabelPositionSequence().getY();
					
					int dx = Math.abs((xSeq-oldXorigine));
					int dy = Math.abs((ySeq-oldYorigine));
					
					messageToAdd.getLabel().setLabelPositionSeq((newXorigine+dx),(newYorigine + dy));
					// Update Label Positions Communication Diagram
					int xCom = messageToAdd.getLabel().getLabelPositionComm().getX();
					int yCom = messageToAdd.getLabel().getLabelPositionComm().getY();
					
					dx = Math.abs((xCom-oldXorigine));
					dy = Math.abs((yCom-oldYorigine));
					
					messageToAdd.getLabel().setLabelPositionComm((newXorigine+dx), (newYorigine+dy));
					
					updateMessagePropertiesAfterClone(updatedCanvas,c, messageToAdd);
				}
			}*/
			
		}
	}
	
	private void updateMessageLabels(Window updatedWindow) {
		for(Window w : subWindows) {
			for( Message p : w.getMessages()) {
				// Get updated Label name from "updatedCanvas"
				String newLabelName = Window.findMessageByNumber(updatedWindow, p.getMessageNumber()).getLabel().getLabelname();
				p.getLabel().setLabelname(newLabelName);
			}
		}
	}
	
	/**
	 * Updates the properties of a given message from a given canvas to its new clone.
	 * @param updatedWindow		The cloned canvas.
	 * @param newWindow			The new canvas clone.
	 * @param messageToAdd		The message to update.
	 */
	public static void updateMessagePropertiesAfterClone(Window updatedWindow , Window newWindow, Message messageToAdd ) {
		// Update Message properties to new Canvas after clone!
		int numberSentBy = Window.findPartyByNumber(updatedWindow, messageToAdd.getSentBy().getPartyNumber()).getPartyNumber();
		messageToAdd.setSentBy(Window.findPartyByNumber(newWindow, numberSentBy));
		int numberReceivedBy = Window.findPartyByNumber(updatedWindow, messageToAdd.getReicevedBy().getPartyNumber()).getPartyNumber();
		messageToAdd.setReicevedBy(Window.findPartyByNumber(newWindow, numberReceivedBy));
		if (messageToAdd.getPredecessor() != null) {
			int numberPredecessor = Window.findMessageByNumber(updatedWindow, messageToAdd.getPredecessor().getMessageNumber()).getMessageNumber();
			messageToAdd.setPredecessor(Window.findMessageByNumber(newWindow,numberPredecessor));
		}
		if (messageToAdd.getResult() != null) { // Invocation Message has Result == null
			int numberResult = Window.findMessageByNumber(updatedWindow, messageToAdd.getResult().getMessageNumber()).getMessageNumber();
			messageToAdd.setResult((ResultMessage)Window.findMessageByNumber(newWindow, numberResult));
		}
	}
	
	private void updatePartyTypes(Window updatedWindow){
		for(Window w : subWindows) {
			if( !w.equals(updatedWindow)) { // No concurrent modification !
				for( Party p : updatedWindow.getParties()) {
					
					// Clone Party with correct type -> remove Old Party -> Add new Party with correct type
	
					Party partyToFindInUpdatedCanvas = Window.findPartyByNumber(updatedWindow, p.getPartyNumber());
					
					Party partyToAdd = partyToFindInUpdatedCanvas.clone();
					
					Party partyToDelete = Window.findPartyByNumber(w, p.getPartyNumber());
					
					// Update Message with new Party (partyToAdd)
					for( Message m : w.getMessages()) {
						if( m.getSentBy().equals(partyToDelete)) {
							m.setSentBy(partyToAdd);
						}
						if( m.getReicevedBy().equals(partyToDelete)) {
							m.setReicevedBy(partyToAdd);
						}
					}
										
					w.deleteParty(partyToDelete);		
					w.addParty(partyToAdd);
					
				
					
					partyToAdd.setPosSeq(partyToDelete.getPosSeq().getX(),partyToDelete.getPosSeq().getY());
					partyToAdd.getLabel().setLabelPositionSeq(partyToDelete.getLabel().getLabelPositionSequence().getX(), partyToDelete.getLabel().getLabelPositionSequence().getY());
					
					partyToAdd.setPosComm(partyToDelete.getPosComm().getX(), partyToDelete.getPosComm().getY());
					partyToAdd.getLabel().setLabelPositionComm(partyToDelete.getLabel().getLabelPositionComm().getX(), partyToDelete.getLabel().getLabelPositionComm().getY());
					// Update Label Postion for Communication Diagram
					// Label Postion in communication view is not used......... (See Add PartyHandler)
					
				}
			}
		}
	}
	
	private void updateDeletePartyOrMessage(Window updatedWindow) {
		updateMessages(updatedWindow);
		
		if( subWindows.size() > 1 ) {
			Window usedForFindingPartyNumber=null;
			for(Window w : subWindows) {
				if( !w.equals(updatedWindow)) {
					usedForFindingPartyNumber = w;
				}
			}
			ArrayList<Party> listToDelete = new ArrayList<Party>();
			for( Party p :usedForFindingPartyNumber.getParties()) {
				if( Window.findPartyByNumber(updatedWindow, p.getPartyNumber()) == null){
					listToDelete.add(p);
				}
			}
			for( Window  w: subWindows) {
				if( !w.equals(updatedWindow)) {
					for (Party partyToDelte :  listToDelete) {
						w.getParties().remove(Window.findPartyByNumber(w, partyToDelte.getPartyNumber()));
					}
				}
			}
		}
		/**
		if( subWindows.size() > 1 ) {
			for( Canvas c : subWindows) {
				if( !c.equals(updatedCanvas)) {
					c.deleteAllMessages();
					
				
					int oldXorigine = updatedCanvas.getOrigineX();
					int oldYorigine = updatedCanvas.getOrigineY();
					int newXorigine = c.getOrigineX();
					int newYorigine = c.getOrigineY();
					
					// Clone Messages
					for( Message m : updatedCanvas.getMessages()) {
						Message messageToAdd = (Message) m.clone();
						c.addMessage(messageToAdd);
					}
					SelectElementHandler.updateMessagePositions(c , oldXorigine, oldYorigine, newXorigine, newYorigine);
					for( Message m : c.getMessages()) {
						Interaction.updateMessagePropertiesAfterClone(updatedCanvas,c, m);
					}
					
				}
			}
		}
		*/
	}
	
	/**
	 * Copy messages from one canvas to another.
	 * @param updatedWindow			The canvas to copy from.
	 * @param notUpdatedWindow		The canvas to update.
	 */
	public static void copyMessages(Window updatedWindow , Window notUpdatedWindow) {
		
		int oldXorigine = updatedWindow.getOrigineX();
		int oldYorigine = updatedWindow.getOrigineY();
		int newXorigine = notUpdatedWindow.getOrigineX();
		int newYorigine = notUpdatedWindow.getOrigineY();
		
		// Clone Messages
		for( Message m : updatedWindow.getMessages()) {
			Message messageToAdd = (Message) m.clone();
			notUpdatedWindow.addMessage(messageToAdd);
		}
		MoveWindowHandler.updateMessagePositions(notUpdatedWindow , oldXorigine, oldYorigine, newXorigine, newYorigine);
		for( Message m : notUpdatedWindow.getMessages()) {
			Interaction.updateMessagePropertiesAfterClone(updatedWindow,notUpdatedWindow, m);
		}
		Window.updateYPositionLMessageLabelsSequenceDiagram(notUpdatedWindow);
	}

}


