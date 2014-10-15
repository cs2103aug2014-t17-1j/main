package TestCases;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Test;

import taskDo.Executor;
import taskDo.StorageList;
import taskDo.Task;
import commandFactory.CommandType;
import Parser.ParsedResult;

public class ExecutorTestCase {

	@Test
	public void test() {
		ParsedResult testParsedResult = new ParsedResult();
		testParsedResult.setCommandType(CommandType.ADD);
		Task testTask = new Task();
		testTask.setDescription("test task 1");
		
		DateTime dueDate = new DateTime();
		testTask.setDueDate(dueDate);
		testParsedResult.setTask(testTask);
		Executor executor = new Executor();
		executor.execute(testParsedResult);
		
		ArrayList<Task> testArrayList = new ArrayList<Task>();
		testArrayList.add(testTask);
		assertEquals(StorageList.getInstance().getTaskList(), testArrayList);
	}
}
