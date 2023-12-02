
public class FasterFunctions {
	public static void main(String[] args) {
		float totalTime = 0;
		long startTime = System.currentTimeMillis();
		//Your Code Here
		
		WorkQueue wq = new WorkQueue();
		
		/* workQueue.addJob(() -> {
				stuff goes in here
			}); */
		
		
		wq.addJob(() -> {
			SlowFunctions.ackermann(3, 14);
			});
		
		wq.addJob(() -> {
			SlowFunctions.millionsOfSqrts();
		});
		
		wq.addJob(() -> {
			SlowFunctions.slowSortLargeArray();
		});
		
		wq.shutdown();
		
		
		
		
		
		
		
		
		float endTime = (float)((double)System.currentTimeMillis() - (double)startTime) / 1000.0f;
		totalTime += endTime;
		System.out.println("Total Time: " + totalTime);
	}
}
