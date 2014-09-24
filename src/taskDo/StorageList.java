package taskDo;

import java.util.ArrayList;

public class StorageList {
	private static StorageList storageList = new StorageList();
	private  ArrayList<Task> taskList = new ArrayList<Task>();
	
	private StorageList(){
		
	}
	
	public static StorageList getInstance(){
		return storageList;
		
	}
	
	public ArrayList<Task> getTaskList(){
		return taskList;
	}
	
	
}
