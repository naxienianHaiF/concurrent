package com.wjh.learn.thread;

/**
 * 作为线程任务存在
 * @author JHW
 *
 */
public class RunnableOne implements Runnable{

	@Override
	public void run() {
		while (true) {
			System.out.println(Thread.currentThread().getName() + 
					" thread is running...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Thread thread = new Thread(new RunnableOne());
		thread.start();
	}
}
