package parser;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import taskDo.Task;
import commonClasses.SummaryReport;

public class TestCommonInterpreterMethods {

	@Test
	// @author  A0111936J
	public void testIsValidSelection() {

		ArrayList<Task> testingList = new ArrayList<Task>();
		testingList.add(new Task());
		testingList.add(new Task());
		testingList.add(new Task());
		testingList.add(new Task());

		// List size of 4. Selection available on GUI will be 1 to 4.
		SummaryReport.setDisplayList(testingList);

		// Lower boundary case value
		boolean result = CommonInterpreterMethods.isValidSelection("0");
		Assert.assertEquals(false, result);

		// Lower boundary case value
		result = CommonInterpreterMethods.isValidSelection("1");
		Assert.assertEquals(true, result);

		// Upper boundary case value
		result = CommonInterpreterMethods.isValidSelection("4");
		Assert.assertEquals(true, result);

		// Upper boundary case value
		result = CommonInterpreterMethods.isValidSelection("5");
		Assert.assertEquals(false, result);
	}

}
