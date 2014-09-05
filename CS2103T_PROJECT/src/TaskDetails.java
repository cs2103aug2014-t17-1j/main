
import java.util.Calendar;

//This is the skeleton class specifying all attributes that each task should have//TAI YI IN THE HOUSE!!!
//HUANG LI TESTING
public class TaskDetails {
	
	private String catogory;
	private String taskDescription;  
	private String priorityField;
	private Calendar deadLine;
	private boolean isCompleted;



	public TaskDetails(String catogory,String taskDescription,String priorityField,Calendar deadLine,boolean isCompleted)
	{
		setCatogory(catogory);
		setEvent(taskDescription);
		setPriority(priorityField);
		setDeadLine(deadLine);
		setStatus(isCompleted);
	}

	public String getCatogory(){
		return this.catogory;
	}
	public String getTaskDescription(){
		return this.taskDescription;
	}
	public String getPriority(){
		return this.priorityField;
	}
	public Calendar getDeadLine(){
		return this.deadLine;
	}
	public boolean getStatus(){
		return this.isCompleted;
	}

//*********************Mutators***************************

	public void setCatogory(String catogory){
		this.catogory = catogory;
	}
	public void setEvent(String taskDescription)
	{
		this.taskDescription = taskDescription;
	}
	public void setPriority(String priorityField)
	{
		this.priorityField = priorityField;
	}
	public void setDeadLine(Calendar deadLine)
	{
		this.deadLine=deadLine;
	}

	public void setStatus(boolean isCompleted)
	{
		this.isCompleted=isCompleted;
	}


	public String toString()
	{
		return"[Catogory: "+ this.getCatogory()+ " Task:" +this.getTaskDescription()+ " Priority: "+this.getPriority()+ "Deadline "+ this.getDeadLine().get(Calendar.DAY_OF_MONTH)+ "Status: "+this.getStatus()+"]";
	}
	

}
