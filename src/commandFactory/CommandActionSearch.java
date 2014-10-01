package commandFactory;

import java.util.ArrayList;
import java.util.Iterator;

import taskDo.ParsedResult;
//import taskDo.SearchType;
import taskDo.StorageList;
import taskDo.SummaryReport;
import taskDo.Task;

public class CommandActionSearch implements CommandAction {
	@Override
	public void execute(){
		switch(ParsedResult.getSearchMode()){
		case ID:
			searchByID();	// ID referring to taskID not ScreenID
			break;
		case KEYWORD:
			searchByKeyword();
			break;
		case CATEGORY:
			searchByCategory();
			break;
		case DATE:
			searchByDate();
			break;
		default: break;
		}
		System.out.println("added successfully <-- print from CommandActionSearch.java");
	}

	@Override
	public void undo(){
		System.out.println("CANNOT undo search <-- CommandActionSearch.java");
	}
	
	private void searchByID() {

	}
	
	private void searchByKeyword() {
		// TODO Auto-generated method stub
	}

	private void searchByCategory() {

	}
	
	private void searchByDate() {

	}
	
	@Override
	public void updateSummaryReport(){

	}
}



