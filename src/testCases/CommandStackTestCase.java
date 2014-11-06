package testCases;

import static org.junit.Assert.*;

import org.junit.Test;

import uiView.CommandStack;

public class CommandStackTestCase {
	private CommandStack commandStack;
	@Test
	public void test() {
		String command = "";
		commandStack = new CommandStack();
		commandStack.insertCommand(command);
		assertEquals(commandStack.retrieveCommandFromForwardStack(),command);
		assertEquals(commandStack.retrieveCommandFromForwardStack(),command);
		
	}
	
	@Test
	public void testLongString(){
		commandStack = new CommandStack();
		String command = "new task";
		commandStack.insertCommand(command);
		assertEquals(commandStack.retrieveCommandFromForwardStack(),command);
		assertEquals(commandStack.retrieveCommandFromBackwardStack(),command);
	}

}
