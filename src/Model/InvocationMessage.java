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
	
	/**
	 * Sets this invocation message's method name to the given method name.
	 * @param methodName	The given method name.
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	/**
	 * Sets this invocation message's arguments name to the given method arguments.
	 * @param arguments		The given method arguments.
	 */
	public void setArguments(String[] arguments) {
		this.arguments = arguments;
	}
	
	/**
	 * Return this invocation message's method name.
	 * @return		This invocation message's method name.
	 */
	public String getMethodName() {
		return this.methodName;
	}
	
	/**
	 * Return this invocation message's arguments.
	 * @return		This invocation message's arguments.
	 */
	public String[] getArguments() {
		return this.arguments;
	}
	
	/**
	 * Adds an arguments to the list of arguments.
	 * @param var		The argument to add.
	 */
	public void addArgument(String var) {
		System.out.println("oud: " + arguments.length);
		String[] newArguments = new String[arguments.length+1];
		for(int i = 0; i<arguments.length; i++){
			newArguments[i] = arguments[i];
		}
		newArguments[arguments.length] = var;
		System.out.println("nieuw: " + newArguments.length + " " + newArguments[arguments.length]);
	}
	
}
