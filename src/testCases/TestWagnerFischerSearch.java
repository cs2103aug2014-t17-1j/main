package testCases;

import org.junit.Assert;
import org.junit.Test;

import taskDo.WagnerFischerSearch;

public class TestWagnerFischerSearch {
	//@author Boo Tai Yi  A0111936J
	@Test
	public void test() {
		WagnerFischerSearch testing = new WagnerFischerSearch();
		
		int editDist = testing.getEditDistance("kitten", "sitting");
		
		Assert.assertEquals(3,editDist);
			
	}

}
