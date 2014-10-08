package taskDo;
/*
 * @author Paing Zin Oo(Jack)
 */
public class Category {
	private String name;
	private int count;
	
	public Category(String name){
		this.name = name;
		this.count = 0;
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
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
