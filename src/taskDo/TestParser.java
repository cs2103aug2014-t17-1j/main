package taskDo;

import org.junit.Assert;
import org.junit.Test;

import commandFactory.CommandType;

public class TestParser {

	@Test
	public void testAdd() {
		boolean isValid = Parser.parseString("add [Test Add due] due [20/08/1991]");
		
		Assert.assertEquals(true, isValid);
		Assert.assertEquals(CommandType.ADD, ParsedResult.getCommandType());
		Assert.assertEquals("Test Add due", ParsedResult.getTaskDetails().getDescription());
		Assert.assertEquals("20/08/1991",ParsedResult.getTaskDetails().getDueDate().toLocalDate().toString("dd/MM/yyyy"));
		
		isValid = Parser.parseString("add [Test add due and category] due [20th august 1991] category [testing]");
		
		Assert.assertEquals(true, isValid);
		Assert.assertEquals(CommandType.ADD, ParsedResult.getCommandType());
		Assert.assertEquals("Test add due and category", ParsedResult.getTaskDetails().getDescription());
		Assert.assertEquals("20/08/1991",ParsedResult.getTaskDetails().getDueDate().toLocalDate().toString("dd/MM/yyyy"));
		Assert.assertEquals("testing", ParsedResult.getTaskDetails().getCatogory());
	}

}
