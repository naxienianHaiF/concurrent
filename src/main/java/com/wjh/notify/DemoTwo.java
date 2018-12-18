package com.wjh.notify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * condition
 * @see DemoOne
 * @author JHW
 *
 */
public class DemoTwo {

	private int signal = 1;
	private Lock lock = new ReentrantLock();
	private Condition a = lock.newCondition();
	private Condition b = lock.newCondition();
	private Condition c = lock.newCondition();
	
	public static void main(String[] args) {
		DemoTwo two = new DemoTwo();
		A1 a = new A1(two);
		B1 b = new B1(two);
		C1 c = new C1(two);
		
		Thread t1 = new Thread(a);
		Thread t2 = new Thread(b);
		Thread t3 = new Thread(c);
		
		t1.start();
		t2.start();
		t3.start();
	}
	
	public void a() {
		lock.lock();
		while (signal != 1) {
			try {
				a.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("a");
		signal++;
		b.signal();
		lock.unlock();
	}
	
	public void b() {
		lock.lock();
		while (signal != 2) {
			try {
				b.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("b");
		signal++;
		c.signal();
		lock.unlock();
	}
	
	public  void c() {
		lock.lock();
		while (signal != 3) {
			try {
				c.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("c");
		signal = 1;
		a.signal();
		lock.unlock();
	}
}

class A1 implements Runnable{
	private DemoTwo one;
	
	public A1(DemoTwo one) {
		this.one = one;
	}

	@Override
	public void run() {
		while (true) {
			one.a();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class B1 implements Runnable{
	private DemoTwo one;
	
	public B1(DemoTwo one) {
		this.one = one;
	}

	@Override
	public void run() {
		while (true) {
			one.b();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class C1 implements Runnable{
	private DemoTwo one;
	
	public C1(DemoTwo one) {
		this.one = one;
	}

	@Override
	public void run() {
		while (true) {
			one.c();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
