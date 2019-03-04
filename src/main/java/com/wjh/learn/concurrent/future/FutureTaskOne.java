package com.wjh.learn.concurrent.future;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureTaskOne {

	public static void main(String[] args) throws Exception {
		
		Callable<Integer> callable = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				System.out.println(Thread.currentThread().getName()+" thread starting...");
				Thread.sleep(3000);
				System.out.println(Thread.currentThread().getName()+" thread finished...");
				return 1;
			}
			
		};
		
		FutureTask<Integer> task = new FutureTask<>(callable);
		Thread thread = new Thread(task);
		thread.start();
		
		System.out.println(Thread.currentThread().getName()+" thread do something...");
		int result = task.get();
		System.out.println("before task method which name is isDone...");
		if (task.isDone()) {
			System.out.println("the task result is "+result);
		}
	}
}
