package taskDo;

import java.util.ArrayList;

import commonClasses.StorageList;

public class CategoryList {

	// Members
	private static ArrayList<Category> categoryList;

	// Constructor
	private CategoryList() {

	}

	// Accessor
	public static ArrayList<Category> getCategoryList() {
		if (categoryList == null) {
			categoryList = new ArrayList<Category>();
		}
		return categoryList;
	}

	// Mutator
	public static boolean addCategory(String name) {
		categoryList.add(new Category(name));
		return true;
	}

	public static boolean deleteCategory(String name) {
		for (int i = 0; i < categoryList.size(); i++) {
			if (categoryList.get(i).getName().toLowerCase()
					.equals(name.toLowerCase())) {
				categoryList.remove(i);
				return true;
			}
		}
		return false;
	}

	// Extra methods
	public static void updateCategoryList(ArrayList<Task> updatedTask) {

		categoryList.clear();

		for (int i = 0; i < updatedTask.size(); i++) {
			if (!updatedTask.get(i).isCompleted()) {
				String categoryName = updatedTask.get(i).getCategory();
				if (categoryName != null) {
					if (isExistingCategory(categoryName.toLowerCase())) {
						addCountToCategory(categoryName.toLowerCase());
					} else {
						addCategory(categoryName.toLowerCase());
					}

				}
			}
		}

	}

	public static boolean isExistingCategory(String name) {
		if (CategoryList.getCategoryList().isEmpty()) {
			return false;
		}
		for (int i = 0; i < categoryList.size(); i++) {
			if (categoryList.get(i).getName().toLowerCase()
					.equals(name.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public static int getCategoryIndex(String name) {
		for (int i = 0; i < categoryList.size(); i++) {
			if (categoryList.get(i).getName().toLowerCase()
					.equals(name.toLowerCase())) {
				return i;
			}
		}
		return -1;// index not found
	}

	public static void addCountToCategory(String name) {
		int index = getCategoryIndex(name);

		if (index != -1) {
			categoryList.get(index).addCount();
		}
	}

	public static void minusCountToCategory(String name) {
		int index = getCategoryIndex(name);

		if (index != -1) {
			categoryList.get(index).decreaseCount();
		}
	}
}
