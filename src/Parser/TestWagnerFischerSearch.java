package Parser;

import org.junit.Assert;
import org.junit.Test;

public class TestWagnerFischerSearch {

	@Test
	public void test() {
		WagnerFischerSearch testing = new WagnerFischerSearch();
		
		int editDist = testing.getEditDistance("kitten", "sitting");
		
		Assert.assertEquals(3,editDist);
			
	}

}
