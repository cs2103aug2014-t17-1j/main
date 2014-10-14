package taskDo;

import java.util.ArrayList;

public class CategoryList {

	//Members
	private static ArrayList<Category> categoryList;

	//Constructor
	private CategoryList() {

	}

	//Accessor
	public static ArrayList<Category> getCategoryList() {
		if(categoryList == null) {
			categoryList = new ArrayList<Category>();
		}
		return categoryList;
	}

	//Mutator
	public boolean addCategory(String name) {
		categoryList.add(new Category(name));
		return true;
	}

	public boolean deleteCategory(String name) {
		for(int i=0; i<categoryList.size(); i++) {
			if(categoryList.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
				categoryList.remove(i);
				return true;
			}
		}
		return false;
	}

	//Extra methods
	public boolean isExistingCatgory(String name) {
		for(int i=0; i<categoryList.size(); i++) {
			if(categoryList.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	public int getCategoryIndex(String name) {
		for(int i=0; i<categoryList.size(); i++) {
			if(categoryList.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
				return i;
			}
		}
		return -1;//index not found
	}
	
	public void addCountToCategory(String name) {
		int index = getCategoryIndex(name);
			
		if(index != -1) {
			categoryList.get(index).addCount();	
		}
	}
	
	public void minusCountToCategory(String name) {
		int index = getCategoryIndex(name);
			
		if(index != -1) {
			categoryList.get(index).decreaseCount();	
		}
	}
}

