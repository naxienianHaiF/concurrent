package com.wjh.learn.thread;

/**
 * 线程中断
 * @author JHW
 *
 */
public class ThreadOne extends Thread{
	
	public ThreadOne() {
	}
	public ThreadOne(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		while(true) {
			System.out.println(getName()+" *** "+"extends thread...");
			if (isInterrupted()) {
				System.out.println(getName()+" ==> "+"is interrupted...");
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Thread thread1 = new ThreadOne("first");
		Thread thread2 = new ThreadOne("second");
		
		thread1.start();
		thread2.start();
		
		thread1.interrupt();
	}

}
