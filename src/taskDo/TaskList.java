package taskDo;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList = null;
    private TaskList() { }
    public static synchronized ArrayList<Task> getTaskList() {
        if (taskList == null) {
        	taskList = new ArrayList<Task>();
        }
        return taskList;
    }
}