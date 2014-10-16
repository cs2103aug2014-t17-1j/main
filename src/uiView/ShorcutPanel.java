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

public class ShorcutPanel extends JPanel{
	
	public ShorcutPanel(){
		String []shortcuts = Constants.SHORTCUTS;
		setLayout(new GridLayout(shortcuts.length,1));
		setBorder(BorderFactory.createTitledBorder(null,Constants.HEADER_SHORTCUTS, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, Font.getFont("times new roman"), Constants.COLOR_LEFT_PANEL_HEADER));
		setPreferredSize(Constants.DIMENSION_SHORCUT_PANEL);
		for(int i = 0; i < shortcuts.length; i++){
			JLabel label_shortcut = new JLabel (shortcuts[i]);
			label_shortcut.setForeground(Constants.COLOR_LEFT_PANEL_TEXT);
			add(label_shortcut);
		}
		setBackground(Constants.COLOR_LEFT_PANEL_BG);
		setFocusTraversalKeysEnabled(false);
		
	}

}
