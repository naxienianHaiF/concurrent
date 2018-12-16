package com.wjh.learn.concurrent.lock;

public class SynchronizedTwo {

	public static void main(String[] args) {
		SynchronizedTwo two = new SynchronizedTwo();
		new Thread(() ->{
			two.a();
		}).start();
		
		new Thread(() ->{
			two.b();
		}).start();
		
	}
	
	private synchronized void a() {
		System.out.println(Thread.currentThread().getName()+ 
				" method a..."+"\r\n"+System.currentTimeMillis());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private synchronized void b() {
		System.out.println(Thread.currentThread().getName()+ 
				" method b..."+"\r\n"+System.currentTimeMillis());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
