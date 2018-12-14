package com.wjh.learn.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.wjh.learn.concurrent.aqs.MyselfAqs;

/**
 * Callable demo
 * @author JHW
 *
 */
public class CallableOne implements Callable<Integer>{
	
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
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 20; i++) {
			try {
				int result = service.submit(new CallableOne()).get();
				System.out.println(Thread.currentThread().getName()+"..."+ result);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.println("====>" +count);
		service.shutdown();
	}

}
