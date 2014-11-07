package commandFactory;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

import parser.ParsedResult;
import commonClasses.Constants;
import commonClasses.StorageList;
import taskDo.SearchType;
import taskDo.Task;
import taskDo.TaskType;

public class Search {
	int taskIndex;
	ArrayList<Task> returnList;
	SearchType searchType;
	
	private static final Logger log = LogManager.getLogger(Search.class);

	public Search(){
		taskIndex = -1;
		returnList = new ArrayList<Task>();
	}

	public int searchById(int id, ArrayList<Task> taskList){
		for(Task task: taskList){
			if(id == task.getId()){
				taskIndex = taskList.indexOf(task);
				break;
			}
		}
		return taskIndex;
	}
	
	public ArrayList<Task> searchForDisplay(ParsedResult parsedResult) {
		switch(parsedResult.getSearchMode()){
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
		for(Task task: StorageList.getInstance().getTaskList()){
			if(task.getCategory().toLowerCase().equals(parsedResult.getTaskDetails().getCategory().toLowerCase())){
				returnList.add(task);
			}
		}
		return returnList;
	}

	private ArrayList<Task> searchByAll() {
		for(Task task: StorageList.getInstance().getTaskList()){
			if(isNotCompleted(task)){
				returnList.add(task);
			}
		}
		return returnList;
	}

	public ArrayList<Task> searchByDate(ParsedResult parsedResult) {
		Task sourceTask = parsedResult.getTaskDetails();

		for(Task targetTask: StorageList.getInstance().getTaskList()){
			if(targetTask.getTaskType().equals(TaskType.TIMED) && isSourceTaskWithinTimedTask(sourceTask,targetTask)) {
				returnList.add(targetTask);
			} else if(isSameDueDate(sourceTask, targetTask)&& isNotCompleted(targetTask)){
				returnList.add(targetTask);
			}
		}
		return returnList;
	}

	private boolean isSourceTaskWithinTimedTask(Task sourceTask, Task targetTask) {
		//sourceTask isbefore targetTask duedate
		//source Task isafter targetTask start date
		boolean isBefore = sourceTask.getDueDate().toLocalDate().isBefore(targetTask.getDueDate().toLocalDate());
		boolean isAfter = sourceTask.getDueDate().toLocalDate().isAfter(targetTask.getStartDate().toLocalDate());
		
		return (isBefore && isAfter);
	}

	private boolean isSameDueDate(Task sourceTask, Task targetTask) {
		return targetTask.getDueDate().toLocalDate().equals(sourceTask.getDueDate().toLocalDate());
	}

	public ArrayList<Task> searchByRangeOfDates(ParsedResult parsedResult){
		Task sourceTask = parsedResult.getTaskDetails();
		for(Task targetTask: StorageList.getInstance().getTaskList()){
			if(isNotCompleted(targetTask)){
				if(targetTask.getTaskType().equals(TaskType.DEADLINE)){
					if(isDeadlineTaskWithinRange(sourceTask, targetTask)){
						returnList.add(targetTask);
					}
				}else if(targetTask.getTaskType().equals(TaskType.TIMED)){
					if(isTimedTaskWithinRange(sourceTask, targetTask)){
						returnList.add(targetTask);
					}
				}
			}
		}
		return returnList;
	}

	private boolean isTimedTaskWithinRange(Task sourceTask, Task targetTask) {
		boolean isNotBefore = !targetTask.getDueDate().toLocalDate().isBefore(sourceTask.getStartDate().toLocalDate());
		boolean isNotAfter = !targetTask.getStartDate().toLocalDate().isAfter(sourceTask.getDueDate().toLocalDate());
		return isNotBefore && isNotAfter;
	}

	private boolean isDeadlineTaskWithinRange(Task sourceTask, Task targetTask) {
		boolean isNotBefore = !targetTask.getDueDate().toLocalDate().isBefore(sourceTask.getStartDate().toLocalDate());
		boolean isNotAfter = !targetTask.getDueDate().toLocalDate().isAfter(sourceTask.getDueDate().toLocalDate());
		return isNotBefore && isNotAfter;
	}

	public ArrayList<Task> searchByKeyword(ParsedResult parsedResult){
		String searchInput = parsedResult.getTaskDetails().getTitle();
		log.info("Search Input [" + searchInput + "].");
		ArrayList<Task> taskList = StorageList.getInstance().getTaskList();
		String[] splittedInput = searchInput.split(" ");
		for(int i=0;i<splittedInput.length;i++) {
			for(int j=0;j<taskList.size();j++){
				if(isNotCompleted(taskList.get(j))){
					if(taskList.get(j).getTitle().contains(splittedInput[i])){
						returnList.add(taskList.get(j));
					}
				}
			}
		}

		if(returnList.isEmpty()) { //2nd level search fail
			WagnerFischerSearch wfSearch = new WagnerFischerSearch();
			for(int i=0;i<splittedInput.length;i++) {
				for(int j=0;j<taskList.size();j++){
					if(isNotCompleted(taskList.get(j))){
						String[] splittedDescription = taskList.get(j).getTitle().split(" ");
						for(int k=0;k<splittedDescription.length;k++) {
							int editDist = wfSearch.getEditDistance(splittedDescription[k].toLowerCase(), splittedInput[i].toLowerCase());
							if(editDist <= 2) {
								returnList.add(taskList.get(j));
								break;
							}
						}
					}
				}
			}
		}
		return returnList;
	}

	public ArrayList<Task> searchByCompleted(){
		for(Task task: StorageList.getInstance().getTaskList()){
			if(task.isCompleted()){
				returnList.add(task);
			}
		}
		return returnList;
	}

	private boolean isNotCompleted(Task targetTask) {
		return !targetTask.isCompleted();
	}

	public ArrayList<Task> searchByOverdue(){
		DateTime today = new DateTime();
		for(Task task: StorageList.getInstance().getTaskList()){
			if(isNotSomeday(task) && isNotCompleted(task) && task.getDueDate().isBefore(today))
				returnList.add(task);
		}
		return returnList;
	}

	private boolean isNotSomeday(Task task) {
		return task.getDueDate().toLocalDate().getYear()!= Constants.NILL_YEAR;
	}

	public ArrayList<Task> searchByOverdueAndToday() {
		DateTime today = new DateTime();
		
		for(Task task: StorageList.getInstance().getTaskList()){
			if(isNotSomeday(task) && isNotCompleted(task) && isNotAfterToday(task, today)){
				returnList.add(task);
			}
		}
		return returnList;
	}

	private boolean isNotAfterToday(Task task, DateTime today) {
		
		return !task.getDueDate().toLocalDate().isAfter(today.toLocalDate());
	}
}

