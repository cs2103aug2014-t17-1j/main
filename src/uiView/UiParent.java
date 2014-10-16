package uiView;

public interface UiParent {
	public void passToParser(String command);
	public void pressedF1();
	public void pressedF2();
	public void pressedTab(boolean isCommandBox);
	public void setRowSelected(int rowSelected);
}
