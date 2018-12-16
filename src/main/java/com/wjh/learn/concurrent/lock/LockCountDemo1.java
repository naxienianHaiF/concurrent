package com.wjh.learn.concurrent.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCountDemo1 {
	
	//请求总数
	public static int threadTotal = 5000;
	//同时并发执行的线程数
	private static int conTotal = 200;
	private static int count = 0;
	
	private final static Lock lock = new ReentrantLock();

	public static void main(String[] args) throws Exception{
		ExecutorService service = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(conTotal);
		final CountDownLatch latch = new CountDownLatch(threadTotal);
		
		for (int i = 0; i < threadTotal; i++) {
			service.execute(() -> {
				try {
					semaphore.acquire();
					add();
					semaphore.release();
				} catch (Exception e) {
					e.printStackTrace();
				}
				latch.countDown();
			});
		}
		latch.await();
		service.shutdown();
		System.out.println("the value of count is " + count);
	}
	
	private static void add() {
		lock.lock();
		try {
			count++;
		} finally {
			lock.unlock();
		}
	}
}
