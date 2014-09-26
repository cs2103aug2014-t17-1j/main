package commandFactory;

public class CommandActionEdit implements CommandAction {
	@Override
	public void execute(){
		System.out.println("edit <-- CommandActionEdit.java");
	}
	@Override
	public void undo(){
		System.out.println("undo edit <-- CommandActionEdit.java");
	}
	@Override
	public void updateSummaryReport(){
		System.out.println("updateSummaryReport -- Edit <-- CommandActionEdit.java");
	}
}
