package uiView;

import java.util.ArrayList;

public class UIPanelList {
	private ArrayList<Observer> uiList;
	
	public UIPanelList(){
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
