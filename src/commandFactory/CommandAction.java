package commandFactory;

public interface CommandAction {
	public void execute();
	public void undo();
	public void updateSummaryReport();
}