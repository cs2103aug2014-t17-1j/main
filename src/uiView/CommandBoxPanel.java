package uiView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import commonClasses.SummaryReport;

public class CommandBoxPanel extends JPanel implements KeyListener,Observer{
	private int typeCount;
	private JTextField commandBox;
	private String command;
	private JLabel feedBackMsg;
	
	public CommandBoxPanel(){
		setLayout(new BorderLayout());
		initFeedBackMsg();
		initCommandBox();
		add(feedBackMsg,BorderLayout.NORTH);
	    add(commandBox,BorderLayout.SOUTH);
	    setBackground(Color.BLACK);
	    
	}

	private void initFeedBackMsg() {
		feedBackMsg = new JLabel("",JLabel.LEFT);
		feedBackMsg.validate();
		feedBackMsg.setForeground(ColorBox.colorPool[24]);
	}
	
	public void setFocusToCommandBox(){
		commandBox.requestFocusInWindow();
	}
	
	
	private void initCommandBox() {
		commandBox = new JTextField();
		if(typeCount == 0 ){
			setIntroTextInCommandBox();
		}
		
		commandBox.requestFocusInWindow();
	    commandBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				 command= commandBox.getText();
				 UiViewModifier.parseToParser(command);
				 commandBox.setText("");
				 System.out.println(command);
			}
	    	
	    });
	   commandBox.addKeyListener(this);
	}

	
	private void setIntroTextInCommandBox() {
		commandBox.setForeground(Color.GRAY);
		commandBox.setText("Enter your command here" );
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(typeCount == 0){
			commandBox.setText("");
			commandBox.setForeground(Color.BLACK);
		}
		typeCount++;
		
		
		if(arg0.getKeyCode()==KeyEvent.VK_F1){
			System.out.println("you have entered F1");
				UiViewModifier.pressedF1();
		}
//		if(arg0.getKeyCode() == KeyEvent.VK_TAB){
//			System.out.println("COMMAND BOX TAB IS PRESSED");
//			UiViewModifier.pressedTab(true);
//		}
//		if(arg0.getKeyCode()==KeyEvent.VK_F2){
//			mainFrame.remove(rightDetailPanel);
//			refreshFrame();
//		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		String text_feedBack = SummaryReport.getFeedBackMsg();
		// TODO Auto-generated method stub
		if(text_feedBack == null){
			feedBackMsg.setText("");
		} else{
			feedBackMsg.setText(SummaryReport.getFeedBackMsg());
		}
		
	}
}
