package lab9;

/**
 * 
 * COMP 3021
 * 
This is a class that prints the maximum value of a given array of 90 elements

This is a single threaded version.

Create a multi-thread version with 3 threads:

one thread finds the max among the cells [0,29] 
another thread the max among the cells [30,59] 
another thread the max among the cells [60,89]

Compare the results of the three threads and print at console the max value.

 * 
 * @author valerio
 *
 */
public class FindMax implements Runnable{
	// this is an array of 90 elements
	// the max value of this array is 9999
	static int[] array = { 1, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2, 3, 4543,
			234, 3, 454, 1, 2, 3, 1, 9999, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3, 1, 34, 5, 6, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3 };

	public static void main(String[] args) {
		new FindMax().printMax();
	}
	
	int max = 0;
	int start = 0;
	int end = array.length/3;
	
	public void printMax() {
		// this is a multi-thread version
		Thread t1 = new Thread(this);
		Thread t2 = new Thread(this);
		Thread t3 = new Thread(this);
		
		t1.start();		
		try {
			t1.join();	
			start += array.length/3;
			end += array.length/3;
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		
		t2.start();	
		try {
			t2.join();	
			start += array.length/3;
			end += array.length/3;
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		
		t3.start();
		try {
			t3.join();		
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		
		System.out.println("the max value is " + max);		
	}

	/**
	 * returns the max value in the array within a give range [begin,range]
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	private int findMax(int begin, int end) {
		// you should NOT change this function
		int max = array[begin];
		for (int i = begin + 1; i <= end; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		int cur = findMax(start, end - 1);
		max = (cur > max)? cur: max;
	}
}
