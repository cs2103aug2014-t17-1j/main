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

	public int getTaskIndex() {
		return taskIndex;
	}

	public void searchById(int id){
		assert !StorageList.getInstance().getTaskList().isEmpty();
		for(Task taskIterator: StorageList.getInstance().getTaskList()){
			if(id == taskIterator.getId()){
				taskIndex = StorageList.getInstance().getTaskList().indexOf(taskIterator);
				break;
			}
		}
	}

	public ArrayList<Task> searchByDate(ParsedResult parsedResult) {
		Task sourceTask = parsedResult.getTaskDetails();
		
		for(Task targetTask: StorageList.getInstance().getTaskList()){
			if(isSameDueDate(sourceTask, targetTask)){
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
}
