package uiView;
/*
 * @author Paing Zin Oo(Jack)
 */
public interface UiParent {
	public void passToParser(String command);
	public void pressedF1();
	public void pressedF2();
	public void pressedF3();
	public void updateFrame();
	public void updateDetailPanel();
	public void pressedTab(boolean isCommandBox);
	public void setRowSelected(int rowSelected);

}
