package TestCases;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import taskDo.Executor;
import taskDo.Task;
import Parser.ParsedResult;
import Parser.Parser;
import commonClasses.Constants;
import commonClasses.SummaryReport;

public class SystemTesting {
	@Test
	public void test() {
		String command = "add new ";
		Parser parser = new Parser();
		Executor executor = new Executor();
		ParsedResult parseResult = parser.parseString(command);
		if(parseResult.getValidationResult()){
			executor.execute(parseResult);
		}
		assertEquals(SummaryReport.getFeedBackMsg(), Constants.MESSAGE_SUCCESS_ADD);
		
		command = "delete 1";
		parseResult = parser.parseString(command);
		if(parseResult.getValidationResult()){
			executor.execute(parseResult);
		}
		assertEquals(SummaryReport.getFeedBackMsg(), Constants.MESSAGE_SUCCESS_DELETE);
		
		command = "fjsklfdklfje";
		parseResult = parser.parseString(command);
		if(parseResult.getValidationResult()){
			executor.execute(parseResult);
		}
		assertEquals(SummaryReport.getFeedBackMsg(), Constants.MESSAGE_INVALID_COMMAND);
		
		command = "delete 50";
		parseResult = parser.parseString(command);
		if(parseResult.getValidationResult()){
			executor.execute(parseResult);
		}
		assertEquals(SummaryReport.getFeedBackMsg(), Constants.MESSAGE_INVALID_SELECTION);
		
		command = "edit 50";
		parseResult = parser.parseString(command);
		if(parseResult.getValidationResult()){
			executor.execute(parseResult);
		}
		assertEquals(SummaryReport.getFeedBackMsg(), Constants.MESSAGE_INVALID_SELECTION);
		
		command = "search abcdefghigklfje";
		parseResult = parser.parseString(command);
		if(parseResult.getValidationResult()){
			executor.execute(parseResult);
		}
		assertEquals(SummaryReport.getFeedBackMsg(), Constants.MESSAGE_FAIL_SEARCH);
		
	}
}
