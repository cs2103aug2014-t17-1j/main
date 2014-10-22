package commonClasses;

import java.util.ArrayList;

import readAndWriteFile.ConvertToJson;
import readAndWriteFile.ReadAndWriteToFile;
import taskDo.Category;
import taskDo.Task;

/*
 * @author Paing Zin Oo(Jack)
 */
public class StorageList {
	private static StorageList storageList;
	private ArrayList<Task> mainTaskList;
	private ArrayList<Category> mainCategoryList;
	private ReadAndWriteToFile readWrite;
	private ConvertToJson convertTojson;
	
	
	private StorageList(){
		 mainTaskList = new ArrayList<Task>();
		 readWrite = new ReadAndWriteToFile();
		 convertTojson = new ConvertToJson();
		 mainCategoryList = new ArrayList<Category>();
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
		mainTaskList = readWrite.readTaskFromFile();
	
	}
	
	public void saveToFile(){
		System.out.println("SIZE IS "+mainTaskList.size());
		convertTojson.setCategoryList(mainCategoryList);
		convertTojson.setTaskList(mainTaskList);
		readWrite.setjSonText(convertTojson.changeToJSonObj(true));
		readWrite.writeToFile(true);
		readWrite.setjSonText(convertTojson.changeToJSonObj(false));
		readWrite.writeToFile(false);
	}
	
	
}
