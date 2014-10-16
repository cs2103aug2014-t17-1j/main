package commandFactory;

import taskDo.History;
import commonClasses.StorageList;

public class UndoActionAdd implements UndoAction {

	@Override
	public void undo() {
		Search search = new Search();
		search.searchById(History.getTaskHistory().pop().getId());
		StorageList.getInstance().getTaskList().remove(search.getTaskIndex());
	}
}
