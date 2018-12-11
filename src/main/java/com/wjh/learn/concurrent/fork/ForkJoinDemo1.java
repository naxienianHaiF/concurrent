package com.wjh.learn.concurrent.fork;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo1 extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 20181211212456L;
	// 阈值
	private static final int THRESHOLD = 2;
	private int start;
	private int end;

	public ForkJoinDemo1(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public static void main(String[] args) {
		
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		ForkJoinDemo1 demo1 = new ForkJoinDemo1(1, 100);
		Future<Integer> future = forkJoinPool.submit(demo1);
		try {
			System.out.println(future.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		//判断任务是否足够小
		boolean canCompute = (end - start) <= THRESHOLD;
		if (canCompute) {
			for (int i=start;i<=end;i++) {
				sum +=i;
			}
		} else {
			int middle = (start+end)/2;
			ForkJoinDemo1 left = new ForkJoinDemo1(start, middle);
			ForkJoinDemo1 right = new ForkJoinDemo1(middle+1, end);
			//执行子任务
			left.fork();
			right.fork();
			//等待子任务执行完，并得到执行结果
			int leftResult = left.join();
			int rightResult = right.join();
			
			sum = leftResult + rightResult;
		}
		return sum;
	}
}
