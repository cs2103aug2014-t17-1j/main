package commandFactory;


public class DeleteCommandAction implements CommandAction{
	@Override
	public void execute(){
		System.out.println("task is deleted");
	}
	
	public void undo(){
		System.out.println("undo delete");
	}
	
	public void updateSummaryReport(){
		System.out.println("updateSummaryReport -- delete");
	}
}
