package com.wjh.learn.concurrent.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo1 {
	
	private static final int permits = 3;
	private static final int ThreadCount = 20;
	
	public static void main(String[] args) {
		/**
		 * 信号量
		 */
		Semaphore semaphore = new Semaphore(permits);
		
		ExecutorService service = Executors.newCachedThreadPool();
		
		for (int i = 0; i < ThreadCount; i++) {
			final int threadNum = i;
			try {
				//获取一个许可
				semaphore.acquire();
				test(threadNum);
				//释放一个许可
				semaphore.release();
			} catch (Exception e) {
				System.out.println("exception..."+ e.getMessage());
			}
		}
		System.out.println("finish...");
		service.shutdown();
	}
	
	private static void test(int i) throws Exception{
		System.out.println(Thread.currentThread().getName()+"  "+i+"   "
				+ "["+System.currentTimeMillis()+"]");
		Thread.sleep(1000);
	}

}
