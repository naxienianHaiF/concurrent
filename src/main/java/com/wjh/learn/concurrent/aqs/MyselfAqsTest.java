package com.wjh.learn.concurrent.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyselfAqsTest {

	private static MyselfAqs aqs = new MyselfAqs();
	private static int count = 0;
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		
		for (int i = 0; i < 10; i++) {
			service.execute(() -> {
				a();
			});
		}
		System.out.println(count);
		service.shutdown();
	}
	
	private static void a() {
		aqs.lock();
		count++;
		System.out.println(Thread.currentThread().getName()+" "+ count);
		b();
		aqs.unlock();
	}
	
	public static void b() {
		aqs.lock();
		System.out.println("[method b]"+System.currentTimeMillis());
		aqs.unlock();
	}
}
