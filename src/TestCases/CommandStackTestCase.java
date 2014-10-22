package TestCases;

import static org.junit.Assert.*;

import org.junit.Test;

import uiView.CommandStack;

/*
 * @author Paing Zin Oo(Jack)
 */
public class CommandStackTestCase {
	private CommandStack commandStack; 
	
	@Test
	public void test(){
		String command = "testing";
		commandStack = new CommandStack();
		commandStack.insertCommand(command);
		assertEquals(commandStack.retrieveCommandFromBackwardStack(),"");
		assertEquals(commandStack.retrieveCommandFromForwardStack(),command);
		assertEquals(commandStack.retrieveCommandFromBackwardStack(),command);
		
		String command1 = "";
		commandStack.insertCommand(command1);
		assertEquals(commandStack.retrieveCommandFromBackwardStack(),command1);
		assertEquals(commandStack.retrieveCommandFromForwardStack(),command1);
		assertEquals(commandStack.retrieveCommandFromBackwardStack(),command1);
	}
}
