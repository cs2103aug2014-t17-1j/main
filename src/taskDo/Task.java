package taskDo;
import java.util.Date;

import org.joda.time.DateTime;

/*
 * @author Paing Zin Oo(Jack)
 */
public class Task implements Comparable<Task>{
	//test
	private final int INCREMENT = 1;
	private static int lastTaskId = 0;
	private int id;
	private String category;
	private String description;
	private boolean important;
	private DateTime dueDate;
	private DateTime startDate;
	private boolean completed;

	public Task( String category, String description,
			boolean important, DateTime dueDate, DateTime startDate,
			boolean completed) {
		super();
		lastTaskId++;
		this.id = lastTaskId+INCREMENT;
		this.category = category;
		this.description = description;
		this.important = important;
		this.dueDate = dueDate;
		this.startDate = startDate;
		this.completed = completed;
	}
	
	public Task(){
		lastTaskId++;
		this.id = lastTaskId+INCREMENT;
		this.description = "";
	}
	
	public Task(String description){
		this.description = description;
		
	}
	
	public static int getLastTaskId(){
		return lastTaskId;
	}
	
	//for testing only
	public Task (DateTime dueDate){
		this.id=lastTaskId+1;
		lastTaskId++;
		this.dueDate = dueDate;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isImportant() {
		return important;
	}

	public void setImportant(boolean important) {
		this.important = important;
	}

	public DateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(DateTime dueDate) {
		this.dueDate = dueDate;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public String toString() {
		 return "ID" + this.id + "[Catogory: "+ this.getCategory()+ " Task:"
		 +this.getDescription();
//		 + " Priority: "+this.getPriority()+
//		 "dueDate "+ this.getDeadLine().get(Calendar.DAY_OF_MONTH)+
//		 "Status: "+this.getStatus()+"]";
	}

	@Override
	public int compareTo(Task task) {
		 if (getDueDate() == null || task.getDueDate() == null){
			 return -1;
		 }
		 return getDueDate().compareTo(task.getDueDate());
	}
}
