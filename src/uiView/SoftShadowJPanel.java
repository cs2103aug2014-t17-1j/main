package uiView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class SoftShadowJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int PIXELS = 10;

	// @author  A0111936J
	public SoftShadowJPanel() {
		Border border = BorderFactory.createEmptyBorder(PIXELS, PIXELS, PIXELS,
				PIXELS);
		this.setBorder(BorderFactory.createCompoundBorder(this.getBorder(),
				border));
		this.setLayout(new BorderLayout());
	}

	@Override
	protected void paintComponent(Graphics g) {
		int shade = 0;
		int topOpacity = 80;
		for (int i = 0; i < PIXELS; i++) {
			g.setColor(new Color(shade, shade, shade,
					((topOpacity / PIXELS) * i)));
			g.drawRect(i, i, this.getWidth() - ((i * 2) + 1), this.getHeight()
					- ((i * 2) + 1));
		}
	}

}
