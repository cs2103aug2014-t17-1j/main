package taskDo;

import java.util.ArrayList;

/*
 * @author Paing Zin Oo(Jack)
 */
public class StorageList {
	private static StorageList storageList = new StorageList();
	private ArrayList<Task> mainTaskList = new ArrayList<Task>();
	
	private StorageList(){
		
	}
	
	public static StorageList getInstance(){
		return storageList;
		
	}
	
	public ArrayList<Task> getTaskList(){
		return mainTaskList;
	}
	
	public void setTaskList(ArrayList<Task> taskList){
		this.mainTaskList = taskList;
	}
	
	
}
