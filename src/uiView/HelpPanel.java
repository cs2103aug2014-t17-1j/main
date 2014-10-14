package uiView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import commonClasses.Constants;

public class HelpPanel extends JPanel{
	
	public HelpPanel(){
		String []shortcuts = Constants.SHORTCUTS;
		setLayout(new GridLayout(shortcuts.length,1));
		setBorder(BorderFactory.createTitledBorder(null,Constants.HEADER_SHORTCUTS, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, Font.getFont("times new roman"), ColorBox.colorPool[24]));
		setPreferredSize(new Dimension(200,350));
		for(int i = 0; i < shortcuts.length; i++){
			JLabel label_shortcut = new JLabel (shortcuts[i]);
			label_shortcut.setForeground(ColorBox.colorPool[24]);
			add(label_shortcut);
		}
		setBackground(Color.BLACK);
		
	}

}
