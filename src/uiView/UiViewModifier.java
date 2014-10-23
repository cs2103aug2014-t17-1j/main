package uiView;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

import taskDo.Executor;
import Parser.ParsedResult;
import Parser.Parser;

import commonClasses.Constants;
import commonClasses.SummaryReport;

/*
 * @author Paing Zin Oo(Jack)
 */
public class UiViewModifier implements WindowListener,UiParent{

	private JFrame mainFrame;

	private Executor executor; 

	private UIPanelList uiList;
	private HeaderPanel headerPanel;
	private ShorcutPanel helpPanel;
	private CommandBoxPanel commandBoxPanel;
	private DetailPanel detailPanel; 
	private ContentTablePanel contentPanel;
	private int rowSelected;
	private Parser parser;
	private ParsedResult parseResult;
	
	
	public UiViewModifier(){
		rowSelected = Constants.DEFAULT_ROW_SELECTED;
		parser = new Parser();
		executor = new Executor();
		mainFrame = new JFrame();
		mainFrame.setLayout(new BorderLayout());
		

		uiList = new UIPanelList();
		headerPanel = new HeaderPanel();
		mainFrame.add(headerPanel,BorderLayout.NORTH);
		
		helpPanel = new ShorcutPanel();
		mainFrame.add(helpPanel,BorderLayout.WEST);
		
		commandBoxPanel = new CommandBoxPanel(this);
		mainFrame.add(commandBoxPanel,BorderLayout.SOUTH);
		
		
		
		contentPanel = new ContentTablePanel(this);
		mainFrame.add(contentPanel,BorderLayout.CENTER);
		
		uiList.addUI(commandBoxPanel);
		uiList.addUI(headerPanel);
		uiList.addUI(contentPanel);

		
	    setJFrameProperties();
	    System.out.println(mainFrame.getFocusOwner());
	    if(mainFrame.getFocusOwner() instanceof JTextField ){
	    	System.out.println("Text Field ");
	    }
	}
	
	public void updateAllPanels(){
		uiList.notifyUIs();
	}
	
	public Component getCurrentFocusComponent(){
		return mainFrame.getFocusOwner();
	}
	
	public boolean isFocusOnCommandBox(){
		if(getCurrentFocusComponent() instanceof JTextField){
			return true;
		}
		return false;
	}
	
	public boolean isFocusOnJTable(){
		if(getCurrentFocusComponent() instanceof JTable){
			return true;
		}
		return false;
	}
	
	public void passToParser(String command){
		if(command!=null){
			parseResult = parser.parseString(command);
			if(parseResult.getValidationResult()){
				System.out.println("Parse String reached here");
				executor.execute(parseResult);
			}
			updateAllPanels();
		}
	}
	

	private void setJFrameProperties() {
		mainFrame.setTitle("Task.Do"); 
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
		mainFrame.pack();
		mainFrame.addWindowListener(this);
	}

	
	public void pressedF1(){
		if(isDetailPanelExisting()){
			mainFrame.remove(detailPanel);
		} else{
			createDetailPanel(HotKeyType.F1);
		}
		updateAllPanels();
	}
	

	
	public void pressedF2(){
		if(rowSelected != Constants.DEFAULT_ROW_SELECTED){
			if(isDetailPanelExisting()){
				mainFrame.remove(detailPanel);
			} else{
				createDetailPanel(HotKeyType.F2);
			}
			updateAllPanels();
			setFocus();
			updateFrame();
		}
	
	}
	
	public void pressedF3(){
		if(isDetailPanelExisting()){
			mainFrame.remove(detailPanel);
		} else {
			createDetailPanel(HotKeyType.F3);
		}
		updateAllPanels();
	}
	
	private void createDetailPanel(HotKeyType hotkey) {
		switch(hotkey){
			case F1:
				detailPanel = new DetailPanel(HotKeyType.F1);
				break;
			case F2:
				detailPanel = new DetailPanel(SummaryReport.getDisplayList().get(rowSelected));
				break;
			case F3:
				detailPanel = new DetailPanel(HotKeyType.F3);
				break;
			default:
				break;
				
		}
		mainFrame.add(detailPanel,BorderLayout.EAST);	
	}
	
	public void updateDetailPanel(){
		if(rowSelected != -1){
			System.out.println(rowSelected);
			if(isDetailPanelExisting()){
				mainFrame.remove(detailPanel);
			}
			detailPanel = new DetailPanel(SummaryReport.getDisplayList().get(rowSelected));
			mainFrame.add(detailPanel,BorderLayout.EAST);
			detailPanel.revalidate();
			updateFrame();
		}
	}

	private boolean isDetailPanelExisting(){
		if(detailPanel != null){
			if(detailPanel.isDisplayable()){
				return true;
			}
		}
		return false;
	}
	
	public void updateFrame() {
		mainFrame.pack();
		mainFrame.revalidate();
		mainFrame.repaint();
		
		
	}
	
	public void setFocus(){
		if(isFocusOnJTable()){
			System.out.println("FOCUS ON TABLE");
			contentPanel.highlightRow();
		}
		if(isFocusOnCommandBox()){
			System.out.println("FOCUS ON COMMANDBOX");
			commandBoxPanel.setFocusToCommandBox();
		}
	}

	public void setRowSelected (int selected){
		rowSelected = selected;
	}
	
	public void pressedTab(boolean isCommandBox) {
		if(isCommandBox){
			contentPanel.requestFocusInWindow();
		} else{
			commandBoxPanel.setFocusToCommandBox();
		}		
	}
	

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.exit(0);
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}
