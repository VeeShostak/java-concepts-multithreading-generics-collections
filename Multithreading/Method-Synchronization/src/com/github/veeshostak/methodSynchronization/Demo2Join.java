package com.github.veeshostak.methodSynchronization;


//will be used by multiple threads
class SharedSamle {
	private int counter = 0;
	
	synchronized void incrementCounter(){
		for(int i = 0; i < 1000 ; i++) {
			counter++;
		}
	}
	int getCounter(){
		return counter;
	}
	
}

public class Demo2Join {
	
	public static void main(String[] args) {
		SharedSamle mSharedSample = new SharedSamle();
		
		// use anonymous class
		
		Thread t1 = new Thread(){
			public void run() {
				mSharedSample.incrementCounter();	
			}
			
		};
		Thread t2 = new Thread(){
			public void run() {
				mSharedSample.incrementCounter();	
			}
			
		};
		
		// expected output 2,000. we get diff output on each run, 
		// b/c value of counter var is still getting incremented in another thread, 
		// while value had already been printed
		
		// Solution: (still another problem exists)
		// Use join method: block the main thread until, the threads that you used join on complete their work. 
		
       t1.start();
       t2.start();
      
	
		try {
			t1.join(); // wait for thread t1 to finish.
			t2.join(); // wait for thread t2 to finish.
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		// Another problem:
		// if method is not synchronized, expected output of 2,000 is not guaranteed,
		// not thread-safe, because multiple threads can read counter var concurrently.
		
		// (in assembly to increment variable: laod the var addr (get value), increment (write).
		// hence if not not thread-safe, output can be above or below expected (both can load 0 and write 1...etc)
		
		
		// still inconsistent output. add method synchronization to fix
		
	
       
       System.out.println(mSharedSample.getCounter());
       
	}

}
