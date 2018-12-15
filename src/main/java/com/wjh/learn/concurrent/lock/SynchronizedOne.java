package com.wjh.learn.concurrent.lock;

public class SynchronizedOne {

	private static volatile int  count = 0;
	private Object object = new Object();
	/**
	   * 锁是当前调用对象，this
	 */
	private synchronized int a() {
		return count;
	}
	
	/**
	 * 锁是当前类的Class对象
	 */
	private static synchronized void b() {
		count++;
		System.out.println("b "+ count);
	}
	
	private void c() {
		synchronized (object) {
			System.out.println("同步代码块的锁是同步代码块中的对象。。。");
		}
	}
	
	public static void main(String[] args) throws Exception{
		Thread thread = new Thread(() ->  {
			b();
		});
		
		thread.start();
		Thread.sleep(1000);
		System.out.println(count);
		
		SynchronizedOne one = new SynchronizedOne();
		Thread t2 = new Thread(() ->{
			one.c();
		});
		System.out.println("count   "+ count);
	}
}
