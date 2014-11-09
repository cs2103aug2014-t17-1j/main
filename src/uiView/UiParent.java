package uiView;

import javax.swing.JFrame;

/* The interface for all the panels 
 *
 */
public interface UiParent {
	// @author Paing Zin Oo(Jack) A0112581N
	public void passToParser(String command);

	public void pressedF1();

	public void pressedF3();

	public void updateFrame();

	public void updateDetailPanel();

	public void pressedTab(boolean isCommandBox);

	public void setRowSelected(int rowSelected);

	public JFrame getMainFrame();

	public void removeDetailPanel();

	public void setVisible(boolean isVisible);

}
