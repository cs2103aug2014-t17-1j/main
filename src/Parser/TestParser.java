package Parser;

import org.junit.Assert;
import org.junit.Test;

import taskDo.TaskType;

import commandFactory.CommandType;
import commonClasses.Constants;
import commonClasses.SummaryReport;

public class TestParser {

	@Test
	public void testAdd() {
		Parser testingParser = new Parser();
		ParsedResult result = new ParsedResult();

		// No Deadline, category, impt, note
		result = testingParser.parseString("add homework -category testing -impt N -note extra points");
		Assert.assertEquals(true, result.getValidationResult());
		Assert.assertEquals(CommandType.ADD, result.getCommandType());
		Assert.assertEquals("homework", result.getTaskDetails().getDescription());
		Assert.assertEquals("testing", result.getTaskDetails().getCategory());
		Assert.assertEquals(false,result.getTaskDetails().isImportant());
		Assert.assertEquals("extra points",result.getTaskDetails().getTaskNote());
		Assert.assertEquals(TaskType.TODO, result.getTaskDetails().getTaskType());

		// With Deadline
		result = testingParser.parseString("add homework -due 20/08/1991");
		Assert.assertEquals(true, result.getValidationResult());
		Assert.assertEquals(CommandType.ADD, result.getCommandType());
		Assert.assertEquals("homework", result.getTaskDetails().getDescription());
		Assert.assertEquals("20/08/1991", result.getTaskDetails().getDueDate().toLocalDate().toString("dd/MM/yyyy"));
		Assert.assertEquals(TaskType.DEADLINE, result.getTaskDetails().getTaskType());
		
		// With Deadline, category, impt, note
		result = testingParser.parseString("add homework -due 20 aug 1991 -category testing -impt Y -note extra points");
		Assert.assertEquals(true, result.getValidationResult());
		Assert.assertEquals(CommandType.ADD, result.getCommandType());
		Assert.assertEquals("homework", result.getTaskDetails().getDescription());
		Assert.assertEquals("20/08/1991", result.getTaskDetails().getDueDate().toLocalDate().toString("dd/MM/yyyy"));
		Assert.assertEquals("testing", result.getTaskDetails().getCategory());
		Assert.assertEquals(true,result.getTaskDetails().isImportant());
		Assert.assertEquals("extra points",result.getTaskDetails().getTaskNote());
		Assert.assertEquals(TaskType.DEADLINE, result.getTaskDetails().getTaskType());
		
		// From startDate to end date
		result = testingParser.parseString("add homework -from 20 aug 1991 -to 10 sep 1991");
		Assert.assertEquals(true, result.getValidationResult());
		Assert.assertEquals(CommandType.ADD, result.getCommandType());
		Assert.assertEquals("homework", result.getTaskDetails().getDescription());
		Assert.assertEquals("20/08/1991", result.getTaskDetails().getStartDate().toLocalDate().toString("dd/MM/yyyy"));
		Assert.assertEquals("10/09/1991", result.getTaskDetails().getDueDate().toLocalDate().toString("dd/MM/yyyy"));
		Assert.assertEquals(TaskType.TIMED, result.getTaskDetails().getTaskType());
		
		// From startDate to end date, category, impt, note
		result = testingParser	.parseString("add homework -from 20 aug 1991 -to 10 sep 1991 -category Sample -impt Y -note extra points");
		Assert.assertEquals(true, result.getValidationResult());
		Assert.assertEquals(CommandType.ADD, result.getCommandType());
		Assert.assertEquals("homework", result.getTaskDetails().getDescription());
		Assert.assertEquals("20/08/1991", result.getTaskDetails().getStartDate().toLocalDate().toString("dd/MM/yyyy"));
		Assert.assertEquals("10/09/1991", result.getTaskDetails().getDueDate().toLocalDate().toString("dd/MM/yyyy"));
		Assert.assertEquals("Sample", result.getTaskDetails().getCategory());
		Assert.assertEquals(true, result.getTaskDetails().isImportant());
		Assert.assertEquals("extra points",result.getTaskDetails().getTaskNote());
		Assert.assertEquals(TaskType.TIMED, result.getTaskDetails().getTaskType());
		
		// due date, start date, end date, category,impt, note (Expected error)
		result = testingParser	.parseString("add homework -due today -from 20 aug 1991 -to 10 sep 1991 -category Sample -impt Y -note extra points");
		Assert.assertEquals(false, result.getValidationResult());
		Assert.assertEquals(Constants.MESSAGE_INVALID_COMBINATION_DUE_AND_FROMTO, SummaryReport.getFeedBackMsg());
		
		result = testingParser	.parseString("add homework -from 20 aug 1991 -to 10 sep 1991 -due today -category Sample -impt Y -note extra points");
		Assert.assertEquals(false, result.getValidationResult());
		Assert.assertEquals(Constants.MESSAGE_INVALID_COMBINATION_DUE_AND_FROMTO, SummaryReport.getFeedBackMsg());
		
		//Using 'to' command without 'from' command(expected error)
		result = testingParser	.parseString("add homework -to 10 sep 1991 -category Sample -impt Y -note extra points");
		Assert.assertEquals(false, result.getValidationResult());
		Assert.assertEquals(Constants.MESSAGE_MISSING_START_DATE_FOR_TASK, SummaryReport.getFeedBackMsg());
		
		// With Deadline, category, impt, note (Put in invalid date, error expected)
		result = testingParser.parseString("add homework -due 20th aug 1991 -category testing -impt Y -note extra points");
		Assert.assertEquals(false, result.getValidationResult());
		Assert.assertEquals(Constants.MESSAGE_INVALID_DATE, SummaryReport.getFeedBackMsg());
		
		// start date,end date, category, impt, note (Put in invalid start date, error expected)
		result = testingParser.parseString("add homework -from 20th aug 1991 -to 10 sep 2014 -category testing -impt Y -note extra points");
		Assert.assertEquals(false, result.getValidationResult());
		Assert.assertEquals(Constants.MESSAGE_INVALID_DATE, SummaryReport.getFeedBackMsg());
	
		// start date,end date, category, impt, note (Put in invalid end date, error expected)
		result = testingParser.parseString("add homework -from 20 aug 1991 -to 10th sep 2014 -category testing -impt Y -note extra points");
		Assert.assertEquals(false, result.getValidationResult());
		Assert.assertEquals(Constants.MESSAGE_INVALID_DATE, SummaryReport.getFeedBackMsg());
		
		//start date, end date, category, impt, note (Put in invalid impt level, error expected)
		result = testingParser.parseString("add homework -from 20 aug 1991 -to 10 sep 2014 -category testing -impt anything  -note extra points");
		Assert.assertEquals(false, result.getValidationResult());
		Assert.assertEquals(Constants.MESSAGE_INVALID_IMPORTANCE_PARAM, SummaryReport.getFeedBackMsg());
		
	}
	
	@Test
	public void testDisplay() {
		Parser testingParser = new Parser();
		ParsedResult result = new ParsedResult();
		
		//no deadline
		result = testingParser.parseString("display todo");
		Assert.assertEquals(true, result.getValidationResult());
		Assert.assertEquals(CommandType.DISPLAY, result.getCommandType());
		Assert.assertEquals(Constants.SOMEDAY, result.getTaskDetails().getDueDate());
		
		//with deadline
		result = testingParser.parseString("display 20/08/1991");
		Assert.assertEquals(true, result.getValidationResult());
		Assert.assertEquals(CommandType.DISPLAY, result.getCommandType());
		Assert.assertEquals("20/08/1991", result.getTaskDetails().getDueDate().toLocalDate().toString("dd/MM/yyyy"));
		
		//range of dates
		result = testingParser.parseString("display 20/08/1991 -to 10 sep 1991");
		Assert.assertEquals(true, result.getValidationResult());
		Assert.assertEquals(CommandType.DISPLAY, result.getCommandType());
		Assert.assertEquals("20/08/1991", result.getTaskDetails().getStartDate().toLocalDate().toString("dd/MM/yyyy"));
		Assert.assertEquals("10/09/1991", result.getTaskDetails().getDueDate().toLocalDate().toString("dd/MM/yyyy"));
		
	}

}
