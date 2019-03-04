package com.wjh.learn.concurrent.future;

public class MySelfFuture {

	public static void main(String[] args) {
		ProductFactory factory = new ProductFactory();
		Order order = factory.createProduct("cake");
		
		System.out.println("我去上班了，下班来取蛋糕");
		
		Product product = order.getProduct();
		System.out.println("我拿到蛋糕了。"+product);
	}
}
