package taskDo;
/**
 * 
 * Category class to store the information about the category
 *
 */
public class Category {
	// @author  A0112581N
	private String name;
	private int count;

	public Category(String name) {
		this.name = name;
		this.count = 1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void addCount() {
		this.count++;
	}

	public void decreaseCount() {
		this.count--;
	}

}
