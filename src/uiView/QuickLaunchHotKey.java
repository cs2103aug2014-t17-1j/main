package uiView;

import java.awt.Frame;

import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;

public class QuickLaunchHotKey implements HotKeyListener {

	private UiViewModifier uiView;

	// @author Boo Tai Yi A0111936J
	public QuickLaunchHotKey(UiViewModifier uiView) {
		this.uiView = uiView;
	}

	@Override
	public void onHotKey(HotKey arg0) {
		if (uiView.isMinimized()) {
			uiView.setMinimized(false);
			uiView.getMainFrame().setState(Frame.NORMAL);
		} else {
			if (!uiView.getMainFrame().isVisible()) {
				uiView.getMainFrame().setVisible(true);
			} else if (uiView.getMainFrame().isVisible()) {
				uiView.getMainFrame().setVisible(false);

			}
		}
	}

}
