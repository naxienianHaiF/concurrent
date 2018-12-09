package com.wjh.learn.concurrent.aqs;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo3 {
	
	private static final int THREAD_NUM = 10;
	private static final int parties = 5;
	
	private static CyclicBarrier barrier = new CyclicBarrier(parties, ()-> {
		System.out.println("当CyclicBarrier中的线程数到达指定数量时候，优先执行这个里面的Runnable。。。");
	});
	
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
	
	private static void test(int num) throws Exception {
		Thread.sleep(1000);
		System.out.println(Thread.currentThread().getName()+" is ready..."+ num);
		barrier.await();
		System.out.println(Thread.currentThread().getName()+" is continue..."+ num);
	}

}
