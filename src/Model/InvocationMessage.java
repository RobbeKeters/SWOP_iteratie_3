package Model;
/**
 * 
 * An invocation message in a diagram.
 * 
 * @author Kevin Lavrijssen, Wout Mees, Florent Nander Meijer, Robbe Keters
 *
 */
public class InvocationMessage extends Message {
	/**
	 * Constructor.
	 * @param lab				The new invocation message's label.
	 * @param resultMessage		The new invocation message's result message.
	 */
	public InvocationMessage(String lab, ResultMessage resultMessage) {
		super(lab);
		result = resultMessage;
	}
	
	private String methodName;
	
	private String[] arguments;
	
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	public void setArguments(String[] arguments) {
		this.arguments = arguments;
	}
	
	public String getMethodName() {
		return this.methodName;
	}
	
	public String[] getArguments() {
		return this.arguments;
	}
	
}
