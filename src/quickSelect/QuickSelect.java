package quickSelect;

import java.util.Comparator;

public class QuickSelect {
	
	
	// TEMPLATED QUICKSELECT
	
	/** Selects the {@code n}-th element of array {@code arr} according to the ordering specified by {@code c}.
	 * 
	 * @param arr The array of objects.
	 * @param n The index of the element to select from the ordered elements of {@code arr}.
	 * @param c The {@code Comparator} specifying an ordering of the elements of {@code arr}.
	 * @return The element at index {@code n} of the ordered elements of {@code arr}.
	 */
	public static <T> T quickSelect(T [] arr, int n, Comparator<T> c) {
		
		return quickSelect(arr, 0, arr.length, n, c);
	}

	/** Selects the {@code n}-th element of the sub-array of {@code arr} bounded by index {@code left} (inclusive) and {@code right} (exclusive) according to the ordering specified by {@code c}.
	 * 
	 * @param arr The array of objects.
	 * @param n The index of the element to select from the ordered elements of the sub-array of {@code arr}.
	 * @param left The left index (inclusive) of the sub-array of {@code arr} to select from.
	 * @param right The right index (exclusive) of the sub-array of {@code arr} to select from.
	 * @param c The {@code Comparator} specifying an ordering of the elements of the sub-array of {@code arr}.
	 * @return The element at index {@code n} of the ordered elements of the sub-array of {@code arr}.
	 */
	public static <T> T quickSelect(T[] arr, int left, int right, int n, Comparator<T> c) {
	
		if(right < 0 || left > arr.length) throw new ArrayIndexOutOfBoundsException("The range [" + left + ", " + right + ") extends beyond the bounds of the array.");
		if(right <= left) throw new IllegalArgumentException("right index less than or equal to left index.");
		if(n >= right - left || n < 0) throw new ArrayIndexOutOfBoundsException(n + " is outside range: [0," + (right - left) + ").");
		
		
		if(right - left == 1) {
			return arr[left];
		}
		
		int pivot = medOf3(arr,left,right,c); 
		
		swap(arr,left,pivot);
		
		int lefti = left+1, righti = right-1;
		
		while(lefti < righti) {
			while(lefti < righti && c.compare(arr[lefti],arr[left]) <= 0) {
				lefti++;
			}
			while(righti > lefti && c.compare(arr[righti], arr[left]) > 0) {
				righti--;
			}
			swap(arr,lefti,righti);
		}
		
		lefti--;
		
		swap(arr, left, lefti);
		if(left + n == lefti) {
			return arr[lefti];
		}
		
		if(left + n < lefti) {
			return quickSelect(arr, left, lefti, n,c);
		}
		
		return quickSelect(arr, lefti + 1, right, n - (lefti + 1 - left),c);
		
	}
	
	/** Selects the {@code n}-th element of array {@code arr} according to the natural ordering of elements specified by the {@code Comparable<T>} interface.
	 * 
	 * @param arr The array of objects.
	 * @param n The index of the element to select from the ordered elements of {@code arr}.
	 * @return The element at index {@code n} of the ordered elements of {@code arr}.
	 */
	public static <T extends Comparable<? super T>> T quickSelect(T [] arr, int n) {
		
		return quickSelect(arr,0,arr.length,n);
	}

	/** Selects the {@code n}-th element of the sub-array of {@code arr} bounded by index {@code left} (inclusive) and {@code right} (exclusive) according to the natural ordering of elements specified by the {@code Comparable<T>} interface.
	 * 
	 * @param arr The array of objects.
	 * @param n The index of the element to select from the ordered elements of the sub-array of {@code arr}.
	 * @param left The left index (inclusive) of the sub-array of {@code arr} to select from.
	 * @param right The right index (exclusive) of the sub-array of {@code arr} to select from.
	 * @return The element at index {@code n} of the ordered elements of the sub-array of {@code arr}.
	 */
	public static <T extends Comparable<? super T>> T quickSelect(T[] arr, int left, int right, int n) {
	
		if(right < 0 || left > arr.length) throw new ArrayIndexOutOfBoundsException("The range [" + left + ", " + right + ") extends beyond the bounds of the array.");
		if(right <= left) throw new IllegalArgumentException("right index less than or equal to left index.");
		if(n >= right - left || n < 0) throw new ArrayIndexOutOfBoundsException(n + " is outside range: [0," + (right - left) + ").");
		
		
		if(right - left == 1) {
			return arr[left];
		}
		
		int pivot = medOf3(arr,left,right,(t1, t2) -> t1.compareTo(t2)); 
		
		swap(arr,left,pivot);
		
		int lefti = left+1, righti = right-1;
		
		while(lefti < righti) {
			while(lefti < righti && arr[lefti].compareTo(arr[left]) <= 0) {
				lefti++;
			}
			while(righti > lefti && arr[righti].compareTo(arr[left]) > 0) {
				righti--;
			}
			swap(arr,lefti,righti);
		}
		
		lefti--;
		
		swap(arr, left, lefti);
		if(left + n == lefti) {
			return arr[lefti];
		}
		
		if(left + n < lefti) {
			return quickSelect(arr, left, lefti, n);
		}
		
		return quickSelect(arr, lefti + 1, right, n - (lefti + 1 - left));
		
	}

	private static <T> void swap(T[] arr, int i1, int i2) {
		
		T tmp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = tmp;

	}
	
	private static <T> int medOf3(T[] arr, int left, int right, Comparator<T> c) {
		right--;
		int mid = (left + right) / 2;
		
		if(c.compare(arr[left], arr[right]) < 0) {
			if(c.compare(arr[right], arr[mid]) <= 0) {
				return right;
			}
			return c.compare(arr[mid], arr[left]) < 0 ? left : mid;
		}
		else if(c.compare(arr[left], arr[mid]) <= 0) {
			return left;
		}
		
		return c.compare(arr[mid], arr[right]) < 0 ? right : mid;
	}
	
	// QUICKSELECT ON ARRAYS OF PRIMITIVES
	
	// INT
	
	/** Selects the {@code n}-th element of array {@code arr} according to the ordering specified by {@code c}.
	 * 
	 * @param arr An integer array.
	 * @param n The index of the element to select from the ordered elements of {@code arr}.
	 * @param c The {@code Comparator} specifying an ordering of the elements of {@code arr}.
	 * @return The element at index {@code n} of the ordered elements of {@code arr}.
	 */
	public static int quickSelect(int [] arr, int n, Comparator<Integer> c) {
		
		return quickSelect(arr, 0, arr.length, n, c);
	}

	/** Selects the {@code n}-th element of the sub-array of {@code arr} bounded by index {@code left} (inclusive) and {@code right} (exclusive) according to the ordering specified by {@code c}.
	 * 
	 * @param arr An integer array.
	 * @param n The index of the element to select from the ordered elements of the sub-array of {@code arr}.
	 * @param left The left index (inclusive) of the sub-array of {@code arr} to select from.
	 * @param right The right index (exclusive) of the sub-array of {@code arr} to select from.
	 * @param c The {@code Comparator} specifying an ordering of the elements of the sub-array of {@code arr}.
	 * @return The element at index {@code n} of the ordered elements of the sub-array of {@code arr}.
	 */
	public static int quickSelect(int[] arr, int left, int right, int n, Comparator<Integer> c) {
	
		if(right < 0 || left > arr.length) throw new ArrayIndexOutOfBoundsException("The range [" + left + ", " + right + ") extends beyond the bounds of the array.");
		if(right <= left) throw new IllegalArgumentException("right index less than or equal to left index.");
		if(n >= right - left || n < 0) throw new ArrayIndexOutOfBoundsException(n + " is outside range: [0," + (right - left) + ").");
		
		if(right - left == 1) {
			return arr[left];
		}
		
		int pivot = medOf3(arr,left,right,c); 
		
		swap(arr,left,pivot);
		
		int lefti = left+1, righti = right-1;
		
		while(lefti < righti) {
			while(lefti < righti && c.compare(arr[lefti],arr[left]) <= 0) {
				lefti++;
			}
			while(righti > lefti && c.compare(arr[righti], arr[left]) > 0) {
				righti--;
			}

			swap(arr,lefti,righti);

		}
		
		lefti--;
		
		swap(arr, left, lefti);
		
		if(left + n == lefti) {
			return arr[lefti];
		}
		
		if(left + n < lefti) {
			return quickSelect(arr, left, lefti, n,c);
		}
		
		return quickSelect(arr, lefti + 1, right, n - (lefti + 1 - left),c);
		
	}
	
	/** Selects the {@code n}-th element of array {@code arr} according to the natural ordering of elements.
	 * 
	 * @param arr An integer array.
	 * @param n The index of the element to select from the ordered elements of {@code arr}.
	 * @return The element at index {@code n} of the ordered elements of {@code arr}.
	 */
	public static int quickSelect(int [] arr, int n) {
		
		return quickSelect(arr,0,arr.length,n);
	}

	/** Selects the {@code n}-th element of the sub-array of {@code arr} bounded by index {@code left} (inclusive) and {@code right} (exclusive) according to the natural ordering of elements.
	 * 
	 * @param arr An integer array.
	 * @param n The index of the element to select from the ordered elements of the sub-array of {@code arr}.
	 * @param left The left index (inclusive) of the sub-array of {@code arr} to select from.
	 * @param right The right index (exclusive) of the sub-array of {@code arr} to select from.
	 * @return The element at index {@code n} of the ordered elements of the sub-array of {@code arr}.
	 */
	public static int quickSelect(int[] arr, int left, int right, int n) {
	
		if(right < 0 || left > arr.length) throw new ArrayIndexOutOfBoundsException("The range [" + left + ", " + right + ") extends beyond the bounds of the array.");
		if(right <= left) throw new IllegalArgumentException("right index less than or equal to left index.");
		if(n >= right - left || n < 0) throw new ArrayIndexOutOfBoundsException(n + " is outside range: [0," + (right - left) + ").");
		
				
		if(right - left == 1) {
			return arr[left];
		}
		
		int pivot = medOf3(arr,left,right,(t1, t2) -> t1.compareTo(t2)); 
		
		swap(arr,left,pivot);
		
		int lefti = left+1, righti = right-1;
		
		while(lefti < righti) {
			while(lefti < righti && arr[lefti] <= arr[left]) {
				lefti++;
			}
			while(righti > lefti && arr[righti] > arr[left]) {
				righti--;
			}
			
			swap(arr,lefti,righti);
		
		}
		
		lefti--;
		
		swap(arr, left, lefti);
		
		
		if(left + n == lefti) {
			return arr[lefti];
		}
		
		if(left + n < lefti) {
			return quickSelect(arr, left, lefti, n);
		}
		
		return quickSelect(arr, lefti + 1, right, n - (lefti + 1 - left));
		
	}

	private static  void swap(int[] arr, int i1, int i2) {
		
		int tmp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = tmp;

	}
	
	private static int medOf3(int[] arr, int left, int right, Comparator<Integer> c) {
		right--;
		int mid = (left + right) / 2;
		
		if(c.compare(arr[left], arr[right]) < 0) {
			if(c.compare(arr[right], arr[mid]) <= 0) {
				return right;
			}
			return c.compare(arr[mid], arr[left]) < 0 ? left : mid;
		}
		else if(c.compare(arr[left], arr[mid]) <= 0) {
			return left;
		}
		
		return c.compare(arr[mid], arr[right]) < 0 ? right : mid;
	}
	
	// DOUBLE

	/** Selects the {@code n}-th element of array {@code arr} according to the ordering specified by {@code c}.
	 * 
	 * @param arr A double array.
	 * @param n The index of the element to select from the ordered elements of {@code arr}.
	 * @param c The {@code Comparator} specifying an ordering of the elements of {@code arr}.
	 * @return The element at index {@code n} of the ordered elements of {@code arr}.
	 */
	public static double quickSelect(double [] arr, int n, Comparator<Double> c) {
		
		return quickSelect(arr, 0, arr.length, n, c);
	}

	/** Selects the {@code n}-th element of the sub-array of {@code arr} bounded by index {@code left} (inclusive) and {@code right} (exclusive) according to the ordering specified by {@code c}.
	 * 
	 * @param arr A double array.
	 * @param n The index of the element to select from the ordered elements of the sub-array of {@code arr}.
	 * @param left The left index (inclusive) of the sub-array of {@code arr} to select from.
	 * @param right The right index (exclusive) of the sub-array of {@code arr} to select from.
	 * @param c The {@code Comparator} specifying an ordering of the elements of the sub-array of {@code arr}.
	 * @return The element at index {@code n} of the ordered elements of the sub-array of {@code arr}.
	 */
	public static double quickSelect(double [] arr, int left, int right, int n, Comparator<Double> c) {
	
		if(right < 0 || left > arr.length) throw new ArrayIndexOutOfBoundsException("The range [" + left + ", " + right + ") extends beyond the bounds of the array.");
		if(right <= left) throw new IllegalArgumentException("right index less than or equal to left index.");
		if(n >= right - left || n < 0) throw new ArrayIndexOutOfBoundsException(n + " is outside range: [0," + (right - left) + ").");
		
		
		if(right - left == 1) {
			return arr[left];
		}
		
		int pivot = medOf3(arr,left,right,c); 
		
		swap(arr,left,pivot);
		
		int lefti = left+1, righti = right-1;
		
		while(lefti < righti) {
			while(lefti < righti && c.compare(arr[lefti],arr[left]) <= 0) {
				lefti++;
			}
			while(righti > lefti && c.compare(arr[righti], arr[left]) > 0) {
				righti--;
			}
			swap(arr,lefti,righti);
		}
		
		lefti--;
		
		swap(arr, left, lefti);
		if(left + n == lefti) {
			return arr[lefti];
		}
		
		if(left + n < lefti) {
			return quickSelect(arr, left, lefti, n,c);
		}
		
		return quickSelect(arr, lefti + 1, right, n - (lefti + 1 - left),c);
		
	}
	
	/** Selects the {@code n}-th element of array {@code arr} according to the natural ordering of elements.
	 * 
	 * @param arr A double array.
	 * @param n The index of the element to select from the ordered elements of {@code arr}.
	 * @return The element at index {@code n} of the ordered elements of {@code arr}.
	 */
	public static double quickSelect(double [] arr, int n) {
		
		return quickSelect(arr,0,arr.length,n);
	}

	/** Selects the {@code n}-th element of the sub-array of {@code arr} bounded by index {@code left} (inclusive) and {@code right} (exclusive) according to the natural ordering of elements.
	 * 
	 * @param arr A double array.
	 * @param n The index of the element to select from the ordered elements of the sub-array of {@code arr}.
	 * @param left The left index (inclusive) of the sub-array of {@code arr} to select from.
	 * @param right The right index (exclusive) of the sub-array of {@code arr} to select from.
	 * @return The element at index {@code n} of the ordered elements of the sub-array of {@code arr}.
	 */
	public static double quickSelect(double[] arr, int left, int right, int n) {
	
		if(right < 0 || left > arr.length) throw new ArrayIndexOutOfBoundsException("The range [" + left + ", " + right + ") extends beyond the bounds of the array.");
		if(right <= left) throw new IllegalArgumentException("right index less than or equal to left index.");
		if(n >= right - left || n < 0) throw new ArrayIndexOutOfBoundsException(n + " is outside range: [0," + (right - left) + ").");
		
		
		if(right - left == 1) {
			return arr[left];
		}
		
		int pivot = medOf3(arr,left,right,(t1, t2) -> t1.compareTo(t2)); 
		
		swap(arr,left,pivot);
		
		int lefti = left+1, righti = right-1;
		
		while(lefti < righti) {
			while(lefti < righti && arr[lefti] <= arr[left]) {
				lefti++;
			}
			while(righti > lefti && arr[righti] > arr[left]) {
				righti--;
			}
			swap(arr,lefti,righti);
		}
		
		lefti--;
		
		swap(arr, left, lefti);
		if(left + n == lefti) {
			return arr[lefti];
		}
		
		if(left + n < lefti) {
			return quickSelect(arr, left, lefti, n);
		}
		
		return quickSelect(arr, lefti + 1, right, n - (lefti + 1 - left));
		
	}
	
	private static  void swap(double[] arr, int i1, int i2) {
		
		double tmp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = tmp;

	}
	
	private static int medOf3(double [] arr, int left, int right, Comparator<Double> c) {
		right--;
		int mid = (left + right) / 2;
		
		if(c.compare(arr[left], arr[right]) < 0) {
			if(c.compare(arr[right], arr[mid]) <= 0) {
				return right;
			}
			return c.compare(arr[mid], arr[left]) < 0 ? left : mid;
		}
		else if(c.compare(arr[left], arr[mid]) <= 0) {
			return left;
		}
		
		return c.compare(arr[mid], arr[right]) < 0 ? right : mid;
	}
	
	
	// LONG

	/** Selects the {@code n}-th element of array {@code arr} according to the ordering specified by {@code c}.
	 * 
	 * @param arr A long array.
	 * @param n The index of the element to select from the ordered elements of {@code arr}.
	 * @param c The {@code Comparator} specifying an ordering of the elements of {@code arr}.
	 * @return The element at index {@code n} of the ordered elements of {@code arr}.
	 */
	public static long quickSelect(long [] arr, int n, Comparator<Long> c) {
		
		return quickSelect(arr, 0, arr.length, n, c);
	}

	/** Selects the {@code n}-th element of the sub-array of {@code arr} bounded by index {@code left} (inclusive) and {@code right} (exclusive) according to the ordering specified by {@code c}.
	 * 
	 * @param arr A long array.
	 * @param n The index of the element to select from the ordered elements of the sub-array of {@code arr}.
	 * @param left The left index (inclusive) of the sub-array of {@code arr} to select from.
	 * @param right The right index (exclusive) of the sub-array of {@code arr} to select from.
	 * @param c The {@code Comparator} specifying an ordering of the elements of the sub-array of {@code arr}.
	 * @return The element at index {@code n} of the ordered elements of the sub-array of {@code arr}.
	 */
	public static long quickSelect(long [] arr, int left, int right, int n, Comparator<Long> c) {
	
		if(right < 0 || left > arr.length) throw new ArrayIndexOutOfBoundsException("The range [" + left + ", " + right + ") extends beyond the bounds of the array.");
		if(right <= left) throw new IllegalArgumentException("right index less than or equal to left index.");
		if(n >= right - left || n < 0) throw new ArrayIndexOutOfBoundsException(n + " is outside range: [0," + (right - left) + ").");
		
		
		if(right - left == 1) {
			return arr[left];
		}
		
		int pivot = medOf3(arr,left,right,c); 
		
		swap(arr,left,pivot);
		
		int lefti = left+1, righti = right-1;
		
		while(lefti < righti) {
			while(lefti < righti && c.compare(arr[lefti],arr[left]) <= 0) {
				lefti++;
			}
			while(righti > lefti && c.compare(arr[righti], arr[left]) > 0) {
				righti--;
			}
			swap(arr,lefti,righti);
		}
		
		lefti--;
		
		swap(arr, left, lefti);
		if(left + n == lefti) {
			return arr[lefti];
		}
		
		if(left + n < lefti) {
			return quickSelect(arr, left, lefti, n,c);
		}
		
		return quickSelect(arr, lefti + 1, right, n - (lefti + 1 - left),c);
		
	}
	
	/** Selects the {@code n}-th element of array {@code arr} according to the natural ordering of elements.
	 * 
	 * @param arr A long array.
	 * @param n The index of the element to select from the ordered elements of {@code arr}.
	 * @return The element at index {@code n} of the ordered elements of {@code arr}.
	 */
	public static long quickSelect(long [] arr, int n) {
		
		return quickSelect(arr,0,arr.length,n);
	}

	/** Selects the {@code n}-th element of the sub-array of {@code arr} bounded by index {@code left} (inclusive) and {@code right} (exclusive) according to the natural ordering of elements.
	 * 
	 * @param arr A long array.
	 * @param n The index of the element to select from the ordered elements of the sub-array of {@code arr}.
	 * @param left The left index (inclusive) of the sub-array of {@code arr} to select from.
	 * @param right The right index (exclusive) of the sub-array of {@code arr} to select from.
	 * @return The element at index {@code n} of the ordered elements of the sub-array of {@code arr}.
	 */
	public static long quickSelect(long[] arr, int left, int right, int n) {
	
		if(right < 0 || left > arr.length) throw new ArrayIndexOutOfBoundsException("The range [" + left + ", " + right + ") extends beyond the bounds of the array.");
		if(right <= left) throw new IllegalArgumentException("right index less than or equal to left index.");
		if(n >= right - left || n < 0) throw new ArrayIndexOutOfBoundsException(n + " is outside range: [0," + (right - left) + ").");
		
		
		if(right - left == 1) {
			return arr[left];
		}
		
		int pivot = medOf3(arr,left,right,(t1, t2) -> t1.compareTo(t2)); 
		
		swap(arr,left,pivot);
		
		int lefti = left+1, righti = right-1;
		
		while(lefti < righti) {
			while(lefti < righti && arr[lefti] <= arr[left]) {
				lefti++;
			}
			while(righti > lefti && arr[righti] > arr[left]) {
				righti--;
			}
			swap(arr,lefti,righti);
		}
		
		lefti--;
		
		swap(arr, left, lefti);
		if(left + n == lefti) {
			return arr[lefti];
		}
		
		if(left + n < lefti) {
			return quickSelect(arr, left, lefti, n);
		}
		
		return quickSelect(arr, lefti + 1, right, n - (lefti + 1 - left));
		
	}
	
	private static  void swap(long[] arr, int i1, int i2) {
		
		long tmp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = tmp;

	}
	
	private static int medOf3(long [] arr, int left, int right, Comparator<Long> c) {
		right--;
		int mid = (left + right) / 2;
		
		if(c.compare(arr[left], arr[right]) < 0) {
			if(c.compare(arr[right], arr[mid]) <= 0) {
				return right;
			}
			return c.compare(arr[mid], arr[left]) < 0 ? left : mid;
		}
		else if(c.compare(arr[left], arr[mid]) <= 0) {
			return left;
		}
		
		return c.compare(arr[mid], arr[right]) < 0 ? right : mid;
	}
	
	
	
	/*
	private static void printarr(int[] arr) {
		
		for(int i: arr) {
			System.err.print(i + " ");
		}
		System.err.println();
		
	}
	*/
	

}
