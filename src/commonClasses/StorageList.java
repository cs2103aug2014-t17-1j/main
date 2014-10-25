package commonClasses;

import java.util.ArrayList;

import readAndWriteFile.ConvertToJson;
import readAndWriteFile.ReadAndWriteToFile;
import taskDo.CategoryList;
import taskDo.Task;

/*
 * @author Paing Zin Oo(Jack)
 */
public class StorageList {
	private static StorageList storageList;
	private ArrayList<Task> mainTaskList;
	//private ArrayList<Category> mainCategoryList;
	private ReadAndWriteToFile readWrite;
	private ConvertToJson convertTojson;
	
	private StorageList(){
		 mainTaskList = new ArrayList<Task>();
		// mainCategoryList = new ArrayList<Category>();
		 readWrite = new ReadAndWriteToFile();
		 convertTojson = new ConvertToJson();
	}
	
	public static StorageList getInstance(){
		if(storageList == null){
			storageList = new StorageList();
		}
		return storageList;
		
	}
	
	public ArrayList<Task> getTaskList(){
		return mainTaskList;
	}
	
	public void setTaskList(ArrayList<Task> taskList){
		this.mainTaskList = taskList;
	}
	
	public void loadFile(){
		mainTaskList = readWrite.readTasksFromFile();
		CategoryList.getCategoryList();
		CategoryList.updateCategoryList(mainTaskList);
		//mainCategoryList = readWrite.readCategoriesFromFile();
	}
	
	public void saveToFile(){
		System.out.println("SIZE IS "+mainTaskList.size());
		saveTasksToFile();
		//saveCategoriesToFile();
	}

//	private void saveCategoriesToFile() {
//		convertTojson.setCategoryList(mainCategoryList);
//		readWrite.setjSonText(convertTojson.changeToJSonObj(false));
//		readWrite.writeToFile(false);
//	}

	private void saveTasksToFile() {
		convertTojson.setTaskList(mainTaskList);
		readWrite.setjSonText(convertTojson.changeToJsonObj(true));
		readWrite.writeToFile(true);
	}
	
	
}
