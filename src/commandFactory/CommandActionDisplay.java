package commandFactory;


public class CommandActionDisplay implements CommandAction{
	@Override
	public void execute(){
		System.out.println("display task list <-- CommandActionDisplay.java");
	}
	@Override
	public void undo(){
		System.out.println("undo display <-- CommandActionDisplay.java");
	}
	@Override
	public void updateSummaryReport(){
		System.out.println("updateSummaryReport -- display <-- CommandActionDisplay.java");
	}
}
