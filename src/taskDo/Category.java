package taskDo;
/*
 * @author Paing Zin Oo(Jack)
 */
public class Category {
	private String name;
	private static int count;
	
	public Category(String name){
		this.name = name;
		count = 1;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int newCount) {
		count = newCount;
	}
	
	
}
