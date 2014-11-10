package taskDo;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

import parser.ParsedResult;
import commonClasses.Constants;
import commonClasses.StorageList;

/**
 * Search is a help class for command executing
 * and also update SummaryReport
 *
 */
public class Search {
	// @Author  A0112508R
	ArrayList<Task> returnList;
	SearchType searchType;
	int taskIndex;

	private static final Logger log = LogManager.getLogger(Search.class);

	public Search() {
		taskIndex = -1;
		returnList = new ArrayList<Task>();
	}

	public int searchById(int id, ArrayList<Task> taskList) {
		for (Task task : taskList) {
			if (id == task.getId()) {
				taskIndex = taskList.indexOf(task);
				break;
			}
		}
		return taskIndex;
	}

	public ArrayList<Task> searchForDisplay(ParsedResult parsedResult) {
		switch (parsedResult.getSearchMode()) {
			case ALL:
				return searchByAll();
			case DATE:
				return searchByDate(parsedResult);
			case RANGEOFDATES:
				return searchByRangeOfDates(parsedResult);
			case COMPLETED:
				return searchByCompleted();
			case CATEGORY:
				return searchByCategory(parsedResult);
			case KEYWORD:
				return searchByKeyword(parsedResult);
			case OVERDUE:
				return searchByOverdue();
			default:
				return null;
		}
	}

	private ArrayList<Task> searchByCategory(ParsedResult parsedResult) {
		String category = parsedResult.getTaskDetails().getCategory()
				.toLowerCase();
		for (Task task : StorageList.getInstance().getTaskList()) {
			if (task.getCategory().toLowerCase().equals(category)) {
				returnList.add(task);
			}
		}
		return returnList;
	}

	private ArrayList<Task> searchByAll() {
		for (Task task : StorageList.getInstance().getTaskList()) {
			if (isNotCompleted(task)) {
				returnList.add(task);
			}
		}
		return returnList;
	}

	public ArrayList<Task> searchByDate(ParsedResult parsedResult) {
		Task parsedTask = parsedResult.getTaskDetails();

		for (Task task : StorageList.getInstance().getTaskList()) {
			if (isTimedTask(task) && isWithinTimedTask(parsedTask, task)) {
				returnList.add(task);
			} else if (isSameDueDate(parsedTask, task) && isNotCompleted(task)) {
				returnList.add(task);
			}
		}
		return returnList;
	}

	private boolean isTimedTask(Task targetTask) {
		return targetTask.getTaskType().equals(TaskType.TIMED);
	}

	private boolean isWithinTimedTask(Task parsedTask, Task task) {
		// sourceTask isbefore targetTask duedate
		// source Task isafter targetTask start date
		DateTime dueDate = parsedTask.getDueDate();

		boolean isBefore = dueDate.toLocalDate().isBefore(
				task.getDueDate().toLocalDate());
		boolean isAfter = dueDate.toLocalDate().isAfter(
				task.getStartDate().toLocalDate());

		return (isBefore && isAfter);
	}

	private boolean isSameDueDate(Task sourceTask, Task targetTask) {
		DateTime target = targetTask.getDueDate();
		DateTime source = sourceTask.getDueDate();

		return target.toLocalDate().equals(source.toLocalDate());
	}

	public ArrayList<Task> searchByRangeOfDates(ParsedResult parsedResult) {
		Task sourceTask = parsedResult.getTaskDetails();

		for (Task targetTask : StorageList.getInstance().getTaskList()) {
			if (isNotCompleted(targetTask)) {
				addValidTask(sourceTask, targetTask);
			}
		}
		return returnList;
	}

	private void addValidTask(Task sourceTask, Task targetTask) {
		if (targetTask.getTaskType().equals(TaskType.DEADLINE)) {
			addDeadlineTask(sourceTask, targetTask);
		} else if (isTimedTask(targetTask)) {
			addTimedTask(sourceTask, targetTask);
		}
	}

	private void addTimedTask(Task sourceTask, Task targetTask) {
		if (isTimedTaskWithinRange(sourceTask, targetTask)) {
			returnList.add(targetTask);
		}
	}

	private void addDeadlineTask(Task sourceTask, Task targetTask) {
		if (isDeadlineTaskWithinRange(sourceTask, targetTask)) {
			returnList.add(targetTask);
		}
	}

	private boolean isTimedTaskWithinRange(Task sourceTask, Task targetTask) {
		boolean isNotBefore = !targetTask.getDueDate().toLocalDate()
				.isBefore(sourceTask.getStartDate().toLocalDate());
		boolean isNotAfter = !targetTask.getStartDate().toLocalDate()
				.isAfter(sourceTask.getDueDate().toLocalDate());
		return isNotBefore && isNotAfter;
	}

	private boolean isDeadlineTaskWithinRange(Task sourceTask, Task targetTask) {
		boolean isNotBefore = !targetTask.getDueDate().toLocalDate()
				.isBefore(sourceTask.getStartDate().toLocalDate());
		boolean isNotAfter = !targetTask.getDueDate().toLocalDate()
				.isAfter(sourceTask.getDueDate().toLocalDate());
		return isNotBefore && isNotAfter;
	}

	// @author  A0111936J
	public ArrayList<Task> searchByKeyword(ParsedResult parsedResult) {
		ArrayList<Task> taskList = StorageList.getInstance().getTaskList();
		String searchInput = parsedResult.getTaskDetails().getTitle();
		String[] splittedInput = searchInput.split(" ");

		log.info("Search Input [" + searchInput + "].");

		for (int charIdx = 0; charIdx < splittedInput.length; charIdx++) {
			addLevelOneValidTasks(taskList, splittedInput, charIdx);
		}

		if (returnList.isEmpty()) { // 2nd level search fail
			WagnerFischerSearch wfSearch = new WagnerFischerSearch();
			for (int charIdx = 0; charIdx < splittedInput.length; charIdx++) {
				searchForValidTasks(taskList, splittedInput, wfSearch, charIdx);
			}
		}
		return returnList;
	}

	private void addLevelOneValidTasks(ArrayList<Task> taskList,
			String[] splittedInput, int charIdx) {
		for (int taskIdx = 0; taskIdx < taskList.size(); taskIdx++) {
			if (isNotCompleted(taskList.get(taskIdx))) {
				if (taskList.get(taskIdx).getTitle()
						.contains(splittedInput[charIdx])) {
					returnList.add(taskList.get(taskIdx));
				}
			}
		}
	}

	private void searchForValidTasks(ArrayList<Task> taskList,
			String[] splittedInput, WagnerFischerSearch wfSearch, int charIdx) {
		for (int taskIdx = 0; taskIdx < taskList.size(); taskIdx++) {
			if (isNotCompleted(taskList.get(taskIdx))) {
				String[] splittedDescription = taskList.get(taskIdx).getTitle()
						.split(" ");
				addValidTasks(taskList, splittedInput, wfSearch, charIdx,
						taskIdx, splittedDescription);
			}
		}
	}

	private void addValidTasks(ArrayList<Task> taskList,
			String[] splittedInput, WagnerFischerSearch wfSearch, int charIdx,
			int taskIdx, String[] splittedDescription) {
		for (int splitIdx = 0; splitIdx < splittedDescription.length; splitIdx++) {
			int editDist = wfSearch.getEditDistance(
					splittedDescription[splitIdx].toLowerCase(),
					splittedInput[charIdx].toLowerCase());
			if (editDist <= 2) {
				returnList.add(taskList.get(taskIdx));
				break;
			}
		}
	}

	// @Author  A0112508R
	public ArrayList<Task> searchByCompleted() {
		for (Task task : StorageList.getInstance().getTaskList()) {
			if (task.isCompleted()) {
				returnList.add(task);
			}
		}
		return returnList;
	}

	public ArrayList<Task> searchByOverdue() {
		DateTime today = new DateTime();

		for (Task task : StorageList.getInstance().getTaskList()) {
			boolean isOverDue = task.getDueDate().isBefore(today);
			if (isNotSomeday(task) && isNotCompleted(task) && isOverDue)
				returnList.add(task);
		}
		return returnList;
	}

	public ArrayList<Task> searchByOverdueAndToday() {
		DateTime today = new DateTime();

		for (Task task : StorageList.getInstance().getTaskList()) {
			boolean isNotAfterToday = !task.getDueDate().toLocalDate()
					.isAfter(today.toLocalDate());
			if (isNotSomeday(task) && isNotCompleted(task) && isNotAfterToday) {
				returnList.add(task);
			}
		}
		return returnList;
	}

	private boolean isNotSomeday(Task task) {
		return task.getDueDate().toLocalDate().getYear() != Constants.NILL_YEAR;
	}

	private boolean isNotCompleted(Task targetTask) {
		return !targetTask.isCompleted();
	}
}
