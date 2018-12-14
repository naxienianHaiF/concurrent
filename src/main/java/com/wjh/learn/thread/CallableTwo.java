package com.wjh.learn.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import com.wjh.learn.concurrent.aqs.MyselfAqs;

public class CallableTwo implements Callable<Integer>{
	
	private static int count = 0;
	private static MyselfAqs aqs = new MyselfAqs();

	@Override
	public Integer call() throws Exception {
		try {
			aqs.lock();
			count++;
			System.out.println("==>>>>>>"+ Thread.currentThread().getName());
			return count;
		} finally {
			aqs.unlock();
		}
	}
	
	public static void main(String[] args) throws Exception {
		CallableTwo two = new CallableTwo();
		
		FutureTask<Integer> task = new FutureTask<>(two);
		Thread thread = new Thread(task);
		
		thread.start();
		
		System.out.println("先执行其他的代码。。。");
		Integer result = task.get();
		System.out.println("最终执行结果为："+result);
	}

}
