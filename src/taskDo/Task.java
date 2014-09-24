package taskDo;
import java.util.Date;

/*
 * @author Paing Zin Oo(Jack)
 */
public class Task implements Comparable<Task>{
	//test
	private final int INCREMENT = 1;
	private static int lastTaskId = 0;
	private int id;
	private String catogory;
	private String description;
	private boolean important;
	private Date dueDate;
	private Date startDate;
	private boolean completed;

	public Task( String catogory, String description,
			boolean important, Date dueDate, Date startDate,
			boolean completed) {
		super();
		lastTaskId++;
		this.id = lastTaskId+INCREMENT;
		this.catogory = catogory;
		this.description = description;
		this.important = important;
		this.dueDate = dueDate;
		this.startDate = startDate;
		this.completed = completed;
	}
	
	public Task(){
		this.id = lastTaskId+INCREMENT;
		this.description = "TESTING";
	}
	
	public Task(String description){
		this.description = description;
		
	}
	
	public static int getLastTaskId(){
		return lastTaskId;
	}
	
	//for testing only
	public Task (Date dueDate){
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

	public String getCatogory() {
		return catogory;
	}

	public void setCatogory(String catogory) {
		this.catogory = catogory;
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

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public String toString() {
		 return "ID" + this.id + "[Catogory: "+ this.getCatogory()+ " Task:"
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
