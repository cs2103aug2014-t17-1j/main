package commandFactory;


public class CommandActionDisplay implements CommandAction{
	@Override
	public void execute(){
		System.out.println("display task list");
	}
	
	public void undo(){
		System.out.println("undo display");
	}
	
	public void updateSummaryReport(){
		System.out.println("updateSummaryReport -- display");
	}
}
