package commandFactory;

public class CommandActionInit implements CommandAction {
	@Override
	public void execute(){
		System.out.println("init system <-- CommandActionInit.java");
	}
	@Override
	public void undo(){
		System.out.println("cannot undo init <-- CommandActionInit.java");
	}
	@Override
	public void updateSummaryReport(){
		System.out.println("updateSummaryReport -- init <-- CommandActionInit.java");
	}
}
