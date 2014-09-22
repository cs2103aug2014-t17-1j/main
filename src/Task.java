import java.util.Calendar;

//This is the skeleton class specifying all attributes that each task should have
public class Task implements Comparable<Task>{
	//test
	private int id;
	private String category;
	private String description;
	private boolean important;
	private Calendar dueDate;
	private Calendar startDate;
	private boolean completed;
	
	
	@SuppressWarnings("null")
	public Task() {
		this.id = (Integer) null;
		this.category = null;
		this.description = null;
		this.important = (Boolean) null;
		this.dueDate = null;
		this.startDate = null;
		this.completed = (Boolean) null;
	}
	
	public Task(int id, String catogory, String description,
			boolean important, Calendar dueDate, Calendar startDate,
			boolean completed) {
		this.id = id;
		this.category = catogory;
		this.description = description;
		this.important = important;
		this.dueDate = dueDate;
		this.startDate = startDate;
		this.completed = completed;
	}
	
	/*
	 * Testing purpose
	 */
	public Task(String description){
		this.description = description;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCatogory() {
		return category;
	}

	public void setCatogory(String catogory) {
		this.category = catogory;
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

	public Calendar getDueDate() {
		return dueDate;
	}

	public void setDueDate(Calendar dueDate) {
		this.dueDate = dueDate;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public String toString() {
		// return"[Catogory: "+ this.getCatogory()+ " Task:"
		// +this.getTaskDescription()+ " Priority: "+this.getPriority()+
		// "dueDate "+ this.getDeadLine().get(Calendar.DAY_OF_MONTH)+
		// "Status: "+this.getStatus()+"]";
		return "test string";
	}

	@Override
	public int compareTo(Task task) {
		
		return 0;
	}

}
