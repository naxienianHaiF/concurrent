package com.wjh.learn.thread;

public class InnerClassOne {

	public static void main(String[] args) {
		new Thread() {
			public void run() {
				System.out.println("...");
			}; 
		}.start();
		
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName()+"...");
		}).start();
	}
}
