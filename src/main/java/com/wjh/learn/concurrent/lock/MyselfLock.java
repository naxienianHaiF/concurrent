package com.wjh.learn.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义一个锁，不使用AQS，支持可重入
 * @author JHW
 *
 */
public class MyselfLock implements Lock{
	private volatile int  state = 0;
	private Thread currentThread = null;

	@Override
	public synchronized void lock() {
		Thread temp = Thread.currentThread();
		
		/*
		 * 当线程获取不到锁时，或者不是当前线程的时候，让线程等待
		 */
		while (state != 0 && temp != currentThread) {
			try {
				System.out.println(Thread.currentThread().getName()+
						"该线程在等待获取到锁。。。");
				wait();
				System.out.println(Thread.currentThread().getName()+
						" 线程释放了锁。。。"+
						"release lock finish ...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		state++;
		currentThread = temp;
		System.out.println(Thread.currentThread().getName()+
				"获取到了锁。。。"+state
				+"  >>>"+currentThread.getName());
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		
	}

	@Override
	public boolean tryLock() {
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return false;
	}

	@Override
	public synchronized void unlock() {
		if (currentThread == Thread.currentThread()) {
			state--;
			System.out.println(Thread.currentThread().getName()+
					"释放了锁。。。"+state
					+" >>>"+currentThread.getName());
			if (state == 0) {
				currentThread = null;
				notify();
			}
		}
	}

	@Override
	public Condition newCondition() {
		return null;
	}

	
}
