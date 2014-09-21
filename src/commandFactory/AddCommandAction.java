package commandFactory;


public class AddCommandAction implements CommandAction{
	@Override
	public void execute(){
		System.out.println("task is added");
	}
	
	public void undo(){
		System.out.println("undo add");
	}
	
	public void updateSummaryReport(){
		System.out.println("updateSummaryReport -- add");
	}
}
