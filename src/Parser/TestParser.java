package Parser;

import org.junit.Assert;
import org.junit.Test;

import commandFactory.CommandType;
import commonClasses.Constants;

public class TestParser {

	@Test
	public void testAdd() {
		Parser testingParser = new Parser();
		ParsedResult result = new ParsedResult();

		// No Deadline
		result = testingParser.parseString("-add Test Add no deadline");
		Assert.assertEquals(true, result.getValidationResult());
		Assert.assertEquals(CommandType.ADD, result.getCommandType());
		Assert.assertEquals("Test Add no deadline", result.getTaskDetails().getDescription());

		// With Deadline
		result = testingParser.parseString("-add Test Add due -due 20/08/1991");
		Assert.assertEquals(true, result.getValidationResult());
		Assert.assertEquals(CommandType.ADD, result.getCommandType());
		Assert.assertEquals("Test Add due", result.getTaskDetails().getDescription());
		Assert.assertEquals("20/08/1991", result.getTaskDetails().getDueDate().toLocalDate().toString("dd/MM/yyyy"));

		// With Deadline and category
		result = testingParser.parseString("-add Test add due and category -due 20th august 1991 -category testing");
		Assert.assertEquals(true, result.getValidationResult());
		Assert.assertEquals(CommandType.ADD, result.getCommandType());
		Assert.assertEquals("Test add due and category", result.getTaskDetails().getDescription());
		Assert.assertEquals("20/08/1991", result.getTaskDetails().getDueDate().toLocalDate().toString("dd/MM/yyyy"));
		Assert.assertEquals("testing", result.getTaskDetails().getCategory());

		// From startDate to end date
		result = testingParser.parseString("-add Test fromto -from 20th august 1991 -to 10th september 1991");
		Assert.assertEquals(true, result.getValidationResult());
		Assert.assertEquals(CommandType.ADD, result.getCommandType());
		Assert.assertEquals("Test fromto", result.getTaskDetails().getDescription());
		Assert.assertEquals("20/08/1991", result.getTaskDetails().getStartDate().toLocalDate().toString("dd/MM/yyyy"));
		Assert.assertEquals("10/09/1991", result.getTaskDetails().getDueDate().toLocalDate().toString("dd/MM/yyyy"));

		// From startDate to end date with category and importance level
		result = testingParser	.parseString("-add Test fromto -from 20 aug 1991 -to 10 sep 1991 -category Sample -impt Y");
		Assert.assertEquals(true, result.getValidationResult());
		Assert.assertEquals(CommandType.ADD, result.getCommandType());
		Assert.assertEquals("Test fromto", result.getTaskDetails().getDescription());
		Assert.assertEquals("20/08/1991", result.getTaskDetails().getStartDate().toLocalDate().toString("dd/MM/yyyy"));
		Assert.assertEquals("10/09/1991", result.getTaskDetails().getDueDate().toLocalDate().toString("dd/MM/yyyy"));
		Assert.assertEquals("Sample", result.getTaskDetails().getCategory());
		Assert.assertEquals(true, result.getTaskDetails().isImportant());

		// Importance level
		result = testingParser.parseString("-add Test impt -impt Y");
		Assert.assertEquals(true, result.getValidationResult());
		Assert.assertEquals(CommandType.ADD, result.getCommandType());
		Assert.assertEquals("Test impt", result.getTaskDetails().getDescription());
		Assert.assertEquals(true, result.getTaskDetails().isImportant());

	}
	
	public void testDisplay() {
		Parser testingParser = new Parser();
		ParsedResult result = new ParsedResult();
		
		//no deadline
		result = testingParser.parseString("-display TODO");
		Assert.assertEquals(true, result.getValidationResult());
		Assert.assertEquals(CommandType.DISPLAY, result.getCommandType());
		Assert.assertEquals(Constants.SOMEDAY, result.getTaskDetails().getDueDate());
		
		//with deadline
		result = testingParser.parseString("-display 20/08/1991");
		Assert.assertEquals(true, result.getValidationResult());
		Assert.assertEquals(CommandType.DISPLAY, result.getCommandType());
		Assert.assertEquals("20/08/1991", result.getTaskDetails().getDueDate().toLocalDate().toString("dd/MM/yyyy"));
		
		//range of dates
		result = testingParser.parseString("-display 20/08/1991 -to 10 sep 1991");
		Assert.assertEquals(true, result.getValidationResult());
		Assert.assertEquals(CommandType.DISPLAY, result.getCommandType());
		Assert.assertEquals("20/08/1991", result.getTaskDetails().getStartDate().toLocalDate().toString("dd/MM/yyyy"));
		Assert.assertEquals("10/09/1991", result.getTaskDetails().getDueDate().toLocalDate().toString("dd/MM/yyyy"));
		
	}

}
