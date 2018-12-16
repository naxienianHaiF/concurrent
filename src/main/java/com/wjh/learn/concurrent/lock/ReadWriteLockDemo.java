package com.wjh.learn.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

	private Map<String, String> map = new HashMap<>();
	private ReadWriteLock rwl = new ReentrantReadWriteLock();
	private Lock read = rwl.readLock();
	private Lock write = rwl.writeLock();
	
	
	public String get(String key) {
		try {
			read.lock();
			System.out.println(Thread.currentThread().getName()+
					"reading start... ");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return map.get(key);
		} finally {
			read.unlock();
			System.out.println(Thread.currentThread().getName()+
					"reading finished...");
		}
	}
	
	public void put(String key,String value) {
		try {
			write.lock();
			System.out.println(Thread.currentThread().getName()+
					"write start...");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			map.put(key, value);
		} finally {
			write.unlock();
			System.out.println(Thread.currentThread().getName()+
					"write finished...");
		}
	}
	
	public void set(Map<String, String> map) {
		this.map = map;
	}
}
