package taskDo;
import org.joda.time.DateTime;


public class Task implements Comparable<Task>{
	//@author Boo Tai Yi  A0111936J
	private final int INCREMENT = 1;
	private static int lastTaskId = 0;
	private int id;
	private String category;
	private String title;
	private String note;
	private boolean important;
	private DateTime dueDate;
	private DateTime startDate;
	private boolean completed;
	private TaskType type;
	
	public Task( String category, String description,
			boolean important, DateTime dueDate, DateTime startDate,
			boolean completed) {
		super();
		lastTaskId++;
		this.id = lastTaskId+INCREMENT;
		this.category = category;
		this.title = description;
		this.important = important;
		this.dueDate = dueDate;
		this.startDate = startDate;
		this.completed = completed;
	}
	
	public Task(){
		lastTaskId++;
		this.id = lastTaskId+INCREMENT;
		this.title = "";
		this.type = TaskType.TODO;
		this.category = "Others";
		this.note = "";
	}
	
	public Task(String description){
		this.title = description;
		
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	
	public TaskType getTaskType() {
		return this.type;
	}
	
	public void setTaskType(TaskType taskType) {
		this.type = taskType;
	}

	public String getNote() {
		return this.note;
	}
	
	public void setNote(String taskNote) {
		this.note = taskNote;
	}
	public String toString() {
		 return "ID" + this.id + "Category: "+ this.getCategory()+ " Task:"
		 +this.getTitle();
	}

	@Override
	public int compareTo(Task task) {
		 if (getDueDate() == null || task.getDueDate() == null){
			 return -1;
		 } else if(getDueDate().getYear() == 0) {
			 return 1;
		 } else if(task.getDueDate().getYear() == 0) {
			 return -1;
		 }
		 return getDueDate().compareTo(task.getDueDate());
	}
}
