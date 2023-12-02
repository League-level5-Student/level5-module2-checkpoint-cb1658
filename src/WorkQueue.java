import java.lang.Thread.State;
import java.util.ArrayDeque;

public class WorkQueue implements Runnable{

	private ArrayDeque<Job> jobQueue = new ArrayDeque<Job>();
	private Thread[] threads;
	private volatile boolean isRunning = true;
	
	public WorkQueue() {
		int tt = Runtime.getRuntime().availableProcessors()-1;
		threads = new Thread[tt];
		
		for(int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(this);
			threads[i].start();
		}
	}

	public int getThreadCount() {
		return threads.length;
	}
	
	public void addJob(Job j) {
		synchronized(jobQueue) {
			jobQueue.add(j);
			jobQueue.notify();
		}
	}
	
	public void shutdown() {
		completeAllJobs();
		
		isRunning = false; 
		synchronized(jobQueue){
			jobQueue.notifyAll();
		}
	}
	
	public boolean performJob() {
		Job j = null;
		synchronized(jobQueue) {
			if(!jobQueue.isEmpty()) {
				j = jobQueue.remove();
			}
		}
		if(j != null) {
			j.perform();
			return true; 
		}
		else {
			return false;
		}
	}
	
	public void completeAllJobs() {
		while(!jobQueue.isEmpty()) {
			performJob();
		}
		
		for(int i = 0; i < threads.length; i++) {
			if(threads[i].getState() != State.WAITING) {
				i=-1;
			}
		}
	}
	
	@Override
	public void run() {
		while(isRunning) {
			if(!performJob()) {
				synchronized(jobQueue) {
					try {
						jobQueue.wait();
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
		}
		}
		
	}

}
