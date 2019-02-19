package com.wjh.learn.thread.join;

public class ThreadJoinDemo {

	public static void main(String[] args) throws Exception {
		MyJoin join1 = new MyJoin();
		MyJoin join2 = new MyJoin();
		MyJoin join3 = new MyJoin();
		
		join1.start();
		join1.join();
		join2.start();
		join2.join();
		join3.start();
	}
}
