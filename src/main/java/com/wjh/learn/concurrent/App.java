package com.wjh.learn.concurrent;

import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		System.out.println("haha");
		System.out.println("learn concurrent...");
		Integer[] a = new Integer[] { 1, 2 };
		List<Integer> list = Arrays.asList(a);

		list.forEach(e -> System.out.println(e));
	}
}
