import static org.junit.Assert.*;

import org.junit.Test;


public class ParserTest {

	@Test
	public void test() {
		Parser.parseString("add [Test command parameter] due 15th oct");
		
	}

}
