package commandFactory;

import taskDo.History;
import taskDo.StorageList;

public class UndoActionDelete implements UndoAction {

	@Override
	public void undo() {
		StorageList.getInstance().getTaskList().add(History.getTaskHistory().pop());
	}
}
