package com.github.veeshostak.methodSynchronization;

// will be used by multiple thread
class Shared {
	
	// synchronized
	// When 1 thread starts using, other threads cannot make use of the method, execution time increases
	synchronized void counter(int amount) {
		for (int i = 0; i < 5; i++) {
			System.out.println(amount * i);
			try {
				Thread.sleep(100);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
				
			}
		}
	}
} 


class increment1 implements Runnable {
	private Shared mShared;

	public increment1(Shared mShared) {
		super();
		this.mShared = mShared;
	}

	@Override
	public void run() {
		mShared.counter(5);
		
	}
}

class increment2 implements Runnable {
	private Shared mShared;

	public increment2(Shared mShared) {
		super();
		this.mShared = mShared;
	}

	@Override
	public void run() {
		mShared.counter(4);
		
	}
}

public class Demo {
	public static void main(String[] args) {
		
		Shared tempShared = new Shared();
		Thread t1 = new Thread(new increment1(tempShared));
		Thread t2 = new Thread(new increment2(tempShared));
		t1.start();
		t2.start();
		
		
	}
	
}
