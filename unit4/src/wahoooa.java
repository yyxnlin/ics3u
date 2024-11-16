import java.util.Arrays;

public class wahoooa {

	public static void main(String[] args) {
		double[] arr1 = {1, 2, 2, 3, 3, 4, 3, 2, 3};
		double[] arr2 = {2, 3};
		
		int count = 0;

		double[] arr3 = new double[arr1.length];
		System.out.println(Arrays.toString(arr2));

		for (int i = 0; i < arr1.length; i++) {
			arr3[i] = arr1[i];
		}

		for (int i = 0; i <= arr1.length - arr2.length; i++) {
			boolean found = true;
			for (int j = 0; j < arr2.length; j++) {
				if (arr1[i + j] != arr2[j]) {
					found = false;
					break;
				}
			}

			if (found) {
				for (int j = i; j < i+arr2.length; j++) {
					arr3[j] = -1000;
				}
				i+= arr2.length;
				count++;

			}

		}

		System.out.println("count: " + count);
		int j = 0;

		int newLen = arr1.length - count * arr2.length;
		double[] newArr = new double[newLen];

		for (int i = 0; i < arr1.length; i++) {
			if(arr3[i] != -1000) {
				newArr[j] = arr3[i];
				j++;
			}
		}

		System.out.println("new arr : " + Arrays.toString(newArr));


	}

}
