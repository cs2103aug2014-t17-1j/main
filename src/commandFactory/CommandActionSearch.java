package commandFactory;

import java.util.ArrayList;

import Parser.ParsedResult;
import taskDo.Task;
import commonClasses.StorageList;

public class CommandActionSearch implements CommandAction{

	public void execute(ParsedResult parsedResult) {
		String searchInput = parsedResult.getTaskDetails().getTitle();
		
		ArrayList<Task> taskList = StorageList.getInstance().getTaskList();
		ArrayList<Task> searchResult = new ArrayList<Task>();

			String[] splittedInput = searchInput.split(" ");
			for(int i=0;i<splittedInput.length;i++) {
				for(int j=0;j<taskList.size();j++){
					if(taskList.get(j).getTitle().contains(splittedInput[i])){
						searchResult.add(taskList.get(j));
					}
				}
			}
		
		if(searchResult.isEmpty()) { //2nd level search fail
			WagnerFischerSearch wfSearch = new WagnerFischerSearch();
			for(int i=0;i<splittedInput.length;i++) {
				for(int j=0;j<taskList.size();j++){
					for(int k=0;k<taskList.get(j).getTitle().length();k++) {
						String[] splittedDescription = taskList.get(j).getTitle().split(" ");
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
