package com.wjh.learn.concurrent.lock;

import java.util.HashMap;
import java.util.Map;

public class ReadWriteLockDemoTest {

	public static void main(String[] args) {
		ReadWriteLockDemo demo = new ReadWriteLockDemo();
//		writeWrite(demo);
		Map<String, String> map = new HashMap<>() ;
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		demo.set(map);
		
		readRead(demo);
//		readWrite(demo);
		
	}
	
	/**
	 * 测试读锁操作
	 * @param demo
	 */
	private static void readRead(ReadWriteLockDemo demo) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+
						"  "+demo.get("key2"));
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+
						"  "+demo.get("key1"));
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+
						"  "+demo.get("key3"));
			}
		}).start();
	}
	
	/**
	 * 测试读锁、写锁的运行情况。
	 * <br />写锁拿到锁后，读锁也需要等待
	 * @param demo
	 */
 	private static void readWrite(ReadWriteLockDemo demo) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				demo.put("key1", "value1");
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(demo.get("key1"));
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				demo.put("key3", "value3");
			}
		}).start();
	}
	
	/**
	 * 测试写锁，多个写操作运行情况.
	 * <br />某个写锁拿到锁后，其他的写锁线程剧需要等待。
	 * @param demo
	 */
	private static void writeWrite(ReadWriteLockDemo demo) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				demo.put("key1", "value1");
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				demo.put("key2", "value2");
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				demo.put("key3", "value3");
			}
		}).start();
	}
}
