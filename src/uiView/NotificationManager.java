package uiView;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.melloware.jintellitype.JIntellitype;

public class NotificationManager implements MouseListener{
		private JFrame frame;
	public NotificationManager(final JFrame frame) {
		this.frame = frame;
	    if (!SystemTray.isSupported()) {
	        System.out.println("SystemTray is not supported");
	        return;
	      }

	      SystemTray tray = SystemTray.getSystemTray();
	      ImageIcon tray_icon = new ImageIcon(getClass().getResource("/image/Task.Do Icon.png"));
	      Image image = tray_icon.getImage();
//	      Toolkit toolkit = Toolkit.getDefaultToolkit();
//	      Image image = toolkit.getImage("Image/Task.Do Icon.png");

	      PopupMenu menu = new PopupMenu();

//	      MenuItem messageItem = new MenuItem("Show Message");
//	      messageItem.addActionListener(new ActionListener() {
//	        public void actionPerformed(ActionEvent e) {
//	          JOptionPane.showMessageDialog(null, "www.java2s.com");
//	        }
//	      });
//	      menu.add(messageItem);
	      
	      MenuItem openItem = new MenuItem("Open");
	      openItem.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	          frame.setVisible(true);
	        }
	      });
	      menu.add(openItem);
	      MenuItem closeItem = new MenuItem("Exit");
	      closeItem.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	JIntellitype.getInstance().cleanUp();
	        	System.exit(0);
	        }
	      });
	      menu.add(closeItem);
	      TrayIcon icon = new TrayIcon(image, "Task.Do", menu);
	      icon.addMouseListener(this);
	      icon.setImageAutoSize(true);

	      try {
			tray.add(icon);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		frame.setVisible(true);
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
	