package Parser;

import java.util.ArrayList;

import taskDo.Task;

import commandFactory.CommandAction;
import commonClasses.StorageList;

public class CommandActionSearch implements CommandAction{

	public void execute(ParsedResult parsedResult) {
		String searchInput = parsedResult.getTaskDetails().getDescription();
		
		//search by keyword function can refactor into search class
		ArrayList<Task> taskList = StorageList.getInstance().getTaskList();
		ArrayList<Task> searchResult = new ArrayList<Task>();
		for(int i=0;i<taskList.size();i++){
			if(taskList.get(i).getDescription().contains(searchInput)){
				searchResult.add(taskList.get(i));
			}
		}
		if(searchResult.isEmpty()) { //!st level search fail
			String[] splittedInput = searchInput.split(" ");
			for(int i=0;i<splittedInput.length;i++) {
				for(int j=0;j<taskList.size();j++){
					if(taskList.get(j).getDescription().contains(splittedInput[i])){
						searchResult.add(taskList.get(j));
					}
				}
			}
		}
		
		if(searchResult.isEmpty()) { //2nd level search fail
			String[] splittedInput = searchInput.split(" ");
			WagnerFischerSearch wfSearch = new WagnerFischerSearch();
			for(int i=0;i<splittedInput.length;i++) {
				for(int j=0;j<taskList.size();j++){
					for(int k=0;k<taskList.get(j).getDescription().length();k++) {
						String[] splittedDescription = taskList.get(j).getDescription().split(" ");
						int editDist = wfSearch.getEditDistance(splittedDescription[k], splittedInput[i]);
						if(editDist <= 2) {
							searchResult.add(taskList.get(j));
							break;
						}
					}
				}
			}
		}
		
	}

	public void undo(Task lastTask) {
		// TODO Auto-generated method stub
		
	}
	
	
}
