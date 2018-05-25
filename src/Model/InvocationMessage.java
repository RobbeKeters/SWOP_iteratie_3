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
	
	private String[] arguments = new String[1];
	
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

	public void addArgument(String var) {
		String[] newArguments = new String[arguments.length+1];
		for(int i = 0; i<arguments.length; i++){
			newArguments[i] = arguments[i];
		}
		newArguments[arguments.length] = var;
	}
	
}
