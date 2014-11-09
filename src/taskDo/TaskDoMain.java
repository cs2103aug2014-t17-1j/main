package taskDo;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uiView.UiViewModifier;
import commonClasses.Constants;
import commonClasses.StorageList;
import commonClasses.SummaryReport;

public class TaskDoMain {
	static FileLock lock;
	static FileChannel channel;
	private static final Logger logger = LogManager.getLogger(TaskDoMain.class);
	//@author Boo Tai Yi  A0111936J
	public static void main(String args[]) {
		try {
			if (isAppActive()) {
				System.exit(1);
			}
		} catch (Exception e) {
			logger.error(Constants.STRING_ERROR_TASKDO_IS_ACTIVE);
			e.printStackTrace();
		}

		ArrayList<Task> taskList = StorageList.getInstance().getTaskList();
		SummaryReport.setDisplayList(taskList);
		new UiViewModifier();

	}

	@SuppressWarnings("resource")
	public static boolean isAppActive() throws Exception {
		File file = new File(System.getProperty("user.home"), "TaskDoLock.tmp");
		channel = new RandomAccessFile(file, "rw").getChannel();

		lock = channel.tryLock();
		if (lock == null) {
			channel.close();
			return true;
		}
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					lock.release();
					channel.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		return false;
	}
}
