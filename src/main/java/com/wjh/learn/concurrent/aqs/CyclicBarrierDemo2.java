package com.wjh.learn.concurrent.aqs;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierDemo2 {
	
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
	
	/**
	 * CyclicBarrier 超时等待时间
	 * @param num 
	 * @throws Exception
	 */
	private static void test(int num) throws Exception {
		Thread.sleep(1000);
		System.out.println(Thread.currentThread().getName()+" is ready..."+ num);
		barrier.await(2000, TimeUnit.MILLISECONDS);
		System.out.println(Thread.currentThread().getName()+" is continue..."+ num);
	}

}
