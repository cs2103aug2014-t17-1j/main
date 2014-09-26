package commandFactory;

public class CommandActionSave implements CommandAction {
	@Override
	public void execute(){
		System.out.println("Save <-- CommandActionSave.java");
	}
	@Override
	public void undo(){
		System.out.println("cannot undo Save <-- CommandActionSave.java");
	}
	@Override
	public void updateSummaryReport(){
		System.out.println("updateSummaryReport -- Save <-- CommandActionSave.java");
	}
}
