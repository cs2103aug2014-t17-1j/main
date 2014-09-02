
import java.util.Calendar;


//This is the skeleton class specifying all attributes that each task should have
public class TaskDetails {
	
	private int serialNum;
	private String folder;
	private String taskDescription;  
	private String priorityField;
	private String details;
	private Calendar dateOfEntry;
	private Calendar deadLine;
	private Calendar reminder;



	public TaskDetails(int serialNum,String folder,String taskDescription,String details,String priorityField,Calendar dateOfEntry,Calendar deadLine,Calendar reminder)
	{
		setSerialNum(serialNum);
		setFolder(folder);
		setEvent(taskDescription);
		setDetails(details);
		setPriority(priorityField);
		setEntryDate(dateOfEntry);
		setDeadLine(deadLine);
		setReminder(reminder);
	}


	public int getSerialNum(){
		return this.serialNum;
	}
	public String getFolder(){
		return this.folder;
	}
	public String getTaskDescription(){
		return this.taskDescription;
	}
	public String getDetails(){
		return this.details;
	}
	public String getPriority(){
		return this.priorityField;
	}
	public Calendar getDateOfEntry(){
		return this.dateOfEntry;
	}
	public Calendar getDeadLine(){
		return this.deadLine;
	}
	public Calendar getReminder(){
		return this.reminder;
	}

//*********************Mutators***************************
	public void setSerialNum(int serialNum)
	{
		this.serialNum=serialNum;
	}
	public void setFolder(String folder){
		this.folder = folder;
	}
	public void setEvent(String taskDescription)
	{
		this.taskDescription = taskDescription;
	}
	public void setDetails(String details){
		this.details = details;
	}
	public void setPriority(String priorityField)
	{
		this.priorityField = priorityField;
	}

	public void setEntryDate(Calendar dateOfEntry)
	{
		this.dateOfEntry=dateOfEntry;
	}

	public void setDeadLine(Calendar deadLine)
	{
		this.deadLine=deadLine;
	}

	public void setReminder(Calendar reminder)
	{
		this.reminder=reminder;
	}


	public String toString()
	{
		return"[Serial: "+serialNum+" folder name:"+ this.getFolder()+ " Task:" +this.getTaskDescription()+ " Priority: "+this.getPriority()+ "Date of Entry" +getDateOfEntry().get(Calendar.DAY_OF_MONTH)+ " deadline "+ this.getDeadLine().get(Calendar.DAY_OF_MONTH)+ "]";
	}
}
