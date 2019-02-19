package com.wjh.learn.thread.join;

public class MyJoin extends Thread{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " 正在执行...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
