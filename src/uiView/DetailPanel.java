package uiView;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import taskDo.Task;
import commonClasses.Constants;

public class DetailPanel extends JPanel implements Observer{
	
	public DetailPanel(){
		String []helpCommands = Constants.HELPCOMMANDS;
		setLayout(new GridLayout(helpCommands.length,1));
		setPreferredSize(Constants.DIMENSION_DETAIL_PANEL);
		setBorder(BorderFactory.createTitledBorder(null,Constants.HEADER_HELP, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, Font.getFont("times new roman"), Constants.COLOR_DETAIL_PANEL_TEXT));
		setBackground(Constants.COLOR_DETAIL_PANEL_BG);
		for(int i = 0 ; i < helpCommands.length; i++){
			JLabel lbl_helpCommand = new JLabel(helpCommands[i]);
			lbl_helpCommand.setForeground(Constants.COLOR_DETAIL_PANEL_TEXT);
			add(lbl_helpCommand);
			
		}
		
	}
	public DetailPanel(Task task){
		System.out.println("DETAIL PANEL FOR TASK");
		setLayout(new GridLayout(4,1));
		setPreferredSize(Constants.DIMENSION_DETAIL_PANEL);
		setBorder(BorderFactory.createTitledBorder(null,Constants.HEADER_DETAIL, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, Font.getFont("times new roman"), Constants.COLOR_DETAIL_PANEL_TEXT));
		setBackground(Constants.COLOR_DETAIL_PANEL_BG);
		JLabel label = new JLabel(task.getDescription());
		label.setForeground(Constants.COLOR_DETAIL_PANEL_TEXT);
		add(label);
		DateTimeFormatter dateFormat = DateTimeFormat.forPattern("dd-MM-yyyy");
		String dueDate = dateFormat.print(task.getDueDate().toLocalDate());
		label = new JLabel("Due On " +dueDate);
		label.setForeground(Constants.COLOR_DETAIL_PANEL_TEXT);
		add(label);
		assert task.getCategory() != null;
		label = new JLabel(task.getCategory());
		label.setForeground(Constants.COLOR_DETAIL_PANEL_TEXT);
		add(label);
		
		
	}
	public boolean isExisting(){
		if(isDisplayable()){
			return true;
		}
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}
