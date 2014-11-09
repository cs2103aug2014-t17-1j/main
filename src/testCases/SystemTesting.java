package testCases;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import parser.ParsedResult;
import parser.Parser;
import taskDo.Executor;

import commonClasses.Constants;
import commonClasses.SummaryReport;

public class SystemTesting {
	@Test
	public void test() {
		//@author Paing Zin Oo(Jack)  A0112581N
		String command = "add new ";
		Parser parser = new Parser();
		Executor executor = new Executor();
		ParsedResult parseResult = parser.parseString(command);
		if(parseResult.getIsExecutorApplicable()){
			executor.execute(parseResult);
		}
		assertEquals(SummaryReport.getFeedBackMsg(), Constants.MESSAGE_SUCCESS_ADD);
		
		command = "delete 1";
		parseResult = parser.parseString(command);
		if(parseResult.getIsExecutorApplicable()){
			executor.execute(parseResult);
		}
		assertEquals(SummaryReport.getFeedBackMsg(), "TASK 1 IS DELETED");
		
		command = "fjsklfdklfje";
		parseResult = parser.parseString(command);
		if(parseResult.getIsExecutorApplicable()){
			executor.execute(parseResult);
		}
		assertEquals(SummaryReport.getFeedBackMsg(), Constants.MESSAGE_INVALID_COMMAND);
		
		command = "delete 50";
		parseResult = parser.parseString(command);
		if(parseResult.getIsExecutorApplicable()){
			executor.execute(parseResult);
		}
		assertEquals(SummaryReport.getFeedBackMsg(), Constants.MESSAGE_INVALID_SELECTION);
		
		command = "edit 50";
		parseResult = parser.parseString(command);
		if(parseResult.getIsExecutorApplicable()){
			executor.execute(parseResult);
		}
		assertEquals(SummaryReport.getFeedBackMsg(), Constants.MESSAGE_INVALID_SELECTION);
		
		command = "search abcdefghigklfje";
		parseResult = parser.parseString(command);
		if(parseResult.getIsExecutorApplicable()){
			executor.execute(parseResult);
		}
		assertEquals(SummaryReport.getFeedBackMsg(), Constants.MESSAGE_FAIL_SEARCH);
		
	}
}
