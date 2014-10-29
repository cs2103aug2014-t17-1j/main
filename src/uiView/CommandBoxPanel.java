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
import javax.swing.border.EmptyBorder;

import commonClasses.Constants;
import commonClasses.SummaryReport;
/*
 * @author Paing Zin Oo(Jack)
 */
public class CommandBoxPanel extends JPanel implements KeyListener,Observer{
	private int typeCount;
	private JTextField commandBox;
	private String command;
	private JLabel feedbackMsg;
	private UiParent parent;
	private CommandStack commandStack;
	
	public CommandBoxPanel(UiParent parent){
		commandStack = new CommandStack();
		this.parent = parent;
		setLayout(new BorderLayout());
		initFeedbackMsg();
		initCommandBox();
		add(feedbackMsg,BorderLayout.SOUTH);
	    add(commandBox,BorderLayout.NORTH);
	    setBackground(Constants.COLOR_COMMAND_PANEL_BG);
	    
	}

	private void initFeedbackMsg() {
		assert feedbackMsg !=null;
		String feedBack = SummaryReport.getFeedBackMsg();
		feedbackMsg = new JLabel(feedBack,JLabel.LEFT);
		feedbackMsg.validate();
		feedbackMsg.setForeground(Constants.COLOR_COMMAND_PANEL_TEXT);
	}
	
	public void setFocusToCommandBox(){
		commandBox.requestFocusInWindow();
	}
	
	
	private void initCommandBox() {
		commandBox = new JTextField();
		commandBox.setBorder(new EmptyBorder(10,10,10,10));
		if(typeCount == 0 ){
			setIntroTextInCommandBox();
		}
		
		commandBox.requestFocusInWindow();
	    commandBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				 command= commandBox.getText();
				 parent.passToParser(command);
				 commandStack.insertCommand(command);
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
				parent.pressedF1();
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_F2){
			System.out.println("you have entered F3");
			parent.pressedF3();
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN){
			commandBox.setText(commandStack.retrieveCommandFromBackwardStack());
		}
		if(arg0.getKeyCode() == KeyEvent.VK_UP){
			commandBox.setText(commandStack.retrieveCommandFromForwardStack());
		}
		
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
			feedbackMsg.setText("");
		} else{
			feedbackMsg.setText(SummaryReport.getFeedBackMsg());
		}
		
	}
}
