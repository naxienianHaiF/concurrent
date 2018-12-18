package com.wjh.learn.concurrent.aqs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 每个线程计算一行和，再合并计算结果
 * @author JHW
 *
 */
public class Demo {

	private int[] nums ;
	
	public Demo(int count) {
		nums = new int[count];
	}
	
	public static void main(String[] args) {
		List<String> list = readList();
		int count = list.size();
		Demo demo = new Demo(count);
		
		for (int i=0;i<count;i++) {
			final int j=i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					demo.cal(list.get(j), j);
				}
			}).start();
		}
		
		while (Thread.activeCount() >1) {
			
		}
		demo.sum();
	}
	
	private static List<String> readList() {
		List<String> list = new ArrayList<>();
		String line = null;
		BufferedReader stream = null;
		try {
			 stream= new BufferedReader(new FileReader("E:\\eclipse\\workbench\\concurrent\\src\\main\\java\\com\\wjh\\learn\\concurrent\\aqs\\nums.txt"));
			while ((line = stream.readLine()) != null) {
				list.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	/**
	 * 
	 * @param string 该行数据
	 * @param index 第几行
	 */
	private void cal(String string, int index) {
		if (string == null || string.length() == 0) {
			return;
		}
		String[] strings = string.split(",");
		int total = 0;
		for (String str: strings) {
			total += Integer.parseInt(str);
		}
		nums[index] = total;
		System.out.println(Thread.currentThread().getName() + 
				" 第  "+index+" 行计算内容为："+string+" 计算结果为："+total);
	}
	
	private void sum() {
		int result = 0;
		for (int i=0;i<nums.length;i++) {
			result += nums[i];
		}
		System.out.println("最后求和结果为："+result);
	}
}
