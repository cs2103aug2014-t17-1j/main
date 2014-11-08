package uiView;

import java.util.ArrayList;
/* This class is to store the panel that needs to be updated
 * 
 * @author Paing Zin Oo(Jack)  A0112581N
 */
public class UiPanelList {
	private ArrayList<Observer> uiList;
	
	//@author Paing Zin Oo(Jack)  A0112581N
	public UiPanelList(){
		uiList = new ArrayList<Observer>();
	}
	public void addUI(Observer o){
		uiList.add(o);
	}
	public void notifyUIs(){
		
		for(Observer o: uiList){
			o.update();
		}
	}
}
