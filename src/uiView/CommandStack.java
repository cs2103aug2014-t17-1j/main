package uiView;

import java.util.Stack;

import commonClasses.Constants;

/* This class is for storing lists of commands that users that and store them in the stacks 
 *  so that they can retrieve accordingly
 *  
 */
public class CommandStack {
	// @author Paing Zin Oo(Jack) A0112581N
	private Stack<String> forwardStack;
	private Stack<String> backwardStack;

	public CommandStack() {
		forwardStack = new Stack<String>();
		backwardStack = new Stack<String>();
	}

	public void insertCommand(String command) {
		forwardStack.push(command);
	}

	public String retrieveCommandFromForwardStack() {
		String command = Constants.STRING_STRING;
		if (forwardStack.isEmpty()) {
			return command;
		} else {
			command = forwardStack.pop();
			backwardStack.add(command);
		}
		return command;
	}

	public String retrieveCommandFromBackwardStack() {
		String command = Constants.STRING_STRING;
		if (backwardStack.isEmpty()) {
			return command;
		} else {
			command = backwardStack.pop();
			forwardStack.add(command);
		}
		return command;
	}

}
