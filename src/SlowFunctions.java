import java.util.Random;

/* 
 *	If you are getting a StackOverflow exception while running the following program, perform the following:
 *		1. Go to Run > Run Configurations
 *      2. Click on the tab that reads "(x)= Arguments"
 *      3. In the box labeled "VM arguments:", add this: -Xss64m 
 * 
 *	Run the following program. Please be patient. It is supposed to take a long time to complete.
 *  Read the code and follow along with the output. 
 *  
 *  The program is running three static methods in sequence. Each method takes a long time to complete so the 
 *  program has a very slow total run time. Since each method does not rely on the others to run, the total 
 *  run time of the program can be reduced by taking advantage of threads. 
 *  
 *  In the main method of the FasterFunctions class, call each of the three static methods in a new Thread and
 *  improve the total run time. Don't forget to join the threads before the main thread finishes.
 * 
 */

public class SlowFunctions {
	public static void main(String[] args) {
		float totalTime = 0;
		long startTime = System.currentTimeMillis();
		System.out.println("Starting slowSortLargeArray()...");
		SlowFunctions.slowSortLargeArray();
		System.out.println("slowSortLargeArray() Complete.");
		float endTime = (float)((double)System.currentTimeMillis() - (double)startTime) / 1000.0f;
		totalTime += endTime;
		System.out.println("slowSortLargeArray() Time: " + endTime + "\n\n");
		
		startTime = System.currentTimeMillis();
		System.out.println("Starting ackermann(3, 14)...");
		System.out.println("ackermann(3, 14): " + SlowFunctions.ackermann(3, 14));
		System.out.println("ackermann(3, 14) Complete.");
		endTime = (float)((double)System.currentTimeMillis() - (double)startTime) / 1000.0f;
		totalTime += endTime;
		System.out.println("ackerman(3, 14) Time: " + endTime + "\n\n");
		
		System.out.println("Starting millionsOfSqrts()...");
		SlowFunctions.millionsOfSqrts();
		System.out.println("millionsOfSqrts() Complete.");
		endTime = (float)((double)System.currentTimeMillis() - (double)startTime) / 1000.0f;
		totalTime += endTime;
		
		System.out.println("Total Time: " + totalTime + "\n\n");
	}
	
	public static void slowSortLargeArray() {
		final int BIL = 100_000;
		int[] nums = new int[BIL];
		for(int i = 0; i < BIL; i++) {
			nums[i] = new Random().nextInt();
		}
		for(int i = 0; i < BIL - 1; i++) {
			for(int j = i + 1; j < BIL; j++){
				if(nums[i] > nums[j]) {
					int t = nums[i];
					nums[i] = nums[j];
					nums[j] = t;
				}
			}
		}
		System.out.println("Smallest Value: " + nums[0]);
		System.out.println("Largest Value: " + nums[nums.length - 1]);
	}
	
	public static long ackermann(long m, long n) {
		if(m == 0) {
			return n + 1;
		}else if(m > 0 && n == 0) {
			return ackermann(m - 1, 1);
		}else {
			return ackermann(m - 1, ackermann(m, n - 1));
		}
	}
	
	public static void millionsOfSqrts() {
		for(int i = 0; i < 400000000; i++) {
			double d = Math.sqrt(new Random().nextInt(Integer.MAX_VALUE));
		}
	}
}
