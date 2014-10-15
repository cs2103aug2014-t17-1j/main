package uiView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import commonClasses.Constants;

public class DetailPanel extends JPanel implements Observer{
	
	public DetailPanel(){
		String []helpCommands = Constants.HELPCOMMANDS;
		setLayout(new GridLayout(helpCommands.length,1));
		setPreferredSize(new Dimension(420,400));
		setBorder(BorderFactory.createTitledBorder(null,"HELP PANEL", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, Font.getFont("times new roman"), ColorBox.colorPool[24]));
		setBackground(Color.BLACK);
		for(int i = 0 ; i < helpCommands.length; i++){
			JLabel lbl_helpCommand = new JLabel(helpCommands[i]);
			lbl_helpCommand.setForeground(ColorBox.colorPool[24]);
			add(lbl_helpCommand);
			
		}
		
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
