package com.wjh.learn.concurrent.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ZiXuanDemo {

	
	private static int threadTotal = 20;
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0;i< threadTotal; i++) {
			service.execute(() -> {
				System.out.println("test zi xuan...");
			});
		}
		
		service.shutdown();
		while (Thread.activeCount() != 1) {
			
		}
		System.out.println("zi xuan result...");
	}
}
