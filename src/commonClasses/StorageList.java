package commonClasses;

import java.util.ArrayList;

import readAndWriteFile.ConvertToJson;
import readAndWriteFile.ReadAndWriteToFile;
import taskDo.CategoryList;
import taskDo.Task;

/*
 *  The storage for all the tasks list
 */
public class StorageList {
	// @author Paing Zin Oo(Jack) A0112581N
	private static StorageList storageList;
	private ArrayList<Task> mainTaskList;
	private ReadAndWriteToFile readWrite;
	private ConvertToJson convertTojson;

	private StorageList() {
		mainTaskList = new ArrayList<Task>();
		readWrite = new ReadAndWriteToFile();
		convertTojson = new ConvertToJson();
	}

	public static StorageList getInstance() {
		if (storageList == null) {
			storageList = new StorageList();
		}
		return storageList;

	}

	public ArrayList<Task> getTaskList() {
		return mainTaskList;
	}

	public void setTaskList(ArrayList<Task> taskList) {
		this.mainTaskList = taskList;
	}

	public void loadFile() {
		mainTaskList = readWrite.readTasksFromFile();
		CategoryList.getCategoryList();
		CategoryList.updateCategoryList(mainTaskList);
	}

	public void saveToFile() {
		saveTasksToFile();
	}

	private void saveTasksToFile() {
		convertTojson.setTaskList(mainTaskList);
		readWrite.setjSonText(convertTojson.changeToJsonObj(true));
		readWrite.writeToFile(true);
	}

}
