package com.wjh.learn.concurrent.aqs;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo1 {
	
	private static final int THREAD_NUM = 10;
	private static final int parties = 5;
	
	private static CyclicBarrier barrier = new CyclicBarrier(parties);
	
	public static void main(String[] args) throws Exception {
		
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 1; i <= THREAD_NUM; i++) {
			final int temp = i;
			Thread.sleep(1000);
			executor.execute(() -> {
				try {
					test(temp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		executor.shutdown();
	}
	/*
	 *  pool-1-thread-1 is ready...1
		pool-1-thread-2 is ready...2
		pool-1-thread-3 is ready...3
		pool-1-thread-4 is ready...4
		pool-1-thread-5 is ready...5
		pool-1-thread-1 is continue...1
		pool-1-thread-2 is continue...2
		pool-1-thread-3 is continue...3
		pool-1-thread-5 is continue...5
		pool-1-thread-4 is continue...4
		pool-1-thread-6 is ready...6
		pool-1-thread-4 is ready...7
		pool-1-thread-1 is ready...8
		pool-1-thread-3 is ready...9
		pool-1-thread-5 is ready...10
		pool-1-thread-5 is continue...10
		pool-1-thread-4 is continue...7
		pool-1-thread-3 is continue...9
		pool-1-thread-1 is continue...8
		pool-1-thread-6 is continue...6
	 */
	private static void test(int num) throws Exception {
		Thread.sleep(1000);
		System.out.println(Thread.currentThread().getName()+" is ready..."+ num);
		barrier.await();
		System.out.println(Thread.currentThread().getName()+" is continue..."+ num);
	}

}
