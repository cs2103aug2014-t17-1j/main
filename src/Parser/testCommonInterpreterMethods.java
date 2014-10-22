package Parser;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import taskDo.Task;
import commonClasses.SummaryReport;

public class testCommonInterpreterMethods {

	@Test
	public void testIsValidSelection() {
		
		ArrayList<Task> testingList = new ArrayList<Task>();
		testingList.add(new Task());
		testingList.add(new Task());
		testingList.add(new Task());
		testingList.add(new Task());
		
		//List size of 4. Selection available on GUI will be 1 to 4. 
		SummaryReport.setDisplayList(testingList);
		
		//Out of range. Must be at least 1(boundary case)
		boolean result = CommonInterpreterMethods.isValidSelection("0");
		Assert.assertEquals(false, result);
		
		//within range. testing 1(boundary case)
		result = CommonInterpreterMethods.isValidSelection("1");
		Assert.assertEquals(true, result);
		
		//within range. testing 4(boundary case)
		result = CommonInterpreterMethods.isValidSelection("4");
		Assert.assertEquals(true, result);
		
		//out of range. testing 5(boundary case)
		result = CommonInterpreterMethods.isValidSelection("5");
		Assert.assertEquals(false, result);
	}

}
