package com.wjh.learn.concurrent.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;

/**
 * @author JHW
 *
 */
public class MyselfLockTest {

	private static int count = 0;
	private Lock lock = new MyselfLock();
	
	public static void main(String[] args) {
		MyselfLockTest test = new MyselfLockTest();
		
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i=0;i<20;i++) {
			service.execute(new Runnable() {
				@Override
				public void run() {
					test.getNext();
				}
			});
		}
		
		service.shutdown();
	}
	
	private int getNext() {
		try {
			lock.lock();
			count++;
			b();
			return count;
		} finally {
			lock.unlock();
		}
	}
	
	private void b() {
		try {
			lock.lock();
			count++;
			System.out.println("method b");
		} finally {
			lock.unlock();
		}
	}
}
