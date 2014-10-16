package commandFactory;

import taskDo.History;
import commonClasses.StorageList;
import taskDo.Task;

public class UndoActionEdit implements UndoAction {

	@Override
	public void undo() {
		Task historyTask = History.getTaskHistory().pop();
		Search search = new Search();
		
		search.searchById(historyTask.getId());
		StorageList.getInstance().getTaskList().remove(search.getTaskIndex());
		StorageList.getInstance().getTaskList().add(historyTask);
	}
}
