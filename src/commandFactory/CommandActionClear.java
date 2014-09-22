package commandFactory;


public class CommandActionClear implements CommandAction{
	@Override
	public void execute(){
		System.out.println("task is cleared");
	}
	
	public void undo(){
		System.out.println("undo clear");
	}
	
	public void updateSummaryReport(){
		System.out.println("updateSummaryReport -- clear");
	}
}
