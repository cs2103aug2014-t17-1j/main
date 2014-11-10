package testCases;

import static org.junit.Assert.*;

import org.junit.Test;

import uiView.CommandStack;

public class CommandStackTestCase {
	private CommandStack commandStack;

	@Test
	// @author  A0112581N
	public void test() {
		String command = "";
		commandStack = new CommandStack();
		commandStack.insertCommand(command);
		assertEquals(commandStack.retrieveCommandFromForwardStack(), command);
		assertEquals(commandStack.retrieveCommandFromForwardStack(), command);

	}

	@Test
	public void testLongString() {
		commandStack = new CommandStack();
		String command = "new task";
		commandStack.insertCommand(command);
		assertEquals(commandStack.retrieveCommandFromForwardStack(), command);
		assertEquals(commandStack.retrieveCommandFromBackwardStack(), command);
	}

	@Test
	public void testNull() {
		commandStack = new CommandStack();
		String command = null;
		commandStack.insertCommand(command);
		assertEquals(commandStack.retrieveCommandFromBackwardStack(), "");
		assertEquals(commandStack.retrieveCommandFromForwardStack(), null);

	}

	@Test
	public void testForinvalid() {
		commandStack = new CommandStack();
		String command = "ckjfksldjkfl";
		commandStack.insertCommand(command);
		assertEquals(commandStack.retrieveCommandFromBackwardStack(), "");
		assertEquals(commandStack.retrieveCommandFromForwardStack(),
				"ckjfksldjkfl");
	}

}
