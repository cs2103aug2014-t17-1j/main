package taskDo;

import org.junit.Assert;
import org.junit.Test;

public class TestParser {

	@Test
	public void testAdd() {
		boolean isValid = Parser.parseString("add [Test Add due] due [20/08/1991]");
		
		Assert.assertEquals(true, isValid);
	}

}
