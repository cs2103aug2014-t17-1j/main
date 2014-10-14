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

import taskDo.Controller;

public class CommandBoxPanel extends JPanel implements KeyListener,Observer{
	private Controller controller;
	private int typeCount;
	private JTextField commandBox;
	private String command;
	private JLabel feedBackMsg;
	
	public CommandBoxPanel(){
		controller = new Controller();
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
	

	private void initCommandBox() {
		commandBox = new JTextField();
		if(typeCount == 0 ){
			setIntroTextInCommandBox();
		}
		
		commandBox.requestFocusInWindow();
	    commandBox.addActionListener(new ActionListener(){
		 	/*
	    	 * (non-Javadoc)
	    	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    	 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				 command= commandBox.getText();
				 controller.setUserCommand(command);
				 controller.parseToParser();
				 commandBox.setText("");
				 System.out.println(command);
			}
	    	
	    });
	   commandBox.addKeyListener(this);
	}
	
	public Controller getController(){
		return controller;
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
		// TODO Auto-generated method stub
		
	}
}
