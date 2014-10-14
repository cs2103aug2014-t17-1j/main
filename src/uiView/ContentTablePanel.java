package uiView;

import java.awt.Dimension;

import javax.swing.JPanel;

public class ContentTablePanel extends JPanel implements Observer{
	
	public void ContentTablePanel(){
		setPreferredSize(new Dimension(500,400));
		
	}

	@Override
	public void update() {
		
		
	}
}
