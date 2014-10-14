package Parser;

import org.junit.Assert;
import org.junit.Test;

import commandFactory.CommandType;

public class TestParser {

	@Test
	public void testAdd() {
		Parser testingParser = new Parser();
		ParsedResult result = new ParsedResult();
		
		//No Deadline
		result = testingParser.parseString("add [Test Add no deadline]");
		Assert.assertEquals(true, result.getValidationResult());
		Assert.assertEquals(CommandType.ADD, result.getCommandType());
		Assert.assertEquals("Test Add no deadline", result.getTaskDetails().getDescription());	

		//With Deadline
		result = testingParser.parseString("add [Test Add due] due [20/08/1991]");
		Assert.assertEquals(true, result.getValidationResult());
		Assert.assertEquals(CommandType.ADD, result.getCommandType());
		Assert.assertEquals("Test Add due", result.getTaskDetails().getDescription());
		Assert.assertEquals("20/08/1991",result.getTaskDetails().getDueDate().toLocalDate().toString("dd/MM/yyyy"));
		
		//With Deadline and category
		result = testingParser.parseString("add [Test add due and category] due [20th august 1991] category [testing]");
		Assert.assertEquals(true, result.getValidationResult());
		Assert.assertEquals(CommandType.ADD, result.getCommandType());
		Assert.assertEquals("Test add due and category", result.getTaskDetails().getDescription());
		Assert.assertEquals("20/08/1991", result.getTaskDetails().getDueDate().toLocalDate().toString("dd/MM/yyyy"));
		Assert.assertEquals("testing", result.getTaskDetails().getCategory());
	}

}
