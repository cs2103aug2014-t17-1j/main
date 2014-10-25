package commandFactory;

import java.util.ArrayList;

import Parser.ParsedResult;
import commonClasses.StorageList;
import taskDo.SearchType;
import taskDo.Task;
import taskDo.TaskType;

public class Search {
	int taskIndex;
	ArrayList<Task> returnList;
	SearchType searchType;

	public Search(){
		taskIndex = -1;
		returnList = new ArrayList<Task>();
	}

	public int searchById(int id){
		assert !StorageList.getInstance().getTaskList().isEmpty();
		for(Task taskIterator: StorageList.getInstance().getTaskList()){
			if(id == taskIterator.getId()){
				taskIndex = StorageList.getInstance().getTaskList().indexOf(taskIterator);
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
			return searchByCompleted(parsedResult);
		default:
			return null;
		}
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
			if(isSameDueDate(sourceTask, targetTask)&& isNotCompleted(targetTask)){
				returnList.add(targetTask);
			}
		}
		return returnList;
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
						for(int k=0;k<taskList.get(j).getTitle().length();k++) {
							String[] splittedDescription = taskList.get(j).getTitle().split(" ");
							int editDist = wfSearch.getEditDistance(splittedDescription[k], splittedInput[i]);
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

	public ArrayList<Task> searchByCompleted(ParsedResult parsedResult){
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
}

