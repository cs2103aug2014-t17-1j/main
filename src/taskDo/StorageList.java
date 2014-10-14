package taskDo;

import java.util.ArrayList;

import readAndWriteFile.ReadAndWriteToFile;

/*
 * @author Paing Zin Oo(Jack)
 */
public class StorageList {
	private static StorageList storageList;
	private ArrayList<Task> mainTaskList;
	private ReadAndWriteToFile readWrite; 
	
	private StorageList(){
		 mainTaskList = new ArrayList<Task>();
		 readWrite = new ReadAndWriteToFile();
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
		mainTaskList = readWrite.readFromFile();
	}
	
	public void saveToFile(){
		readWrite.writeToFile();
	}
	
	
}
