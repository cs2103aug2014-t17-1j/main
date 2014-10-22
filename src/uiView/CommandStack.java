package uiView;

import java.util.Stack;

import commonClasses.Constants;
/*
 * @author Paing Zin Oo(Jack)
 */
public class CommandStack {
	private Stack<String> forwardStack;
	private Stack<String> backwardStack;
	
	
	public CommandStack(){
		forwardStack = new Stack<String>();
		backwardStack = new Stack<String>();
	}
	
	public void insertCommand(String command){
		forwardStack.push(command);
	}
	
	public String retrieveCommandFromForwardStack(){
		String command = Constants.EMPTY_STRING;
		if(forwardStack.isEmpty()){
				return command;
		} else{
			command = forwardStack.pop();
			backwardStack.add(command);
		}
		return command;
	}
	
	public String retrieveCommandFromBackwardStack(){
		String command = Constants.EMPTY_STRING;
		if(backwardStack.isEmpty()){
			return command;
		}else{
			command = backwardStack.pop();
			forwardStack.add(command);
		}
		return command;
	}
	
	
}
