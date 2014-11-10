package taskDo;

public class WagnerFischerSearch {
	// @author  A0111936J

	// Calculates the levenshtein distance between 2 Strings
	public int getEditDistance(String input1, String input2) {

		int length1 = input1.length();
		int length2 = input2.length();
		int tracker;

		int[][] arr = new int[length1 + 1][length2 + 1];

		for (int i = 0; i <= length1; i++) {
			arr[i][0] = i;
		}
		for (int j = 0; j <= length2; j++) {
			arr[0][j] = j;
		}

		for (int i = 1; i <= length1; i++) {
			for (int j = 1; j <= length2; j++) {
				if (input1.charAt(i - 1) == input2.charAt(j - 1)) {
					tracker = 0;
				} else {
					tracker = 1;
				}

				int tempMinimum = Math.min(arr[i - 1][j], arr[i][j - 1]) + 1;
				arr[i][j] = Math
						.min(tempMinimum, (arr[i - 1][j - 1] + tracker));
			}
		}

		return arr[length1][length2];
	}
}
