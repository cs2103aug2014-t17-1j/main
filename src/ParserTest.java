import org.junit.Assert;
import org.junit.Test;


public class ParserTest {

	@Test
	public void test() {
		Parser.parseString("add [Test command parameter] due 15th oct");
		//Task expected = new Task();
		expected.setDescription("Test command parameter");
		
		Assert.assertEquals(expected, ParsedResult.getTaskDetails());
		Assert.assertEquals(CommandType.ADD,ParsedResult.getCommandType());
	}

}
