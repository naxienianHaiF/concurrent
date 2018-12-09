package com.wjh.learn.concurrent.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo1 {
	
	private static final int CountThread = 100;
	
	public static void main(String[] args) throws Exception {
		
		final CountDownLatch downLatch = new CountDownLatch(CountThread);
		
		ExecutorService service = Executors.newCachedThreadPool();
		
		for (int i = 0; i <CountThread; i++) {
			final int threadNum = i;
			service.execute(() -> {
				try {
					test(threadNum);
				} catch (Exception e) {
					System.out.println("exception..."+e.getMessage());
				} finally {
					downLatch.countDown();
				}
			});
		}
		downLatch.await();
		System.out.println("finish...");
		service.shutdown();
	}
	
	private static void test(int i) throws Exception{
		Thread.sleep(100);
		System.out.println(Thread.currentThread().getName()+"  i   "
				+ "["+System.currentTimeMillis()+"]");
		Thread.sleep(100);
	}

}
