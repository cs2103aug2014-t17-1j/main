package TestCases;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Test;

import taskDo.Executor;
import commonClasses.StorageList;
import taskDo.Task;
import commandFactory.CommandType;
import Parser.ParsedResult;

public class ExecutorTestCase {

	private ArrayList<Task> testArrayList = new ArrayList<Task>();
	private ParsedResult testParsedResult = new ParsedResult();
	private Executor executor = new Executor();
	private DateTime dueDate = new DateTime();

	@Test
	public void test() {
		testDeleteEmptyList();
		testAddTask1();
		testAddTask2();
		testDeleteTask1();
	}

	private void testDeleteEmptyList() {
		Task testTask = new Task();
		testTask.setDescription("");
		testTask.setDueDate(dueDate);
		testTask.setId(0);
		
		testParsedResult.setCommandType(CommandType.DELETE);
		testParsedResult.setTask(testTask);
		
		executor.execute(testParsedResult);
		
		assertEquals(StorageList.getInstance().getTaskList(), testArrayList);
	}
	private void testAddTask1() {
		Task testTask = new Task();
		
		testTask.setDescription("test task 1");
		testTask.setDueDate(dueDate);
		testTask.setId(0);
	
		testParsedResult.setCommandType(CommandType.ADD);
		testParsedResult.setTask(testTask);
	
		executor.execute(testParsedResult);
		
		testArrayList.add(testTask);
	
		assertEquals(StorageList.getInstance().getTaskList(), testArrayList);
	}

	private void testAddTask2() {
		Task testTask = new Task();
		
		testTask.setDescription("test task 2");
		testTask.setDueDate(dueDate);
		testTask.setId(1);
	
		testParsedResult.setCommandType(CommandType.ADD);
		testParsedResult.setTask(testTask);
	
		executor.execute(testParsedResult);
		
		testArrayList.add(testTask);
	
		assertEquals(StorageList.getInstance().getTaskList(), testArrayList);
	}
	
	private void testDeleteTask1() {
		Task testTask = new Task();
		testTask.setDescription("");
		testTask.setDueDate(dueDate);
		testTask.setId(0);
		
		testParsedResult.setCommandType(CommandType.DELETE);
		testParsedResult.setTask(testTask);
		
		executor.execute(testParsedResult);
		
		testArrayList.remove(0);
		
		assertEquals(StorageList.getInstance().getTaskList(), testArrayList);
	}
}